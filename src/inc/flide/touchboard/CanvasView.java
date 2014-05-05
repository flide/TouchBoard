package inc.flide.touchboard;

import android.util.Log;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.Display;
import android.graphics.Canvas;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Matrix;
import android.view.MotionEvent;
import android.content.Context;
import android.hardware.display.DisplayManager;

public class CanvasView extends View 
{

	private static final String logTag = "CanvasView";
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
		Log.v(logTag, "CanvasView(Context) Started");
		SetupDrawing();
		Log.v(logTag, "CanvasView(Context) Ended");
	}
	
	public CanvasView(Context context, AttributeSet attrs) 
	{
		super(context, attrs);
		Log.v(logTag, "CanvasView(Context, AtributeSet) Started");
		SetupDrawing();
		Log.v(logTag, "CanvasView(Context, AtributeSet) Ended");
	}
	
	public CanvasView(Context context, AttributeSet attrs, int defStyleAttr) 
	{
		super(context, attrs, defStyleAttr);
		Log.v(logTag, "CanvasView(Context, AtributeSet, int) Started");
		SetupDrawing();
		Log.v(logTag, "CanvasView(Context, AtributeSet, int) Ended");
	}
	
	private void SetupDrawing()
	{

		Log.v(logTag, "SetupDrawing() started");
		path = new Path();
		paint = new Paint();
		
		paint.setColor(paintColor);
		paint.setAntiAlias(true);
		paint.setStrokeWidth(1);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeJoin(Paint.Join.ROUND);
		paint.setStrokeCap(Paint.Cap.ROUND);
		
		canvasPaint = new Paint(Paint.DITHER_FLAG);
		Log.v(logTag, "SetupDrawing() ended");
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) 
	{
		Log.v(logTag, "onSizeChanged(w="+w+", h="+h+", oldw="+oldw+", oldh="+oldh+") Started");
		super.onSizeChanged(w, h, oldw, oldh);

		DisplayManager displayManager = (DisplayManager)getContext().getSystemService(Context.DISPLAY_SERVICE);
		Display display = displayManager.getDisplay(Display.DEFAULT_DISPLAY);

		if(oldw==0 && oldh==0)
		{
			Log.v(logTag, "Application Initialized");
			canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
			currentDisplayOrientation = display.getRotation();
		}
		else if( currentDisplayOrientation != display.getRotation())
		{
			Log.v(logTag, "Screen was reoriented");
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
		Log.v(logTag, "canvasBitmap set, Drawing to Canvas");
		drawCanvas = new Canvas(canvasBitmap);
		Log.v(logTag, "onSizeChanged(int,int,int,int) Ended");
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
