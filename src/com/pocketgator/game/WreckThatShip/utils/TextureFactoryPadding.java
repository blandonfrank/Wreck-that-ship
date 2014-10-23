package com.pocketgator.game.WreckThatShip.utils;

import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.opengl.texture.source.AssetTextureSource;
import org.anddev.andengine.opengl.texture.source.ITextureSource;

import android.content.Context;

/**
 * Use to remove artifacts that get added to pngs with transparent backgrounds
 * @author Frank
 * @author mr_deimos
 */
public class TextureFactoryPadding extends TextureRegionFactory {


	private static String sAssetBasePath = "";

	public static TextureRegion createFromAssetPadded(final Texture pTexture, final Context pContext, final String pAssetPath, final int pTexturePositionX, final int pTexturePositionY, final int padding) {
		final ITextureSource textureSource = new AssetTextureSource(pContext, TextureFactoryPadding.sAssetBasePath + pAssetPath);
		return TextureFactoryPadding.createFromSourcePadded(pTexture, textureSource, pTexturePositionX, pTexturePositionY, padding);
	}
	
	public static TextureRegion createFromSourcePadded(final Texture pTexture, final ITextureSource pTextureSource, final int pTexturePositionX, final int pTexturePositionY, final int padding) {
		final TextureRegion textureRegion = new TextureRegion(pTexture, pTexturePositionX+padding, pTexturePositionY+padding, pTextureSource.getWidth()-padding*2, pTextureSource.getHeight()-padding*2);
		pTexture.addTextureSource(pTextureSource, textureRegion.getTexturePositionX()-padding, textureRegion.getTexturePositionY()-padding);
		return textureRegion;
	}
	
}
