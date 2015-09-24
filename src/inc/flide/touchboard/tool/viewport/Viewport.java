package inc.flide.touchboard.tool.viewport;

import inc.flide.touchboard.logging.*;
import inc.flide.touchboard.tool.*;

import android.view.MotionEvent;

public class Viewport extends Tool
{
	public boolean handleTouchEvent(MotionEvent event){
		Logger.Info(this, "Touch Event consumed in the Observe Tool");
		return true;
	}
}
