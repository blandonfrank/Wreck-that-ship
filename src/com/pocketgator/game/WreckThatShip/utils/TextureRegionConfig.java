package com.pocketgator.game.WreckThatShip.utils;

import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;


import android.content.Context;
/**
 * This class will define a configuration for a texture region
 * @author Frank
 *
 */
public final class TextureRegionConfig implements ITextureRegionConfig{

	/*
	 * Fields
	 */
	
	private final Context context;
	private final String path;
	private final int pTexturePositionX;
	private final int pTexturePositionY;
	private final Texture texture;
	
	/**
	 * Creates a new TextureRegionConfig object
	 * @param ptexture			
	 * @param pcontext	
	 * @param path					This is the path of where the Texture is found i.e: assets/gfx/...
	 * @param pTexturePositionX		X-position of the region in the texture 
	 * @param pTexturePositionY		Y-position of the region in the texture 
	 */
	public TextureRegionConfig(final Texture ptexture, final Context pcontext, final String path, final int pTexturePositionX, final int pTexturePositionY){
		this.texture = ptexture;
		this.context = pcontext;
		this.path = path;
		this.pTexturePositionX = pTexturePositionX;
		this.pTexturePositionY = pTexturePositionY;
	}
	/**
	 * Returns a texture region
	 */
	public TextureRegion getTextureRegion() {
		return TextureRegionFactory.createFromAsset(this.texture, this.context, this.path, this.pTexturePositionX, this.pTexturePositionY);
	}
	/** 
	 * Use {@link getTextureRegion} 
	 */
	@Deprecated 
	public TiledTextureRegion getTiledTextureRegion() {
		return null;
	}
}
