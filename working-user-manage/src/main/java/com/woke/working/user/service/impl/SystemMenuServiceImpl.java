package com.woke.working.user.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woke.working.common.BusinessMsgEnum;
import com.woke.working.common.PageBean;
import com.woke.working.common.dto.user.SysPermissionDTO;
import com.woke.working.common.dto.user.SystemMenuDTO;
import com.woke.working.common.dto.user.SystemMenuPageDTO;
import com.woke.working.common.enumeration.StatusEnum;
import com.woke.working.common.vo.ResponseVo;
import com.woke.working.common.vo.user.SystemMenuTreeVo;
import com.woke.working.user.dao.SystemMenuDao;
import com.woke.working.user.dao.SystemPermissionDao;
import com.woke.working.user.entity.SysPermission;
import com.woke.working.user.entity.SysPermissionTree;
import com.woke.working.user.entity.SystemMenu;
import com.woke.working.user.entity.TreeModel;
import com.woke.working.user.service.SystemMenuService;
import com.woke.working.user.util.Md5Util;
import com.woke.working.web.exception.BusinessErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

@Service
@Slf4j
public class SystemMenuServiceImpl implements SystemMenuService {

	@Autowired
	private SystemMenuDao systemMenuDao;

	@Autowired
	private SystemPermissionDao systemPermissionDao;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResponseVo addMenu(SystemMenuDTO systemMenuDTO) {
		List<SystemMenu> systemMenuList = systemMenuDao.selectList(
				new LambdaQueryWrapper<SystemMenu>().eq(SystemMenu::getStatus, StatusEnum.ENABLE.getCode()));
		SystemMenu addMeunVo = new SystemMenu();
		// 如果上级为空默认为1层级
		if (!StringUtils.isEmpty(systemMenuDTO.getParentId())) {
			SystemMenu systemMenu = systemMenuList.stream()
					.filter(systemMenuVo -> systemMenuVo.getId().equalsIgnoreCase(systemMenuDTO.getParentId()))
					.findFirst().orElse(null);
			if (Objects.isNull(systemMenu)) {
				throw new BusinessErrorException(BusinessMsgEnum.WORKING_USER_ADD_MENU_EXCEPTION);
			}
			int level = systemMenu.getLevel().intValue() + 1;
			SystemMenu treeVo = systemMenuList.stream()
					.filter(systemMenuVo -> systemMenuVo.getMenuCode().equalsIgnoreCase(systemMenuDTO.getMenuCode())
							&& systemMenuVo.getLevel().equals(level))
					.findFirst().orElse(null);
			if (Objects.nonNull(treeVo)) {
				throw new BusinessErrorException(BusinessMsgEnum.WORKING_USER_PERMISSION_EXIST);
			}
			addMeunVo.setParentCode(systemMenu.getParentCode());
			addMeunVo.setLevel(level);
		} else {
			SystemMenu systemMenu = systemMenuList.stream()
					.filter(systemMenuVo -> systemMenuVo.getMenuCode().equalsIgnoreCase(systemMenuDTO.getMenuCode()))
					.findFirst().orElse(null);
			if (Objects.nonNull(systemMenu)) {
				throw new BusinessErrorException(BusinessMsgEnum.WORKING_USER_PERMISSION_EXIST);
			}
		}
		BeanUtils.copyProperties(systemMenuDTO, addMeunVo);
		return ResponseVo.success(systemMenuDao.insert(addMeunVo));
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResponseVo deleteMenu(String id) {
		SystemMenu systemMenu = Optional.ofNullable(systemMenuDao.selectById(id)).orElse(new SystemMenu());
		if (Objects.isNull(systemMenu)) {
			throw new BusinessErrorException(BusinessMsgEnum.WORKING_USER_DELETE_MENU_EXCEPTION);
		}
		Long existCount = systemMenuDao.selectCount(new LambdaQueryWrapper<SystemMenu>().eq(SystemMenu::getParentId, id)
				.eq(SystemMenu::getStatus, StatusEnum.ENABLE.getCode()));
		if (existCount > 0) {
			throw new BusinessErrorException(BusinessMsgEnum.WORKING_USER_EXIST_CHILD_NODE);
		}
		systemMenu.setStatus(false);
		return ResponseVo.success(systemMenuDao.updateById(systemMenu));
	}

	@Override
	public ResponseVo updateMenu(SystemMenuDTO systemMenuDTO) {
		SystemMenu systemMenu = systemMenuDao.selectOne(new LambdaQueryWrapper<SystemMenu>()
				.eq(SystemMenu::getId, systemMenuDTO.getId()).eq(SystemMenu::getStatus, StatusEnum.ENABLE.getCode()));

		if (!systemMenuDTO.getMenuCode().equalsIgnoreCase(systemMenu.getMenuCode())) {
			Long existCount = systemMenuDao.selectCount(
					new LambdaQueryWrapper<SystemMenu>().eq(SystemMenu::getStatus, StatusEnum.ENABLE.getCode())
							.eq(SystemMenu::getMenuCode, systemMenuDTO.getMenuCode()));
			if (existCount > 0) {
				throw new BusinessErrorException(BusinessMsgEnum.WORKING_USER_PERMISSION_EXIST);
			}
		}
		BeanUtils.copyProperties(systemMenuDTO, systemMenu);
		return ResponseVo.success(systemMenuDao.updateById(systemMenu));
	}

	@Override
	public ResponseVo selectMenuPage(SystemMenuPageDTO systemMenuPageDTO) {

		List<SysPermission> list = systemMenuDao.selectMenu(systemMenuPageDTO);
		List<SysPermissionTree> treeList = new ArrayList<>();

		// 如果有菜单名查询条件，则平铺数据 不做上下级
		if (!StringUtils.isEmpty(systemMenuPageDTO.getMenuName())) {
			if (list != null && list.size() > 0) {
				treeList = list.stream().map(e -> {
					e.setLeaf(true);
					return new SysPermissionTree(e);
				}).collect(Collectors.toList());
			}
		} else {
			getTreeList(treeList, list, null);
		}

		return ResponseVo.success(treeList);
	}

	private void getTreeList(List<SysPermissionTree> treeList, List<SysPermission> metaList, SysPermissionTree temp) {
		for (SysPermission permission : metaList) {
			String tempPid = permission.getParentId();
			SysPermissionTree tree = new SysPermissionTree(permission);
			if (temp == null && StringUtils.isEmpty(tempPid)) {
				treeList.add(tree);
				if (!tree.isLeaf()) {
					getTreeList(treeList, metaList, tree);
				}
			} else if (temp != null && tempPid != null && tempPid.equals(temp.getId())) {
				temp.getChildren().add(tree);
				if (!tree.isLeaf()) {
					getTreeList(treeList, metaList, tree);
				}
			}

		}
	}

	@Override
	public ResponseVo selectMenu() {
		List<SystemMenuTreeVo> systemMenuTreeVoList = systemMenuDao.selectMenuTree();
		Map<String, List<SystemMenuTreeVo>> groupMap = systemMenuTreeVoList.stream()
				.collect(Collectors.groupingBy(x -> Optional.ofNullable(x.getParentId()).orElse("0")));
		systemMenuTreeVoList.forEach(systemMenuTreeVo -> {
			systemMenuTreeVo.setCheldrenList(groupMap.get(systemMenuTreeVo.getId()));
		});
		List<SystemMenuTreeVo> collect = systemMenuTreeVoList.stream()
				.filter(systemMenuTreeVo -> StringUtils.isEmpty(systemMenuTreeVo.getParentId()))
				.collect(Collectors.toList());
		return ResponseVo.success(collect);
	}

	@Override
	public ResponseVo getPermCode() {
		// 获取当前用户的权限集合
		List<SysPermission> metaList = systemMenuDao.queryByUserById("a21364c311631aba5c41f90d25361bec");
		// 按钮权限（用户拥有的权限集合）
		List<String> codeList = metaList.stream()
				.filter((permission) -> 2 == permission.getMenuType() && "1".equals(permission.getStatus()))
				.collect(ArrayList::new, (list, permission) -> list.add(permission.getPerms()), ArrayList::addAll);
		//
		JSONArray authArray = new JSONArray();
		this.getAuthJsonArray(authArray, metaList);
		// 查询所有的权限
		List<SysPermission> allAuthList = systemMenuDao.findList(2);
		JSONArray allAuthArray = new JSONArray();
		this.getAllAuthJsonArray(allAuthArray, allAuthList);
		JSONObject result = new JSONObject();
		// 所拥有的权限编码
		result.put("codeList", codeList);
		// 按钮权限（用户拥有的权限集合）
		result.put("auth", authArray);
		// 全部权限配置集合（按钮权限，访问权限）
		result.put("allAuth", allAuthArray);
		result.put("sysSafeMode", true);
		return ResponseVo.success(result);
	}

	/**
	 * 获取权限JSON数组
	 * 
	 * @param jsonArray
	 * @param allList
	 */
	private void getAllAuthJsonArray(JSONArray jsonArray, List<SysPermission> allList) {
		JSONObject json = null;
		for (SysPermission permission : allList) {
			json = new JSONObject();
			json.put("action", permission.getPerms());
			json.put("status", permission.getStatus());
			// 1显示2禁用
			json.put("type", permission.getPermsType());
			json.put("describe", permission.getName());
			jsonArray.add(json);
		}
	}

	private void getAuthJsonArray(JSONArray jsonArray, List<SysPermission> metaList) {
		for (SysPermission permission : metaList) {
			if (permission.getMenuType() == null) {
				continue;
			}
			JSONObject json = null;
			if (permission.getMenuType() == 2 && "1".equals(permission.getStatus())) {
				json = new JSONObject();
				json.put("action", permission.getPerms());
				json.put("type", permission.getPermsType());
				json.put("describe", permission.getName());
				jsonArray.add(json);
			}
		}
	}

	/**
	 * 获取菜单JSON数组
	 * 
	 * @param jsonArray
	 * @param metaList
	 * @param parentJson
	 */
	private void getPermissionJsonArray(JSONArray jsonArray, List<SysPermission> metaList, JSONObject parentJson) {
		for (SysPermission permission : metaList) {
			if (permission.getMenuType() == null) {
				continue;
			}
			String tempPid = permission.getParentId();
			JSONObject json = getPermissionJsonObject(permission);
			if (json == null) {
				continue;
			}
			if (parentJson == null && StringUtils.isEmpty(tempPid)) {
				jsonArray.add(json);
				if (!permission.isLeaf()) {
					getPermissionJsonArray(jsonArray, metaList, json);
				}
			} else if (parentJson != null && !StringUtils.isEmpty(tempPid)
					&& tempPid.equals(parentJson.getString("id"))) {
				// 类型( 0：一级菜单 1：子菜单 2：按钮 )
				if (permission.getMenuType() == 2) {
					JSONObject metaJson = parentJson.getJSONObject("meta");
					if (metaJson.containsKey("permissionList")) {
						metaJson.getJSONArray("permissionList").add(json);
					} else {
						JSONArray permissionList = new JSONArray();
						permissionList.add(json);
						metaJson.put("permissionList", permissionList);
					}
					// 类型( 0：一级菜单 1：子菜单 2：按钮 )
				} else if (permission.getMenuType() == 1 || permission.getMenuType() == 0) {
					if (parentJson.containsKey("children")) {
						parentJson.getJSONArray("children").add(json);
					} else {
						JSONArray children = new JSONArray();
						children.add(json);
						parentJson.put("children", children);
					}

					if (!permission.isLeaf()) {
						getPermissionJsonArray(jsonArray, metaList, json);
					}
				}
			}

		}
	}

	/**
	 * 根据菜单配置生成路由json
	 * 
	 * @param permission
	 * @return
	 */
	private JSONObject getPermissionJsonObject(SysPermission permission) {
		JSONObject json = new JSONObject();
		// 类型(0：一级菜单 1：子菜单 2：按钮)
		if (permission.getMenuType() == 2) {
			return null;
		} else if (permission.getMenuType() == 0 || permission.getMenuType() == 1) {
			json.put("id", permission.getId());
			if (permission.isRoute()) {
				// 表示生成路由
				json.put("route", "1");
			} else {
				// 表示不生成路由
				json.put("route", "0");
			}

			if (isWwwHttpUrl(permission.getUrl())) {
				json.put("path", Md5Util.md5Encode(permission.getUrl(), "utf-8"));
			} else {
				json.put("path", permission.getUrl());
			}

			// 重要规则：路由name (通过URL生成路由name,路由name供前端开发，页面跳转使用)
			if (!StringUtils.isEmpty(permission.getComponentName())) {
				json.put("name", permission.getComponentName());
			} else {
				json.put("name", urlToRouteName(permission.getUrl()));
			}

			JSONObject meta = new JSONObject();
			// 是否隐藏路由，默认都是显示的
			if (permission.isHidden()) {
				json.put("hidden", true);
				// vue3版本兼容代码
				meta.put("hideMenu", true);
			}
			// 聚合路由
			if (permission.isAlwaysShow()) {
				json.put("alwaysShow", true);
			}
			json.put("component", permission.getComponent());
			// 由用户设置是否缓存页面 用布尔值
			if (permission.isKeepAlive()) {
				meta.put("keepAlive", true);
			} else {
				meta.put("keepAlive", false);
			}

			/* update_begin author:wuxianquan date:20190908 for:往菜单信息里添加外链菜单打开方式 */
			// 外链菜单打开方式
			if (permission.isInternalOrExternal()) {
				meta.put("internalOrExternal", true);
			} else {
				meta.put("internalOrExternal", false);
			}
			/* update_end author:wuxianquan date:20190908 for: 往菜单信息里添加外链菜单打开方式 */

			meta.put("title", permission.getName());

			// update-begin--Author:scott Date:20201015 for：路由缓存问题，关闭了tab页时再打开就不刷新 #842
			String component = permission.getComponent();
			if (!StringUtils.isEmpty(permission.getComponentName()) || !StringUtils.isEmpty(component)) {
				meta.put("componentName", this.getString(permission.getComponentName(),
						component.substring(component.lastIndexOf("/") + 1)));
			}
			// update-end--Author:scott Date:20201015 for：路由缓存问题，关闭了tab页时再打开就不刷新 #842

			if (StringUtils.isEmpty(permission.getParentId())) {
				// 一级菜单跳转地址
				json.put("redirect", permission.getRedirect());
				if (!StringUtils.isEmpty(permission.getIcon())) {
					meta.put("icon", permission.getIcon());
				}
			} else {
				if (!StringUtils.isEmpty(permission.getIcon())) {
					meta.put("icon", permission.getIcon());
				}
			}
			if (isWwwHttpUrl(permission.getUrl())) {
				meta.put("url", permission.getUrl());
			}
			// update-begin--Author:sunjianlei Date:20210918 for：新增适配vue3项目的隐藏tab功能
			if (permission.isHideTab()) {
				meta.put("hideTab", true);
			}
			// update-end--Author:sunjianlei Date:20210918 for：新增适配vue3项目的隐藏tab功能
			json.put("meta", meta);
		}

		return json;
	}

	public static String getString(String s, String defval) {
		if (StringUtils.isEmpty(s)) {
			return (defval);
		}
		return (s.trim());
	}

	/**
	 * 判断是否外网URL 例如： http://localhost:8080/jeecg-boot/swagger-ui.html#/ 支持特殊格式： {{
	 * window._CONFIG['domianURL'] }}/druid/ {{ JS代码片段 }}，前台解析会自动执行JS代码片段
	 *
	 * @return
	 */
	private boolean isWwwHttpUrl(String url) {
		boolean flag = url != null && (url.startsWith("http://") || url.startsWith("https://") || url.startsWith("{{"));
		if (flag) {
			return true;
		}
		return false;
	}

	/**
	 * 通过URL生成路由name（去掉URL前缀斜杠，替换内容中的斜杠‘/’为-） 举例： URL = /isystem/role RouteName =
	 * isystem-role
	 *
	 * @return
	 */
	private String urlToRouteName(String url) {
		if (!StringUtils.isEmpty(url)) {
			if (url.startsWith("/")) {
				url = url.substring(1);
			}
			url = url.replace("/", "-");

			// 特殊标记
			url = url.replace(":", "@");
			return url;
		} else {
			return null;
		}
	}

	@Override
	public ResponseVo getUserPermissionByToken(HttpServletRequest request) {

		List<SysPermission> metaList = systemMenuDao.queryByUserById("a21364c311631aba5c41f90d25361bec");

		JSONObject json = new JSONObject();
		JSONArray menujsonArray = new JSONArray();
		this.getPermissionJsonArray(menujsonArray, metaList, null);
		// 一级菜单下的子菜单全部是隐藏路由，则一级菜单不显示
		this.handleFirstLevelMenuHidden(menujsonArray);

		JSONArray authjsonArray = new JSONArray();
		this.getAuthJsonArray(authjsonArray, metaList);
		// 查询所有的权限
		JSONArray allauthjsonArray = new JSONArray();
		List<SysPermission> allAuthList = systemMenuDao.findList(2);
		this.getAllAuthJsonArray(allauthjsonArray, allAuthList);
		// 路由菜单
		json.put("menu", menujsonArray);
		// 按钮权限（用户拥有的权限集合）
		json.put("auth", authjsonArray);
		// 全部权限配置集合（按钮权限，访问权限）
		json.put("allAuth", allauthjsonArray);
		json.put("sysSafeMode", true);
		return ResponseVo.success(json);
	}

	/**
	 * 一级菜单的子菜单全部是隐藏路由，则一级菜单不显示
	 * 
	 * @param jsonArray
	 */
	private void handleFirstLevelMenuHidden(JSONArray jsonArray) {
		jsonArray = jsonArray.stream().map(obj -> {
			JSONObject returnObj = new JSONObject();
			JSONObject jsonObj = (JSONObject) obj;
			if (jsonObj.containsKey("children")) {
				JSONArray childrens = jsonObj.getJSONArray("children");
				childrens = childrens.stream()
						.filter(arrObj -> !"true".equals(((JSONObject) arrObj).getString("hidden")))
						.collect(Collectors.toCollection(JSONArray::new));
				if (childrens == null || childrens.size() == 0) {
					jsonObj.put("hidden", true);

					// vue3版本兼容代码
					JSONObject meta = new JSONObject();
					meta.put("hideMenu", true);
					jsonObj.put("meta", meta);
				}
			}
			return returnObj;
		}).collect(Collectors.toCollection(JSONArray::new));
	}

	public static boolean hasIndexPage(List<SysPermission> metaList) {
		boolean hasIndexMenu = false;
		for (SysPermission sysPermission : metaList) {
			if ("首页".equals(sysPermission.getName())) {
				hasIndexMenu = true;
				break;
			}
		}
		return hasIndexMenu;
	}

	@Override
	public ResponseVo queryTreeList() {
		List<String> ids = new ArrayList<>();
		List<SysPermission> list = systemMenuDao.findList(null);
		for (SysPermission sysPer : list) {
			ids.add(sysPer.getId());
		}
		List<TreeModel> treeList = new ArrayList<>();
		getTreeModelList(treeList, list, null);
		Map<String, Object> resMap = new HashMap(5);
		// 全部树节点数据
		resMap.put("treeList", treeList);
		// 全部树ids
		resMap.put("ids", ids);
		return ResponseVo.success(resMap);
	}

	private void getTreeModelList(List<TreeModel> treeList, List<SysPermission> metaList, TreeModel temp) {
		for (SysPermission permission : metaList) {
			String tempPid = permission.getParentId();
			TreeModel tree = new TreeModel(permission.getId(), tempPid, permission.getName(), permission.getRuleFlag(),
					permission.isLeaf());
			if (temp == null && StringUtils.isEmpty(tempPid)) {
				treeList.add(tree);
				if (!tree.getIsLeaf()) {
					getTreeModelList(treeList, metaList, tree);
				}
			} else if (temp != null && tempPid != null && tempPid.equals(temp.getKey())) {
				temp.getChildren().add(tree);
				if (!tree.getIsLeaf()) {
					getTreeModelList(treeList, metaList, tree);
				}
			}

		}
	}

	@Override
	public ResponseVo queryRolePermission(String roleId) {
		List<String> list = systemMenuDao.findRoleMenuList(roleId);
		return ResponseVo.success(list);
	}

	@Override
	public ResponseVo checkPermDuplication(String id, String url, Boolean alwaysShow) {
		SysPermission role = null;
		if (!StringUtils.isEmpty(id)) {
			role = systemMenuDao.getPermissionUrlById(id);
		}
		SysPermission newRole = systemMenuDao.getPermissionUrlTenant(url);
		if (newRole != null) {
			if (!newRole.getId().equals(id) || role == null) {
				return ResponseVo.fail("访问路径已存在");
			}
		}
		return ResponseVo.success(true);
	}

	@Override
	public ResponseVo addPermission(SysPermissionDTO sysPermissionDTO) {
		SysPermission sysPermission = new SysPermission();
		BeanUtils.copyProperties(sysPermissionDTO, sysPermission);
		// 判断是否是一级菜单，是的话清空父菜单
		if (sysPermission.getMenuType() == 0) {
			sysPermission.setParentId(null);
		}
		// ----------------------------------------------------------------------
		String pid = sysPermission.getParentId();
		if (!StringUtils.isEmpty(pid)) {
			// 有父节点需要查询父节点的menuCode
			SysPermission role = systemMenuDao.getPermissionUrlById(pid);
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(role.getMenuCode()).append(":").append(sysPermissionDTO.getMenuCode());
			sysPermissionDTO.setMenuCode(stringBuffer.toString());
			// 设置父节点不为叶子节点
			this.systemMenuDao.setMenuLeaf(pid, 0);
		}
		sysPermission.setCreateTime(new Date());
		sysPermission.setDelFlag(0);
		sysPermission.setLeaf(true);
		systemPermissionDao.insert(sysPermission);
		return ResponseVo.success(true);
	}

	@Override
	public ResponseVo updatePermission(SysPermissionDTO sysPermissionDTO) {
		SysPermission p = systemPermissionDao.selectById(sysPermissionDTO.getId());
		SysPermission sysPermission = new SysPermission();
		BeanUtils.copyProperties(sysPermissionDTO, sysPermission);
		if (Objects.nonNull(p)){
			sysPermission.setUpdateTime(new Date());
			// ----------------------------------------------------------------------
			// Step1.判断是否是一级菜单，是的话清空父菜单ID
			if (sysPermission.getMenuType() == 0) {
				sysPermission.setParentId("");
			}
			// Step2.判断菜单下级是否有菜单，无则设置为叶子节点
			Long count = systemPermissionDao.selectCount(
					new QueryWrapper<SysPermission>().lambda().eq(SysPermission::getParentId, sysPermission.getId()));
			if (count == 0) {
				sysPermission.setLeaf(true);
			}
			// 如果当前菜单的父菜单变了，则需要修改新父菜单和老父菜单的，叶子节点状态
			String pid = sysPermission.getParentId();
			boolean flag = (!StringUtils.isEmpty(pid) && !pid.equals(p.getParentId()))
					|| StringUtils.isEmpty(pid) && !StringUtils.isEmpty(p.getParentId());
			if (flag) {
				// a.设置新的父菜单不为叶子节点
				this.systemMenuDao.setMenuLeaf(pid, 0);
				// 如果当前父菜单更换就需要变化code
				SysPermission role = systemMenuDao.getPermissionUrlById(pid);

				// 获取当前更改的菜单权限code
				String[] value = sysPermission.getMenuCode().split(":");
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(role.getMenuCode()).append(":").append(value[value.length-1]);
				sysPermission.setMenuCode(stringBuffer.toString());

				// b.判断老的菜单下是否还有其他子菜单，没有的话则设置为叶子节点
				Long cc = systemPermissionDao.selectCount(
						new QueryWrapper<SysPermission>().lambda().eq(SysPermission::getParentId, p.getParentId()));
				if (cc == 0) {
					if (!StringUtils.isEmpty(p.getParentId())) {
						this.systemMenuDao.setMenuLeaf(p.getParentId(), 1);
					}
				}
			}
			systemPermissionDao.updateById(sysPermission);
		}
		return ResponseVo.success();
	}
}
