package com.admin.ucsmonywa.thirdyear_model;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class Friday{

	@SerializedName("shedule")
	private List<SheduleItem> shedule;

	public void setShedule(List<SheduleItem> shedule){
		this.shedule = shedule;
	}

	public List<SheduleItem> getShedule(){
		return shedule;
	}
}