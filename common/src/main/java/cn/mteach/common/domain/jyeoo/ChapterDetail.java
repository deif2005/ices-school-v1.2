package cn.mteach.common.domain.jyeoo;

import java.util.List;

/**
 * Created by defi on 2017-02-16.
 */

public class ChapterDetail {
    private String ID;
    private Integer GradeID;
    private Integer TermID;
    private Integer EditionID;
    private String Name;
    private String Desc;
    private String Cover;
    private Integer Type;
    private Integer Status;
    private Integer Subject;
    private String Term;
    private String Grade;
    private String Edition;
    private String TypeName;
    private List<Children> Children;

    public class Points{
        private String Key;
        private String Value;

        public String getKey() {
            return Key;
        }

        public void setKey(String key) {
            Key = key;
        }

        public String getValue() {
            return Value;
        }

        public void setValue(String value) {
            Value = value;
        }
    }

    public class Children{
        private String ID;
        private String PID;
        private String BookID;
        private String Name;
        private Integer Seq;
        private String Desc;
        private String DotName;
        private String PointNo;
        private String PK;
        private Integer Subject;
        private List<Children> Children;
        private List<Points> Points;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getPID() {
            return PID;
        }

        public void setPID(String PID) {
            this.PID = PID;
        }

        public String getBookID() {
            return BookID;
        }

        public void setBookID(String bookID) {
            BookID = bookID;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public int getSeq() {
            return Seq;
        }

        public void setSeq(int seq) {
            Seq = seq;
        }

        public String getDesc() {
            return Desc;
        }

        public void setDesc(String desc) {
            Desc = desc;
        }

        public String getDotName() {
            return DotName;
        }

        public void setDotName(String dotName) {
            DotName = dotName;
        }

        public String getPointNo() {
            return PointNo;
        }

        public void setPointNo(String pointNo) {
            PointNo = pointNo;
        }

        public String getPK() {
            return PK;
        }

        public void setPK(String PK) {
            this.PK = PK;
        }

        public int getSubject() {
            return Subject;
        }

        public void setSubject(int subject) {
            Subject = subject;
        }

        public List<Children> getChildren() {
            return Children;
        }

        public void setChildren(List<Children> children) {
            Children = children;
        }

        public List<ChapterDetail.Points> getPoints() {
            return Points;
        }

        public void setPoints(List<ChapterDetail.Points> points) {
            Points = points;
        }
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Integer getGradeID() {
        return GradeID;
    }

    public void setGradeID(Integer gradeID) {
        GradeID = gradeID;
    }

    public Integer getTermID() {
        return TermID;
    }

    public void setTermID(Integer termID) {
        TermID = termID;
    }

    public int getEditionID() {
        return EditionID;
    }

    public void setEditionID(int editionID) {
        EditionID = editionID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getCover() {
        return Cover;
    }

    public void setCover(String cover) {
        Cover = cover;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public int getSubject() {
        return Subject;
    }

    public void setSubject(int subject) {
        Subject = subject;
    }

    public String getTerm() {
        return Term;
    }

    public void setTerm(String term) {
        Term = term;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }

    public String getEdition() {
        return Edition;
    }

    public void setEdition(String edition) {
        Edition = edition;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }

    public List<Children> getChildren() {
        return Children;
    }

    public void setChildren(List<Children> children) {
        Children = children;
    }



}
