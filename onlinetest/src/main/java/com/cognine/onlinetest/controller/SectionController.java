package com.cognine.onlinetest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognine.onlinetest.model.Section;
import com.cognine.onlinetest.service.SectionService;
import com.cognine.onlinetest.utils.exception.EntityNotFoundException;
import com.cognine.onlinetest.utils.exception.FailedToPersistException;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class SectionController {

	@Autowired
	private SectionService sectionService;

	@PostMapping("/createSection")
	public int createSection(@RequestBody Section section) throws FailedToPersistException {
		section.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		return sectionService.createSection(section);
	}

	@PostMapping("/updateSection")
	public int updateSection(@RequestBody Section section) throws FailedToPersistException {
		section.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		return sectionService.updateSection(section);
	}

	@GetMapping("/getSections")
	public List<Section> getSections(@RequestParam("pageNumber") int pageNumber, @RequestParam("noOfRows") int noOfRows,
			@RequestParam(name = "searchSectionName", required = false) String searchSectionName)
			throws EntityNotFoundException {
		return sectionService.getSections(pageNumber, noOfRows, searchSectionName);
	}

}
