package com.example.qr_code.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
	// The class is definied to query and connect to the Data Base.
	/// <summary>
	/// The class consists of three attributes.
	/// </summary>
	
    String TAG = "DbHelper";
    SQLiteDatabase db;
    Context context;

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "qrcode";

    // Table Name
    public static final String TABLE_USER = "users";
    public static final String TABLE_ASSETS = "assets";

    // Table columns users
    public static final String _USER_ID = "_id";
    public static final String USER_NAME = "name";
    public static final String USER_USERNAME = "username";
    public static final String USER_PASSWORD = "password";
    public static final String USER_STATUS = "status";
    public static final String USER_REGISTER = "register";

    // Table columns assets
    public static final String _ASSETS_ID = "_id";
    public static final String ASSETS_NAME = "name";
    public static final String ASSETS_DESCRIPTION = "description";
    public static final String ASSETS_AMOUNT = "amount";
    public static final String ASSETS_LOCATION = "location";

    // Creating user table query
    private static final String CREATE_TABLE_USER =
            "CREATE TABLE IF NOT EXISTS "+TABLE_USER+" (ID integer primary key autoincrement, USERS_ID text, USERS_NAME text, USERS_USERNAME text, " +
            "USERS_PASSWORD text, USERS_STATUS text, USERS_REGISTER text);";

    // Creating assets table query
    private static final String CREATE_TABLE_ASSETS =
            "CREATE TABLE IF NOT EXISTS "+TABLE_ASSETS+" (ID integer primary key autoincrement, ASSETS_ID text, ASSETS_NAME text, ASSETS_DESCRIPTION text, " +
                    "ASSETS_AMOUNT text, ASSETS_LOCATION text);";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.e(TAG, "start db helper");
    }
	
	// onCreate() is a builtin method defined in 'appcompt' library.
	// This method is run for the first time when the DatabaseHelper class is called.
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        db = sqLiteDatabase;
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_ASSETS);
        Log.e(TAG, "Database has been created" );

    }
	
	
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
	
	// insertUsers() is a user defined method.
	// The function is responsible for inserting the user's data into the data base.
    public void insertUsers(User users){
        db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("USERS_ID", users.getId());
        contentValues.put("USERS_NAME", users.getName());
        contentValues.put("USERS_USERNAME", users.getUsername());
        contentValues.put("USERS_PASSWORD", users.getPassword());
        contentValues.put("USERS_STATUS", users.getStatus());
        contentValues.put("USERS_REGISTER", users.getDate());
        db.insert("users", null, contentValues);

    }

	// insertAssets() is a user defined method.
	// The function is responsible for inserting the user's assets into the data base.
    public void insertAssets(Assets assets) {
        db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("ASSETS_ID", assets.getId());
        contentValues.put("ASSETS_NAME", assets.getName());
        contentValues.put("ASSETS_DESCRIPTION", assets.getDescription());
        contentValues.put("ASSETS_AMOUNT", assets.getAmount());
        contentValues.put("ASSETS_LOCATION", assets.getLocation());
        db.insert("assets", null, contentValues);
        Log.e(TAG, "INSERT ASSETS" );
    }

	// updateAssets() is a user defined method.
	// The function is responsible for updating the user's assets into the data base.
    public void updateAssets(Assets assets){
        db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put("ASSETS_ID", assets.getId());
        values.put("ASSETS_NAME", assets.getName());
        values.put("ASSETS_DESCRIPTION", assets.getDescription());
        values.put("ASSETS_AMOUNT", assets.getAmount());
        values.put("ASSETS_LOCATION", assets.getLocation());

        db.execSQL("UPDATE "+TABLE_ASSETS+" SET ASSETS_NAME = "+"'"+assets.getName()+"', " +
                "ASSETS_DESCRIPTION = '"+assets.getDescription()+"',"+
                "ASSETS_AMOUNT = '"+assets.getAmount()+"',"+
                "ASSETS_LOCATION = '"+assets.getLocation()+"' "+
                "WHERE ASSETS_ID = "+"'"+assets.getId()+"'");
    }

	// updateUsers() is a user defined method.
	// The function is responsible for updating the user's data into the data base.
    public void updateUsers(User user){
        db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put("USERS_ID", user.getId());
        values.put("USERS_NAME", user.getName());
        values.put("USERS_USERNAME", user.getUsername());
        values.put("USERS_PASSWORD", user.getPassword());
        values.put("USERS_STATUS", user.getStatus());
        values.put("USERS_REGISTER", user.getDate());

        db.update(TABLE_USER, values, "USERS_ID="+user.getId(), null);
    }

	// getAssetsById() is a user defined method.
	// The function is responsible for  getting the user's assets as per id the data base.
    public Assets getAssetsById(String id) {
        Assets assets = new Assets();

        db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_ASSETS + " WHERE ASSETS_ID = ? ";
        Cursor cursor = db.rawQuery(query, new String[] { id });
        if (cursor.moveToFirst()) {
            assets.setId(cursor.getString(1));
            assets.setName(cursor.getString(2));
            assets.setDescription(cursor.getString(3));
            assets.setAmount(cursor.getString(4));
            assets.setLocation(cursor.getString(5));
        }

        return assets;
    }

	// getAssets() is a user defined method.
	// The function is responsible for getting all the asset's from the database.
    public ArrayList<Assets> getAssets(){
        ArrayList<Assets> asset = new ArrayList<Assets>();

        db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_ASSETS;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Assets assets = new Assets();
                assets.setId(cursor.getString(1));
                assets.setName(cursor.getString(2));
                assets.setDescription(cursor.getString(3));
                assets.setAmount(cursor.getString(4));
                assets.setLocation(cursor.getString(5));
                asset.add(assets);
            }
            while (cursor.moveToNext());
        }

        return asset;
    }

	// deleteAllAssets() is a user defined method.
	// The function is responsible for deleting all user's assets in the data base.
    public void deleteAllAssets(){
        db = this.getWritableDatabase();

        db.execSQL("DELETE FROM "+TABLE_ASSETS);
    }

	// deleteAssets() is a user defined method.
	// The function is responsible for deleting only one user assets in the data base.
    public void deleteAssets(String asset_id ){
        db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_ASSETS+" WHERE ASSETS_ID = '"+asset_id+"'");
    }
}