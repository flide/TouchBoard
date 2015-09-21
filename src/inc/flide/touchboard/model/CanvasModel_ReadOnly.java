package inc.flide.touchboard.model;

import android.graphics.Bitmap;
import android.graphics.Paint;

abstract public class CanvasModel_ReadOnly
{
	abstract public Mode getCurrentMode();
	abstract public int getForegroundColor();
	abstract public int getBackgroundColor();
	abstract public Bitmap getBitmap();
}

