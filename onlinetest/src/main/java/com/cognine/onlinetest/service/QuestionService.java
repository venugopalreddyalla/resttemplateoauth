package com.cognine.onlinetest.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.cognine.onlinetest.model.Question;
import com.cognine.onlinetest.model.TestSection;
import com.cognine.onlinetest.utils.exception.EntityNotFoundException;
import com.cognine.onlinetest.utils.exception.FailedToPersistException;

public interface QuestionService {
	public static final Logger logger = LogManager.getLogger(QuestionService.class);

	public int createQuestion(Question question) throws FailedToPersistException;
	
	public String createQuestionsByExcel(InputStream inputStream,String  updatedBy) throws FailedToPersistException,IOException, EntityNotFoundException;

	public int updateQuestion(Question question) throws FailedToPersistException;

	public List<Question> getQuestions(int pageNumber, int noOfRows, String searchQuestionName)
			throws EntityNotFoundException;
	
	public void getQuestionsForSectionMapping(List<TestSection> sectionList,int testId)
			throws EntityNotFoundException, FailedToPersistException;

	public int createTestQuestionMapping(int testId, List<Question> listOfQuestions,String updatedBy)throws FailedToPersistException;

}
