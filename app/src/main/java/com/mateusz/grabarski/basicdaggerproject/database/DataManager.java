package com.mateusz.grabarski.basicdaggerproject.database;

import android.content.Context;
import android.content.res.Resources;

import com.mateusz.grabarski.basicdaggerproject.base.qualifiers.ApplicationContext;
import com.mateusz.grabarski.basicdaggerproject.database.models.User;
import com.mateusz.grabarski.basicdaggerproject.utils.SharedPrefsHelper;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Mateusz Grabarski on 08.02.2018.
 */
@Singleton
public class DataManager {

    private Context mContext;
    private DatabaseHelper mDbHelper;
    private SharedPrefsHelper mSharedPrefsHelper;

    @Inject
    public DataManager(@ApplicationContext Context context,
                       DatabaseHelper dbHelper,
                       SharedPrefsHelper sharedPrefsHelper) {
        mContext = context;
        mDbHelper = dbHelper;
        mSharedPrefsHelper = sharedPrefsHelper;
    }

    public void saveAccessToken(String accessToken) {
        mSharedPrefsHelper.put(SharedPrefsHelper.PREF_KEY_ACCESS_TOKEN, accessToken);
    }

    public String getAccessToken() {
        return mSharedPrefsHelper.get(SharedPrefsHelper.PREF_KEY_ACCESS_TOKEN, null);
    }

    public Long createUser(User user) throws Exception {
        return mDbHelper.insertUser(user);
    }

    public User getUser(Long userId) throws Resources.NotFoundException, NullPointerException {
        return mDbHelper.getUser(userId);
    }
}
