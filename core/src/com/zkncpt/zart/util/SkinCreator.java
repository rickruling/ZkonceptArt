package com.zkncpt.zart.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.utils.Array;
import com.zkncpt.zart.widget.Palette.PalletteStyle;
import com.zkncpt.zart.widget.Spinner.SpinnerStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class SkinCreator {

	private static final String tag = "SkinCreator";

	private static Skin skin;
	private static SkinCreator INSTANCE;

	private SkinCreator() {
		Gdx.app.log(tag, "Initialising SkinCreator ");
		skin = new Skin(Gdx.files.internal(Constants.SKIN_LIBGDX_UI),
				new TextureAtlas(Gdx.files.internal(Constants.TEXTURE_ATLAS_LIBGDX_UI)));
		initialise();
	}

	public static Skin getSkin() {
		Gdx.app.log(tag, "returning skin ");
		if (INSTANCE == null) {
			INSTANCE = new SkinCreator();
		}
		return skin;
	}

	public Color getColor(String hexCode) {
		String rHex = hexCode.substring(1, 3);
		String gHex = hexCode.substring(3, 5);
		String bHex = hexCode.substring(5, 7);
		Gdx.app.debug(tag, "rHex : " + rHex + ",gHex : " + gHex + ",bHex : " + bHex);
		int r = Integer.parseInt(rHex, 16);
		int g = Integer.parseInt(gHex, 16);
		int b = Integer.parseInt(bHex, 16);
		return new Color(r, g, b, 1);
	}

	public Color getColor(float r, float g, float b) {
		return new Color(r / 255, g / 255, b / 255, 1);
	}

	private void initialise() {
		// customer Styles
		SpinnerStyle spinnerStyle = new SpinnerStyle();
		spinnerStyle.upArrow = skin.getDrawable("spinnerUp");
		spinnerStyle.downArrow = skin.getDrawable("spinnerDown");
		spinnerStyle.textButtonStyle = skin.get(TextButtonStyle.class);
		skin.add("default", spinnerStyle, SpinnerStyle.class);

		WindowStyle winStyle = skin.get("default", WindowStyle.class);
		winStyle.stageBackground = skin.newDrawable("white", new Color(1f, 1f, 1f, 0.5f));

		PalletteStyle palleteStyle = new PalletteStyle();
		Array<Color> colors = new Array<Color>();
		colors.add(getColor("#FFFF00"));// Color.YELLOW
		colors.add(getColor(250f, 165f, 0f));// Color.ORANGE
		colors.add(getColor(245f, 0f, 0f));// Color.RED
		colors.add(getColor(128f, 0f, 128f));// Color.PURPLE
		colors.add(getColor(5f, 0f, 255f));// Color.BLUE
		colors.add(getColor("#000000"));// Color.BLACK

		palleteStyle.colors = colors;
		skin.add("default", palleteStyle, PalletteStyle.class);

		palleteStyle = new PalletteStyle();
		colors = new Array<Color>();
		colors.add(Color.WHITE);
		colors.add(getColor(159, 106, 226));
		colors.add(getColor(46, 12, 89));
		colors.add(Color.BLACK);
		colors.add(Color.BLACK);
		// colors.add(Color.MAGENTA);
		// colors.add(Color.BROWN);
		palleteStyle.colors = colors;
		skin.add("palleteStyle2", palleteStyle, PalletteStyle.class);

		addDrawablesToSkins();
		Gdx.app.log(tag, "Added to skin : " + this);

	}

	private void addDrawablesToSkins() {
		// WHITE
		// Generate a 1x1 white texture and store it in the skin named "white".
		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));

		pixmap.setColor(Color.GRAY);
		pixmap.fill();
		skin.add("gray", new Texture(pixmap));

		pixmap.setColor(Color.BLACK);
		pixmap.fill();
		skin.add("black", new Texture(pixmap));

	}

}
