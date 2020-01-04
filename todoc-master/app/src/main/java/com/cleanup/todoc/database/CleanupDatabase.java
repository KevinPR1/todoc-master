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
    public static CleanupDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (CleanupDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CleanupDatabase.class, "MyDatabase.db")
                            .addCallback(prepopulateDatabase())
                            .build();
                }
            }
        }
        return INSTANCE;
    }


    private static Callback prepopulateDatabase() {
        return new Callback() {

            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);

                ContentValues contentValues = new ContentValues();
                contentValues.put("id", 1);
                contentValues.put("task", "Salle 2");

                db.insert("Project", OnConflictStrategy.IGNORE, contentValues);
            }
        };

    }

}