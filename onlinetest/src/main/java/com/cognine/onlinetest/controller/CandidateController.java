package com.cognine.onlinetest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognine.onlinetest.model.Candidate;
import com.cognine.onlinetest.service.CandidateService;
import com.cognine.onlinetest.utils.exception.EntityNotFoundException;
import com.cognine.onlinetest.utils.exception.FailedToPersistException;

@RestController
public class CandidateController {

	@Autowired
	CandidateService candidateService;

	@PostMapping("/saveCandidate/{isInsert}")
	public int saveCandidate(@RequestBody Candidate candidate, @PathVariable("isInsert") boolean isInsert)
			throws FailedToPersistException {
		return candidateService.saveCandidate(candidate, isInsert);
	}

	@GetMapping("/getCandidateByEmailorId")
	public Candidate getCandidate(@RequestParam(value = "id", required = false) Integer canId,
			@RequestParam(value = "emailId", required = false) String emailId) throws EntityNotFoundException {
		return candidateService.getCandidateByEmailorId(canId, emailId);
	}

	@GetMapping("/getTypes/{type}")
	public List<String> fetchTypeValues(@PathVariable("type") String type) throws EntityNotFoundException {
		return candidateService.fetchTypeValues(type);
	}

}
