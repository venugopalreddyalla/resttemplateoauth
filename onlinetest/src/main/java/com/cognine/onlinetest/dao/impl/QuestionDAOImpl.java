package com.cognine.onlinetest.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognine.onlinetest.dao.QuestionDAO;
import com.cognine.onlinetest.dao.mapper.QuestionMapper;
import com.cognine.onlinetest.model.Question;
import com.cognine.onlinetest.utils.exception.EntityNotFoundException;
import com.cognine.onlinetest.utils.exception.FailedToPersistException;

@Repository
public class QuestionDAOImpl implements QuestionDAO {
	@Autowired
	QuestionMapper questionMapper;

	@Override
	public int createQuestion(Question question) throws FailedToPersistException {
		int questionId = -1;
		try {
			questionMapper.createQuestion(question);
			questionId = question.getQuestionId();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new FailedToPersistException(Question.class, "sectiondId", question.getSectionId() + "",
					"questiontext", question.getQuestionText());
		}
		return questionId;
	}
	
	@Override
	public int updateQuestion(Question question) throws FailedToPersistException {
		int questionId = -1;
		try {
			questionMapper.updateQuestion(question);
			questionId = question.getQuestionId();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new FailedToPersistException(Question.class, "questionid", question.getQuestionId() + "",
					"sectiondId", question.getSectionId() + "");
		}
		return questionId;
	}

	@Override
	public List<Question> getQuestions(int limit, int pageNumber, String searchQuestionName) {
		return questionMapper.getQuestions(pageNumber, limit, searchQuestionName);
	}

	@Override
	public List<Question> getQuestionsForSectionMapping(int sectionId, String questionPriority, int limit) {
		return questionMapper.getQuestionsForSectionMapping(sectionId, questionPriority, limit);
	}

	@Override
	public int createTestQuestionMapping(int testId, List<Question> listOfQuestions,String updatedBy) throws FailedToPersistException {
		int questionId = -1;
		try {
			questionId = questionMapper.createTestQuestionMapping(testId, listOfQuestions,updatedBy);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new FailedToPersistException(Question.class, "questionid",
					"Creations Of TestSectionMapping Is Failed");
		}
		return questionId;
	}

	@Override
	public Integer questionIdBySectionIdQuestionText(int sectionId, String questionText) throws EntityNotFoundException {	
		Integer questionId = -1;
		try {
			questionId =questionMapper.questionIdBySectionIdQuestionText(sectionId, questionText);
			if(questionId==null)
				return questionId;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new EntityNotFoundException(Question.class, "Questionid Is Not Found.");
		}
		return questionId;
	}

}
