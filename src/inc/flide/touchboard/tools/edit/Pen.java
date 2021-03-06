package inc.flide.touchboard.tools.edit;

import inc.flide.touchboard.logging.*;
import inc.flide.touchboard.model.CanvasModel;
import inc.flide.touchboard.tools.Tool;

import android.view.MotionEvent;

import android.graphics.Paint;
import android.graphics.Path;

public class Pen extends Tool
{
	private boolean penDownFlag,penMoveFlag,penUpFlag;
	
	public Pen(){
		setPaintAttributes();
		clearPenDownFlag();
		clearPenMoveFlag();
		clearPenUpFlag();
	}
	
	@Override
	public boolean handleTouchEvent(MotionEvent event, CanvasModel model)
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
		model.updateBitmap(path, paint);
		return true;
	}

	private void onPenDown(MotionEvent event)
	{
		path.reset();
		setPenDownFlag();
		float x = event.getX();
		float y = event.getY(); 
		path.moveTo(x, y);
	}

	private void onPenMove(MotionEvent event)
	{
		setPenMoveFlag();
		clearPenDownFlag();
		float x = event.getX();
		float y = event.getY();
		path.lineTo(x, y);
	}

	private void onPenUp(MotionEvent event)
	{
		setPenUpFlag();

		float x = event.getX();
		float y = event.getY();
		
		if(penDownFlag == true)
		{
			path.addCircle(x, y, paint.getStrokeWidth()/2.0f,Path.Direction.CW);
			clearPenDownFlag();
		}
		else if(penMoveFlag == true)
		{
			clearPenMoveFlag();
		}
		clearPenUpFlag();
	}

	private void setPaintAttributes()
	{
		//paint.setColor(model.getForegroundColor());
		paint.setAntiAlias(true);
		paint.setStrokeWidth(5);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeJoin(Paint.Join.ROUND);
		paint.setStrokeCap(Paint.Cap.ROUND);
		paint.setDither(true);
		Logger.Verbose(this,"Ending setPaintAttributes()");
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
