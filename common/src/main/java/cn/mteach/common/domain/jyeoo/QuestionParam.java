package cn.mteach.common.domain.jyeoo;

/**
 * Created by defi on 2017-02-17.
 * 按教材或考点搜索试题时提供的参数
 */

public class QuestionParam {

    private String subject; //学科英文名称
    private Integer tp;// 训练类别（1：教材；2：考点）
    private String p1;// 教材标识或一级考点编号
    private String p2;// 教材目录或二级考点编号
    private String p3;// 考点编号
    private Integer dg;// 难度（0：不限；1：基础题；2：中档题；3：难题）
    private Integer ct;// 题型（0：全部；1：选择题；2：填空题；9：解答题）
    private boolean sc;// 真题集（true；false）
    private boolean gc;// 好题集（true；false）
    private boolean rc;// 常考题（true；false）
    private boolean yc;// 压轴题（true；false）
    private boolean ec;// 易错题（true；false）
    private boolean er;// 错题（true；false）
    private Integer po;// 0：综合排序；1：组卷次数；2：真题次数；3：试题难度
    private Integer pd;// 0：升序；1：降序
    private Integer pi;//当前页（以1开始）
    private Integer ps;//每页记录数

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getTp() {
        return tp;
    }

    public void setTp(Integer tp) {
        this.tp = tp;
    }

    public String getP1() {
        return p1;
    }

    public void setP1(String p1) {
        this.p1 = p1;
    }

    public String getP2() {
        return p2;
    }

    public void setP2(String p2) {
        this.p2 = p2;
    }

    public String getP3() {
        return p3;
    }

    public void setP3(String p3) {
        this.p3 = p3;
    }

    public Integer getDg() {
        return dg;
    }

    public void setDg(Integer dg) {
        this.dg = dg;
    }

    public Integer getCt() {
        return ct;
    }

    public void setCt(Integer ct) {
        this.ct = ct;
    }

    public boolean getSc() {
        return sc;
    }

    public void setSc(boolean sc) {
        this.sc = sc;
    }

    public boolean getGc() {
        return gc;
    }

    public void setGc(boolean gc) {
        this.gc = gc;
    }

    public boolean getRc() {
        return rc;
    }

    public void setRc(boolean rc) {
        this.rc = rc;
    }

    public boolean getYc() {
        return yc;
    }

    public void setYc(boolean yc) {
        this.yc = yc;
    }

    public boolean getEc() {
        return ec;
    }

    public void setEc(boolean ec) {
        this.ec = ec;
    }

    public boolean getEr() {
        return er;
    }

    public void setEr(boolean er) {
        this.er = er;
    }

    public Integer getPo() {
        return po;
    }

    public void setPo(Integer po) {
        this.po = po;
    }

    public Integer getPd() {
        return pd;
    }

    public void setPd(Integer pd) {
        this.pd = pd;
    }

    public Integer getPi() {
        return pi;
    }

    public void setPi(Integer pi) {
        this.pi = pi;
    }

    public Integer getPs() {
        return ps;
    }

    public void setPs(Integer ps) {
        this.ps = ps;
    }
}
