package com.resuability.services.response.model;

import java.util.ArrayList;
import java.util.List;

public class RetrieveReusableComponentResponse {
	
	private String responseStatus;
	private String statusCode;
	private String statusDesc;
	private List<StatusModel> statusDetails=new ArrayList<StatusModel>();
	private List<ComponentModel> componentInfo=new ArrayList<ComponentModel>();
	
	public String getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public List<StatusModel> getStatusDetails() {
		return statusDetails;
	}
	public void setStatusDetails(List<StatusModel> statusDetails) {
		this.statusDetails = statusDetails;
	}
	public List<ComponentModel> getComponentInfo() {
		return componentInfo;
	}
	public void setComponentInfo(List<ComponentModel> componentInfo) {
		this.componentInfo = componentInfo;
	}

}
