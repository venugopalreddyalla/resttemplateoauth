package com.cognine.onlinetest.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cognine.onlinetest.model.Question;
import com.cognine.onlinetest.utils.exception.EntityNotFoundException;
import com.cognine.onlinetest.utils.exception.FailedToPersistException;

public interface QuestionDAO {
	public static final Logger logger = LogManager.getLogger(QuestionDAO.class);

	public int createQuestion(Question question) throws FailedToPersistException;

	public int updateQuestion(Question question) throws FailedToPersistException;

	public List<Question> getQuestions(int limit, int pageNumber, String searchQuestionName)
			throws EntityNotFoundException;

	public List<Question> getQuestionsForSectionMapping(int sectionId, String questionPriority, int limit)
			throws EntityNotFoundException;

	public int createTestQuestionMapping(int testId, List<Question> listOfQuestions,String updatedBy) throws FailedToPersistException;
	
	public Integer questionIdBySectionIdQuestionText(int sectionId,String questionText)throws EntityNotFoundException;

}
