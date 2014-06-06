package inc.flide.touchboard.tool;

import inc.flide.touchboard.logging.*;
import inc.flide.touchboard.model.*;
import inc.flide.touchboard.*;

import android.view.MotionEvent;

import android.graphics.Canvas;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Path;

public class Pen extends Brush
{
	private boolean penDownFlag,penMoveFlag,penUpFlag;
	public Pen(CanvasModel model)
	{
		super(model);
		setPaintAttributes();
		clearPenDownFlag();
		clearPenMoveFlag();
		clearPenUpFlag();
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
		setPenDownFlag();
		float x = event.getX();
		float y = event.getY();
		 
		path.moveTo(x, y);
		//model.updateBitmap(path,paint);
	}
	public void onPenMove(MotionEvent event)
	{
		setPenMoveFlag();
		clearPenDownFlag();
		float x = event.getX();
		float y = event.getY();
		path.lineTo(x, y);
		model.updateBitmap(path,paint);
	}
	public void onPenUp(MotionEvent event)
	{
		setPenUpFlag();

		float x = event.getX();
		float y = event.getY();
		
		if(penDownFlag == true)
		{
			path.addCircle(x, y, paint.getStrokeWidth()/2.0f,Path.Direction.CW);
			model.updateBitmap(path,paint);
			clearPenDownFlag();
		}
		else if(penMoveFlag == true)
		{
			model.updateBitmap(path,paint);
			clearPenMoveFlag();
		}
		path.reset();
		clearPenUpFlag();
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

	private void setPenDownFlag()
	{ penDownFlag = true; }
	private void clearPenDownFlag()
	{ penDownFlag = false; }
	private void setPenMoveFlag()
	{ penMoveFlag = true; }
	private void clearPenMoveFlag()
	{ penMoveFlag = false; }
	private void setPenUpFlag()
	{ penUpFlag = true; }
	private void clearPenUpFlag()
	{ penUpFlag = false; }
}
