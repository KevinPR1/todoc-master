package com.cleanup.todoc.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.cleanup.todoc.Repositories.ProjectDataRepository;
import com.cleanup.todoc.Repositories.TaskDataRepository;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * Created by Kevin  - Openclassrooms on 15/01/2020
 */
public class TaskViewModel extends ViewModel {

    //region REPOSITORIES
    private final TaskDataRepository taskDataSource;
    private final ProjectDataRepository projectDataSource;
    private final Executor executor;
    //endregion


    public TaskViewModel(TaskDataRepository taskDataSource, ProjectDataRepository projectDataSource, Executor executor) {
        this.taskDataSource = taskDataSource;
        this.projectDataSource = projectDataSource;
        this.executor = executor;
    }




    //region Project

    /**
     * Create project ( from database )
     */
    public void createProject(Project project) {
        executor.execute(() -> {
            this.projectDataSource.createProject(project);
        });
    }

    //endregion


    //region Task

    /**
     * Get list task ( from database )
     */
    public LiveData<List<Task>> getTasks() {
        return taskDataSource.getTask();
    }

    /**
     * Create task ( from database )
     */
    public void createTask(Task task) {
        executor.execute(() -> {
            taskDataSource.createTask(task);
        });
    }

    /**
     * Delete task ( from database )
     */
    public void deleteTask(Task task) {
        executor.execute(() -> {
            taskDataSource.deleteTask(task);
        });
    }

    //endregion

}
