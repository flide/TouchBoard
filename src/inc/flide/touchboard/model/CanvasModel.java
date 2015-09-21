package inc.flide.touchboard.model;

import android.graphics.Canvas;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.KeyEvent;
import inc.flide.touchboard.logging.Logger;

public class CanvasModel extends CanvasModel_ReadOnly {
	private int foregroundColor, backgroundColor;
	private Bitmap bitmap;
	private Mode currentMode;


	public final void changeMode(KeyEvent event) {
		Logger.Verbose(this, "Change Mode called");
		if (currentMode == Mode.Edit) {
			currentMode = Mode.Observe;
		} else if (currentMode == Mode.Observe) {
			currentMode = Mode.Edit;
		}
	}

	public CanvasModel() {
	}

	public Mode getCurrentMode() {
		return currentMode;
	}

	public void updateBitmap(Path path, Paint paint) {
		Canvas canvas = new Canvas(bitmap);
		canvas.drawPath(path, paint);
	}

	// Getter's and Setter's for the data variables
	public int getForegroundColor() {
		return foregroundColor;
	}

	public void setForegroundColor(int foregroundColor) {
		this.foregroundColor = foregroundColor;
	}

	public int getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(int backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = Bitmap.createBitmap(bitmap);
	}

}
