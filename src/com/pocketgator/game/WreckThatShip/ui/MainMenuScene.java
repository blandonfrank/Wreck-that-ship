package com.pocketgator.game.WreckThatShip.ui;

import java.util.List;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.sprite.TiledSprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;

import com.pocketgator.game.WreckThatShip.utils.ResourceManager;


public class MainMenuScene extends Scene implements GUIScene {

	private final String sceneName;
	private final BaseGameActivity context;
	private final ResourceManager rsManager;

	//Constants
    private float CAMERA_WIDTH = 800;
    private float CAMERA_HEIGHT = 480;
    protected static final int ANILOGO= 0;
    protected static final int NEWGAME= ANILOGO+1;
    
    //End Constants

	
	private AnimatedSprite logoMainMenu;
	private TiledSprite NewGameButton;
	private TiledSprite ayudaButton;
	private TiledSprite exitButton;
	private TiledSprite AudioOptionsButton;
	private TiledSprite GameInformationButton;
	private Sprite mainMenuCredits;
	
	private TextureRegion creditsArea;
	private TiledTextureRegion helpButton;
	private TiledTextureRegion exitGameButton;
	private TiledTextureRegion audioButton;
	private TiledTextureRegion infoButton;
	private TiledTextureRegion mainMenuButton;
	private TextureRegion mainMenuBackgroundText;
	private TiledTextureRegion mainMenuAnimationLogo;
	

	
	public MainMenuScene(String pSceneName, int pLayerCount, BaseGameActivity pContext){
		super(pLayerCount);
		this.sceneName = pSceneName;
		this.setName(sceneName);
		this.context = pContext;
		this.rsManager = (ResourceManager) this.context.getGameResourceManager();
		createUI();
	}

	public void createUI() {
		initResources();
		
		this.getBottomLayer().addEntity(new Sprite(0, 0, this.mainMenuBackgroundText));
		this.logoMainMenu = new AnimatedSprite(CAMERA_WIDTH /2 - this.mainMenuAnimationLogo.getTileWidth() /2 , 5, this.mainMenuAnimationLogo);
		this.getTopLayer().addEntity(this.logoMainMenu);
		this.logoMainMenu.animate(300);
		this.mainMenuCredits = new Sprite(CAMERA_WIDTH-this.creditsArea.getWidth()-20,20,this.creditsArea);
		this.mainMenuCredits.setVisible(false);
		
		//new game button
		this.NewGameButton = new TiledSprite(CAMERA_WIDTH / 2 - this.mainMenuButton.getTileWidth()/2,this.mainMenuAnimationLogo.getHeight()/2-3, this.mainMenuButton){
	
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				
				if(pSceneTouchEvent.getAction() == MotionEvent.ACTION_DOWN) {
					Log.d("", "DOWN");
					this.setCurrentTileIndex(1);
					
				}
				else if(pSceneTouchEvent.getAction() == MotionEvent.ACTION_UP) {
					Log.d("", "UP");
					this.setCurrentTileIndex(0);
					Intent intent = new Intent(MainMenuScene.this.context,BaseWreckThatShipGameActivity.class);
					MainMenuScene.this.context.startActivity(intent);
				}
				return true;
			}
		};
		//help button
		this.ayudaButton = new TiledSprite(CAMERA_WIDTH / 2 - this.mainMenuButton.getTileWidth()/2,this.mainMenuAnimationLogo.getHeight()/2+ this.helpButton.getHeight()+2, this.helpButton){
			
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				
				if(pSceneTouchEvent.getAction() == MotionEvent.ACTION_DOWN) {
					this.setCurrentTileIndex(1);
					
				}
				else if(pSceneTouchEvent.getAction() == MotionEvent.ACTION_UP) {
					this.setCurrentTileIndex(0);
				}
				return true;
			}
		};
		//exit game button
		this.exitButton = new TiledSprite(CAMERA_WIDTH / 2 - this.mainMenuButton.getTileWidth()/2,this.mainMenuAnimationLogo.getHeight()/2+ this.helpButton.getHeight()*2+2, this.exitGameButton){
			
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				
				if(pSceneTouchEvent.getAction() == MotionEvent.ACTION_DOWN) {
					this.setCurrentTileIndex(1);
					
				}
				else if(pSceneTouchEvent.getAction() == MotionEvent.ACTION_UP) {
					this.setCurrentTileIndex(0);
					AlertDialog.Builder really = new AlertDialog.Builder(MainMenuScene.this.context);
					really.setTitle("Wreck That Ship!");
					really.setMessage("Are you sure you want to quit?");
					really.setPositiveButton("Yes", new DialogInterface.OnClickListener(){

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							MainMenuScene.this.context.finish();
							
						}
						
					});
					really.setNegativeButton("No", null)
					.show();
				
			}
				return true;
		}
		};
		//audio options button
		this.AudioOptionsButton = new TiledSprite(10,CAMERA_HEIGHT-this.audioButton.getHeight(),this.audioButton){
	
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				
				if(pSceneTouchEvent.getAction() == MotionEvent.ACTION_DOWN) {
		
					this.setCurrentTileIndex(1);
					
				}
				else if(pSceneTouchEvent.getAction() == MotionEvent.ACTION_UP) {
					this.setCurrentTileIndex(0);
				}
				return true;
			}
		};
		//game information button
		this.GameInformationButton = new TiledSprite(CAMERA_WIDTH-10-this.infoButton.getWidth()/2,CAMERA_HEIGHT-this.AudioOptionsButton.getHeight(),this.infoButton){
			
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				
				if(pSceneTouchEvent.getAction() == MotionEvent.ACTION_DOWN) {
		
					this.setCurrentTileIndex(1);
					mainMenuCredits.setVisible(true);
					
				}
				else if(pSceneTouchEvent.getAction() == MotionEvent.ACTION_UP) {
					this.setCurrentTileIndex(0);
					mainMenuCredits.setVisible(false);
				}
				return true;
			}
		};
		this.registerTouchArea(GameInformationButton);
		this.registerTouchArea(AudioOptionsButton);
		this.registerTouchArea(NewGameButton);
		this.registerTouchArea(exitButton);
		this.registerTouchArea(ayudaButton);
		this.getTopLayer().addEntity(GameInformationButton);
		this.getTopLayer().addEntity(AudioOptionsButton);
		this.getTopLayer().addEntity(NewGameButton);
		this.getTopLayer().addEntity(mainMenuCredits);
		this.getTopLayer().addEntity(exitButton);
		this.getTopLayer().addEntity(ayudaButton);
		this.setTouchAreaBindingEnabled(true);
		
	}
	
	public void initResources() {
		List<Texture> textures = this.rsManager.getLoadTexture(this);
		this.rsManager.loadTextures(textures);
		this.mainMenuBackgroundText = this.rsManager.getTextureRegion("mainMenuBackgroundText");
		this.mainMenuAnimationLogo = this.rsManager.getTiledTextureRegion("mainMenuAnimationLogo");
		this.mainMenuButton = this.rsManager.getTiledTextureRegion("mainMenuButton");
		this.audioButton = this.rsManager.getTiledTextureRegion("audioButton");
		this.infoButton = this.rsManager.getTiledTextureRegion("infoButton");
		this.creditsArea = this.rsManager.getTextureRegion("creditsArea");
		this.exitGameButton = this.rsManager.getTiledTextureRegion("exitGameButton");
		this.helpButton = this.rsManager.getTiledTextureRegion("helpButton");
		
	}

}
