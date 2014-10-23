package com.pocketgator.game.WreckThatShip.mapcontrol;

import org.anddev.andengine.engine.camera.ZoomCamera;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.input.touch.TouchEvent;


import android.util.FloatMath;
import android.util.Log;

import android.view.MotionEvent;
/**
 * Takes advantage of the multitouch functionalities of the phone
 * PinchZoomDetector is only usable on multitouch-enalbed versions (2.1+)
 * @author Frank
 * @author BrR
 * 
 */
public class PinchZoomDetector implements IOnSceneTouchListener
{	
	private float lastSpacing;
	private final ZoomCamera cam;
	private final MapPanning panner;
	private float zoomFactor;
	private  boolean pinching;
	
	
	
	public PinchZoomDetector(ZoomCamera cam, MapPanning panner)
	{
		this.pinching = true;
		this.cam = cam;
		this.panner = panner;

	}
	
	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent)
	{
		int action = pSceneTouchEvent.getAction();
		if(action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_POINTER_1_UP || action == MotionEvent.ACTION_POINTER_2_UP)
		{
			this.lastSpacing = 0;
			return false;
		}
		if(pSceneTouchEvent.getMotionEvent().getPointerCount() == 2)
		{
			if(this.cam.getZoomFactor() > 0.9f ){
	       		this.cam.setReleased(false);
	       		this.pinching = true;
			}
			this.handleMultitouchGesture(pSceneTouchEvent.getMotionEvent());
			
			return true;
		}
		return false;
		
	}
	
	private void handleMultitouchGesture(MotionEvent evt) {

		int maxZF = 1;
		
		float ratio = this.cam.getWidth() / this.cam.getHeight();
		
		float minZF = 1 / ratio;
		
		zoomFactor = this.cam.getZoomFactor();
		this.panner.cancelCurScrolling();
		
		if(this.cam.getReleased() && zoomFactor == minZF)
			pinching = false;
		
		if(this.cam.getReleased())
			pinching = false;
		
		if (pinching && !this.cam.getReleased()) {
			final float curSpacing = this.spacing(evt);
			if (this.lastSpacing > 0) {

				

				zoomFactor = this.cam.getZoomFactor()
						* (curSpacing / this.lastSpacing);

				if (zoomFactor <= maxZF && zoomFactor >= minZF) {

					zoomFactor *= (curSpacing / this.lastSpacing);



					if (zoomFactor > maxZF)
						zoomFactor = maxZF;

					else if (zoomFactor < minZF) {
						zoomFactor = minZF;
					}
					this.cam.setZoomFactor(zoomFactor);

					this.cam.setCenter(this.cam.getCenterX(), this.cam
							.getHeight());

				}
			}
			this.lastSpacing = curSpacing;
		}
	}
	
	private float spacing(MotionEvent evt)
	{
		float x = evt.getX(0) - evt.getX(1);
		float y = evt.getY(0) - evt.getY(1);
		return FloatMath.sqrt(x * x + y * y);
	}	
	
	public void cancelPinching(){
		this.pinching = false;
	}
	
}
