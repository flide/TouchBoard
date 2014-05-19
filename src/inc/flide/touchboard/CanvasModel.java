package inc.flide.touchboard;

import inc.flide.touchboard.tools.*;
import inc.flide.touchboard.logging.*;

import android.graphics.Canvas;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Path;

public class CanvasModel
{
	private Tool tool;
	private int foregroundColor, backgroundColor;
	private Bitmap bitmap;

	public CanvasModel()
	{
		 Logger.Verbose(this.getClass().getName(), "Trying to create Model object");
		 tool = new Pen();
		 tool.setModel(this);
		 Logger.Verbose(this.getClass().getName(), "Tool has been initialized");
	}
	public void updateBitmap(Path path, Paint paint)
	{
		 Canvas canvas = new Canvas(bitmap);
		 canvas.drawPath(path,paint);
	}
	//Getter's and Setter's for the data variables
	public Tool getTool()
	{
		 return tool;
	}
	public void setTool(Tool tool)
	{
		 this.tool = tool;
	}
	public int getForegroundColor()
	{
		 return foregroundColor;
	}
	public void setForegroundColor(int foregroundColor)
	{
		 this.foregroundColor = foregroundColor;
	}
	public int getBackgroundColor()
	{
		 return backgroundColor;
	}
	public void setBackgroundColor(int backgroundColor)
	{
		 this.backgroundColor = backgroundColor;
	}
	public Bitmap getBitmap()
	{
		 return bitmap;
	}
	public void setBitmap(Bitmap bitmap)
	{
		 this.bitmap = Bitmap.createBitmap(bitmap);
	}
}
