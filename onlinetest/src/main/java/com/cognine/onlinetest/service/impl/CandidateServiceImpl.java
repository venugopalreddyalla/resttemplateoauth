package com.cognine.onlinetest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognine.onlinetest.dao.CandidateDAO;
import com.cognine.onlinetest.model.Candidate;
import com.cognine.onlinetest.service.CandidateService;
import com.cognine.onlinetest.utils.exception.EntityNotFoundException;
import com.cognine.onlinetest.utils.exception.FailedToPersistException;

@Service
public class CandidateServiceImpl implements CandidateService {

	@Autowired
	CandidateDAO candidateDAO;

	@Override
	public int saveCandidate(Candidate candidate, boolean isInsert) throws FailedToPersistException {
		if (isInsert) {
			return candidateDAO.createCandidate(candidate);
		} else {
			return candidateDAO.updateCandidate(candidate);
		}
	}

	@Override
	public Candidate getCandidateByEmailorId(Integer canId, String emailId) throws EntityNotFoundException {
		int candidateId = (canId == null ? 0 : canId);
		Candidate candidate = candidateDAO.getCandidateByEmailorId(candidateId, (candidateId > 0 ? null : emailId));
		if (candidate == null) {
			throw new EntityNotFoundException(Candidate.class, "id", candidateId + "", "email", emailId);
		}
		return candidate;
	}

	@Override
	public List<String> fetchTypeValues(String type) throws EntityNotFoundException {
		List<String> typesList = candidateDAO.fetchTypeValues(type);
		if (typesList == null || (typesList != null && typesList.size() == 0)) {
			throw new EntityNotFoundException("Types", "type", type);
		}
		return typesList;
	}

}
