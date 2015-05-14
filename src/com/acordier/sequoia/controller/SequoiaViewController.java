package com.acordier.sequoia.controller;

import java.util.List;

import javax.sound.midi.MidiDevice;

import controlP5.ControlEvent;
import controlP5.ControlListener;
import controlP5.DropdownList;
import controlP5.Matrix;
import controlP5.Numberbox;

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
		final Matrix _control = control;
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
				System.out.println(event.getValue() / 120 * 1000);
			}
		});
	}
}
