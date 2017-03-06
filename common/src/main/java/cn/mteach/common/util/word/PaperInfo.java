package cn.mteach.common.util.word;

import java.util.List;
import java.util.Map;

/**
 * Created by Sugior on 2016/12/8.
 */
public class PaperInfo {
    private String title;
    private String name;
    private String totalScore;
    private String timeLength;
    private String paperId;
    private String fieldName;
    private Integer currMaxQuestionId = 0;
    private Map<String, Integer> questionTypeList;
    private List<BigQuestion> bigQuestionList;

    public List<BigQuestion> getBigQuestionList() {
        return bigQuestionList;
    }

    public void setBigQuestionList(List<BigQuestion> bigQuestionList) {
        this.bigQuestionList = bigQuestionList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore;
    }

    public String getTimeLength() {
        return timeLength;
    }

    public void setTimeLength(String timeLength) {
        this.timeLength = timeLength;
    }


    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public Integer getCurrMaxQuestionId() {
        return currMaxQuestionId;
    }

    public void setCurrMaxQuestionId(Integer currMaxQuestionId) {
        this.currMaxQuestionId = currMaxQuestionId;
    }

    public Map<String, Integer> getQuestionTypeList() {
        return questionTypeList;
    }

    public void setQuestionTypeList(Map<String, Integer> questionTypeList) {
        this.questionTypeList = questionTypeList;
    }


    public String getContent() {
        String questionStr = "[";
        if (bigQuestionList != null) {
            Integer currQuestionId =currMaxQuestionId;
            for (int i = 0; i < bigQuestionList.size(); i++) {
                BigQuestion bigQuestion = bigQuestionList.get(i);
                List<SmallQuestion> questionList = bigQuestion.getQuestionList();
                if (questionList != null) {
                    for (int j = 0; j < questionList.size(); j++) {
                        SmallQuestion smallQuestion = questionList.get(j);
                        String questionTypeName = smallQuestion.getQuestionType();
                        Integer questionTypeId = getQuestionTypeId(questionTypeName);
                        String contentStr= smallQuestion.getContent().replaceAll("[\\\"]", "\\\\\\\"");
                        contentStr= contentStr.replaceAll("\\\\\\\\\\\"","\\\\\\\\\\\\\"");
                        questionStr = questionStr + "{\"questionId\":" + (currQuestionId++).toString();
                        questionStr = questionStr + ",\"content\":\"" +contentStr + "\"";
                        questionStr = questionStr + ",\"answer\":\"" + smallQuestion.getAnswers() + "\"";
                        questionStr = questionStr + ",\"analysis\":\"" + smallQuestion.getAnalysis() + "\"";
                        questionStr = questionStr + ",\"questionTypeId\":\"" + questionTypeId + "\"";
                        questionStr = questionStr + ",\"referenceName\":\""+smallQuestion.getReferenceName()+"\"";
                        questionStr = questionStr + ",\"pointName\":\"" + smallQuestion.getKnowledge() + "\"";
                        questionStr = questionStr + ",\"fieldName\":\"" + fieldName + "\"";
                        questionStr = questionStr + ",\"questionPoint\":" + bigQuestion.getSingleScore();
                        questionStr = questionStr + ",\"examingPoint\":\"\"";
                        questionStr = questionStr + ",\"knowledgePointId\":0 },";
                    }
                }
            }
        }
        if (!"".equals(questionStr)) {
            questionStr = questionStr.trim();
            questionStr = questionStr.substring(0, questionStr.length() -1);
        }
        questionStr = questionStr + "]";
        return questionStr;
    }

    public String getAnswerSheet() {
        String answerSheetStr = " {\"examHistroyId\":0, \"examId\":0, \"examPaperId\":"+paperId+", \"duration\":0, ";
        answerSheetStr = answerSheetStr + "\"answerSheetItems\":[ ";
        if (bigQuestionList != null) {
            Integer currQuestionId =currMaxQuestionId;
            for (int i = 0; i < bigQuestionList.size(); i++) {
                BigQuestion bigQuestion = bigQuestionList.get(i);
                List<SmallQuestion> questionList = bigQuestion.getQuestionList();
                if (questionList != null) {
                    for (int j = 0; j < questionList.size(); j++) {
                        SmallQuestion smallQuestion = questionList.get(j);
                        String questionTypeName = smallQuestion.getQuestionType();
                        Integer questionTypeId = getQuestionTypeId(questionTypeName);
                        answerSheetStr = answerSheetStr + "{\"point\":" + bigQuestion.getSingleScore();
                        answerSheetStr = answerSheetStr + ",\"questionTypeId\":" + questionTypeId;
                        answerSheetStr = answerSheetStr + ",\"answer\":\"" + smallQuestion.getAnswers() + "\"";
                        answerSheetStr = answerSheetStr + ",\"questionId\":" + Integer.toString(currMaxQuestionId++);
                        answerSheetStr = answerSheetStr + ",\"right\":false },";
                    }
                }
            }
        }
        if (!"".equals(answerSheetStr)) {
            answerSheetStr = answerSheetStr.trim();
            answerSheetStr = answerSheetStr.substring(0, answerSheetStr.length() - 1);
        }
        answerSheetStr = answerSheetStr + "],\"pointMax\":" + getTotalScore();
        answerSheetStr = answerSheetStr + ", \"pointRaw\":0 }";
        return answerSheetStr;
    }

    public Integer getQuestionTypeId(String questionTypeName) {
        Integer questionTypeId = 5;
        if (questionTypeList != null) {
            if (questionTypeList.containsKey(questionTypeName)) {
                questionTypeId = questionTypeList.get(questionTypeName);
            }
        }
        return questionTypeId;
    }
}
