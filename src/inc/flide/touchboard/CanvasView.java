package inc.flide.touchboard;

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

	//Adding the Debug variables and functions
	private static final String CLASS_NAME = "CanvasView";
	private static final boolean DEBUG = true;
	private static final boolean VERBOSE = true;

	void Verbose(String message)
	{
		 if(VERBOSE == true)
			 Log.v(CLASS_NAME, message);
			  
	}

	void Debug(String message)
	{
		 if(DEBUG == true)
			 Log.d(CLASS_NAME, message);
			  
	}
	//Debuging segment ends
	
	static int currentDisplayOrientation;
	//drawing path
	private Path path;
	//drawing and canvas paint
	private Paint paint, canvasPaint;
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
		path = new Path();
		paint = new Paint();
		
		paint.setColor(paintColor);
		paint.setAntiAlias(true);
		paint.setStrokeWidth(1);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeJoin(Paint.Join.ROUND);
		paint.setStrokeCap(Paint.Cap.ROUND);
		
		canvasPaint = new Paint(Paint.DITHER_FLAG);
		Verbose("SetupDrawing() ended");
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

	@Override
	protected void onDraw(Canvas canvas) 
	{
	//draw view
		canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
		canvas.drawPath(path, paint);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) 
	{
	//detect user touch
		float touchX = event.getX();
		float touchY = event.getY();
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
		    path.moveTo(touchX, touchY);
		    break;
		case MotionEvent.ACTION_MOVE:
		    path.lineTo(touchX, touchY);
		    break;
		case MotionEvent.ACTION_UP:
		    drawCanvas.drawPath(path, paint);
		    path.reset();
		    break;
		default:
		    return false;
		}
		
		invalidate();
		return true;
	}
}
