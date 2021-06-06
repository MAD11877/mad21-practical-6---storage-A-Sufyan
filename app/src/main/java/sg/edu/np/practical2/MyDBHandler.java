package sg.edu.np.practical2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "userDB.db";
        public static final String TABLE_USERS = "Users";
        public static final String COLUMN_NAME = "Name";
        public static final String COLUMN_DESCRIPTION = "Description";
        public static final String COLUMN_ID = "ID";
        public static final String COLUMN_FOLLOWED = "Followed";

        public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
            super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS +
                "(" + COLUMN_NAME + " TEXT," + COLUMN_DESCRIPTION + " TEXT," + COLUMN_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_FOLLOWED + " INTEGER" + ")";
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public void addUser(User user){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_DESCRIPTION, user.getDescription());
        values.put(COLUMN_ID, user.getId());
        values.put(COLUMN_FOLLOWED, user.getFollowed());
        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    public User findUser(String username){
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE "
                + COLUMN_NAME + " = \"" + username + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        User user = new User();
        if (cursor.moveToFirst()){
            user.setName(cursor.getString(0));
            user.setDescription(cursor.getString(1));
            user.setId(Integer.parseInt(cursor.getString(2)));
            user.setFollowed(Boolean.parseBoolean(cursor.getString(3)));
            cursor.close();
        }
        else{
            user = null;
        }

        db.close();
        return user;
    }

    public boolean deleteUser(String username){
        boolean result = false;
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE "
                + COLUMN_NAME + " = \"" + username + "\"";
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        User user = new User();

        if (cursor.moveToFirst()){
            user.setId(Integer.parseInt(cursor.getString(2)));
            db.delete(TABLE_USERS, COLUMN_ID + " = ?",
                    new String[] { String.valueOf(user.getId())});
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public ArrayList<User> getUser(){
        ArrayList<User> uList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            User userData = new User();
            userData.setName(cursor.getString(0));
            userData.setDescription(cursor.getString(1));
            //userData.setId(Integer.parseInt(cursor.getString(2)));
            userData.setId(cursor.getInt(2));
            userData.setFollowed(Boolean.parseBoolean((cursor.getString(3))));

            uList.add(userData);
        }
        cursor.close();
        db.close();
        return uList;
    }

    public void updateUser(User user){
        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();

        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_DESCRIPTION, user.getDescription());
        values.put(COLUMN_ID, user.getId());
        values.put(COLUMN_FOLLOWED, user.getFollowed());

        db.update(TABLE_USERS, values, COLUMN_ID + " =?",
                new String[] {String.valueOf(user.getId())});
    }
}
