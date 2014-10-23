package com.pocketgator.game.WreckThatShip.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.camera.ZoomCamera;
import org.anddev.andengine.engine.camera.hud.HUD;
import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.primitive.Line;
import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnAreaTouchListener;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.entity.scene.Scene.ITouchArea;
import org.anddev.andengine.entity.shape.IShape;
import org.anddev.andengine.entity.shape.Shape;
import org.anddev.andengine.entity.shape.modifier.IShapeModifier;
import org.anddev.andengine.entity.shape.modifier.MoveModifier;
import org.anddev.andengine.entity.shape.modifier.PathModifier;
import org.anddev.andengine.entity.shape.modifier.ScaleModifier;
import org.anddev.andengine.entity.shape.modifier.IShapeModifier.IShapeModifierListener;
import org.anddev.andengine.entity.shape.modifier.PathModifier.IPathModifierListener;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.extension.physics.box2d.FixedStepPhysicsWorld;
import org.anddev.andengine.extension.physics.box2d.PhysicsConnector;
import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;
import org.anddev.andengine.extension.physics.box2d.util.triangulation.EarClippingTriangulator;
import org.anddev.andengine.extension.physics.box2d.util.triangulation.ITriangulationAlgoritm;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.font.FontFactory;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.opengl.util.GLHelper;
import org.anddev.andengine.ui.activity.BaseGameActivity;
import org.anddev.andengine.util.MathUtils;
import org.anddev.andengine.util.Path;
import org.anddev.andengine.util.modifier.IModifier;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.pocketgator.game.WreckThatShip.entity.GreekShip;
import com.pocketgator.game.WreckThatShip.entity.Ship;
import com.pocketgator.game.WreckThatShip.mapcontrol.MapFlinger;
import com.pocketgator.game.WreckThatShip.mapcontrol.MapPanning;
import com.pocketgator.game.WreckThatShip.mapcontrol.MotionForwarder;
import com.pocketgator.game.WreckThatShip.mapcontrol.MultitouchHolder;
import com.pocketgator.game.WreckThatShip.mapcontrol.TouchDistributor;
import com.pocketgator.game.WreckThatShip.utils.ResourceManager;
import com.pocketgator.game.WreckThatShip.utils.TextureFactoryPadding;


import android.graphics.Color;
import android.graphics.PixelFormat;
import android.hardware.SensorManager;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.Toast;

public class BaseWreckThatShipGameActivity extends BaseGameActivity implements IOnSceneTouchListener{
	
	
        // ===========================================================
        // Constants
        // ===========================================================
		private Display deviceD;
        private float CAMERA_WIDTH;
        private float CAMERA_HEIGHT;
       
 
        // ===========================================================
        // Fields
        // ===========================================================
        protected LevelScene scene;

        private ZoomCamera chaseCamera;
        private float ratio;
		private float Scale;

		private ResourceManager resManager;


	    /* END Testing castle and torches textures and textureRegions*/
    	@Override
    	public Engine onLoadEngine() {
    		//this.chaseCamera = new BoundCamera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
        	
        	this.deviceD = getWindowManager().getDefaultDisplay();
        	
    		CAMERA_WIDTH = deviceD.getWidth();
    		CAMERA_HEIGHT = deviceD.getHeight();
    		ratio = CAMERA_WIDTH / CAMERA_HEIGHT;
    	   	this.chaseCamera = new ZoomCamera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT){
    	   		@Override
    			public void onUpdate(final float pSecondsElapsed) {

    	   			 Scale = this.getZoomFactor();
				if (scene != null) {
					if (scene.chaseFireball()) {
							if (Scale > 0.6) {
								Log.d("Zoom-Factor:", " " + Scale);
								this.setZoomFactor(Scale - 0.005f);
								this.setCenter(this.getCenterX(), this
										.getHeight());
								this.setChaseShape(null);
							}


					} else if (!scene.chaseFireball() && chaseCamera.getReleased()) {

						if (Scale <= 1f) {
							this.setZoomFactor(Scale + 0.005f);
							this.setCenter(this.getCenterX(), this.getHeight());

						}
					}

					super.onUpdate(pSecondsElapsed);
				}
			}
		};

    	   	
    	   
    	   	chaseCamera.offsetCenter(0, 240);
            
        	return new Engine(new EngineOptions(true, ScreenOrientation.LANDSCAPE, new RatioResolutionPolicy((int)CAMERA_WIDTH, (int)CAMERA_HEIGHT), this.chaseCamera).setNeedsMusic(true));
    	}
    	
        @Override
        public void onLoadResources() {
       this.resManager = new ResourceManager(this);
       this.setGameResourceManager(this.resManager);
       
        }

        @Override
        public Scene onLoadScene() {
        	this.scene = new LevelScene(this.mEngine, "IN_GAME_SCENE", 4, this, this.chaseCamera);

                return scene ;
        }
        

		private Scene createScene(String pScene){
			return scene;
			
		}
		

		@Override
        public void onLoadComplete() {
              //  this.pointer.setVisible(true);
        }



		@Override
		public boolean onSceneTouchEvent(Scene pScene,
				TouchEvent pSceneTouchEvent) {

			return false;
		}


        	
}





