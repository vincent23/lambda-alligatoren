package de.croggle.ui.renderer;

import de.croggle.game.board.InternalBoardObject;

class PlaceHolderActor extends BoardObjectActor {

	private float siblingX;
	private float siblingW;
	private float supposedWidth;

	public PlaceHolderActor(InternalBoardObject object) {
		super(object);
	}

	@Override
	public void setBounds(float x, float y, float w, float h) {
		setX(x);
		setY(y);

		setWidth(w);
		setHeight(h);
	}

	private boolean isLeftOfSibling(float x) {
		return x < siblingX;
	}

	@Override
	public void setX(float x) {
		if (isLeftOfSibling(x)) {
			super.setWidth(supposedWidth + (siblingX - x) + 1);
			super.setX(x);
		} else {
			super.setWidth(supposedWidth + (x - siblingX) + 1);
			super.setX(siblingX + siblingW - 1);
		}
	}

	@Override
	public void setWidth(float w) {
		supposedWidth = w;
		setX(getX());
	}

	@Override
	public void setSize(float width, float height) {
		setWidth(width);
		setHeight(height);
	}

	public void setSiblingXAndWidth(float x, float w) {
		siblingX = x;
		siblingW = w;
	}

	@Override
	public void draw(com.badlogic.gdx.graphics.g2d.SpriteBatch batch,
			float parentAlpha) {
		if (de.croggle.AlligatorApp.DEBUG) {
			batch.draw(de.croggle.data.AssetManager.getInstance()
					.getColorTexture(de.croggle.game.Color.uncolored()),
					getX(), getY(), getWidth() * getScaleX(), getHeight()
							* getScaleY());
		}
	}

	public void setActualX(float x) {
		super.setX(x);
	}
}
