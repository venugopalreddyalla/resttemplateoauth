package com.cognine.onlinetest.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognine.onlinetest.dao.TestDAO;
import com.cognine.onlinetest.model.CandidateTestSummary;
import com.cognine.onlinetest.model.Test;
import com.cognine.onlinetest.model.TestCode;
import com.cognine.onlinetest.model.TestSection;
import com.cognine.onlinetest.service.QuestionService;
import com.cognine.onlinetest.service.TestService;
import com.cognine.onlinetest.utils.exception.EntityNotFoundException;
import com.cognine.onlinetest.utils.exception.FailedToPersistException;

@Service
public class TestServiceImpl implements TestService {
	@Autowired
	TestDAO testDAO;

	@Autowired
	QuestionService questionService;

	@Override
	@Transactional
	public int createTest(Test test) throws EntityNotFoundException, FailedToPersistException {

		int createTest = testDAO.createTest(test);
		int testId = test.getTestId();

		List<TestSection> sectionList = test.getSectionList();
		for (TestSection testSection : sectionList) {
			testSection.setUpdatedBy(test.getUpdatedBy());
		}
		testDAO.createTestSectionMapping(sectionList, testId);
		questionService.getQuestionsForSectionMapping(sectionList, testId);
		return createTest;
	}

	@Override
	public int updateTest(Test test) throws FailedToPersistException {
		return testDAO.updateTest(test);
	}

	@Override
	public List<Test> getTests(int pageNumber, int noOfRows, String searchTestName) throws EntityNotFoundException {
		return testDAO.getTests(noOfRows, (pageNumber != 1 ? (--pageNumber * noOfRows) : --pageNumber), searchTestName);
	}

	@Override
	public int createTestCode(TestCode testCode) throws FailedToPersistException {
		testCode.setTestCode(RandomStringUtils.randomAlphanumeric(8, 9).toUpperCase());
		return testDAO.createTestcode(testCode);
	}

	@Override
	public List<TestCode> getTestCodeDetails(int pageNumber, int noOfRows, String searchTestName)
			throws EntityNotFoundException {
		return testDAO.getTestCodeDetails(noOfRows, (pageNumber != 1 ? (--pageNumber * noOfRows) : --pageNumber),
				searchTestName);
	}

	@Override
	public int updateTestCodeDetails(TestCode testCode) throws FailedToPersistException {
		return testDAO.updateTestCodeDetails(testCode);
	}

	@Scheduled(cron = "0 0/2 * * * ?")
	public int deleteTestCode() {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		return testDAO.deleteTestCode(ts);
	}

	@Override
	public List<CandidateTestSummary> getCandidatesTestSummary(int pageNumber, int noOfRows, String searchTestCode,
			String searchTestName) throws EntityNotFoundException {
		return testDAO.getCandidatesTestSummary(noOfRows, pageNumber, searchTestCode, searchTestName);
	}

	@Override
	public int updateResumeCandidateTest(Map paramMap, String upadtedBy) throws FailedToPersistException {
		int canId = (int) paramMap.get("canId");
		int testId = (int) paramMap.get("testId");
		boolean testCompleted = (boolean) paramMap.get("testCompleted");

		return testDAO.updateResumeCandidateTest(canId, testId, testCompleted, upadtedBy);
	}

}
