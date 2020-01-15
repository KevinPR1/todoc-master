package com.cleanup.todoc.Repositories;


import android.arch.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Task;

import java.util.List;

/**
 * Created by Kevin  - Openclassrooms on 15/01/2020
 */
public class TaskDataRepository {

    private final TaskDao taskDao;

    public TaskDataRepository(TaskDao taskDao) { this.taskDao = taskDao; }

    // --- GET ---
    public LiveData<List<Task>> getTask(){ return this.taskDao.getTasks(); }

    // --- CREATE ---

    public void createTask(Task task){ taskDao.insertTask(task); }

    // --- DELETE ---
    public void deleteTask(Task task){ taskDao.deleteTask(task); }
}
