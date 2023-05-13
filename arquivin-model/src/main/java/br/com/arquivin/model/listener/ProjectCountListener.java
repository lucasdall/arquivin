package br.com.arquivin.model.listener;

import javax.persistence.PostLoad;

import br.com.arquivin.model.File;
import br.com.arquivin.model.Project;

public class ProjectCountListener {

	@PostLoad
	public void updateCounts(Project project) {
		if(project.getAllowedUsers() != null) {
			project.setCountUsers(project.getAllowedUsers().size());
		}
		if(project.getFiles() != null) {
			Integer countFiles = 0;
			for(File file : project.getFiles()) {
				if(!file.getRemoved()) {
					countFiles++;
				}
			}
			project.setCountfiles(countFiles);
		}
	}
	
}
