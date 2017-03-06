package cn.mteach.management.service;

import cn.mteach.common.domain.jyeoo.*;
import cn.mteach.common.domain.question.Field;
import cn.mteach.common.domain.question.Reference;
import cn.mteach.common.jyeoo.api.BaseInfoApiService;
import cn.mteach.common.jyeoo.api.PaperApiService;
import cn.mteach.common.jyeoo.api.TeachingMaterialApiService;
import cn.mteach.common.jyeoo.util.JsonUtil;
import cn.mteach.common.util.Page;
import cn.mteach.common.util.ehcache.GetCacheData;
import fr.opensagres.xdocreport.document.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by wuliangpu on 2017/2/17.
 */
@Service("saveApiDataService")
public class SaveApiDataServiceImpl implements SaveApiDataService{

    @Autowired
    private QuestionService questionService;
    @Autowired
    private JyeooService jyeooService;
    /**
     * 保存api中的学科
     */
    public void saveSubjectFromApi() {
        BaseInfoApiService baseInfoApiService =new BaseInfoApiService();
        String token= GetCacheData.getJyeooToken();
        try {
            List subjectList= baseInfoApiService.getSubjects(token);
            for(int i=0;i<subjectList.size();i++){
                List list=(List)subjectList.get(i);
                String keyId=(String)list.get(0);
                String englisthName=(String)list.get(1);
                String name=(String)list.get(2);
                Boolean exist= questionService.isExistSubject(name);
                if(!exist){
                    Field field=new Field();
                    field.setFieldName(name);
                    field.setMemo(name);
                    field.setEnglishName(englisthName);
                    field.setState(true);
                    field.setKeyId(keyId);
                    questionService.addField(field);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存api中所有来源
     */
    public void saveSourceFromApi() {
        TeachingMaterialApiService teachingMaterialService=new TeachingMaterialApiService();
        String token= GetCacheData.getJyeooToken();
        try {
            Page<Field> page=new Page<>();
            page.setPageSize(100);
            List<Field> subjectList=questionService.getAllField(page);
            for(int i=0;i<subjectList.size();i++){
                Field field=subjectList.get(i);
                String englisthName=field.getEnglishName();
                if(englisthName==null){
                    continue;
                }
                String json=teachingMaterialService.getCommon(token,englisthName,2,0);
                List<Map> list= JsonUtil.jsonMapToList(json);
                for(int j=0;j<list.size();j++){
                    Map map=list.get(j);
                    String referenceName=(String)map.get("Value");
                    String keyId=String.valueOf(map.get("Key"));
                    Boolean exist= questionService.isExistReference(referenceName,englisthName);
                    if(!exist){
                        Reference reference=new Reference();
                        reference.setReferenceName(referenceName);
                        reference.setSubjectName(englisthName);
                        reference.setState(true);
                        reference.setKeyId(keyId);
                        questionService.addReference(reference);
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存api中 年级
     */
    public void saveGradeFromApi() {
        BaseInfoApiService baseInfoApiService =new BaseInfoApiService();
        String token= GetCacheData.getJyeooToken();
        try {
            String json= baseInfoApiService.getGrades(token);
            List<Map> gradeList=JsonUtil.jsonMapToList(json);
            for(int i=0;i<gradeList.size();i++){
                Map map=gradeList.get(i);
                String gradeName=(String)map.get("Value");
                String keyId=String.valueOf(map.get("Key"));
                Boolean exist= jyeooService.isExistGrade(gradeName);
                if(!exist){
                    Grade grade=new Grade();
                    grade.setGradeName(gradeName);
                    grade.setState(1);
                    grade.setKeyId(keyId);
                    jyeooService.addGrade(grade);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存api中所有版本
     */
    public void saveVersionFromApi() {
        TeachingMaterialApiService teachingMaterialService=new TeachingMaterialApiService();
        String token= GetCacheData.getJyeooToken();
        try {
            Page<Field> page=new Page<>();
            page.setPageSize(100);
            List<Field> subjectList=questionService.getAllField(page);
            for(int i=0;i<subjectList.size();i++){
                Field field=subjectList.get(i);
                String englisthName=field.getEnglishName();
                if(englisthName==null){
                    continue;
                }
                String json=teachingMaterialService.getCommon(token,englisthName,3,0);
                List<Map> list= JsonUtil.jsonMapToList(json);
                for(int j=0;j<list.size();j++){
                    Map map=list.get(j);
                    String versionName=(String)map.get("Value");
                    String keyId=String.valueOf(map.get("Key"));
                    Boolean exist= jyeooService.isExistVersion(versionName, englisthName);
                    if(!exist){
                        Version version=new Version();
                        version.setVersionName(versionName);
                        version.setState(1);
                        version.setSubjectName(englisthName);
                        version.setKeyId(keyId);
                        jyeooService.addVersion(version);
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存组卷
     * @param subjectName 学科英文名称
     * @param tp 分类（0：最新组卷；1：最热组卷；2：用户组卷）
     * @param pi 当前页（以1开始）
     * @param ps 每页记录数
     * @return
     */
    public List<UserPaper> savePaperFromPai(String subjectName,int tp,int pi,int ps){
        String token= GetCacheData.getJyeooToken();
        PaperApiService paperApiService=new PaperApiService();
        try {
          String json= paperApiService.searchPaper(token,subjectName,tp,pi,ps);
            JSONObject jsonObject=new JSONObject(json);
            List list= (List)jsonObject.get("Data");
            for(int i=0;i<list.size();i++){
                JyPaper jyPaper=new JyPaper();
                Map map=(Map)list.get(i);
                jyPaper.setId(map.get("ID").toString());
                jyPaper.setTitle(map.get("Title").toString());
                jyPaper.setDescription(map.get("Description").toString());
                jyPaper.setQuestionXml(map.get("QuestionXml").toString());
                jyPaper.setViewCount((Integer)map.get("ViewCount"));
                jyPaper.setDownCount((Integer)map.get("DownCount"));
                jyPaper.setSubject((Integer)map.get("Subject"));
                jyPaper.setScore((Integer)map.get("Score"));
                jyPaper.setState(1);
                jyPaper.setCreatorId(map.get("CreatorID").toString());
                jyPaper.setCreatorDate(map.get("CreateDate").toString());
                jyeooService.addJyPaper(jyPaper);
            }
            System.out.print(json);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
