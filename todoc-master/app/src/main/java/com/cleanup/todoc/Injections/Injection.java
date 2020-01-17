package com.cleanup.todoc.Injections;

import android.content.Context;

import com.cleanup.todoc.Repositories.ProjectDataRepository;
import com.cleanup.todoc.Repositories.TaskDataRepository;
import com.cleanup.todoc.database.CleanupDatabase;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Kevin  - Openclassrooms on 15/01/2020
 */
public class Injection {



    public static ProjectDataRepository provideProjectDataSource(Context context) {
        CleanupDatabase database = CleanupDatabase.getInstance(context);
        return new ProjectDataRepository(database.projectDao());
    }

    public static TaskDataRepository provideTaskDataSource(Context context) {
        CleanupDatabase database = CleanupDatabase.getInstance(context);
        return new TaskDataRepository(database.taskDao());
    }

    public static Executor provideExecutor(){ return Executors.newSingleThreadExecutor(); }

    public static ViewModelFactory provideViewModelFactory(Context context) {
        TaskDataRepository dataSourceTask = provideTaskDataSource(context);
        ProjectDataRepository dataSourceProject = provideProjectDataSource(context);
        Executor executor = provideExecutor();
        return new ViewModelFactory(dataSourceTask, dataSourceProject, executor);
    }

}
