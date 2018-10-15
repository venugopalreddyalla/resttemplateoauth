package com.cognine.onlinetest.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cognine.onlinetest.model.Candidate;
import com.cognine.onlinetest.utils.exception.EntityNotFoundException;
import com.cognine.onlinetest.utils.exception.FailedToPersistException;

public interface CandidateService {

	public static final Logger logger = LogManager.getLogger(CandidateService.class);

	public int saveCandidate(Candidate candidate, boolean isInsert) throws FailedToPersistException;

	public Candidate getCandidateByEmailorId(Integer canId, String emailId) throws EntityNotFoundException;

	public List<String> fetchTypeValues(String type) throws EntityNotFoundException;

}
