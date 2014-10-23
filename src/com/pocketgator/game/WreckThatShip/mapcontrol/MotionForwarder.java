package com.pocketgator.game.WreckThatShip.mapcontrol;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.input.touch.TouchEvent;

import android.view.GestureDetector;

/**
 * Helps figure out the gestures using android gesture detector
 * @author Frank
 *
 */
public class MotionForwarder implements IOnSceneTouchListener{

	private GestureDetector detector;
	
	public MotionForwarder(GestureDetector detector){
		this.detector = detector;
	}
	
	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		return this.detector.onTouchEvent(pSceneTouchEvent.getMotionEvent());
	}

}
