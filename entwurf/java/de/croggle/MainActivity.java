package de.croggle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import android.os.Bundle;

/**
 * Android backend that initializes the central ApplicationListener.
 * @depend - <creates> - AlligatorApp
 */
public class MainActivity extends AndroidApplication {

	/**
	 * Initializes the central ApplicationListener. 
	 * Is called by the android lifecycle as soon as the app is started. On return, the inner app lifecycle of ApplicationListener is started.
	 */
	@Override
	void onCreate(Bundle savedInstance){
	
	}

}
