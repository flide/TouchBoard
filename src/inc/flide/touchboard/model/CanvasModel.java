package inc.flide.touchboard.model;

import android.graphics.Canvas;

import java.util.Iterator;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Path;
import inc.flide.touchboard.Observer;
import inc.flide.touchboard.Subject;
import android.graphics.Color;

public class CanvasModel extends CanvasModel_ReadOnly implements Subject{
	private int foregroundColor, backgroundColor;
	private Bitmap bitmap;
	private static CanvasModel model;
	
	private CanvasModel() {
		this.backgroundColor = Color.WHITE;
		this.foregroundColor = Color.BLACK;
	}

	public static CanvasModel getModel(){
		if(model == null){
			model = new CanvasModel();
		}
		
		return model;
	}

	public void updateBitmap(Path path, Paint paint) {
		Canvas canvas = new Canvas(bitmap);
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
		this.bitmap = Bitmap.createBitmap(bitmap);
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
