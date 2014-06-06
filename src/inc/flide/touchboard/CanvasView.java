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
		Logger.Verbose(this.getClass().getName(),"CanvasView(Context) Started");
		Logger.Verbose(this.getClass().getName(),"CanvasView(Context) Ended");
	}
	
	public CanvasView(Context context, AttributeSet attrs) 
	{
		super(context, attrs);
		Logger.Verbose(this.getClass().getName(),"CanvasView(Context, AtributeSet) Started");
		Logger.Verbose(this.getClass().getName(),"CanvasView(Context, AtributeSet) Ended");
	}
	
	public CanvasView(Context context, AttributeSet attrs, int defStyleAttr) 
	{
		super(context, attrs, defStyleAttr);
		Logger.Verbose(this.getClass().getName(),"CanvasView(Context, AtributeSet, int) Started");
		Logger.Verbose(this.getClass().getName(),"CanvasView(Context, AtributeSet, int) Ended");
	}


	@Override
	protected void onDraw(Canvas canvas) 
	{
		Logger.Verbose(this.getClass().getName(), "Starting onDraw()");
		//Debugging
		Bitmap bitmap = model.getBitmap();
		Logger.Debug(this.getClass().getName(),"Bitmap is ok");
		Paint paint = model.getTool().getPaint();
		Logger.Debug(this.getClass().getName(),"Paint is ok");
		//debugging ended
		canvas.drawBitmap(model.getBitmap(), 0, 0,model.getTool().getPaint());
		Logger.Verbose(this.getClass().getName(), "Ending onDraw()");
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) 
	{
		Logger.Verbose(this.getClass().getName(),"onSizeChanged(w="+w+", h="+h+", oldw="+oldw+", oldh="+oldh+") Started");
		super.onSizeChanged(w, h, oldw, oldh);

		Activity parentActivity = (Activity)getContext();
		Display display = parentActivity.getWindowManager().getDefaultDisplay();

		Bitmap canvasBitmap;

		if(oldw==0 && oldh==0)
		{
			//Initialization of the application and this block will run only once.
			Logger.Verbose(this.getClass().getName(),"Application Initialized");
			canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
			currentDisplayOrientation = display.getRotation();
		}
		else if( currentDisplayOrientation != display.getRotation())
		{
			Logger.Verbose(this.getClass().getName(),"Screen was reoriented");
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
		Logger.Verbose(this.getClass().getName(),"canvasBitmap set, Drawing to Canvas");
		//drawCanvas = new Canvas(canvasBitmap);
		//model data should not be manipulated in the View. Think something else.
		model.setBitmap(canvasBitmap);
		Logger.Verbose(this.getClass().getName(),"onSizeChanged(int,int,int,int) Ended");
	}

}
