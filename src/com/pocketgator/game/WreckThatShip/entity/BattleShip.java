package com.pocketgator.game.WreckThatShip.entity;

import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import com.badlogic.gdx.physics.box2d.Body;

/**
 * 
 * @author Frank
 *
 */
public class BattleShip extends Ship implements IShootStrategy {

	public BattleShip(float positionX, float positionY, int shipID,
			int shipHealth, int range, int speed,
			TiledTextureRegion pTiledTextureRegion) {
		super(positionX, positionY, shipID, shipHealth, range, speed,
				pTiledTextureRegion);
	}


	@Override
	public void shoot(Body body) {
		// TODO Auto-generated method stub
		
	}

}
