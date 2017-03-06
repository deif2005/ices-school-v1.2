package cn.mteach.management.controller.page.admin;

import cn.mteach.common.domain.question.Field;
import cn.mteach.common.domain.user.Department;
import cn.mteach.common.domain.user.Group;
import cn.mteach.common.domain.user.User;
import cn.mteach.common.util.Page;
import cn.mteach.common.util.PagingUtil;
import cn.mteach.management.security.UserInfo;
import cn.mteach.management.service.QuestionService;
import cn.mteach.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserPageAdmin {

	@Autowired
	private UserService userService;
	@Autowired
	private QuestionService questionService;
	/**
	 * 学员管理
	 * @return
	 */
	@RequestMapping(value = { "admin/user/student-list" }, method = RequestMethod.GET)
	public String userListPage(Model model, HttpServletRequest request) {

		UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		int index = 1;
		if (request.getParameter("page") != null)
			index = Integer.parseInt(request.getParameter("page"));
		Page<User> page = new Page<User>();
		page.setPageNo(index);
		page.setPageSize(10);
		int groupId = 0;
		String searchStr = "";
		if(request.getParameter("groupId") != null){
			groupId = Integer.parseInt(request.getParameter("groupId"));
			searchStr = request.getParameter("searchStr");
		}
		List<Department> depList = userService.getDepList(null);
		List<User> userList = userService.getUserListByGroupIdAndParams(groupId, "ROLE_STUDENT", searchStr, page);
		String pageStr = PagingUtil.getPagelink(index, page.getTotalPage(), "", "admin/user/student-list");
		List<Group> groupList = userService.getGroupListByUserId(userInfo.getUserid(), null);
		model.addAttribute("depList", depList);
		model.addAttribute("groupId", groupId);
		model.addAttribute("userList", userList);
		model.addAttribute("pageStr", pageStr);
		model.addAttribute("groupList", groupList);
		model.addAttribute("authority", "ROLE_STUDENT");
		return "user-list";
	}
	
	/**
	 * 考试历史
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "admin/user/student-examhistory" }, method = RequestMethod.GET)
	public String examHistoryPage(Model model, HttpServletRequest request){
		return "";
	}
	
	/**
	 * 学员资料
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "admin/user/student-profile" }, method = RequestMethod.GET)
	public String studentProfilePage(Model model, HttpServletRequest request){
		return "";
	}
	
	/**
	 * 教师管理
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "admin/user/teacher-list" }, method = RequestMethod.GET)
	public String teacherListPage(Model model, HttpServletRequest request) {

		int index = 1;
		if (request.getParameter("page") != null)
			index = Integer.parseInt(request.getParameter("page"));
		Page<User> page = new Page<User>();
		page.setPageNo(index);
		page.setPageSize(10);
		UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Department> depList = userService.getDepList(null);
		List<User> userList = userService.getUserListByRoleId(userInfo.getRoleMap().get("ROLE_TEACHER").getRoleId(), page);
		String pageStr = PagingUtil.getPagelink(index, page.getTotalPage(), "", "admin/user/teacher-list");
		model.addAttribute("depList", depList);
		model.addAttribute("userList", userList);
		model.addAttribute("pageStr", pageStr);
		return "admin/teacher-list";
	}
	
	/**
	 * 教师资料
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "admin/user/teacher-profile" }, method = RequestMethod.GET)
	public String teacherProfilePage(Model model, HttpServletRequest request){
		return "";
	}

	/**
	 * ifram内部学生列表
	 */
//	@RequestMapping(value = { "admin/user/inner/user-list/{groupId}-{authority}" }, method = RequestMethod.GET)
//	public String showUserListInnerAdminPage(Model model, HttpServletRequest request, @PathVariable Integer groupId, @PathVariable String authority) {
//		UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		int index = 1;
//		if (request.getParameter("page") != null)
//			index = Integer.parseInt(request.getParameter("page"));
//		Page<User> page = new Page<User>();
//		page.setPageNo(index);
//		page.setPageSize(10);
//		String searchStr = "";
//		if(request.getParameter("searchStr") != null){
//			searchStr = request.getParameter("searchStr");
//		}
//		List<User> userList = userService.getUserListByGroupIdAndParams(groupId, authority, searchStr, page);
//		String pageStr = PagingUtil.getPagelink(index, page.getTotalPage(), "&searchStr=" + searchStr, "admin/user/inner/user-list/" + groupId + "-" + authority);
//
//		List<Group> groupList = userService.getGroupListByUserId(userInfo.getUserid(), null);
//		model.addAttribute("userList", userList);
//		model.addAttribute("pageStr", pageStr);
//		model.addAttribute("groupList", groupList);
//		model.addAttribute("groupId", groupId);
//		model.addAttribute("authority", "ROLE_STUDENT");
//		model.addAttribute("searchStr", searchStr);
//		return "user-list";
//	}

	/**
	 * 添加用户界面
	 * 暂时保留，可能废弃
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "admin/user/add-teacher", method = RequestMethod.GET)
	private String addUserPage(Model model, HttpServletRequest request) {

		List<Field> fieldList = questionService.getAllField(null);
		model.addAttribute("fieldList", fieldList);
		return "admin/add-user";
	}
	
	
}
