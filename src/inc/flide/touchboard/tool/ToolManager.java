package inc.flide.touchboard.tool;

import android.view.KeyEvent;
import inc.flide.touchboard.logging.Logger;
import inc.flide.touchboard.model.Mode;
import inc.flide.touchboard.tool.edit.Pen;
import inc.flide.touchboard.tool.observe.Observe;

public class ToolManager {

	private Mode currentMode = Mode.Edit;
	private Tool currentTool = new Pen();
	
	public final void changeMode(KeyEvent event) {
		Logger.Verbose(this, "Change Mode called");
		if (currentMode == Mode.Edit) {
			currentMode = Mode.Observe;
			currentTool = new Observe();
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
