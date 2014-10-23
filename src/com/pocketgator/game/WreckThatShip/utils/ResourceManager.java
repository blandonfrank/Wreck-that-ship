package com.pocketgator.game.WreckThatShip.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.anddev.andengine.audio.sound.Sound;
import org.anddev.andengine.audio.sound.SoundManager;
import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.font.FontFactory;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureManager;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;
import org.anddev.andengine.ui.activity.IGameResourceManager;

import android.graphics.Color;
import android.graphics.Typeface;




/**
 * This class will be used to load and unload resources (Textures,sounds,fonts..) for the game
 * @author Frank
 * 
 */
public class ResourceManager implements IGameResourceManager {


	/*
	 * Main-Menu Textures
	 */
	private Map<String, ITextureRegionConfig> gameRegions;
	private Texture mainMenuBackgroundTexture;
	private Texture mainMenuAnimatedLogoTexture;
	private Texture mainMenuButtonTexture;
	private TextureOptions textureOptions;
	
	/*
	 * In-Game Textures
	 */
	private Texture inGameBackgroundTextureL;
	private Texture inGameBackgroundTextureR;
	private Texture inGameSpritesTexture;
	private Texture inGameTrailLineTexture;
	private Texture inGamePointerTexture;
	private Texture inGameControlTexture;
	private Texture inGameCastleTexture;
	private Texture inGameFontTexture;
	
	
	private BaseGameActivity context;
	private Engine engine;
	  
	Map<String, Texture> textures;
	Map<String, Font> fonts;
	Map<String, Sound> sounds;
	
