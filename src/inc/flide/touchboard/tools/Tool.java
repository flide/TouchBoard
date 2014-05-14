package inc.flide.touchboard.tools;

import android.view.MotionEvent;

import android.graphics.Canvas;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Path;

abstract public class Tool
{
	protected Path path;
	protected Paint paint;
	protected int color;
	abstract public boolean handleTouchEvent(MotionEvent event);

	//getter methods
	public Path getPath()
	{
		 return path;
	}
	public Paint getPaint()
	{
		 return paint;
	}

	//setter methods
	public void setPaint(Paint input_paint)
	{
		 this.paint = new Paint(input_paint);
	}
}
