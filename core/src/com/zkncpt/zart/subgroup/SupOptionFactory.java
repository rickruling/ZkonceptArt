package com.zkncpt.zart.subgroup;

import com.zkncpt.zart.screen.MainScreen;

public class SupOptionFactory {
	private FileOptionGroup fileOption;
	private DecomposeOptionGroup decomposeOption;
	private LayerOptionGroup layerOption;
	private AbstractSubOption currentOption;
	private MainScreen mainScreen;

	public SupOptionFactory(MainScreen mainScreen) {
		super();
		this.mainScreen = mainScreen;
		fileOption = new FileOptionGroup(mainScreen);
		layerOption = new LayerOptionGroup(mainScreen);
		decomposeOption = new DecomposeOptionGroup(mainScreen);
		fileOption.setVisible(false);
		layerOption.setVisible(false);
		decomposeOption.setVisible(false);
		mainScreen.addActor(fileOption);
		mainScreen.addActor(layerOption);
		mainScreen.addActor(decomposeOption);
	}

	public enum SUBMENU {
		FILE, LAYER, DECOMPOSE
	};

	public void setSubMenu(SUBMENU submenu) {
		if (!(currentOption == null))
			currentOption.setVisible(false);
		switch (submenu) {
		case FILE:
			currentOption = fileOption;
			break;
		case LAYER:
			currentOption = layerOption;
			break;
		case DECOMPOSE:
			currentOption = decomposeOption;
			break;
		default:
			break;
		}
		currentOption.setVisible(true);
	}

}
