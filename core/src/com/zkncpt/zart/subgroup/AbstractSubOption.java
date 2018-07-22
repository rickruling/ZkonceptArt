package com.zkncpt.zart.subgroup;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.zkncpt.zart.screen.MainScreen;
import com.zkncpt.zart.util.Constants;
import com.zkncpt.zart.util.SkinCreator;

public abstract class AbstractSubOption extends Table {
	private static final String tag = "AbstractSubOption";

	protected Skin skin;
	protected MainScreen mainScreen;
	TextButton layerName;
	protected int actorYPosition = (int) (Constants.HEIGHT);

	public AbstractSubOption(MainScreen mainScreen) {
		super();
		this.mainScreen = mainScreen;
		this.skin = SkinCreator.getSkin();
		setSize(Constants.SUBMENU_WIDTH, Constants.HEIGHT);
		setPosition(Constants.MENU_WIDTH, 0);
		Drawable background = SkinCreator.getSkin().newDrawable("white", Color.DARK_GRAY);
		setBackground(background);
		layerName = new TextButton("FILE", skin);
		layerName.pack();
		layerName.setSize(Constants.SUBMENU_WIDTH, layerName.getHeight() * 2);
		actorYPosition -= layerName.getHeight();
		layerName.setPosition(0, actorYPosition);
		addActor(layerName);
//		initialise();
	}

//	protected abstract void initialise();

}
