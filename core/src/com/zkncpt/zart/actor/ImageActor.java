package com.zkncpt.zart.actor;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class ImageActor extends Image {

	Rectangle bounds;
	public Pixmap pixmap;

	public Rectangle getBounds() {
		return bounds;
	}

	public ImageActor(Pixmap pixmap) {
		super(new Texture(pixmap));
		this.pixmap = pixmap;
		setBounds(getX(), getY(), getWidth(), getHeight());
		bounds = new Rectangle((int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());
	}

	public void updateBounds() {
		bounds.setX(getX());
		bounds.setY(getY());
	}

	private int getIndex(int pixel, float[] arr, int length) {
		int i = 0;
		while (i < length && pixel > arr[i])
			i++;
		return i;
	}

}
