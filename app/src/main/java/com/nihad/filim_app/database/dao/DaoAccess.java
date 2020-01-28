package com.nihad.filim_app.database.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.nihad.filim_app.model.FilimModel;

import java.util.List;

@Dao
public interface DaoAccess {



    @Insert
    Long insertTask(FilimModel account_group);


    @Query("SELECT * FROM FilimModel ORDER BY id desc")
    LiveData<List<FilimModel>> fetchAllTasks_group();


    @Query("SELECT * FROM FilimModel WHERE id =:taskId")
    LiveData<FilimModel> getTask_group(int taskId);


    @Update
    void updateTask(FilimModel note);


    @Delete
    void deleteTask(FilimModel note);
}
