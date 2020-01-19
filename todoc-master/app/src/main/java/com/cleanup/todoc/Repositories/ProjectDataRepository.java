package com.cleanup.todoc.Repositories;

import android.arch.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.model.Project;

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
    public LiveData<Project> getProject(long projectId) { return this.projectDao.getProject(projectId); }

    /**
     * Create project from database
     */
    // --- CREATE PROJECT ---
    public void createProject(Project project)
    {
        this.projectDao.createProject(project);
    }

}
