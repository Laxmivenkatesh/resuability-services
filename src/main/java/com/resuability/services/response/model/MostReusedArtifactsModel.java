package com.resuability.services.response.model;

import java.util.ArrayList;
import java.util.List;

public class MostReusedArtifactsModel {
	
	private List<MostReusedArtifactItem> mostReusedArtifacts=new ArrayList<MostReusedArtifactItem>();

	public List<MostReusedArtifactItem> getMostReusedArtifacts() {
		return mostReusedArtifacts;
	}

	public void setMostReusedArtifacts(List<MostReusedArtifactItem> mostReusedArtifacts) {
		this.mostReusedArtifacts = mostReusedArtifacts;
	}
	

}
