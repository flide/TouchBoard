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
		float x = event.getX();
		float y = event.getY();
		
		switch (event.getAction()) 
		{
		case MotionEvent.ACTION_DOWN:
		    path.moveTo(x, y);
		    path.addCircle(x, y, paint.getStrokeWidth()/2,Path.Direction.CW);
		    model.updateBitmap(path,paint);
		    break;
		case MotionEvent.ACTION_MOVE:
		    path.lineTo(x, y);
		    model.updateBitmap(path,paint);
		    break;
		case MotionEvent.ACTION_UP:
		    //update model with the new path
		    model.updateBitmap(path,paint);
		    path.reset();
		    break;
		default:
		    return false;
		}
		return true;
	}
}
