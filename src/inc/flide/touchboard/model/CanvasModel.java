package inc.flide.touchboard.model;

import android.graphics.Canvas;

import java.util.Iterator;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Path;
import inc.flide.touchboard.view.Observer;
import android.graphics.Color;

/*
 * It is the Model's job to notify the View when something has changed.
 * */
public class CanvasModel implements Subject{
	private int foregroundColor, backgroundColor;
	private Bitmap bitmap;
	private Canvas canvas;
	
	public CanvasModel() {
		this.backgroundColor = Color.argb(0, 0, 0, 0);
		this.foregroundColor = Color.BLACK;
		this.bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
		this.canvas = new Canvas(bitmap);
	}

	public void updateBitmap(Path path, Paint paint) {
		canvas.drawPath(path, paint);
		notifyObservers();
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
		this.bitmap = bitmap;
		this.canvas.setBitmap(this.bitmap);
		notifyObservers();
	}

	public void resetBitmap() {
		this.bitmap.eraseColor(backgroundColor);
		notifyObservers();
	}

	@Override
	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void notifyObservers() {
		Iterator<Observer> observerIterator = observers.iterator();
		while(observerIterator.hasNext()){
			observerIterator.next().subjectUpdated();
		}
	}
}
