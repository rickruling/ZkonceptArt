package com.zkncpt.zart.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.zkncpt.zart.AbstractUploadHandler;
import com.zkncpt.zart.group.ImageProcessorGroup;
import com.zkncpt.zart.group.MenuGroup;
import com.zkncpt.zart.subgroup.SupOptionFactory;
import com.zkncpt.zart.subgroup.SupOptionFactory.SUBMENU;
import com.zkncpt.zart.util.Assets;
import com.zkncpt.zart.util.Constants;

public class MainScreen extends Stage implements Screen, InputProcessor {

	private static final String tag = "MainScreen";

	public ImageProcessorGroup imageProcessorGroup;
	public SupOptionFactory subOptionFactory;
	public MenuGroup menuGroup;
	public AbstractUploadHandler uploadHandler;

	public MainScreen(Game zkoncept) {
		super(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		Constants.setGraphicSizes(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		uploadHandler = Assets.getInstance().getUploadHandler();

		init();
		Gdx.input.setInputProcessor(this);
		Gdx.app.log(tag, "created MainScreen");

		imageProcessorGroup = new ImageProcessorGroup(this);
		this.addActor(imageProcessorGroup);

		subOptionFactory = new SupOptionFactory(this);
		subOptionFactory.setSubMenu(SUBMENU.FILE);

		menuGroup = new MenuGroup(this);
		this.addActor(menuGroup);

		Window.WindowStyle ws2 = new Window.WindowStyle();
		ws2.titleFontColor = Color.WHITE;

	}

	private void init() {

	}

	public void removeActors() {

	}

	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		act(Gdx.graphics.getDeltaTime());
		draw();
	}

	@Override
	public void resize(int width, int height) {
		Constants.setGraphicSizes(width, height);
		super.getViewport().update(width, height);
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return super.touchDown(screenX, screenY, pointer, button);
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return super.touchDragged(screenX, screenY, pointer);
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return super.touchUp(screenX, screenY, pointer, button);
	}

	@Override
	public boolean scrolled(int amount) {
		return super.scrolled(amount);
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}

}
