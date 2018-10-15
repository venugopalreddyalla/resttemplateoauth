package com.cognine.onlinetest.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cognine.onlinetest.dao.ExamDAO;
import com.cognine.onlinetest.model.Candidate;
import com.cognine.onlinetest.model.Question;
import com.cognine.onlinetest.model.Test;
import com.cognine.onlinetest.model.TestCandidate;
import com.cognine.onlinetest.model.TestSection;
import com.cognine.onlinetest.service.CandidateService;
import com.cognine.onlinetest.service.ExamService;
import com.cognine.onlinetest.utils.exception.EntityNotFoundException;
import com.cognine.onlinetest.utils.exception.FailedToPersistException;
import com.cognine.onlinetest.utils.exception.InvalidUserException;
import com.google.gson.Gson;

@Service
public class ExamServiceImpl implements ExamService {

	@Autowired
	ExamDAO testDAO;
	@Autowired
	CandidateService candidateService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public TestCandidate validateEnteredDetails(String testCode, String emailId)
			throws EntityNotFoundException, InvalidUserException {
		TestCandidate testCandidate = new TestCandidate();
		Test testObj = testDAO.validateTestCode(testCode);
		if (testObj == null) {
			throw new EntityNotFoundException(Test.class, "code", testCode);
		} else {
			Candidate candidate = validateCandidateEmail(emailId, testObj.getTestId(), testCode);
			testCandidate.setTest(testObj);
			testCandidate.setCandidate(candidate);
		}
		return testCandidate;
	}

	@Override
	public List<TestSection> fetchTestSections(int testId) throws EntityNotFoundException {
		List<TestSection> testSectionList = testDAO.fetchTestSections(testId);
		if (testSectionList == null || (testSectionList != null && testSectionList.size() == 0)) {
			throw new EntityNotFoundException(TestSection.class, "test id", testId + "");
		}
		return testSectionList;
	}

