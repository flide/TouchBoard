package inc.flide.touchboard.model;

import android.graphics.Bitmap;

abstract public class CanvasModel_ReadOnly
{
	abstract public int getForegroundColor();
	abstract public int getBackgroundColor();
	abstract public Bitmap getBitmap();
}

