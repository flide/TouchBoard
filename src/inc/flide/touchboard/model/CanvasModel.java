package inc.flide.touchboard.model;

import android.graphics.Canvas;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Color;

public class CanvasModel extends CanvasModel_ReadOnly {
	private int foregroundColor, backgroundColor;
	private Bitmap bitmap;

	public CanvasModel() {
		this.backgroundColor = Color.WHITE;
		this.foregroundColor = Color.BLACK;
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

	public void resetBitmap() {
		this.bitmap.eraseColor(backgroundColor);
	}

}
