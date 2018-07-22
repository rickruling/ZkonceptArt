package com.zkncpt.zart.subgroup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.zkncpt.zart.screen.MainScreen;
import com.zkncpt.zart.util.Constants;
import com.zkncpt.zart.widget.IntensityUpdaterWidget;
import com.zkncpt.zart.widget.Palette;
import com.zkncpt.zart.widget.PaletteHolderWidget;
import com.zkncpt.zart.widget.Spinner;

public class DecomposeOptionGroup extends AbstractSubOption {
	private static final String tag = "DecomposeOptionGroup";
	Label fileName;
	int decomposeLevelCount;
	int palletePosition;
	IntensityUpdaterWidget widget;
	PaletteHolderWidget palleteGroup;
	int buttonPadding = 2;
	int buttonWidth = (int) (Constants.SUBMENU_WIDTH * 0.8f);
	int buttonStartPosition = (int) (Constants.SUBMENU_WIDTH * 0.1f);

	public DecomposeOptionGroup(MainScreen mainScreen) {
		super(mainScreen);
		initialise();
	}

	/**
	 * Adding <br/>
	 * 1. order, spinner in the horizontal group. <br/>
	 * 2. decompose button
	 */
	protected void initialise() {

		Gdx.app.log(tag, "initialising");
		super.layerName.setText("DECOMPOSE");
		decomposeLevelCount = 5;

		// 1. order, spinner in the horizontal group.
		final Label countLabel = new Label("order : ", skin);
		countLabel.pack();
		Spinner decomposeSpinner = new Spinner(skin);

		HorizontalGroup orderGroup = new HorizontalGroup();
		orderGroup.addActor(countLabel);
		orderGroup.addActor(decomposeSpinner);
		orderGroup.pack();
		actorYPosition -= orderGroup.getHeight();
		orderGroup.setPosition(buttonStartPosition, actorYPosition);
		this.addActor(orderGroup);

		// 2. decompose button
		final TextButton decomposeButton = new TextButton("Decompose", skin);
		decomposeButton.pack();
		decomposeButton.setSize(buttonWidth, decomposeButton.getHeight() * 1.2f);
		actorYPosition -= decomposeButton.getHeight();
		decomposeButton.setPosition(buttonStartPosition, actorYPosition);
		this.addActor(decomposeButton);

		// 3. clear button
		final TextButton publishButton = new TextButton("Publish", skin);
		publishButton.pack();
		publishButton.setSize(buttonWidth, publishButton.getHeight() * 1.2f);
		actorYPosition -= publishButton.getHeight() + buttonPadding;
		publishButton.setPosition(buttonStartPosition, actorYPosition);
		this.addActor(publishButton);
		publishButton.addListener(new PublishClickListener());

		final TextButton choosePalletteButton = new TextButton("Pallette", skin);
		choosePalletteButton.pack();
		choosePalletteButton.setSize(buttonWidth, choosePalletteButton.getHeight() * 1.2f);
		actorYPosition -= choosePalletteButton.getHeight() + buttonPadding;
		choosePalletteButton.setPosition(buttonStartPosition, actorYPosition);
		this.addActor(choosePalletteButton);

		// add PalleteGroup table
		PaletteHolderWidget palleteGroup = createPallete((int) choosePalletteButton.getHeight());
		actorYPosition -= palleteGroup.getHeight() + buttonPadding;
		palleteGroup.setPosition(buttonStartPosition, actorYPosition);
		Gdx.app.log(tag, "Adding palleteGroup at : " + actorYPosition);

		this.addActor(palleteGroup);

		widget = new IntensityUpdaterWidget(skin, mainScreen);
		widget.update(palleteGroup.palletes.get(0).getStyle().colors);
		widget.pack();
		actorYPosition -= widget.getHeight() + buttonPadding;
		widget.setPosition(buttonStartPosition, actorYPosition);
		widget.setWidth(buttonWidth);
		widget.setVisible(false);

		Gdx.app.log(tag, "Adding palleteGroup at : " + actorYPosition);
		this.addActor(widget);

		decomposeSpinner.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				try {
					decomposeLevelCount = Integer.parseInt(countLabel.getText().toString().trim());
				} catch (Exception e) {
					Gdx.app.error(tag, "exception while parsing int : " + e.getMessage());
				}
			}
		});

		decomposeButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				mainScreen.imageProcessorGroup.decompose();
			}
		});
	}

	/**
	 * Add palette element<br/>
	 */
	private PaletteHolderWidget createPallete(int palleteHeight) {
		palleteGroup = new PaletteHolderWidget(skin);
		Palette pallete = new Palette(skin, (int) Constants.SUBMENU_WIDTH, palleteHeight);
		pallete.addListener(new PalleteClickListener(pallete));
		palleteGroup.addPallete(pallete);
		pallete = new Palette(skin, "palleteStyle2", (int) Constants.SUBMENU_WIDTH, palleteHeight);
		pallete.addListener(new PalleteClickListener(pallete));
		palleteGroup.addPallete(pallete);
		palleteGroup.pack();
		return palleteGroup;
	}

	class PalleteClickListener extends ClickListener {
		Palette palette;

		public PalleteClickListener(Palette pallete) {
			super();
			this.palette = pallete;
		}

		@Override
		public void clicked(InputEvent event, float x, float y) {
			super.clicked(event, x, y);
			widget.setVisible(true);
			widget.update(palette.getStyle().colors);
		}
	}

	class PublishClickListener extends ClickListener {

		public PublishClickListener() {
			super();
		}

		@Override
		public void clicked(InputEvent event, float x, float y) {
			super.clicked(event, x, y);
			mainScreen.imageProcessorGroup.sendToserver();
		}
	}
}
