package cn.mteach.management.service;

import cn.mteach.common.PropertiesConfig;
import cn.mteach.common.SystemConfig;
import cn.mteach.common.domain.exam.ExamPaper;
import cn.mteach.common.domain.exam.Paper;
import cn.mteach.common.domain.exam.PaperCreatorParam;
import cn.mteach.common.domain.question.*;
import cn.mteach.common.util.CustomXWPFDocument;
import cn.mteach.common.util.Page;
import cn.mteach.common.util.StringUtil;
import cn.mteach.common.util.word.BigQuestion;
import cn.mteach.common.util.word.PaperInfo;
import cn.mteach.common.util.word.SmallQuestion;
import cn.mteach.common.util.word.WordPaper;
import cn.mteach.management.persistence.ExamPaperMapper;
import cn.mteach.management.persistence.QuestionMapper;
import cn.mteach.management.security.UserInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

@Service("examPaperService")
public class ExamPaperServiceImpl implements ExamPaperService {

	@Autowired
	private ExamPaperMapper examPaperMapper;
	@Autowired
	private QuestionMapper questionMapper;
	@Autowired
	private QuestionService questionService;

	private String imageHttpShowPath="";

	@Override
	public List<ExamPaper> getExamPaperList(String searchStr, String paperType, Page<ExamPaper> page) {
		// TODO Auto-generated method stub
		return examPaperMapper.getExamPaperList(searchStr, paperType, page);
	}

	@Override
	public void insertExamPaper(ExamPaper examPaper) {
		// TODO Auto-generated method stub
		examPaperMapper.insertExamPaper(examPaper);
	}

	@Override
	@Transactional
	public void createExamPaper(HashMap<Integer, HashMap<Integer, List<QuestionStruts>>> questionMap,
			HashMap<Integer, Integer> questionTypeNum, HashMap<Integer, Float> questionTypePoint,
			HashMap<Integer, Float> knowledgePointRate, ExamPaper examPaper) {
		// TODO Auto-generated method stub

		HashMap<Integer,String> knowledgeMap = (HashMap<Integer, String>) questionService.getKnowledgePointMap(0);
		HashMap<Integer,String> typeMap = (HashMap<Integer, String>) questionService.getQuestionTypeMap();
		Paper paper = new Paper(questionMap, questionTypeNum, questionTypePoint, knowledgePointRate, knowledgeMap, typeMap);
		try {
			paper.createPaper();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e1.getMessage());
		}

		try {
			HashMap<Integer, QuestionStruts> paperQuestionMap = paper.getPaperQuestionMap();

			Iterator<Integer> it = paperQuestionMap.keySet().iterator();
			List<Integer> idList = new ArrayList<Integer>();
			while (it.hasNext()) {
				idList.add(it.next());
			}
			List<QuestionQueryResult> questionList = questionMapper.getQuestionAnalysisListByIdList(idList);
			for (QuestionQueryResult q : questionList) {
				q.setQuestionPoint(questionTypePoint.get(q.getQuestionTypeId()));
			}
			Gson gson = new Gson();
			examPaper.setContent(gson.toJson(questionList));
			examPaperMapper.insertExamPaper(examPaper);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

	}

	@Override
	public ExamPaper getExamPaperById(int examPaperId) {
		// TODO Auto-generated method stub
		return examPaperMapper.getExamPaperById(examPaperId);
	}

	@Override
	public void updateExamPaper(ExamPaper examPaper) {
		// TODO Auto-generated method stub
		examPaperMapper.updateExamPaper(examPaper);
	}

	@Override
	public void deleteExamPaper(int id) {
		// TODO Auto-generated method stub
		examPaperMapper.deleteExamPaper(id);
	}

	@Override
	public List<ExamPaper> getEnabledExamPaperList(String userName, Page<ExamPaper> page) {
		// TODO Auto-generated method stub
		return examPaperMapper.getEnabledExamPaperList(userName, page);
	}

