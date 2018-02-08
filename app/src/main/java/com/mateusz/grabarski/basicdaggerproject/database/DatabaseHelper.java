package com.mateusz.grabarski.basicdaggerproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mateusz.grabarski.basicdaggerproject.base.qualifiers.ApplicationContext;
import com.mateusz.grabarski.basicdaggerproject.base.qualifiers.DatabaseInfo;
import com.mateusz.grabarski.basicdaggerproject.database.models.User;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Mateusz Grabarski on 08.02.2018.
 */
@Singleton
public class DatabaseHelper extends SQLiteOpenHelper {

    @Inject
    public DatabaseHelper(@ApplicationContext Context context,
                          @DatabaseInfo String dbName,
                          @DatabaseInfo Integer version) {
        super(context, dbName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        tableCreateStatements(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseTables.USER_TABLE_NAME);
        onCreate(db);
    }

    private void tableCreateStatements(SQLiteDatabase db) {
        try {
            db.execSQL(
                    "CREATE TABLE IF NOT EXISTS "
                            + DatabaseTables.USER_TABLE_NAME + "("
                            + DatabaseTables.USER_COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + DatabaseTables.USER_COLUMN_USER_NAME + " VARCHAR(20), "
                            + DatabaseTables.USER_COLUMN_USER_ADDRESS + " VARCHAR(50), "
                            + DatabaseTables.USER_COLUMN_USER_CREATED_AT + " VARCHAR(10) DEFAULT " + getCurrentTimeStamp() + ", "
                            + DatabaseTables.USER_COLUMN_USER_UPDATED_AT + " VARCHAR(10) DEFAULT " + getCurrentTimeStamp() + ")"
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected User getUser(Long userId) throws Resources.NotFoundException, NullPointerException {
        Cursor cursor = null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            cursor = db.rawQuery(
                    "SELECT * FROM "
                            + DatabaseTables.USER_TABLE_NAME
                            + " WHERE "
                            + DatabaseTables.USER_COLUMN_USER_ID
                            + " = ? ",
                    new String[]{userId + ""});

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                User user = new User();
                user.setId(cursor.getLong(cursor.getColumnIndex(DatabaseTables.USER_COLUMN_USER_ID)));
                user.setName(cursor.getString(cursor.getColumnIndex(DatabaseTables.USER_COLUMN_USER_NAME)));
                user.setAddress(cursor.getString(cursor.getColumnIndex(DatabaseTables.USER_COLUMN_USER_ADDRESS)));
                user.setCreatedAt(cursor.getString(cursor.getColumnIndex(DatabaseTables.USER_COLUMN_USER_CREATED_AT)));
                user.setUpdatedAt(cursor.getString(cursor.getColumnIndex(DatabaseTables.USER_COLUMN_USER_UPDATED_AT)));
                return user;
            } else {
                throw new Resources.NotFoundException("User with id " + userId + " does not exists");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (cursor != null)
                cursor.close();
        }
    }

    protected Long insertUser(User user) throws Exception {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(DatabaseTables.USER_COLUMN_USER_NAME, user.getName());
            contentValues.put(DatabaseTables.USER_COLUMN_USER_ADDRESS, user.getAddress());
            return db.insert(DatabaseTables.USER_TABLE_NAME, null, contentValues);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private String getCurrentTimeStamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }
}
