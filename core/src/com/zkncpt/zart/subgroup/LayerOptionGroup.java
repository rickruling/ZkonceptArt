package com.zkncpt.zart.subgroup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.zkncpt.zart.screen.MainScreen;
import com.zkncpt.zart.util.Constants;

public class LayerOptionGroup extends AbstractSubOption {
	private static final String tag = "LayerOptionGroup";

	public LayerOptionGroup(MainScreen mainScreen) {
		super(mainScreen);
		initialise();
	}

	protected void initialise() {
		Gdx.app.log(tag, "initialising");
		super.layerName.setText("LAYER");
		Label addLayer = new Label("Add layer", skin);
		addLayer.pack();
		actorYPosition -= addLayer.getHeight();
		addLayer.setPosition(Constants.MENU_WIDTH / 3, actorYPosition);
		this.addActor(addLayer);
	}

}
