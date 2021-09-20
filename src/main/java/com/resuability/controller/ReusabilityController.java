package com.resuability.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.resuability.services.model.Component;
import com.resuability.services.model.Team;
import com.resuability.services.repository.ComponentRepository;
import com.resuability.services.repository.TeamRepository;
import com.resuability.services.response.model.ComponentModel;
import com.resuability.services.response.model.DeleteComponentRequest;
import com.resuability.services.response.model.DeleteComponentResponse;
import com.resuability.services.response.model.MostReusedArtifactItem;
import com.resuability.services.response.model.MostReusedArtifactsModel;
import com.resuability.services.response.model.RetrieveReusableComponentResponse;
import com.resuability.services.response.model.ReusabilityModel;
import com.resuability.services.response.model.ReusabilityPerComponentModel;
import com.resuability.services.response.model.ReusabilityPerTeamModel;
import com.resuability.services.response.model.ReusabilityStatsModel;
import com.resuability.services.response.model.SaveReusableComponentRequest;
import com.resuability.services.response.model.SaveReusableComponentResponse;
import com.resuability.services.response.model.StatusModel;

@CrossOrigin(origins = "http://localhost:8083")
@RestController
@RequestMapping(value ="/reusability-meter/v1")
public class ReusabilityController {
	@Autowired
	ComponentRepository componentRepository;
	@Autowired
	TeamRepository teamRepository;
	@Autowired
	ObjectMapper objectMapper;

