package com.cognine.onlinetest.model;

import java.util.Date;

public class TestCode {

	private int testId;
	private String testCode;
	private Date activeFrom;
	private Date activeTo;
	private String instituteName;
	private String contactName;
	private String contactNo;
	private String contactEmail;
	private Date testCodeCreationTime;
	private boolean isTestCodeActive;
	private Date updationDate;
	private String updatedBy;
	private String testName;
	private Date sheduleCurrectTime;

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public String getTestCode() {
		return testCode;
	}

	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}

	public Date getActiveFrom() {
		return activeFrom;
	}

	public void setActiveFrom(Date activeFrom) {
		this.activeFrom = activeFrom;
	}

	public Date getActiveTo() {
		return activeTo;
	}

	public void setActiveTo(Date activeTo) {
		this.activeTo = activeTo;
	}

	public String getInstituteName() {
		return instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public Date getTestCodeCreationTime() {
		return testCodeCreationTime;
	}

	public void setTestCodeCreationTime(Date testCodeCreationTime) {
		this.testCodeCreationTime = testCodeCreationTime;
	}

	public boolean isTestCodeActive() {
		return isTestCodeActive;
	}

	public void setTestCodeActive(boolean isTestCodeActive) {
		this.isTestCodeActive = isTestCodeActive;
	}

	public Date getUpdationDate() {
		return updationDate;
	}

	public void setUpdationDate(Date updationDate) {
		this.updationDate = updationDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public Date getSheduleCurrectTime() {
		return sheduleCurrectTime;
	}

	public void setSheduleCurrectTime(Date sheduleCurrectTime) {
		this.sheduleCurrectTime = sheduleCurrectTime;
	}

}
