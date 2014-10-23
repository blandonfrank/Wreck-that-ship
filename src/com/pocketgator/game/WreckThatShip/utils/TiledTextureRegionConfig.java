package com.pocketgator.game.WreckThatShip.utils;

import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import android.content.Context;

/**
 * This class will define a configuration for a tiled texture region
 * @author Frank
 *
 */
public class TiledTextureRegionConfig implements ITextureRegionConfig {
	
	
	private Context context;
	private final String path;
	private final int pTexturePositionX;
	private final int pTexturePositionY;
	private final int pTileColumns;
	private final int pTileRows;
	private final Texture texture;
	 
	/**
	 * 
	 * @param pTexture
	 * @param pContext
	 * @param path					This is the path of where the Texture is found i.e: assets/gfx/...
	 * @param pTexturePositionX		X-position of the region in the texture 
	 * @param pTexturePositionY		Y-position of the region in the texture 
	 * @param pTileColumns
	 * @param pTileRows
	 */
	public TiledTextureRegionConfig(final Texture pTexture, final Context pContext, final String path,final int pTexturePositionX, final int pTexturePositionY, final int pTileColumns, final int pTileRows){
		this.texture = pTexture;
		this.context = pContext;
		this.path = path;
		this.pTexturePositionX = pTexturePositionX;
		this.pTexturePositionY = pTexturePositionY;
		this.pTileColumns = pTileColumns;
		this.pTileRows = pTileRows;
	}
	
	
	/**
	 * Returns a TiledTextureRegion
	 * @return
	 */

	@Override
	public TiledTextureRegion getTiledTextureRegion() {
		return TextureRegionFactory.createTiledFromAsset(this.texture, this.context, this.path, this.pTexturePositionX, this.pTexturePositionY, this.pTileColumns, this.pTileRows);

	}

	/**
	 * Use {@link getTiledTextureRegion()}
	 */
	@Deprecated
	public TextureRegion getTextureRegion() {
		return null;
	}



}
