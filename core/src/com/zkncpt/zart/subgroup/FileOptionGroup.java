package com.zkncpt.zart.subgroup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.zkncpt.zart.screen.MainScreen;
import com.zkncpt.zart.util.Constants;

public class FileOptionGroup extends AbstractSubOption {
	private static final String tag = "FileOptionGroup";
	Label fileName;

	public FileOptionGroup(MainScreen mainScreen) {
		super(mainScreen);
		initialise();
	}

	protected void initialise() {
		Gdx.app.log(tag, "initialising");
		super.layerName.setText("FILE");
		Label browseLabel = new Label("Upload", skin);
		browseLabel.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				Gdx.app.log(tag, "upload Clicked");
				mainScreen.uploadHandler.browse();
			}
		});
		browseLabel.pack();
		actorYPosition -= browseLabel.getHeight();
		browseLabel.setPosition(Constants.MENU_WIDTH / 3, actorYPosition);
		this.addActor(browseLabel);
	}
}
