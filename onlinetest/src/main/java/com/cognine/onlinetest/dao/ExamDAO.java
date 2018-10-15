package com.cognine.onlinetest.dao;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cognine.onlinetest.model.Candidate;
import com.cognine.onlinetest.model.Question;
import com.cognine.onlinetest.model.Test;
import com.cognine.onlinetest.model.TestSection;
import com.cognine.onlinetest.utils.exception.EntityNotFoundException;
import com.cognine.onlinetest.utils.exception.FailedToPersistException;

public interface ExamDAO {

	public static final Logger logger = LogManager.getLogger(ExamDAO.class);

	public Test validateTestCode(String testCode) throws EntityNotFoundException;

	public List<TestSection> fetchTestSections(int testId) throws EntityNotFoundException;

	public List<Question> fetchTestQuestionsWithAnswers(int testId) throws EntityNotFoundException;

	public int createCandidateTestSummaryRecord(int canId, int testId, String uUID, String testCode)
			throws FailedToPersistException;

	public int updateCandidateSummary(int canId, int testId, String uUID, String endTest, int elapsedTimeinMins,
			float score, float maxScore, String sectionBreakup, String completionText, float lowScore,
			float mediumScore, float highScore, String testCode) throws FailedToPersistException;

	public int validateCandidateforResume(int canId, int testId, String testCode);

	public int updateCandidateUUIDforResume(String uuid, int canId, int testId, String testCode)
			throws FailedToPersistException;

	public List<Question> fetchResumeQuestions(int testId, int canId, String testCode) throws EntityNotFoundException;

	public int validateCandidatewithUUID(int canId, int testId, String uuid, String testCode);

	public int insertCandidateAnswer(int canId, int testId, int questionId, int selectedAnswer, boolean isAnswerValid,
			String testCode) throws FailedToPersistException;

	public int updateCandidateAnswer(int canId, int testId, int questionId, int selectedAnswer, boolean isAnswerValid,
			String testCode) throws FailedToPersistException;

	public int getElapsedTime(int testId, int canId, String testCode) throws EntityNotFoundException;

	public Map<String, Integer> fetchCandidateScoresperSection(int canId, int testId, String testCode);

	public Map<String, Integer> fetchCandidateScoresperComplexity(int canId, int testId, String testCode);

	public int fetchMaxScoreperTest(int testId);

	public Candidate validateCandidateEmail(String emailId) throws EntityNotFoundException;

	public int clearAnswer(int canId, int testId, int questionId, String testCode);
}
