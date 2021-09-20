package com.resuability.services.response.model;

import java.util.ArrayList;
import java.util.List;

public class ReusabilityModel {
	
	private List<ReusabilityPerTeamModel> reusabilityPerTeamModel=new ArrayList<ReusabilityPerTeamModel>();
	private List<ReusabilityPerComponentModel> reusabilityPerComponentModel=new ArrayList<ReusabilityPerComponentModel>();
	
	public List<ReusabilityPerTeamModel> getReusabilityPerTeamModel() {
		return reusabilityPerTeamModel;
	}
	public void setReusabilityPerTeamModel(List<ReusabilityPerTeamModel> reusabilityPerTeamModel) {
		this.reusabilityPerTeamModel = reusabilityPerTeamModel;
	}
	public List<ReusabilityPerComponentModel> getReusabilityPerComponentModel() {
		return reusabilityPerComponentModel;
	}
	public void setReusabilityPerComponentModel(List<ReusabilityPerComponentModel> reusabilityPerComponentModel) {
		this.reusabilityPerComponentModel = reusabilityPerComponentModel;
	}

}
