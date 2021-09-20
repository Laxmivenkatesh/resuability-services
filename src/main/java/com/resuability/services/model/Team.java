package com.resuability.services.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "teams")
public class Team {
	@Id
	private String id;
	private String name;
	private String programName;
	private String applicationName;
	private String securityGroup;
	private List<String> componentsCreated=new ArrayList<String>();
	private List<String> componentsUsed=new ArrayList<String>();
	private Date createdOn;
	private String createdBy;
	private Date updatedOn;
	private String updatedBy;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public String getSecurityGroup() {
		return securityGroup;
	}
	public void setSecurityGroup(String securityGroup) {
		this.securityGroup = securityGroup;
	}
	public List<String> getComponentsCreated() {
		return componentsCreated;
	}
	public void setComponentsCreated(List<String> componentsCreated) {
		this.componentsCreated = componentsCreated;
	}
	public List<String> getComponentsUsed() {
		return componentsUsed;
	}
	public void setComponentsUsed(List<String> componentsUsed) {
		this.componentsUsed = componentsUsed;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

}
