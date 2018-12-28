package PersonalHelper;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

public class personalProvider extends ContentProvider {
    private SQLiteOpenHelper helper;
    private SQLiteDatabase db;
    private static UriMatcher matcher;
    private static final String AUTHORITY = "com.SportAPP2.0.provider.poiprovider";
    public static final Uri CONTENT_URI = Uri.parse("content://"+AUTHORITY);

    static {

        matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(AUTHORITY, "user", 1);// 配置表
        matcher.addURI(AUTHORITY, "user/#", 2);// 匹配任何数字
        matcher.addURI(AUTHORITY, "user/*", 3);// 匹配任何文本

    }

    @Override
    public boolean onCreate() {
        helper = new personalHelper(getContext());
        db = helper.getWritableDatabase();
        Log.d("qf", "personalProvider--->onCreate()");
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        db.insert("personal", null, values);
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int code = matcher.match(uri);
        int result = 0;
        switch (code) {
            case 1:
                result = db.update("personal", values, selection, selectionArgs);
                break;
            case 2:
                result = db.update("personal", values, "_id=" + ContentUris.parseId(uri) + " AND " + selection,
                        selectionArgs);
                break;
            // 根据手动传参id来更新
            case 3:
                result = db.update("personal", values, selection, selectionArgs);
                break;
        }
        return result;
    }
}
