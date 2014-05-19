package inc.flide.touchboard.tools;

import inc.flide.touchboard.logging.*;
import android.view.MotionEvent;

import android.graphics.Canvas;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Path;

abstract public class Brush extends Tool
{
	public Brush()
	{
		Logger.Verbose(this.getClass().getName(), "Initiating Brush");
		//setup default brush
		path = new Path();
		paint = new Paint();
		int color = 0xFF000000;

		paint.setColor(color);
		paint.setAntiAlias(true);
		paint.setStrokeWidth(1);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeJoin(Paint.Join.ROUND);
		paint.setStrokeCap(Paint.Cap.ROUND);
		paint.setDither(true);
		Logger.Verbose(this.getClass().getName(), "Ending Brush");
	}
}
