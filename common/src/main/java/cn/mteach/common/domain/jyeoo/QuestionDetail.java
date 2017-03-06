package cn.mteach.common.domain.jyeoo;

import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * Created by defi on 2017-02-20.
 */
public class QuestionDetail {
    private Integer Count;
    private Integer PageIndex;
    private Integer PageSize;
    private String Message;
    private List<QuestionData> Data;
    private Object EXData;
    private Object Keys;
    private Integer TotalPage;

    public class QuestionData {
        private List<ChapterDetail.Points> Points;
        private Object Topics;
        private String Analyse;
        private String Method;
        private String Discuss;
        private String Teacher;
        private String ID;
        private Integer Subject;
        private Integer Cate;
        private String CateName;
        private String Label;
        private Object LabelReportID;
        private String Content;
        private Object Options;
        private Object Answers;
        private Object UserAnswers;
        private Object QuesFiles;
        private Integer VIP;
        private Integer Point;
        private Float Degree;
        private Integer RC;
        private Integer FavTime;
        private Integer FavCount;
        private Integer ViewCount;
        private Integer DownCount;
        private Integer PaperCount;
        private Integer PaperTime;

        public List<ChapterDetail.Points> getPoints() {
            return Points;
        }

        public void setPoints(List<ChapterDetail.Points> points) {
            Points = points;
        }

        public Object getTopics() {
            return Topics;
        }

        public void setTopics(List<T> topics) {
            Topics = topics;
        }

        public String getAnalyse() {
            return Analyse;
        }

        public void setAnalyse(String analyse) {
            Analyse = analyse;
        }

        public String getMethod() {
            return Method;
        }

        public void setMethod(String method) {
            Method = method;
        }

        public String getDiscuss() {
            return Discuss;
        }

        public void setDiscuss(String discuss) {
            Discuss = discuss;
        }

        public String getTeacher() {
            return Teacher;
        }

        public void setTeacher(String teacher) {
            Teacher = teacher;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public Integer getSubject() {
            return Subject;
        }

        public void setSubject(Integer subject) {
            Subject = subject;
        }

        public Integer getCate() {
            return Cate;
        }

        public void setCate(Integer cate) {
            Cate = cate;
        }

        public String getCateName() {
            return CateName;
        }

        public void setCateName(String cateName) {
            CateName = cateName;
        }

        public String getLabel() {
            return Label;
        }

        public void setLabel(String label) {
            Label = label;
        }

        public Object getLabelReportID() {
            return LabelReportID;
        }

        public void setLabelReportID(Object labelReportID) {
            LabelReportID = labelReportID;
        }

        public String getContent() {
            return Content;
        }

        public void setContent(String content) {
            Content = content;
        }

        public Object getOptions() {
            return Options;
        }

        public void setOptions(List<T> options) {
            Options = options;
        }

        public Object getAnswers() {
            return Answers;
        }

        public void setAnswers(List<T> answers) {
            Answers = answers;
        }

        public Object getUserAnswers() {
            return UserAnswers;
        }

        public void setUserAnswers(List<T> userAnswers) {
            UserAnswers = userAnswers;
        }

        public Object getQuesFiles() {
            return QuesFiles;
        }

        public void setQuesFiles(List<T> quesFiles) {
            QuesFiles = quesFiles;
        }

        public Integer getVIP() {
            return VIP;
        }

        public void setVIP(Integer VIP) {
            this.VIP = VIP;
        }

        public Integer getPoint() {
            return Point;
        }

        public void setPoint(Integer point) {
            Point = point;
        }

        public Float getDegree() {
            return Degree;
        }

        public void setDegree(Float degree) {
            Degree = degree;
        }

        public Integer getRC() {
            return RC;
        }

        public void setRC(Integer RC) {
            this.RC = RC;
        }

        public Integer getFavTime() {
            return FavTime;
        }

        public void setFavTime(Integer favTime) {
            FavTime = favTime;
        }

        public Integer getFavCount() {
            return FavCount;
        }

        public void setFavCount(Integer favCount) {
            FavCount = favCount;
        }

        public Integer getViewCount() {
            return ViewCount;
        }

        public void setViewCount(Integer viewCount) {
            ViewCount = viewCount;
        }

        public Integer getDownCount() {
            return DownCount;
        }

        public void setDownCount(Integer downCount) {
            DownCount = downCount;
        }

        public Integer getPaperCount() {
            return PaperCount;
        }

        public void setPaperCount(Integer paperCount) {
            PaperCount = paperCount;
        }

        public Integer getPaperTime() {
            return PaperTime;
        }

        public void setPaperTime(Integer paperTime) {
            PaperTime = paperTime;
        }
    }

    public Integer getCount() {
        return Count;
    }

    public void setCount(Integer count) {
        Count = count;
    }

    public Integer getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        PageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return PageSize;
    }

    public void setPageSize(Integer pageSize) {
        PageSize = pageSize;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public List<QuestionData> getData() {
        return Data;
    }

    public void setData(List<QuestionData> date) {
        Data = date;
    }

    public Object getEXData() {
        return EXData;
    }

    public void setEXData(Object EXData) {
        this.EXData = EXData;
    }

    public Object getKeys() {
        return Keys;
    }

    public void setKeys(Object keys) {
        Keys = keys;
    }

    public Integer getTotalPage() {
        return TotalPage;
    }

    public void setTotalPage(Integer totalPage) {
        TotalPage = totalPage;
    }
}


