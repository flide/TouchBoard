package inc.flide.touchboard.tool;

import android.view.KeyEvent;
import inc.flide.touchboard.logging.Logger;
import inc.flide.touchboard.model.Mode;
import inc.flide.touchboard.tool.edit.Pen;
import inc.flide.touchboard.tool.viewport.Viewport;

public class ToolManager {

	private Mode currentMode;
	private Tool currentTool = new Pen();
	
	public ToolManager(){
		currentMode = Mode.Edit;
	}
	public final void changeMode(KeyEvent event) {
		Logger.Verbose(this, "Change Mode called");
		if (currentMode == Mode.Edit) {
			currentMode = Mode.Observe;
			currentTool = new Viewport();
		} else if (currentMode == Mode.Observe) {
			currentMode = Mode.Edit;
			currentTool = new Pen();
		}
	}

	public Mode getCurrentMode() {
		return currentMode;
	}
	
	public Tool getTool(){
		return currentTool;
	}
}
