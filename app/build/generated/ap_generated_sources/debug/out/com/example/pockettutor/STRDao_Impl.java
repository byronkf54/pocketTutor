package com.example.pockettutor;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class STRDao_Impl implements STRDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<STRDB> __insertionAdapterOfSTRDB;

  private final EntityDeletionOrUpdateAdapter<STRDB> __deletionAdapterOfSTRDB;

  private final EntityDeletionOrUpdateAdapter<STRDB> __updateAdapterOfSTRDB;

  public STRDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSTRDB = new EntityInsertionAdapter<STRDB>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `SubTopReq` (`ID`,`SUBJECT`,`TOPIC`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, STRDB value) {
        stmt.bindLong(1, value.getID());
        if (value.getSUBJECT() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getSUBJECT());
        }
        if (value.getTOPIC() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getTOPIC());
        }
      }
    };
    this.__deletionAdapterOfSTRDB = new EntityDeletionOrUpdateAdapter<STRDB>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `SubTopReq` WHERE `ID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, STRDB value) {
        stmt.bindLong(1, value.getID());
      }
    };
    this.__updateAdapterOfSTRDB = new EntityDeletionOrUpdateAdapter<STRDB>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `SubTopReq` SET `ID` = ?,`SUBJECT` = ?,`TOPIC` = ? WHERE `ID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, STRDB value) {
        stmt.bindLong(1, value.getID());
        if (value.getSUBJECT() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getSUBJECT());
        }
        if (value.getTOPIC() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getTOPIC());
        }
        stmt.bindLong(4, value.getID());
      }
    };
  }

  @Override
  public void insert(final STRDB db) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfSTRDB.insert(db);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final STRDB db) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfSTRDB.handle(db);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final STRDB db) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfSTRDB.handle(db);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<STRDB>> getAllNodes() {
    final String _sql = "SELECT * FROM SubTopReq ORDER BY ID DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"SubTopReq"}, false, new Callable<List<STRDB>>() {
      @Override
      public List<STRDB> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfID = CursorUtil.getColumnIndexOrThrow(_cursor, "ID");
          final int _cursorIndexOfSUBJECT = CursorUtil.getColumnIndexOrThrow(_cursor, "SUBJECT");
          final int _cursorIndexOfTOPIC = CursorUtil.getColumnIndexOrThrow(_cursor, "TOPIC");
          final List<STRDB> _result = new ArrayList<STRDB>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final STRDB _item;
            final String _tmpSUBJECT;
            _tmpSUBJECT = _cursor.getString(_cursorIndexOfSUBJECT);
            final String _tmpTOPIC;
            _tmpTOPIC = _cursor.getString(_cursorIndexOfTOPIC);
            _item = new STRDB(_tmpSUBJECT,_tmpTOPIC);
            final int _tmpID;
            _tmpID = _cursor.getInt(_cursorIndexOfID);
            _item.setID(_tmpID);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }
}
