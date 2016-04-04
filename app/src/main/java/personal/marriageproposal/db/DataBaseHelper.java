package personal.marriageproposal.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

import personal.marriageproposal.dummy.DummyContent.Hint;

/**
 * Created by rodrigo on 04/04/16.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    //The Android's default system path of your application database.
    private static String DB_PATH;
    private static String DB_NAME = "treasurehunt.sqlite3";
    private SQLiteDatabase myDataBase;
    private final Context context;

    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     * @param context
     */

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;

        String packageName = context.getPackageName();
        DB_PATH = String.format("//data//data//%s//databases//", packageName);
    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase(){
        SQLiteDatabase checkDB = null;

        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        } catch(SQLiteException e) {
            //database does't exist yet.
            Log.e(this.getClass().toString(), "Error while checking db");
        }

        if (checkDB != null) {
            checkDB.close();
        }

        return checkDB != null;
    }

    public SQLiteDatabase openDataBase() throws SQLException {
        //Open the database
        String path = DB_PATH + DB_NAME;
        if (myDataBase == null) {
            myDataBase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
        }

        return myDataBase;
    }

    @Override
    public synchronized void close() {

        if (myDataBase != null)
            myDataBase.close();

        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    // Add your public helper methods to access and get title from the database.
    // You could return cursors by doing "return myDataBase.query(....)" so it'd be easy
    // to you to create adapters for your views.
    public List<Hint> getAllHints() {
        List<Hint> hints = new LinkedList<>();

        // 1. build the query
        String query = "SELECT * FROM hints";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build hint and add it to list
        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(0);
                String title = cursor.getString(1);
                String details = cursor.getString(2);
                String password = cursor.getString(3);
                int status = cursor.getInt(4);
                Hint hint = new Hint(id, title, details, password, status);
                // Add hint to books
                hints.add(hint);
            } while (cursor.moveToNext());
        }

        return hints;
    }

}