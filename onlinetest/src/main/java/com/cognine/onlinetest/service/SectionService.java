package com.cognine.onlinetest.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cognine.onlinetest.model.Section;
import com.cognine.onlinetest.utils.exception.EntityNotFoundException;
import com.cognine.onlinetest.utils.exception.FailedToPersistException;

public interface SectionService {

	public static final Logger logger = LogManager.getLogger(SectionService.class);

	public int createSection(Section section) throws FailedToPersistException;

	public int updateSection(Section section) throws FailedToPersistException;

	public List<Section> getSections(int pageNumber, int noOfRows, String searchSectionName)
			throws EntityNotFoundException;

}
