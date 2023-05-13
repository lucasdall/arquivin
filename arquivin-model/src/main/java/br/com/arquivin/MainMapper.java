package br.com.arquivin;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import br.com.arquivin.dto.ProjectDTO;
import br.com.arquivin.model.Project;

public class MainMapper {

	public static void main(String[] args) {
		ModelMapper mm = new ModelMapper();
		
		Project parent = new Project();
		parent.setName("pai");
		
		Project child1 = new Project();
		child1.setName("filho1");
		child1.setParentProject(parent);
		Project child2 = new Project();
		child2.setName("filho2");
		child2.setParentProject(parent);
		
		parent.getChildProjects().add(child1);
		parent.getChildProjects().add(child2);
		
//		ProjectDTO dto = mm.map(parent, ProjectDTO.class);
		List<Project> pjs = new ArrayList<>(0);
		pjs.add(parent);
		List<ProjectDTO> dto = mm.map(pjs, new TypeToken<List<ProjectDTO>>() {}.getType());
		System.out.println(ToStringBuilder.reflectionToString(dto, ToStringStyle.MULTI_LINE_STYLE));
//		System.out.println(ToStringBuilder.reflectionToString(dto.getChildProjects().get(0), ToStringStyle.MULTI_LINE_STYLE));
//		System.out.println(ToStringBuilder.reflectionToString(dto.getChildProjects().get(1), ToStringStyle.MULTI_LINE_STYLE));
	}
	
}
