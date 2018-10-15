package com.cognine.onlinetest.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognine.onlinetest.dao.ExamDAO;
import com.cognine.onlinetest.dao.mapper.ExamMapper;
import com.cognine.onlinetest.model.Candidate;
import com.cognine.onlinetest.model.Question;
import com.cognine.onlinetest.model.Score;
import com.cognine.onlinetest.model.Test;
import com.cognine.onlinetest.model.TestSection;
import com.cognine.onlinetest.utils.exception.EntityNotFoundException;
import com.cognine.onlinetest.utils.exception.FailedToPersistException;

@Repository
public class ExamDAOImpl implements ExamDAO {

	@Autowired
	ExamMapper mapper;

	@Override
	public Test validateTestCode(String testCode) throws EntityNotFoundException {
		return mapper.validateTestCode(testCode);
	}

	@Override
	public List<TestSection> fetchTestSections(int testId) throws EntityNotFoundException {
		return mapper.fetchTestSections(testId);
	}

	@Override
	public List<Question> fetchTestQuestionsWithAnswers(int testId) throws EntityNotFoundException {
		return mapper.fetchTestQuestionsWithAnswers(testId);
	}

	@Override
	public int createCandidateTestSummaryRecord(int canId, int testId, String uUID, String testCode)
			throws FailedToPersistException {
		int returnVal = -1;
		try {
			returnVal = mapper.createCandidateTestSummaryRecord(canId, testId, uUID, testCode);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new FailedToPersistException(Test.class, "candidateId", canId + "", "testId", testId + "", "UUID",
					uUID);
		}
		return returnVal;
	}

	@Override
	public int updateCandidateSummary(int canId, int testId, String uUID, String endTest, int elapsedTimeinMins,
			float score, float maxScore, String sectionBreakup, String completionText, float lowScore,
			float mediumScore, float highScore, String testCode) throws FailedToPersistException {
		int returnVal = -1;
		try {
			returnVal = mapper.updateCandidateSummary(canId, testId, uUID, endTest, elapsedTimeinMins, score, maxScore,
					sectionBreakup, completionText, lowScore, mediumScore, highScore, testCode);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new FailedToPersistException(Test.class, "candidateId", canId + "", "testId", testId + "", "UUID",
					uUID);
		}
		return returnVal;
	}

	@Override
	public int validateCandidateforResume(int canId, int testId, String testCode) {
		return mapper.validateCandidateforResume(canId, testId, testCode);
	}

	@Override
	public int updateCandidateUUIDforResume(String uuid, int canId, int testId, String testCode)
			throws FailedToPersistException {
		int returnVal = -1;
		try {
			returnVal = mapper.updateCandidateUUIDforResume(uuid, canId, testId, testCode);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new FailedToPersistException(Test.class, "candidateId", canId + "", "testId", testId + "", "UUID",
					uuid);
		}
		return returnVal;
	}

	@Override
	public List<Question> fetchResumeQuestions(int testId, int canId, String testCode) throws EntityNotFoundException {
		return mapper.fetchResumeQuestions(testId, canId, testCode);
	}

	@Override
	public int validateCandidatewithUUID(int canId, int testId, String uuid, String testCode) {
		return mapper.validateCandidatewithUUID(canId, testId, uuid, testCode);
	}

	@Override
	public int insertCandidateAnswer(int canId, int testId, int questionId, int selectedAnswer, boolean isAnswerValid,
			String testCode) throws FailedToPersistException {
		int returnVal = -1;
		try {
			returnVal = mapper.insertCandidateAnswer(canId, testId, questionId, selectedAnswer, isAnswerValid,
					testCode);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new FailedToPersistException(Test.class, "candidateId", canId + "", "testId", testId + "",
					"questionId", questionId + "");
		}
		return returnVal;
	}

	@Override
	public int updateCandidateAnswer(int canId, int testId, int questionId, int selectedAnswer, boolean isAnswerValid,
			String testCode) throws FailedToPersistException {
		int returnVal = -1;
		try {
			returnVal = mapper.updateCandidateAnswer(canId, testId, questionId, selectedAnswer, isAnswerValid,
					testCode);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new FailedToPersistException(Test.class, "candidateId", canId + "", "testId", testId + "",
					"questionId", questionId + "");
		}
		return returnVal;
	}

	@Override
	public int getElapsedTime(int testId, int canId, String testCode) throws EntityNotFoundException {
		return mapper.getElapsedTime(testId, canId, testCode);
	}

	@Override
	public Map<String, Integer> fetchCandidateScoresperSection(int canId, int testId, String testCode) {
		Map<String, Integer> resultMap = new HashMap<String, Integer>();
		try {
			List<Score> resultList = mapper.fetchCandidateScoresperSection(canId, testId, testCode);
			for (Score score : resultList) {
				resultMap.put(score.getSectionName(), score.getScore());
			}
			return resultMap;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return resultMap;
	}

	@Override
	public Map<String, Integer> fetchCandidateScoresperComplexity(int canId, int testId, String testCode) {
		Map<String, Integer> resultMap = new HashMap<String, Integer>();
		try {
			List<Score> resultList = mapper.fetchCandidateScoresperComplexity(canId, testId, testCode);
			for (Score score : resultList) {
				resultMap.put(score.getComplexity(), score.getScore());
			}
			return resultMap;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return resultMap;
	}

	@Override
	public int fetchMaxScoreperTest(int testId) {
		try {
			return mapper.fetchMaxScoreperTest(testId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return 0;
	}

	@Override
	public Candidate validateCandidateEmail(String emailId) throws EntityNotFoundException {
		return mapper.validateCandidateEmail(emailId);
	}

	@Override
	public int clearAnswer(int canId, int testId, int questionId, String testCode) {
		return mapper.clearAnswer(canId, testId, questionId, testCode);
	}

}
