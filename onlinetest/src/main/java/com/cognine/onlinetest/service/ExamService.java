package com.cognine.onlinetest.service;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cognine.onlinetest.model.Question;
import com.cognine.onlinetest.model.TestCandidate;
import com.cognine.onlinetest.model.TestSection;
import com.cognine.onlinetest.utils.exception.EntityNotFoundException;
import com.cognine.onlinetest.utils.exception.FailedToPersistException;
import com.cognine.onlinetest.utils.exception.InvalidUserException;

public interface ExamService {

	public static final Logger logger = LogManager.getLogger(ExamService.class);

	public TestCandidate validateEnteredDetails(String testCode, String emailId)
			throws EntityNotFoundException, InvalidUserException;

	public List<TestSection> fetchTestSections(int testId) throws EntityNotFoundException;

	public int updateCandidateSummary(Map paramMap) throws FailedToPersistException;

	public Map<Integer, Question> startCandidateTest(Map paramMap)
			throws EntityNotFoundException, FailedToPersistException;

	public Map<Integer, Question> resumeCandidateTest(Map paramMap)
			throws EntityNotFoundException, FailedToPersistException, InvalidUserException;

	public int saveCandidateAnswer(Map paramMap)
			throws FailedToPersistException, InvalidUserException, EntityNotFoundException;

	public int endCandidateTest(Map paramMap)
			throws FailedToPersistException, InvalidUserException, EntityNotFoundException;

	public int getElapsedTime(int testId, int canId, String testCode) throws EntityNotFoundException;

	public int clearAnswer(Map paramMap);
}
