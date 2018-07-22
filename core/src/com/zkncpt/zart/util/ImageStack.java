package com.zkncpt.zart.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;

public class ImageStack extends Stack {

	private static final String tag = "ImageStack";
	Pixmap intialPixmap;
	Image initalActor;
	public Texture initialTexture;

	public ImageStack(float width, float height) {
		super();
		this.setSize(width, height);
	}

	public void initialise(Pixmap pixmap) {
		Gdx.app.log(tag, "initialing actual image in Stack : " + pixmap);
		this.removeActor(initalActor);
		decomposePixmaps();
		intialPixmap = pixmap;
		initalActor = new Image(new Texture(pixmap));
		initalActor.setSize(getWidth(), getHeight());
		addActor(initalActor);
	}

	public void initialise(Texture texture) {
		Gdx.app.log(tag, "initialing actual texture in Stack : "+texture);
		this.removeActor(initalActor);
		decomposePixmaps();
		initalActor = new Image(texture);
		initalActor.setSize(getWidth(), getHeight());
		addActor(initalActor);
	}

	private void decomposePixmaps() {
		// if (this.intialPixmap != null)
		// this.intialPixmap.dispose();
	}

	public void initialise() {
		Gdx.app.log(tag, "initialing texture in Stack : ");
		initalActor = new Image(initialTexture);
		initalActor.setSize(getWidth(), getHeight());
		addActor(initalActor);
	}
}
