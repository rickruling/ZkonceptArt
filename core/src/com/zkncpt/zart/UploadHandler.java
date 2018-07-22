package com.zkncpt.zart;

import com.badlogic.gdx.graphics.Pixmap;

public interface UploadHandler {

	public String getImageAsString();

	public Pixmap getPixmap();

	public void setPixmap(Pixmap pixmap);

	public void browse();

	public void getFileName();

	public void save(Pixmap pixmap);

	public String getDataURL(Pixmap pixmap);
}
