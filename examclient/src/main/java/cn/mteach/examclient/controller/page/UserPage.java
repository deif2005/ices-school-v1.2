package cn.mteach.examclient.controller.page;

import cn.mteach.common.domain.exam.Message;
import cn.mteach.common.domain.news.News;
import cn.mteach.common.domain.user.Department;
import cn.mteach.common.domain.user.User;
import cn.mteach.common.util.MenuItem;
import cn.mteach.common.util.Page;
import cn.mteach.common.util.StandardPasswordEncoderForSha1;
import cn.mteach.examclient.service.ExamService;
import cn.mteach.examclient.service.NewsService;
import cn.mteach.examclient.service.QuestionService;
import cn.mteach.examclient.service.UserService;
import cn.mteach.examclient.security.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
public class UserPage {
	
	@Autowired
	private UserService userService;
	@Autowired
	private ExamService examService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private NewsService newsService;
	
	/**
	 * 用户登录页面
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/user-login-page" }, method = RequestMethod.GET)
	public String loginPage(Model model, @RequestParam(value = "result", required = false, defaultValue = "") String result,HttpServletRequest request) {
		AuthenticationServiceException exception = (AuthenticationServiceException)request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
		if(exception != null){
			if("准考证号码错误！".equals(exception.getMessage()) || "不在考试时间范围内，不允许使用准考证！".equals(exception.getMessage()))
				return "redirect:quick-start";
		}
		
		return "login";
	}
	
	@RequestMapping(value = { "/quick-start" }, method = RequestMethod.GET)
	public String quickStartPage(Model model, @RequestParam(value = "result", required = false, defaultValue = "") String result) {
		
		return "quick-start";
	}
	
	@RequestMapping(value = { "/user-register" }, method = RequestMethod.GET)
	public String registerPage(Model model) {
		List<Department> depList = userService.getDepList(null);
		model.addAttribute("depList", depList);
		return "register";
	}
	
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String homePage(Model model) {
		
		return "redirect:student/home";
	}
	
	@RequestMapping(value = { "student/home" }, method = RequestMethod.GET)
	public String directToBaseHomePage(Model model, HttpServletRequest request) {
		
		Page<News> pageModel = new Page<News>();
		pageModel.setPageNo(1);
		pageModel.setPageSize(2);
		List<News> newsList = newsService.getNewsList(pageModel);
		model.addAttribute("newsList", newsList);
		
		request.getSession().removeAttribute("SPRING_SECURITY_LAST_EXCEPTION");
		String result = request.getParameter("result");
		if ("failed".equals(result)) {
			model.addAttribute("result_msg", "登录失败");
		}

		if (SecurityContextHolder.getContext().getAuthentication() == null){
			//this.appendBaseInfo(model);
			return "home";
		}
		if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().endsWith("anonymousUser")){
			//this.appendBaseInfo(model);
			return "home";
		}
		
		UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		//非常关键，只有histid被重置，其他都不变
		if(userInfo.getHistId() != 0){
			return "redirect:student/exam-start/" + userInfo.getExamId();
		}
		LinkedHashMap<String,MenuItem> map = (LinkedHashMap<String, MenuItem>) userService.getMenuItemsByAuthority("ROLE_STUDENT");
		userInfo.setMenuMap(map);
		
		return "home";
	}
	
	@RequestMapping(value = { "student/setting/information" }, method = RequestMethod.GET)
	public String settingPage(Model model) {
		
		List<Department> depList = userService.getDepList(null);
		model.addAttribute("depList", depList);
		return "setting";
	}

	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	@RequestMapping(value = { "/student/updateUser" }, method = RequestMethod.POST)
	public String updateUser(User user) {
		UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		try {
			userService.updateUser(user, null);
			userInfo.setTrueName(user.getTrueName());
			userInfo.setEmail(user.getEmail());
			userInfo.setNationalId(user.getNationalId());
			userInfo.setPhoneNum(user.getPhoneNum());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/student/setting/updateSuccess";
	}

	@RequestMapping(value = { "/student/setting/updateSuccess" }, method = RequestMethod.GET)
	public String updateSuccess(User user) {
		return "update-success";
	}

	/**
	 * 修改密码
	 * @param user
	 * @return
	 */
	@RequestMapping(value = { "/student/changePwd" }, method = RequestMethod.POST)
	public String changePassword(User user){
		Message message = new Message();
		UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		try{
			String password = user.getPassword() + "{" + userInfo.getUsername() + "}";
			PasswordEncoder passwordEncoder = new StandardPasswordEncoderForSha1();
			String resultPassword = passwordEncoder.encode(password);
			user.setPassword(resultPassword);
			user.setUserName(userInfo.getUsername());
			userService.updateUserPwd(user, null);
		}catch(Exception e){
			e.printStackTrace();
			message.setResult(e.getClass().getName());
		}
		return "redirect:/student/setting/updateSuccess";
	}

	@RequestMapping(value = { "student/setting/change-password" }, method = RequestMethod.GET)
	public String changePasswordPage() {
		return "change-password";
	}
	
	@RequestMapping(value = { "news/{newsId}" }, method = RequestMethod.GET)
	public String newsDetailPage(Model model,@PathVariable("newsId") int newsId){
		News news = newsService.getNewsById(newsId);
		model.addAttribute("news", news);
		return "news-detail";
	}
}
