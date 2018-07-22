package com.zkncpt.zart.widget;
//package com.zkncpt.zart.widget;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.g2d.Batch;
//import com.badlogic.gdx.scenes.scene2d.Actor;
//import com.badlogic.gdx.scenes.scene2d.InputEvent;
//import com.badlogic.gdx.scenes.scene2d.ui.Label;
//import com.badlogic.gdx.scenes.scene2d.ui.Skin;
//import com.badlogic.gdx.scenes.scene2d.ui.Table;
//import com.badlogic.gdx.scenes.scene2d.ui.Window;
//import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
//import com.badlogic.gdx.utils.Array;
//import com.zkncpt.zart.subgroup.DecomposeOptionGroup;
//import com.zkncpt.zart.widget.Pallette.PalletteStyle;
//
//public class PalletePopUp extends Window {
//	private static final String tag = "PalletePopUp";
//
//	public Array<Pallette> palletes;
//	DecomposeOptionGroup decomposeOptionGroup;
//	int i = 0;
//
//	public PalletePopUp(String title, Skin skin, DecomposeOptionGroup decomposeOptionGroup) {
//		super(title, skin);
//		palletes = new Array<Pallette>();
//		this.decomposeOptionGroup = decomposeOptionGroup;
//	}
//
//	public void addPallete(final Pallette pallete) {
//		pallete.setUserObject(new Integer(i++));
//		Gdx.app.log(tag, "Added pallete : " + pallete.getUserObject());
//		palletes.add(pallete);
//		PalletteStyle palleteStyle = pallete.getStyle();
//		Pallette newPallete = new Pallette(palleteStyle, (int) pallete.getWidth(), (int) pallete.getHeight());
//		this.add(newPallete).pad(5f);
//		newPallete.addListener(new ClickListener() {
//			@Override
//			public void clicked(InputEvent event, float x, float y) {
//				super.clicked(event, x, y);
//				Gdx.app.log(tag, "upadating to : " + pallete.getUserObject());
//				decomposeOptionGroup.updatePallete(palletes.get((Integer) pallete.getUserObject()));
//				setVisible(false);
//			}
//		});
//		this.row();
//	}
//
//	@Override
//	public void setStyle(WindowStyle style) {
//		super.setStyle(style);
//	}
//
//	@Override
//	public WindowStyle getStyle() {
//		return super.getStyle();
//	}
//
//	@Override
//	public void draw(Batch batch, float parentAlpha) {
//		super.draw(batch, parentAlpha);
//	}
//
//	@Override
//	protected void drawStageBackground(Batch batch, float parentAlpha, float x, float y, float width, float height) {
//		super.drawStageBackground(batch, parentAlpha, x, y, width, height);
//	}
//
//	@Override
//	protected void drawBackground(Batch batch, float parentAlpha, float x, float y) {
//		super.drawBackground(batch, parentAlpha, x, y);
//	}
//
//	@Override
//	public Actor hit(float x, float y, boolean touchable) {
//		return super.hit(x, y, touchable);
//	}
//
//	@Override
//	public boolean isMovable() {
//		return super.isMovable();
//	}
//
//	@Override
//	public void setMovable(boolean isMovable) {
//		super.setMovable(isMovable);
//	}
//
//	@Override
//	public boolean isModal() {
//		return super.isModal();
//	}
//
//	@Override
//	public void setModal(boolean isModal) {
//		super.setModal(isModal);
//	}
//
//	@Override
//	public void setKeepWithinStage(boolean keepWithinStage) {
//		super.setKeepWithinStage(keepWithinStage);
//	}
//
//	@Override
//	public boolean isResizable() {
//		return super.isResizable();
//	}
//
//	@Override
//	public void setResizable(boolean isResizable) {
//		super.setResizable(isResizable);
//	}
//
//	@Override
//	public void setResizeBorder(int resizeBorder) {
//		super.setResizeBorder(resizeBorder);
//	}
//
//	@Override
//	public boolean isDragging() {
//		return super.isDragging();
//	}
//
//	@Override
//	public float getPrefWidth() {
//		return super.getPrefWidth();
//	}
//
//	@Override
//	public Table getTitleTable() {
//		return super.getTitleTable();
//	}
//
//	@Override
//	public Label getTitleLabel() {
//		return super.getTitleLabel();
//	}
//
//}
