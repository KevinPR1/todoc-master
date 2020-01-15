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

    @Test
    public void insertAndGetProject() throws InterruptedException {
        // BEFORE : Adding a new user
        this.database.projectDao().createProject(PROJECT_TEST);
        // TEST
        Project project = LiveDataUtil.getValue(this.database.projectDao().getProject(PROJECT_ID));
        assertTrue(project.getName().equals(PROJECT_TEST.getName()) && project.getId() == PROJECT_ID);
    }

    @Test
    public void getTasksWhenNoTaskInserted() throws InterruptedException {
        // TEST
        List<Task> items = LiveDataUtil.getValue(this.database.taskDao().getTasks(PROJECT_ID));
        assertTrue(items.isEmpty());
    }

    @Test
    public void insertAndGetTasks() throws InterruptedException {
        // BEFORE : Adding demo user & demo items

        this.database.projectDao().createProject(PROJECT_TEST);
        this.database.taskDao().insertTask(NEW_TASK_1);
        this.database.taskDao().insertTask(NEW_TASK_2);
        this.database.taskDao().insertTask(NEW_TASK_3);

        // TEST
        List<Task> tasks = LiveDataUtil.getValue(this.database.taskDao().getTasks(PROJECT_ID));
        assertEquals(3, tasks.size());
    }

    @Test
    public void insertAndDeleteTask() throws InterruptedException {
        // BEFORE : Adding demo user & demo item. Next, get the item added & delete it.
        this.database.projectDao().createProject(PROJECT_TEST);
        this.database.taskDao().insertTask(NEW_TASK_1);
        Task taskAdded = LiveDataUtil.getValue(this.database.taskDao().getTasks(PROJECT_ID)).get(0);
        this.database.taskDao().deleteTask(taskAdded.getId());

        //TEST
        List<Task> items = LiveDataUtil.getValue(this.database.taskDao().getTasks(PROJECT_ID));
        assertTrue(items.isEmpty());
    }

}