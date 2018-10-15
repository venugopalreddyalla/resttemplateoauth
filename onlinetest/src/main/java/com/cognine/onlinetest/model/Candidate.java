package com.cognine.onlinetest.model;

import java.util.Date;

public class Candidate {

	private int canId;
	private String name;
	private String emailId;
	private String degree;
	private String collegeName;
	private String department;
	private int graduationYear;
	private float percentage;
	private String phoneNo;
	private String govtUIDType;
	private String govtUID;
	private String currentEmployer;
	private String currentPosition;
	private Date currentEmpStartDate;
	private Date currentEmpEndDate;
	private boolean currentlyWorking;
	private String currentEmpCity;
	private int totalExpYears;
	private int totalExpMonths;
	private Date creationDate;
	private String gender;
	private boolean isAllowedForTest = true;
	private boolean isResumeTest = false;

	public int getCanId() {
		return canId;
	}

	public void setCanId(int canId) {
		this.canId = canId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getGovtUIDType() {
		return govtUIDType;
	}

	public void setGovtUIDType(String govtUIDType) {
		this.govtUIDType = govtUIDType;
	}

	public String getGovtUID() {
		return govtUID;
	}

	public void setGovtUID(String govtUID) {
		this.govtUID = govtUID;
	}

	public String getCurrentEmployer() {
		return currentEmployer;
	}

	public void setCurrentEmployer(String currentEmployer) {
		this.currentEmployer = currentEmployer;
	}

	public String getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(String currentPosition) {
		this.currentPosition = currentPosition;
	}

	public Date getCurrentEmpStartDate() {
		return currentEmpStartDate;
	}

	public void setCurrentEmpStartDate(Date currentEmpStartDate) {
		this.currentEmpStartDate = currentEmpStartDate;
	}

	public Date getCurrentEmpEndDate() {
		return currentEmpEndDate;
	}

	public void setCurrentEmpEndDate(Date currentEmpEndDate) {
		this.currentEmpEndDate = currentEmpEndDate;
	}

	public boolean isCurrentlyWorking() {
		return currentlyWorking;
	}

	public void setCurrentlyWorking(boolean currentlyWorking) {
		this.currentlyWorking = currentlyWorking;
	}

	public String getCurrentEmpCity() {
		return currentEmpCity;
	}

	public void setCurrentEmpCity(String currentEmpCity) {
		this.currentEmpCity = currentEmpCity;
	}

	public int getTotalExpYears() {
		return totalExpYears;
	}

	public void setTotalExpYears(int totalExpYears) {
		this.totalExpYears = totalExpYears;
	}

	public int getTotalExpMonths() {
		return totalExpMonths;
	}

	public void setTotalExpMonths(int totalExpMonths) {
		this.totalExpMonths = totalExpMonths;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public int getGraduationYear() {
		return graduationYear;
	}

	public void setGraduationYear(int graduationYear) {
		this.graduationYear = graduationYear;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public boolean isAllowedForTest() {
		return isAllowedForTest;
	}

	public void setAllowedForTest(boolean isAllowedForTest) {
		this.isAllowedForTest = isAllowedForTest;
	}

	public boolean isResumeTest() {
		return isResumeTest;
	}

	public void setResumeTest(boolean isResumeTest) {
		this.isResumeTest = isResumeTest;
	}

}
