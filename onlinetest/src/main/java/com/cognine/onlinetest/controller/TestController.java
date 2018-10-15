package com.cognine.onlinetest.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognine.onlinetest.model.CandidateTestSummary;
import com.cognine.onlinetest.model.Test;
import com.cognine.onlinetest.model.TestCode;
import com.cognine.onlinetest.service.TestService;
import com.cognine.onlinetest.utils.exception.EntityNotFoundException;
import com.cognine.onlinetest.utils.exception.FailedToPersistException;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class TestController {
	@Autowired
	TestService testService;

	@PostMapping("/createTest")
	public int createTest(@RequestBody Test test) throws FailedToPersistException, EntityNotFoundException {
		test.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		return testService.createTest(test);
	}

	@PostMapping("/updateTest")
	public int updateTest(@RequestBody Test test) throws FailedToPersistException {
		test.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		return testService.updateTest(test);
	}

	@GetMapping("/getTests")
	public List<Test> getTests(@RequestParam(value = "pageNumber") int pageNumber,
			@RequestParam(value = "noOfRows") int noOfRows,
			@RequestParam(name = "searchTestName", required = false) String searchTestName)
			throws EntityNotFoundException {
		return testService.getTests(pageNumber, noOfRows, searchTestName);
	}

	@PostMapping("/createTestCode")
	public int createTestCode(@RequestBody TestCode testCode) throws FailedToPersistException {
		testCode.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		return testService.createTestCode(testCode);
	}

	@PostMapping("/updateTestCodeInformation")
	public int updateTestCodeDetails(@RequestBody TestCode testCode) throws FailedToPersistException {
		testCode.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		return testService.updateTestCodeDetails(testCode);
	}

	@GetMapping("/getTestCodeInformations")
	public List<TestCode> getTestCodeDetails(@RequestParam(value = "pageNumber") int pageNumber,
			@RequestParam(value = "noOfRows") int noOfRows,
			@RequestParam(name = "searchTestName", required = false) String searchTestName)
			throws EntityNotFoundException {
		return testService.getTestCodeDetails(pageNumber, noOfRows, searchTestName);
	}

	@GetMapping("/getCandidatesTestSummary")
	public List<CandidateTestSummary> getCandidatesTestSummary(@RequestParam(value = "pageNumber") int pageNumber,
			@RequestParam(value = "noOfRows") int noOfRows,
			@RequestParam(name = "searchTestCode", required = false) String searchTestCode,
			@RequestParam(name = "searchTestName", required = false) String searchTestName)
			throws EntityNotFoundException {
		return testService.getCandidatesTestSummary(pageNumber, noOfRows, searchTestCode, searchTestName);

	}

	@PostMapping("/updateResumeCandidateTest")
	public int updateResumeCandidateTest(@RequestBody Map paramMap) throws FailedToPersistException {
		String upadtedBy = SecurityContextHolder.getContext().getAuthentication().getName();
		return testService.updateResumeCandidateTest(paramMap, upadtedBy);

	}
}
