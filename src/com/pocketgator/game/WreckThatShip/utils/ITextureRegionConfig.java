package com.pocketgator.game.WreckThatShip.utils;

import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

/**
 * All TextureRegionConfig should implement this interface
 * @author Frank
 *
 */
public interface ITextureRegionConfig {

	public abstract TextureRegion getTextureRegion();

	public abstract TiledTextureRegion getTiledTextureRegion(); 
}