	@GetMapping(value = "/stats", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getReusabilityStats() throws JsonProcessingException {
		List<Component> componentData = componentRepository.findAll();
		List<Team> teamData = teamRepository.findAll();
		String response= " ";
		Integer totLinesofCode=0;
		for(Component component:componentData) {
			totLinesofCode+=component.getLinesOfCode();
		}
		ReusabilityStatsModel model=new ReusabilityStatsModel();
		model.setReusableArtifacts(componentData.size());
		model.setTeamsUsingReusableComp(teamData.size());
		model.setTotLinesofCode(totLinesofCode);
		response=objectMapper.writeValueAsString(model).replace("totLinesofCode", "Total lines of code").replace("teamsUsingReusableComp", "Teams using reusable artifact");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/mostReusedArtifacts", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getMostReusedArtifacts() throws JsonProcessingException {
		MostReusedArtifactsModel mostReusedArtifactsModel=new MostReusedArtifactsModel(); 
		List<Component> componentData = componentRepository.findAll();
		Collections.sort(componentData,Comparator.comparingInt(Component::getUsedByCount).reversed());
		String response= " ";
		for(Component component:componentData) {
			MostReusedArtifactItem artifactItem=new MostReusedArtifactItem();
			artifactItem.setComponentName(component.getName());
			artifactItem.setComponentType(component.getType());
			artifactItem.setLinesOfCode(component.getName());
			mostReusedArtifactsModel.getMostReusedArtifacts().add(artifactItem);
		}
		response=objectMapper.writeValueAsString(mostReusedArtifactsModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/reusability", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getReusability() throws JsonProcessingException {
		ReusabilityModel reusabilityModel=new ReusabilityModel(); 

		List<Team> teamData = teamRepository.findAll();
		List<Component> componentData = componentRepository.findAll();

		String response= " ";
		for(Component component:componentData) {
			ReusabilityPerComponentModel artifactItem=new ReusabilityPerComponentModel();
			artifactItem.setComponentName(component.getName());
			artifactItem.setComponentId(component.getId());
			artifactItem.setNumberOfTeams(component.getUsedByCount());
			reusabilityModel.getReusabilityPerComponentModel().add(artifactItem);
		}

		for(Team team:teamData) {
			ReusabilityPerTeamModel artifactItem=new ReusabilityPerTeamModel();
			artifactItem.setTeamName(team.getName());
			artifactItem.setTeamId(team.getId());
			artifactItem.setNumberOfComponents(team.getComponentsUsed().size());
			reusabilityModel.getReusabilityPerTeamModel().add(artifactItem);
		}

		response=objectMapper.writeValueAsString(reusabilityModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}





	@GetMapping(value = "/components", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RetrieveReusableComponentResponse> getAllcomponents() throws JsonProcessingException {
		RetrieveReusableComponentResponse reusableComponentResponse=new RetrieveReusableComponentResponse(); 
		try {
			List<Component> componentData = componentRepository.findAll();

			for(Component component:componentData) {
				List<Team> teamData = teamRepository.findByIdIn(component.getUsedBy());
				ComponentModel componentModel=new ComponentModel();
				componentModel.setId(component.getId());
				componentModel.setName(component.getName());
				componentModel.setType(component.getType());
				componentModel.setDescription(component.getDescription());
				componentModel.setVersion(component.getVersion());
				componentModel.setActive(component.isActive());
				componentModel.setLinesOfCode(component.getLinesOfCode());
				componentModel.setOwnedBy(component.getOwnedBy());
				componentModel.setUsedBy(component.getUsedBy());
				componentModel.setTeamInfo(teamData);
				reusableComponentResponse.getComponentInfo().add(componentModel);
			}
		}
		catch (Exception e) {
			StatusModel model=new StatusModel();
			model.setStatusDesc(e.getMessage());
			reusableComponentResponse.getStatusDetails().add(model);
			return new ResponseEntity<>(reusableComponentResponse, HttpStatus.OK);
		}
		//response=objectMapper.writeValueAsString(reusabilityModel);
		return new ResponseEntity<>(reusableComponentResponse, HttpStatus.OK);
	}



	@PostMapping("/save/component")
	public ResponseEntity<SaveReusableComponentResponse> createOrUpdateComponent(@RequestBody SaveReusableComponentRequest saveReusableComponentRequest) throws JsonProcessingException {
		SaveReusableComponentRequest componentRequest=new SaveReusableComponentRequest();
		objectMapper.writeValueAsString(componentRequest);
		SaveReusableComponentResponse saveReusableComponentResponse=new SaveReusableComponentResponse();
		try {
			boolean isNewComponent=false;
			boolean isNewTeam=false;
			List<Team> teamsList=new ArrayList<Team>();
			for(ComponentModel componentModel:saveReusableComponentRequest.getComponentInfo()) {
				List<String> teamIds=new ArrayList<String>();
				if(null==componentModel.getId()) {
					//componentModel.setId(UUID.randomUUID().toString());
					isNewComponent=true;
				}
				List<Team> teams=componentModel.getTeamInfo();
				for(Team teamModel:teams) {
					if(null==teamModel.getId()) {
						//teamModel.setId(UUID.randomUUID().toString());
						isNewTeam=true;
					}
					teamIds.add(teamModel.getId());
					teamModel.getComponentsUsed().add(componentModel.getId());
					Set<String> set = new LinkedHashSet<>();
					set.addAll(teamModel.getComponentsUsed());
					teamModel.getComponentsUsed().clear();
					teamModel.getComponentsUsed().addAll(set);
					componentModel.getUsedBy().add(teamModel.getId());

					Team team =new Team();
					mapTeam(teamModel, team,isNewTeam);
					teamsList.add(team);
				}
				Set<String> set = new LinkedHashSet<>();
				set.addAll(teamIds);
				componentModel.getUsedBy().clear();
				componentModel.getUsedBy().addAll(set);

				Component component =new Component();
				mapComponent(componentModel, component,isNewComponent);
				componentRepository.save(component);
				teamRepository.save(teamsList);

			}
			return new ResponseEntity<>(saveReusableComponentResponse, HttpStatus.CREATED);
		} catch (Exception e) {
			StatusModel model=new StatusModel();
			model.setStatusDesc(e.getMessage());
			saveReusableComponentResponse.getStatusDetails().add(model);
			return new ResponseEntity<>(saveReusableComponentResponse, HttpStatus.OK);
		}
	}

	@PostMapping("/delete")
	public ResponseEntity<DeleteComponentResponse> deleteComponent(@RequestBody DeleteComponentRequest deleteComponentRequest) {
		DeleteComponentResponse deleteComponentResponse=new DeleteComponentResponse();
		try {

			for(ComponentModel componentModel:deleteComponentRequest.getComponentInfo()) {
				Component componentToBeDeleted= componentRepository.findById(componentModel.getId()).get();
				List<String> teams=componentToBeDeleted.getUsedBy();
				componentRepository.delete(componentToBeDeleted);
				List<Team> teamsToUpdate=teamRepository.findByIdIn(teams);
				teamsToUpdate.forEach(team->team.getComponentsUsed().removeIf(component->component.equals(componentToBeDeleted.getId())));
				teamRepository.save(teamsToUpdate);
				for(Team teamModel:componentModel.getTeamInfo()) {
					Team teamToBeDeleted= teamRepository.findById(teamModel.getId()).get();
					List<String> components=teamToBeDeleted.getComponentsUsed();
					teamRepository.delete(teamToBeDeleted);
					List<Component> componentToUpdate=componentRepository.findByIdIn(components);
					componentToUpdate.forEach(component->component.getUsedBy().removeIf(team->team.equals(teamToBeDeleted.getId())));
					componentRepository.save(componentToUpdate);

				}

			}

			return new ResponseEntity<>(deleteComponentResponse, HttpStatus.OK);
		} catch (Exception e) {
			StatusModel model=new StatusModel();
			model.setStatusDesc(e.getMessage());
			deleteComponentResponse.getStatusDetails().add(model);
			return new ResponseEntity<>(deleteComponentResponse, HttpStatus.OK);
		}
	}

	private void mapTeam(Team teamModel, Team team,boolean isNew) {
		if(null!=teamModel.getName()) {
			team.setName(teamModel.getName());	
		}
		if(null!=teamModel.getProgramName()) {
			team.setProgramName(teamModel.getProgramName());	
		}
		if(null!=teamModel.getApplicationName()) {
			team.setApplicationName(teamModel.getApplicationName());	
		}
		if(null!=teamModel.getProgramName()) {
			team.setProgramName(teamModel.getProgramName());	
		}
		if(null!=teamModel.getSecurityGroup()) {
			team.setSecurityGroup(teamModel.getSecurityGroup());	
		}
		if(null!=teamModel.getComponentsCreated()) {
			team.setComponentsCreated(teamModel.getComponentsCreated());	
		}
		if(null!=teamModel.getComponentsUsed()) {
			team.setComponentsUsed(teamModel.getComponentsUsed());	
		}
		if(isNew) {
			team.setCreatedOn(new Date());	
		}
		if(null!=teamModel.getCreatedBy()) {
			team.setCreatedBy(teamModel.getCreatedBy());	
		}
		team.setUpdatedOn(new Date());	

		if(null!=teamModel.getUpdatedBy()) {
			team.setUpdatedBy(teamModel.getUpdatedBy());	
		}
	}

	private void mapComponent(ComponentModel componentModel, Component component,boolean isNew) {
		component.setName(componentModel.getName());
		component.setType(componentModel.getType());
		component.setDescription(componentModel.getDescription());
		component.setVersion(componentModel.getVersion());
		component.setActive(componentModel.isActive());
		component.setOwnedBy(componentModel.getOwnedBy());

		if(null!=componentModel.getLinesOfCode()) {
			component.setLinesOfCode(componentModel.getLinesOfCode());
		}
		if(null!=componentModel.getDependencies()) {
			component.setDependencies(componentModel.getDependencies());
		}
		if(null!=componentModel.getTags()) {
			component.setTags(componentModel.getTags());
		}
		if(null!=componentModel.getUsedBy()) {
			component.setUsedBy(componentModel.getUsedBy());
		}
		if(null!=componentModel.getCreatedBy()) {
			component.setCreatedBy(componentModel.getCreatedBy());
		}
		if(null!=componentModel.getUpdatedBy()) {
			component.setUpdatedBy(componentModel.getUpdatedBy());
		}
		if(isNew) {
			component.setCreatedOn(new Date());
		}
		component.setUpdatedOn(new Date());

	}



}
