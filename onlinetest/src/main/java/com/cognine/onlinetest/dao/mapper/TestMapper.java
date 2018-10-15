package com.cognine.onlinetest.dao.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.cognine.onlinetest.model.CandidateTestSummary;
import com.cognine.onlinetest.model.Test;
import com.cognine.onlinetest.model.TestCode;
import com.cognine.onlinetest.model.TestSection;

@Mapper
public interface TestMapper {
	public static final String createTest = "insert into tests (testname,islateraltest,testdurationinmins,isnegativemarking,updatedby) values(#{testName},#{isLateralTest}"
			+ ",#{testDurationinMins},#{isNegativeMarking},#{updatedBy})";
	public static final String createTestCode = "insert into testcodemapping (testid,testcode,activefrom,activeto,institutename,contactname,contactno,contactemail,istestcodeactive,updatedby) values"
			+ "(#{testId},#{testCode},#{activeFrom},#{activeTo},#{instituteName},#{contactName},#{contactNo},#{contactEmail},#{isTestCodeActive},#{updatedBy})";

	@Insert(createTest)
	@Options(useGeneratedKeys = true, keyProperty = "testId", keyColumn = "testid")
	public int createTest(Test test);

	@Update("<script> update tests <set> " + "<if test='testName != null'>testname=#{testName},</if> "
			+ "<if test='isLateralTest != null'>islateraltest =#{isLateralTest},</if> "
			+ "<if test='testDurationinMins != null'>testdurationinmins =#{testDurationinMins},</if> "
			+ "<if test='isNegativeMarking != null'>isnegativemarking =#{isNegativeMarking},</if> "
			+ "<if test='isActive != null'>isactive =#{isActive},</if> " + "updationdate =CURRENT_TIMESTAMP ,"
			+ "<if test='updatedBy != null'>updatedby =#{updatedBy},</if> " + "</set> where testid=#{testId} </script>")
	public int updateTest(Test test);

	@Select("<script>select T.testid,T.testname,T.islateraltest,T.testdurationinmins,T.isnegativemarking "
			+ "from (select * from tests where isactive=true <if   test='searchTestName != null'> and testname like #{searchTestName}||'%'  </if>) T  "
			+ " order by testid  limit #{limit} offset #{pageNumber} </script>")
	@Results({ @Result(property = "testId", column = "testid"), @Result(property = "testName", column = "testname"),
			@Result(property = "isLateralTest", column = "islateraltest"),
			@Result(property = "testDurationinMins", column = "testdurationinmins"),
			@Result(property = "isNegativeMarking", column = "isnegativemarking") })
	public List<Test> getTests(@Param("limit") int limit, @Param("pageNumber") int pageNumber,
			@Param("searchTestName") String searchTestName);

	@Insert(createTestCode)
	public int createTestCode(TestCode testCode);

	@Update("<script> update testcodemapping <set> " + "<if test='activeFrom != null'>activefrom=#{activeFrom},</if> "
			+ "<if test='activeTo != null'>activeto =#{activeTo},</if> "
			+ "<if test='instituteName != null'>institutename =#{instituteName},</if> "
			+ "<if test='contactName != null'>contactname =#{contactName},</if> "
			+ "<if test='contactNo != null'>contactno =#{contactNo},</if> "
			+ "<if test='contactEmail != null'>contactemail =#{contactEmail},</if> "
			+ "<if test='isTestCodeActive != null'>istestcodeactive =#{isTestCodeActive},</if> "
			+ "updationdate =CURRENT_TIMESTAMP ," + "<if test='updatedBy != null'>updatedby =#{updatedBy},</if> "
			+ "</set> where testid=#{testId} </script>")
	public int updateTestCodeDetails(TestCode testCode);

