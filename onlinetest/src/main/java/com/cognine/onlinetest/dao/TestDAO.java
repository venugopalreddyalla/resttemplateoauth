package com.cognine.onlinetest.dao;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cognine.onlinetest.model.CandidateTestSummary;
import com.cognine.onlinetest.model.Test;
import com.cognine.onlinetest.model.TestCode;
import com.cognine.onlinetest.model.TestSection;
import com.cognine.onlinetest.utils.exception.EntityNotFoundException;
import com.cognine.onlinetest.utils.exception.FailedToPersistException;

public interface TestDAO {
	public static final Logger logger = LogManager.getLogger(TestDAO.class);

	public int createTest(Test test) throws FailedToPersistException;

	public int updateTest(Test test) throws FailedToPersistException;

	public List<Test> getTests(int limit, int pageNumber, String searchTestName) throws EntityNotFoundException;

	public int createTestcode(TestCode testCode) throws FailedToPersistException;

	public int updateTestCodeDetails(TestCode testCode) throws FailedToPersistException;

	public List<TestCode> getTestCodeDetails(int limit, int pageNumber, String searchTestName)
			throws EntityNotFoundException;

	public List<CandidateTestSummary> getCandidatesTestSummary(int limit, int pageNumber, String searchTestCode,
			String searchTestName)

			throws EntityNotFoundException;

	public int deleteTestCode(Date currentTime);

	public int createTestSectionMapping(List<TestSection> listTestSection, int testId) throws FailedToPersistException;

	public int updateResumeCandidateTest(int canId, int testId, boolean testCompleted, String upadtedBy)
			throws FailedToPersistException;

}
