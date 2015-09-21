package inc.flide.touchboard.tool.observe;

import inc.flide.touchboard.logging.*;
import inc.flide.touchboard.model.*;
import inc.flide.touchboard.tool.*;
import inc.flide.touchboard.*;

import android.view.MotionEvent;

import android.graphics.Canvas;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Path;

public class Zoom extends ObserveTool
{

	@Override
	public boolean handleTouchEvent(MotionEvent event)
	{
		Logger.Info(this,"Zoom is supposed to handle this touchevent");
		return false;
	}
}
