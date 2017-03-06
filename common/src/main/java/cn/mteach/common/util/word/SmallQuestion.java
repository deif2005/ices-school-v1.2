package cn.mteach.common.util.word;

import java.util.List;

/**
 * Created by Sugior on 2016/12/8.
 */
public class SmallQuestion {
    private String title;
    private String questionName;
    private String questionNO;
    private String questionId;
    private String questionType;
    private String titleImg;
    private String answers;
    private String analysis;
    private String knowledge;
    private String referenceName;
    private String choiceImgList;
    private List<ChoiceItem> choiceList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public String getQuestionNO() {
        return questionNO;
    }

    public void setQuestionNO(String questionNO) {
        this.questionNO = questionNO;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getContent() {
        String jsonContent = "{\"title\":\""+title.trim()+"\",";
        jsonContent = jsonContent+"\"titleImg\":\""+titleImg+"\",";
        jsonContent = jsonContent+"\"choiceList\":"+getChoiceListStr()+",";
        jsonContent = jsonContent+"\"choiceImgList\":{"+choiceImgList+"}}";
        return jsonContent;
        //return content;
    }

    public String getTitleImg() {
        return titleImg;
    }

    public void setTitleImg(String titleImg) {
        this.titleImg = titleImg;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public String getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(String knowledge) {
        this.knowledge = knowledge;
    }

    public String getReferenceName() {
        return referenceName;
    }

    public void setReferenceName(String referenceName) {
        this.referenceName = referenceName;
    }

    public String getChoiceImgList() {
        return choiceImgList;
    }

    public void setChoiceImgList(String choiceImgList) {
        this.choiceImgList = choiceImgList;
    }

    public List<ChoiceItem> getChoiceList() {
        return choiceList;
    }

    public void setChoiceList(List<ChoiceItem> choiceList) {
        this.choiceList = choiceList;
    }

    private  String getChoiceListStr() {
        //{"A":"终生","B":"10年内","C":"5年内","D":"20年内"}
        String choiceStr = "";
        if (choiceList != null) {
            for (int i = 0; i < choiceList.size(); i++) {
                ChoiceItem choiceMap = choiceList.get(i);
                choiceStr = choiceStr + "\"" + choiceMap.getItemNO();
                choiceStr = choiceStr + "\":\"" + choiceMap.getItemStr().trim() + "\"";
                choiceStr = choiceStr + (i < choiceList.size()-1?",":"");

            }
        }
        choiceStr = "{" + choiceStr + "}";

        return choiceStr;
    }

}
