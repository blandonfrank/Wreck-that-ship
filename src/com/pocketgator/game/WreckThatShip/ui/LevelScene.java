package com.pocketgator.game.WreckThatShip.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import org.anddev.andengine.audio.music.Music;
import org.anddev.andengine.audio.music.MusicFactory;
import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.ZoomCamera;
import org.anddev.andengine.engine.camera.hud.HUD;
import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.shape.IShape;

import org.anddev.andengine.entity.shape.modifier.LoopShapeModifier;
import org.anddev.andengine.entity.shape.modifier.MoveModifier;
import org.anddev.andengine.entity.shape.modifier.PathModifier;
import org.anddev.andengine.entity.shape.modifier.IShapeModifier.IShapeModifierListener;
import org.anddev.andengine.entity.shape.modifier.PathModifier.IPathModifierListener;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.sprite.TiledSprite;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.extension.physics.box2d.FixedStepPhysicsWorld;
import org.anddev.andengine.extension.physics.box2d.PhysicsConnector;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.opengl.util.GLHelper;
import org.anddev.andengine.ui.activity.BaseGameActivity;
import org.anddev.andengine.util.Debug;
import org.anddev.andengine.util.HorizontalAlign;
import org.anddev.andengine.util.Path;
import org.anddev.andengine.util.modifier.IModifier;

import android.hardware.SensorManager;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.pocketgator.game.WreckThatShip.entity.Castle;
import com.pocketgator.game.WreckThatShip.entity.GreekShip;
import com.pocketgator.game.WreckThatShip.entity.PhysicsBodyFactory;
import com.pocketgator.game.WreckThatShip.entity.Ship;
import com.pocketgator.game.WreckThatShip.mapcontrol.MapFlinger;
import com.pocketgator.game.WreckThatShip.mapcontrol.MapPanning;
import com.pocketgator.game.WreckThatShip.mapcontrol.MotionForwarder;
import com.pocketgator.game.WreckThatShip.mapcontrol.MultitouchHolder;
import com.pocketgator.game.WreckThatShip.mapcontrol.TouchDistributor;
import com.pocketgator.game.WreckThatShip.utils.ResourceManager;

/**
 * This class will contain all the information regarding each level (each scene)
 * @author Frank
 *
 */

public class LevelScene extends Scene implements GUIScene{
	
	private float CAMERA_WIDTH;
    private float CAMERA_HEIGHT;
	private final Engine engine;
	private final BaseGameActivity context;
	private final String sceneName;
	private final PhysicsWorld mPhysicsWorld;
	private ZoomCamera chaseCamera;
	private Display deviceD;
	private float ratio;
	private AnimatedSprite fireBall;
	private Sprite cliff;
	private TextureRegion igBackgroundTextureRegionL;
	private TextureRegion igBackGroundTextureRegionR;
	private TextureRegion igCliffTextureRegion;
	private TextureRegion igCircleTextureRegion;
	private TextureRegion igCloudsTextureRegion;
	private TiledTextureRegion igAncientShip;
	private TiledTextureRegion igAncientShipGreen;
	private TiledTextureRegion igFireball;
	private TextureRegion igCataBodyTextureRegion;
	private TextureRegion igCataSwingTextureRegion;
	private TiledTextureRegion igCastle1TextureRegion;
	private TiledTextureRegion igCastle2TextureRegion;
	private TiledTextureRegion igCastle3TextureRegion;
	private TiledTextureRegion igCastle4TextureRegion;
	private TiledTextureRegion igTorch1TextureRegion;
	private TiledTextureRegion igTorch2TextureRegion;
	
	private TiledTextureRegion igStoneButton;
	private TiledTextureRegion igSteelButton;
	private TiledTextureRegion igFireButton;
	
	private TextureRegion igWave1;
	private TextureRegion igWave2;
	private TextureRegion igWave3;
	private TextureRegion igHudWindow;
	private TextureRegion igfakeCliff;
	private ResourceManager rsManager;
	private Sprite[] spriteArray;
	private int moveTime;
	private Castle[] castle;
	private Sprite waveOne;
	private Sprite waveTwo;
	private Sprite waveThree;
	private Sprite cliffHack;
	private TiledTextureRegion igStone;
	private TiledSprite fireButton;
	private TiledSprite stoneButton;
	private TiledSprite steelButton;
	private ArrayList<GreekShip>gShips;
	private ArrayList<Body> fireBallBodies;
	private ArrayList<AnimatedSprite> stoneBalls;
	private ArrayList<AnimatedSprite> fireBalls;
	private ArrayList<Ship> tempShip;
	private ArrayList<Body> stoneBallsBodies;
	private ArrayList<Castle> tempCastle;
	public Sprite cataSwing;
	private TimerHandler shipRespawnHandler;
	private TimerHandler shipShootingHandler;
	private TimerHandler cleanerHandler;
	private TimerHandler gameOverHandler;
	private int shots = 0;
	private Body fireBallBody;
	private Body fireBallBodyShip;
	private int shipIndex=0;
	private int fireballIndex =0;
	private int stonesIndex = 0;
	private float[] swingCoordinates;
	private TrailLine trailLine;
	private int numFrames = 0;
	private int numShips =0;
	private Body[] castleBodies;
	private int level;
	private int deadShipCount;
	private HUD hud;
	private Font hudFont;
	private Text score;
	private Text time;
	private Text health;
	private ChangeableText cScore;
	private ChangeableText cHealth;
	private ChangeableText cTime;
	private ChangeableText cGameOver;
	private ChangeableText cLevel;
	private ChangeableText cHit;
	private Sprite hudWindow;
	private int overallHealth = 1;
	private int overallScore = 0;
	
	
	/*
	 * Testing Music
	 */
	
