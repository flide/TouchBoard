package inc.flide.touchboard.tool;

import inc.flide.touchboard.model.*;
import inc.flide.touchboard.logging.*;
import inc.flide.touchboard.tool.observe.ObserveTool;
import inc.flide.touchboard.tool.observe.Zoom;
import inc.flide.touchboard.tool.edit.EditTool;
import inc.flide.touchboard.tool.edit.Pen;
import android.view.MotionEvent;
import android.view.KeyEvent;

import android.graphics.Paint;
import android.graphics.Path;

abstract public class Tool {
	private EditTool editTool;
	private ObserveTool observeTool;
	private Pen currentTool;

	protected Path path;
	protected Paint paint;
	
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
