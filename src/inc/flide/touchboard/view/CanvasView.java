package inc.flide.touchboard.view;

import inc.flide.touchboard.CanvasActivity;
import inc.flide.touchboard.R;
import inc.flide.touchboard.logging.*;
import android.util.AttributeSet;
import android.content.Context;

import android.view.View;
import android.view.Display;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Bitmap;
import android.graphics.Matrix;

//this will be the View of the MVC.
//The ONLY Responsibility this class should and will have, would be to Present the screen to the user.
//Strict NO to state maintenance, handling user input or doing any data manipulation.
//Whenever the Model indicates to the View that it has changed, it is View's job to update itself.
public class CanvasView extends View implements Observer
{
	
	private static int currentDisplayOrientation;
	private CanvasActivity canvasActivity;

	public CanvasView(Context context) 
	{
		super(context);
		Logger.v(this,"CanvasView(Context) Started");
		intializeView();
		Logger.v(this,"CanvasView(Context) Ended");
	}
	
	public CanvasView(Context context, AttributeSet attrs) 
	{
		super(context, attrs);
		Logger.v(this,"CanvasView(Context, AtributeSet) Started");
		intializeView();
		Logger.Verbose(this,"CanvasView(Context, AtributeSet) Ended");
	}
	
	public CanvasView(Context context, AttributeSet attrs, int defStyleAttr) 
	{
		super(context, attrs, defStyleAttr);
		Logger.Verbose(this,"CanvasView(Context, AtributeSet, int) Started");
		intializeView();
		Logger.Verbose(this,"CanvasView(Context, AtributeSet, int) Ended");
	}

	private void intializeView(){
		this.canvasActivity = (CanvasActivity)getContext();
		this.setBackgroundResource(R.drawable.transparent);
		this.setKeepScreenOn(true);
	}

	@Override
	protected void onDraw(Canvas canvas) 
	{
		Logger.Verbose(this, "Starting onDraw()");
		canvas.drawBitmap(canvasActivity.getCurrentDrawableBitmap(), new Matrix(),null);
		Logger.Verbose(this, "Ending onDraw()");
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) 
	{
		Logger.Verbose(this,"onSizeChanged(w="+w+", h="+h+", oldw="+oldw+", oldh="+oldh+") Started");
		super.onSizeChanged(w, h, oldw, oldh);

		CanvasActivity activity = (CanvasActivity)getContext();
		Display display = activity.getWindowManager().getDefaultDisplay();

		if(oldw==0 && oldh==0)
		{
			//Initialization of the application and this block will run only once.
			Logger.Verbose(this,"Application Initialized");
			activity.intializeBitmap(w, h);
			currentDisplayOrientation = display.getRotation();
		}
		else if( currentDisplayOrientation != display.getRotation())
		{
			Logger.Verbose(this,"Screen was reoriented");
			int previousDisplayOrientation = currentDisplayOrientation;
			currentDisplayOrientation = display.getRotation();
			int rotateBy = (currentDisplayOrientation - previousDisplayOrientation) * -1;
			canvasActivity.reorientScreen(oldw, oldh, rotateBy);
		}
		
		Logger.Verbose(this,"onSizeChanged(int,int,int,int) Ended");
	}

	@Override
	public void subjectUpdated() {
		this.invalidate();
	}

}
