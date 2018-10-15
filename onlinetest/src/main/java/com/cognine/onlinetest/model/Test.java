package com.cognine.onlinetest.model;

import java.util.Date;
import java.util.List;

public class Test {

	private int testId;
	private String testName;
	private int testDurationinMins;
	private int testCodeActivetimeinMins;
	private boolean isNegativeMarking;
	private boolean isLateralTest;
	private boolean isActive = true;
	private Date creationDate;
	private Date updationDate;
	private String updatedBy;
	private List<TestSection> sectionList;

	public List<TestSection> getSectionList() {
		return sectionList;
	}

	public void setSectionList(List<TestSection> sectionList) {
		this.sectionList = sectionList;
	}

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public int getTestDurationinMins() {
		return testDurationinMins;
	}

	public void setTestDurationinMins(int testDurationinMins) {
		this.testDurationinMins = testDurationinMins;
	}

	public int getTestCodeActivetimeinMins() {
		return testCodeActivetimeinMins;
	}

	public void setTestCodeActivetimeinMins(int testCodeActivetimeinMins) {
		this.testCodeActivetimeinMins = testCodeActivetimeinMins;
	}

	public boolean isNegativeMarking() {
		return isNegativeMarking;
	}

	public void setNegativeMarking(boolean isNegativeMarking) {
		this.isNegativeMarking = isNegativeMarking;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
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

	public boolean isLateralTest() {
		return isLateralTest;
	}

	public void setLateralTest(boolean isLateralTest) {
		this.isLateralTest = isLateralTest;
	}

}
