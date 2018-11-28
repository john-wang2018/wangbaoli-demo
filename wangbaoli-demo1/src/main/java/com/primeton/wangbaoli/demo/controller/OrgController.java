package com.primeton.wangbaoli.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.primeton.wangbaoli.demo.entity.Org;
import com.primeton.wangbaoli.demo.entity.ResponseResult;
import com.primeton.wangbaoli.demo.exception.DemoException;
import com.primeton.wangbaoli.demo.service.IOrgService;
import com.primeton.wangbaoli.demo.util.TreeUtile;

import io.swagger.annotations.ApiOperation;

/**
 * 组织机构控制层，实现增删改查功能
 * 
 * @author root
 *
 */
@RestController
@RequestMapping("/api/orgs")
public class OrgController {
	@Autowired
	private IOrgService orgService;

	/**
	 * 创建组织机构
	 * 
	 * @param org     组织机构对象
	 * @param session 会话
	 * @return 结果对象
	 * @throws DemoException
	 */
	@ApiOperation("创建组织机构")
	@PostMapping("/")
	public ResponseResult<Org> createOrg(@RequestBody Org org, HttpSession session) throws DemoException {
		orgService.createOrg(org, session);
		return new ResponseResult<Org>(ResponseResult.OK, org);

	}

	/**
	 * 根据组织机构id删除对应的组织机构
	 * 
	 * @param id 组织机构id
	 * @return 结果
	 * @throws DemoException
	 */
	@ApiOperation("根据组织机构id删除对应的组织机构")
	@DeleteMapping("/{id}")
	public ResponseResult<Void> removeOrg(@PathVariable("id") Integer id) throws DemoException {
		Integer state = orgService.removeOrg(id);
		if (state == 1)
			return new ResponseResult<Void>(ResponseResult.OK);
		return new ResponseResult<Void>(ResponseResult.ERR);
	}

	/**
	 * 根据组织机构id修改组织机构信息
	 * 
	 * @param org     组织机构对象
	 * @param session 会话
	 * @return 修改后的组织机构信息
	 * @throws DemoException
	 */
	@ApiOperation("修改组织机构信息")
	@PutMapping("/{id}")
	public ResponseResult<Org> modifyOrg(@RequestBody Org org, HttpSession session) throws DemoException {
		Org orginfo = orgService.modifyOrg(org, session);
		return new ResponseResult<Org>(ResponseResult.OK, orginfo);
	}

	/**
	 * 根据组织机构id查找组织机构信息
	 * 
	 * @param id 组织机构id
	 * @return 组织机构对象
	 * @throws DemoException
	 */
	@ApiOperation("根据组织机构id查找组织机构信息")
	@GetMapping("/{id}")
	public ResponseResult<Org> getOrg(@PathVariable("id") Integer id) throws DemoException {
		Org data = orgService.getOrg(id);
		return new ResponseResult<Org>(ResponseResult.OK, data);

	}

	/**
	 * 显示所有的组织机构信息
	 * 
	 * @param page 显示的页数
	 * @param size 每页显示的条数
	 * @return 组织机构对象列表
	 * @throws DemoException
	 */
	@ApiOperation("显示所有的组织机构信息")
	@GetMapping("/")
	public List<Org> queryOrgs(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "0") Integer size) throws DemoException {
		return TreeUtile.RecursiveOrg(orgService.queryOrgs(page, size));
	}
	/**
	 * 通过上级机构id查找所有的下级机构
	 * @param superOrgId上级机构id
	 * @return 本机构下的所有机构
	 * @throws DemoException
	 */
	@GetMapping("/actions/query/child_org/{superOrgId}")
	public List<Org> queryChildOrgs(@PathVariable("superOrgId")Integer superOrgId) throws DemoException{
		return TreeUtile.RecursiveOrg(orgService.queryChildOrgs(superOrgId));
	}

}
