package inc.flide.touchboard;

import inc.flide.touchboard.logging.*;
import inc.flide.touchboard.model.*;
import inc.flide.touchboard.tool.*;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.Window;
import android.view.MotionEvent;
import android.view.KeyEvent;

//Treating this as the Controller in the MVC pattern.
//Major responsibility would be to handle all the inputs by the user,
//and would include the touch events and the key events.
//Also, Controller should NOT be responsible for the mantainance of the State of the application
//I say, Controller should also not be concerned with the modification of Model directly. But just might need 
//the access to the model to do some "stuff"
public class CanvasActivity extends Activity {

	private CanvasView view;
	private ToolManager toolManager;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Logger.Verbose(this,"Starting onCreate(Bundle)");
		super.onCreate(savedInstanceState);

		Window window = getWindow();
		WindowManager.LayoutParams wmlp = new WindowManager.LayoutParams();
		wmlp.copyFrom(window.getAttributes());
		if (Build.VERSION.SDK_INT >= 18) {
			wmlp.rotationAnimation = WindowManager.LayoutParams.ROTATION_ANIMATION_JUMPCUT;
		}
		window.setAttributes(wmlp);

		setImmersiveMode();

		setContentView(R.layout.activity_canvas);
		toolManager = new ToolManager();	
		view = (CanvasView) findViewById(R.id.viewCanvas);
		CanvasModel.getModel().registerObserver(view);
		
		Logger.Verbose(this,"Ending onCreate(Bundle)");
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus) {
			setImmersiveMode();
		}
	}

	@SuppressLint("InlinedApi")
	private void setImmersiveMode() {
		int uiOptions = getWindow().getDecorView().getSystemUiVisibility();
		int newUiOptions = uiOptions;

		// Set the Flags for maximum Screen utilization
		if (Build.VERSION.SDK_INT >= 18) {
			newUiOptions = newUiOptions | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
		}

		if (Build.VERSION.SDK_INT >= 16) {
			newUiOptions = newUiOptions | View.SYSTEM_UI_FLAG_FULLSCREEN;
			newUiOptions = newUiOptions | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
			newUiOptions = newUiOptions | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
		}

		if (Build.VERSION.SDK_INT >= 14) {
			newUiOptions = newUiOptions | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
		}

		if (Build.VERSION.SDK_INT >= 1) {
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
		}
		// All flags are a go, let it rip!!
		getWindow().getDecorView().setSystemUiVisibility(newUiOptions);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Logger.v(this, "Touch Event Encountered");
		toolManager.getTool().handleTouchEvent(event);
		Logger.v(this, "Tool handeled the event");
		return true;
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event){
		event.startTracking();
		return true;
	}
	
	@Override 
	public boolean onKeyUp(int keyCode, KeyEvent event){
		if (event.isTracking() && !event.isCanceled()) {
        	switch(event.getKeyCode()){
				case KeyEvent.KEYCODE_VOLUME_DOWN:
					toolManager.changeMode(event);
					return true;
				case KeyEvent.KEYCODE_BACK:
					CanvasModel.getModel().resetBitmap();
					return true;
        	}
        }
		return super.onKeyUp(keyCode, event);
	}
	
	@Override
	public boolean onKeyLongPress(int keyCode, KeyEvent event){
    	switch(event.getKeyCode()){
			case KeyEvent.KEYCODE_BACK:
				this.finish();
				return true;
    	}
		return super.onKeyLongPress(keyCode, event);
	}
	
	public void intializeBitmap(int w, int h) {
		Bitmap canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		CanvasModel.getModel().setBitmap(canvasBitmap);
	}

	public void reorientScreen(int oldw, int oldh, int rotateBy) {
		Matrix matrix = new Matrix();
		matrix.setRotate(rotateBy * 90f);
		Bitmap rotatedBitmap = Bitmap.createBitmap(CanvasModel.getModel().getBitmap(), 0, 0, oldw, oldh, matrix, false);
		CanvasModel.getModel().setBitmap(rotatedBitmap);
	}

}
