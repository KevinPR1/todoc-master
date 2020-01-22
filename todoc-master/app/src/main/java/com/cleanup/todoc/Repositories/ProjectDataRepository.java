package com.cleanup.todoc.Repositories;

import android.arch.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.model.Project;

import java.util.List;

/**
 * Created by Kevin  - Openclassrooms on 15/01/2020
 */
public class ProjectDataRepository {

    private final ProjectDao projectDao;

    /**
     * ViewModel does not directly manipulate the data source.
     */
    public ProjectDataRepository(ProjectDao projectDao) { this.projectDao = projectDao; }

    /**
     * Get the list of project in database
     */
    // --- GET PROJECT ---
    public LiveData<List<Project>> getAllProjects() { return this.projectDao.getAllProjects(); }

    /**
     * Create project from database
     */
    // --- CREATE PROJECT ---
    public void createProject(Project project)
    {
        this.projectDao.createProject(project);
    }

}
