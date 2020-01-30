package com.nihad.filim_app.database.db;



import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.nihad.filim_app.Utils.Converters;
import com.nihad.filim_app.database.dao.DaoAccess;
import com.nihad.filim_app.model.FilimModel;


@Database(entities = {FilimModel.class}, version = 1, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class FilimDatabase extends RoomDatabase {

    public abstract DaoAccess daoAccess();
}
