package com.example.pockettutor;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class STRRepository {
    private STRDao strDao;
    private LiveData<List<STRDB>> allData;

    public STRRepository(Application application) {
        DB db = DB.getInstance(application);
        strDao = db.strDao();
        allData = strDao.getAllNodes();
    }

    public void insert(STRDB strdb) {
        new InsertData(strDao).execute(strdb);
    }

    public void update(STRDB strdb) {
        new UpdateData(strDao).execute(strdb);
    }

    public void delete(STRDB strdb) {
        new DeleteData(strDao).execute(strdb);
    }

    public LiveData<List<STRDB>> getAllData() {
        return allData;
    }

    private static class InsertData extends AsyncTask<STRDB, Void, Void> {
        private STRDao strDao;

        private InsertData(STRDao strDao) {
            this.strDao = strDao;
        }

        @Override
        protected Void doInBackground(STRDB... strdbs) {
            strDao.insert(strdbs[0]);
            return null;
        }
    }

    private static class UpdateData extends AsyncTask<STRDB, Void, Void> {
        private STRDao strDao;

        private UpdateData(STRDao strDao) {
            this.strDao = strDao;
        }

        @Override
        protected Void doInBackground(STRDB... strdbs) {
            strDao.update(strdbs[0]);
            return null;
        }
    }

    private static class DeleteData extends AsyncTask<STRDB, Void, Void> {
        private STRDao strDao;

        private DeleteData(STRDao strDao) {
            this.strDao = strDao;
        }

        @Override
        protected Void doInBackground(STRDB... strdbs) {
            strDao.delete(strdbs[0]);
            return null;
        }
    }
}
