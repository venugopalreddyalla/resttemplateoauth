package com.cognine.onlinetest.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.cognine.onlinetest.model.Candidate;
import com.cognine.onlinetest.model.Question;
import com.cognine.onlinetest.model.Score;
import com.cognine.onlinetest.model.Test;
import com.cognine.onlinetest.model.TestSection;

@Mapper
public interface ExamMapper {

	public static final String validateTestCode = "select A.testid,A.testname,A.testdurationinmins,A.islateraltest, A.isnegativemarking "
			+ "from tests A inner join testcodemapping B on A.testid = B.testid where A.isactive = true and B.testcode = #{testcode} and B.istestcodeactive=true";
	public static final String fetchTestSections = "select T.testid,T.sectionid,S.sectionname,(T.easyno+T.mediumno+T.hardno) as total from testsectionmapping T inner join sections S "
			+ "on T.sectionid = S.sectionid and T.testid = #{testid} ";
	public static final String fetchTestQuestionsWithAnswers = "select questionid, sectionid, questiontext, option1, option2, option3, option4, complexity,answer from testquestionmapping where testid = #{testid} "
			+ "order by sectionid";
	public static final String createCandidateTestSummaryRecord = "insert into candidatetestsummary(candidateid,testid,uuid,testcode) values (#{canid},#{testid},#{uuid},#{testCode})";
	public static final String validateCandidateforResume = "select count(1) from candidatetestsummary where istestcompleted=false and candidateid=#{canid} and testid=#{testid} and testcode=#{testcode}";
	public static final String updateCandidateUUIDforResume = "update candidatetestsummary set uuid=#{uuid} where candidateid=#{canid} and testid=#{testid} and testCode=#{testcode}";
	public static final String fetchResumeQuestions = "select A.questionid, A.sectionid, A.questiontext, A.option1, A.option2, A.option3, A.option4, A.complexity,B.selectedanswer "
			+ "from "
			+ "(select questionid, sectionid, questiontext, option1, option2, option3, option4, complexity from testquestionmapping where testid=#{testid}) A "
			+ "left outer join "
			+ "(Select testid, candidateid, questionid, selectedanswer from candidatetestmapping where testid=#{testid} and candidateid=#{canid} and testcode=#{testcode})B "
			+ "on A.questionid = B.questionid order by A.sectionid";
	public static final String validateCandidatewithUUID = "select count(1) from candidatetestsummary where istestcompleted=false and testid=#{testid} and candidateid=#{canid} and uuid=#{uuid} and testcode=#{testcode}";
	public static final String insertCandidateAnswer = "insert into candidatetestmapping(candidateid, testid, questionid, selectedanswer, isanswervalid, testcode) values (#{canid}, #{testid}, #{questionid}, #{selectedanswer}, #{isanswervalid}, #{testcode})";
	public static final String updateCandidateAnswer = "update candidatetestmapping set selectedanswer = #{selectedanswer}, isanswervalid=#{isanswervalid}, updationdate=CURRENT_TIMESTAMP "
			+ "where testid=#{testid} and candidateid=#{canid} and questionid=#{questionid} and testcode=#{testcode}";
	public static final String getElapsedTime = "select elapsedtimeinmins from candidatetestsummary where testid=#{testid} and candidateid=#{canid} and testcode=#{testcode}";
	public static final String fetchCandidateScoresperSection = "select B.sectionid, (select sectionname from sections where sectionid=B.sectionid) as sectionname, SUM((case when A.isanswervalid = false "
			+ "then (case when B.complexity='1-Low' then -1 when B.complexity='2-Medium' then -2 else -3 end) "
			+ "else (case when B.complexity='1-Low' then 2 when B.complexity='2-Medium' then 4 else 6 end) end)) as score from candidatetestmapping A inner join questions B on A.questionid = B.questionid "
			+ "where A.candidateid = #{canid} and A.testid =#{testid} and A.testcode=#{testcode} group by B.sectionid order by B.sectionid";
	public static final String fetchCandidateScoresperComplexity = "select B.complexity, SUM((case when A.isanswervalid = false then (case when B.complexity='1-Low' then -1 when B.complexity='2-Medium' then -2 else -3 end) "
			+ "else (case when B.complexity='1-Low' then 2 when B.complexity='2-Medium' then 4 else 6 end) end)) as score from candidatetestmapping A inner join questions B on A.questionid = B.questionid "
			+ "where A.candidateid = #{canid} and A.testid =#{testid} and A.testcode=#{testcode} group by B.complexity order by B.complexity";
	public static final String fetchMaxScoreperTest = "select SUM(case when complexity='1-Low' then 2 when complexity='2-Medium' then 4 else 6 end) as score from testquestionmapping where testid = #{testid}";
	public static final String validateCandidateEmail = "Select A.canid,"
			+ "case when B.datediff>(select CAST(coalesce(typevalue, '0') AS integer) from dtypes where dtype = 'CANDIDATEVALIDITY') then false "
			+ "else true end as isallowedfortest from "
			+ "(select * from candidate where emailid = #{emailid})A inner join "
			+ "(select candidateid, date(CURRENT_TIMESTAMP)::date - date(max(endtime))::date as datediff from candidatetestsummary "
			+ "group by candidateid) B on A.canid = B.candidateid";
	public static final String clearAnswer = "delete from candidatetestmapping where candidateid=#{candidateid} and testid=#{testid} and questionid=#{questionid} and testcode=#{testcode}";

