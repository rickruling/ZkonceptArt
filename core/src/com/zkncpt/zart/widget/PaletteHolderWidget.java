package com.zkncpt.zart.widget;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.zkncpt.zart.subgroup.DecomposeOptionGroup;

public class PaletteHolderWidget extends Table {
	private static final String tag = "PalleteHolderWidget";

	public Array<Palette> palletes;
	DecomposeOptionGroup decomposeOptionGroup;
	int i = 0;

	public PaletteHolderWidget(Skin skin) {
		super(skin);
		palletes = new Array<Palette>();
	}

	public void addPallete(final Palette pallete) {
		palletes.add(pallete);
		this.add(pallete).pad(2f);
		this.row();
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
	}

	@Override
	protected void drawBackground(Batch batch, float parentAlpha, float x, float y) {
		super.drawBackground(batch, parentAlpha, x, y);
	}

	@Override
	public Actor hit(float x, float y, boolean touchable) {
		return super.hit(x, y, touchable);
	}

	@Override
	public float getPrefWidth() {
		return super.getPrefWidth();
	}

}
