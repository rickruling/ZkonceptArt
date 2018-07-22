package com.zkncpt.zart.client;

import com.badlogic.gdx.graphics.Pixmap;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Image;
import com.zkncpt.zart.AbstractUploadHandler;

public class UploadHandlerWebImpl extends AbstractUploadHandler {
	private static final String tag = "UploadHandlerWebImpl";

	private String imageAsString;
	private HtmlLauncher htmlLauncher;
	private Timer elapsedTimer;
	private Image image;

	public UploadHandlerWebImpl(HtmlLauncher htmlLauncher) {
		this.htmlLauncher = htmlLauncher;
		intialiseFunction();
	}

	public native void intialiseFunction()/*-{
		console.log("JSNI: initialising function");
		$wnd.uploadHandler = this;
		$wnd.changeHandlerFunction = function changeHandlerFunction() {
			var uploadHandler = $wnd.uploadHandler;
			var fileElement = $doc.getElementById('fileUpload');
			var reader = new FileReader();
			reader.onload = function() {
				var dataURL = reader.result;
				console.log("JSNI: reader complete");
				uploadHandler.@com.zkncpt.zart.client.UploadHandlerWebImpl::setImageAsString(Ljava/lang/String;)(dataURL);
			};
			if (fileElement.files.length == 1) {
				reader.readAsDataURL(fileElement.files[0]);
			}
		}
	}-*/;

	public String getImageAsString() {
		return imageAsString;
	}

	public native void triggerUploadWindow()/*-{
		var handle = $doc.getElementById('fileUpload');
		handle.click();
	}-*/;

	public void setImageAsString(String imageAsString) {
		this.imageAsString = imageAsString;
		htmlLauncher.log(tag, "image read complete : " + imageAsString.substring(0, 20));
		image = new Image();
		image.setUrl(imageAsString);

		elapsedTimer = new Timer() {
			public void run() {
				loadImage();
			}
		};
		elapsedTimer.scheduleRepeating(100);
	}

	private void loadImage() {
		ImageElement ie = ImageElement.as(image.getElement());
		htmlLauncher.log(tag, "image element: " + ie + " url : " + imageAsString.substring(0, 20));
		pixmap = new Pixmap(ie);
		htmlLauncher.log(tag,
				"pixmaP before processing: height : " + this.pixmap.getHeight() + " width : " + this.pixmap.getWidth());
		if (pixmap.getHeight() > 0) {
			elapsedTimer.cancel();
			this.setPixmap(pixmap);
		}
	}

	@Override
	public void setPixmap(Pixmap pixmap) {
		this.pixmap = pixmap;
		this.changed = true;
	}

	@Override
	public void browse() {
		htmlLauncher.log(tag, "opening upload window");
		triggerUploadWindow();
	}

	@Override
	public void getFileName() {
	}

	@Override
	public Pixmap getPixmap() {
		return super.pixmap;
	}
	
	@Override
	public String getDataURL(Pixmap pixmap) {
		return pixmap.getCanvasElement().toDataUrl("image/png");
	}

	@Override
	public void save(Pixmap pixmap) {
		htmlLauncher.log(tag, "saving : " + pixmap);

		Window.open(pixmap.getCanvasElement().toDataUrl("image/png"), "_blank", "");
		htmlLauncher.log(tag, "saving : " + pixmap.getCanvasElement().toDataUrl("image/png"));

		// ie.get

		// PixmapIO.writePNG(Gdx.files.internal("internal.png"), pixmap);
		// TODO Auto-generated method stub

	}

}
