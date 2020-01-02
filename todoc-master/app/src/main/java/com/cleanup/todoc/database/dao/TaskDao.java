package com.cleanup.todoc.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.cleanup.todoc.model.Task;

import java.util.List;

/**
 * Created by Kevin  - Openclassrooms on 02/01/2020
 */
@Dao
public interface TaskDao {

    @Query("SELECT * FROM Task WHERE projectId = :id")
    LiveData<List<Task>> getTasks(long id);

    @Insert
    long insertTask(Task task);

    @Update
    int updateTask(Task task);

    @Query("DELETE FROM Task WHERE id = :id")
    int deleteTask(long id);
}
