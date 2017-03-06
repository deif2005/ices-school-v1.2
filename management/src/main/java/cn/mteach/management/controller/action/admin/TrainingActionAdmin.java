package cn.mteach.management.controller.action.admin;

import cn.mteach.common.domain.exam.Message;
import cn.mteach.common.domain.training.Training;
import cn.mteach.common.domain.training.TrainingSection;
import cn.mteach.management.security.UserInfo;
import cn.mteach.management.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TrainingActionAdmin {
	@Autowired
	private TrainingService trainingService;
	@RequestMapping(value = "admin/training/add-training", method = RequestMethod.POST)
	public @ResponseBody Message addTraining(@RequestBody Training training) {
		UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Message msg = new Message();
		try {
			training.setCreatorId(userInfo.getUserid());
			trainingService.addTraining(training);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg.setResult(e.getClass().getName());
		}
		return msg;
	}

	/**
	 * 新增培训章节内容
	 * @param section
	 * @return
	 */
	@RequestMapping(value = "admin/training/add-training-section", method = RequestMethod.POST)
	public @ResponseBody Message addTrainingSection(@RequestBody TrainingSection section){
		UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Message msg = new Message();
		try {
			section.setUserId(userInfo.getUserid());
			trainingService.addTrainingSection(section);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg.setResult(e.getClass().getName());
		}
		return msg;
	}

	/**
	 * 删除培训内容
	 * @param training
	 * @return
	 */
	@RequestMapping(value = "admin/training/del-training", method = RequestMethod.POST)
	public @ResponseBody Message delTrainingById(@RequestBody Training training){
		UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Message msg = new Message();
		try {
//			如果是管理员用户userId传入-1
//			if (userInfo.getRolesName().contains("ROLE_ADMIN")){
//				userInfo.setUserid(-1);
//			}
			trainingService.deleteTraining(training.getTrainingId(), userInfo.getUserid());
		} catch (Exception e){
			e.printStackTrace();
			msg.setResult(e.getClass().getName());
		}
		return msg;
	}

	/**
	 * 删除章节内容
	 * @param section
	 * @return
	 */
	@RequestMapping(value = "admin/training/del-training-section", method = RequestMethod.POST)
	public @ResponseBody Message delTrainingSection(@RequestBody TrainingSection section){
		UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Message msg = new Message();
		try {
			trainingService.deleteTrainingSection(section.getSectionId(),userInfo.getUserid());
		} catch (Exception e){
			e.printStackTrace();
			msg.setResult(e.getClass().getName());
		}
		return msg;
	}
}
