package com.cognine.onlinetest.service;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cognine.onlinetest.model.CandidateTestSummary;
import com.cognine.onlinetest.model.Test;
import com.cognine.onlinetest.model.TestCode;
import com.cognine.onlinetest.utils.exception.EntityNotFoundException;
import com.cognine.onlinetest.utils.exception.FailedToPersistException;

public interface TestService {
	public static final Logger logger = LogManager.getLogger(TestService.class);

	public int createTest(Test test) throws FailedToPersistException, EntityNotFoundException;

	public int updateTest(Test test) throws FailedToPersistException;;

	public List<Test> getTests(int pageNumber, int noOfRows, String searchTestName) throws EntityNotFoundException;

	public int createTestCode(TestCode testCode) throws FailedToPersistException;

	public int updateTestCodeDetails(TestCode testcode) throws FailedToPersistException;

	public List<TestCode> getTestCodeDetails(int pageNumber, int noOfRows, String searchTestName)

			throws EntityNotFoundException;

	public List<CandidateTestSummary> getCandidatesTestSummary(int pageNumber, int noOfRows, String searchTestCode,
			String searchTestName)

			throws EntityNotFoundException;

	public int updateResumeCandidateTest(Map paramMap, String upadtedBy) throws FailedToPersistException;

}
