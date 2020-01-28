package com.nihad.filim_app.database.db;



import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.nihad.filim_app.database.dao.DaoAccess;
import com.nihad.filim_app.model.FilimModel;


@Database(entities = {FilimModel.class}, version = 1, exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {

    public abstract DaoAccess daoAccess();
}
