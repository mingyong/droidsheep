package de.trier.infsec.koch.droidsheep.helper;

public interface Constants {

	public static final int 	MENU_WIFILIST_ID 			= 0;
	public static final int 	MENU_CLEAR_SESSIONLIST_ID 	= 1;
	public static final int 	MENU_DEBUG_ID 				= 2;
	public static final int 	MENU_GENERIC 				= 3;
	public static final int 	MENU_CLEAR_BLACKLIST_ID 	= 4;
	public static final int 	MENU_HELP_FORUM			 	= 5;
	public static final int 	MENU_DONATE				 	= 6;
	public static final int 	NOTIFICATION_ID 			= 4711;
	
	public static final String 	BUNDLE_KEY_TYPE 			= "TYPE";
	public static final String 	BUNDLE_TYPE_WIFICHANGE 		= "WIFICHANGE";
	public static final String 	BUNDLE_TYPE_START 			= "START";
	public static final String 	BUNDLE_TYPE_STOP	 		= "STOP";
	public static final String 	BUNDLE_TYPE_NEWAUTH 		= "NEWAUTH";
	public static final String 	BUNDLE_TYPE_LOADAUTH 		= "LOADAUTH";
	public static final String 	BUNDLE_KEY_ID 				= "ID";
	public static final String 	BUNDLE_KEY_MOBILE 			= "MOBILE";
	public static final String 	BUNDLE_KEY_AUTH 			= "AUTH";
	public static final String 	BUNDLE_KEY_NOROOT 			= "NOROOT";
	
	public static final boolean DEBUG 						= false;
	
	public static final String 	CLEANUP_COMMAND_DROIDSHEEP 	= "killall droidsheep\n";
	public static final String 	CLEANUP_COMMAND_ARPSPOOF   	= "killall arpspoof\n";
	
	public static final String 	APPLICATION_TAG 			= "DROIDSHEEP";
	
	public static final int 	ID_MOBILE 					= 1;
	public static final int 	ID_NORMAL 					= 2;
	public static final int 	ID_REMOVEFROMLIST 			= 3;
	public static final int 	ID_BLACKLIST 				= 4;
	public static final int 	ID_SAVE 					= 5;
	public static final int 	ID_DELETE 					= 6;
	public static final int 	ID_EXPORT					= 7;

}
