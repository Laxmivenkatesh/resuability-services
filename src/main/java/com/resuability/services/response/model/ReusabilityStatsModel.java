package com.resuability.services.response.model;

public class ReusabilityStatsModel {
	private Integer reusableArtifacts; 
	private Integer totLinesofCode;
	private Integer teamsUsingReusableComp;
	
	public Integer getReusableArtifacts() {
		return reusableArtifacts;
	}
	public void setReusableArtifacts(Integer reusableArtifacts) {
		this.reusableArtifacts = reusableArtifacts;
	}
	public Integer getTotLinesofCode() {
		return totLinesofCode;
	}
	public void setTotLinesofCode(Integer totLinesofCode) {
		this.totLinesofCode = totLinesofCode;
	}
	public Integer getTeamsUsingReusableComp() {
		return teamsUsingReusableComp;
	}
	public void setTeamsUsingReusableComp(Integer teamsUsingReusableComp) {
		this.teamsUsingReusableComp = teamsUsingReusableComp;
	}
	
	


}