	@Select(validateTestCode)
	@Results({ @Result(property = "testId", column = "testId"), @Result(property = "testName", column = "testname"),
			@Result(property = "testDurationinMins", column = "testdurationinmins"),
			@Result(property = "isNegativeMarking", column = "isnegativemarking"),
			@Result(property = "isLateralTest", column = "islateraltest") })
	public Test validateTestCode(@Param("testcode") String testCode);

	@Select(fetchTestSections)
	@Results({ @Result(property = "testId", column = "testId"), @Result(property = "sectionId", column = "sectionid"),
			@Result(property = "sectionName", column = "sectionname"),
			@Result(property = "questionsperSection", column = "total") })
	public List<TestSection> fetchTestSections(@Param("testid") int testId);

	@Select(fetchTestQuestionsWithAnswers)
	@Results({ @Result(property = "questionId", column = "questionid"),
			@Result(property = "sectionId", column = "sectionid"),
			@Result(property = "questionText", column = "questiontext"),
			@Result(property = "option1", column = "option1"), @Result(property = "option2", column = "option2"),
			@Result(property = "option3", column = "option3"), @Result(property = "option4", column = "option4"),
			@Result(property = "complexity", column = "complexity"), @Result(property = "answer", column = "answer") })
	public List<Question> fetchTestQuestionsWithAnswers(@Param("testid") int testId);

	@Select(fetchResumeQuestions)
	@Results({ @Result(property = "questionId", column = "questionid"),
			@Result(property = "sectionId", column = "sectionid"),
			@Result(property = "questionText", column = "questiontext"),
			@Result(property = "option1", column = "option1"), @Result(property = "option2", column = "option2"),
			@Result(property = "option3", column = "option3"), @Result(property = "option4", column = "option4"),
			@Result(property = "complexity", column = "complexity"),
			@Result(property = "selectedAnswer", column = "selectedanswer") })
	public List<Question> fetchResumeQuestions(@Param("testid") int testId, @Param("canid") int canId,
			@Param("testcode") String testCode);

	@Select(getElapsedTime)
	public int getElapsedTime(@Param("testid") int testId, @Param("canid") int canId,
			@Param("testcode") String testCode);

	@Insert(createCandidateTestSummaryRecord)
	public int createCandidateTestSummaryRecord(@Param("canid") int canId, @Param("testid") int testId,
			@Param("uuid") String uUID, @Param("testCode") String testCode);

