package inc.flide.touchboard;

import inc.flide.touchboard.logging.*;
import inc.flide.touchboard.model.*;
import inc.flide.touchboard.tool.*;
import inc.flide.touchboard.tool.edit.Pen;
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
	private Pen tool;
	private CanvasModel model;

	public CanvasActivity() {
	}

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

		setImmersiveMode(); // if possible that is... get the best available

		model = new CanvasModel();
		Logger.Info(this,"model initialized");
		tool = new Pen();
		Logger.Debug(this,"tool created");	
		setContentView(R.layout.activity_canvas);
		Logger.Debug(this,"content view set");
		view = (CanvasView) findViewById(R.id.viewCanvas);
		Logger.Debug(this,"View found");
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
		tool.handleTouchEvent(event);
		Logger.v(this, "Tool handeled the event");
		model.updateBitmap(tool.getPath(), tool.getPaint());
		Logger.v(this, "model updated with the change");
		try{
			if(view == null){
				Logger.d(this, "how the fuck is 'view' null??");
			}
			view.invalidate();
			Logger.v(this, "View invalidated and redrawn");
		}catch(Exception e){
			Logger.e(this, "invalidate fucked up!!", e);
		}
		return true;
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		Logger.Verbose(this,"Starting dispatchKeyEvent(KeyEvent)");
		if (event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_DOWN) {
			Logger.Verbose(this,"Volume Key pressed");
			model.changeMode(event);
			return true;
		}
		Logger.Verbose(this,"Ending dispatchKeyEvent(KeyEvent)");
		return super.dispatchKeyEvent(event);
	}

	public void intializeBitmap(int w, int h) {
		Bitmap canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		model.setBitmap(canvasBitmap);
	}

	public void reorientScreen(int oldw, int oldh, int rotateBy) {
		Matrix matrix = new Matrix();
		matrix.postRotate(rotateBy * 90f);
		Bitmap rotatedBitmap = Bitmap.createBitmap(model.getBitmap(), 0, 0, oldw, oldh, matrix, false);
		model.setBitmap(rotatedBitmap);
	}
	
	public CanvasModel getModel(){
		return model;
	}
	
	public void setModel(CanvasModel  model){
		this.model = model;
	}
	
	public Tool getTool(){
		return tool;
	}
}
