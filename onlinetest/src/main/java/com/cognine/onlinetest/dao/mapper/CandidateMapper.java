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

import com.cognine.onlinetest.model.Candidate;

@Mapper
public interface CandidateMapper {

	public static final String createCandidate = "insert into candidate(name,emailid,degree,collegename,department,graduationyear,percentage,phoneno,govtuidtype,govuid,currentemployer,currentposition,"
			+ "currentempstartdate,currentempenddate, currentlyworking,currentempcity,totalexperienceyrs,totalexperiencemonths,gender) values(#{name},#{emailId},#{degree},#{collegeName},#{department},#{graduationYear},"
			+ "#{percentage},#{phoneNo},#{govtUIDType},#{govtUID},#{currentEmployer},#{currentPosition},#{currentEmpStartDate},#{currentEmpEndDate},#{currentlyWorking},#{currentEmpCity},#{totalExpYears},"
			+ "#{totalExpMonths},#{gender})";
	public static final String updateCandidate = "update candidate set name=#{name}, emailid=#{emailId}, degree=#{degree},collegename=#{collegeName},department=#{department},"
			+ "graduationyear=#{graduationYear},percentage=#{percentage},phoneno=#{phoneNo},govtuidtype=#{govtUIDType},govuid=#{govtUID},currentemployer=#{currentEmployer},currentposition=#{currentPosition},"
			+ "currentempstartdate=#{currentEmpStartDate},currentempenddate=#{currentEmpEndDate},currentlyworking=#{currentlyWorking},currentempcity=#{currentEmpCity},totalexperienceyrs=#{totalExpYears},"
			+ "totalexperiencemonths=#{totalExpMonths},gender=#{gender} where canid=#{canId}";

	public static final String getCandidateByEmailorId = "select * from candidate where canid = #{canid} or emailid = #{emailid}";
	public static final String fetchTypeValues = "select typevalue from dtypes where dtype = #{type}";

	@Insert(createCandidate)
	@Options(useGeneratedKeys = true, keyProperty = "canId", keyColumn = "canid")
	public int createCandidate(Candidate candidate);

	@Select(getCandidateByEmailorId)
	@Results({ @Result(property = "canId", column = "canid"), @Result(property = "name", column = "name"),
			@Result(property = "emailId", column = "emailid"), @Result(property = "degree", column = "degree"),
			@Result(property = "collegeName", column = "collegename"),
			@Result(property = "department", column = "department"),
			@Result(property = "graduationYear", column = "graduationyear"),
			@Result(property = "percentage", column = "percentage"), @Result(property = "phoneNo", column = "phoneno"),
			@Result(property = "govtUIDType", column = "govtuidtype"), @Result(property = "govtUID", column = "govuid"),
			@Result(property = "currentEmployer", column = "currentemployer"),
			@Result(property = "currentPosition", column = "currentposition"),
			@Result(property = "currentEmpStartDate", column = "currentempstartdate"),
			@Result(property = "currentEmpEndDate", column = "currentempenddate"),
			@Result(property = "currentlyWorking", column = "currentlyworking"),
			@Result(property = "currentEmpCity", column = "currentempcity"),
			@Result(property = "totalExpYears", column = "totalexperienceyrs"),
			@Result(property = "totalExpMonths", column = "totalexperiencemonths"),
			@Result(property = "creationDate", column = "creationdate"),
			@Result(property = "gender", column = "gender") })
	public Candidate getCandidateByEmailorId(@Param("canid") int canId, @Param("emailid") String emailId);

	@Select(fetchTypeValues)
	public List<String> fetchTypeValues(@Param("type") String type);

	@Update(updateCandidate)
	public int updateCandidate(Candidate candidate);

}
