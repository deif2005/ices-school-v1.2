package cn.mteach.management.controller.action;

import cn.mteach.common.FileConstants;
import cn.mteach.common.SystemConfig;
import cn.mteach.common.UploadFileResponse;
import cn.mteach.common.domain.exam.Message;
import cn.mteach.common.domain.jyeoo.QuestionParam;
import cn.mteach.common.domain.question.Chapter;
import cn.mteach.common.domain.question.KnowledgePoint;
import cn.mteach.common.domain.question.Question;
import cn.mteach.common.domain.question.QuestionTag;
import cn.mteach.common.util.file.FileUploadUtil;
import cn.mteach.management.controller.action.admin.JyeooApi;
import cn.mteach.management.security.UserInfo;
import cn.mteach.management.service.JyeooService;
import cn.mteach.management.service.QuestionService;
import cn.mteach.management.service.SaveApiDataService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.rabbitmq.client.AMQP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
public class QuestionAction {

    @Autowired
	private QuestionService questionService;

	@Autowired
	private SaveApiDataService saveApiDataService;

	/**
	 * 添加试题
	 * 
	 * @param question
	 * @return
	 */
	@RequestMapping(value = "/secure/question/question-add", method = RequestMethod.POST)
	public @ResponseBody Message addQuestion(@RequestBody Question question) {

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Message message = new Message();
		Gson gson = new Gson();
		question.setContent(gson.toJson(question.getQuestionContent()));
		question.setCreate_time(new Date());
		question.setCreator(userDetails.getUsername());
		try {
			questionService.addQuestion(question);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			message.setResult("error");
			message.setMessageInfo(e.getClass().getName());
			e.printStackTrace();
		}

		return message;
	}

	/**
	 * 获取试题的标签列表
	 * 
	 * @param questionId
	 * @return
	 */
	@RequestMapping(value = "/secure/question/question-tag/{questionId}", method = RequestMethod.GET)
	public @ResponseBody Message getQuestionTag(@PathVariable("questionId") int questionId) {
		Message message = new Message();
		UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<QuestionTag> tagList = questionService.getQuestionTagByQuestionIdAndUserId(questionId,
				userInfo.getUserid(), null);
		message.setObject(tagList);
		return message;
	}

	/**
	 * 为试题添加标签
	 * 
	 * @param questionId
	 * @param questionTagList
	 * @return
	 */
	@RequestMapping(value = "/secure/question/add-question-tag", method = RequestMethod.POST)
	public @ResponseBody Message addQuestionTag(@RequestBody int questionId,
			@RequestBody List<QuestionTag> questionTagList) {
		Message message = new Message();
		UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		try {
			questionService.addQuestionTag(questionId, userInfo.getUserid(), questionTagList);
		} catch (Exception e) {
			e.printStackTrace();
			message.setResult(e.getClass().getName());
		}

		return message;
	}

