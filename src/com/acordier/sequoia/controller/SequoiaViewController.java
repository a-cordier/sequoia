package com.acordier.sequoia.controller;

import static com.acordier.sequoia.model.SequoiaConstants.DEFAULT_TEMPO;

import java.util.List;

import javax.sound.midi.MidiDevice;

import com.acordier.sequoia.view.SequoiaNumberBox;

import controlP5.ControlEvent;
import controlP5.ControlListener;
import controlP5.DropdownList;
import controlP5.Group;
import controlP5.Matrix;
import controlP5.Numberbox;

import static com.acordier.sequoia.model.SequoiaConstants.TRANSPOSER_MONITOR_ID;
import static com.acordier.sequoia.model.SequoiaConstants.TRANSPOSER_DOWN_BTN_ID;
import static com.acordier.sequoia.model.SequoiaConstants.TRANSPOSER_UP_BTN_ID;

public class SequoiaViewController {

	private SequoiaCoreController coreController;
	private Matrix matrix;

	public SequoiaViewController() {
		coreController = new SequoiaCoreController();
	}

	public void bindMidiOutputDeviceSelector(DropdownList control) {
		List<MidiDevice> devices = coreController.getMidiOutputDevices();
		int i = 0;
		for (MidiDevice device : devices) {
			control.addItem(device.getDeviceInfo().getName(), i);
			i++;
		}
		final DropdownList _control = control;
		control.addListener(new ControlListener() {
			@Override
			public void controlEvent(ControlEvent event) {
				int idx = ((int) event.getValue());
				String deviceName = _control.getItem(idx).getName();
				System.out.println(deviceName);
				coreController.setMidiOutputDevice(deviceName);
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
		this.matrix = control;
	}

	public void bindTempo(Numberbox control) {
		control.addListener(new ControlListener() {
			@Override
			public void controlEvent(ControlEvent event) {
				matrix.setInterval((int) ((60.F / (event.getValue() * 4)) * 1000));
			}
		});
		control.setValue(DEFAULT_TEMPO);
	}
	
	public void bindTransposer(Group controls) {
		final SequoiaNumberBox monitor = (SequoiaNumberBox)(controls.getController(TRANSPOSER_MONITOR_ID));
		monitor.setValue(coreController.getOctave());
		controls.getController(TRANSPOSER_UP_BTN_ID).addListener(new ControlListener() {
			@Override
			public void controlEvent(ControlEvent event) {
				monitor.setValue(monitor.getValue() + event.getValue());
				coreController.setOctave((int)monitor.getValue());
			}
		});
		controls.getController(TRANSPOSER_DOWN_BTN_ID).addListener(new ControlListener() {
			@Override
			public void controlEvent(ControlEvent event) {
				monitor.setValue(monitor.getValue() - event.getValue());
				coreController.setOctave((int)monitor.getValue());
			}
		});
	}
}
