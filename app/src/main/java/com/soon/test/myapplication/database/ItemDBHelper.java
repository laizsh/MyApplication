package com.soon.test.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

public class ItemDBHelper extends SQLiteOpenHelper{
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "soontest.db";

    private static final String SQL_CREATE_ITEMS = "CREATE TABLE " + Item.TABLE_NAME + " (" +
            Item.COLUMN_NAME_STR1 + " TEXT UNIQUE," +
            Item.COLUMN_NAME_STR2 + " TEXT," +
            Item.COLUMN_NAME_STR3 + " TEXT," +
            Item.COLUMN_NAME_STR4 + " TEXT," +
            Item.COLUMN_NAME_STR5 + " TEXT," +
            Item.COLUMN_NAME_STR6 + " TEXT," +
            Item.COLUMN_NAME_STR7 + " TEXT," +
            Item.COLUMN_NAME_INT1 + " INTEGER," +
            Item.COLUMN_NAME_INT2 + " INTEGER," +
            Item.COLUMN_NAME_BOOLEAN1 + " INTEGER)";

    private static final String SQL_DELETE_ITEMS =
            "DROP TABLE IF EXISTS " + Item.TABLE_NAME;

    final String TAG = "ItemDBHelper";
    public ItemDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "ItemDataBase onCreate");
        db.execSQL(SQL_CREATE_ITEMS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.i(TAG, "ItemDataBase onUpgrade");
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ITEMS);
        onCreate(db);
    }

    public void clearItems(){
        getWritableDatabase().delete(Item.TABLE_NAME,null,null);
    }

    public Item readItem(String str1){
        SQLiteDatabase db = getReadableDatabase();
        // Filter results
        String selection = Item.COLUMN_NAME_STR1 + " = ?";
        String[] selectionArgs = { str1 };

        Cursor cursor = db.query(
                Item.TABLE_NAME,   // The table to query
                null,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        if(cursor != null){
            int indexStr1 = cursor.getColumnIndex(Item.COLUMN_NAME_STR1);
            int indexStr2 = cursor.getColumnIndex(Item.COLUMN_NAME_STR2);
            int indexStr3 = cursor.getColumnIndex(Item.COLUMN_NAME_STR3);
            int indexStr4 = cursor.getColumnIndex(Item.COLUMN_NAME_STR4);
            int indexStr5 = cursor.getColumnIndex(Item.COLUMN_NAME_STR5);
            int indexStr6 = cursor.getColumnIndex(Item.COLUMN_NAME_STR6);
            int indexStr7 = cursor.getColumnIndex(Item.COLUMN_NAME_STR7);
            int indexInt1 = cursor.getColumnIndex(Item.COLUMN_NAME_INT1);
            int indexInt2 = cursor.getColumnIndex(Item.COLUMN_NAME_INT2);
            int indexBoolean1 = cursor.getColumnIndex(Item.COLUMN_NAME_BOOLEAN1);

            while(cursor.moveToNext()){
                Item item = new Item();
                item.str1 = cursor.getString(indexStr1);
                item.str2 = cursor.getString(indexStr2);
                item.str3 = cursor.getString(indexStr3);
                item.str4 = cursor.getString(indexStr4);
                item.str5 = cursor.getString(indexStr5);
                item.str6 = cursor.getString(indexStr6);
                item.str7 = cursor.getString(indexStr7);
                item.int1 = cursor.getInt(indexInt1);
                item.int2 = cursor.getInt(indexInt2);
                item.boolean1 = cursor.getInt(indexBoolean1) == 1;

                cursor.close();

                return item;
            }
        }

        cursor.close();

        return null;
    }

    public long saveObj(Item item){
        SQLiteDatabase db = getWritableDatabase();
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(Item.COLUMN_NAME_STR1, item.str1);
        values.put(Item.COLUMN_NAME_STR2, item.str2);
        values.put(Item.COLUMN_NAME_STR3, item.str3);
        values.put(Item.COLUMN_NAME_STR4, item.str4);
        values.put(Item.COLUMN_NAME_STR5, item.str5);
        values.put(Item.COLUMN_NAME_STR6, item.str6);
        values.put(Item.COLUMN_NAME_STR7, item.str7);
        values.put(Item.COLUMN_NAME_INT1, item.int1);
        values.put(Item.COLUMN_NAME_INT2, item.int2);
        values.put(Item.COLUMN_NAME_BOOLEAN1, item.boolean1?1:0);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.replace(Item.TABLE_NAME,null,values);
//        System.out.println("save Obj by db return =" + newRowId);
        return newRowId;
    }

    public List<Item> readItems(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(
                Item.TABLE_NAME,   // The table to query
                null,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        List<Item> items = new LinkedList<>();
        if(cursor != null){
            int indexStr1 = cursor.getColumnIndex(Item.COLUMN_NAME_STR1);
            int indexStr2 = cursor.getColumnIndex(Item.COLUMN_NAME_STR2);
            int indexStr3 = cursor.getColumnIndex(Item.COLUMN_NAME_STR3);
            int indexStr4 = cursor.getColumnIndex(Item.COLUMN_NAME_STR4);
            int indexStr5 = cursor.getColumnIndex(Item.COLUMN_NAME_STR5);
            int indexStr6 = cursor.getColumnIndex(Item.COLUMN_NAME_STR6);
            int indexStr7 = cursor.getColumnIndex(Item.COLUMN_NAME_STR7);
            int indexInt1 = cursor.getColumnIndex(Item.COLUMN_NAME_INT1);
            int indexInt2 = cursor.getColumnIndex(Item.COLUMN_NAME_INT2);
            int indexBoolean1 = cursor.getColumnIndex(Item.COLUMN_NAME_BOOLEAN1);

            while(cursor.moveToNext()){
                Item item = new Item();
                item.str1 = cursor.getString(indexStr1);
                item.str2 = cursor.getString(indexStr2);
                item.str3 = cursor.getString(indexStr3);
                item.str4 = cursor.getString(indexStr4);
                item.str5 = cursor.getString(indexStr5);
                item.str6 = cursor.getString(indexStr6);
                item.str7 = cursor.getString(indexStr7);
                item.int1 = cursor.getInt(indexInt1);
                item.int2 = cursor.getInt(indexInt2);
                item.boolean1 = cursor.getInt(indexBoolean1) == 1;

                items.add(item);
            }
        }

        cursor.close();

        return items;
    }

    public void saveObjs(List<Item> items){
        getWritableDatabase().beginTransaction();
        for(Item item : items){
            saveObj(item);
        }
        getWritableDatabase().setTransactionSuccessful();
        getWritableDatabase().endTransaction();
    }
}