	@Override
	public void generateDoc(ExamPaper examPaper, String rootPath) throws Exception {
		// TODO Auto-generated method stub
		String basePath = System.getProperty("catalina.base") + ",webapps,";
		String regReplace=PropertiesConfig.getConfigString("/upload/question/header/path");
		String filePath = basePath + "Management,resources,template,doc_tmp.docx";
		//filePath = filePath.replace(',', File.separatorChar);
		filePath = SystemConfig.getExportPaperTempPath(rootPath);
		String tmpFilePath = SystemConfig.getPaperWordTemplatePath(rootPath) + "doc_tmp.docx";
		OPCPackage pack = POIXMLDocument.openPackage(tmpFilePath);
		CustomXWPFDocument doc = new CustomXWPFDocument(pack);
		Gson gson = new Gson();
		String content = examPaper.getContent();
		List<QuestionQueryResult> questionList = gson.fromJson(content, new TypeToken<List<QuestionQueryResult>>(){}.getType());
		// 设置标题
		XWPFParagraph p1 = doc.createParagraph();
		// 设置字体对齐方式
		p1.setAlignment(ParagraphAlignment.CENTER);
		p1.setVerticalAlignment(TextAlignment.TOP);
		// 第一页要使用p1所定义的属性
		XWPFRun r1 = p1.createRun();
		// 设置字体是否加粗
		r1.setBold(true);
		r1.setFontSize(20);
		// 设置使用何种字体
		r1.setFontFamily("Courier");
		// 设置上下两行之间的间距
		r1.setTextPosition(40);
		r1.setText(examPaper.getName());
		
		for(QuestionQueryResult question : questionList){
			QuestionContent questionContent = gson.fromJson(question.getContent(), QuestionContent.class);
			// 设置试题标题
			XWPFParagraph t = doc.createParagraph();
			// 设置字体对齐方式
			t.setAlignment(ParagraphAlignment.LEFT);
			t.setVerticalAlignment(TextAlignment.TOP);
			
			XWPFRun rt = t.createRun();
			// 设置字体是否加粗
			rt.setBold(false);
			rt.setFontSize(15);
			// 设置使用何种字体
			rt.setFontFamily("Courier");
			// 设置上下两行之间的间距
			rt.setTextPosition(40);
			String optTitle= StringUtil.replaceHtml(questionContent.getTitle());
			rt.setText(optTitle + "(" + question.getQuestionPoint() + "分)");
			
			if(!"".equals(questionContent.getTitleImg()) && questionContent.getTitleImg() != null){
			//	String titlePicPath = basePath.replace(',', File.separatorChar);
			//	File titlePic = new File(titlePicPath + questionContent.getTitleImg());
				String pics=questionContent.getTitleImg();
				String arg[]=pics.split(",");
				for(int i=0;i<arg.length;i++){
					String pic=arg[i];
					String picPath=rootPath+pic.substring(pic.indexOf(regReplace),pic.length());
					File titlePic = new File(picPath);
					BufferedImage sourceImg = ImageIO.read(new FileInputStream(titlePic));
					String ind = doc.addPictureData(new FileInputStream(titlePic),
							XWPFDocument.PICTURE_TYPE_JPEG);
					doc.createPicture(doc.getAllPictures().size() - 1,
							sourceImg.getWidth() / 2, sourceImg.getHeight() / 2);
					sourceImg.flush();
				}

			}
			XWPFParagraph crt = doc.createParagraph();
			XWPFRun cr = crt.createRun();
			cr.setText("");
			//选择题和判断题增加选项
			if(question.getQuestionTypeId() == 1 || question.getQuestionTypeId() == 2 || question.getQuestionTypeId() == 3){
				for(Map.Entry<String, String> entry : questionContent.getChoiceList().entrySet()){
					// 设置试题标题
					XWPFParagraph c = doc.createParagraph();
					// 设置字体对齐方式
					c.setAlignment(ParagraphAlignment.LEFT);
					c.setVerticalAlignment(TextAlignment.TOP);
					
					XWPFRun rc = c.createRun();
					// 设置字体是否加粗
					rc.setBold(false);
					rc.setFontSize(15);
					// 设置使用何种字体
					rc.setFontFamily("Courier");
					// 设置上下两行之间的间距
					rc.setTextPosition(40);
					String optValue= StringUtil.replaceHtml(entry.getValue());
					rc.setText(entry.getKey() + " " + optValue);
					
					if(questionContent.getChoiceImgList().containsKey(entry.getKey())){
					//	String picPath = basePath.replace(',', File.separatorChar) + questionContent.getChoiceImgList().get(entry.getKey());
					    String pics=questionContent.getChoiceImgList().get(entry.getKey());
						String arg[]=pics.split(",");
						for(int i=0;i<arg.length;i++){
							String pic=arg[i];
							String picPath=rootPath+pic.substring(pic.indexOf(regReplace),pic.length());
							File picture = new File(picPath);
							BufferedImage sourceImg = ImageIO.read(new FileInputStream(picture));

							String ind = doc.addPictureData(new FileInputStream(picture),
									XWPFDocument.PICTURE_TYPE_JPEG);
							doc.createPicture(doc.getAllPictures().size() - 1,
									sourceImg.getWidth() / 2, sourceImg.getHeight() / 2);
							sourceImg.flush();
						}

					}
					XWPFParagraph crta = doc.createParagraph();
					XWPFRun cra = crta.createRun();
					cra.setText("");
				}
			}
		}
		FileOutputStream out;
		try {
			File f = new File(filePath);
			if(!f.exists()){
				f.mkdirs();
			}
			out = new FileOutputStream(filePath + examPaper.getName() + ".docx");
			doc.write(out);
			out.close();
			System.out.println("success");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * 解析word试卷
	 * @param ServerBasePath
	 * @param HttpShowPath
	 * @param paperCreatorParam
	 * @param userInfo
	 * @return
	 */
	@Transactional
	@Override
	public Map<Integer, String> analyzeWordPaper(String ServerBasePath, String HttpShowPath,
												 PaperCreatorParam paperCreatorParam, UserInfo userInfo){
		Map<Integer, String> result = new HashMap<>();
		Integer paperId = 0;
		String htmlName = paperCreatorParam.getFileName().substring(0, paperCreatorParam.getFileName().lastIndexOf(".")) + ".html";
		//word文件位置
		String wordPath = ServerBasePath + SystemConfig.getWordPaperImportPath(String.valueOf(paperCreatorParam.getFieldId())) + paperCreatorParam.getFileName();
		//html文件的物理路径
		String htmlPath = ServerBasePath + SystemConfig.getHtmlPaperPath(String.valueOf(paperCreatorParam.getFieldId())) + htmlName;
		//html文件http访问路径
		String showPath = HttpShowPath + SystemConfig.getHtmlPaperPath(String.valueOf(paperCreatorParam.getFieldId())).replace("\\","/") + htmlName;
		imageHttpShowPath = HttpShowPath + SystemConfig.getHtmlPaperPath(String.valueOf(paperCreatorParam.getFieldId())).replace("\\","/");
		WordPaper wordPaper = new WordPaper(wordPath,htmlPath);
		wordPaper.setEncodeing(System.getProperty("file.encoding")); //
		if (wordPaper.analysisPaper()){
			PaperInfo paperInfo = wordPaper.getPaperInfo();
			if (paperCreatorParam.getTime() > 0)
				paperInfo.setTimeLength(String.valueOf(paperCreatorParam.getTime()));
			paperId = importPaperInfo(paperInfo, paperCreatorParam.getFieldId(), userInfo, paperCreatorParam.getPaperName(), paperCreatorParam.getPassPoint());
			result.put(paperId,showPath);
		}else{
			throw new RuntimeException("试卷解析出现异常");
		}
		return result;
	}

	/**
	 * 导入试卷内容
	 * @param paperInfo
	 * @return 试卷编号
	 */
	@Override
	public Integer importPaperInfo(PaperInfo paperInfo, int fieldId, UserInfo userInfo, String paperName, int passPoint){
		Integer result;
		Map<Integer,String> questionMap = new HashMap<>();
		//检查重复导入
		ExamPaper paper = examPaperMapper.getExamPaperByName(paperName);
		if (paper != null){
			throw new RuntimeException("试卷重复导入");
		}
		//添加试卷
		ExamPaper examPaper = new ExamPaper();
		try {
			Integer maxQuestionId = questionMapper.getMaxQuestionId();
			String filedName = questionMapper.getFieldById(fieldId).getFieldName();
			List<QuestionType> questionTypeList = questionMapper.getQuestionTypeList();
			paperInfo.setCurrMaxQuestionId(maxQuestionId);
			paperInfo.setFieldName(filedName);
			examPaper.setName(paperName);
			examPaper.setContent(paperInfo.getContent().replace("#icesServerHostUrl#", imageHttpShowPath));
			examPaper.setAnswerSheet(paperInfo.getAnswerSheet());
			examPaper.setDuration(Integer.parseInt(paperInfo.getTimeLength()));
			examPaper.setTotalPoint(Float.parseFloat(paperInfo.getTotalScore()));
			examPaper.setCreator(userInfo.getUsername());
			examPaper.setSubjective(true);
			examPaper.setFieldId(fieldId);
			examPaper.setPassPoint(passPoint);
			examPaperMapper.insertExamPaper(examPaper);
			result = examPaper.getId();
			paperInfo.setPaperId(String.valueOf(result));
			List<KnowledgePoint> knowledgePointList = new ArrayList<>();
			//获取大题
			List<BigQuestion> bigQuestionList = paperInfo.getBigQuestionList();
			for (BigQuestion bigQuestion : bigQuestionList){
				//获取小题
				for (SmallQuestion impQuestion : bigQuestion.getQuestionList()){
					Question question = new Question();
					String knowledge = impQuestion.getKnowledge().replaceAll("[\\\\pP、，。！？：；“”‘’,.!?:;\"']", "");
					question.setName(impQuestion.getQuestionName());
					question.setContent(impQuestion.getContent().replace("#icesServerHostUrl#", imageHttpShowPath));
					question.setAnswer(impQuestion.getAnswers());
					question.setAnalysis(impQuestion.getAnalysis());
					question.setReferenceName(impQuestion.getReferenceName());
					for (QuestionType questionType : questionTypeList) {
						if (questionType.getName().equals(bigQuestion.getType().trim()))
							question.setQuestion_type_id(questionType.getId());
					}
					if (question.getQuestion_type_id()==0)
						throw new RuntimeException("未知题型"+bigQuestion.getType()+"不允许导入");
					question.setPoints(0);
					question.setExpose_times(2);
					question.setRight_times(1);
					question.setWrong_times(1);
					question.setDifficulty(1);
					question.setIs_visible(true);
					question.setCreator(userInfo.getUsername());
					questionMapper.insertQuestion(question);
					//题号-知识点对应关系
					questionMap.put(maxQuestionId++, knowledge);
					//添加知识点
					KnowledgePoint knowledgePoint = new KnowledgePoint();
					knowledgePoint.setPointName(knowledge);
					knowledgePointList.add(knowledgePoint);
				}
			}
			//导入知识点
			importKnowledgePoint(knowledgePointList,fieldId);
			//获取返回的题号和知识点号 建立题号-知识点对应关系表
			List<KnowledgePoint> knowledgePoints = questionService.getKnowledgePointByFieldId(fieldId,null);
			for (KnowledgePoint knowledgePoint : knowledgePoints){
				for (Map.Entry<Integer, String> entry : questionMap.entrySet()){
					if (knowledgePoint.getPointName().equals(entry.getValue())){
						//插入题号知识点序号关系
						questionMapper.addQuestionKnowledgePoint(entry.getKey(), knowledgePoint.getPointId());
					}
				}
			}
		}catch (Exception e){
			throw new RuntimeException(e.getMessage());
		}
		return result;
	}

	/**
	 * 导入知识点
	 * @param knowledgePoints
	 */
	private void importKnowledgePoint(List<KnowledgePoint> knowledgePoints, int fieldId){
		StringBuffer dbContent = new StringBuffer();
		String wordContent = "";
		Set<String> wordPointSet = new HashSet<String>();
		//获取已存在的知识点
		List<KnowledgePoint> knowledgePointList = questionService.getKnowledgePointByFieldId(fieldId,null);
		for (KnowledgePoint knowledgepoint : knowledgePointList){
			dbContent.append("[^" + knowledgepoint.getPointName() + "^]");
		}
		//过滤标点符号
		String dbPointStr = dbContent.toString().replaceAll("[\\\\pP、，。！？：；“”‘’《》,.!?:;\"']","");
		//排除重复知识点
		for (KnowledgePoint knowledgePoint : knowledgePoints){
			wordPointSet.add(knowledgePoint.getPointName());
		}
		//排除已存在知识点
		Iterator<String> it = wordPointSet.iterator();
		while (it.hasNext()){
			String wordPointStr = it.next().replaceAll("[\\\\pP、，。！？：；“”‘’《》,.!?:;\"']","");
			//存在则删除
			if (dbPointStr.toString().contains(wordPointStr)){
				it.remove();
			}
		}
		//保存知识点
		Iterator<String> iter = wordPointSet.iterator();
		while (iter.hasNext()){
			KnowledgePoint knowledgePoint = new KnowledgePoint();
			knowledgePoint.setPointName(iter.next().trim());
			knowledgePoint.setFieldId(fieldId);
			questionMapper.addKnowledgePoint(knowledgePoint);
		}
	}
}
