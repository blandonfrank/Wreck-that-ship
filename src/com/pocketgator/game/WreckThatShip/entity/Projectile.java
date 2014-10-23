package com.pocketgator.game.WreckThatShip.entity;

import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

/**
 * Projectiles depending on levels
 * @author Frank
 *
 */
public class Projectile extends AnimatedSprite{

	private static float mX;
	private static float mY;
	private static TiledTextureRegion mTiledTextureRegion;

	/**
	 * Creates a projectile object
	 * @param pX
	 * @param pY
	 * @param pTileWidth
	 * @param pTileHeight
	 * @param pTiledTextureRegion
	 */
	public Projectile(float pX, float pY, float pTileHeight,
			TiledTextureRegion pTiledTextureRegion) {
		super(pX, pY,pTiledTextureRegion);
	}

	public Projectile() {
		super(mX, mY, mTiledTextureRegion);
	}


}
