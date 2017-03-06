package cn.mteach.common.domain.jyeoo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by wuliangpu on 2017/2/14.
 */
public class UserExam  implements Serializable {

    private static final long serialVersionUID = 3831920272132842365L;

    private String Subject;//训练学科

    private String ID;//组卷标识

    private String UserID;//用户标识

    private String Title;//训练名称

    private String RFID;//关联标识

    private String Type;//训练类别

    private int Correct;//证确试题数量

    private int Mistake;//错误试题数量

    private int Scroe;//得分（0-100）

    private long  Begin;//开始时间

    private long End;//结束时间

    private List<Map<String,List<Ques>>> Groups;//试题分组

    private String CreatorID;//创建者标识

    private long CreateDate;//创建日期

    private String Header;//训练头提醒

    private String Footer;//训练尾提醒

    private String Status;//状态


    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getRFID() {
        return RFID;
    }

    public void setRFID(String RFID) {
        this.RFID = RFID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public int getCorrect() {
        return Correct;
    }

    public void setCorrect(int correct) {
        Correct = correct;
    }

    public int getMistake() {
        return Mistake;
    }

    public void setMistake(int mistake) {
        Mistake = mistake;
    }

    public int getScroe() {
        return Scroe;
    }

    public void setScroe(int scroe) {
        Scroe = scroe;
    }

    public long getBegin() {
        return Begin;
    }

    public void setBegin(long begin) {
        Begin = begin;
    }

    public long getEnd() {
        return End;
    }

    public void setEnd(long end) {
        End = end;
    }

    public List<Map<String, List<Ques>>> getGroups() {
        return Groups;
    }

    public void setGroups(List<Map<String, List<Ques>>> groups) {
        Groups = groups;
    }

    public String getCreatorID() {
        return CreatorID;
    }

    public void setCreatorID(String creatorID) {
        CreatorID = creatorID;
    }

    public long getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(long createDate) {
        CreateDate = createDate;
    }

    public String getHeader() {
        return Header;
    }

    public void setHeader(String header) {
        Header = header;
    }

    public String getFooter() {
        return Footer;
    }

    public void setFooter(String footer) {
        Footer = footer;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}