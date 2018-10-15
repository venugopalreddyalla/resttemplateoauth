package com.cognine.onlinetest.model;

public class TestSection {

	private int testId;
	private int sectionId;
	private String sectionName;
	private int questionsperSection;
	private int easyNo;
	private int mediumNo;
	private int hardNo;
	private String updatedBy;

	public int getTestId() {
		return testId;
	}
	
	

	public String getUpdatedBy() {
		return updatedBy;
	}



	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}



	public void setTestId(int testId) {
		this.testId = testId;
	}

	public int getSectionId() {
		return sectionId;
	}

	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public int getQuestionsperSection() {
		return questionsperSection;
	}

	public void setQuestionsperSection(int questionsperSection) {
		this.questionsperSection = questionsperSection;
	}

	public int getEasyNo() {
		return easyNo;
	}

	public void setEasyNo(int easyNo) {
		this.easyNo = easyNo;
	}

	public int getMediumNo() {
		return mediumNo;
	}

	public void setMediumNo(int mediumNo) {
		this.mediumNo = mediumNo;
	}

	public int getMediumNO() {
		return mediumNo;
	}

	public void setMediumNO(int mediumNO) {
		this.mediumNo = mediumNO;
	}

	public int getHardNo() {
		return hardNo;
	}

	public void setHardNo(int hardNo) {
		this.hardNo = hardNo;
	}

}
