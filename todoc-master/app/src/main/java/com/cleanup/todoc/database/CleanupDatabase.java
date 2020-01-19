package com.cleanup.todoc.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;

import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

/**
 * Created by Kevin  - Openclassrooms on 02/01/2020
 */
@Database(entities = {Task.class, Project.class}, version = 1, exportSchema = false)
public abstract class CleanupDatabase extends RoomDatabase {

    // --- SINGLETON ---
    private static volatile CleanupDatabase INSTANCE;

    // --- DAO ---
    public abstract TaskDao taskDao();

    public abstract ProjectDao projectDao();
    
    // --- INSTANCE ---
    /**
     * get instance of database : "MyDatabase.db"
     */
    public static CleanupDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (CleanupDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CleanupDatabase.class, "MyDatabase.db")
                            .addCallback(prepopulateDatabase()).allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * test to create some task for instrumentalized tests
     * and create some data in the virtual phone
     */
    private static Callback prepopulateDatabase() {
        return new Callback() {

            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                //project
                ContentValues project = new ContentValues();
                project.put("id",2);
                project.put("name","2");
                project.put("color",0xFFEADAD1);
                db.insert("Project", OnConflictStrategy.IGNORE,project);
                //Task
                ContentValues contentValues = new ContentValues();
                contentValues.put("id", 1);
                contentValues.put("name", "Salle 2");
                contentValues.put("projectId", 2);
                contentValues.put("creationTimestamp", 1578217579);

                db.insert("Task", OnConflictStrategy.IGNORE, contentValues);
            }
        };

    }

}