package com.primeton.wangbaoli.dao;


import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;
import com.primeton.wangbaoli.entity.Org;
/**
 * 组织机构映射类
 * @author root
 *
 */
@Mapper
public interface OrgMapper{
	/**
	 * 创建组织机构信息
	 * @param org 组织机构对象
	 * @return 返回值为1表示创建成功，0表示创建失败。
	 */
	@Insert("Insert into  org(orgname,createuser,createdate,modifuser,modifdate,preorgid) values(#{orgname}"
			+ ",#{createuser},#{createdate},#{modifuser},#{modifdate},#{preorgid})")
	@Options(useGeneratedKeys=true,keyProperty="id",keyColumn="id")
	Integer insertOrg(Org org);
	/**
	 * 通过id删除对应的组织机构信息
	 * @param id 组织机构id
	 * @return返回值1表示删除成功，返回值0，表示删除失败。
	 */
	@Delete("DELETE FROM ORG WHERE ID=#{id}")
	Integer deleteOrg(Integer id);
	/**
	 * 修改部门机构信息
	 * @param id 部门id
	 * @return 返回值为1则修改成功，0则修改失败。
	 */ 
	@UpdateProvider(type=Provider.class,method="update")
	Integer updateOrg(Org org);
	/**
	 * 通过部门id查找部门信息
	 * @param id 部门id
	 * @return 部门对象
	 */
	@Select("select id,orgname,preorgid from org where id=#{id}")
	Org getOrg(Integer id);
	/**
	 * 通过部门名称关键字进行模糊查找
	 * @param orgname 部门名称
	 * @return 部门对象列表
	 */
	@Select("select id,preorgid,orgname from org where orgname like #{orgname}")
	List<Org> queryByLikeName(String orgname);
	/**
	 * 通过组织机构名精确查找组织机构信息。
	 * @param orgname 组织机构名
	 * @return 组织机构对象
	 */
	@Select("select id,preorgid,orgname from org where orgname=#{orgname}")
	Org getByName(String orgname);
	/**
	 * 查询所有组织机构信息
	 * @return 组织机构对象列表。
	 */
	@Select("select id,orgname,preorgid from org")
	List<Org> queryOrgs();
	
	/**
	 * 实现动态SQL的内部类
	 */
	class Provider{
		public String update(Org org){
			return new SQL() {
				{
					UPDATE("ORG");
					if(org.getOrgname()!=null)
						SET("orgname=#{orgname}");
					if(org.getPreorgid()!=null)
						SET("preorgid=#{preorgid}");
					SET("modifuser=#{modifuser}","modifdate=#{modifdate}");
					WHERE("ID=#{id}");
				}
			}.toString();
		}
	}
	
}
