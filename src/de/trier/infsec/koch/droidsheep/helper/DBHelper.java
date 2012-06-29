package de.trier.infsec.koch.droidsheep.helper;

import java.util.HashMap;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBHelper {

	private static SQLiteDatabase droidsheepDB = null;
	public static final String DROIDSHEEP_DBNAME = "droidsheep";

	public static final String CREATE_PREFERENCES = "CREATE TABLE IF NOT EXISTS DROIDSHEEP_PREFERENCES "
			+ "(id integer primary key autoincrement, " + "name  varchar(100)," + "value varchar(100));";

	public static final String CREATE_BLACKLIST = "CREATE TABLE IF NOT EXISTS DROIDSHEEP_BLACKLIST "
			+ "(id integer primary key autoincrement, " + "domain varchar(100));";

	public static void initDB(Context c) {
		DBHelper.droidsheepDB = c.openOrCreateDatabase(DROIDSHEEP_DBNAME, Context.MODE_PRIVATE, null);
		droidsheepDB.execSQL(CREATE_PREFERENCES);
		droidsheepDB.execSQL(CREATE_BLACKLIST);
	}

	public static boolean getGeneric(Context c) {
		initDB(c);
		Cursor cur = droidsheepDB.rawQuery("SELECT * FROM DROIDSHEEP_PREFERENCES WHERE name = 'generic';", new String[] {});
		if (cur.moveToNext()) {
			String s = cur.getString(cur.getColumnIndex("value"));
			cur.close();
			droidsheepDB.close();
			return Boolean.parseBoolean(s);
		} else {
			cur.close();
			droidsheepDB.close();
			return false;
		}
	}

	public static HashMap<String, Object> getBlacklist(Context c) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		initDB(c);
		Cursor cur = droidsheepDB.rawQuery("SELECT domain FROM DROIDSHEEP_BLACKLIST;", new String[] {});

		while (cur.moveToNext()) {
			String s = cur.getString(cur.getColumnIndex("domain"));
			map.put(s, null);
		}

		cur.close();
		droidsheepDB.close();
		return map;
	}

	public static void addBlacklistEntry(Context c, String name) {
		initDB(c);
		droidsheepDB.execSQL("INSERT INTO DROIDSHEEP_BLACKLIST (domain) VALUES (?);", new Object[] { name });
		droidsheepDB.close();
	}

	public static void setGeneric(Context c, boolean b) {
		initDB(c);
		Cursor cur = droidsheepDB.rawQuery("SELECT count(id) as count FROM DROIDSHEEP_PREFERENCES where name = 'generic';",
				new String[] {});
		cur.moveToFirst();
		int count = (int) cur.getLong(cur.getColumnIndex("count"));
		if (count == 0) {
			droidsheepDB.execSQL("INSERT INTO DROIDSHEEP_PREFERENCES (name, value) values ('generic', ?);",
					new String[] { Boolean.toString(b) });
		} else {
			droidsheepDB.execSQL("UPDATE DROIDSHEEP_PREFERENCES SET value=? WHERE name='generic';",
					new String[] { Boolean.toString(b) });
		}
		droidsheepDB.close();
	}

	public static void clearBlacklist(Context c) {
		initDB(c);
		droidsheepDB.execSQL("DELETE FROM DROIDSHEEP_BLACKLIST;", new Object[] {});
		droidsheepDB.close();
	}

	public static long getLastDonateMessage(Context c) {
		try {
			initDB(c);
			Cursor cur = droidsheepDB.rawQuery("SELECT value FROM DROIDSHEEP_PREFERENCES where name = 'donate';",
					new String[] {});
			cur.moveToFirst();
			long datetime = cur.getLong(cur.getColumnIndex("value"));
			return datetime;
		} catch (Exception e) {
			Log.d(Constants.APPLICATION_TAG, "Could not load last donate datetime: " + e.getLocalizedMessage());
		} finally {
			droidsheepDB.close();
		}
		return 0L;
	}

	public static void setLastDonateMessage(Context c, long date) {
		initDB(c);
		Cursor cur = droidsheepDB.rawQuery("SELECT count(id) as count FROM DROIDSHEEP_PREFERENCES where name = 'donate';", new String[] {});
		cur.moveToFirst();
		int count = (int) cur.getLong(cur.getColumnIndex("count"));
		if (count == 0) {
			droidsheepDB.execSQL("INSERT INTO DROIDSHEEP_PREFERENCES (name, value) values ('donate', ?);",
					new String[] { Long.toString(date) });
		} else {
			droidsheepDB.execSQL("UPDATE DROIDSHEEP_PREFERENCES SET value=? WHERE name='donate';",
					new String[] { Long.toString(date) });
		}
		droidsheepDB.close();
	}
	
	public static void setLastUpdateCheck(Context c, long date) {
		initDB(c);
		Cursor cur = droidsheepDB.rawQuery("SELECT count(id) as count FROM DROIDSHEEP_PREFERENCES where name = 'update';", new String[] {});
		cur.moveToFirst();
		int count = (int) cur.getLong(cur.getColumnIndex("count"));
		if (count == 0) {
			droidsheepDB.execSQL("INSERT INTO DROIDSHEEP_PREFERENCES (name, value) values ('update', ?);",
					new String[] { Long.toString(date) });
		} else {
			droidsheepDB.execSQL("UPDATE DROIDSHEEP_PREFERENCES SET value=? WHERE name='update';",
					new String[] { Long.toString(date) });
		}
		droidsheepDB.close();
	}
	
	public static long getLastUpdateMessage(Context c) {
		try {
			initDB(c);
			Cursor cur = droidsheepDB.rawQuery("SELECT value FROM DROIDSHEEP_PREFERENCES where name = 'update';",
					new String[] {});
			cur.moveToFirst();
			long datetime = cur.getLong(cur.getColumnIndex("value"));
			return datetime;
		} catch (Exception e) {
			Log.d(Constants.APPLICATION_TAG, "Could not load last update datetime: " + e.getLocalizedMessage());
		} finally {
			droidsheepDB.close();
		}
		return 0L;
	}
}
