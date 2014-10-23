package com.pocketgator.game.WreckThatShip.mapcontrol;

import org.anddev.andengine.engine.camera.ZoomCamera;
import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.shape.Shape;

/**
 * Helps prevent the game crashing in devices with no multitouch functionalities (<2.1)
 * @author Frank
 *
 */
public class MultitouchHolder {


	public static Scene.IOnSceneTouchListener getPinchZoomDetector(ZoomCamera cam, MapPanning panning){
		return SingletonHolder.getPinchZoomDetector(cam, panning);
	}
	
	static class SingletonHolder{
		private static Scene.IOnSceneTouchListener getPinchZoomDetector(ZoomCamera cam, MapPanning panning){
			return new PinchZoomDetector(cam, panning);
		}
	}
	
	

}
