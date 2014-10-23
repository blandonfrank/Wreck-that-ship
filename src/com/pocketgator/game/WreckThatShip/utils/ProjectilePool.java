package com.pocketgator.game.WreckThatShip.utils;

import org.anddev.andengine.util.pool.GenericPool;

import com.pocketgator.game.WreckThatShip.entity.Projectile;

public class ProjectilePool extends GenericPool <Projectile> {

	public ProjectilePool() {
		super();
	}

	/**
	 * 
	 * @param pInitialSize
	 * @param pGrowth
	 */
	public ProjectilePool(final int pInitialSize, final int pGrowth) {
		super(pInitialSize, pGrowth);
	}

	@Override
	protected Projectile onAllocatePoolItem() {
		final Projectile tmpProjectile = new Projectile();
		return null;
	}

	/**
	 * @param pItem
	 */
	protected void onHandleObtainItem(final Projectile pItem) {
		pItem.setVisible(true);
		pItem.setIgnoreUpdate(false);
	}
	
	/**
	 * @param pItem
	 */
	protected void onHandleRecycleItem(final Projectile pItem) {
		pItem.setVisible(false);
		pItem.setIgnoreUpdate(true);
	}


}
