package com.admin.ucsmonywa.secondyear_model;


import com.google.gson.annotations.SerializedName;


public class SheduleItem{

	@SerializedName("duration")
	private String duration;

	@SerializedName("teacher")
	private String teacher;

	@SerializedName("subject")
	private String subject;

	public void setDuration(String duration){
		this.duration = duration;
	}

	public String getDuration(){
		return duration;
	}

	public void setTeacher(String teacher){
		this.teacher = teacher;
	}

	public String getTeacher(){
		return teacher;
	}

	public void setSubject(String subject){
		this.subject = subject;
	}

	public String getSubject(){
		return subject;
	}
}