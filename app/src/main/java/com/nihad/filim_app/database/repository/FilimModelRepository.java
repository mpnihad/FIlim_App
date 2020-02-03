package com.nihad.filim_app.database.repository;


import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.room.Room;

import com.nihad.filim_app.database.db.FilimDatabase;
import com.nihad.filim_app.model.FilimModel;

import java.util.List;

public class FilimModelRepository {

    private String DB_NAME = "filim_db";

    private FilimDatabase filimDatabase;
    public FilimModelRepository(Context context) {
        filimDatabase = Room.databaseBuilder(context, FilimDatabase.class, DB_NAME).build();
    }

    public void insertTask(String title,
                           String description) {

      //  insertTask(title, description, false, null);
    }

    public void insertTask1(FilimModel filim_db_Model) {


//        note.setTitle(title);
//        note.setDescription(description);
//        note.setCreatedAt(AppUtils.getCurrentDateTime());
//        note.setModifiedAt(AppUtils.getCurrentDateTime());
//        note.setEncrypt(encrypt);
//
//
//        if(encrypt) {
//            note.setPassword(AppUtils.generateHash(password));
//        } else note.setPassword(null);

        insertTask(filim_db_Model);
    }


    public void insertTask(final FilimModel filim_db_Model) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    filimDatabase.daoAccess().insertTask(filim_db_Model);
                }catch (Exception e){

                }
                return null;
            }
        }.execute();
    }

    public void updateTask(final FilimModel filim_db_Model) {
//        note.setModifiedAt(AppUtils.getCurrentDateTime());

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                filimDatabase.daoAccess().updateTask(filim_db_Model);
                return null;
            }
        }.execute();
    }

    public void deleteTask(final int id) {
        final LiveData<FilimModel> task = getTask(id);
        if(task != null) {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    filimDatabase.daoAccess().deleteTask(task.getValue());
                    return null;
                }
            }.execute();
        }
    }

    public void deleteTask(final FilimModel filim_db_Model) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                filimDatabase.daoAccess().deleteTask(filim_db_Model);
                return null;
            }
        }.execute();
    }

    public LiveData<FilimModel> getTask(int id) {
        return filimDatabase.daoAccess().getTask_group(id);
    }

    public LiveData<PagedList<FilimModel>> getTasks() {

        PagedList.Config pagedListConfig=(new PagedList.Config.Builder()).setEnablePlaceholders(true)
                .setPrefetchDistance(10)
                .setPageSize(20).build();

        LiveData<PagedList<FilimModel>> filimList;
        filimList = new LivePagedListBuilder<>(
                filimDatabase.daoAccess().fetchAllTasks_group(), pagedListConfig).build();
        return filimList;
    }


    public LiveData<Integer> getcount() {
        return filimDatabase.daoAccess().getCount();
    }
}
