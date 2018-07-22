package com.zkncpt.zart.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Disposable;
import com.zkncpt.zart.AbstractUploadHandler;

public class Assets implements Disposable, AssetErrorListener {
	private static final String tag = "Assets";

	public static Assets INSTANCE;// = new Assets();
	AbstractUploadHandler uploadHandler;
	Texture img ;
	Skin skin;

	private Assets() {
//		Gdx.files.internal("badlogic.jpg");
//		img = new Texture(Gdx.files.internal("badlogic.jpg"));
//		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		// Gdx.app.log(tag, "test");
		// Gdx.app.log(tag, "" + Gdx.files.internal("badlogic.jpg"));
		// skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		// skin = new Skin(Gdx.files.internal(Constants.SKIN_LIBGDX_UI),
		// new
		// TextureAtlas(Gdx.files.internal(Constants.TEXTURE_ATLAS_LIBGDX_UI)));

		// skin =;
	}

	// Skin skin = SkinCreator.getSkin();
	// Skin skin = new Sk

	// Skin skin = new Skin(Constants.SKIN_LIBGDX_UI,
	// Constants.TEXTURE_ATLAS_LIBGDX_UI);

	public AbstractUploadHandler getUploadHandler() {
		return uploadHandler;
	}

	public Skin getSkin() {
		return skin;
	}

	public void setSkin(Skin skin) {
		this.skin = skin;
	}

	public void setUploadHandler(AbstractUploadHandler uploadHandler) {
		System.out.println("setting upload handler");
		this.uploadHandler = uploadHandler;
	}

	public static Assets getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Assets();
		}
		return INSTANCE;
	}

	@Override
	public void error(@SuppressWarnings("rawtypes") AssetDescriptor asset, Throwable throwable) {
	}

	@Override
	public void dispose() {

	}

	public Pixmap createRectanglePixmap(float x, float y, Color color, float alpha) {
		Pixmap pixmap = new Pixmap((int) x, (int) y, Format.RGBA8888);
		pixmap.setColor(color.r, color.g, color.b, alpha);
		pixmap.fillRectangle(0, 0, (int) x, (int) y);
		return pixmap;
	}

	public Pixmap createTiledPixmap(float x, float y) {
		Pixmap pixmap = new Pixmap((int) x, (int) y, Format.RGBA8888);
		Color colorA = Color.WHITE;
		Color colorB = Color.GRAY;
		pixmap.setColor(colorA);
		pixmap.fillRectangle(0, 0, (int) x / 2, (int) y / 2);
		pixmap.fillRectangle((int) x / 2, (int) y / 2, (int) x, (int) y);
		pixmap.setColor(colorB);
		pixmap.fillRectangle((int) 0, (int) y / 2, (int) x / 2, (int) y);
		pixmap.fillRectangle((int) x / 2, (int) 0, (int) x, (int) y / 2);
		return pixmap;
	}

	public Image createRectangleImage(float x, float y, Color color, float alpha) {
		return new Image(new Texture(createRectanglePixmap(x, y, color, alpha)));
	}

}