	@Select(validateCandidateforResume)
	public int validateCandidateforResume(@Param("canid") int canId, @Param("testid") int testId,
			@Param("testcode") String testCode);

	@Update(updateCandidateUUIDforResume)
	public int updateCandidateUUIDforResume(@Param("uuid") String uuid, @Param("canid") int canId,
			@Param("testid") int testId, @Param("testcode") String testCode);

	@Select(validateCandidatewithUUID)
	public int validateCandidatewithUUID(@Param("canid") int canId, @Param("testid") int testId,
			@Param("uuid") String uuid, @Param("testcode") String testCode);

	@Update("<script> update candidatetestsummary <set> "
			+ "<if test='endtest != null'>istestcompleted=true,endtime=CURRENT_TIMESTAMP,testcompletiontext=#{completiontext},</if> "
			+ "<if test='elapsedtimeinmins != -1'>elapsedtimeinmins =#{elapsedtimeinmins},</if> "
			+ "<if test='score != -1'>scored =#{score},</if> "
			+ "<if test='maxscore != -1'>maxscore =#{maxscore},</if> "
			+ "<if test='lowscore != -1'>lowscore=#{lowscore},</if> "
			+ "<if test='mediumscore != -1'>mediumscore=#{mediumscore},</if> "
			+ "<if test='highscore != -1'>highscore=#{highscore},</if> "
			+ "<if test='sectionbreakup != null'>sectionbreakup=#{sectionbreakup},</if> "
			+ "</set> where candidateid=#{canid} and testid=#{testid} and uuid=#{uuid} and testcode=#{testcode} </script>")
	public int updateCandidateSummary(@Param("canid") int canId, @Param("testid") int testId,
			@Param("uuid") String uUID, @Param("endtest") String endTest,
			@Param("elapsedtimeinmins") int elapsedTimeinMins, @Param("score") float score,
			@Param("maxscore") float maxScore, @Param("sectionbreakup") String sectionBreakup,
			@Param("completiontext") String completionText, @Param("lowscore") float lowScore,
			@Param("mediumscore") float mediumScore, @Param("highscore") float highScore,
			@Param("testcode") String testCode);

	@Insert(insertCandidateAnswer)
	public int insertCandidateAnswer(@Param("canid") int canId, @Param("testid") int testId,
			@Param("questionid") int questionId, @Param("selectedanswer") int selectedAnswer,
			@Param("isanswervalid") boolean isAnswerValid, @Param("testcode") String testCode);

	@Update(updateCandidateAnswer)
	public int updateCandidateAnswer(@Param("canid") int canId, @Param("testid") int testId,
			@Param("questionid") int questionId, @Param("selectedanswer") int selectedAnswer,
			@Param("isanswervalid") boolean isAnswerValid, @Param("testcode") String testCode);

	@Select(fetchCandidateScoresperSection)
	@Results({ @Result(property = "score", column = "score"), @Result(property = "sectionId", column = "sectionid"),
			@Result(property = "sectionName", column = "sectionname") })
	public List<Score> fetchCandidateScoresperSection(@Param("canid") int canId, @Param("testid") int testId,
			@Param("testcode") String testCode);

	@Select(fetchCandidateScoresperComplexity)
	@Results({ @Result(property = "score", column = "score"), @Result(property = "complexity", column = "complexity") })
	public List<Score> fetchCandidateScoresperComplexity(@Param("canid") int canId, @Param("testid") int testId,
			@Param("testcode") String testCode);

	@Select(fetchMaxScoreperTest)
	public int fetchMaxScoreperTest(@Param("testid") int testId);

	@Select(validateCandidateEmail)
	@Results({ @Result(property = "canId", column = "canid"),
			@Result(property = "isAllowedForTest", column = "isallowedfortest") })
	public Candidate validateCandidateEmail(@Param("emailid") String emailId);

	@Delete(clearAnswer)
	public int clearAnswer(@Param("candidateid") int canId, @Param("testid") int testId,
			@Param("questionid") int questionId, @Param("testcode") String testCode);

}
