package inc.flide.touchboard.logging;

import android.util.Log;

public class Logger implements LoggingConstants
{
	public static String tag;
	
	public static void setTag(String className)
	{
		tag = LoggingConstants.Project_Name+className;
	}

	public static void Debug(String message)
	{
		 if(LoggingConstants.DEBUG == true)
			 Log.d(tag, message);
	}

	public static void Debug(String message, Throwable tr)
	{
		 if(LoggingConstants.DEBUG == true)
			 Log.d(tag, message, tr);
	}

	public static void Error(String message)
	{
		 if(LoggingConstants.ERROR == true)
			 Log.e(tag, message);
	}

	public static void Error(String message, Throwable tr)
	{
		 if(LoggingConstants.ERROR == true)
			 Log.e(tag, message, tr);
	}

	public static void Info(String message)
	{
		 if(LoggingConstants.INFO == true)
			 Log.i(tag, message);
	}

	public static void Info(String message, Throwable tr)
	{
		 if(LoggingConstants.INFO == true)
			 Log.i(tag, message, tr);
	}
	
	public static void Verbose(String message)
	{
		 if(LoggingConstants.VERBOSE == true)
			 Log.v(tag, message);
	}

	public static void Verbose(String message, Throwable tr)
	{
		 if(LoggingConstants.VERBOSE == true)
			 Log.v(tag, message, tr);
	}

	public static void Warn(String message)
	{
		 if(LoggingConstants.WARN == true)
			 Log.w(tag, message);
	}

	public static void Warn(Throwable tr)
	{
		 if(LoggingConstants.WARN == true)
			 Log.w(tag, tr);
	}

	public static void Warn(String message, Throwable tr)
	{
		 if(LoggingConstants.WARN == true)
			 Log.w(tag, message, tr);
	}

}