	@Cacheable(value = "testQuestions", key = "{#testId}")
	private Map<Integer, Question> fetchTestQuestions(int testId) throws EntityNotFoundException {
		Map<Integer, Question> tempQuestionsMap = fetchTestQuestionsWithAnswers(testId);
		Map<Integer, Question> questionsMap = new LinkedHashMap<Integer, Question>();
		try {
			for (Entry<Integer, Question> questionSet : tempQuestionsMap.entrySet()) {
				Question finalQuestion = (Question) questionSet.getValue().clone();
				finalQuestion.setAnswer(-1);
				questionsMap.put(finalQuestion.getQuestionId(), finalQuestion);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return questionsMap;
	}

	@Cacheable(value = "testQuestionsWithAnswers", key = "{#testId}")
	private Map<Integer, Question> fetchTestQuestionsWithAnswers(int testId) throws EntityNotFoundException {
		List<Question> testQuestionList = testDAO.fetchTestQuestionsWithAnswers(testId);
		Map<Integer, Question> questionsMap = new LinkedHashMap<Integer, Question>();
		if (testQuestionList == null || (testQuestionList != null && testQuestionList.size() == 0)) {
			throw new EntityNotFoundException(Question.class, "test id", testId + "");
		} else {
			for (Question question : testQuestionList) {
				questionsMap.put(question.getQuestionId(), question);
			}
		}
		return questionsMap;
	}

	private int createCandidateTestSummaryRecord(Map paramMap) throws FailedToPersistException {
		int result = -1;
		try {
			int canId = (int) paramMap.get("canId");
			int testId = (int) paramMap.get("testId");
			String uUID = (String) paramMap.get("uuid");
			String testCode = (String) paramMap.get("testCode");
			result = testDAO.createCandidateTestSummaryRecord(canId, testId, uUID, testCode);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new FailedToPersistException(Test.class, "params", paramMap.toString());
		}
		return result;
	}

	@Override
	public int updateCandidateSummary(Map paramMap) throws FailedToPersistException {
		int result = -1;
		try {
			int canId = (int) paramMap.get("canId");
			int testId = (int) paramMap.get("testId");
			String uUID = (String) paramMap.get("uuid");
			String testCode = (String) paramMap.get("testCode");

			String endTest = (paramMap.containsKey("endTest") ? (String) paramMap.get("endTest") : null);
			int elapsedTimeinMins = (paramMap.containsKey("elapsedTimeinMins") ? (int) paramMap.get("elapsedTimeinMins")
					: -1);
			int score = (paramMap.containsKey("score") ? (int) paramMap.get("score") : -1);
			int maxScore = (paramMap.containsKey("maxScore") ? (int) paramMap.get("maxScore") : -1);
			String sectionBreakup = (paramMap.containsKey("sectionBreakup") ? (String) paramMap.get("sectionBreakup")
					: null);
			String completionText = (paramMap.containsKey("completionText") ? (String) paramMap.get("completionText")
					: null);
			int lowScore = (paramMap.containsKey("lowScore") ? (int) paramMap.get("lowScore") : -1);
			int mediumScore = (paramMap.containsKey("mediumScore") ? (int) paramMap.get("mediumScore") : -1);
			int highScore = (paramMap.containsKey("highScore") ? (int) paramMap.get("highScore") : -1);

			result = testDAO.updateCandidateSummary(canId, testId, uUID, endTest, elapsedTimeinMins, score, maxScore,
					sectionBreakup, completionText, lowScore, mediumScore, highScore, testCode);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new FailedToPersistException(Test.class, "", "");
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Map<Integer, Question> startCandidateTest(Map paramMap)
			throws EntityNotFoundException, FailedToPersistException {
		try {
			if (createCandidateTestSummaryRecord(paramMap) >= 0) {
				int testId = (int) paramMap.get("testId");
				return fetchTestQuestions(testId);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new FailedToPersistException(Test.class, "params", paramMap.toString());
		}
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Map<Integer, Question> resumeCandidateTest(Map paramMap)
			throws EntityNotFoundException, FailedToPersistException, InvalidUserException {
		try {
			int canId = (int) paramMap.get("canId");
			int testId = (int) paramMap.get("testId");
			String uUID = (String) paramMap.get("uuid");
			String testCode = (String) paramMap.get("testCode");

			// check if the user is allowed to resume test and update uuid and
			// isTestResumedflag
			if (validateCandidateforResume(canId, testId, testCode) == 1
					&& updateCandidateUUIDforResume(uUID, canId, testId, testCode) == 1) {
				return fetchResumeQuestions(testId, canId, testCode);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		return null;
	}

	private int validateCandidateforResume(int canId, int testId, String testCode) throws InvalidUserException {
		int returnVal = testDAO.validateCandidateforResume(canId, testId, testCode);
		if (returnVal <= 0) {
			throw new InvalidUserException(Candidate.class, "canId", canId + "", "testId", testId + "");
		}
		return returnVal;
	}

	private int updateCandidateUUIDforResume(String uuid, int canId, int testId, String testCode)
			throws FailedToPersistException {
		return testDAO.updateCandidateUUIDforResume(uuid, canId, testId, testCode);
	}

	private Map<Integer, Question> fetchResumeQuestions(int testId, int canId, String testCode)
			throws EntityNotFoundException {
		List<Question> testQuestionList = testDAO.fetchResumeQuestions(testId, canId, testCode);
		Map<Integer, Question> questionsMap = new LinkedHashMap<Integer, Question>();
		if (testQuestionList == null || (testQuestionList != null && testQuestionList.size() == 0)) {
			throw new EntityNotFoundException(Question.class, "test id", testId + "");
		} else {
			for (Question question : testQuestionList) {
				questionsMap.put(question.getQuestionId(), question);
			}
		}
		return questionsMap;
	}

	private int validateCandidatewithUUID(int canId, int testId, String uuid, String testCode) {
		return testDAO.validateCandidatewithUUID(canId, testId, uuid, testCode);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int saveCandidateAnswer(Map paramMap)
			throws FailedToPersistException, InvalidUserException, EntityNotFoundException {
		int result = -1;
		try {
			int canId = (int) paramMap.get("canId");
			int testId = (int) paramMap.get("testId");
			String uUID = (String) paramMap.get("uuid");
			int questionId = (int) paramMap.get("questionId");
			int selectedAnswer = (int) paramMap.get("selectedAnswer");
			boolean answeredEarlier = (boolean) paramMap.get("answeredEarlier");
			String testCode = (String) paramMap.get("testCode");

			// check if the user is valid by checking their UUID
			if (validateCandidatewithUUID(canId, testId, uUID, testCode) == 1) {
				boolean isAnswerValid = validateSelectedAnswer(testId, questionId, selectedAnswer);
				if (answeredEarlier) {
					result = testDAO.updateCandidateAnswer(canId, testId, questionId, selectedAnswer, isAnswerValid,
							testCode);
				} else {
					result = testDAO.insertCandidateAnswer(canId, testId, questionId, selectedAnswer, isAnswerValid,
							testCode);
				}

				if (result <= 0) {
					throw new FailedToPersistException(Test.class, "params", paramMap.toString());
				}

			} else {
				throw new InvalidUserException(Candidate.class, "params", paramMap.toString());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		return result;
	}

	private boolean validateSelectedAnswer(int testId, int questionId, int selectedAnswer)
			throws EntityNotFoundException {
		Map<Integer, Question> questionsMap = fetchTestQuestionsWithAnswers(testId);
		return (questionsMap.get(questionId).getAnswer() == selectedAnswer);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int endCandidateTest(Map paramMap)
			throws FailedToPersistException, InvalidUserException, EntityNotFoundException {
		int result = -1;
		try {
			int canId = (int) paramMap.get("canId");
			int testId = (int) paramMap.get("testId");
			String uUID = (String) paramMap.get("uuid");
			String testCode = (String) paramMap.get("testCode");

			// check if the user is valid by checking their UUID
			if (validateCandidatewithUUID(canId, testId, uUID, testCode) == 1) {
				// TODO get testcompletionreason from paramMap and call updateCandidateSummary
				Map scoreMap = calculateScore(testId, canId, testCode);
				scoreMap.put("endTest", "Y");
				scoreMap.putAll(paramMap);
				result = updateCandidateSummary(scoreMap);
			} else {
				throw new InvalidUserException(Candidate.class, "params", paramMap.toString());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		return result;
	}

	@Override
	public int getElapsedTime(int testId, int canId, String testCode) throws EntityNotFoundException {
		int elapsedTime = testDAO.getElapsedTime(testId, canId, testCode);
		if (elapsedTime < 0) {
			throw new EntityNotFoundException(Candidate.class, "testId", testId + "", "canId", canId + "");
		}
		return elapsedTime;
	}

	@Cacheable(value = "maxscorePerTest", key = "{#testId}")
	private int fetchMaxScoreperTest(int testId) {
		return testDAO.fetchMaxScoreperTest(testId);
	}

	private Map calculateScore(int testId, int canId, String testCode) throws EntityNotFoundException {
		Map scoreMap = new HashMap<>();

		Map<Integer, Question> questions = fetchTestQuestionsWithAnswers(testId);
		Map<String, Integer> sectionScores = testDAO.fetchCandidateScoresperSection(canId, testId, testCode);
		Map<String, Integer> complexityScores = testDAO.fetchCandidateScoresperComplexity(canId, testId, testCode);
		int maxScoreforTest = fetchMaxScoreperTest(testId);
		String sectionScoresText = new Gson().toJson(sectionScores);
		int lowScore = (complexityScores.containsKey("1-Low") ? complexityScores.get("1-Low") : 0);
		int medScore = (complexityScores.containsKey("2-Medium") ? complexityScores.get("2-Medium") : 0);
		int highScore = (complexityScores.containsKey("3-High") ? complexityScores.get("3-High") : 0);

		scoreMap.put("score", lowScore + medScore + highScore);
		scoreMap.put("maxScore", maxScoreforTest);
		scoreMap.put("lowScore", lowScore);
		scoreMap.put("mediumScore", medScore);
		scoreMap.put("highScore", highScore);
		scoreMap.put("sectionBreakup", sectionScoresText);

		return scoreMap;

	}

	private Candidate validateCandidateEmail(String emailId, int testId, String testCode)
			throws EntityNotFoundException, InvalidUserException {
		Candidate candidate = testDAO.validateCandidateEmail(emailId);
		if (candidate != null) {
			if (validateCandidateforResume(candidate.getCanId(), testId, testCode) == 1) {
				candidate = candidateService.getCandidateByEmailorId(null, emailId);
				candidate.setResumeTest(true);
			}
		} else {
			try {
				candidate = candidateService.getCandidateByEmailorId(null, emailId);
			} catch (Exception e) {
				logger.info(e.getMessage());
			}
		}
		return candidate;
	}

	@Override
	public int clearAnswer(Map paramMap) {
		int returnVal = -1;
		try {
			int canId = (int) paramMap.get("canId");
			int testId = (int) paramMap.get("testId");
			int questionId = (int) paramMap.get("questionId");
			String testCode = (String) paramMap.get("testCode");

			returnVal = testDAO.clearAnswer(canId, testId, questionId, testCode);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return returnVal;
	}

}
