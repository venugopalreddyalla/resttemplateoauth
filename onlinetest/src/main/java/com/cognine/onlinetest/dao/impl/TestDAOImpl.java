package com.cognine.onlinetest.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognine.onlinetest.dao.TestDAO;
import com.cognine.onlinetest.dao.mapper.TestMapper;
import com.cognine.onlinetest.model.CandidateTestSummary;
import com.cognine.onlinetest.model.Test;
import com.cognine.onlinetest.model.TestCode;
import com.cognine.onlinetest.model.TestSection;
import com.cognine.onlinetest.utils.exception.EntityNotFoundException;
import com.cognine.onlinetest.utils.exception.FailedToPersistException;

@Repository
public class TestDAOImpl implements TestDAO {
	@Autowired
	TestMapper mapper;

	@Override
	public int createTest(Test test) throws FailedToPersistException {
		int testId = -1;
		try {
			mapper.createTest(test);
			testId = test.getTestId();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new FailedToPersistException(Test.class, "testName", test.getTestName());
		}
		return testId;
	}

	@Override
	public int updateTest(Test test) throws FailedToPersistException {
		int testId = -1;
		try {
			mapper.updateTest(test);
			testId = test.getTestId();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new FailedToPersistException(Test.class, "testId", test.getTestId() + "", "testName",
					test.getTestName());
		}
		return testId;
	}

	@Override
	public List<Test> getTests(int limit, int pageNumber, String searchTestName) throws EntityNotFoundException {

		return mapper.getTests(limit, pageNumber, searchTestName);
	}

	@Override
	public int createTestcode(TestCode testCode) throws FailedToPersistException {
		int testId = -1;
		try {
			mapper.createTestCode(testCode);
			testId = testCode.getTestId();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new FailedToPersistException(TestCode.class, "testId", testCode.getTestId() + "");
		}
		return testId;
	}

	@Override
	public int updateTestCodeDetails(TestCode testCode) throws FailedToPersistException {
		int testId = -1;
		try {
			mapper.updateTestCodeDetails(testCode);
			testId = testCode.getTestId();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new FailedToPersistException(Test.class, "testId", testCode.getTestId() + "");
		}
		return testId;
	}

	@Override
	public List<TestCode> getTestCodeDetails(int limit, int pageNumber, String searchTestName)
			throws EntityNotFoundException {
		return mapper.getTestCodeDetails(limit, pageNumber, searchTestName);
	}

	@Override
	public int deleteTestCode(Date currentTime) {
		return mapper.deleteTestCode(currentTime);
	}

	@Override
	public int createTestSectionMapping(List<TestSection> listTestSection, int testId) throws FailedToPersistException {
		int rowsInserted;

		try {
			rowsInserted = mapper.createTestSectionMapping(listTestSection, testId);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new FailedToPersistException(TestSection.class, "ErrorInStoringTestSectionMapping");
		}
		return rowsInserted;
	}

	@Override
	public List<CandidateTestSummary> getCandidatesTestSummary(int limit, int pageNumber, String searchTestCode,
			String searchTestName) throws EntityNotFoundException {
		return mapper.getCandidatesTestSummary(limit, pageNumber, searchTestCode, searchTestName);
	}

	@Override
	public int updateResumeCandidateTest(int canId, int testId, boolean testCompleted, String upadtedBy)
			throws FailedToPersistException {

		return mapper.updateResumeCandidateTest(canId, testId, testCompleted, upadtedBy);
	}

}
