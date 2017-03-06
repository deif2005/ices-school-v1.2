package cn.mteach.management.controller.action.admin;

import cn.mteach.common.SystemConfig;
import cn.mteach.common.UploadFileResponse;
import cn.mteach.common.domain.exam.*;
import cn.mteach.common.domain.question.QuestionQueryResult;
import cn.mteach.common.domain.question.QuestionStruts;
import cn.mteach.common.util.QuestionAdapter;
import cn.mteach.common.util.file.FileUploadUtil;
import cn.mteach.common.util.file.FileUtil;
import cn.mteach.management.security.UserInfo;
import cn.mteach.management.service.ExamPaperService;
import cn.mteach.management.service.QuestionService;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ExamPaperActionAdmin {
	private Logger log = Logger.getLogger(ExamPaperActionAdmin.class);

	@Autowired
	private ExamPaperService examPaperService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;

	/**
	 * 自动或者手动组卷(插入一张空试卷)
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "admin/exampaper-add", method = RequestMethod.POST)
	public @ResponseBody
	Message createExamPaper(@RequestBody PaperCreatorParam param) {

		UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		Message message = new Message();
		ExamPaper examPaper = new ExamPaper();
		examPaper.setName(param.getPaperName());
		examPaper.setDuration(param.getTime());
		examPaper.setPassPoint(param.getPassPoint());
		examPaper.setFieldId(param.getPaperType());
		examPaper.setCreator(userInfo.getUsername());
		examPaper.setTotalPoint(param.getPaperPoint());
		examPaper.setSubjective(true);
		
		//手工组卷
		if(param.getQuestionKnowledgePointRate().size() == 0){
			try{
				
				examPaperService.insertExamPaper(examPaper);
			}catch(Exception ex){
				message.setResult(ex.getMessage());
			}
			message.setGeneratedId(examPaper.getId());
			return message;
		}
		List<Integer> idList = new ArrayList<Integer>();

		HashMap<Integer, Float> knowledgeMap = param
				.getQuestionKnowledgePointRate();
		Iterator<Integer> it = knowledgeMap.keySet().iterator();
		while(it.hasNext()){
			idList.add(it.next());
		}

		HashMap<Integer, HashMap<Integer, List<QuestionStruts>>> questionMap = questionService
				.getQuestionStrutsMap(idList);
		
		try{
			examPaperService.createExamPaper(questionMap, param.getQuestionTypeNum(),
					param.getQuestionTypePoint(),
					param.getQuestionKnowledgePointRate(), examPaper);
			message.setGeneratedId(examPaper.getId());
		}catch(Exception e){
			//e.printStackTrace();
			message.setResult(e.getMessage());
		}
		return message;
	}
	
	/**
	 * 批量添加试题
	 * @param model
	 * @param idList
	 * @return
	 */
	@RequestMapping(value = "/admin/exampaper/get-question-detail4add", method = RequestMethod.POST)
	public @ResponseBody List<QuestionQueryResult> getQuestion5add(Model model, HttpServletRequest request, @RequestBody List<Integer> idList) {
		String strUrl = "http://" + request.getServerName() // 服务器地址
				+ ":" + request.getServerPort() + "/";
		
		Set<Integer> set = new TreeSet<Integer>();
		for(int id : idList){
			set.add(id);
		}
		idList.clear();
		Iterator<Integer> it = set.iterator();
		while(it.hasNext()){
			idList.add(it.next());
		}
		List<QuestionQueryResult> returnList = questionService.getQuestionDescribeListByIdList(idList);
		
		for(QuestionQueryResult question : returnList){
			QuestionAdapter adapter = new QuestionAdapter(question, strUrl);
			question.setContent(adapter.getStringFromXML());
		}
		return returnList;
	}

	/**
	 * 修改试卷
	 * @param model
	 * @param examPaperId
	 * @param questionPointMap
	 * @return
	 */
	@RequestMapping(value = "/admin/exampaper/update-exampaper/{examPaperId}", method = RequestMethod.POST)
	public @ResponseBody
	Message exampaperOnUpdate(Model model,
			@PathVariable("examPaperId") int examPaperId,
			@RequestBody HashMap<Integer, Float> questionPointMap) {
		Message message = new Message();
		try{
			ExamPaper examPaper = new ExamPaper();
			Integer passPoint = examPaperService.getExamPaperById(examPaperId).getPassPoint();
			List<Integer> idList = new ArrayList<Integer>();
			Iterator<Integer> it = questionPointMap.keySet().iterator();
			float sum = 0;
			while(it.hasNext()){
				int key = it.next();
				idList.add(key);
			}
			List<QuestionQueryResult> questionList = questionService.getQuestionDescribeListByIdList(idList);
			AnswerSheet as = new AnswerSheet();
			as.setExamPaperId(examPaperId);
			List<AnswerSheetItem> asList = new ArrayList<AnswerSheetItem>();
			for(QuestionQueryResult q : questionList){
				AnswerSheetItem item = new AnswerSheetItem();
				item.setAnswer(q.getAnswer());
				item.setQuestionId(q.getQuestionId());
				item.setPoint(questionPointMap.get(q.getQuestionId()));
				item.setQuestionTypeId(q.getQuestionTypeId());
				q.setQuestionPoint(questionPointMap.get(q.getQuestionId()));
				//小题分检测
				if(questionPointMap.get(q.getQuestionId()) == 0){
//					throw new RuntimeException("第"+q.getQuestionId()+"小题分数未设定");
					message.setResult("小题"+q.getQuestionId()+"分数未设定");
					return message;
				}
				sum += questionPointMap.get(q.getQuestionId());
				asList.add(item);
			}
			if (passPoint > sum){
				message.setResult("总分不能小于及格分数");
				return message;
			}
			as.setPointMax(sum);
			as.setAnswerSheetItems(asList);
			Gson gson = new Gson();
			String content = gson.toJson(questionList);
			String answerSheet = gson.toJson(as);
			examPaper.setAnswerSheet(answerSheet);
			examPaper.setContent(content);
			examPaper.setTotalPoint(sum);
			examPaper.setId(examPaperId);
			
			//这两个属性区别试卷是否可用
			//examPaper.setIs_subjective(true);
			//examPaper.setStatus(1);
			examPaperService.updateExamPaper(examPaper);
		} catch(Exception e){
			log.error("exampaperOnUpdate",e);
			message.setResult(e.getLocalizedMessage());

		}
		return message;
	}

	/**
	 * 删除试卷
	 * @param examPaperId
	 * @return
	 */
	@RequestMapping(value = "admin/exampaper/paper-delete", method = RequestMethod.POST)
	public @ResponseBody Message deleteExamPaper(@RequestBody Integer examPaperId){
		Message message = new Message();
		try{
			ExamPaper examPaper = examPaperService.getExamPaperById(examPaperId);
			if(examPaper.getStatus() == 1){
				message.setResult("已发布的试卷不允许删除");
				return message;
			}
			examPaperService.deleteExamPaper(examPaperId);
		}catch(Exception e){
			message.setResult(e.getClass().getName());
		}
		return message;
	}
	
	/**
	 * 生成试卷doc
	 * @param examPaperId
	 * @return
	 */
	@RequestMapping(value = "admin/exampaper/create-doc-{examPaperId}", method = RequestMethod.GET)
	public @ResponseBody Message createDocPaper(@PathVariable("examPaperId") int examPaperId){
		Message msg = new Message();
		UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
//		String basePath = SystemConfig.getStaticUploadBasePath() + SystemConfig.getExportPaperTempPath();
//		String filePath = basePath + "doc_tmp.docx";
//		FileUtil.createFilePath(filePath);
		String dateStr = new SimpleDateFormat("yyyyMMddhh24mmss").format(new Date());
		String path = request.getSession().getServletContext().getRealPath("/");
//		String filePath = System.getProperty("catalina.base")
//				+ ",webapps,files,tmp," + userInfo.getUsername() + "," + dateStr;
//		String relativePath = "files,tmp," + userInfo.getUsername() + "," + dateStr;
		ExamPaper examPaper = examPaperService.getExamPaperById(examPaperId);
		try {
			examPaperService.generateDoc(examPaper, path);//filePath.replace(",", File.separator)
			FileUtil.download(request, response, SystemConfig.getExportPaperTempPath(path) + examPaper.getName() + ".docx", examPaper.getName() + ".docx");
			msg.setMessageInfo((SystemConfig.getExportPaperTempPath(path) + examPaper.getName() + ".docx").replace(",", File.separator));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("createDocPaper", e);
		}
		return msg;
	}

	/**
	 * 上传word试卷
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/admin/exampaper/upload-word-{fieldId}/{fileType}", method = RequestMethod.POST)
	@ResponseBody
	public Message uploadWordPaper(HttpServletRequest request, @PathVariable("fieldId") String fieldId,
								   @PathVariable("fileType") String fileType){
		Message message = new Message();
		if (!"docx,doc".contains(fileType)){
			throw new RuntimeException("导入的文件类型错误，必须为word文档");
		}
		try {
			String relaPath = SystemConfig.getWordPaperImportPath(fieldId);
			UploadFileResponse uploadFileResponse = FileUploadUtil.uploadFile(request, relaPath);
			message.setMessageInfo(uploadFileResponse.getFirstNewFileName());
		}catch (Exception e){
			// TODO Auto-generated catch block
			message.setResult("error");
			log.error("uploadWordPaper", e);
		}
		return message;
	}

	/**
	 * 导入word试卷，设置试卷信息
	 * @param paperCreatorParam
	 * @return
	 */
	@RequestMapping(value = "/admin/exampaper/paper-import", method = RequestMethod.POST)
	public @ResponseBody Message importPaper(@RequestBody PaperCreatorParam paperCreatorParam){
		UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Message message = new Message();
		try {
			//paperCreatorParam.setFieldId(1);
			Map<Integer, String> result = examPaperService.analyzeWordPaper(SystemConfig.getStaticResourcePath(request),
					SystemConfig.getHttpStaticResourcePath(request), paperCreatorParam, userInfo);
			Iterator iterator = result.entrySet().iterator();
			Map.Entry<Integer, String> entry = (Map.Entry)iterator.next();
			message.setGeneratedId(entry.getKey());
			message.setMessageInfo(entry.getValue());
		}catch (Exception e){
			message.setResult("error");
			log.error("importPaper", e);
		}
		return message;
	}

}
