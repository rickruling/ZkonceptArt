package com.zkncpt.zart.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.zkncpt.zart.AbstractUploadHandler;

public class UploadHandlerAppImpl extends AbstractUploadHandler {
	
	private static final String tag = "UploadHandlerAppImpl";

	public boolean upload() {
		return false;

	}

	@Override
	public String getImageAsString() {
		return null;
	}

	@Override
	public void setPixmap(Pixmap pixmap) {
		this.changed = true;
		this.pixmap = pixmap;
	}

	@Override
	public void browse() {
		Pixmap pixmap = new Pixmap(Gdx.files.internal("testImages/alia.jpg"));
		setPixmap(pixmap);
	}

	@Override
	public void getFileName() {
	}

	@Override
	public Pixmap getPixmap() {
		return this.pixmap;
	}

	@Override
	public void save(Pixmap pixmap) {
		
		PixmapIO.writePNG(Gdx.files.external("pixmapBufferFile.png"), pixmap);
		Gdx.app.log(tag, "saving...");
	}

	@Override
	public String getDataURL(Pixmap pixmap) {
		return "";
	}

}
