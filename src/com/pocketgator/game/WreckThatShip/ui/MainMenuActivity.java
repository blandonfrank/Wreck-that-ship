package com.pocketgator.game.WreckThatShip.ui;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.camera.ZoomCamera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.menu.MenuScene;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.sprite.TiledSprite;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import com.pocketgator.game.WreckThatShip.utils.ResourceManager;

import android.content.Intent;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
/**
 * Main menu 
 * @author Howard 
 *
 */

public class MainMenuActivity extends BaseGameActivity{

	//Constants
	private Display deviceSize;
    private float CAMERA_WIDTH;
    private float CAMERA_HEIGHT;
    
    //End Constants

	private ZoomCamera MenuCamera;
	protected MainMenuScene scene;
	private ResourceManager rsManager;
	@Override
	public void onLoadComplete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Engine onLoadEngine() {
		
		this.deviceSize = getWindowManager().getDefaultDisplay();
		CAMERA_WIDTH = deviceSize.getWidth();
		CAMERA_HEIGHT = deviceSize.getHeight();
		
		
		this.MenuCamera = new ZoomCamera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		return new Engine(new EngineOptions(true, ScreenOrientation.LANDSCAPE, new RatioResolutionPolicy((int)CAMERA_WIDTH, (int)CAMERA_HEIGHT), this.MenuCamera).setNeedsMusic(true));
	}

	@Override
	public void onLoadResources() {
		this.rsManager = new ResourceManager(this);
		this.setGameResourceManager(this.rsManager);
	}

	@Override
	public Scene onLoadScene() {
		this.mEngine.registerUpdateHandler(new FPSLogger());
	
        this.scene = new MainMenuScene("MAIN_MENU_SCENE",2,this);	
        return scene; 
	}
	
	public void onResume() {
		super.onResume();
	}

	public void onGameResumed() {
		super.onGameResumed();

	}

	public void onDestroy() {
		super.onDestroy();
	}

	public void onFinish() {
		super.finish();
	}
	
	
	
}