	/**
	 * Creates a ResourceManager
	 * Includes all Textures, Sounds, Fonts...etc
	 * @param pBaseGameActivity
	 */
	public ResourceManager(BaseGameActivity pBaseGameActivity){
		this.textureOptions = TextureOptions.BILINEAR_PREMULTIPLYALPHA;
		this.mainMenuBackgroundTexture = new Texture(1024, 1024,this.textureOptions);
		this.mainMenuAnimatedLogoTexture =  new Texture(1024, 512, this.textureOptions);
		this.mainMenuButtonTexture = new Texture(512,512,this.textureOptions);
		this.inGameBackgroundTextureL =  new Texture(1024, 1024, this.textureOptions);
		this.inGameBackgroundTextureR = new Texture(1024,1024, this.textureOptions);
		this.inGameSpritesTexture = new Texture(512,512,this.textureOptions);
		this.inGameTrailLineTexture = new Texture(16,16, this.textureOptions);
		this.inGamePointerTexture = new Texture(512,512,this.textureOptions);
		this.inGameControlTexture = new Texture(1024,1024,this.textureOptions);
		this.inGameCastleTexture = new Texture (1024,1024, this.textureOptions);
		this.inGameFontTexture = new Texture(256,256,this.textureOptions);
		this.gameRegions = new HashMap<String, ITextureRegionConfig>();
		this.fonts = new HashMap<String, Font>();
		this.sounds = new HashMap<String, Sound>();
		this.textures = new HashMap<String, Texture>();
		this.context = pBaseGameActivity;
		engine = pBaseGameActivity.getEngine();
		
		initTextures();
		initMainMenuTextureRegions();
		initIngameTextureRegions();
		initSounds();
		initFonts();
		
		
	}
	
	
	private void initSounds() {
		try {
			SoundManager soundManager = this.context.getSoundManager();
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}


	private void initMainMenuTextureRegions() {

		TextureRegionConfig mainMenuBackgroundText = new TextureRegionConfig(this.mainMenuBackgroundTexture,this.context,"gfx/mainmenu.png",0,0);
		TiledTextureRegionConfig mainMenuAnimationLogo = new TiledTextureRegionConfig(this.mainMenuAnimatedLogoTexture, this.context, "gfx/MLogoAnimated.png",0,0,3,2);
		TiledTextureRegionConfig mainMenuButton = new TiledTextureRegionConfig (this.mainMenuButtonTexture, this.context,"gfx/NGB.png",0,0,2,1);
		TiledTextureRegionConfig helpButton = new TiledTextureRegionConfig (this.mainMenuButtonTexture, this.context,"gfx/helpButton.png",0,115,2,1);
		TiledTextureRegionConfig exitGameButton = new TiledTextureRegionConfig (this.mainMenuButtonTexture, this.context,"gfx/exitGame.png",0,230,2,1);
		TiledTextureRegionConfig audioButton = new TiledTextureRegionConfig(this.mainMenuBackgroundTexture, this.context,"gfx/audioButton.png",0,487,2,1);
		TiledTextureRegionConfig infoButton = new TiledTextureRegionConfig(this.mainMenuBackgroundTexture,this.context,"gfx/infoButton.png",160,487,2,1);
		TextureRegionConfig creditsArea = new TextureRegionConfig(this.mainMenuBackgroundTexture, this.context,"gfx/credits.png", 803,0);
		this.gameRegions.put("mainMenuBackgroundText", mainMenuBackgroundText);
		this.gameRegions.put("mainMenuAnimationLogo", mainMenuAnimationLogo);
		this.gameRegions.put("mainMenuButton", mainMenuButton);
		this.gameRegions.put("helpButton", helpButton);
		this.gameRegions.put("exitGameButton", exitGameButton);
		this.gameRegions.put("audioButton", audioButton);
		this.gameRegions.put("infoButton", infoButton);
		this.gameRegions.put("creditsArea",creditsArea);
		
		
	}


	private void initIngameTextureRegions() {
	TextureRegionConfig igBackgroundTextureRegionL = new TextureRegionConfig(this.inGameBackgroundTextureL, this.context,"gfx/TestBackgroundL.png",0,0);
	TextureRegionConfig igBackGroundTextureRegionR = new TextureRegionConfig(this.inGameBackgroundTextureR, this.context, "gfx/TestBackgroundR.png",0,0);
	TextureRegionConfig igCliffTextureRegion = new TextureRegionConfig(this.inGameBackgroundTextureR, this.context,"gfx/cliff.png", 0, 809);
	TextureRegionConfig igCircleTextureRegion = new TextureRegionConfig(this.inGameTrailLineTexture, this.context,"gfx/ball.png", 0,0);
	TextureRegionConfig igCloudsTextureRegion = new TextureRegionConfig(this.inGameBackgroundTextureL, this.context, "gfx/clouds.png",0,igBackgroundTextureRegionL.getTextureRegion().getHeight());
	TiledTextureRegionConfig igAncientShip = new TiledTextureRegionConfig(this.inGameSpritesTexture, this.context,"gfx/shipYellow.png",0,0,4,3);
	TiledTextureRegionConfig igAncientShipGreen = new TiledTextureRegionConfig(this.inGameSpritesTexture, this.context,"gfx/shipGreen.png",0,183,4,3);
	//projectiles
	TiledTextureRegionConfig igFireball = new TiledTextureRegionConfig(this.inGamePointerTexture, this.context, "gfx/fireball.png",0,0,3,2);
	TiledTextureRegionConfig igSteelBall = new TiledTextureRegionConfig(this.inGamePointerTexture, this.context, "gfx/fireball.png",0,65,3,2);
	TiledTextureRegionConfig igStoneBall = new TiledTextureRegionConfig(this.inGamePointerTexture,this.context,"gfx/stone.png",0,100,1,1);
	//castle and hudwindow
	TextureRegionConfig igCastle = new TextureRegionConfig(this.inGamePointerTexture, this.context, "gfx/wholecastle.png",0,137);
	TextureRegionConfig igHudWindow = new TextureRegionConfig(this.inGamePointerTexture, this.context, "gfx/hudWindow.png",0,270);
	
	TextureRegionConfig igWave1 = new TextureRegionConfig(this.inGameBackgroundTextureR, this.context,"gfx/wavesquared.png",0,939);
	TextureRegionConfig igWave2 = new TextureRegionConfig(this.inGameCastleTexture, this.context,"gfx/wave2.png",0,800);
	TextureRegionConfig igWave3 = new TextureRegionConfig(this.inGameCastleTexture, this.context,"gfx/wavelettes.png",0,900);
	TextureRegionConfig igfakeCliff = new TextureRegionConfig(this.inGameBackgroundTextureR, this.context,"gfx/cliffhack.png",550,0);
	//in game buttons
	TiledTextureRegionConfig igStoneButton = new TiledTextureRegionConfig(inGameBackgroundTextureR, this.context,"gfx/ingameRock.png",550,132,3,1);
	TiledTextureRegionConfig igSteelButton = new TiledTextureRegionConfig(inGameBackgroundTextureR, this.context,"gfx/ingameSteel.png",550,205,3,1);
	TiledTextureRegionConfig igFireButton = new TiledTextureRegionConfig(inGameBackgroundTextureR, this.context,"gfx/ingameFireRock.png",550,280,3,1);
	
	//Control Texture Regions
	TextureRegionConfig igCataBodyTextureRegion = new TextureRegionConfig(this.inGameControlTexture,this.context,"gfx/cataBody.png",322,0);
	TextureRegionConfig igCataSwingTextureRegion = new TextureRegionConfig(this.inGameControlTexture, this.context, "gfx/swing.png",700,0);
	TextureRegionConfig igPointerTextureRegion = new TextureRegionConfig(this.inGameControlTexture, this.context, "gfx/needle.png",0,500);
	TiledTextureRegionConfig igControlTextureRegion = new TiledTextureRegionConfig(this.inGameControlTexture, this.context, "gfx/bckAni.png",0,0,2,2);
	TiledTextureRegionConfig igPowerTextureRegion = new TiledTextureRegionConfig(this.inGameControlTexture, this.context,"gfx/Animatedpowerbar.png",0,322,2,8);
	TiledTextureRegionConfig igCastle1TextureRegion = new TiledTextureRegionConfig (this.inGameCastleTexture, this.context, "gfx/castle1.png", 0,0,4,2);
	TiledTextureRegionConfig igCastle2TextureRegion = new TiledTextureRegionConfig (this.inGameCastleTexture, this.context, "gfx/castle2.png",342,0,4,2);
	TiledTextureRegionConfig igCastle3TextureRegion = new TiledTextureRegionConfig (this.inGameCastleTexture, this.context, "gfx/castle3.png",0,248,4,2);
	TiledTextureRegionConfig igCastle4TextureRegion = new TiledTextureRegionConfig (this.inGameCastleTexture, this.context, "gfx/castle4.png",342,248,4,2);
	TiledTextureRegionConfig igTorch1TextureRegion = new TiledTextureRegionConfig (this.inGameCastleTexture, this.context, "gfx/torchSprite.png",687,0,7,2);
	TiledTextureRegionConfig igTorch2TextureRegion = new TiledTextureRegionConfig (this.inGameCastleTexture, this.context, "gfx/torchSprite2.png",687,90,7,2);
	this.gameRegions.put("igBackgroundTextureRegionL", igBackgroundTextureRegionL);
	this.gameRegions.put("igBackGroundTextureRegionR", igBackGroundTextureRegionR);
	this.gameRegions.put("igCliffTextureRegion", igCliffTextureRegion);
	this.gameRegions.put("igCircleTextureRegion", igCircleTextureRegion);
	this.gameRegions.put("igCloudsTextureRegion", igCloudsTextureRegion);
	this.gameRegions.put("igAncientShip", igAncientShip);
	this.gameRegions.put("igAncientShipGreen", igAncientShipGreen);
	//projectiles
	this.gameRegions.put("igFireball", igFireball);
	this.gameRegions.put("igSteelBall", igSteelBall);
	this.gameRegions.put("igStoneBall", igStoneBall);
	this.gameRegions.put("igWave1", igWave1);
	this.gameRegions.put("igWave2", igWave2);
	this.gameRegions.put("igWave3", igWave3);
	this.gameRegions.put("igfakeCliff", igfakeCliff);
	//In game buttons
	this.gameRegions.put("igStoneButton", igStoneButton);
	this.gameRegions.put("igSteelButton", igSteelButton);
	this.gameRegions.put("igFireButton", igFireButton);
	this.gameRegions.put("igCastle", igCastle);
	this.gameRegions.put("igHudWindow", igHudWindow);
	
	this.gameRegions.put("igCataBodyTextureRegion", igCataBodyTextureRegion);
	this.gameRegions.put("igCataSwingTextureRegion", igCataSwingTextureRegion);
	this.gameRegions.put("igPointerTextureRegion", igPointerTextureRegion);
	this.gameRegions.put("igControlTextureRegion", igControlTextureRegion);
	this.gameRegions.put("igPowerTextureRegion", igPowerTextureRegion);
	this.gameRegions.put("igCastle1TextureRegion", igCastle1TextureRegion);
	this.gameRegions.put("igCastle2TextureRegion", igCastle2TextureRegion);
	this.gameRegions.put("igCastle3TextureRegion", igCastle3TextureRegion);
	this.gameRegions.put("igCastle4TextureRegion", igCastle4TextureRegion);
	this.gameRegions.put("igTorch1TextureRegion", igTorch1TextureRegion);
	this.gameRegions.put("igTorch2TextureRegion", igTorch2TextureRegion);
		
	}

	private void initFonts(){
		
		Font igHudFont1 = FontFactory.createFromAsset(this.inGameFontTexture, this.context, "font/39smooth.ttf", 28, true, Color.GRAY);
		this.engine.getFontManager().loadFont(igHudFont1);
		this.fonts.put("igHudFont1", igHudFont1);
		
		
	}
	

	private void initTextures() {
		this.mainMenuBackgroundTexture.setId("mainMenuBackgroundTexture");
		this.mainMenuAnimatedLogoTexture.setId("mainMenuAnimatedLogoTexture");
		this.mainMenuButtonTexture.setId("mainMenuButtonTexture");
		this.textures.put("mainMenuBackgroundTexture", this.mainMenuBackgroundTexture);
		this.textures.put("mainMenuAnimatedLogoTexture", this.mainMenuAnimatedLogoTexture);
		this.textures.put("mainMenuButtonTexture", this.mainMenuButtonTexture);
		this.inGameBackgroundTextureL.setId("inGameBackgroundTextureL");
		this.inGameBackgroundTextureR.setId("inGameBackgroundTextureR");
		this.inGameSpritesTexture.setId("inGameSpritesTexture");
		this.inGameTrailLineTexture.setId("inGameTrailLineTexture");
		this.inGamePointerTexture.setId("inGamePointerTexture");
		this.textures.put("inGameBackGroundTextureL", this.inGameBackgroundTextureL);
		this.textures.put("inGameBackgroundTextureR", this.inGameBackgroundTextureR);
		this.textures.put("inGameSpritesTexture", this.inGameSpritesTexture);
		this.textures.put("inGameTrailLineTexture",this.inGameTrailLineTexture);
		this.textures.put("inGamePointerTexture", this.inGamePointerTexture);
		this.textures.put("inGameControlTexture", this.inGameControlTexture);
		this.textures.put("inGameCastleTexture", this.inGameCastleTexture);
		this.textures.put("inGameFontTexture", this.inGameFontTexture);
	}
	
	/**
	 * Returns a list of textures for the given scene - the scene must have a name
	 * @param pScene
	 * @return list<Texture>
	 */
	private List<Texture> getTextures(Scene pScene){
		ArrayList<Texture> textures = new ArrayList<Texture>();
		
		String sceneName = pScene.getName();
		if(pScene.getName()==null)
			new Exception ("The Scene must have a unique name!: ");
		if(sceneName.equals("MAIN_MENU_SCENE")){
			textures.add(this.mainMenuBackgroundTexture);
			textures.add(this.mainMenuAnimatedLogoTexture);
			textures.add(this.mainMenuButtonTexture);
		}
		if(sceneName.equals("IN_GAME_SCENE")){
			textures.add(this.inGameBackgroundTextureL);
			textures.add(this.inGameBackgroundTextureR);
			textures.add(this.inGamePointerTexture);
			textures.add(this.inGameSpritesTexture);
			textures.add(this.inGameTrailLineTexture);
			textures.add(this.inGameCastleTexture);
			textures.add(this.inGameFontTexture);
		}
		if(sceneName.endsWith("CONTROL_SCENE")){
			textures.add(this.inGameControlTexture);
		}
		return textures;
	}

	/**
	 * Returns a Font with the specified font name
	 * @param pFont
	 */
	@Override
	public Font getFont(String pFont) {
			if(this.fonts.get(pFont)==null){
				new Exception("Null FONT:" + pFont).printStackTrace();
			}
			return this.fonts.get(pFont);
		}


	/**
	 * Returns a list of Textures for the specified Scene
	 * @param pScene
	 */
	@Override
	public List<Texture> getLoadTexture(Scene pScene) {
		return getTextures(pScene);
	}

	/**
	 * Returns a sound with the specified name
	 * @param pSound
	 */
	@Override
	public Sound getSound(String pSound) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Returns a TextureRegion
	 * @param pTexturRegion
	 */
	@Override
	public TextureRegion getTextureRegion(String pTextureRegion) {
		if(this.gameRegions.get(pTextureRegion) == null){
			new Exception("Null TextureRegion: " + pTextureRegion).printStackTrace();
		}
		return (TextureRegion) (this.gameRegions.get(pTextureRegion).getTextureRegion());
	}
	/**
	 * Returns a TiledTextureRegion
	 * 
	 */
	@Override
	public TiledTextureRegion getTiledTextureRegion(String pTiledTextureRegion) {
		if(this.gameRegions.get(pTiledTextureRegion)==null){
			new Exception("Null TextureRegion: " + pTiledTextureRegion).printStackTrace();
		}
		return (TiledTextureRegion) (this.gameRegions.get(pTiledTextureRegion).getTiledTextureRegion());
	}
	
	/**
	 * Returns a list of textures for the given scene - This scene must have a name
	 * This textures are to be Unloaded!
	 * @param pScene
	 */
	@Override
	public List<Texture> getUnloadTexture(Scene pScene) {
		return getTextures(pScene);
	}



	/**
	 * Loads all the exture into the engine Texture Manager
	 * @param pList
	 */
	public void loadTextures(List<Texture> pList){
		TextureManager txtManager = this.engine.getTextureManager();
		for(int i = 0; i < pList.size(); i++){
			txtManager.loadTexture((Texture) pList.get(i));
		}
	}
	
	/**
	 * Pauses a sound with the specified sound name
	 * @param pSound
	 */
	@Override
	public void pauseSound(String pSound) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Plays a sound with the specified sound name
	 * @param pSound
	 */
	@Override
	public void playSound(String pSound) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Plays a sound with the specified sound name, and length in seconds
	 * @param pSound
	 * @param pSeconds
	 */
	@Override
	public void playSound(String pSound, int pSeconds) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Stops a sound with the specified sound name
	 * @param pSound
	 */
	@Override
	public void stopSound(String pSound) {
		// TODO Auto-generated method stub
		
	}





	

}
