package com.cognine.onlinetest.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cognine.onlinetest.model.Section;
import com.cognine.onlinetest.utils.exception.EntityNotFoundException;
import com.cognine.onlinetest.utils.exception.FailedToPersistException;

public interface SectionDAO {
	public static final Logger logger = LogManager.getLogger(SectionDAO.class);

	public int createSection(Section section) throws FailedToPersistException;

	public int updateSection(Section section) throws FailedToPersistException;

	public List<Section> getSections(int limit, int pageNumber, String searchSectionName)
			throws EntityNotFoundException;

	public Integer getSectionIdBySectionName(@Param("sectionName")String sectionName)throws EntityNotFoundException;
}
