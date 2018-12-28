package PersonalHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class personalHelper extends SQLiteOpenHelper {

    public personalHelper(Context context) {
        super(context, "personal.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建一个表
        db.execSQL("CREATE TABLE IF NOT EXISTS  personal (id integer primary key autoincrement ," +
                "userName text not null ," +
                "trainLevel text not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