	private Music smoothWave;
	private Music fire;
	private Music horn;

	PhysicsBodyFactory factory = new PhysicsBodyFactory();
	
	public LevelScene(Engine pEngine, String pSceneName, int pLayerCount, BaseGameActivity pContext, ZoomCamera camera){
		super(pLayerCount);
		this.engine = pEngine;
		this.sceneName = pSceneName;
		this.setName(sceneName);
		this.context = pContext;
		this.mPhysicsWorld = new FixedStepPhysicsWorld(60,new Vector2(0, SensorManager.GRAVITY_EARTH),false);
		this.registerUpdateHandler(this.mPhysicsWorld);
		this.rsManager = (ResourceManager) this.context.getGameResourceManager();
		this.chaseCamera = camera;
		initCamera();
		initResources();
		initHUD();
		createUI();
		createShips();
		createStoneBalls();
		createFireBalls();

	}
	
	

	/**
	 * Create an initialize an array full of fireBalls
	 */
	private void createFireBalls() {
		this.fireBalls = new ArrayList<AnimatedSprite>();
		this.fireBallBodies = new ArrayList<Body>();
		for(int i=0; i < 15; i++){
			fireBalls.add(new AnimatedSprite(-10,-10,this.igFireball));
			fireBalls.get(i).setVisible(false);
			fireBalls.get(i).setIgnoreUpdate(true);
			fireBalls.get(i).setScale(0.8f);
			this.getLayer(2).addEntity(fireBalls.get(i));
			
		}
		
		
	}



	/**
	 * Create an initialize an array full of stones
	 */
	private void createStoneBalls() {
		this.stoneBallsBodies = new ArrayList<Body>();
		this.stoneBalls = new ArrayList<AnimatedSprite>();
		
		for(int i=0; i < 30; i++){
			this.stoneBalls.add(new AnimatedSprite(-10,-10,this.igStone));
			this.stoneBalls.get(i).setVisible(false);
			this.stoneBalls.get(i).setIgnoreUpdate(true);
			this.stoneBalls.get(i).setScale(0.5f);
			this.getLayer(2).addEntity(stoneBalls.get(i));
		}
	
	}

	/**
	 * Create an array for ships
	 */
	private void createShips() {
		gShips = new ArrayList<GreekShip>();
		tempShip = new ArrayList<Ship>();
		for(int i=0; i < 5; i++){
			gShips.add(null);
		}
		tempShip.add(new GreekShip(-10, -10, 0, 0, 0, 0, this.igAncientShip, this.mPhysicsWorld));

	}
	private void initCamera() {
		this.deviceD = this.context.getWindowManager().getDefaultDisplay();

		CAMERA_WIDTH = deviceD.getWidth();
		CAMERA_HEIGHT = deviceD.getHeight();
		this.ratio = CAMERA_WIDTH / CAMERA_HEIGHT;
		

	}

