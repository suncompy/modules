/**
 * Copyright 2018 人人开源 http://www.renren.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.lebaoxun.manager.sys.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lebaoxun.commons.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.manager.sys.entity.SysMenuEntity;
import com.lebaoxun.manager.sys.entity.SysRoleMenuEntity;
import com.lebaoxun.manager.sys.entity.SysUserButton;
import com.lebaoxun.manager.sys.service.SysMenuService;
import com.lebaoxun.manager.sys.service.SysRoleMenuService;
import com.lebaoxun.manager.sys.service.SysUserRoleService;

/**
 * 系统菜单
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年10月27日 下午9:58:15
 */
@RestController
public class SysMenuController extends AbstractController {
	@Autowired
	private SysMenuService sysMenuService;
	
	@Autowired
	private SysUserRoleService sysUserRoleService;
	
	@Autowired
	private SysRoleMenuService sysRoleMenuService; 
	
	/**
	 * 导航菜单
	 */
	@RequestMapping("/sys/menu/nav")
	public ResponseMessage nav(@RequestParam("userId")Long userId){ 
		List<SysMenuEntity> menuList = sysMenuService.getUserMenuList(userId);
		Map<String,Object> dataModel = new HashMap<String,Object>();
		dataModel.put("menuList", menuList);
		return ResponseMessage.ok(dataModel); 
	}
	
	/**
	 * 获取用户菜单按钮权限
	 */
	@RequestMapping("/sys/menu/findButtonByUserId")
	public List<SysUserButton> findButtonByUserId(@RequestParam("userId")Long userId,@RequestParam("menuUrl") String menuUrl){
		List<SysUserButton> buttonList=new ArrayList<SysUserButton>();
		//查询菜单下所有的按钮，包括没授权的
		EntityWrapper<SysMenuEntity> menuWrapper=new EntityWrapper<SysMenuEntity>();
		menuWrapper.eq("url", menuUrl);
		SysMenuEntity menuOneObj=sysMenuService.selectOne(menuWrapper);
		
		menuWrapper=new EntityWrapper<SysMenuEntity>();
		menuWrapper.eq("parent_id", menuOneObj.getMenuId());
		menuWrapper.eq("type", 2);
		List<SysMenuEntity> allMenuList=sysMenuService.selectList(menuWrapper);
		//将list转换成map
		if(allMenuList==null||allMenuList.size()==0)return buttonList;
		//多角色去除重复按钮
		Map <Long,SysMenuEntity> menuMap=new HashMap<Long,SysMenuEntity>();
		if(userId==1){//超级管理员拥有所有按钮权限
			for(SysMenuEntity menuObj:allMenuList){
				SysUserButton button=new SysUserButton();
				button.setButtonCode(menuObj.getButtonCode());
				button.setButtonName(menuObj.getName());
				button.setButtonCss(menuObj.getButtonCss());
				buttonList.add(button);
			}
			return buttonList;
		}
		List<Long> roleList=sysUserRoleService.queryRoleIdList(userId);
		if(roleList==null||roleList.size()==0)return buttonList;
		Map<Long,SysMenuEntity> allMenuMap=new HashMap<Long,SysMenuEntity>();
		for(SysMenuEntity menuObj:allMenuList){
			allMenuMap.put(menuObj.getMenuId(), menuObj);
		}
		List<Long> buttonIds=new ArrayList<Long>();
		if(allMenuList!=null){
			for(SysMenuEntity _menuObj : allMenuList){
				buttonIds.add(_menuObj.getMenuId());
			}
		}
		for(Long roleId : roleList){
			//查询菜单有权限的按钮
			EntityWrapper<SysRoleMenuEntity> roleMenuWrapper=new EntityWrapper<SysRoleMenuEntity>();
			roleMenuWrapper.in("menu_id", buttonIds);
			roleMenuWrapper.eq("role_id", roleId);
			List<SysRoleMenuEntity> roleMenuList=sysRoleMenuService.selectList(roleMenuWrapper);
			if(roleMenuList!=null){
				for(SysRoleMenuEntity sysRoleObj:roleMenuList){
					menuMap.put(sysRoleObj.getMenuId(), allMenuMap.get(sysRoleObj.getMenuId()));
				}
			}
		}
		for (Map.Entry<Long,SysMenuEntity> entry : menuMap.entrySet()) {
			SysUserButton button=new SysUserButton();
			button.setButtonCode(entry.getValue().getButtonCode());
			button.setButtonName(entry.getValue().getName());
			button.setButtonCss(entry.getValue().getButtonCss());
			buttonList.add(button);
		}
		return buttonList;
	}
	
