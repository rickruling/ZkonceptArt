package com.zkncpt.zart.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.badlogic.gdx.graphics.Pixmap;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.zkncpt.zart.ZkonceptArt;

public class HtmlLauncher extends GwtApplication {
	private static final String tag = "HtmlLauncher";

	HorizontalPanel panel;
	FileUpload fileUpload;
	ZkonceptArt zArt;
	Pixmap pixmap;

	GwtApplicationConfiguration config;

	@Override
	public void onModuleLoad() {
		super.onModuleLoad();
		panel = new HorizontalPanel();
		fileUpload = new FileUpload();
		fileUpload.getElement().setId("fileUpload");
		fileUpload.getElement().setAttribute("style", "display:none");
		fileUpload.getElement().setAttribute("onchange", "changeHandlerFunction()");
		panel.add(fileUpload);

		Element element = Document.get().getElementById("embed-html");
		element.appendChild(panel.getElement());
		log(tag, "onModule Load complete");
	}

	public void changeLabel(String label) {
		if (label != null && label.length() > 0) {
			if (label.contains("\\"))
				label = label.substring(label.lastIndexOf("\\") + 1);
		}
	}

	class ResizeListner implements ResizeHandler {
		@Override
		public void onResize(ResizeEvent event) {
			int width = event.getWidth();
			int height = event.getHeight();
			scaleCanvas(config.width, config.height, width, height);
			log(tag, "resizing the GWT app , h : " + height + "w : " + width);
			zArt.resize(width, height);
			Gdx.gl.glViewport(0, 0, width, height);
		}
	}

	private void scaleCanvas(int oldWidth, int oldHeight, int width, int height) {
		Element element = Document.get().getElementById("embed-html");
		NodeList<Element> nl = element.getElementsByTagName("canvas");

		if (nl != null && nl.getLength() > 0) {
			Element canvas = nl.getItem(0);
			canvas.setAttribute("width", "" + width + "px");
			canvas.setAttribute("height", "" + height + "px");
			canvas.getStyle().setWidth(width, Style.Unit.PX);
			canvas.getStyle().setHeight(height, Style.Unit.PX);
			Window.scrollTo((oldWidth - width) / 2, (oldHeight - height) / 2);
		}
	}

	@Override
	public GwtApplicationConfiguration getConfig() {
		int height = (int) (Window.getClientHeight());
		int width = (int) (Window.getClientWidth());
		config = new GwtApplicationConfiguration(width, height);
		Window.enableScrolling(false);
		Window.setMargin("0");
		Window.addResizeHandler(new ResizeListner());
		return config;
	}

	@Override
	public ApplicationListener createApplicationListener() {
		log(tag, "Initialising the Engine");
		zArt = new ZkonceptArt(new UploadHandlerWebImpl(this));
		return zArt;
	}

	@Override
	public void log(String tag, String message) {
		if (getLogLevel() >= LOG_INFO) {
			consoleLog(tag + ": " + message);
		}
	}

	@Override
	public void log(String tag, String message, Throwable exception) {
		if (getLogLevel() >= LOG_INFO) {
			consoleLog(tag + ": " + message + "\n" + exception.getMessage());
		}
	}

	@Override
	public void error(String tag, String message) {
		if (getLogLevel() >= LOG_ERROR) {
			consoleLog(tag + ": " + message);
		}
	}

	@Override
	public void error(String tag, String message, Throwable exception) {
		if (getLogLevel() >= LOG_ERROR) {
			consoleLog(tag + ": " + message + "\n" + exception.getMessage());
		}
	}

	@Override
	public void debug(String tag, String message) {
		if (getLogLevel() >= LOG_DEBUG) {
			consoleLog(tag + ": " + message);
		}
	}

	@Override
	public void debug(String tag, String message, Throwable exception) {
		if (getLogLevel() >= LOG_DEBUG) {
			consoleLog(tag + ": " + message + "\n" + exception.getMessage());
		}
	}
}