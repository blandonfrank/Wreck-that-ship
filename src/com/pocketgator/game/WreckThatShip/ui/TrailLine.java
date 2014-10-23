package com.pocketgator.game.WreckThatShip.ui;

import java.util.Random;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

/**
 * Trail line that will be used to follow any of the projectiles
 * @author Frank
 *
 */

public class TrailLine {

	private final Sprite[] trailLines;
	private final TextureRegion textureRegion;
	private final Random generator;
	private Sprite trailLine;
	private final Scene scene;
	private final int size;
	private int index;
	
	/**
	 * Creates a Trail line that follows an object
	 * @param pScene
	 * @param pTextureRegion
	 */
	public TrailLine(Scene pScene, TextureRegion pTextureRegion){
		this.scene = pScene;
		this.textureRegion = pTextureRegion;
		this.generator = new Random();
		this.trailLines = new Sprite[50];
		this.size = this.trailLines.length;
		this.index = 0;
		initTrail();
	}

	private void initTrail() {
		for(int i=0; i <size ; i++){
        	
        	float xPos = this.generator.nextFloat();
        	float yPos = generator.nextFloat();
        	this.trailLine = new Sprite(xPos, yPos, this.textureRegion);
        	this.trailLines[i] = trailLine;
        	this.trailLines[i].setVisible(false);
        	this.scene.getLayer(2).addEntity(trailLines[i]);
        }
		
	}
	
	/**
	 * Creates a trailLine at the given x and y coordinates
	 * @param xPos
	 * @param yPos
	 */
	public void createTrailLine(final float xPos, final float yPos) {
		if (index >= size)
			this.index = 0;
		this.trailLines[index].setPosition(xPos, yPos);
		this.trailLines[index++].setVisible(true);
	}
	
	/**
	 * Removes previous trailLine from the scene
	 */
	public void cleanTrailLine(){
		for(int i= size - 1; i >= 0; i-- )
			this.trailLines[i].setVisible(false);
	}
	
}
