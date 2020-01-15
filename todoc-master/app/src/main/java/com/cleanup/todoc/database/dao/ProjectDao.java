package com.cleanup.todoc.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.cleanup.todoc.model.Project;


/**
 * Created by Kevin  - Openclassrooms on 02/01/2020
 */
@Dao
public interface ProjectDao {
    /**
     * Create a project when the user click on any project in the spinner
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createProject(Project project);

    /**
     * get prject Id for the task
     */
    @Query("SELECT * FROM Project WHERE id = :id")
    LiveData<Project> getProject(long id);

}
