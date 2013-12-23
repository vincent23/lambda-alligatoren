package de.croggle;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;

/**
 * Android backend that initializes the central ApplicationListener.
 * 
 * @depend - <creates> - AlligatorApp
 */
public class MainActivity extends AndroidApplication {

	/**
	 * Initializes the central ApplicationListener. Is called by the android
	 * lifecycle as soon as the app is started. On return, the inner app
	 * lifecycle of ApplicationListener is started.
	 */
	@Override
	protected void onCreate(Bundle savedInstance) {

	}

}
