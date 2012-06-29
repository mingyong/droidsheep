package de.trier.infsec.koch.droidsheep.helper;

import android.content.Context;
import android.content.Intent;
import de.trier.infsec.koch.droidsheep.auth.Auth;
import de.trier.infsec.koch.droidsheep.objects.CookieWrapper;

public class MailHelper {
	
	public static void sendAuthByMail(Context c, Auth a) {
		StringBuffer sb = new StringBuffer();
		for (CookieWrapper cw : a.getCookies()) {
			sb.append("[Cookie: \n");
			sb.append("domain: " + cw.getCookie().getDomain() + "\n");
			sb.append("path: " + cw.getCookie().getPath() + "\n");
			sb.append(cw.getCookie().getName());
			sb.append("=");
			sb.append(cw.getCookie().getValue());
			sb.append(";]\n");
		}
				
	    final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
	    emailIntent .setType("plain/text");
	    emailIntent .putExtra(android.content.Intent.EXTRA_SUBJECT, "DROIDSHEEP Cookie export");
	    emailIntent .putExtra(android.content.Intent.EXTRA_TEXT, sb.toString());
	    c.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
	}

	public static void sendStringByMail(Context c, String string) {
	    final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
	    emailIntent .setType("plain/text");
	    emailIntent .putExtra(android.content.Intent.EXTRA_SUBJECT, "DROIDSHEEP DEBUG INFORMATION");
	    emailIntent .putExtra(android.content.Intent.EXTRA_TEXT, string);
	    c.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
	}

}
