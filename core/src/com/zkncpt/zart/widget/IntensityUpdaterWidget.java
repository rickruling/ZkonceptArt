package com.zkncpt.zart.widget;

import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.zkncpt.zart.screen.MainScreen;

public class IntensityUpdaterWidget extends Table {
	private static final String tag = "IntensityUpdaterWidget";
	Array<Slider> sliders;
	SliderStyle style;
	Skin skin;
	private ShaderProgram shader;
//	private ShaderProgram clearShader;
	float[] shaderIntensity;
	MainScreen mainScreen;

	public IntensityUpdaterWidget(Skin skin, MainScreen mainScreen) {
		super(skin);
		this.skin = skin;
		this.shader = mainScreen.imageProcessorGroup.getShader();
//		this.clearShader = mainScreen.imageProcessorGroup.getClearShader();
		this.mainScreen = mainScreen;
		setStyle(skin.get("default-horizontal", SliderStyle.class));
		initialize();
		setSize(getPrefWidth(), getPrefHeight());
	}

	public void update(Array<Color> colors) {
		Gdx.app.debug(tag, "Updating Intensity");
		clearChildren();
		if (shaderIntensity == null) {
			resetShaderIntensity(colors.size);
		}
		recolorShader(colors);
		for (int i = 0; i < colors.size; i++) {
			SliderStyle style = new SliderStyle(this.style);
			style.knob = skin.newDrawable("white", colors.get(i));
			style.knobBefore = skin.newDrawable("white", colors.get(i));
			style.knobOver = skin.newDrawable("white", colors.get(i));
			style.knobDown = skin.newDrawable("white", colors.get(i));
			Slider slider = new Slider(0, 1, (float) 0.01, false, style);
			slider.setValue(shaderIntensity[i]);
			slider.addListener(new SliderChangeListener(slider, i));
			add(slider).pad(2f);
			row();
		}
		initialiseShader(colors);
		pack();
	}

	private void initialiseShader(Array<Color> colors) {
		shader.begin();
		for (int i = 0; i < colors.size; i++) {
			shader.setUniformf("u_colors[" + i + "]", colors.get(i));
			shader.setUniformf("u_colorIntensity[" + i + "]", shaderIntensity[i]);
			Gdx.app.log(tag, "color : " + colors.get(i) + " intensity : " + shaderIntensity[i]);
		}
		shader.end();
//		clearShader.begin();
//		for (int i = 0; i < colors.size; i++) {
//			clearShader.setUniformf("u_colors[" + i + "]", colors.get(i));
//		}
//		clearShader.end();
		mainScreen.imageProcessorGroup.decompose();
	}

	private void resetShaderIntensity(int length) {
		shaderIntensity = new float[10];
		for (int i = 0; i < length; i++) {
			shaderIntensity[i] = (float) (length - i - 1) / (length - 1);
		}
		Gdx.app.log(tag, "intensity : " + Arrays.toString(shaderIntensity));
	}

	public void draw(Batch batch, float parentAlpha) {
		validate();
		super.draw(batch, parentAlpha);
	}

	public IntensityUpdaterWidget(SliderStyle style) {
		setStyle(style);
		initialize();
		setSize(getPrefWidth(), getPrefHeight());
	}

	private void initialize() {
		setTouchable(Touchable.enabled);
	}

	public void setStyle(SliderStyle style) {
		if (style == null)
			throw new IllegalArgumentException("style cannot be null.");
		this.style = style;
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

	public void updateShader(int index, float value) {
		Gdx.app.log(tag, "updating : index : " + index + " value : " + value);
		shader.begin();
		shader.setUniformf("u_colorIntensity[" + index + "]", value);
		Gdx.app.log(tag, "changing intensity i: " + index + " intensity : " + value);
		shaderIntensity[index] = value;
		shader.end();
		mainScreen.imageProcessorGroup.decompose();
	}

	private void recolorShader(Array<Color> colors) {
		shader.begin();
		for (int i = 0; i < colors.size; i++) {
			shader.setUniformf("u_colors[" + i + "]", colors.get(i));
			shader.setUniformf("u_colorIntensity[" + i + "]", shaderIntensity[i]);
			Gdx.app.log(tag, " re - color : " + colors.get(i) + " intensity : " + shaderIntensity[i]);
		}
		shader.end();
//		clearShader.begin();
//		for (int i = 0; i < colors.size; i++) {
//			clearShader.setUniformf("u_colors[" + i + "]", colors.get(i));
//		}
//		clearShader.end();
		mainScreen.imageProcessorGroup.decompose();
	}

	private class SliderChangeListener extends ChangeListener {
		int index;
		Slider slider;

		public SliderChangeListener(Slider slider, int i) {
			index = i;
			this.slider = slider;
		}

		@Override
		public void changed(ChangeEvent event, Actor actor) {
			Gdx.app.debug(tag, "Slider change event");
			updateShader(index, slider.getValue());
		}

	}

}
