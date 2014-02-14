package de.croggle.test;

import android.content.Context;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.AndroidFiles;
import com.badlogic.gdx.backends.android.AndroidGraphics;
import com.badlogic.gdx.backends.android.surfaceview.FillResolutionStrategy;

import de.croggle.AlligatorApp;
import de.croggle.backends.android.AndroidLocalizationBackend;
import de.croggle.data.LocalizationHelper;

public class TestHelper {
	private static Context usedContext = null;
	private static TestActivity testContext = null;
	private static AlligatorApp app = null;
	private static AndroidLocalizationBackend localizationBackend = null;

	private static boolean appContextChanged = true;
	private static boolean inputContextChanged = true;
	private static boolean filesContextChanged = true;
	private static boolean audioContextChanged = true;
	private static boolean graphicsContextChanged = true;
	private static boolean alligatorContextChanged = true;
	private static boolean localizationContextChanged = true;

	private TestHelper() {
	}

	public static void setupContext(Context context) {
		if (context == null) {
			throw new IllegalArgumentException("Cannot set null as context");
		}
		if (context != usedContext) {
			usedContext = context;
			testContext = new TestActivity(context);
			Gdx.app = testContext;
			invalidateAll();
		}
	}

	private static void invalidateAll() {
		appContextChanged = true;
		inputContextChanged = true;
		filesContextChanged = true;
		audioContextChanged = true;
		graphicsContextChanged = true;
		alligatorContextChanged = true;
		localizationContextChanged = true;
	}

	public static void setupAll(Context context) {
		setupContext(context);
		setupGdx();
		setupLocalizationBackend();
	}

	public static void setupGdx() {
		setupGdxApp();
		setupGdxAudio();
		setupGdxFiles();
		setupGdxGraphics();
		setupGdxInput();
	}

	public static void setupGdxApp() {
		TestActivity tc = getTestContext();
		if (tc == null) {
			throw new IllegalStateException(
					"Cannot set up Gdx.app without a test context");
		}
		if (Gdx.app == null || appContextChanged) {
			Gdx.app = tc;
			appContextChanged = false;
		}
	}

	public static void setupGdxInput() {
		TestActivity tc = getTestContext();
		if (tc == null) {
			throw new IllegalStateException(
					"Cannot set up Gdx.input without a test context");
		}
		if (Gdx.input == null || inputContextChanged) {
			Gdx.input = new TestInput(tc, tc, null,
					new AndroidApplicationConfiguration());
			inputContextChanged = false;
		}
	}

	public static void setupGdxFiles() {
		TestActivity tc = getTestContext();
		if (tc == null) {
			throw new IllegalStateException(
					"Cannot set up Gdx.files without a test context");
		}
		if (Gdx.files == null || filesContextChanged) {
			Gdx.files = new AndroidFiles(tc.getAssets(), tc.getFilesDir()
					.getAbsolutePath());
			filesContextChanged = false;
		}
	}

	public static void setupGdxAudio() {
		// TODO
	}

	public static void setupGdxGraphics() {
		TestActivity tc = getTestContext();
		if (tc == null) {
			throw new IllegalStateException(
					"Cannot set up Gdx.graphics without a test context");
		}
		if (Gdx.graphics == null || graphicsContextChanged) {
			AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
			Gdx.graphics = new AndroidGraphics(
					tc,
					config,
					config.resolutionStrategy == null ? new FillResolutionStrategy()
							: config.resolutionStrategy);
			graphicsContextChanged = false;
		}
	}

	public static AlligatorApp getApp() {
		TestActivity tc = getTestContext();
		if (tc == null) {
			throw new IllegalStateException(
					"Cannot serve you an alligator app without a test context");
		}
		if (app == null || alligatorContextChanged) {
			setupGdxFiles();
			setupGdxGraphics();
			setupLocalizationBackend();
			AlligatorApp.HEADLESS = true;
			app = new AlligatorApp();
			app.create();
			alligatorContextChanged = false;
		}

		return app;
	}

	public static TestActivity getTestContext() {
		return testContext;
	}

	public static void setupLocalizationBackend() {
		TestActivity tc = getTestContext();
		if (tc == null) {
			throw new IllegalStateException(
					"Cannot set up LocalizationBackend without a test context");
		}

		if (localizationBackend == null || localizationContextChanged) {
			localizationBackend = new AndroidLocalizationBackend(tc);
			LocalizationHelper.setBackend(localizationBackend);
			localizationContextChanged = false;
		}
	}
}
