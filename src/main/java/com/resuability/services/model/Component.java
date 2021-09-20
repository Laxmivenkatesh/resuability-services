package com.resuability.services.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "components")
public class Component {
	@Id
	private String id;
	private String name;
	private String type;
	private String description;
	private String version;
	private boolean active;
	private Integer linesOfCode;
	private List<String> dependencies=new ArrayList<String>();
	private List<String> tags=new ArrayList<String>();
	private String ownedBy;
	private List<String> usedBy=new ArrayList<String>();
	private Date createdOn;
	private String createdBy;
	private String updatedBy;
	private Date updatedOn;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Integer getLinesOfCode() {
		return linesOfCode;
	}
	public void setLinesOfCode(Integer linesOfCode) {
		this.linesOfCode = linesOfCode;
	}
	public List<String> getDependencies() {
		return dependencies;
	}
	public void setDependencies(List<String> dependencies) {
		this.dependencies = dependencies;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public String getOwnedBy() {
		return ownedBy;
	}
	public void setOwnedBy(String ownedBy) {
		this.ownedBy = ownedBy;
	}
	public List<String> getUsedBy() {
		return usedBy;
	}
	public void setUsedBy(List<String> usedBy) {
		this.usedBy = usedBy;
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
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	
	public Integer getUsedByCount() {
		if(usedBy==null) {
			return 0;
		}
		return getUsedBy().size();
	}
	
	
}
