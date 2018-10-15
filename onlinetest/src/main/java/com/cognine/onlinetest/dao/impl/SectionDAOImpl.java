package com.cognine.onlinetest.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognine.onlinetest.dao.SectionDAO;
import com.cognine.onlinetest.dao.mapper.SectionMapper;
import com.cognine.onlinetest.model.Section;
import com.cognine.onlinetest.utils.exception.EntityNotFoundException;
import com.cognine.onlinetest.utils.exception.FailedToPersistException;

@Repository
public class SectionDAOImpl implements SectionDAO {
	@Autowired
	private SectionMapper mapper;

	@Override
	public int createSection(Section section) throws FailedToPersistException {
		int sectionId = -1;
		try {
			mapper.createSection(section);
			sectionId = section.getSectionId();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new FailedToPersistException(Section.class, "sectionname", section.getSectionName());
		}
		return sectionId;
	}

	@Override
	public int updateSection(Section section) throws FailedToPersistException {
		int sectionId = -1;
		try {
			mapper.updateSection(section);
			sectionId = section.getSectionId();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new FailedToPersistException(Section.class, "sectionId", section.getSectionId() + "", "sectionName",
					section.getSectionName());
		}
		return sectionId;
	}

	@Override
	public List<Section> getSections(int limit, int pageNumber, String searchSectionName)
			throws EntityNotFoundException {
		return mapper.getSections(pageNumber, limit, searchSectionName);
	}

	@Override
	public Integer getSectionIdBySectionName(String sectionName) throws EntityNotFoundException {
		Integer sectionId = mapper.getSectionIdBySectionName(sectionName);
		if(sectionId==null)
			return -1;
		else
		return sectionId;
	}

}
