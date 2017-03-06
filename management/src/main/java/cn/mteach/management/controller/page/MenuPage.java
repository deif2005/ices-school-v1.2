package cn.mteach.management.controller.page;

import cn.mteach.common.domain.exam.Message;
import cn.mteach.common.util.MenuItem;
import cn.mteach.management.service.SystemService;
import cn.mteach.management.security.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MenuPage {

	@Autowired
	private SystemService systemService;

	@RequestMapping(value = "common-page/menu", method = RequestMethod.GET)
	@ResponseBody
	public Message showMenuPage(Model model, HttpServletRequest request) {
		Message msg = new Message();
		UserInfo userInfo = "anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal()) ? null : (UserInfo) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		if(userInfo==null){
			msg.setResult("null");
			return msg;
		}
		String role =  userInfo.getRolesName();
		LinkedHashMap<String,MenuItem> menuMap = (LinkedHashMap<String, MenuItem>) systemService.getMenuItemsByAuthority(role);
		model.addAttribute("menuMap", menuMap);
		msg.setObject(menuMap);
		return msg;
	}
	/**
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "common-page/top-menu" }, method = RequestMethod.GET)
	public String showTopMenuPage(Model model, HttpServletRequest request) {

		UserInfo userInfo = "anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal()) ? null : (UserInfo) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();

		String role = userInfo == null ? "ROLE_TEACHER" : userInfo.getRolesName();

		List<MenuItem> list = new ArrayList<MenuItem>();

		if (userInfo != null) {
			LinkedHashMap<String, MenuItem> map = userInfo.getMenuMap();
			for (Map.Entry<String, MenuItem> entry : map.entrySet()) {
				list.add(entry.getValue());
			}
			model.addAttribute("topMenuList", list);
		}
		
		System.out.println(request.getParameter("topMenuId"));
		System.out.println(request.getParameter("leftMenuId"));
		model.addAttribute("topMenuId", request.getParameter("topMenuId"));
		model.addAttribute("leftMenuId", request.getParameter("leftMenuId"));
		
		return "common/top-menu";
	}

	@RequestMapping(value = { "common-page/left-menu" }, method = RequestMethod.GET)
	public String showLeftMenuPage(Model model, HttpServletRequest request) {
		
		String topMenuId =request.getParameter("topMenuId");
		String leftMenuId =request.getParameter("leftMenuId");

		UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String role = userInfo.getRolesName();

		List<MenuItem> list = new ArrayList<MenuItem>();

		LinkedHashMap<String, MenuItem> map = userInfo.getMenuMap().get(topMenuId).getChildMap();
		for (Map.Entry<String, MenuItem> entry : map.entrySet()) {
			list.add(entry.getValue());
		}

		model.addAttribute("leftMenuList", list);
		System.out.println(request.getParameter("topMenuId"));
		System.out.println(request.getParameter("leftMenuId"));
		model.addAttribute("topMenuId", topMenuId);
		model.addAttribute("leftMenuId", leftMenuId);

		return "common/left-menu";
		
		

	}

	@RequestMapping(value = { "common-page/footer" }, method = RequestMethod.GET)
	public String showFooterPage(Model model, HttpServletRequest request) {

		return "common/footer";
	}
}