	/**
	 * 获取试题详细信息
	 * @param questionId
	 * @return
	 */
	@RequestMapping(value = "/secure/question/question-detail/{questionId}", method = RequestMethod.GET)
	public @ResponseBody Message getQuestionDetail(@PathVariable("questionId") int questionId) {
		Message message = new Message();
		//UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		try {
			Question question = questionService.getQuestionDetail(questionId, 0);
			message.setObject(question);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message.setResult(e.getCause().getMessage());
		}
		return message;
	}
	/**
	 * 修改试题知识点
	 * 
	 * @param questionId
	 * @param pointId
	 * @param questionTagList
	 * @return
	 */
	@RequestMapping(value = "/secure/question/question-update/{questionId}/{pointId}", method = RequestMethod.POST)
	public @ResponseBody Message updateQuestionKnowledge(@PathVariable int questionId, @PathVariable int pointId,
			@RequestBody List<QuestionTag> questionTagList) {

		Message message = new Message();
		UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Question question = new Question();
		question.setId(questionId);
		List<Integer> pointIdList = new ArrayList<Integer>();
		pointIdList.add(pointId);
		question.setPointList(pointIdList);
		try {
			questionService.updateQuestionPoint(question, userInfo.getUserid(), questionTagList);
		} catch (Exception e) {
			message.setResult(e.getClass().getName());
		}

		return message;
	}
	
	@RequestMapping(value = "/secure/question/question-update", method = RequestMethod.POST)
	public @ResponseBody Message updateQuestion(@RequestBody String jsonStr){
		Message msg = new Message();
		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(jsonStr);
		List<QuestionTag> questionTagList = gson.fromJson(element.getAsJsonObject().get("tags"), new TypeToken<ArrayList<QuestionTag>>(){}.getType());
		Question question = gson.fromJson(element.getAsJsonObject().get("question"), Question.class);
		try {
			questionService.updateQuestion(question, questionTagList);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg.setResult(e.getCause().getMessage());
		}
		//TO-DO:需要提交到数据库，保证在事务中提交
		return msg;
	}

	@RequestMapping(value = "/secure/question/get-knowledge-point/{fieldId}", method = RequestMethod.GET)
	public @ResponseBody Message getQuestionPointByFieldId(@PathVariable int fieldId) {
		Message message = new Message();
		HashMap<Integer, String> pointMap = new HashMap<Integer, String>();
		List<KnowledgePoint> pointList = questionService.getKnowledgePointByFieldId(fieldId, null);
		for (KnowledgePoint point : pointList) {
			pointMap.put(point.getPointId(), point.getPointName());
		}
		message.setObject(pointMap);
		return message;
	}

	@RequestMapping(value = "/secure/question/delete-question/{questionId}", method = RequestMethod.GET)
	public @ResponseBody Message deleteQuestion(Model model, @PathVariable("questionId") int questionId) {

		// UserDetails userDetails = (UserDetails)
		// SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Message message = new Message();
		try {
			questionService.deleteQuestionByQuestionId(questionId);
		} catch (Exception ex) {
			message.setResult(ex.getClass().getName());
		}

		return message;
	}

	@RequestMapping(value = "/secure/upload-uploadify-img", method = RequestMethod.POST)
	public @ResponseBody String uploadImg(HttpServletRequest request, HttpServletResponse response) {
		UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<String> filePathList = new ArrayList<String>();
		try {
			filePathList = FileUploadUtil.uploadImg(request, response, userInfo.getUsername());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (filePathList.size() == 0) {
			return "系统错误";
		}

		return filePathList.get(0);
	}

	/**
	 * 上传图片文件
	 * @param request
	 * @param fileType
	 * @return
	 */
	@RequestMapping(value = "/secure/upload-question-img", method = RequestMethod.POST)
	public @ResponseBody List<String> uploadQuestionImg(HttpServletRequest request,String fileType) {
		List<String> resultList = new ArrayList<>();
		String showPath;
		String fileName;
		try {
			if (!FileConstants.UPLOAD_IMAGE_SUFFIX.contains(fileType)) {
				throw new Exception("上传的图片文件类型错误");
			}
			String relPath = SystemConfig.getQuestionUploadPath(fileType);
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


	@RequestMapping(value = "/secure/upload-uploadify", method = RequestMethod.POST)
	public @ResponseBody String uploadFile(HttpServletRequest request, HttpServletResponse response) {
		UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<String> filePathList = new ArrayList<String>();
		try {
			filePathList = FileUploadUtil.uploadQuestionFile(request, response, userInfo.getUsername());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (filePathList.size() == 0) {
			return "系统错误";
		}
		return filePathList.get(0);
	}
	
	@RequestMapping(value = "/secure/question-import/{id}", method = RequestMethod.POST)
	public @ResponseBody Message courseImport(@RequestBody String filePath, @PathVariable("id") int id) {
		Message message = new Message();
		UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		if(id == 0){
			message.setResult("error");
			message.setMessageInfo("请选择题库");
			return message;
		}
		try{
			questionService.uploadQuestions(filePath, userInfo.getUsername(),id);
		}catch(RuntimeException e){
			message.setResult(e.getClass().getName() + ":" + e.getMessage());
			message.setMessageInfo(e.getMessage());
		}
		return message;
	}

	@RequestMapping(value = "/secure/getChapter", method = RequestMethod.GET)
	public @ResponseBody Message getChapter(Chapter chapter){
		chapter.setSubject(20);
		chapter.setSubjectName("math");
		chapter.setGradeId(7);
		chapter.setTermId(1);
		chapter.setEditionId(14);
		saveApiDataService.saveSubjectFromApi();
		questionService.getChapter(chapter);
		return null;
	}

	@RequestMapping(value = "/secure/getquestion", method = RequestMethod.GET)
	public @ResponseBody Message getQuestion(QuestionParam questionParam){
		questionParam.setSubject("math");
		questionParam.setTp(1);
		questionParam.setP1("75a08844-6562-4bf5-a182-034cf7929588");
		questionParam.setP2("c9032643-4635-41bf-aa80-5ad2e0491170");
		questionParam.setP3("");
		questionParam.setDg(0);
		questionParam.setCt(0);
		questionParam.setSc(false);
		questionParam.setGc(false);
		questionParam.setRc(false);
		questionParam.setYc(false);
		questionParam.setEc(false);
		questionParam.setEr(false);
		questionParam.setPo(0);
		questionParam.setPd(0);
		questionParam.setPi(1);
		questionParam.setPs(10);
		questionService.getRemortQuestion(questionParam);
		return null;
	}
}
