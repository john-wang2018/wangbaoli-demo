package com.primeton.wangbaoli.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.primeton.wangbaoli.config.LogAop;
import com.primeton.wangbaoli.entity.Org;
import com.primeton.wangbaoli.entity.ResponseResult;
import com.primeton.wangbaoli.service.IOrgService;
import io.swagger.annotations.ApiOperation;
/**
 * 组织机构控制层，实现增删改查功能
 * @author root
 *
 */
@RestController
@RequestMapping("/api/orgs")
public class OrgController {
	@Autowired
	private IOrgService orgS;
	public OrgController() {
		
	}
	/**
	 * 创建组织机构
	 * @param org 组织机构对象
	 * @param session 会话
	 * @return 结果对象
	 */
	@LogAop(name="/aop/aop.action")
	@ApiOperation("创建组织机构")
	@PostMapping("/create")
	public ResponseResult<Org> createOrg(Org org,HttpSession session) {
		orgS.createOrg(org, session);
		return new ResponseResult<Org>( ResponseResult.OK,org);
		
	}
	/**
	 * 根据组织机构id删除对应的组织机构
	 * @param id 组织机构id
	 * @return  结果
	 */
	@LogAop(name="/aop/aop.action")
	@ApiOperation("根据组织机构id删除对应的组织机构")
	@DeleteMapping("/remove/{id}")
	public ResponseResult<Void> removeOrg(@PathVariable("id")Integer id) {
		orgS.removeOrg(id);
		return new ResponseResult<Void>(ResponseResult.OK);
	}
	/**
	 * 根据组织机构id修改组织机构信息
	 * @param org 组织机构对象
	 * @param session 会话
	 * @return 修改后的组织机构信息
	 */
	@LogAop(name="/aop/aop.action")
	@ApiOperation("修改组织机构信息")
	@PutMapping("/put/{id}")
	public ResponseResult<Org> modifyOrg(Org org,HttpSession session,@PathVariable("id")Integer id) {
		Org data = orgS.modifyOrg(org,session,id );
		return new ResponseResult<Org>(ResponseResult.OK, data);
	}
	/**
	 * 根据组织机构id查找组织机构信息
	 * @param id 组织机构id
	 * @return 组织机构对象
	 */
	@LogAop(name="/aop/aop.action")
	@ApiOperation("根据组织机构id查找组织机构信息")
	@GetMapping("/get/{id}")
	public ResponseResult<Org> getOrg(@PathVariable("id")Integer id){
		Org data=orgS.getOrg(id);
		return new ResponseResult<Org>(ResponseResult.OK,data);
		
	}
	/**
	 * 根据组织机构名称迷糊查找组织机构信息
	 * @param orgname 组织机构名称
	 * @param page 显示的页数
	 * @param size 每页显示的条数
	 * @return 组织机构对象列表
	 */
	@LogAop(name="/aop/aop.action")
	@ApiOperation("根据组织机构名称迷糊查找组织机构信息")
	@GetMapping("/query/name")
	public List<Org> queryByLikeName(String orgname,@RequestParam(value="page",defaultValue="0")Integer page,@RequestParam(value="size",defaultValue="0")Integer size){
		return orgS.queryByLike(orgname, page, size);
	}
	/**
	 * 显示所有的组织机构信息
	 * @param page 显示的页数
	 * @param size 每页显示的条数
	 * @return 组织机构对象列表
	 */
	@LogAop(name="/aop/aop.action")
	@ApiOperation("显示所有的组织机构信息")
	@GetMapping("/query")
	public List<Org> queryOrgs(@RequestParam(value="page",defaultValue="0")Integer page,@RequestParam(value="size",defaultValue="0")Integer size){
		return orgS.queryOrgs(page, size);
	}
	

}
