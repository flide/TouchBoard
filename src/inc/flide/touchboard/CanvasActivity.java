package inc.flide.touchboard;

import inc.flide.touchboard.logging.*;
import inc.flide.touchboard.*;
import inc.flide.touchboard.model.*;
import inc.flide.touchboard.tool.*;

import android.app.Activity;
import android.util.Log;
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
public class CanvasActivity extends Activity
{
	private CanvasModel model;
	private CanvasView view;

	public CanvasActivity()
	{
		Logger.setTag(this.getClass().getName());
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		Logger.Verbose("Starting onCreate(Bundle)");
		super.onCreate(savedInstanceState);

		Window window = getWindow();
		WindowManager.LayoutParams wmlp = new WindowManager.LayoutParams();
		wmlp.copyFrom(window.getAttributes());
		if(Build.VERSION.SDK_INT >= 18)
		{
			wmlp.rotationAnimation = WindowManager.LayoutParams.ROTATION_ANIMATION_JUMPCUT;
		}
		window.setAttributes(wmlp);

		setImmersiveMode();	//if possible that is... get the best available

		setContentView(R.layout.activity_canvas);

		model = new CanvasModel();
		view = (CanvasView)findViewById(R.id.viewCanvas);
		view.setModel(model);

		Logger.Verbose("Ending onCreate(Bundle)");
	}
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) 
	{
		Logger.Verbose("Starting the onWindowFocusChanged()");
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus) 
			{
			setImmersiveMode();
			}
		Logger.Verbose("Ending the onWindowFocusChanged()");
	}
    	
    	private void setImmersiveMode()
	{
		Logger.Verbose("Starting the setImmersiveMode()");
		int uiOptions = getWindow().getDecorView().getSystemUiVisibility();
		int newUiOptions = uiOptions;

		//Set the Flags for maximum Screen utilization
		
		if(Build.VERSION.SDK_INT >= 18)
		{
			//We have Immersive mode available
			newUiOptions = newUiOptions | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
		}

		if(Build.VERSION.SDK_INT >= 16)
		{
			 newUiOptions = newUiOptions | View.SYSTEM_UI_FLAG_FULLSCREEN;
			 newUiOptions = newUiOptions | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
			 newUiOptions = newUiOptions | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
		}

		if(Build.VERSION.SDK_INT >= 14)
		{
			 newUiOptions = newUiOptions | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
		}

		if(Build.VERSION.SDK_INT >= 1)
		{
			 getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
			 getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
		}
		//All flags are a go, let it rip!!
		getWindow().getDecorView().setSystemUiVisibility(newUiOptions);

		Logger.Verbose("Ending the setImmersiveMode()");
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) 
	{
		//currentTool.handleTouchEvent(event);
		model.getTool().handleTouchEvent(event);
		view.invalidate();
		return true;
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event)
	{
		Logger.Verbose("Starting dispatchKeyEvent(KeyEvent)");
		if(event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_DOWN || event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_UP)
		{
			Logger.Verbose("Volume Key pressed");
			model.changeMode(event); 
			return true;
		}
		Logger.Verbose("Ending dispatchKeyEvent(KeyEvent)");
		return super.dispatchKeyEvent(event);
	}

}
