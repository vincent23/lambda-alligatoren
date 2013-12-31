package de.croggle;

import android.os.Bundle;

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
	 * @param savedInstanceState  a Bundle containing the activity's previously frozen state, if there was one.
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
		
		initialize(new AlligatorApp(this), config);
	}

}
