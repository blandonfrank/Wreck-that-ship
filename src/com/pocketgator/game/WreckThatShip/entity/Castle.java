package com.pocketgator.game.WreckThatShip.entity;

import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

/**
 * Basic house object
 * @author Frank
 *
 */
public class Castle extends AnimatedSprite {

	private int health;
	private int sector;
	
	/**
	 * Creates a house

	 * @param pX
	 * @param pY
	 * @param pTileWidth
	 * @param pTileHeight
	 * @param pTiledTextureRegion
	 */
	public Castle(final float pX, final float pY,
			TiledTextureRegion pTiledTextureRegion, final int sector, final int health) {
		super(pX, pY,pTiledTextureRegion);
		this.health = health;
		this.sector = sector;
	}
	
	public int getType(){
		return this.sector;
	}
	
	public void setType(final int type){
		this.sector = type;
	}
	
	public int getHealth(){
		return this.health;
	}
	
	public void setHealth(final int health){
		this.health = health;
	}
	
	public void decreaseHealth(final int health){
		this.health-= health;
	}
}
