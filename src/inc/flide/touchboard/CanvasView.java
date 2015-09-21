package inc.flide.touchboard;

import inc.flide.touchboard.model.*;
import inc.flide.touchboard.logging.*;

import android.util.AttributeSet;
import android.content.Context;

import android.view.View;
import android.view.Display;
import android.graphics.Canvas;

//this will be the View of the MVC.
//I am not very convinced that CanvasView should be the View part of MVC model, but current
//experiment scenario dictates that I try this out.
//Primary Responsibility and the ONLY Responsibility this class should and will have, would be
//to Present the screen to the user.
//Strict No to state mantianance, handling user input or doing any data manipulation.
public class CanvasView extends View
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
		this.setKeepScreenOn(true);
	}

	@Override
	protected void onDraw(Canvas canvas) 
	{
		Logger.Verbose(this, "Starting onDraw()");
		if(canvasActivity == null){
			Logger.d(this, "activity is null!!!");
			return;
		}
		canvas.drawBitmap(canvasActivity.getModel().getBitmap(), 0, 0,canvasActivity.getTool().getPaint());
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

}
