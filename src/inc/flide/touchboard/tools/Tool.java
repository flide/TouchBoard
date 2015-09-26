package inc.flide.touchboard.tools;

import android.view.MotionEvent;
import inc.flide.touchboard.model.CanvasModel;
import android.graphics.Paint;
import android.graphics.Path;

abstract public class Tool {
	protected Path path;
	protected Paint paint;
	
	protected Tool(){
		path = new Path();
		paint = new Paint();
	}
	abstract public boolean handleTouchEvent(MotionEvent event, CanvasModel model);

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