	@Select("<script>select A.testid, B.testname,A.testcode,A.activefrom,A.activeto,A.institutename,A.contactname,A.contactno,A.contactemail from testcodemapping A inner join tests B on A.testid=B.testid  where A.istestcodeactive=true and B.isactive=true "
			+ "<if   test='searchTestName != null'>  and B.testname like #{searchTestName}||'%' </if>"
			+ "  order by A.testid limit #{limit} offset #{pageNumber}" + " </script>")
	@Results({ @Result(property = "testCode", column = "testcode"), @Result(property = "testId", column = "testid"),
			@Result(property = "testName", column = "testname"),
			@Result(property = "activeFrom", column = "activefrom"),
			@Result(property = "activeTo", column = "activeto"),
			@Result(property = "instituteName", column = "institutename"),
			@Result(property = "contactName", column = "contactname"),
			@Result(property = "contactNo", column = "contactno"),
			@Result(property = "contactEmail", column = "contactemail") })
	public List<TestCode> getTestCodeDetails(@Param("limit") int limit, @Param("pageNumber") int pageNumber,
			@Param("searchTestName") String searchTestName);

	@Update(" update testcodemapping set istestcodeactive =false, updationdate =CURRENT_TIMESTAMP,"
			+ "updatedby='system' where #{currentTime}>=activeto and istestcodeactive=true ")
	public int deleteTestCode(@Param("currentTime") Date currentTime);

	@Insert("<script>insert into testsectionmapping(testid,sectionid,easyno,mediumno,hardno,updatedby) values"
			+ "<foreach collection=\"listTestSections\" item=\"listTestSection\" separator=\",\">"
			+ "(#{testId},#{listTestSection.sectionId},#{listTestSection.easyNo},#{listTestSection.mediumNo},#{listTestSection.hardNo},#{listTestSection.updatedBy}) </foreach> </script>")
	public int createTestSectionMapping(@Param("listTestSections") List<TestSection> listTestSections,
			@Param("testId") int testId);

	@Select("<script>select B.emailid,C.testname, A.candidateid,A.testid,A.uuid,A.isteststarted,A.starttime,A.endtime,A.elapsedtimeinmins,"
			+ "			A.istestcompleted,A.scored,A.maxscore,A.lowscore,A.mediumscore,A.highscore,A.sectionbreakup,A.testcompletiontext from candidatetestsummary A  "
			+ "			inner join candidate B on A.candidateid= B.canid  "
			+ "			inner join tests C on A.testid=C.testid <if test='searchTestName != null'> where  C.testname like #{searchTestName}||'%' </if> <if test='searchTestCode != null'> AND B.testcode=#{searchTestCode} </if>"
			+ "			order by A.scored asc limit #{limit} offset #{pageNumber} </script>")
	@Results({ @Result(property = "testCode", column = "testcode"), @Result(property = "testId", column = "testid"),
			@Result(property = "testName", column = "testname"),
			@Result(property = "candidateId", column = "candidateid"),
			@Result(property = "emailid", column = "emailid"), @Result(property = "uuId", column = "uuid"),
			@Result(property = "testId", column = "testid"),
			@Result(property = "isTestStarted", column = "isteststarted"),
			@Result(property = "startTime", column = "starttime"), @Result(property = "endTime", column = "endtime"),
			@Result(property = "elapsedTimeInMins", column = "elapsedtimeinmins"),
			@Result(property = "isTestCompleted", column = "istestcompleted"),
			@Result(property = "scored", column = "scored"), @Result(property = "maxScore", column = "maxscore"),
			@Result(property = "lowScore", column = "lowscore"),
			@Result(property = "mediumScore", column = "mediumscore"),
			@Result(property = "highScore", column = "highscore"),
			@Result(property = "sectionBreakUp", column = "sectionbreakup"),
			@Result(property = "completionText", column = "testcompletiontext") })
	public List<CandidateTestSummary> getCandidatesTestSummary(@Param("limit") int limit,
			@Param("pageNumber") int pageNumber, @Param("searchTestCode") String searchTestCode,
			@Param("searchTestName") String searchTestName);

	@Update("update candidatetestsummary set istestcompleted=#{testCompleted} where candidateid=#{canId} and testid=#{testId} ")
	public int updateResumeCandidateTest(@Param("canId") int canId, @Param("testId") int testId,
			@Param("testCompleted") boolean testCompleted, @Param("upadtedBy") String upadtedBy);
}
