package com.admin.ucsmonywa.fourthyear_model;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class Tuesday{

	@SerializedName("shedule")
	private List<SheduleItem> shedule;

	public void setShedule(List<SheduleItem> shedule){
		this.shedule = shedule;
	}

	public List<SheduleItem> getShedule(){
		return shedule;
	}
}