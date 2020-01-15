package com.cleanup.todoc.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

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

    // REPOSITORIES
    private final TaskDataRepository taskDataSource;
    private final ProjectDataRepository projectDataSource;
    private final Executor executor;

    // DATA
    @Nullable
    private LiveData<Project> currentProject;

    public TaskViewModel(TaskDataRepository taskDataSource, ProjectDataRepository projectDataSource, Executor executor) {
        this.taskDataSource = taskDataSource;
        this.projectDataSource = projectDataSource;
        this.executor = executor;
    }

    public void init(long projectId) {
        if (this.currentProject != null) {
            return;
        }
        currentProject = projectDataSource.getProject(projectId);
    }

    // -------------
    // FOR PROJECT
    // -------------

    public LiveData<Project> getProjects(long projectId) { return this.currentProject;  }

    public void createProject(Project project) {

        executor.execute(() -> {

            this.projectDataSource.crreateProject(project);

        });
    }

    // -------------
    // FOR TASKS
    // -------------

    public LiveData<List<Task>> getTasks() {
        return taskDataSource.getTask();
    }

    public void createTask(Task task) {
        executor.execute(() -> {
            taskDataSource.createTask(task);
        });
    }

    public void deleteTask(Task task) {
        executor.execute(() -> {
            taskDataSource.deleteTask(task);
        });
    }
}
