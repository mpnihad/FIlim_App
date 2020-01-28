package com.nihad.filim_app.database.repository;


import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.nihad.filim_app.database.db.NoteDatabase;
import com.nihad.filim_app.model.FilimModel;

import java.util.List;

public class FilimModelRepository {

    private String DB_NAME = "db_task";

    private NoteDatabase noteDatabase;
    public FilimModelRepository(Context context) {
        noteDatabase = Room.databaseBuilder(context, NoteDatabase.class, DB_NAME).build();
    }

    public void insertTask(String title,
                           String description) {

      //  insertTask(title, description, false, null);
    }

    public void insertTask1(FilimModel account_group) {


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

        insertTask(account_group);
    }

    public void insertTask(final FilimModel account_group) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    noteDatabase.daoAccess().insertTask(account_group);
                }catch (Exception e){

                }
                return null;
            }
        }.execute();
    }

    public void updateTask(final FilimModel account_group) {
//        note.setModifiedAt(AppUtils.getCurrentDateTime());

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                noteDatabase.daoAccess().updateTask(account_group);
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
                    noteDatabase.daoAccess().deleteTask(task.getValue());
                    return null;
                }
            }.execute();
        }
    }

    public void deleteTask(final FilimModel account_group) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                noteDatabase.daoAccess().deleteTask(account_group);
                return null;
            }
        }.execute();
    }

    public LiveData<FilimModel> getTask(int id) {
        return noteDatabase.daoAccess().getTask_group(id);
    }

    public LiveData<List<FilimModel>> getTasks() {
        return noteDatabase.daoAccess().fetchAllTasks_group();
    }
}
