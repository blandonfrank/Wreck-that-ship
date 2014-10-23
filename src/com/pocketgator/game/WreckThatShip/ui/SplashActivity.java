package com.pocketgator.game.WreckThatShip.ui;

import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.opengl.texture.source.AssetTextureSource;
import org.anddev.andengine.opengl.texture.source.ITextureSource;
import org.anddev.andengine.ui.activity.BaseSplashActivity;


/**
 * This is temporary
 * Basic Splash screen
 * @author Frank
 *
 */
public class SplashActivity extends BaseSplashActivity{

	@Override
	protected ScreenOrientation getScreenOrientation() {
		return ScreenOrientation.LANDSCAPE;
	}

	@Override
	protected ITextureSource onGetSplashTextureSource() {
		return new AssetTextureSource(this, "gfx/developerlogo.png");
	}

	@Override
	protected float getSplashDuration() {
		return 4;
	}
	
	@Override
	protected float getSplashScaleFrom() {
		return 1.0f;
	}
	
	@Override
	protected float getSplashScaleTo() {
		return 1.0f;
	}

	@Override
	protected Class<MainMenuActivity> getFollowUpActivity() {
		return MainMenuActivity.class;
	}

}

