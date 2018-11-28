package com.primeton.wangbaoli.demo.dao;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;

import com.primeton.wangbaoli.demo.entity.User;

/**
 * 用户映射类
 * 
 * @author root
 *
 */
@Mapper
public interface UserMapper {

	/**
	 * 添加用户信息
	 * 
	 * @param user 用户信息对象
	 * @return 添加成功返回1，添加失败返回0；
	 */
	@Insert("INSERT INTO USER(USER_NAME,ORG_ID,PASSWORD,CREATE_USER,CREATE_TIME,SEED)"
			+ " values(#{userName},#{orgId},#{password},#{createUser},#{createTime},#{seed}) ")
	@Options(useGeneratedKeys = true, keyColumn = "ID", keyProperty = "id")
	Integer insertUser(User user);

	/**
	 * 通过用户id删除用户
	 * 
	 * @param id 用户id
	 * @return 成功返回1，失败返回0
	 */
	@Delete("DELETE FROM USER WHERE ID=#{id}")
	Integer deleteUser(Integer id);

	/**
	 * 根据传入信息，修改用户信息
	 * 
	 * @param user 用户信息
	 * @return 修改成功返回1，修改失败返回0；
	 */
	@UpdateProvider(type = Provider.class, method = "update")
	Integer updateUser(User user);

	/**
	 * 通过用户id修改用户密码
	 * 
	 * @param id       用户id
	 * @param password
	 * @return 修改成功返回1，修改失败返回0；
	 */
	@Update("UPDATE  USER  SET PASSWORD=#{password} WHERE ID=#{id}")
	Integer updatePassword(@Param("id") Integer id, @Param("password") String password);

	/**
	 * 根据用户id查找所有用户信息
	 * 
	 * @param id 用户id
	 * @return 用户信息
	 */
	@Select("SELECT ID id,USER_NAME userName,ORG_ID orgId,PASSWORD password,SEED seed FROM USER WHERE ID=#{id}")
	@Results({
			@Result(property = "org", column = "orgId", one = @One(select = "com.primeton.wangbaoli.demo.dao.OrgMapper.getOrg")) })
	User getUser(Integer id);

	/**
	 * 查找所有用户信息
	 * 
	 * @return
	 */

	@Select("SELECT ID id,USER_NAME userName,ORG_ID orgId FROM USER ")
	@Results({
			@Result(property = "org", column = "orgId", one = @One(select = "com.primeton.wangbaoli.demo.dao.OrgMapper.getOrg")) })
	List<User> queryUsers();


	/**
	 * 根据用户名精确查找用户信息
	 * 
	 * @param username 用户名
	 * @return 用户信息列表
	 */
	@Select("SELECT ID id,PASSWORD password,SEED seed,USER_NAME userName,ORG_ID orgId FROM USER WHERE USER_NAME=#{userName}")
	@Results({
			@Result(property = "org", column = "orgId", one = @One(select = "com.primeton.wangbaoli.demo.dao.OrgMapper.getOrg")) })
	User getUserByUserName(String userName);

	/**
	 * 根据用户名模糊查找用户信息
	 * 
	 * @param username 用户名
	 * @return 用户信息列表
	 */
	@Select("<script>SELECT ID id,USER_NAME userName,ORG_ID orgId FROM USER <if test=\"userName !='' \"> WHERE USER_NAME like #{userName}</if></script>")
	@Results({
			@Result(property = "org", column = "orgId", one = @One(select = "com.primeton.wangbaoli.demo.dao.OrgMapper.getOrg")) })
	List<User> queryUsersByKeyWord(User user);
	/**
	 * 通过机构id查找本机构下的所有的员工信息。
	 * 
	 * @param orgid 组织机构id
	 * @return 用户信息列表
	 */
	@Select("SELECT ID id,USER_NAME userName,ORG_ID orgId FROM USER WHERE ORG_ID=#{orgId}")
	@Results({
		@Result(property = "org", column = "orgId", one = @One(select = "com.primeton.wangbaoli.demo.dao.OrgMapper.getOrg")) })
	List<User> queryUsersByOrgId(Integer orgId);
	/**
	 * 动态SQL，修改用户信息
	 * 
	 * @author root
	 *
	 */
	class Provider {
		public String update(User user) {
			return new SQL() {
				{
					UPDATE("USER");
					if (user.getOrgId() != null)
						SET("ORG_ID=#{orgId}");
					if (user.getUserName() != null)
						SET("USER_NAME=#{userName}");
					WHERE("ID=#{id}");
				}
			}.toString();
		}

	}

}
