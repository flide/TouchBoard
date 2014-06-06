package inc.flide.touchboard.tool;

import inc.flide.touchboard.logging.*;
import inc.flide.touchboard.model.*;
import inc.flide.touchboard.*;

import android.view.MotionEvent;

import android.graphics.Canvas;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Path;

abstract public class Brush extends Tool
{
	public Brush(CanvasModel model)
	{
		super(model);
		Logger.Verbose(this.getClass().getName(), "Initiating Brush");

		path = new Path();
		paint = new Paint();

		Logger.Verbose(this.getClass().getName(), "Ending Brush");
	}

}
