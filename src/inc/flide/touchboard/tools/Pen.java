package inc.flide.touchboard.tools;

import inc.flide.touchboard.logging.*;
import inc.flide.touchboard.*;

import android.view.MotionEvent;

import android.graphics.Canvas;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Path;

public class Pen extends Brush
{
	public Pen(CanvasModel model)
	{
		super(model);
		setPaintAttributes();
	}

	public boolean handleTouchEvent(MotionEvent event)
	{
		
		switch (event.getAction()) 
		{
		case MotionEvent.ACTION_DOWN:
			onPenDown(event);
		    break;
		case MotionEvent.ACTION_MOVE:
		    onPenMove(event);
		    break;
		case MotionEvent.ACTION_UP:
		    onPenUp(event);
		    break;
		default:
		    return false;
		}
		return true;
	}

	public void onPenDown(MotionEvent event)
	{
		float x = event.getX();
		float y = event.getY();
		 
		path.moveTo(x, y);
		path.addCircle(x, y, paint.getStrokeWidth()/2.0f,Path.Direction.CW);
		model.updateBitmap(path,paint);
	}
	public void onPenMove(MotionEvent event)
	{
		float x = event.getX();
		float y = event.getY();
		path.lineTo(x, y);
		model.updateBitmap(path,paint);
	}
	public void onPenUp(MotionEvent event)
	{
		float x = event.getX();
		float y = event.getY();
		model.updateBitmap(path,paint);
		path.rewind();
	}

	public void setPaintAttributes()
	{
		Logger.Verbose(this.getClass().getName(), "Starting setPaintAttributes()");
		if(paint == null)
			Logger.Verbose(this.getClass().getName(), "Paint happens to be null at this point!!");
		else
			Logger.Verbose(this.getClass().getName(), "Paint is A-OKAY");


		//paint.setColor(model.getForegroundColor());
		paint.setAntiAlias(true);
		paint.setStrokeWidth(5);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeJoin(Paint.Join.ROUND);
		paint.setStrokeCap(Paint.Cap.ROUND);
		paint.setDither(true);
		Logger.Verbose(this.getClass().getName(), "Ending setPaintAttributes()");
	}
}
