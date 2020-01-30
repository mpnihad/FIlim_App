package com.nihad.filim_app.database.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import com.nihad.filim_app.model.FilimModel;

import java.util.List;



@Dao
public interface DaoAccess {



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertTask(FilimModel filim_db_model);


    @Query("SELECT * FROM FilimModel ORDER BY id desc")
    LiveData<List<FilimModel>> fetchAllTasks_group();

    @Query("SELECT COUNT(*) FROM FilimModel")
    LiveData<Integer> getCount();

    @Query("SELECT * FROM FilimModel WHERE id =:taskId")
    LiveData<FilimModel> getTask_group(int taskId);


    @Update
    void updateTask(FilimModel filimModel);


    @Delete
    void deleteTask(FilimModel filimModel);
}
