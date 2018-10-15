package com.cognine.onlinetest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognine.onlinetest.dao.SectionDAO;
import com.cognine.onlinetest.model.Section;
import com.cognine.onlinetest.service.SectionService;
import com.cognine.onlinetest.utils.exception.EntityNotFoundException;
import com.cognine.onlinetest.utils.exception.FailedToPersistException;

@Service
public class SectionServiceImpl implements SectionService {

	@Autowired
	private SectionDAO sectionDAO;

	@Override
	public int createSection(Section section) throws FailedToPersistException {
		return sectionDAO.createSection(section);
	}

	@Override
	public int updateSection(Section section) throws FailedToPersistException {

		return sectionDAO.updateSection(section);
	}

	@Override
	public List<Section> getSections(int pageNumber, int noOfRows, String searchSectionName)
			throws EntityNotFoundException {

		return sectionDAO.getSections(noOfRows, --pageNumber * noOfRows, searchSectionName);
	}

}
