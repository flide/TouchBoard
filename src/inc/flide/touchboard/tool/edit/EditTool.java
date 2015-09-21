package inc.flide.touchboard.tool.edit;

import inc.flide.touchboard.logging.*;
import inc.flide.touchboard.tool.Tool;

import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;

abstract public class EditTool extends Tool
{

	public EditTool(){
		Logger.Verbose(this,"Initiating Brush");

		Logger.Verbose(this,"Ending Brush");
	}

	abstract public boolean handleTouchEvent(MotionEvent event);
}
