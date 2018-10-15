package com.cognine.onlinetest.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.cognine.onlinetest.model.Role;
import com.cognine.onlinetest.model.User;

@Mapper
public interface UserMapper {

	public static final String createUser = "insert into users(username,userpassword,useremail) values(#{userName},#{password},#{userEmail})";
	public static final String findUserByEmail = "select userid,username,userpassword as password,useremail,firsttimelogin from users where useremail = #{useremail} and isActive=true";
	public static final String getUserRoles = "select roleid,rolename from roles where roleid in (select roleid from userroles where userid=#{userid})";
	public static final String deleteUserRoles = "delete from userroles where userid = #{userid}";

	@Insert(createUser)
	@Options(useGeneratedKeys = true, keyProperty = "userId", keyColumn = "userid")
	public int createUser(User user);

	@Select(findUserByEmail)
	public User findUserWithRoles(@Param("useremail") String userEmail);

	@Select(getUserRoles)
	public List<Role> getUserRoles(@Param("userid") int userId);

	@Delete(deleteUserRoles)
	public int deleteUserRoles(@Param("userid") int userId);

	@Insert("<script>insert into userroles(userid,roleid) values"
			+ "<foreach collection=\"roles\" item=\"role\" separator=\",\">"
			+ "(#{userid},#{role.roleId}) </foreach> </script>")
	public int mapUserRoles(@Param("userid") int userId, @Param("roles") List<Role> roles);

	@Update("<script> update users <set> " + "<if test='userName != null'>username=#{userName},</if> "
			+ "<if test='password != null'>userpassword =#{password},</if> "
			+ "<if test='setFirstTimeLogin != null'>firsttimelogin =#{firstTimeLogin},</if> "
			+ "</set> where userid=#{userId} or useremail=#{userEmail}</script>")
	public int updateUser(User user);

}
