package com.pocketgator.game.WreckThatShip.entity;

import java.util.Random;

import org.anddev.andengine.extension.physics.box2d.PhysicsConnector;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.util.MathUtils;



import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * 
 * @author Frank
 *
 */
public class GreekShip extends Ship implements IShootStrategy {

	private final Random random;
	private float angle;
	private float power;
	private float xComponent;
	private float yComponent;
	private final static float maxAngle = 75;
	private final static float minAngle = 30;
	private final static float minPower = 10;
	private final static float maxPower = 20;
	private final PhysicsWorld mPhysicsWorld;
	private  Body shipBody;
	
	/**
	 * Creates a Greek ship
	 * @param positionX
	 * @param positionY
	 * @param shipID
	 * @param shipHealth
	 * @param range
	 * @param speed
	 * @param pTiledTextureRegion
	 * @param pPhysicsWorld
	 */
	public GreekShip(float positionX, float positionY, int shipID,
			int shipHealth, int range, int speed,
			TiledTextureRegion pTiledTextureRegion, PhysicsWorld pPhysicsWorld) {
		super(positionX, positionY, shipID, shipHealth, range, speed,
				pTiledTextureRegion);
		
		this.random = new Random();
		this.angle =0;
		this.power = 0;
		this.mPhysicsWorld = pPhysicsWorld;
		createBody();
	}

	/**
	 * Creates a body for the ship object
	 */
	private void createBody() {
		this.shipBody = PhysicsBodyFactory.createGreekShipBody(this, this.mPhysicsWorld);
		this.mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(this, shipBody, true,true,false,false));
		this.setUpdatePhysics(false);
	}

	/**
	 * Simple algorithm to shoot ramdonly between 30 - 90 degrees and power between 10 - 20
	 */
	@Override
	public void shoot(Body body) {
		this.angle = minAngle + (this.random.nextFloat() * ((maxAngle - minAngle) + 1));
		this.power = minPower + (this.random.nextFloat() * ((maxPower - minPower) + 1));
		this.angle = MathUtils.degToRad(angle);
		this.xComponent = (float) Math.cos(angle);
		this.yComponent = (float) Math.sin(angle);
		
		body.setLinearVelocity(new Vector2(power * -xComponent, power * -yComponent));
	}
	
	/**
	 * Returns the body that is attached to this ship
	 * @return
	 */
	public Body getBody(){
		return this.shipBody;
	}
	



}
