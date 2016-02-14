package com.example.jil.myproject_prototype2sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.view.View;

/**
 * Created by JIL on 01/02/16.
 */
public class SQLiteDbAdapter {

    public SQLiteDbAdapter(Context context) {
        jilDBHelper = new JilDBHelper(context);
    }

    JilDBHelper jilDBHelper;

    /**
     *This inserts the following parameters into the database
     * @param firstName
     * @param lastName
     * @param username
     * @param password
     * @param email
     * @return the id of the row affected
     */
    public long Insert(String firstName, String lastName, String email, String username, String password)
    {
        String user;
        long newRowId = 0;
        SQLiteDatabase db = jilDBHelper.getWritableDatabase();
        user = getLoginData(username, password);

        if (username.equals(user))
        {
            newRowId = 0;
        }
        else
        {
            // Gets the data repository in write mode
// Create a new map of values, where column names are the keys
            ContentValues contentValues = new ContentValues();
            contentValues.put(jilDBHelper.FIRST_NAME, firstName);
            contentValues.put(jilDBHelper.LAST_NAME, lastName);
            contentValues.put(jilDBHelper.EMAIL_ADDRESS, email);
            contentValues.put(jilDBHelper.USERNAME, username);
            contentValues.put(jilDBHelper.PASSWORD, password);

// Insert the new row, returning the primary key value of the new row
            newRowId = db.insert(
                    jilDBHelper.TABLE_NAME,
                    null,
                    contentValues);
        }
        return newRowId;
    }

    public String readDBData(String[] projection)
    {
        SQLiteDatabase db = jilDBHelper.getReadableDatabase();
        user newUser;
// Define a projection that specifies which columns from the database
// you will actually use after this query.
    /*    projection = {
                jilDBHelper.USER_ID,
                jilDBHelper.FIRST_NAME,
                jilDBHelper.USERNAME,
                jilDBHelper.PASSWORD
        };
        */

        Cursor cursor = db.query(
                jilDBHelper.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext())
        {
            int index1 =  cursor.getColumnIndex(jilDBHelper.USER_ID);
            long cid = cursor.getInt(index1);

            int index2 =  cursor.getColumnIndex(jilDBHelper.FIRST_NAME);
            String firstname = cursor.getString(index2);

            int index3 =  cursor.getColumnIndex(jilDBHelper.USERNAME);
            String username = cursor.getString(index3);

            int index4 =  cursor.getColumnIndex(jilDBHelper.PASSWORD);
            String password = cursor.getString(index4);

            int index5 =  cursor.getColumnIndex(jilDBHelper.EMAIL_ADDRESS);
            String email = cursor.getString(index5);


            buffer.append(cid + " " + firstname +" " + email +  " " + username + " " + password + "\n");
            newUser = new user(cid, firstname, username, password, email);
        }

        return buffer.toString();

    }

    public String getLoginData(String username, String password)
    {
        //SELECT username, password from testDB where names = "" AND password = "";
        SQLiteDatabase db = jilDBHelper.getReadableDatabase();

        String[] columns = {jilDBHelper.USERNAME};
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(jilDBHelper.TABLE_NAME, columns, jilDBHelper.USERNAME + " = ? AND " + jilDBHelper.PASSWORD + " = ?"  , selectionArgs, null, null, null);
        //db.query(jildbHelper.TABLE_NAME, columns, selection, selectionArgs, groupBy, having, orderBy);

        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext())
        {
            int index2 =  cursor.getColumnIndex(jilDBHelper.USERNAME);
            String UserName = cursor.getString(index2);

            buffer.append(UserName + "\n");

        }
        return buffer.toString();
    }

    public void DeleteInfoFromDB(String value)
    {
        SQLiteDatabase db = jilDBHelper.getWritableDatabase();

        // Specify arguments in placeholder order.
        String[] selectionArgs = { String.valueOf(value) };

        // Issue SQL statement.
        db.delete(jilDBHelper.TABLE_NAME,jilDBHelper.USERNAME + " = ? ", selectionArgs);
    }

    public void UpdateDB(String oldData, String NewData)
    {
        SQLiteDatabase db = jilDBHelper.getReadableDatabase();

        // New value for one column
        ContentValues contentValues = new ContentValues();
        contentValues.put(jilDBHelper.USERNAME, NewData);

        // Which row to update, based on the ID
        String selection = jilDBHelper.USERNAME + " = ?";
        String[] selectionArgs = { String.valueOf(oldData) };

        int count = db.update(
                jilDBHelper.TABLE_NAME,
                contentValues,
                selection,
                selectionArgs);
    }



    //This is the class in charge of Creating database and updating Database based on the version
    static class JilDBHelper extends SQLiteOpenHelper {
        // If you change the database schema, you must increment the database version.
        private static final String DATABASE_NAME = "JILDB";
        private static final int DATABASE_VERSION = 1;
        private static final String TABLE_NAME = "HealthDB";
        private static final String USER_ID = "_ID";
        private static final String FIRST_NAME = "first_name";
        private static final String LAST_NAME = "last_name";
        private static final String USERNAME = "username";
        private static final String EMAIL_ADDRESS = "email";
        private static final String PASSWORD = "password";
        private static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE "
                        + TABLE_NAME
                        + " ("
                        + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + FIRST_NAME + " VARCHAR(32), "
                        + LAST_NAME + " VARCHAR(32), "
                        + USERNAME + " VARCHAR(20), "
                        + EMAIL_ADDRESS + " VARCHAR(100), "
                        + LAST_NAME + " VARCHAR(32), "
                        + PASSWORD + " VARCHAR(32), "
                        + " );";

        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS "
                        + TABLE_NAME;

        private Context context;



        public JilDBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
            Message.messageToast(context,"Constructor called");
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try{
                db.execSQL(SQL_CREATE_ENTRIES);
                Message.messageToast(context, "onCreate called");
            }
            catch (SQLException e)
            {
                Message.messageToast(context, "" + e);
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            try{
                db.execSQL(SQL_DELETE_ENTRIES);
                onCreate(db);
                Message.messageToast(context, "onUpgrade called");
            }
            catch (SQLException e)
            {
                Message.messageToast(context,"" + e );
            }

        }
    }
}
