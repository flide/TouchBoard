package inc.flide.touchboard.tools.viewport;

import inc.flide.touchboard.logging.*;
import inc.flide.touchboard.model.CanvasModel;
import inc.flide.touchboard.tools.*;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.view.MotionEvent;

public class Viewport extends Tool
{
	public boolean handleTouchEvent(MotionEvent event, CanvasModel model){
		Logger.Verbose(this, "Viewport tool started with the touch event consumption");
		Matrix matrix = new Matrix();
		matrix.setTranslate(100, 0);
		//matrix.postTranslate(15, 15);
		//matrix.preTranslate(15, 15);
		//matrix.postRotate(1 * 90f);
		Bitmap originalBitmap = model.getBitmap();
		Bitmap transformedBitmap = Bitmap.createBitmap(originalBitmap, 0, 0, originalBitmap.getWidth(), originalBitmap.getHeight(), matrix, true);
		model.setBitmap(transformedBitmap);
		Logger.Verbose(this, "So? is it translatting?");
		return true;
	}
}
