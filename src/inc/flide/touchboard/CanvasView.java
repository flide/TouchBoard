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

public class CanvasView extends View
{
	
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
		Logger.Verbose(this.getClass().getName(),"CanvasView(Context) Started");
		SetupDrawing();
		Logger.Verbose(this.getClass().getName(),"CanvasView(Context) Ended");
	}
	
	public CanvasView(Context context, AttributeSet attrs) 
	{
		super(context, attrs);
		Logger.Verbose(this.getClass().getName(),"CanvasView(Context, AtributeSet) Started");
		SetupDrawing();
		Logger.Verbose(this.getClass().getName(),"CanvasView(Context, AtributeSet) Ended");
	}
	
	public CanvasView(Context context, AttributeSet attrs, int defStyleAttr) 
	{
		super(context, attrs, defStyleAttr);
		Logger.Verbose(this.getClass().getName(),"CanvasView(Context, AtributeSet, int) Started");
		SetupDrawing();
		Logger.Verbose(this.getClass().getName(),"CanvasView(Context, AtributeSet, int) Ended");
	}

	private void SetupDrawing()
	{

		Logger.Verbose(this.getClass().getName(),"SetupDrawing() started");
		currentTool = new Pen();
		Logger.Verbose(this.getClass().getName(),"SetupDrawing() ended");
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
		Logger.Verbose(this.getClass().getName(),"onSizeChanged(w="+w+", h="+h+", oldw="+oldw+", oldh="+oldh+") Started");
		super.onSizeChanged(w, h, oldw, oldh);

		Activity parentActivity = (Activity)getContext();
		Display display = parentActivity.getWindowManager().getDefaultDisplay();

		if(oldw==0 && oldh==0)
		{
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
			Bitmap rotatedBitmap = Bitmap.createBitmap(canvasBitmap, 0, 0, oldw, oldh, matrix, false);
			canvasBitmap.recycle();
			canvasBitmap = Bitmap.createBitmap(rotatedBitmap);
			rotatedBitmap.recycle();
		}
		Logger.Verbose(this.getClass().getName(),"canvasBitmap set, Drawing to Canvas");
		drawCanvas = new Canvas(canvasBitmap);
		Logger.Verbose(this.getClass().getName(),"onSizeChanged(int,int,int,int) Ended");
	}

	
}
