package com.cognine.onlinetest.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognine.onlinetest.dao.CandidateDAO;
import com.cognine.onlinetest.dao.mapper.CandidateMapper;
import com.cognine.onlinetest.model.Candidate;
import com.cognine.onlinetest.utils.exception.EntityNotFoundException;
import com.cognine.onlinetest.utils.exception.FailedToPersistException;

@Repository
public class CandidateDAOImpl implements CandidateDAO {

	@Autowired
	CandidateMapper mapper;

	@Override
	public int createCandidate(Candidate candidate) throws FailedToPersistException {
		int candidateId = -1;
		try {
			mapper.createCandidate(candidate);
			candidateId = candidate.getCanId();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new FailedToPersistException(Candidate.class, "name", candidate.getName());
		}
		return candidateId;
	}

	@Override
	public Candidate getCandidateByEmailorId(int canId, String emailId) throws EntityNotFoundException {
		return mapper.getCandidateByEmailorId(canId, emailId);
	}

	@Override
	public List<String> fetchTypeValues(String type) throws EntityNotFoundException {
		return mapper.fetchTypeValues(type);
	}

	@Override
	public int updateCandidate(Candidate candidate) throws FailedToPersistException {
		int updated = -1;
		try {
			updated = mapper.updateCandidate(candidate);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new FailedToPersistException(Candidate.class, "name", candidate.getName());
		}
		return updated;
	}

}
