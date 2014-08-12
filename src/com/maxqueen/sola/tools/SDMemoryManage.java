package com.maxqueen.sola.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class SDMemoryManage {

	private final SharedPreferences mPreferences;
	private static SDMemoryManage instance = null;

	public static SDMemoryManage getInstance(Context context) {
		if (instance == null)
			sdInit(context);
		return instance;
	}

	private static synchronized void sdInit(Context context) {
		if (instance == null)
			instance = new SDMemoryManage(context);
	}

	private SDMemoryManage(Context context) {
		mPreferences = PreferenceManager.getDefaultSharedPreferences(context);

	}

	public <T> void Put(String key, T obj) {
		Editor mEditor = mPreferences.edit();
		if (obj instanceof String)
			mEditor.putString(key, (String) obj);
		if (obj instanceof Boolean)
			mEditor.putBoolean(key, (Boolean) obj);
		if (obj instanceof Integer)
			mEditor.putInt(key, (Integer) obj);
		if (obj instanceof Float)
			mEditor.putFloat(key, (Float) obj);
		if (obj instanceof Long)
			mEditor.putLong(key, (Long) obj);
		mEditor.commit();
	}

	public SharedPreferences Get() {
		return mPreferences;
	}

	public String getString(String key) {
		return mPreferences.getString(key, "");
	}

	public boolean getBoolean(String key) {
		return mPreferences.getBoolean(key, false);
	}

	public int getInteger(String key) {
		return mPreferences.getInt(key, -1);
	}

	public float getFloat(String key) {
		return mPreferences.getFloat(key, 0.0f);
	}

	public long getLong(String key) {
		return mPreferences.getLong(key, 0);
	}

	public Map<String, ?> GetAll() {
		return mPreferences.getAll();
	}

	public String[] GetAll(String navigation) {
		List<String> list = new ArrayList<String>();
		for (Map.Entry<String, ?> entry : mPreferences.getAll().entrySet())
			if (entry.getKey().indexOf(navigation) != -1)
				list.add(entry.getValue().toString());
		if (list.isEmpty())
			return null;
		else {
			int size = list.size();
			String[] strArray = new String[size];
			System.arraycopy(list.toArray(), 0, strArray, 0, size);
			return strArray;
		}

	}

	public boolean contains(String key) {
		return mPreferences.contains(key);
	}

	public <T> T[] Get(String key, T... obj) {
		return null;
	}

	@SuppressWarnings("unchecked")
	public <T> T Get(String key, T obj) {
		if (obj instanceof String)
			return (T) mPreferences.getString(key, "Null");
		if (obj instanceof Boolean)
			return mPreferences.getBoolean(key, false) ? (T) Boolean.TRUE
					: (T) Boolean.FALSE;
		if (obj instanceof Integer)
			return (T) Integer.valueOf(mPreferences.getInt(key, -1));
		if (obj instanceof Float)
			return (T) Float.valueOf(mPreferences.getFloat(key, 0.0f));
		if (obj instanceof Long)
			return (T) Long.valueOf(mPreferences.getLong(key, 0));
		return null;
	}
}
