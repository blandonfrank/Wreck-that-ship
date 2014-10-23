package com.pocketgator.game.WreckThatShip.mapcontrol;

import org.anddev.andengine.engine.camera.ZoomCamera;
import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.input.touch.TouchEvent;

import android.util.Log;
import android.view.MotionEvent;

/**
 * Will do the panning of the map
 * @author Frank
 * @author Br
 */
public class MapPanning implements IOnSceneTouchListener, IUpdateHandler {


	
	private ZoomCamera camera;
    private boolean touchDown = false;
    private float speedX;
    private float speedY;
    private float lastX;
    private float lastY;
   
    public MapPanning(ZoomCamera camera)
    {
            this.camera = camera;
            
    }
   
    @Override
    public boolean onSceneTouchEvent(Scene pScene, TouchEvent touchEvent)
    {
            MotionEvent evt = touchEvent.getMotionEvent();
           
            if(evt.getAction() == MotionEvent.ACTION_DOWN)
            {
                    this.touchDown = true;
                    this.lastX = evt.getRawX();
                    this.lastY = evt.getRawY();    
                    this.speedX = 0;
                    this.speedY = 0;
            }
            if(this.touchDown)
            {

            	//this.camera.setReleased(false);
                  float centerX = camera.getCenterX() + (lastX - evt.getRawX()) / this.camera.getZoomFactor();
                  float centerY = camera.getCenterY();
                  this.camera.setCenter(centerX, centerY);

                  
                  this.lastX = evt.getRawX();
                  this.lastY = evt.getRawY();    
            }
            if(evt.getAction() == MotionEvent.ACTION_UP)
            {
                    this.touchDown = false;
            }
            return touchDown;
    }


    @Override
    public void onUpdate(float pSecondsElapsed)
    {
            if(!this.touchDown && (this.speedX != 0 || this.speedY != 0))
            {
            		//this.camera.setReleased(false);
            		float centerX = this.camera.getCenterX() - (speedX * pSecondsElapsed / this.camera.getZoomFactor());
            		float centerY = this.camera.getCenterY();
            		
            		this.camera.setCenter(centerX, centerY );
            		
                    this.speedX *= (1.0f - 1.2f * pSecondsElapsed);
                    this.speedY *= (1.0f - 1.2f * pSecondsElapsed);
                   
                    if(speedX < 10 && speedX > -10) speedX = 0;
                    if(speedY < 10 && speedY > -10) speedY = 0;
            }              
    }

    @Override
    public void reset()
    {
           
    }

    public float getSpeedX()
    {
            return speedX;
    }

    public void setSpeedX(float speedX)
    {
            this.speedX = speedX;
    }

    public float getSpeedY()
    {
            return speedY;
    }

    public void setSpeedY(float speedY)
    {
            this.speedY = speedY;
    }
   
    public void cancelCurScrolling()
    {
            this.touchDown = false;
    }
    
   
}

