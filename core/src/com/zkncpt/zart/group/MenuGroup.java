package com.zkncpt.zart.group;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.zkncpt.zart.screen.MainScreen;
import com.zkncpt.zart.subgroup.SupOptionFactory.SUBMENU;
import com.zkncpt.zart.util.Constants;
import com.zkncpt.zart.util.SkinCreator;

public class MenuGroup extends Group {
	private static final String tag = "MenuGroup";
	private static final int UPPER_PADDING = (int) Constants.MENU_WIDTH;
	private int actorYPosition = (int) (Constants.HEIGHT - UPPER_PADDING);
	private Skin skin;
	private MainScreen mainScreen;
	final ImageButton fileOptions;
	final ImageButton layerOptions;
	final ImageButton decomposeOptions;

	public MenuGroup(MainScreen mainScreen) {
		this.mainScreen = mainScreen;
		this.skin = SkinCreator.getSkin();
		setSize(Constants.MENU_WIDTH, Constants.HEIGHT);
		setPosition(0, 0);
		fileOptions = new ImageButton(skin, "file");
		fileOptions.getImage().setColor(Color.BLACK);
		layerOptions = new ImageButton(skin, "layer");
		layerOptions.getImage().setColor(Color.BLACK);
		decomposeOptions = new ImageButton(skin, "decompose");
		decomposeOptions.getImage().setColor(Color.BLACK);

		initialise();
		updateMenuStatus(fileOptions, SUBMENU.FILE);
	}

	private void initialise() {
		placeActor(fileOptions);
		placeActor(layerOptions);
		placeActor(decomposeOptions);
		addListeners();
	}

	private void placeActor(ImageButton imageButton) {
		actorYPosition -= imageButton.getHeight();
		imageButton.setSize(Constants.MENU_WIDTH,
				imageButton.getHeight() / imageButton.getWidth() * Constants.MENU_WIDTH);
		imageButton.setPosition(0, actorYPosition);
		addActor(imageButton);
	}

	private void addListeners() {
		layerOptions.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				updateMenuStatus(layerOptions, SUBMENU.LAYER);
			}
		});

		fileOptions.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				updateMenuStatus(fileOptions, SUBMENU.FILE);
			}
		});
		decomposeOptions.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				updateMenuStatus(decomposeOptions, SUBMENU.DECOMPOSE);
			}
		});
	}

	private void updateMenuStatus(ImageButton imageButton, SUBMENU submenu) {
		fileOptions.setChecked(false);
		fileOptions.getImage().setColor(Color.BLACK);
		layerOptions.setChecked(false);
		layerOptions.getImage().setColor(Color.BLACK);
		decomposeOptions.setChecked(false);
		decomposeOptions.getImage().setColor(Color.BLACK);
		imageButton.setChecked(true);
		imageButton.getImage().setColor(Color.WHITE);
		mainScreen.subOptionFactory.setSubMenu(submenu);
	}
}
