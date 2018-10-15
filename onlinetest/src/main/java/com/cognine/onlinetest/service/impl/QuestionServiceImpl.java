package com.cognine.onlinetest.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognine.onlinetest.dao.QuestionDAO;
import com.cognine.onlinetest.dao.SectionDAO;
import com.cognine.onlinetest.model.Question;
import com.cognine.onlinetest.model.Section;
import com.cognine.onlinetest.model.TestSection;
import com.cognine.onlinetest.service.QuestionService;
import com.cognine.onlinetest.utils.exception.EntityNotFoundException;
import com.cognine.onlinetest.utils.exception.FailedToPersistException;

@Service
public class QuestionServiceImpl implements QuestionService {
	@Autowired
	QuestionDAO questionDAO;

	@Autowired
	SectionDAO sectionDAO;

	@Override
	public int createQuestion(Question question) throws FailedToPersistException {
		return questionDAO.createQuestion(question);
	}

	@Override
	public String createQuestionsByExcel(InputStream inputStream, String updatedBy)
			throws FailedToPersistException, IOException, EntityNotFoundException {
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
		XSSFSheet dataSheet = xssfWorkbook.getSheetAt(0);
		List<Question> listOfQuestions = new ArrayList<Question>();
		for (int i = 1; i <= dataSheet.getLastRowNum(); i++) {
			Question question = new Question();
			XSSFRow row = dataSheet.getRow(i);
			for (int j = 0; j < row.getLastCellNum(); j++) {
				question.setUpdatedBy(updatedBy);
				XSSFCell cell = row.getCell(j);
				int cellType = cell.getCellType();
				int a = j + 1;
				switch (a) {
				case 1:
					question.setSectionName(cell.getStringCellValue());
					break;
				case 2:
					question.setQuestionText(cell.getStringCellValue());
					break;
				case 3:
					if (cellType == 1)
						question.setOption1(cell.getStringCellValue());
					else
						question.setOption1(((Double) cell.getNumericCellValue()).toString());
					break;
				case 4:
					if (cellType == 1)
						question.setOption2(cell.getStringCellValue());
					else
						question.setOption2(((Double) cell.getNumericCellValue()).toString());
					break;
				case 5:
					if (cellType == 1)
						question.setOption3(cell.getStringCellValue());
					else
						question.setOption3(((Double) cell.getNumericCellValue()).toString());
					break;
				case 6:
					if (cellType == 1)
						question.setOption4(cell.getStringCellValue());
					else
						question.setOption4(((Double) cell.getNumericCellValue()).toString());
					break;
				case 7:
					question.setAnswer((int) cell.getNumericCellValue());
					break;
				case 8:
					question.setComplexity(cell.getStringCellValue());
					break;
				default:
					break;
				}
			}
			listOfQuestions.add(question);
		}
		return insertQuestionsByUsingExcel(listOfQuestions);
	}

	@Transactional
	public String insertQuestionsByUsingExcel(List<Question> listOfQuestions)
			throws EntityNotFoundException, FailedToPersistException {
		for (Question question : listOfQuestions) {
			Integer sectionId = sectionDAO.getSectionIdBySectionName(question.getSectionName());
			question.setSectionId(sectionId);
			Integer questionId = questionDAO.questionIdBySectionIdQuestionText(sectionId, question.getQuestionText());
			if (sectionId <= 0 || sectionId == null) {
				Section section = new Section();
				section.setSectionName(question.getSectionName());
				section.setUpdatedBy(question.getUpdatedBy());
				question.setSectionId(sectionDAO.createSection(section));
				questionDAO.createQuestion(question);
			} else if (questionId != null) {
				question.setQuestionId(questionId);
				questionDAO.updateQuestion(question);
			} else {
				questionDAO.createQuestion(question);
			}
		}
		return "All Questions Are Inserted Successfully...";
	}

	@Override
	public int updateQuestion(Question question) throws FailedToPersistException {
		return questionDAO.updateQuestion(question);
	}

	@Override
	public List<Question> getQuestions(int pageNumber, int noOfRows, String searchQuestionName)
			throws EntityNotFoundException {
		return questionDAO.getQuestions(noOfRows, --pageNumber * noOfRows, searchQuestionName);
	}

	@Override
	public void getQuestionsForSectionMapping(List<TestSection> sectionList, int testId)
			throws EntityNotFoundException, FailedToPersistException {
		for (TestSection testSection : sectionList) {
			List<Question> questionsForSectionMapping1 = questionDAO
					.getQuestionsForSectionMapping(testSection.getSectionId(), "1-Low", testSection.getEasyNo());
			createTestQuestionMapping(testId, questionsForSectionMapping1, testSection.getUpdatedBy());
			List<Question> questionsForSectionMapping2 = questionDAO
					.getQuestionsForSectionMapping(testSection.getSectionId(), "2-Medium", testSection.getMediumNo());
			createTestQuestionMapping(testId, questionsForSectionMapping2, testSection.getUpdatedBy());
			List<Question> questionsForSectionMapping3 = questionDAO
					.getQuestionsForSectionMapping(testSection.getSectionId(), "3-High", testSection.getHardNo());
			createTestQuestionMapping(testId, questionsForSectionMapping3, testSection.getUpdatedBy());
		}

	}

	@Override
	public int createTestQuestionMapping(int testId, List<Question> listOfQuestions, String updatedBy)
			throws FailedToPersistException {
		return questionDAO.createTestQuestionMapping(testId, listOfQuestions, updatedBy);
	}

}
