package cn.mteach.management.controller.action.admin;

import cn.mteach.common.domain.exam.Message;
import cn.mteach.common.domain.jyeoo.Grade;
import cn.mteach.common.domain.jyeoo.QuestionLevel;
import cn.mteach.common.domain.jyeoo.QuestionSort;
import cn.mteach.common.domain.jyeoo.Version;
import cn.mteach.common.domain.question.Chapter;
import cn.mteach.common.domain.question.Field;
import cn.mteach.common.domain.question.Reference;
import cn.mteach.common.jyeoo.api.BaseInfoApiService;
import cn.mteach.common.jyeoo.api.PaperApiService;
import cn.mteach.common.jyeoo.api.TeachingMaterialApiService;
import cn.mteach.common.jyeoo.util.JsonUtil;
import cn.mteach.common.util.Page;
import cn.mteach.common.util.ehcache.GetCacheData;
import cn.mteach.management.service.JyeooService;
import cn.mteach.management.service.QuestionService;
import cn.mteach.management.service.SaveApiDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by wuliangpu on 2017/2/14.
 */

@Controller
@RequestMapping(value = "/jyeoo")
public class JyeooApi {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private JyeooService jyeooService;
    @Autowired
    private SaveApiDataService saveApiDataService;
    /**
     * 保存api中的学科
     */
    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void saveSubjectFromApi(HttpServletResponse response) throws Exception{
    //    saveApiDataService.saveSubjectFromApi();

//        Chapter chapter=new Chapter();
//        chapter.setSubjectName("math");
//        chapter.setEditionId(1);
//        chapter.setGradeId(7);
//        chapter.setTermId(1);
//        questionService.getChapter(chapter);

         saveApiDataService.savePaperFromPai("math",1,100,11);

    }

    /**
     * 下载试卷
     * @param subject 学科英文名称
     * @param paperId 试卷id
     * @param method 解析（1.分析、2.解答、4.点评、8.考点、16.专题）
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/downPaper", method = RequestMethod.GET)
    public Object downPaper(HttpServletResponse response,String subject,String paperId,int method) throws Exception{
        PaperApiService paperApiService=new PaperApiService();
        String token=GetCacheData.getJyeooToken();
        byte[] buffer= paperApiService.downPaper(token, subject, paperId,method);
   //     byte[] buffer= paperApiService.downPaper(GetCacheData.getJyeooToken(), "math", "13c0e1b0-93b2-4321-b677-c04357ce9043",4);
        response.addHeader("Content-Disposition", "attachment;filename=paper.doc");
        OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
        response.setContentType("application/octet-stream");
        outputStream.write(buffer);
        outputStream.flush();
        outputStream.close();
        return response;
    }
    /**
     * 获得所有学科
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAllSubject", method = RequestMethod.GET)
    public Object getAllSubject() {
        Message msg = new Message();
        Page<Field> page=new Page<>();
        page.setPageSize(100);
        List<Field> list=questionService.getAllField(page);
        msg.setObject(list);
        return msg;
    }

    /**
     * 根据学科获得来源
     * @param subjectName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getSourceBySubject", method = RequestMethod.GET)
    public Object getSourceBySubject(String subjectName) {
        Message msg = new Message();
        List<Reference> list=jyeooService.getSourceBySubject(subjectName);
        msg.setObject(list);
        return msg;
    }
    /**
     * 根据学科获得版本
     * @param subjectName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getVersionBySubject", method = RequestMethod.GET)
    public Object getVersionBySubject(String subjectName) {
        Message msg = new Message();
        List<Version> list=jyeooService.getVersionBySubject(subjectName);
        msg.setObject(list);
        return msg;
    }

    /**
     * 获得题目难度
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getQuestionLevel", method = RequestMethod.GET)
    public Object getQuestionLevel() {
        Message msg = new Message();
        List<QuestionLevel> list=jyeooService.getQuestionLevel();
        msg.setObject(list);
        return msg;
    }

    /**
     * 获得题目题类
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getQuestionSort", method = RequestMethod.GET)
    public Object getQuestionSort() {
        Message msg = new Message();
        List<QuestionSort> list=jyeooService.getQuestionSort();
        msg.setObject(list);
        return msg;
    }



}
