package com.previmet.synop.db;

import android.database.Cursor;
import android.database.CursorWrapper;

/**
 * Created by Vince on 20.12.2014.
 */
public class DbCursor extends CursorWrapper {
    public DbCursor(Cursor cursor) {
        super(cursor);
    }
}
