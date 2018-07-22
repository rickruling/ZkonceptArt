package com.zkncpt.zart;

import com.badlogic.gdx.graphics.Pixmap;

public abstract class AbstractUploadHandler implements UploadHandler {
	protected Pixmap pixmap;
	public boolean changed = false;
}
