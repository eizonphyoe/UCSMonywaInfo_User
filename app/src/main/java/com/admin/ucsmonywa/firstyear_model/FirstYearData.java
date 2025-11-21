package com.admin.ucsmonywa.firstyear_model;


import com.google.gson.annotations.SerializedName;


public class FirstYearData{

	@SerializedName("Monday")
	private Monday monday;

	@SerializedName("Thursday")
	private Thursday thursday;

	@SerializedName("Friday")
	private Friday friday;

	@SerializedName("wednesday")
	private Wednesday wednesday;

	@SerializedName("Tuesday")
	private Tuesday tuesday;

	@SerializedName("id")
	private String id;

	public void setMonday(Monday monday){
		this.monday = monday;
	}

	public Monday getMonday(){
		return monday;
	}

	public void setThursday(Thursday thursday){
		this.thursday = thursday;
	}

	public Thursday getThursday(){
		return thursday;
	}

	public void setFriday(Friday friday){
		this.friday = friday;
	}

	public Friday getFriday(){
		return friday;
	}

	public void setWednesday(Wednesday wednesday){
		this.wednesday = wednesday;
	}

	public Wednesday getWednesday(){
		return wednesday;
	}

	public void setTuesday(Tuesday tuesday){
		this.tuesday = tuesday;
	}

	public Tuesday getTuesday(){
		return tuesday;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}
}