package com.example.leo.mypracticemenu;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

/**
 * Created by Leo on 2016/6/12.
 */
public class Pref extends PreferenceActivity {
    public static final String PREF = "PROFILE";
    public static final String PREF_NAME = "PROFILE_NAME";
    public static final String PREF_PHONE = "PROFILE_PHONE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref);
    }

    public static String getName(Context context) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        return pref.getString(PREF_NAME, "");
    }

    public static String getPhone(Context context) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        return pref.getString(PREF_PHONE, "");
    }

    public static void setName(Context context, String name) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        pref.edit().putString(PREF_NAME, name).commit();
    }

    public static void setPhone(Context context, String phone) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        pref.edit().putString(PREF_PHONE, phone).commit();
    }

}
