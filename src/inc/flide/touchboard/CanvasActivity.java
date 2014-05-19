package inc.flide.touchboard;

import inc.flide.touchboard.logging.*;
import inc.flide.touchboard.*;
import inc.flide.touchboard.tools.*;

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

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		Logger.Verbose(this.getClass().getName(), "Starting onCreate(Bundle)");
		super.onCreate(savedInstanceState);

		Window window = getWindow();
		WindowManager.LayoutParams wmlp = new WindowManager.LayoutParams();
		Logger.Verbose(this.getClass().getName(), "WindowManager.LayoutParams created");

		wmlp.copyFrom(window.getAttributes());
		Logger.Verbose(this.getClass().getName(), "Attributes copied");

		if(Build.VERSION.SDK_INT >= 18)
		{
			wmlp.rotationAnimation = WindowManager.LayoutParams.ROTATION_ANIMATION_JUMPCUT;
			Logger.Verbose(this.getClass().getName(), "Attribute for rotation set");
		}
		window.setAttributes(wmlp);
		Logger.Verbose(this.getClass().getName(), "Attributes Set");

		setImmersiveMode();	//if possible that is... get the best available

		setContentView(R.layout.activity_canvas);

		model = new CanvasModel();
		Logger.Verbose(this.getClass().getName(), "Model object created");
		view = (CanvasView)findViewById(R.id.viewCanvas);
		view.setModel(model);

		Logger.Verbose(this.getClass().getName(), "Ending onCreate(Bundle)");
	}
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) 
	{
		Logger.Verbose(this.getClass().getName(), "Starting the onWindowFocusChanged()");
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus) 
			{
			setImmersiveMode();
			}
		Logger.Verbose(this.getClass().getName(), "Ending the onWindowFocusChanged()");
	}
    	
    	private void setImmersiveMode()
	{
		Logger.Verbose(this.getClass().getName(), "Starting the setImmersiveMode()");
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

		Logger.Verbose(this.getClass().getName(), "Ending the setImmersiveMode()");
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
		//changeMode(event); 
		return true;
	}

}
