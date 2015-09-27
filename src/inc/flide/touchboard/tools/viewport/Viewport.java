package inc.flide.touchboard.tools.viewport;

import inc.flide.touchboard.logging.*;
import inc.flide.touchboard.model.CanvasModel;
import inc.flide.touchboard.tools.*;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.view.MotionEvent;
import android.graphics.Canvas;

public class Viewport extends Tool
{
	public boolean handleTouchEvent(MotionEvent event, CanvasModel model){
		Logger.Verbose(this, "Viewport tool started with the touch event consumption");
		Bitmap originalBitmap = model.getBitmap();
		Canvas canvas = new Canvas(originalBitmap);
		canvas.translate(100, 100);
		Logger.Verbose(this, "So? is it translatting?");
		return true;
	}
}