	@Override
	public void createUI() {
		/*
		 * Test
		 * 
		 */
		//factory.createGround(mPhysicsWorld);
		factory.createWaterLevel(mPhysicsWorld);
		
		
		initHUD();
		initMultiTouch();
		initVerticalParallax();
		
			this.shipRespawnTimer(10,3);
		this.trailLine = new TrailLine(this, this.igCircleTextureRegion);
		this.engine.registerUpdateHandler(new FPSLogger());
		this.setBackgroundEnabled(false);
		this.chaseCamera.setBounds(0, this.igBackgroundTextureRegionL.getWidth() + this.igBackGroundTextureRegionR.getWidth(),0, CAMERA_HEIGHT * this.ratio);
		this.chaseCamera.setBoundsEnabled(true);
		
		this.getBottomLayer().addEntity(
				new Sprite(0, -2, this.igBackgroundTextureRegionL) {
					protected void onInitDraw(final GL10 pGL) {
						super.onInitDraw(pGL);
						GLHelper.enableTextures(pGL);
						GLHelper.enableTexCoordArray(pGL);
						GLHelper.enableDither(pGL);
					}
				});

		this.getBottomLayer().addEntity(
				new Sprite(1020, 0, this.igBackGroundTextureRegionR) {
					protected void onInitDraw(final GL10 pGL) {
						super.onInitDraw(pGL);
						GLHelper.enableTextures(pGL);
						GLHelper.enableTexCoordArray(pGL);
						GLHelper.enableDither(pGL);
					}
				});
		
		/*
		 * waves
		 */
		this.waveOne = new Sprite(545,680,this.igWave1);
        this.waveTwo = new Sprite(545,690, this.igWave2);
        this.waveThree = new Sprite(545,690,this.igWave3);
        this.cliffHack = new Sprite(526,803-this.igfakeCliff.getHeight(),this.igfakeCliff);
       
        
      
        final Path path2 = new Path(7).to(545, 710).to(550, 704).to(540, 711).to(547, 710).to(547, 703).to(550, 712).to(545, 710);
		this.waveOne.addShapeModifier(new LoopShapeModifier(new PathModifier(6, path2, null, new IPathModifierListener() {
			@Override
			public void onWaypointPassed(final PathModifier pPathModifier, final IShape pShape, final int pWaypointIndex) {	
		}
		})));
		
		final Path path3 = new Path(7).to(545, 703).to(546, 699).to(545, 703).to(547, 699).to(544, 703).to(542, 699).to(545, 703);
		this.waveTwo.addShapeModifier(new LoopShapeModifier(new PathModifier(3, path3, null, new IPathModifierListener() {
			@Override
			public void onWaypointPassed(final PathModifier pPathModifier, final IShape pShape, final int pWaypointIndex) {	
		}
		})));
		
         final Path path4 = new Path(7).to(562, 715).to(570, 710).to(562, 715).to(573, 710).to(565, 715).to(574, 710).to(562, 715);
		this.waveThree.addShapeModifier(new LoopShapeModifier(new PathModifier(7, path4, null, new IPathModifierListener() {
			@Override
			public void onWaypointPassed(final PathModifier pPathModifier, final IShape pShape, final int pWaypointIndex) {	
		}
		})));
		
        /*
         * Testing Music
         */
        
		try {
			this.smoothWave = MusicFactory.createMusicFromAsset(this.engine.getMusicManager(),this.context, "mfx/water_soft.ogg");
			this.smoothWave.setLooping(true);
		} catch (final IOException e) {
			Debug.e("Error", e);
		}
		this.smoothWave.setVolume(.08f);
		this.smoothWave.play();
		
		try {
			this.fire = MusicFactory.createMusicFromAsset(this.engine.getMusicManager(),this.context, "mfx/fire2.ogg");
			this.fire.setLooping(true);
		} catch (final IOException e) {
			Debug.e("Error", e);
		}
		this.fire.setVolume(.1f);
		
		try {
			this.horn = MusicFactory.createMusicFromAsset(this.engine.getMusicManager(),this.context, "mfx/fan.ogg");
			this.horn.setLooping(false);
		} catch (final IOException e) {
			Debug.e("Error", e);
		}
		this.horn.setVolume(.09f);
		this.horn.play();
		
		/*
		 * stone options buttons
		 */
		
		this.stoneButton = new TiledSprite(150,760-igStoneButton.getHeight(),this.igStoneButton);
		this.steelButton = new TiledSprite(160+this.igSteelButton.getTileWidth(),760-igSteelButton.getHeight(),this.igSteelButton);
		this.fireButton = new TiledSprite(170+this.igFireButton.getTileWidth()*2,760-this.igFireButton.getHeight(),this.igFireButton);
		this.fireButton.setCurrentTileIndex(2);
		this.stoneButton.setCurrentTileIndex(1);
		this.steelButton.setCurrentTileIndex(2);
		
		/**
		 * Temporary for the castle
		 */
		
		
		this.cliff = new Sprite(0,804-this.igCliffTextureRegion.getHeight(),this.igCliffTextureRegion);
		Body cliffBody = PhysicsBodyFactory.createCliff(this.cliff, mPhysicsWorld);
		this.mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(this.cliff, cliffBody));
		
		castle = new Castle[4];
		this.castle[0] = new Castle(this.cliff.getWidth()-115-this.igCastle1TextureRegion.getWidth()/4,816-this.cliff.getHeight()-this.igCastle1TextureRegion.getHeight()/2,this.igCastle1TextureRegion,1,100);
		//this.castle1 = new AnimatedSprite(this.cliff.getWidth()-115-this.igCastle1TextureRegion.getWidth()/4,816-this.cliff.getHeight()-this.igCastle1TextureRegion.getHeight()/2,this.igCastle1TextureRegion);
		this.castle[1] = new Castle(this.cliff.getWidth()-115-((this.igCastle1TextureRegion.getWidth()/4)*2),816-this.cliff.getHeight()-this.igCastle1TextureRegion.getHeight()/2,this.igCastle2TextureRegion,2,100);
		//this.castle2 = new AnimatedSprite(this.cliff.getWidth()-115-((this.igCastle1TextureRegion.getWidth()/4)*2),816-this.cliff.getHeight()-this.igCastle1TextureRegion.getHeight()/2,this.igCastle2TextureRegion);
		this.castle[2] = new Castle(this.cliff.getWidth()-115-((this.igCastle1TextureRegion.getWidth()/4)*3),816-this.cliff.getHeight()-this.igCastle1TextureRegion.getHeight()/2,this.igCastle3TextureRegion,3,100);
		//this.castle3 = new AnimatedSprite(this.cliff.getWidth()-115-((this.igCastle1TextureRegion.getWidth()/4)*3),816-this.cliff.getHeight()-this.igCastle1TextureRegion.getHeight()/2,this.igCastle3TextureRegion);
		
		this.castle[3] = new Castle(this.cliff.getWidth()-115-this.igCastle1TextureRegion.getWidth(),816-this.cliff.getHeight()-this.igCastle1TextureRegion.getHeight()/2,this.igCastle4TextureRegion,4,100);
		//this.castle4 = new AnimatedSprite(this.cliff.getWidth()-115-this.igCastle1TextureRegion.getWidth(),816-this.cliff.getHeight()-this.igCastle1TextureRegion.getHeight()/2,this.igCastle4TextureRegion);
		AnimatedSprite torch1 = new AnimatedSprite(this.cliff.getWidth()/4,816-this.cliff.getHeight()-this.igTorch1TextureRegion.getHeight()/2,this.igTorch1TextureRegion);
		AnimatedSprite torch2 = new AnimatedSprite(40,816-this.cliff.getHeight()-this.igTorch2TextureRegion.getHeight()/2,this.igTorch2TextureRegion);
		this.castle[0].setCurrentTileIndex(0);
		this.castle[1].setCurrentTileIndex(0);
		this.castle[2].setCurrentTileIndex(0);
		this.castle[3].setCurrentTileIndex(0);


