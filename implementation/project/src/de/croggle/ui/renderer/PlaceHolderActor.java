package de.croggle.ui.renderer;

import de.croggle.game.board.InternalBoardObject;

public class PlaceHolderActor extends BoardObjectActor {

	private float siblingX;
	private float siblingW;

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

	@Override
	public void setX(float x) {
		boolean leftOfSibling = x < siblingX;
		super.setWidth(siblingX - x);
		if (leftOfSibling) {
			super.setX(x);
		} else {
			super.setX(siblingX + siblingW);
		}
	}

	@Override
	public void setWidth(float w) {

	}

	public void setSiblingXAndWidth(float x, float w) {
		siblingX = x;
		siblingW = w;
	}
}
