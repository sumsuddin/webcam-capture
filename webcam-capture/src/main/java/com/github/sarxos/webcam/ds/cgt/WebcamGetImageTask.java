package com.github.sarxos.webcam.ds.cgt;

import java.awt.image.BufferedImage;

import com.github.sarxos.webcam.Frame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.sarxos.webcam.WebcamDevice;
import com.github.sarxos.webcam.WebcamDriver;
import com.github.sarxos.webcam.WebcamTask;


public class WebcamGetImageTask extends WebcamTask {

	private static final Logger LOG = LoggerFactory.getLogger(WebcamGetImageTask.class);

	private volatile Frame frame = null;

	public WebcamGetImageTask(WebcamDriver driver, WebcamDevice device) {
		super(driver, device);
	}

	public Frame getFrame() {

		try {
			process();
		} catch (InterruptedException e) {
			LOG.debug("Interrupted exception", e);
			return null;
		}

		return frame;
	}

	@Override
	protected void handle() {

		WebcamDevice device = getDevice();
		if (!device.isOpen()) {
			return;
		}

		frame = device.getFrame();
	}
}
