package inc.flide.touchboard;

import inc.flide.touchboard.tools.*;
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

public class CanvasView extends View implements LoggingConstants
{

	//Adding the Debug variables and functions
	private static final String CLASS_NAME = "CanvasView";

	void Verbose(String message)
	{
		 if(LoggingConstants.VERBOSE == true)
			 Log.v(LoggingConstants.Project_Name, CLASS_NAME + message);
			  
	}

	void Debug(String message)
	{
		 if(LoggingConstants.DEBUG == true)
			 Log.d(LoggingConstants.Project_Name, CLASS_NAME + message);
			  
	}
	//Debuging segment ends
	
	static int currentDisplayOrientation;

	Tool currentTool;
	//drawing path
	private Path path;
	//drawing and canvas paint
	private Paint paint;
	//initial color
	private int paintColor = 0xFF000000;
	//canvas
	private Canvas drawCanvas;
	//canvas bitmap
	private Bitmap canvasBitmap;
	
	public CanvasView(Context context) 
	{
		super(context);
		Verbose("CanvasView(Context) Started");
		SetupDrawing();
		Verbose("CanvasView(Context) Ended");
	}
	
	public CanvasView(Context context, AttributeSet attrs) 
	{
		super(context, attrs);
		Verbose("CanvasView(Context, AtributeSet) Started");
		SetupDrawing();
		Verbose("CanvasView(Context, AtributeSet) Ended");
	}
	
	public CanvasView(Context context, AttributeSet attrs, int defStyleAttr) 
	{
		super(context, attrs, defStyleAttr);
		Verbose("CanvasView(Context, AtributeSet, int) Started");
		SetupDrawing();
		Verbose("CanvasView(Context, AtributeSet, int) Ended");
	}

	private void SetupDrawing()
	{

		Verbose("SetupDrawing() started");
		currentTool = new Pen();
		Verbose("SetupDrawing() ended");
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) 
	{

	//detect user touch
		
		currentTool.handleTouchEvent(event);
		drawCanvas.drawPath(currentTool.getPath(), currentTool.getPaint());
		invalidate(); 		//Calls on draw
		return true;
	}

	@Override
	protected void onDraw(Canvas canvas) 
	{
	//draw view
		canvas.drawBitmap(canvasBitmap, 0, 0,currentTool.getPaint());
		//canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
		//canvas.drawPath(path, paint);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) 
	{
		Verbose("onSizeChanged(w="+w+", h="+h+", oldw="+oldw+", oldh="+oldh+") Started");
		super.onSizeChanged(w, h, oldw, oldh);

		Activity parentActivity = (Activity)getContext();
		Display display = parentActivity.getWindowManager().getDefaultDisplay();

		if(oldw==0 && oldh==0)
		{
			Verbose("Application Initialized");
			canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
			currentDisplayOrientation = display.getRotation();
		}
		else if( currentDisplayOrientation != display.getRotation())
		{
			Verbose("Screen was reoriented");
			Matrix matrix = new Matrix();
			int previousDisplayOrientation = currentDisplayOrientation;
			currentDisplayOrientation = display.getRotation();
			int rotateBy = (currentDisplayOrientation - previousDisplayOrientation) * -1;
			matrix.postRotate(rotateBy*90f);
			Bitmap rotatedBitmap = Bitmap.createBitmap(canvasBitmap, 0, 0, oldw, oldh, matrix, false);
			canvasBitmap.recycle();
			canvasBitmap = Bitmap.createBitmap(rotatedBitmap);
			rotatedBitmap.recycle();
		}
		Verbose("canvasBitmap set, Drawing to Canvas");
		drawCanvas = new Canvas(canvasBitmap);
		Verbose("onSizeChanged(int,int,int,int) Ended");
	}

	
}
