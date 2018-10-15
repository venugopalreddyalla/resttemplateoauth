package com.cognine.onlinetest.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.cognine.onlinetest.model.Section;

@Mapper
public interface SectionMapper {

	public static final String createSection = "insert into sections(sectionname,updatedby) values (#{sectionName},#{updatedBy})";

	@Insert(createSection)
	@Options(useGeneratedKeys = true, keyProperty = "sectionId", keyColumn = "sectionid")
	public int createSection(Section section);

	@Select("<script>select S.sectionid,S.sectionname  "
			+ " from (select * from sections  where isactive=true <if   test='searchSectionName != null'> and sectionname like #{searchSectionName}||'%' </if>) S "
			+ " order by sectionid limit #{limit} offset #{pageNumber}</script>")
	@Results({ @Result(property = "sectionName", column = "sectionname"),
			@Result(property = "sectionId", column = "sectionid") })
	public List<Section> getSections(@Param("pageNumber") int pageNumber, @Param("limit") int limit,
			@Param("searchSectionName") String searchSectionName);

	@Update("<script> update sections <set> " + "<if test='sectionName != null'>sectionname=#{sectionName},</if> "
			+ "<if test='isactive =! null'>isactive =#{isActive},</if>" + "updationdate =CURRENT_TIMESTAMP, "
			+ "<if test='updatedBy != null'>updatedby =#{updatedBy},</if> "
			+ "</set> where sectionid=#{sectionId}</script>")
	public int updateSection(Section section);
	
	@Select("select sectionid from sections where upper(sectionname)=upper(#{sectionName})")
	public Integer getSectionIdBySectionName(@Param("sectionName")String sectionName);

}
