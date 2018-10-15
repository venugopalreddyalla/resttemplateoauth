package com.cognine.onlinetest.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cognine.onlinetest.model.Question;
import com.cognine.onlinetest.service.QuestionService;
import com.cognine.onlinetest.utils.exception.EntityNotFoundException;
import com.cognine.onlinetest.utils.exception.FailedToPersistException;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class QuestionController {
	@Autowired
	QuestionService questionService;

	@PostMapping("/createQuestion")
	public int createQuestion(@RequestBody Question question) throws FailedToPersistException {
		question.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		return questionService.createQuestion(question);
	}

	@PostMapping("/updateQuestion")
	public int updateQuestion(@RequestBody Question question) throws FailedToPersistException {

		question.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());

		return questionService.updateQuestion(question);
	}

	@GetMapping("/getQuestions")
	public List<Question> getQuestions(@RequestParam(value = "pageNumber") int pageNumber,
			@RequestParam(value = "noOfRows") int noOfRows,
			@RequestParam(name = "searchQuestionName", required = false) String searchQuestionName)
			throws EntityNotFoundException {
		return questionService.getQuestions(pageNumber, noOfRows, searchQuestionName);
	}

	@PostMapping(value = "/createQuestions")
	public String createQuestionsByExcel(@RequestParam("file") MultipartFile file) throws IOException, FailedToPersistException, EntityNotFoundException {	
		String updatedBy = SecurityContextHolder.getContext().getAuthentication().getName(); 		
		InputStream inputStream = file.getInputStream();
		return questionService.createQuestionsByExcel(inputStream, updatedBy);

	}

}
