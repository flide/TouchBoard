package inc.flide.touchboard.tool;

import inc.flide.touchboard.model.CanvasModel;

import android.view.MotionEvent;

import android.graphics.Canvas;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Path;

abstract public class Tool
{
	protected Path path;
	protected Paint paint;
	protected CanvasModel model;
	abstract public boolean handleTouchEvent(MotionEvent event);

	public Tool(CanvasModel model)
	{
		 this.model = model;
	}

	abstract public void onPenDown(MotionEvent event);
	abstract public void onPenMove(MotionEvent event);
	abstract public void onPenUp(MotionEvent event);

	public Path getPath()
	{
		 return path;
	}
	public void setPath(Path path)
	{
		 this.path = new Path(path);
	}
	public Paint getPaint()
	{
		 return paint;
	}
	public void setPaint(Paint input_paint)
	{
		 this.paint = new Paint(input_paint);
	}
}
