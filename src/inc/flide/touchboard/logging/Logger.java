package inc.flide.touchboard.logging;

import android.util.Log;

public class Logger implements LoggingConstants
{
	public static void Verbose(String className, String message)
	{
		 if(LoggingConstants.VERBOSE == true)
			 Log.v(LoggingConstants.Project_Name, className + " : " + message);
			  
	}

	public static void Debug(String className, String message)
	{
		 if(LoggingConstants.DEBUG == true)
			 Log.d(LoggingConstants.Project_Name, className + " : " + message);
	}
}