		factory.createCastle(this.mPhysicsWorld);
		castleBodies = new Body[13];
		castleBodies = factory.getCastleBodies();
		tempCastle = new ArrayList<Castle>();
		tempCastle.add(new Castle(-20,-20,this.igCastle4TextureRegion,0,0));
		
		torch1.animate(200);
		torch2.animate(200);
		this.getTopLayer().addEntity(waveOne);
		this.getTopLayer().addEntity(waveThree);
		this.getLayer(1).addEntity(waveTwo);
		this.getLayer(2).addEntity(castle[0]);
		this.getLayer(2).addEntity(castle[1]);
		this.getLayer(2).addEntity(castle[2]);
		this.getLayer(2).addEntity(castle[3]);
		this.getLayer(2).addEntity(torch1);
		this.getLayer(2).addEntity(torch2);
		this.getLayer(2).addEntity(cliff);
		this.getTopLayer().addEntity(cliffHack);
		this.getTopLayer().addEntity(stoneButton);
		this.getTopLayer().addEntity(steelButton);
		this.getTopLayer().addEntity(fireButton);
		
		/*
		 * Update handler
		 */
		this.registerUpdateHandler(new IUpdateHandler(){

			@Override
			public void onUpdate(float pSecondsElapsed) {
				numFrames++;
				if(fireBall !=null){
					if(numFrames % 5 ==0)
						trailLine.createTrailLine(fireBall.getX(), fireBall.getY());
						
					if(fireBall.getY() > 650 && fireBallBody !=null){
						fireBall = null;
					}
				}
				
				
				
				for(int i=0; i < numShips; i++){
					if(gShips.get(0).getX() < factory.leftMostSector)
						gShips.get(0).getBody().setLinearVelocity(new Vector2(0f,0f));
						else
							gShips.get(0).getBody().setLinearVelocity(new Vector2(-0.8f,0f));
					if(gShips.get(1) != null ){
						if(gShips.get(1).getX() < factory.leftSector)
							gShips.get(1).getBody().setLinearVelocity(new Vector2(0f,0f));
						else
							gShips.get(1).getBody().setLinearVelocity(new Vector2(-0.8f,0f));
					}
					if(gShips.get(2) != null ){
						if(gShips.get(2).getX() < factory.centerSector)
							gShips.get(2).getBody().setLinearVelocity(new Vector2(0f,0f));
						else
							gShips.get(2).getBody().setLinearVelocity(new Vector2(-0.8f,0f));
					}
				}
				
				fireBallsRemover();
				stoneBallsRemover();
				shipRemover();
			}

			
			@Override
			public void reset() {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		this.mPhysicsWorld.setContactListener(new ContactListener() {

			@Override
			public void beginContact(Contact pContact) {
				
				for(int i=0; i < fireBallBodies.size(); i++){
					
					for(int j =0; j <= numShips - 1; j++){
						if(gShips.get(j) !=null){
							if(pContact.getFixtureB().getBody().equals(fireBallBodies.get(i)) && pContact.getFixtureA().getBody().equals(gShips.get(j).getBody())){
								Log.d("Ship # ", ": " + j);
								Log.d("SHIP", ": " + (Ship)gShips.get(j));
								checkShip((Ship)gShips.get(j));
								
							}
						}	
					}
					if(pContact.getFixtureB().getBody().equals(fireBallBodies.get(i)) && pContact.getFixtureA().getBody().equals(factory.getBoxBody())){
						Log.d("FIreBALLL!", "Touched something");
						fireBallBodies.get(i).getAnimatedSprite().setDelete(true);
					}
					if(pContact.getFixtureA().getBody().equals(fireBallBodies.get(i)) && pContact.getFixtureB().getBody().equals(factory.getBoxBody())){
						Log.d("FIreBALLL!", "Touched something");
						fireBallBodies.get(i).getAnimatedSprite().setDelete(true);
					}
				}

				if(fireBallBodyShip !=null){
				for(int i=0; i < stoneBallsBodies.size(); i++ ){
					if(pContact.getFixtureB().getBody().equals(stoneBallsBodies.get(i)) && pContact.getFixtureA().getBody().equals(factory.getBoxBody())){
						stoneBallsBodies.get(i).getAnimatedSprite().setDelete(true);
					}	
					
					if(pContact.getFixtureB().getBody().equals(stoneBallsBodies.get(i)) && (pContact.getFixtureA().getBody().equals(castleBodies[0]) || pContact.getFixtureA().getBody().equals(castleBodies[1]) || pContact.getFixtureA().getBody().equals(castleBodies[2])))
						checkCastle(castle[0]);

						if(pContact.getFixtureB().getBody().equals(stoneBallsBodies.get(i)) && (pContact.getFixtureA().getBody().equals(castleBodies[3]) || pContact.getFixtureA().getBody().equals(castleBodies[4]) || pContact.getFixtureA().getBody().equals(castleBodies[5])))
							checkCastle(castle[1]);
						if(pContact.getFixtureB().getBody().equals(stoneBallsBodies.get(i)) && (pContact.getFixtureA().getBody().equals(castleBodies[6]) || pContact.getFixtureA().getBody().equals(castleBodies[7]) || pContact.getFixtureA().getBody().equals(castleBodies[8])))
							checkCastle(castle[2]);

						if(pContact.getFixtureB().getBody().equals(stoneBallsBodies.get(i)) && (pContact.getFixtureA().getBody().equals(castleBodies[9]) || pContact.getFixtureA().getBody().equals(castleBodies[10]) || pContact.getFixtureA().getBody().equals(castleBodies[11]) || pContact.getFixtureA().getBody().equals(castleBodies[12])))
							checkCastle(castle[3]);
				}
				}
				
			}

			@Override
			public void endContact(Contact contact) {
				
			}

		});
		this.setChildScene(controllerScene());
	}

	
	
	private void checkCastle(Castle pCastle){
		
		if(!tempCastle.contains(pCastle)){
			pCastle.decreaseHealth(5);
			int health = pCastle.getHealth();
				if(health <=25)
					pCastle.animate(new long[] { 200, 200,200}, 5, 7, true);
				else if (health <= 40)
					pCastle.setCurrentTileIndex(4);
				else if(health <= 65)
					pCastle.setCurrentTileIndex(3);
				else if(health <=75)
					pCastle.setCurrentTileIndex(2);
				else if(health <= 90)
					pCastle.setCurrentTileIndex(1);
				tempCastle.add(pCastle);
				
				//updates the health of the castle
				for(int i = 0; i < castle.length; i++){
					this.overallHealth += castle[i].getHealth();
				}
				if(this.overallHealth > 0){
					this.cHealth.setText(""+overallHealth/4);
					this.overallHealth = 0;
				}
				else{
					this.cGameOver.setPosition(CAMERA_WIDTH/2, CAMERA_HEIGHT/1.5f);
					this.cGameOver.setScale(2);
					this.cGameOver.setText("Game Over!");
					
					this.hud.getTopLayer().addEntity(this.cGameOver);
					gameOverTimer(4);
					
				}
					
		}
	}
	/**
	 * 
	 * @param waitTime
	 */
	private void gameOverTimer(float waitTime) {

		gameOverHandler = new TimerHandler(waitTime, new ITimerCallback()
		{
			
			 @Override
	            public void onTimePassed(final TimerHandler pTimerHandler)
	            {
				 
				 	context.finish();
	            }

		});
		this.engine.registerUpdateHandler(gameOverHandler);
	}
	

	private void checkShip(Ship pShip){
		
		if(!tempShip.contains(pShip)){
			pShip.decreaseHealth(10);
			if(pShip.getShipHealth() <=20)
				pShip.animate(new long[] { 400, 400,400}, 8, 10, true);
				tempShip.add(pShip);
			if(pShip.isWrecked()){
				pShip.animate(new long[] {400,400}, 10,11, true);
				pShip.setDelete(true);
			}
			this.overallScore += 2;
			this.cScore.setText(""+overallScore);
			this.cHit.setPosition(pShip.getX() + 20, pShip.getY() - 40);
			this.cHit.setScale(1.5f);
			this.getTopLayer().addEntity(this.cHit);
			
		}
		
	}
	/**
	 * Removes FireBalls from the ground
	 */
	private void fireBallsRemover() {
		if(fireBallBody !=null){
			for(int i=0; i < fireBalls.size(); i++){
				if(fireBalls.get(i).getDelete()){
					fireBalls.get(i).setDelete(false);
					fireBalls.get(i).setVisible(false);
					fireBalls.get(i).setPosition(-10, -10);
					fireBalls.get(i).setIgnoreUpdate(true);
					mPhysicsWorld.unregisterPhysicsConnector(mPhysicsWorld.getPhysicsConnectorManager().findPhysicsConnectorByShape(fireBalls.get(i)));

					for(int j =0 ; j < fireBallBodies.size(); j++){
						if(fireBallBodies.get(j).getAnimatedSprite().equals(fireBalls.get(i))){
							mPhysicsWorld.destroyBody(fireBallBodies.get(j));
							fireBallBodies.remove(j);
						}
					}
				}
			}
			
		}
		
	}
	
	/**
	 * Removes FireBalls from the ground
	 */
	private void stoneBallsRemover() {
		if(fireBallBodyShip !=null){
			for(int i=0; i < stoneBalls.size(); i++){
				if(stoneBalls.get(i).getDelete()){
					stoneBalls.get(i).setDelete(false);
					stoneBalls.get(i).setVisible(false);
					stoneBalls.get(i).setPosition(-10, -10);
					stoneBalls.get(i).setIgnoreUpdate(true);
					mPhysicsWorld.unregisterPhysicsConnector(mPhysicsWorld.getPhysicsConnectorManager().findPhysicsConnectorByShape(stoneBalls.get(i)));

					for(int j =0 ; j < stoneBallsBodies.size(); j++){
						if(stoneBallsBodies.get(j).getAnimatedSprite().equals(stoneBalls.get(i))){
							mPhysicsWorld.destroyBody(stoneBallsBodies.get(j));
							stoneBallsBodies.remove(j);
						}
					}
				}
			}
			
		}
		
	}
	
	private void shipRemover(){
		if(!gShips.isEmpty()){
			for(int i=0; i < gShips.size(); i++){
				if(gShips.get(i) !=null && gShips.get(i).getDelete()){
					mPhysicsWorld.unregisterPhysicsConnector(mPhysicsWorld.getPhysicsConnectorManager().findPhysicsConnectorByShape(gShips.get(i)));
					mPhysicsWorld.destroyBody(gShips.get(i).getBody());
					this.getLayer(2).removeEntity(gShips.get(i));
					gShips.remove(i);
					numShips--;
					shipIndex--;
					shipRespawnTimer(7,1);
					deadShipCount++;
					this.cHit.setPosition(-100, -100);
				}
			}	
		}
		
	}

	/**
	 * 
	 * @param waitTime
	 */
	private void cleanerTimer(float waitTime, final AnimatedSprite pSprite) {

		cleanerHandler = new TimerHandler(waitTime, new ITimerCallback()
		{
			
			 @Override
	            public void onTimePassed(final TimerHandler pTimerHandler)
	            {

				 	pSprite.setDelete(true);
				 	
	            }

		});
		this.engine.registerUpdateHandler(cleanerHandler);
	}
	
	/**
	 * 
	 * @param waitTime
	 */
	private void shipShootingTimer(float waitTime) {

		shipShootingHandler = new TimerHandler(waitTime, new ITimerCallback()
		{
			
			 @Override
	            public void onTimePassed(final TimerHandler pTimerHandler)
	            {
				 
				 	shootThatCastle();
				 	
	            }

		});
		this.engine.registerUpdateHandler(shipShootingHandler);
	}
	
	private void shipRespawnTimer(float waitTime, final int pNumShips) {

		shipRespawnHandler = new TimerHandler(waitTime, new ITimerCallback()
		{
			 int tempNum = pNumShips;
			 @Override
	            public void onTimePassed(final TimerHandler pTimerHandler)
	            {
				 
				 Log.d("deadShipCount", " 2" + deadShipCount);
					if(deadShipCount % 6 == 0 && deadShipCount !=0){
						level++;
						deadShipCount = 0;
					}
				 	if(tempNum > 0){
				 		shipRespawn();
				 		tempNum--;
				 	}
				 	else{
				 		shipShootingTimer(8);
				 	}
	            }

		});
		this.engine.registerUpdateHandler(shipRespawnHandler);
	}
	
	/**
	 * Respawns a ship every x seconds
	 */
	private void shipRespawn() {

		this.gShips.add(shipIndex, new GreekShip(1350,650,100,100,5,2,shipLevelTexture(level), this.mPhysicsWorld));
		this.gShips.get(shipIndex).animate(new long[] { 400, 400,400,400,400,400,400,400 }, 0, 7, true);
		this.gShips.get(shipIndex).setScale(1.5f);
		this.getLayer(2).addEntity(gShips.get(shipIndex));
		shipRespawnHandler.reset();
		numShips++;
		shipIndex++;
	}
	
	private TiledTextureRegion shipLevelTexture(int pLevel) {
		if(pLevel ==0){
			return this.igAncientShip.clone();
		}
		else if(pLevel ==1){
			return this.igAncientShipGreen.clone();
		}
		else
			return this.igAncientShip.clone();
	}



	/**
	 * Ships fight back!
	 */
	private void shootThatCastle(){		
		if(stonesIndex > stoneBalls.size() - 4)
			stonesIndex =0;
		for(int i=0; i < this.numShips; i++){
			if(gShips.get(i)!=null){
				stoneBalls.get(stonesIndex).setPosition(this.gShips.get(i).getX(), this.gShips.get(i).getY() - 50);
		 		stoneBalls.get(stonesIndex).setVisible(true);
		 		fireBallBodyShip = PhysicsBodyFactory.createCircle(stoneBalls.get(stonesIndex), mPhysicsWorld);
		 		fireBallBodyShip.setAttachedAnimatedSprite(stoneBalls.get(stonesIndex));
		 		this.stoneBallsBodies.add(fireBallBodyShip);
		 		this.gShips.get(i).shoot(fireBallBodyShip);
		 		mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(stoneBalls.get(stonesIndex), fireBallBodyShip,true,true,false,false));
		 		stoneBalls.get(stonesIndex).setUpdatePhysics(false);
		 		cleanerTimer(5, stoneBalls.get(stonesIndex));
		 		stonesIndex++;
			}
		}
		for(int i=1; i < tempCastle.size(); i++){
			tempCastle.remove(i);
		}
		shipShootingHandler.reset();
		
	}
	
	
	private void initVerticalParallax() {
		moveTime=200;
        spriteArray = new Sprite[2];
        spriteArray[0] = new Sprite(0, 0, this.igCloudsTextureRegion);
        spriteArray[1] = new Sprite(0, -this.igCloudsTextureRegion.getHeight(), this.igCloudsTextureRegion);
        
        for(int i=0; i <2; i++)
        	this.getTopLayer().addEntity(this.spriteArray[i]);
        
        verticalParallaxUpdate(0);
		
	}
	/**
	 * Updates the clouds to give it a vertical movement
	 * @param callingSpriteIndex
	 */
	private void verticalParallaxUpdate(int callingSpriteIndex) {
		int targetSpriteIndexTemp = 0;
		switch (callingSpriteIndex) {
		case 0:
			targetSpriteIndexTemp = 1;
			moveTime = 200;
			break;
		case 1:
			targetSpriteIndexTemp = 0;
			moveTime = 200;
			break;
		case 2:
			targetSpriteIndexTemp = 3;
			moveTime = 200;
			break;
		case 3:
			targetSpriteIndexTemp = 2;
			moveTime = 200;
			break;
		default:
			return;

		}
		final int targetSpriteIndex = targetSpriteIndexTemp;
		IShapeModifierListener moveFinished = new IShapeModifierListener() {
//			}

			@Override
			public void onModifierFinished(IModifier<IShape> pModifier,
					IShape pItem) {
				verticalParallaxUpdate(targetSpriteIndex);
				
			}
		};

		MoveModifier firstMotion = new MoveModifier(moveTime,
				-spriteArray[targetSpriteIndex].getWidth() + 1, 0, 140, 140,
				moveFinished);
		MoveModifier secondMotion = new MoveModifier(moveTime, 1,
				spriteArray[targetSpriteIndex].getWidth(), 140, 140);

		spriteArray[targetSpriteIndex].clearShapeModifiers();
		spriteArray[targetSpriteIndex].addShapeModifier(firstMotion);
		spriteArray[callingSpriteIndex].addShapeModifier(secondMotion);

	}
		
	/**
	 * Initializes all the multi-touch functionalities in the phone
	 * Panning, Zooming-in-out, Pinch-Zoom...etc
	 * Fling!
	 */
	private void initMultiTouch() {
		TouchDistributor touchDistributor = new TouchDistributor();
		MapPanning panner = new MapPanning(this.chaseCamera);
		
		if(Build.VERSION.SDK_INT >= 5){
			Scene.IOnSceneTouchListener listener = MultitouchHolder.getPinchZoomDetector(this.chaseCamera, panner);
        	touchDistributor.addTouchListener(listener); 
		}
		this.engine.registerUpdateHandler(panner);
        this.setOnSceneTouchListener(touchDistributor);
        touchDistributor.addTouchListener(panner);               
        MapFlinger mapFlinger = new MapFlinger(panner);
        GestureDetector gestureDetector = new GestureDetector(mapFlinger);
        MotionForwarder motionForwarder = new MotionForwarder(gestureDetector);
        touchDistributor.addTouchListener(motionForwarder);
        this.chaseCamera.setReleased(false);
		
	}
	/**
	 * Hud set up
	 */
	private void initHUD() {
		hud =  new HUD();
		this.hudWindow = new Sprite(this.CAMERA_WIDTH-this.igHudWindow.getWidth()-10,20,this.igHudWindow);
		this.score = new Text(this.CAMERA_WIDTH-this.igHudWindow.getWidth()+7,28,this.hudFont,"Score: ", HorizontalAlign.LEFT);
		this.health = new Text(this.CAMERA_WIDTH-this.igHudWindow.getWidth()+7,59,this.hudFont,"Health: ", HorizontalAlign.LEFT);
		this.time =  new Text(this.CAMERA_WIDTH-this.igHudWindow.getWidth()+7,90,this.hudFont,"Time: ", HorizontalAlign.LEFT);
		
		this.cScore = new ChangeableText(this.CAMERA_WIDTH-this.igHudWindow.getWidth()+150,28,this.hudFont,"123456","123456789".length());
		this.cHealth = new ChangeableText(this.CAMERA_WIDTH-this.igHudWindow.getWidth()+150,59,this.hudFont,"100","100".length());
		this.cTime = new ChangeableText(this.CAMERA_WIDTH-this.igHudWindow.getWidth()+150,90,this.hudFont,"10:00","010:000".length());
		this.cGameOver = new ChangeableText(0,0,this.hudFont,"Game Over!","Game Over!".length());
		this.cHit = new ChangeableText(0,0,this.hudFont,"Hit!","Hit!".length());
		this.cLevel = new ChangeableText(0,0,this.hudFont,"Level ", "Level ".length());
		
		this.cScore.setText("0123456789");
		this.cHealth.setText("0123456789");
		this.cTime.setText(":0123456789");
		this.cGameOver.setText("Game Over!");
		this.cLevel.setText("Level ");
		this.cHit.setText("Hit!");
		this.cHealth.setText("100");
		this.cScore.setText("0");
		
		
		this.registerUpdateHandler(new TimerHandler(1, true, new ITimerCallback() {
			@Override
			public void onTimePassed(final TimerHandler pTimerHandler) {
				
				cTime.setText(""+ (int)engine.getSecondsElapsedTotal()/60 + " : " + (int)engine.getSecondsElapsedTotal() %60);
			
			}
		}));
		
		
		
		this.hud.getTopLayer().addEntity(hudWindow);
		this.hud.getTopLayer().addEntity(score);
		this.hud.getTopLayer().addEntity(time);
		this.hud.getTopLayer().addEntity(health);
		this.hud.getTopLayer().addEntity(cScore);
		this.hud.getTopLayer().addEntity(cHealth);
		this.hud.getTopLayer().addEntity(cTime);
		this.chaseCamera.setHUD(hud);
		
		
	}
	/**
	 * Loads up all the resources needed for this scene
	 */
	@Override
	public void initResources() {
		List<Texture> textures = this.rsManager.getLoadTexture(this);
		this.rsManager.loadTextures(textures);
		
		this.igBackgroundTextureRegionL = this.rsManager.getTextureRegion("igBackgroundTextureRegionL");
		this.igBackGroundTextureRegionR = this.rsManager.getTextureRegion("igBackGroundTextureRegionR");
		this.igCliffTextureRegion = this.rsManager.getTextureRegion("igCliffTextureRegion");
		this.igCircleTextureRegion = this.rsManager.getTextureRegion("igCircleTextureRegion");
		this.igCloudsTextureRegion = this.rsManager.getTextureRegion("igCloudsTextureRegion");
		this.igAncientShip = this.rsManager.getTiledTextureRegion("igAncientShip");
		this.igFireball = this.rsManager.getTiledTextureRegion("igFireball");
		this.igCataBodyTextureRegion = this.rsManager.getTextureRegion("igCataBodyTextureRegion");
		this.igCataSwingTextureRegion = this.rsManager.getTextureRegion("igCataSwingTextureRegion");
		this.igCastle1TextureRegion = this.rsManager.getTiledTextureRegion("igCastle1TextureRegion");
		this.igCastle2TextureRegion = this.rsManager.getTiledTextureRegion("igCastle2TextureRegion");
		this.igCastle3TextureRegion = this.rsManager.getTiledTextureRegion("igCastle3TextureRegion");
		this.igCastle4TextureRegion = this.rsManager.getTiledTextureRegion("igCastle4TextureRegion");
		this.igTorch1TextureRegion = this.rsManager.getTiledTextureRegion("igTorch1TextureRegion");
		this.igTorch2TextureRegion = this.rsManager.getTiledTextureRegion("igTorch2TextureRegion");
		this.igStoneButton = this.rsManager.getTiledTextureRegion("igStoneButton");
		this.igSteelButton = this.rsManager.getTiledTextureRegion("igSteelButton");
		this.igFireButton = this.rsManager.getTiledTextureRegion("igFireButton");
		this.igWave1 = this.rsManager.getTextureRegion("igWave1");
		this.igWave2 = this.rsManager.getTextureRegion("igWave2");
		this.igWave3 = this.rsManager.getTextureRegion("igWave3");
		this.igfakeCliff = this.rsManager.getTextureRegion("igfakeCliff");
		this.igStone = this.rsManager.getTiledTextureRegion("igStoneBall");
		this.igAncientShipGreen = this.rsManager.getTiledTextureRegion("igAncientShipGreen");
		this.igHudWindow = this.rsManager.getTextureRegion("igHudWindow");
		this.hudFont = this.rsManager.getFont("igHudFont1");
	}

	
	
	
	
	private Scene controllerScene(){
		final Scene childScene = new Scene(2);
		childScene.setBackgroundEnabled(false);
		
		childScene.setName("CONTROL_SCENE");
		final Sprite cataBody = new Sprite(60,816-this.igCliffTextureRegion.getHeight()-igCataBodyTextureRegion.getHeight(),this.igCataBodyTextureRegion);
		cataSwing = new Sprite(66+(igCataBodyTextureRegion.getWidth()/4),816-this.igCliffTextureRegion.getHeight()-igCataSwingTextureRegion.getHeight()-8,this.igCataSwingTextureRegion);
		//setting the rotating center at the axis of the catapult boom
		cataSwing.setRotationCenter(cataSwing.getWidth()-(cataSwing.getWidth()/4),cataSwing.getHeight()-(cataSwing.getHeight()/4));
		

		Control control = new Control(this.context, this, childScene);
		control.createUI();
		this.getLayer(2).addEntity(cataSwing);
		this.getLayer(2).addEntity(cataBody);
		childScene.setTouchAreaBindingEnabled(true);
		
		return childScene;
	}

	/**
	 * Use this to shoot a fireball/projectile to the ships
	 * @param angle
	 * @param power
	 */
	public void shootThatShip(final float angle, final int power){
		if(this.chaseCamera.getZoomFactor() <= 0.7)
			this.chaseCamera.setReleased(false);
		else
			this.chaseCamera.setReleased(true);
		this.swingCoordinates = this.cataSwing.getSceneCenterCoordinates();

			if(fireballIndex >= fireBalls.size())
				fireballIndex=0;
			
			this.fireBalls.get(this.fireballIndex).setPosition(swingCoordinates[0] - (this.cataSwing.getWidth()),swingCoordinates[1] - (this.cataSwing.getHeight()/3.5f));
			this.fireBalls.get(this.fireballIndex).animate(new long[] { 100, 100,100,100,100,100 }, 0, 5, true);
			this.fireBall = this.fireBalls.get(this.fireballIndex);
			
			this.fireBallBody = PhysicsBodyFactory.createCircle(this.fireBalls.get(this.fireballIndex), this.mPhysicsWorld);
			this.fireBallBody.setAttachedAnimatedSprite(this.fireBalls.get(this.fireballIndex));
			this.cataSwing.setRotation(this.fireBalls.get(this.fireballIndex).getRotation());
			this.fireBallBody.setLinearVelocity(new Vector2(power* (float) Math.cos(angle),power* (float) Math.sin(angle)));
			this.fireBallBodies.add(fireBallBody);
			this.mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(this.fireBalls.get(this.fireballIndex), fireBallBody,true,true,false,false));
			this.fireBalls.get(this.fireballIndex).setUpdatePhysics(false);
			this.fireBalls.get(this.fireballIndex).setVisible(true);
			this.fireBalls.get(this.fireballIndex).setIgnoreUpdate(false);
			fireballIndex++;
			this.shots++;
			cleanTrailLine();
			cleanerTimer(5, fireBalls.get(this.fireballIndex - 1));
			this.cHit.setPosition(-100, -100);
			
			for(int i=1; i < tempShip.size(); i++)
				tempShip.remove(i);
	}
	
	public boolean chaseFireball(){
		if (this.fireBall != null) {
			if (this.fireBall.getY() < 400) 
				return true;
		}
		return false;
	}
	
	/**
	 * Removes the trailLine as soon as a second fireball is fired
	 */
	private void cleanTrailLine(){
		if (shots > 1){
			this.trailLine.cleanTrailLine();
			this.numFrames = 0;
		}
	}
}
