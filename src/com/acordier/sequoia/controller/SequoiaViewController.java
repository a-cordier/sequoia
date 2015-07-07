package com.acordier.sequoia.controller;

import static com.acordier.sequoia.model.SequoiaConstants.DEFAULT_TEMPO;
import static com.acordier.sequoia.model.SequoiaConstants.TEMPO_DOWN_BTN_ID;
import static com.acordier.sequoia.model.SequoiaConstants.TEMPO_MONITOR_ID;
import static com.acordier.sequoia.model.SequoiaConstants.TEMPO_UP_BTN_ID;
import static com.acordier.sequoia.model.SequoiaConstants.TRANSPOSER_DOWN_BTN_ID;
import static com.acordier.sequoia.model.SequoiaConstants.TRANSPOSER_MONITOR_ID;
import static com.acordier.sequoia.model.SequoiaConstants.TRANSPOSER_UP_BTN_ID;

import java.util.List;

import javax.sound.midi.MidiDevice;

import com.acordier.sequoia.common.Colors;
import com.acordier.sequoia.view.SequoiaControlledView;
import com.acordier.sequoia.view.SequoiaControlledView.ControlFrame;
import com.acordier.sequoia.view.SequoiaToggle;

import controlP5.Bang;
import controlP5.Button;
import controlP5.ControlEvent;
import controlP5.ControlListener;
import controlP5.ControlP5;
import controlP5.Group;
import controlP5.ListBox;
import controlP5.Matrix;
import controlP5.Numberbox;

public class SequoiaViewController {

	private SequoiaCoreController coreController;
	private SequoiaControlledView view;
	
	private ControlFrame settingsFrame;
	private int previousMidiOutputDeviceIdx;

	// private Matrix matrix;

	public SequoiaViewController(SequoiaControlledView view) {
		coreController = new SequoiaCoreController();
		previousMidiOutputDeviceIdx = -1;
		this.view = view;
	}
//
//	public void bindMidiOutputDeviceSelector(DropdownList control) {
//		List<MidiDevice> devices = coreController.getMidiOutputDevices();
//		int i = 0;
//		for (MidiDevice device : devices) {
//			control.addItem(device.getDeviceInfo().getName(), i);
//			i++;
//		}
//		final DropdownList _control = control;
//		control.addListener(new ControlListener() {
//			@Override
//			public void controlEvent(ControlEvent event) {
//				int idx = ((int) event.getValue());
//				String deviceName = _control.getItem(idx).getName();
//				System.out.println(deviceName);
//				coreController.setMidiOutputDevice(deviceName);
//			}
//		});
//	}

	public void bindMidiOutputDeviceSelector(ListBox control) {
		List<MidiDevice> devices = coreController.getMidiOutputDevices();
		int i = 0;
		for (MidiDevice device : devices) {
			control.addItem(device.getDeviceInfo().getName(), i);
			i++;
		}
		final ListBox _control = control;
		control.addListener(new ControlListener() {
			@Override
			public void controlEvent(ControlEvent event) {
				int idx = ((int) event.getValue());
				String deviceName = _control.getItem(idx).getName();
				if(previousMidiOutputDeviceIdx>=0){
					_control.getItem(previousMidiOutputDeviceIdx).setColorBackground(ControlP5.getColor().getForeground());
				}
				_control.getItem(idx).setColorBackground(ControlP5.getColor().getActive());
				coreController.setMidiOutputDevice(deviceName);
				previousMidiOutputDeviceIdx = idx;
			}
		});
	}
	public void bindMatrix(Matrix control) {
		control.addListener(new ControlListener() {
			@Override
			public void controlEvent(ControlEvent event) {
				coreController.trigger(Matrix.getX(event.getValue()));
			}
		});
	}

	public void bindTempo(Group controls) {
		final Numberbox monitor = (Numberbox) (controls
				.getController(TEMPO_MONITOR_ID));
		monitor.setValue(DEFAULT_TEMPO);
		controls.getController(TEMPO_UP_BTN_ID).addListener(
				new ControlListener() {
					@Override
					public void controlEvent(ControlEvent event) {
						monitor.setValue(monitor.getValue() + event.getValue());
						view.getMatrixController()
								.setInterval(
										(int) ((60.F / (monitor.getValue() * 4)) * 1000));
					}
				});
		controls.getController(TEMPO_DOWN_BTN_ID).addListener(
				new ControlListener() {
					@Override
					public void controlEvent(ControlEvent event) {
						monitor.setValue(monitor.getValue() - event.getValue());
						view.getMatrixController()
								.setInterval(
										(int) ((60.F / (monitor.getValue() * 4)) * 1000));
					}
				});
		controls.getController(TEMPO_MONITOR_ID).addListener(
				new ControlListener() {

					@Override
					public void controlEvent(ControlEvent event) {
						view.getMatrixController().setInterval(
								(int) ((60.F / (event.getValue() * 4)) * 1000));
					}

				});
	}

	public void bindTransposer(Group controls) {
		final Numberbox monitor = (Numberbox) (controls
				.getController(TRANSPOSER_MONITOR_ID));
		monitor.setValue(coreController.getOctave());
		controls.getController(TRANSPOSER_UP_BTN_ID).addListener(
				new ControlListener() {
					@Override
					public void controlEvent(ControlEvent event) {
						monitor.setValue(monitor.getValue() + event.getValue());
						coreController.setOctave((int) monitor.getValue());
					}
				});
		controls.getController(TRANSPOSER_DOWN_BTN_ID).addListener(
				new ControlListener() {
					@Override
					public void controlEvent(ControlEvent event) {
						monitor.setValue(monitor.getValue() - event.getValue());
						coreController.setOctave((int) monitor.getValue());
					}
				});
	}

	public void bindPlayBtn(Button control) {
		control.addListener(new ControlListener() {
			@Override
			public void controlEvent(ControlEvent event) {
				view.toggleTransport();
			}
		});
	}
	
	public void bindSetting(SequoiaToggle control){
		control.addListener(new ControlListener() {
			@Override
			public void controlEvent(ControlEvent event) {
				if(((Button)event.getController()).isOn()){
					settingsFrame = view.addControlFrame("Settings", 300, 300);
				}else {
					settingsFrame.getFrame().setVisible(false);
				}
				
			}
		});
	}
	
	public void bindBackBtn(Bang control){
		control.addListener(new ControlListener() {
			@Override
			public void controlEvent(ControlEvent event) {
				view.getMatrixController().stop();
			}
		});
	}
}
