package com.cleanup.todoc.Repositories;

import android.arch.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.model.Project;

/**
 * Created by Kevin  - Openclassrooms on 15/01/2020
 */
public class ProjectDataRepository {

    private final ProjectDao projectDao;

    public ProjectDataRepository(ProjectDao projectDao) { this.projectDao = projectDao; }

    // --- GET USER ---
    public LiveData<Project> getProject(long projectId) { return this.projectDao.getProject(projectId); }
}
