package com.zkncpt.zart.widget;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;
import com.zkncpt.zart.util.Assets;

public class Palette extends Table {
	private static final String tag = "Pallette";
	private PalletteStyle style;
	private Array<Image> colors;
	public float imageWidth = 200;
	public float imageHeight = 50;

	public Palette(Skin skin, int width, int height) {
		super(skin);
		colors = new Array<Image>();
		setStyle(skin.get(PalletteStyle.class), width, height);
		setSize(getPrefWidth(), getPrefHeight());
		update();
	}

	public Palette(Skin skin, String styleName, int width, int height) {
		super(skin);
		colors = new Array<Image>();
		setStyle(skin.get(styleName, PalletteStyle.class), width, height);
		setSize(getPrefWidth(), getPrefHeight());
		update();
	}

	public Palette(PalletteStyle style, int elementWidth, int elementHeight) {
		colors = new Array<Image>();
		setStyle(style, elementWidth, elementHeight);
		setSize(getPrefWidth(), getPrefHeight());
		update();
	}

	private void update() {
		Gdx.app.log(tag, "updating");
		clearChildren();
		for (int i = 0; i < colors.size; i++) {
			add(colors.get(i)).pad(1f);
			Gdx.app.log(tag, "adding Image : " + i);
		}
		row();
		pack();
		Gdx.app.log(tag, " height : " + this.getHeight() + " width : " + this.getWidth());
	}

	public void draw(Batch batch, float parentAlpha) {
		validate();
		super.draw(batch, parentAlpha);
	}

	private void setStyle(PalletteStyle style, int width, int height) {
		if (style == null)
			throw new IllegalArgumentException("style cannot be null.");
		this.style = style;
		float padding = 1f;
		int cellWidth = (int) (width / style.colors.size - style.colors.size * padding);
		for (int i = 0; i < style.colors.size; i++) {
			Image image = new Image(new Texture(
					Assets.getInstance().createRectanglePixmap(cellWidth, height, style.colors.get(i), 1f)));
			this.colors.add(image);
		}
		setBackground(style.background);
		invalidateHierarchy();
	}

	public float getPrefWidth() {
		float width = super.getPrefWidth();
		if (style.background != null)
			width = Math.max(width, style.background.getMinWidth());
		return width;
	}

	public float getPrefHeight() {
		float height = super.getPrefHeight();
		if (style.background != null)
			height = Math.max(height, style.background.getMinHeight());
		return height;
	}

	public float getMinWidth() {
		return getPrefWidth();
	}

	public float getMinHeight() {
		return getPrefHeight();
	}

	public PalletteStyle getStyle() {
		return style;
	}

	static public class PalletteStyle {
		public Drawable background;
		public Array<Color> colors;
	}
}
