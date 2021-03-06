package com.cleanup.todoc.database.dao;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.cleanup.todoc.Utils.LiveDataUtil;
import com.cleanup.todoc.database.CleanupDatabase;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Kevin  - Openclassrooms on 10/01/2020
 */
@RunWith(AndroidJUnit4.class)
public class TaskDaoTest {

    // FOR DATA
    private CleanupDatabase database;

    // DATA SET FOR TEST
    private static long PROJECT_ID = 1;
    private static Project PROJECT_TEST = new Project(PROJECT_ID, "Projet Tartampion", 0xFFEADAD1);


    private static Task NEW_TASK_1 = new Task(1,PROJECT_ID,"test number one",1578217579);
    private static Task NEW_TASK_2 = new Task(2,PROJECT_ID,"test number two",1578217579);
    private static Task NEW_TASK_3 = new Task(3,PROJECT_ID,"test number three",1578217579);

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception {
        this.database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                CleanupDatabase.class)
                .allowMainThreadQueries()
                .build();
    }

    @After
    public void closeDb() throws Exception {
        database.close();
    }

    /**
     * get all projects , add a project and check if the size of projects is 0
     * then , get all projects after the last project added , and check if the list contain Project_TEST
     */
    @Test
    public void insertAndGetProject() throws InterruptedException {
        // TEST
        List<Project> projects = LiveDataUtil.getValue(this.database.projectDao().getAllProjects());
        this.database.projectDao().createProject(PROJECT_TEST);
        assertEquals(projects.size(), 0);

        projects = LiveDataUtil.getValue(this.database.projectDao().getAllProjects());
        assertEquals(projects.size(), 1);
    }

    @Test
    public void getTasksWhenNoTaskInserted() throws InterruptedException {
        // TEST
        List<Task> tasks = LiveDataUtil.getValue(this.database.taskDao().getTasks());
        assertTrue(tasks.isEmpty());
    }

    @Test
    public void insertAndGetTasks() throws InterruptedException {

        this.database.projectDao().createProject(PROJECT_TEST);
        this.database.taskDao().insertTask(NEW_TASK_1);
        this.database.taskDao().insertTask(NEW_TASK_2);
        this.database.taskDao().insertTask(NEW_TASK_3);

        // TEST
        List<Task> tasks = LiveDataUtil.getValue(this.database.taskDao().getTasks());
        assertEquals(3, tasks.size());
    }




    @Test
    public void insertAndDeleteTask() throws InterruptedException {
        this.database.projectDao().createProject(PROJECT_TEST);
        this.database.taskDao().insertTask(NEW_TASK_1);
        Task taskAdded = LiveDataUtil.getValue(this.database.taskDao().getTasks()).get(0);
        this.database.taskDao().deleteTask(taskAdded);

        //TEST
        List<Task> tasks = LiveDataUtil.getValue(this.database.taskDao().getTasks());
        assertTrue(tasks.isEmpty());
    }

}