package com.cleanup.todoc.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;


import com.cleanup.todoc.model.Task;

import java.util.List;

/**
 * Created by Kevin  - Openclassrooms on 02/01/2020
 */
@Dao
public interface TaskDao {

    /**
     * get the list of task
     */
    @Query("SELECT * FROM Task")
    LiveData<List<Task>> getTasks();

    /**
     * insert a task in the list
     */
    @Insert
    long insertTask(Task task);

    /**
     * delete a task into the list
     */
    @Delete
    void deleteTask(Task task);
}
