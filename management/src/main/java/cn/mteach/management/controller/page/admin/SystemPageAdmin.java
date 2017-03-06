package cn.mteach.management.controller.page.admin;

import cn.mteach.common.domain.exam.Message;
import cn.mteach.common.domain.news.News;
import cn.mteach.common.domain.user.Department;
import cn.mteach.common.domain.user.User;
import cn.mteach.common.util.Page;
import cn.mteach.common.util.PagingUtil;
import cn.mteach.management.service.NewsService;
import cn.mteach.management.security.UserInfo;
import cn.mteach.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SystemPageAdmin {
	
	@Autowired
	private UserService userService;
	@Autowired
	private NewsService newsService;
	/**
	 * 管理员列表
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/admin/system/admin-list", method = RequestMethod.GET)
	private String adminListPage(Model model, HttpServletRequest request) {
		int index = 1;
		if (request.getParameter("page") != null)
			index = Integer.parseInt(request.getParameter("page"));
		Page<User> page = new Page<User>();
		page.setPageNo(index);
		page.setPageSize(10);
		UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Department> depList = userService.getDepList(null);
		List<User> userList = userService.getUserListByRoleId(userInfo.getRoleMap().get("ROLE_ADMIN").getRoleId(), page);
		String pageStr = PagingUtil.getPagelink(index, page.getTotalPage(), "", "admin/system/admin-list");
		model.addAttribute("depList", depList);
		model.addAttribute("userList", userList);
		model.addAttribute("pageStr", pageStr);
		return "sys-admin-list";
	}
	
	/**
	 * 添加管理员
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/admin/system/admin-add", method = RequestMethod.GET)
	private String adminAddPage(Model model, HttpServletRequest request) {
		return "";
	}
	
	/**
	 * 数据备份
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/admin/system/backup", method = RequestMethod.GET)
	private String backupPage(Model model, HttpServletRequest request) {
		return "";
	}
	
	/**
	 * 系统公告
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/admin/system/news-list", method = RequestMethod.GET)
	private String newsListPage(Model model, HttpServletRequest request, @RequestParam(value="page",required=false,defaultValue="1") int page) {
		
		Page<News> pageModel = new Page<News>();
		pageModel.setPageNo(page);
		pageModel.setPageSize(10);
		List<News> newsList = newsService.getNewsList(pageModel);
		
		String pageStr = PagingUtil.getPagelink(page, pageModel.getTotalPage(), "", "admin/system/news-list");
		model.addAttribute("newsList", newsList);
		model.addAttribute("pageStr", pageStr);
		return "news-list";
	}
	
	/**
	 * 发布公告
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/admin/system/news-add", method = RequestMethod.GET)
	private String newsAddPage(Model model, HttpServletRequest request) {
		return "";
	}
	/**
	 * 删除公告
	 * @return
	 */
	@RequestMapping(value = "/admin/system/news-delete/{newId}", method = RequestMethod.GET)
	private @ResponseBody Message newsDelete(@PathVariable String newId) {
		Message message=new Message();
		try{
			newsService.deleteNew(newId);
		}catch (Exception e){
			message.setResult(e.getClass().getName());
		}
		return message;
	}

	/**
	 * 更新公告
	 * @return
	 */
	@RequestMapping(value = "/admin/system/updateNew", method = RequestMethod.POST)
	private @ResponseBody Message updateNew(@RequestBody News news) {
		Message message=new Message();
		UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext()
				.getAuthentication()
				.getPrincipal();
		try{
			news.setUserId(userInfo.getUserid());
			newsService.updateNew(news);
		}catch (Exception e){
			message.setResult(e.getClass().getName());
		}
		return message;
	}
}
