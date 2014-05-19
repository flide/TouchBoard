package inc.flide.touchboard.tools;

import inc.flide.touchboard.CanvasModel;

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

	public void setModel(CanvasModel model)
	{
		 this.model = model;
	}
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
