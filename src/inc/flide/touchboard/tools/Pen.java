package inc.flide.touchboard.tools;

import android.view.MotionEvent;

import android.graphics.Canvas;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Path;

public class Pen extends Brush
{
	public boolean handleTouchEvent(MotionEvent event)
	{
		float touchX = event.getX();
		float touchY = event.getY();
		
		switch (event.getAction()) 
		{
		case MotionEvent.ACTION_DOWN:
		    path.moveTo(touchX, touchY);
		    break;
		case MotionEvent.ACTION_MOVE:
		    path.lineTo(touchX, touchY);
		    break;
		case MotionEvent.ACTION_UP:
		    path.reset();
		    break;
		default:
		    return false;
		}
		return true;
	}
}
