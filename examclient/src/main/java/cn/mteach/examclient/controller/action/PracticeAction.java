package cn.mteach.examclient.controller.action;

import cn.mteach.common.FileConstants;
import cn.mteach.common.SystemConfig;
import cn.mteach.common.UploadFileResponse;
import cn.mteach.common.domain.exam.Message;
import cn.mteach.common.domain.exam.UserQuestionHistory;
import cn.mteach.common.domain.question.QuestionHistory;
import cn.mteach.common.util.file.FileUploadUtil;
import cn.mteach.examclient.security.UserInfo;
import cn.mteach.examclient.service.QuestionHistoryService;
import cn.mteach.examclient.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PracticeAction {
	
	@Autowired
	private QuestionService questionService;
	@Autowired
	private QuestionHistoryService questionHistoryService;
	/**
	 * 练习模式完成一道题
	 * 
	 * @param sp
	 * @return
	 */
	@RequestMapping(value = "/student/practice-list/practice-improve", method = RequestMethod.POST)
	public @ResponseBody Message submitPractice(@RequestBody QuestionHistory qh) {
		Message msg = new Message();
		UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserQuestionHistory history = new UserQuestionHistory();
		history.setQuestionId(qh.getQuestionId());
		history.setUserId(userInfo.getUserid());
		history.setQuestionTypeId(qh.getQuestionTypeId());
		boolean isRight = qh.getAnswer().equals(qh.getMyAnswer()) ? true : false;
		history.setRight(isRight);
		
		try {
			questionHistoryService.addUserQuestionHist(history);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			msg.setResult(e.getClass().getName());
			e.printStackTrace();
		}

		return msg;
	}

	/**
	 * 上传图片文件
	 * @param request
	 * @param fileType
	 * @return
	 */
	@RequestMapping(value = "/secure/upload-question-img", method = RequestMethod.POST)
	public @ResponseBody List<String> uploadQuestionImg(HttpServletRequest request,String fileType,String examTimeId) {
		UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<String> resultList = new ArrayList<>();
		String showPath;
		String fileName;
		try {
			if (!FileConstants.UPLOAD_IMAGE_SUFFIX.contains(fileType)) {
				//throw new Exception("上传的图片文件类型错误");
			}
			String relPath = SystemConfig.getUserQuestionResourceUploadPath(examTimeId,String.valueOf(userInfo.getUserid()));
			String baseShowPath = SystemConfig.getHttpStaticResourcePath(request);
			UploadFileResponse uploadFileResponse = FileUploadUtil.uploadFile(request, relPath);
			String OriginalFilename = uploadFileResponse.getFirstOriginalFileName();
			fileName = uploadFileResponse.getFirstNewFileName();
			//fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
			showPath = baseShowPath + relPath + fileName;
			resultList.add(showPath);
			resultList.add(OriginalFilename);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			log.error("QuestionController-uploadPicture:", e);
		}
		return resultList;
	}
}
