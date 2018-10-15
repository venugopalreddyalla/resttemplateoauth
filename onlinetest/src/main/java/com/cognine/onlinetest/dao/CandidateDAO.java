package com.cognine.onlinetest.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cognine.onlinetest.model.Candidate;
import com.cognine.onlinetest.utils.exception.EntityNotFoundException;
import com.cognine.onlinetest.utils.exception.FailedToPersistException;

public interface CandidateDAO {

	public static final Logger logger = LogManager.getLogger(CandidateDAO.class);

	public int createCandidate(Candidate candidate) throws FailedToPersistException;

	public Candidate getCandidateByEmailorId(int canId, String emailId) throws EntityNotFoundException;

	public List<String> fetchTypeValues(String type) throws EntityNotFoundException;

	public int updateCandidate(Candidate candidate) throws FailedToPersistException;
}
