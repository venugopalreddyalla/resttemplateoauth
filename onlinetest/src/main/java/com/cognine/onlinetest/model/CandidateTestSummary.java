package com.cognine.onlinetest.model;

import java.util.Date;

public class CandidateTestSummary {
	private int candidateId;
	private int testId;
	private String testCode;
	private String testName;
	private String uuId;
	private boolean isTestStarted;
	private Date startTime;
	private Date endTime;
	private int elapsedTimeInMins;
	private boolean isTestCompleted;
	private float scored;
	private float maxScore;
	private float lowScore;
	private float mediumScore;
	private float highScore;
	private String sectionBreakUp;
	private String completionText;
	private String emailid;

	public int getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(int candidateId) {
		this.candidateId = candidateId;
	}

	public String getTestCode() {
		return testCode;
	}

	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public String getUuId() {
		return uuId;
	}

	public void setUuId(String uuId) {
		this.uuId = uuId;
	}

	public boolean isTestStarted() {
		return isTestStarted;
	}

	public void setTestStarted(boolean isTestStarted) {
		this.isTestStarted = isTestStarted;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getElapsedTimeInMins() {
		return elapsedTimeInMins;
	}

	public void setElapsedTimeInMins(int elapsedTimeInMins) {
		this.elapsedTimeInMins = elapsedTimeInMins;
	}

	public boolean isTestCompleted() {
		return isTestCompleted;
	}

	public void setTestCompleted(boolean isTestCompleted) {
		this.isTestCompleted = isTestCompleted;
	}

	public float getScored() {
		return scored;
	}

	public void setScored(float scored) {
		this.scored = scored;
	}

	public float getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(float maxScore) {
		this.maxScore = maxScore;
	}

	public float getLowScore() {
		return lowScore;
	}

	public void setLowScore(float lowScore) {
		this.lowScore = lowScore;
	}

	public float getMediumScore() {
		return mediumScore;
	}

	public void setMediumScore(float mediumScore) {
		this.mediumScore = mediumScore;
	}

	public float getHighScore() {
		return highScore;
	}

	public void setHighScore(float highScore) {
		this.highScore = highScore;
	}

	public String getSectionBreakUp() {
		return sectionBreakUp;
	}

	public void setSectionBreakUp(String sectionBreakUp) {
		this.sectionBreakUp = sectionBreakUp;
	}

	public String getCompletionText() {
		return completionText;
	}

	public void setCompletionText(String completionText) {
		this.completionText = completionText;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

}
