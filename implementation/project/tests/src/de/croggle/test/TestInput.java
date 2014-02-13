package de.croggle.test;

import android.content.Context;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.AndroidInput;

public class TestInput extends AndroidInput {

	public TestInput(Application activity, Context context, Object view,
			AndroidApplicationConfiguration config) {
		super(activity, context, view, config);
	}

	@Override
	public int getRotation() {
		return 90;
	}

}
