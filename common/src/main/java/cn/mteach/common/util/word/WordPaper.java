package cn.mteach.common.util.word;

import cn.mteach.common.util.StringUtil;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.*;

import com.google.gson.Gson;

/**
 * Created by Sugior on 2016/12/6.
 */
public class WordPaper {
    private String wordPaperFile = "";
    private String htmlPaperFile = "";
    private String html;
    private PaperInfo paperInfo;
    private Map<String, Integer> questionTypeList;
    private String paperId;
    private String fieldName;
    private Integer currMaxQuestionId;
    private String encodeing = "utf-8";

    public String getEncodeing() {
        return encodeing;
    }

    public void setEncodeing(String encodeing) {
        this.encodeing = encodeing;
    }

    public String getWordPaperFile() {
        return wordPaperFile;
    }

    public void setWordPaperFile(String wordPaperFile) {
        this.wordPaperFile = wordPaperFile;
    }

    public String getHtmlPaperFile() {
        return htmlPaperFile;
    }

    public void setHtmlPaperFile(String htmlFile) {
        this.htmlPaperFile = htmlFile;
    }

    public String getHtml() {
        return html;
    }

    public PaperInfo getPaperInfo() {
        return paperInfo;
    }

    public Integer getCurrMaxQuestionId() {
        return currMaxQuestionId;
    }

    public void setCurrMaxQuestionId(Integer currMaxQuestionId) {
        this.currMaxQuestionId = currMaxQuestionId;
        if (paperInfo != null) {
            paperInfo.setCurrMaxQuestionId(currMaxQuestionId);
        }
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
        if (paperInfo != null) {
            paperInfo.setFieldName(fieldName);
        }
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
        if (paperInfo != null) {
            paperInfo.setPaperId(paperId);
        }
    }

    public Map<String, Integer> getQuestionTypeList() {
        return questionTypeList;
    }

    public void setQuestionTypeList(Map<String, Integer> questionTypeList) {
        this.questionTypeList = questionTypeList;
    }

    public WordPaper() {
        this.questionTypeList = getDefultQuestionTypeList();
    }

    public WordPaper(String wordInputFile, String htmlOutputFile) {
        setWordPaperFile(wordInputFile);
        setHtmlPaperFile(htmlOutputFile);
        this.questionTypeList = getDefultQuestionTypeList();
    }

    public boolean analysisPaper() {
        boolean isAnalysis;
        try {
            String htmlStr = "";
            String tempHtmlFile = StringUtil.chanageExtensionName(wordPaperFile, ".html");
            tempHtmlFile = "".equals(htmlPaperFile) ? tempHtmlFile : htmlPaperFile;
            if (new File(wordPaperFile).getName().toLowerCase().endsWith(".doc")) {
                htmlStr = WordConverter.docConvertToHtmlFile(wordPaperFile, tempHtmlFile, encodeing);
            } else {
                htmlStr = WordConverter.docxConvertToHtmlFile(wordPaperFile, tempHtmlFile, encodeing);
            }
            htmlStr = WordAnalysis.analysisHtmlString(htmlStr);
            paperInfo = WordAnalysis.getPaperInfo(htmlStr);
            paperInfo.setPaperId(paperId);
            paperInfo.setFieldName(fieldName);
            paperInfo.setCurrMaxQuestionId(currMaxQuestionId);
            paperInfo.setQuestionTypeList(questionTypeList);
            isAnalysis = true;
        } catch (IOException e) {
            isAnalysis = false;
        } catch (TransformerException e) {
            isAnalysis = false;
        } catch (ParserConfigurationException e) {
            isAnalysis = false;
        }
        return isAnalysis;
    }

    private Map<String, Integer> getDefultQuestionTypeList() {
        Map<String, Integer> questionTypeMap = new HashMap<>();
        questionTypeMap.put("单选题", 1);
        questionTypeMap.put("多选题", 2);
        questionTypeMap.put("判断题", 3);
        questionTypeMap.put("填空题", 4);
        questionTypeMap.put("简答题", 5);
        questionTypeMap.put("论述题", 6);
        questionTypeMap.put("分析题", 7);
        questionTypeMap.put("计算题", 8);
        questionTypeMap.put("综合题", 9);
        questionTypeMap.put("作文题", 10);
        return questionTypeMap;
    }

    public static void main(String argv[]) {
        try {
            Gson gson = new Gson();
            WordPaper wordPaper = new WordPaper();
            wordPaper.setHtmlPaperFile("D://test//docx//doctest.html");
            wordPaper.setWordPaperFile("D://test//model.docx");
            wordPaper.setCurrMaxQuestionId(100);
            wordPaper.setFieldName("领域测试");
            wordPaper.encodeing = System.getProperty("file.encoding");
            if (wordPaper.analysisPaper()) {
                System.out.println(gson.toJson(wordPaper.getPaperInfo()));
                System.out.println(wordPaper.getPaperInfo().getContent());

                System.out.println(wordPaper.getPaperInfo().getAnswerSheet());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
