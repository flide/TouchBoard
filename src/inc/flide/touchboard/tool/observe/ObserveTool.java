package inc.flide.touchboard.tool.observe;

import inc.flide.touchboard.logging.*;
import inc.flide.touchboard.model.*;
import inc.flide.touchboard.*;
import inc.flide.touchboard.tool.*;

import android.view.MotionEvent;

import android.graphics.Canvas;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Path;

abstract public class ObserveTool extends Tool
{
	
	abstract public boolean handleTouchEvent(MotionEvent event);
}
