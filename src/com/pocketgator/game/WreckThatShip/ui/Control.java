package com.pocketgator.game.WreckThatShip.ui;

import java.util.List;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;
import org.anddev.andengine.util.MathUtils;

import android.util.Log;
import android.view.MotionEvent;

import com.pocketgator.game.WreckThatShip.utils.ResourceManager;

public class Control implements GUIScene{

	private final BaseGameActivity context;
	private final ResourceManager rsManager;
	private final Scene scene;
	private final LevelScene levelScene;
	private float angle;
	private int power;
	private TiledTextureRegion igControlTextureRegion;
	private TextureRegion igPointerTextureRegion;
	private TiledTextureRegion igPowerTextureRegion;
	private AnimatedSprite powerBar;
	private Sprite needle;
	private AnimatedSprite controlBackground;
	

	public Control(final BaseGameActivity context, final LevelScene pLevelScene, final Scene pScene){
		this.context = context;
		this.rsManager = (ResourceManager) this.context.getGameResourceManager();
		this.scene = pScene;
		this.levelScene = pLevelScene;
		this.angle = 0;
		this.power = 0;		
		
		
		
	}

	@Override
	public void createUI() {
		initResources();
		this.scene.setBackgroundEnabled(false);
		this.powerBar = new AnimatedSprite(150,480,this.igPowerTextureRegion);
		this.needle = new Sprite(100-igPointerTextureRegion.getHeight()/2,480-this.igPointerTextureRegion.getHeight()/2,this.igPointerTextureRegion);
		this.controlBackground = new AnimatedSprite(100,320,this.igControlTextureRegion){
			float angle = Control.this.angle;
			int currentTile = 0;

			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {	
				
				angle = (float) (Math.atan((this.getHeight()/2-pTouchAreaLocalY)/(this.getWidth()/2-pTouchAreaLocalX)));
				
				
				if(angle < 0 && angle > - 1.57062179){
					Control.this.needle.setRotation(MathUtils.radToDeg(angle));
					Control.this.angle = angle;
					//Control.this.context.cataSwing.setRotation(MathUtils.radToDeg(angle));
					Control.this.levelScene.cataSwing.setRotation(MathUtils.radToDeg(angle));
					Log.d("Angle - Control: ", " " + angle);
					
				}

				if(pSceneTouchEvent.getAction() == MotionEvent.ACTION_DOWN) {
					this.animate(120, false);
					Control.this.powerBar.animate(50);

					
				}
				else if(pSceneTouchEvent.getAction() == MotionEvent.ACTION_UP){
					Control.this.context.runOnUpdateThread(new Runnable(){

						@Override
						public void run() {
						controlBackground.setCurrentTileIndex(1);
						Control.this.powerBar.stopAnimation();
						
						currentTile = Control.this.powerBar.getCurrentTileIndex() + 1;
						if(currentTile  > 9)
							power = (-currentTile + 17); 
						else
							power = currentTile;
						
							//Control.this.context.shootThemUp(Control.this.angle, (int) (2.5 * power) );
							Control.this.levelScene.shootThatShip(Control.this.angle, (int) (2.5 * power));
							
						}
						
					});
						
					
				}
				return true;
			}	
			
		};
		this.scene.getBottomLayer().addEntity(controlBackground);
		this.scene.getLayer(1).addEntity(needle);
		this.scene.getTopLayer().addEntity(powerBar);
		this.needle.setRotationCenter(needle.getHeight()/2,needle.getHeight()/2);
		this.scene.registerTouchArea(controlBackground);
		this.scene.setTouchAreaBindingEnabled(true);
	}

	@Override
	public void initResources() {
		List<Texture> textures = this.rsManager.getLoadTexture(this.scene);
		this.rsManager.loadTextures(textures);
		
		this.igControlTextureRegion = this.rsManager.getTiledTextureRegion("igControlTextureRegion");
		this.igPointerTextureRegion = this.rsManager.getTextureRegion("igPointerTextureRegion");
		this.igPowerTextureRegion = this.rsManager.getTiledTextureRegion("igPowerTextureRegion");
		
	}
	
	/**
	 * Returns the angle set by the user to shoot
	 * @return
	 */
	public float getAngle(){
		return this.angle;
	}
	
	/**
	 * Returns the power set by the user to shoot
	 * @return
	 */
	public float getPower(){
		return this.power;
	}




}
