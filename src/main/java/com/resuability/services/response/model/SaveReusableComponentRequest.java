package com.resuability.services.response.model;

import java.util.ArrayList;
import java.util.List;

public class SaveReusableComponentRequest {
	
	private List<ComponentModel> componentInfo=new ArrayList<ComponentModel>();
	

	public List<ComponentModel> getComponentInfo() {
		return componentInfo;
	}
	public void setComponentInfo(List<ComponentModel> componentInfo) {
		this.componentInfo = componentInfo;
	}

}
