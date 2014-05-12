package inc.flide.touchboard;

import inc.flide.touchboard.logging.*;

import android.app.Activity;
import android.util.Log;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.Window;

public class CanvasActivity extends Activity implements LoggingConstants
{
	//Adding the Debug variables and functions
	private static final String CLASS_NAME = "CanvasActivity";

	void Verbose(String message)
	{
		 if(LoggingConstants.VERBOSE == true)
			 Log.v(LoggingConstants.Project_Name, CLASS_NAME + message);
			  
	}

	void Debug(String message)
	{
		 if(LoggingConstants.DEBUG == true)
			 Log.d(LoggingConstants.Project_Name, CLASS_NAME + message);
	}
	//Debuging segment ends
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		Verbose("Starting onCreate(Bundle)");
		super.onCreate(savedInstanceState);

		Window window = getWindow();
		WindowManager.LayoutParams wmlp = new WindowManager.LayoutParams();
		Verbose("WindowManager.LayoutParams created");
		wmlp.copyFrom(window.getAttributes());
		Verbose("Attributes copied");
		if(Build.VERSION.SDK_INT >= 18)
		{
			wmlp.rotationAnimation = WindowManager.LayoutParams.ROTATION_ANIMATION_JUMPCUT;
			Verbose("Attribute for rotation set");
		}
		window.setAttributes(wmlp);
		Verbose("Attributes Set");

		setImmersiveMode();	//if possible that is... get the best available

		setContentView(R.layout.activity_canvas);
		Verbose("Ending onCreate(Bundle)");
	}
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) 
	{
	super.onWindowFocusChanged(hasFocus);
	if (hasFocus) 
		{
		setImmersiveMode();
		}
	}
    	
    	private void setImmersiveMode()
	{
		Verbose("Starting the setImmersiveMode()");
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

		Verbose("Ending the setImmersiveMode()");
	}
}