	/**
	 * 所有菜单列表
	 */
	@RequestMapping("/sys/menu/list")
	public List<SysMenuEntity> list(){
		List<SysMenuEntity> menuList = sysMenuService.selectList(null);
		for(SysMenuEntity sysMenuEntity : menuList){
			SysMenuEntity parentMenuEntity = sysMenuService.selectById(sysMenuEntity.getParentId());
			if(parentMenuEntity != null){
				sysMenuEntity.setParentName(parentMenuEntity.getName());
			}
		}

		return menuList;
	}
	
	/**
	 * 选择菜单(添加、修改菜单)
	 */
	@RequestMapping("/sys/menu/select")
	public ResponseMessage select(){
		//查询列表数据
		List<SysMenuEntity> menuList = sysMenuService.queryNotButtonList();
		
		//添加顶级菜单
		SysMenuEntity root = new SysMenuEntity();
		root.setMenuId(0L);
		root.setName("一级菜单");
		root.setParentId(-1L);
		root.setOpen(true);
		menuList.add(root);
		Map<String,Object> dataModel = new HashMap<String,Object>();
		dataModel.put("menuList", menuList);
		return ResponseMessage.ok(dataModel);
	}
	
	/**
	 * 菜单信息
	 */
	@RequestMapping("/sys/menu/info/{menuId}")
	public ResponseMessage info(@PathVariable("menuId") Long menuId){
		SysMenuEntity menu = sysMenuService.selectById(menuId);
		return ResponseMessage.ok().put("menu", menu);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/sys/menu/save")
	public ResponseMessage save(@RequestBody SysMenuEntity menu){
		//数据校验
		verifyForm(menu);
		
		sysMenuService.insert(menu);
		
		return ResponseMessage.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/sys/menu/update")
	public ResponseMessage update(@RequestBody SysMenuEntity menu){
		//数据校验
		verifyForm(menu);
				
		sysMenuService.updateById(menu);
		
		return ResponseMessage.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/sys/menu/delete")
	public ResponseMessage delete(@RequestParam("menuId")long menuId){
		if(menuId <= 31){
			return ResponseMessage.error("-1","系统菜单，不能删除");
		}

		//判断是否有子菜单或按钮
		List<SysMenuEntity> menuList = sysMenuService.queryListParentId(menuId);
		if(menuList.size() > 0){
			return ResponseMessage.error("-1","请先删除子菜单或按钮");
		}

		sysMenuService.delete(menuId);

		return ResponseMessage.ok();
	}
	
	/**
	 * 验证参数是否正确
	 */
	private void verifyForm(SysMenuEntity menu){
		if(StringUtils.isBlank(menu.getName())){
			throw new I18nMessageException("-1","菜单名称不能为空");
		}
		
		if(menu.getParentId() == null){
			throw new I18nMessageException("-1","上级菜单不能为空");
		}
		
		//菜单
		if(menu.getType() == 1){
			if(StringUtils.isBlank(menu.getUrl())){
				throw new I18nMessageException("菜单URL不能为空");
			}
		}
		
		//上级菜单类型
		int parentType = 0;
		if(menu.getParentId() != 0){
			SysMenuEntity parentMenu = sysMenuService.selectById(menu.getParentId());
			parentType = parentMenu.getType();
		}
		
		//目录、菜单
		if(menu.getType() == 0 ||
				menu.getType() == 1){
			if(parentType != 0){
				throw new I18nMessageException("-1","上级菜单只能为目录类型");
			}
			return ;
		}
		
		//按钮
		if(menu.getType() == 2){
			if(parentType != 1){
				throw new I18nMessageException("-1","上级菜单只能为菜单类型");
			}
			return ;
		}
	}
}
