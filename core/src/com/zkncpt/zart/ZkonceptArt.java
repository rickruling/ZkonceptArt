package com.zkncpt.zart;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.zkncpt.zart.screen.MainScreen;
import com.zkncpt.zart.util.Assets;
import com.zkncpt.zart.util.Constants;

public class ZkonceptArt extends Game {

	private static final String tag = "ZkonceptArt";

	BitmapFont font;
	public MainScreen mainScreen;

	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		Gdx.app.debug(tag,
				"creating ZkonceptArt, width : " + Gdx.graphics.getWidth() + " height : " + Gdx.graphics.getHeight());
		Assets.getInstance();
		mainScreen = new MainScreen(this);
		setScreen(mainScreen);
	}

	public ZkonceptArt(AbstractUploadHandler uploadHandler) {
		super();
		if (uploadHandler == null) {
			Gdx.app.log(tag, "no uploadHandler. ");
		} else {
			Assets.getInstance().setUploadHandler(uploadHandler);
		}
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();
	}
}
