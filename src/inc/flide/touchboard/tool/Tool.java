package inc.flide.touchboard.tool;

import android.view.MotionEvent;

import android.graphics.Paint;
import android.graphics.Path;

abstract public class Tool {
	protected Path path;
	protected Paint paint;
	
	protected Tool(){
		path = new Path();
		paint = new Paint();
	}
	abstract public boolean handleTouchEvent(MotionEvent event);

	public Path getPath() {
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
	}

	public Paint getPaint() {
		return paint;
	}

	public void setPaint(Paint paint) {
		this.paint = paint;
	}
	
}
