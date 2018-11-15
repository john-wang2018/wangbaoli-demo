package com.primeton.wangbaoli.dao;

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
import com.primeton.wangbaoli.entity.User;

/**
 * 用户映射类
 * 
 * @author root
 *
 */
@Mapper
public interface UserMapper {
	/**
	 * 通过机构id查找本机构下的所有的员工信息。
	 * 
	 * @param orgid 组织机构id
	 * @return 用户信息列表
	 */
	@Select("select id,username,orgid from user where orgid=#{orgid}")
	List<User> queryByOrgid(Integer orgid);

	/**
	 * 将同一个部门员工的orgid设为null;
	 * 
	 * @param id
	 * @return
	 */
	@Update("update user set orgid=null where oigid=#{orgid}")
	Integer updateOrgidNull(Integer id);

	// TODO-用户添加
	/**
	 * 添加用户信息
	 * 
	 * @param user 用户信息对象
	 * @return 添加成功返回1，添加失败返回0；
	 */
	@Insert("insert into user(username,orgid,password,createuser,createtime,modifuser,modiftime,uuid)"
			+ " values(#{username},#{orgid},#{password},#{createUser},#{createTime},#{modifUser},#{modifTime},#{uuid}) ")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	Integer insertUser(User user);

	// TODO-用户删除
	/**
	 * 通过用户id删除用户
	 * 
	 * @param id 用户id
	 * @return 成功返回1，失败返回0
	 */
	@Delete("delete from user where id=#{id}")
	Integer deleteUser(Integer id);

	// TODO-用户修改
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
	@Update("update  user  set password=#{password} where id=#{id}")
	Integer updatePassword(@Param("id") Integer id, @Param("password") String password);

	/**
	 * 根据用户id查找所有用户信息
	 * 
	 * @param id 用户id
	 * @return 用户信息
	 */
	@Select("select id,username,orgid,password,uuid from user where id=#{id}")
	@Results({
			@Result(property = "org", column = "orgid", one = @One(select = "com.primeton.wangbaoli.dao.OrgMapper.get")) })
	User getUser(Integer id);

	/**
	 * 查找所有用户信息
	 * 
	 * @return
	 */

	@Select("select id,username,orgid from user ")
	@Results({
			@Result(property = "org", column = "orgid", one = @One(select = "com.primeton.wangbaoli.dao.OrgMapper.get")) })
	List<User> queryUsers();

	/**
	 * 根据用户名精确查找用户信息
	 * 
	 * @param username 用户名
	 * @return 用户信息列表
	 */
	@Select("select id,password,uuid,username,orgid from user where username=#{username}")
	@Results({
			@Result(property = "org", column = "orgid", one = @One(select = "com.primeton.wangbaoli.dao.OrgMapper.get")) })
	User getByName(String username);

	/**
	 * 根据用户名模糊查找用户信息
	 * 
	 * @param username 用户名
	 * @return 用户信息列表
	 */
	@Select("select id,username,orgid from user where username like #{username}")
	@Results({
			@Result(property = "org", column = "orgid", one = @One(select = "com.primeton.wangbaoli.dao.OrgMapper.get")) })
	List<User> queryLikeName(String username);

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
					UPDATE("user");
					if (user.getOrgid() != null)
						SET("orgid=#{orgid}");
					if (user.getUsername() != null)
						SET("username=#{username}");
					WHERE("id=#{id}");
				}
			}.toString();
		}

	}

}
