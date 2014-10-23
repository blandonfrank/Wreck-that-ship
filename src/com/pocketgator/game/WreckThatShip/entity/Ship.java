package com.pocketgator.game.WreckThatShip.entity;

import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

/**
 * class that contains every element of a ship (The enemy)
 * @author Frank
 *
 */
public abstract class Ship extends AnimatedSprite{
	


	private static final boolean IS_WRECKED = true;
	private int shipID;
	private int shipHealth;
	private int range;
	private int speed;
	

	
	
	/**
	 * Creates an animated ship
	 * @param positionX
	 * @param positionY
	 * @param shipID
	 * @param shipHealth
	 * @param range
	 * @param speed
	 * @param pTiledTextureRegion
	 */
	public Ship(final float positionX, final float positionY, final int shipID, final int shipHealth, final int range, final int speed,final TiledTextureRegion pTiledTextureRegion){
		super(positionX, positionY,pTiledTextureRegion);
		this.shipID = shipID;
		this.shipHealth = shipHealth;
		this.range = range;
		this.speed = speed;
		
	}
	
	



	public int getShipHealth() {
		return this.shipHealth;
	}


	public int getRange() {
		return this.range;
	}

	
	public int getSpeed() {
		return this.speed;
	}

	
	public boolean isWrecked() {
		if (this.shipHealth <= 0)
			return Ship.IS_WRECKED;
		return false;
	}

	

	public void setShipHealth(int shipHealth) {
		this.shipHealth = shipHealth;
	}

	public void decreaseHealth(final int amount){
		this.shipHealth -= amount;
	}

	public void setRange(int range) {
		this.range = range;
	}

	
	public void setSpeed(int speed) {
		this.speed = speed;
	}


	public void setShipID(int shipID) {
		this.shipID = shipID;
	}


	public int getShipID() {
		return shipID;
	}
	


}
