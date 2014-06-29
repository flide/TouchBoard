package inc.flide.touchboard;

import inc.flide.touchboard.tool.*;
import inc.flide.touchboard.model.*;
import inc.flide.touchboard.logging.*;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.AttributeSet;
import android.content.Context;
import android.app.Activity;

import android.view.View;
import android.view.Display;
import android.view.MotionEvent;

import android.graphics.Canvas;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Matrix;
import android.hardware.display.DisplayManager;

//this will be the View of the MVC.
//I am not very convinced that CanvasView should be the View part of MVC model, but current
//experiment scenario dictates that I try this out.
//Primary Responsibility and the ONLY Responsibility this class should and will have, would be
//to Present the screen to the user.
//Strict No to state mantianance, handling user input or doing any data manipulation.
public class CanvasView extends View
{
	
	private static int currentDisplayOrientation;
	private CanvasModel model;

	public void setModel(CanvasModel model)
	{
		 this.model = model;
	}
	
	public CanvasView(Context context) 
	{
		super(context);
		Logger.setTag(this.getClass().getName());
		Logger.Verbose("CanvasView(Context) Started");
		Logger.Verbose("CanvasView(Context) Ended");
	}
	
	public CanvasView(Context context, AttributeSet attrs) 
	{
		super(context, attrs);
		Logger.setTag(this.getClass().getName());
		Logger.Verbose("CanvasView(Context, AtributeSet) Started");
		Logger.Verbose("CanvasView(Context, AtributeSet) Ended");
	}
	
	public CanvasView(Context context, AttributeSet attrs, int defStyleAttr) 
	{
		super(context, attrs, defStyleAttr);
		Logger.setTag(this.getClass().getName());
		Logger.Verbose("CanvasView(Context, AtributeSet, int) Started");
		Logger.Verbose("CanvasView(Context, AtributeSet, int) Ended");
	}


	@Override
	protected void onDraw(Canvas canvas) 
	{
		Logger.Verbose( "Starting onDraw()");
		//Debugging
		Bitmap bitmap = model.getBitmap();
		Logger.Debug("Bitmap is ok");
		Paint paint = model.getTool().getPaint();
		Logger.Debug("Paint is ok");
		//debugging ended
		canvas.drawBitmap(model.getBitmap(), 0, 0,model.getTool().getPaint());
		Logger.Verbose( "Ending onDraw()");
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) 
	{
		Logger.Verbose("onSizeChanged(w="+w+", h="+h+", oldw="+oldw+", oldh="+oldh+") Started");
		super.onSizeChanged(w, h, oldw, oldh);

		Activity parentActivity = (Activity)getContext();
		Display display = parentActivity.getWindowManager().getDefaultDisplay();

		Bitmap canvasBitmap;

		if(oldw==0 && oldh==0)
		{
			//Initialization of the application and this block will run only once.
			Logger.Verbose("Application Initialized");
			canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
			currentDisplayOrientation = display.getRotation();
		}
		else if( currentDisplayOrientation != display.getRotation())
		{
			Logger.Verbose("Screen was reoriented");
			Matrix matrix = new Matrix();
			int previousDisplayOrientation = currentDisplayOrientation;
			currentDisplayOrientation = display.getRotation();
			int rotateBy = (currentDisplayOrientation - previousDisplayOrientation) * -1;
			matrix.postRotate(rotateBy*90f);
			Bitmap rotatedBitmap = Bitmap.createBitmap(model.getBitmap(), 0, 0, oldw, oldh, matrix, false);
			canvasBitmap = Bitmap.createBitmap(rotatedBitmap);
			rotatedBitmap.recycle();
		}
		else
		{
			 return;
		}
		Logger.Verbose("canvasBitmap set, Drawing to Canvas");
		//model data should not be manipulated in the View. Think something else.
		model.setBitmap(canvasBitmap);
		Logger.Verbose("onSizeChanged(int,int,int,int) Ended");
	}

}
