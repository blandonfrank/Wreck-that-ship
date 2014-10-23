package com.pocketgator.game.WreckThatShip.entity;

import com.badlogic.gdx.physics.box2d.Body;

/**
 * 
 * @author Frank
 *
 */
public interface IShootStrategy {

	public abstract void shoot(Body body);
}
