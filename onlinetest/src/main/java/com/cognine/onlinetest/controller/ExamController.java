package com.cognine.onlinetest.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognine.onlinetest.model.Question;
import com.cognine.onlinetest.model.TestCandidate;
import com.cognine.onlinetest.model.TestSection;
import com.cognine.onlinetest.service.ExamService;
import com.cognine.onlinetest.utils.exception.EntityNotFoundException;
import com.cognine.onlinetest.utils.exception.FailedToPersistException;
import com.cognine.onlinetest.utils.exception.InvalidUserException;

@RestController
public class ExamController {

	@Autowired
	ExamService testService;

	@GetMapping("/validateDetails")
	public TestCandidate validateEnteredDetails(@RequestParam("testCode") String testCode,
			@RequestParam("emailId") String emailId) throws EntityNotFoundException, InvalidUserException {
		return testService.validateEnteredDetails(testCode, emailId);
	}

	@GetMapping("/getSections/{testId}")
	public List<TestSection> fetchTestSections(@PathVariable("testId") int testId) throws EntityNotFoundException {
		return testService.fetchTestSections(testId);
	}

	@GetMapping("/getElapsedTime")
	public int getElapsedTime(@RequestParam("testId") int testId, @RequestParam("canId") int canId,
			@RequestParam("testCode") String testCode) throws EntityNotFoundException {
		return testService.getElapsedTime(testId, canId, testCode);
	}

	@PostMapping("/updateSummary")
	public int updateCandidateSummary(@RequestBody Map paramMap) throws FailedToPersistException {
		return testService.updateCandidateSummary(paramMap);
	}

	@PostMapping("/startTest")
	public Map<Integer, Question> startCandidateTest(@RequestBody Map paramMap)
			throws EntityNotFoundException, FailedToPersistException {
		return testService.startCandidateTest(paramMap);
	}

	@PostMapping("/resumeTest")
	public Map<Integer, Question> resumeCandidateTest(@RequestBody Map paramMap)
			throws EntityNotFoundException, FailedToPersistException, InvalidUserException {
		return testService.resumeCandidateTest(paramMap);
	}

	@PostMapping("/saveAnswer")
	public int saveCandidateAnswer(@RequestBody Map paramMap)
			throws InvalidUserException, FailedToPersistException, EntityNotFoundException {
		return testService.saveCandidateAnswer(paramMap);
	}

	@PostMapping("/endTest")
	public int endCandidateTest(@RequestBody Map paramMap)
			throws InvalidUserException, FailedToPersistException, EntityNotFoundException {
		return testService.endCandidateTest(paramMap);
	}

	@PostMapping("/clearAnswer")
	public int clearAnswer(@RequestBody Map paramMap) {

		return testService.clearAnswer(paramMap);
	}

	@GetMapping("/certificateVerification")
	public String certificateVerification() {
		return "successs";

	}
}
