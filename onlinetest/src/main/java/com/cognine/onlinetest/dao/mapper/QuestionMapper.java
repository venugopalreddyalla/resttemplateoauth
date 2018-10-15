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

import com.cognine.onlinetest.model.Question;

@Mapper
public interface QuestionMapper {

	public static final String createQuestion = "insert into questions(sectionid,questiontext,option1,"
			+ "option2,option3,option4,answer,complexity,updatedby) values(#{sectionId},#{questionText},"
			+ "#{option1} ,#{option2},#{option3},#{option4},#{answer},#{complexity},#{updatedBy})";

	@Insert(createQuestion)
	@Options(useGeneratedKeys = true, keyProperty = "questionId", keyColumn = "questionid")
	public int createQuestion(Question question);

	@Update("<script> update questions <set> " + "<if test='sectionId != null'>sectionid=#{sectionId},</if> "
			+ "<if test='questionText != null'>questiontext =#{questionText},</if> "
			+ "<if test='option1 != null'>option1 =#{option1},</if> "
			+ "<if test='option2 != null'>option2 =#{option2},</if> "
			+ "<if test='option3 != null'>option3 =#{option3},</if> "
			+ "<if test='option4 != null'>option4 =#{option4},</if> "
			+ "<if test='answer != null'>answer =#{answer},</if> "
			+ "<if test='isActive != null'>isactive =#{isActive},</if> "
			+ "<if test='complexity != null'>complexity =#{complexity},</if> " + "updationdate =CURRENT_TIMESTAMP, "
			+ "<if test='updatedBy != null'>updatedby =#{updatedBy},</if> "
			+ "</set> where questionid=#{questionId} </script>")
	public int updateQuestion(Question question);

	@Select("<script>select Q.questionid,Q.sectionid,Q.questiontext,Q.option1,Q.option2,Q.option3,Q.option4,Q.complexity,Q.answer "
			+ "from (select * from questions  where isactive=true <if   test='searchQuestionName != null'> and questiontext like #{searchQuestionName}||'%' </if>) Q  "
			+ " order by questionid  limit #{limit} offset #{pageNumber}</script>")
	@Results({ @Result(property = "questionId", column = "questionid"),
			@Result(property = "sectionId", column = "sectionid"),
			@Result(property = "questionText", column = "questiontext"),
			@Result(property = "option1", column = "option1"), @Result(property = "option2", column = "option2"),
			@Result(property = "option3", column = "option3"), @Result(property = "option4", column = "option4"),
			@Result(property = "complexity", column = "complexity"), @Result(property = "answer", column = "answer") })
	public List<Question> getQuestions(@Param("pageNumber") int pageNumber, @Param("limit") int limit,
			@Param("searchQuestionName") String searchQuestionName);

	@Select("select * from questions where sectionid=#{sectionId} and  complexity=#{questionPriority} and isactive=true order by random() limit #{limit}")
	public List<Question> getQuestionsForSectionMapping(@Param("sectionId") int sectionId,
			@Param("questionPriority") String questionPriority, @Param("limit") int limit);

	@Insert("<script>insert into testquestionmapping(testid,questionid,sectionid,questiontext,option1,option2,option3,option4,answer,complexity,updatedBy) values"
			+ "<foreach collection=\"listOfQuestions\" item=\"listOfQuestion\" separator=\",\">"
			+ "(#{testId},#{listOfQuestion.questionId},#{listOfQuestion.sectionId},#{listOfQuestion.questionText},#{listOfQuestion.option1},#{listOfQuestion.option2},#{listOfQuestion.option3},#{listOfQuestion.option4},#{listOfQuestion.answer},#{listOfQuestion.complexity},#{updatedBy}) </foreach> </script>")
	public int createTestQuestionMapping(@Param("testId") int testId,
			@Param("listOfQuestions") List<Question> listOfQuestions, @Param("updatedBy")String updatedBy);

	@Select("select questionid from questions where sectionid=#{sectionId} and upper(questiontext)=upper(#{questionText})")
	public Integer questionIdBySectionIdQuestionText(@Param("sectionId")int sectionId,@Param("questionText")String questionText);
}
