package com.resuability.services.response.model;

public class MostReusedArtifactItem {
	private String componentName;
	private String componentType;
	private String linesOfCode;
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	public String getComponentType() {
		return componentType;
	}
	public void setComponentType(String componentType) {
		this.componentType = componentType;
	}
	public String getLinesOfCode() {
		return linesOfCode;
	}
	public void setLinesOfCode(String linesOfCode) {
		this.linesOfCode = linesOfCode;
	}	

}
