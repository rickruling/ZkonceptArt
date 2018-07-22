package com.zkncpt.zart.widget;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class Spinner extends Table {
	private static final String tag = "Spinner";
	private SpinnerStyle style;
	private Image buttonUp, buttonDown;
	private TextButton textButton;
	private int value = 5;
	private int MAX = 10;
	private int MIN = 1;
	private int STEP = 1;
	public float imageWidth = 200;
	public float imageHeight = 50;

	public Spinner(Skin skin) {
		super(skin);
		setStyle(skin.get(SpinnerStyle.class));
		initialize();
		setSize(getPrefWidth(), getPrefHeight());
		update();
	}

	private void update() {
		clearChildren();
		add(textButton).expandY().fillY().pad(5);
		Table increaseDecrease = new Table();
		increaseDecrease.add(buttonUp).size(textButton.getWidth(), textButton.getHeight() / 2);
		increaseDecrease.row();
		increaseDecrease.add(buttonDown).size(textButton.getWidth(), textButton.getHeight() / 2);
		add(increaseDecrease);
		pack();
	}

	public void draw(Batch batch, float parentAlpha) {
		validate();
		super.draw(batch, parentAlpha);
	}

	public Spinner(Skin skin, String styleName) {
		super(skin);
		setStyle(skin.get(styleName, SpinnerStyle.class));
		initialize();
		setSize(getPrefWidth(), getPrefHeight());
	}

	public Spinner(SpinnerStyle style) {
		setStyle(style);
		initialize();
		setSize(getPrefWidth(), getPrefHeight());
	}

	private void initialize() {
		setTouchable(Touchable.enabled);
		buttonUp.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				if (value < MAX)
					value += STEP;
				textButton.setText(String.valueOf(value));
			}
		});
		buttonDown.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				if (value > MIN)
					value -= STEP;
				textButton.setText(String.valueOf(value));
			}
		});
	}

	public void setStyle(SpinnerStyle style) {
		if (style == null)
			throw new IllegalArgumentException("style cannot be null.");
		this.style = style;
		this.buttonUp = new Image(style.upArrow);
		this.buttonDown = new Image(style.downArrow);
		this.textButton = new TextButton("5", style.textButtonStyle);
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

	static public class SpinnerStyle {
		public Drawable background;
		public Drawable upArrow, downArrow;
		public TextButtonStyle textButtonStyle;
	}
}
