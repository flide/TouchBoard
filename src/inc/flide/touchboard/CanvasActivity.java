package inc.flide.touchboard;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class CanvasActivity extends Activity {
	
	private View mDecorView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);

		mDecorView = getWindow().getDecorView();
		setImmersiveMode();

		setContentView(R.layout.activity_canvas);
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
	    mDecorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
		    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
		    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
		    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
		    | View.SYSTEM_UI_FLAG_FULLSCREEN
		    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
	}
}
