package com.admin.ucsmonywa.thirdyear_model;


import com.google.gson.annotations.SerializedName;


public class ThirdYearData{

	@SerializedName("tuesday")
	private Tuesday tuesday;

	@SerializedName("wednesday")
	private Wednesday wednesday;

	@SerializedName("thursday")
	private Thursday thursday;

	@SerializedName("friday")
	private Friday friday;

	@SerializedName("id")
	private String id;

	@SerializedName("monday")
	private Monday monday;

	public void setTuesday(Tuesday tuesday){
		this.tuesday = tuesday;
	}

	public Tuesday getTuesday(){
		return tuesday;
	}

	public void setWednesday(Wednesday wednesday){
		this.wednesday = wednesday;
	}

	public Wednesday getWednesday(){
		return wednesday;
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

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setMonday(Monday monday){
		this.monday = monday;
	}

	public Monday getMonday(){
		return monday;
	}
}