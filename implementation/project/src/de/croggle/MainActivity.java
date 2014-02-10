package de.croggle;

import android.os.Bundle;
import android.os.PowerManager.WakeLock;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import de.croggle.data.AndroidLocalizationBackend;
import de.croggle.data.LocalizationBackend;
import de.croggle.data.LocalizationHelper;

/**
 * Android backend that initializes the central ApplicationListener.
 */
public class MainActivity extends AndroidApplication {

	/**
	 * Initializes the central ApplicationListener. Is called by the android
	 * lifecycle as soon as the app is started. On return, the inner app
	 * lifecycle of ApplicationListener is started.
	 * 
	 * @param savedInstanceState
	 *            a Bundle containing the activity's previously frozen state, if
	 *            there was one.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		LocalizationBackend locBack = new AndroidLocalizationBackend(this);
		LocalizationHelper.setBackend(locBack);

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		config.useGL20 = true;
		config.useAccelerometer = false;
		config.useCompass = false;
		config.useWakelock = true;
		config.r = 8;
		config.g = 8;
		config.b = 8;
		config.a = 8;

		initialize(new AlligatorApp(this), config);
	}

	public WakeLock getWakeLock() {
		return wakeLock;
	}

	@Override
	protected void onResume() {
		/*
		 * we do not want libgdx to manage the wakelock for us as we want to do
		 * this on a per-screen basis.
		 */
		WakeLock w = wakeLock;
		wakeLock = null;
		super.onResume();
		wakeLock = w;
	}

	@Override
	protected void onPause() {
		WakeLock w = wakeLock;
		wakeLock = null;
		super.onPause();
		wakeLock = w;
	}
}
