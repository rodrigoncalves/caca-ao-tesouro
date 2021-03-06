package personal.marriageproposal.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    private Context context;

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
        createDataBase();
    }

    public void createDataBase() {
        boolean dbExists = checkDataBase();
        if (!dbExists) {
            this.getReadableDatabase();
            try {
                Log.d("DB", "Inserting data...");
                copyDataBase();
            } catch (IOException e) {
                Log.e(this.getClass().toString(), "Copying error");
                throw new Error("Error copying database!");
            }
        }
    }

    /**
    * Check if the database already exist to avoid re-copying the file each time you open the a
    * @return true if it exists, false if it doesn't
    */
    private boolean checkDataBase() {
        String query = "select name from sqlite_master where name='hints'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        boolean ret = cursor.moveToFirst();
        cursor.close();
        return ret;
    }



    private int copyDataBase() throws IOException {
        int result = 0;
        InputStreamReader inputStreamReader = new InputStreamReader(this.context.getAssets().open("data.sql"));
        BufferedReader insertReader = new BufferedReader(inputStreamReader);

        // Iterate through lines (assuming each insert has its own line and there is no other stuff)

        String insertStmt = "";
        while (insertReader.ready()) {
            String line = insertReader.readLine();
            if (!line.contains(";")) {
                insertStmt = insertStmt.concat(line);
                continue;
            }

            insertStmt = insertStmt.concat(line);
            SQLiteDatabase db = this.getReadableDatabase();
            db.execSQL(insertStmt);
            result++;
            insertStmt = "";
        }

        insertReader.close();

        // returning number of inserted rows
        return result;
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
                Hint hint = new Hint(id, title, details, password);
                // Add hint to books
                hints.add(hint);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return hints;
    }

    public Hint getHintById(String id) {
        String query = "SELECT * FROM hints WHERE _id = '" + id + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Hint hint = null;
        if (cursor.moveToFirst()) {
            String title = cursor.getString(1);
            String details = cursor.getString(2);
            String password = cursor.getString(3);
            hint = new Hint(id, title, details, password);
        }

        cursor.close();

        return hint;
    }
}
