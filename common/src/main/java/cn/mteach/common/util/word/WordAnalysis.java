package cn.mteach.common.util.word;

import java.util.*;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.Exception;

/**
 * Created by Sugior on 2016/12/2.
 */
public class WordAnalysis {
    private static final String splitHtmlStr = "================================";
    private static final String bigQuestionRegex = "(([一二三四五六七八九十]+)[、．.](.*?))[(|（]共(\\d+)题[,|，]每小题(\\d+\\.\\d+|\\d+)分[,|，]总共(\\d+\\.\\d+|\\d+)分[）|)](\\s*[(|（].*?[）|)]){0,1}";
    private static final String bigQuestionHeadRegex = "^" + bigQuestionRegex;
    private static final String smallQuestionRegex = "[<br/>]+\\s*([1-9]\\d*[、．.])";
    private static final String smallQuestionHeadRegex = "^(\\d*)[、．.]+([（|\\(].*[\\)|）]){0,1}(.*)";//"^" + smallQuestionRegex;
    private static final String choiceRegex = "[<br/>]+\\s*([a-zA-Z]+[、．.])";
    private static final String choiceHeadRegex = "^[<br/>]+\\s*(([a-zA-Z])+[、．.](.*))";
    private static final String choiceItemRegex = "(([a-zA-Z])+[、．.](.*))";
    private static final String splitChoiceItemRegex = "([a-zA-Z]+[、．.])";
    private static final String splitKnowledgeAnswerAnalysisRegex = "(\\[答案\\])|(\\[解析\\])|(\\[知识点\\])";
    private static final String knowledgeAndAnswerAndAnalysisRegex ="((\\[答案\\])|(\\[解析\\])|(\\[知识点\\])])(.*)";
    private static final String knowledgeRegex = "(\\[知识点\\])(.*)";
    private static final String answerRegex = "(\\[答案\\])(.*)";
    private static final String analysisRegex = "(\\[解析\\])(.*)";

    private static final String paperNameRegex = "(.+)[(|（](总分[：|:]\\s*(\\d+)\\s*分)[,|，]{0,1}(\\s*时间[：|:]\\s*(\\d+)\\s*分钟){0,1}[）|)]";
    private static final String paperNameHeadRegex = "^" + paperNameRegex;
    private static final String imageRegex ="<img([\\s+][^>]*?[\\s+]*?){0,1}([\\s+][s|S][r|R][c|C]=\"([^\"]*)\")([\\s+][^>]*){0,1}>";
    private static final String tableRegex = "<table [^>]*[^>]*>(<table[^>]*>(<table[^>]*>(<table[^>]*>.*?</table>|.)*?</table>|.)</?[/?(p)][^><]*></table>|.)*?</table>";
    // 删除不需要的标签
    private static final String clearFlagRegex = "<[/]?(font|FONT|span|SPAN|xml|XML|del|DEL|ins|INS|meta|META|[ovwxpOVWXP]:\\w+)[^>]*?>";
    // 删除不需要的属性
    private static final String clearPerporperRegex = "<([^>]*)(?:lang|LANG|class|CLASS|style|STYLE|size|SIZE|face|FACE|[ovwxpOVWXP]:\\w+)=(?:'[^']*'|\"\"[^\"\"]*\"\"|[^>]+)([^>]*)>";
    //(\[\s*\w\s*\]).([^\[]+)


    /**
     * 删除html文本内中的无效的html标签
     *
     * @param htmlStr
     * @return
     */
    public static String deleteHtml(String htmlStr) {
        //html文档的head部分信息
        htmlStr = htmlStr.replaceAll("<(head|HEAD)[^>]*?>[\\s\\S]*?</(head|HEAD)>", "");
        //过滤掉所有script脚本
        htmlStr = htmlStr.replaceAll("<(script|SCRIPT)[^>]*?>[\\s\\S]*?</(script|SCRIPT)>", "");
        //去掉所有标签，但保留br,img,table标签
        htmlStr = htmlStr.replaceAll("</?[^/?(br|BR|p|P|img|IMG|table|TABLE)][^><]*>", "");
        //清除html控件的class
        htmlStr = htmlStr.replaceAll("([\\s]*)(class|CLASS)=\"([^\"]*)\"", "");
        //空白字符、直掉换行符
        htmlStr = htmlStr.replaceAll("\\r|\\t|\\n|\\a", "");
        //body标签
        htmlStr = htmlStr.replaceAll("</?(body|BODY)[^><]*>", "").replaceAll("(&nbsp;|&NBSP;)*", "");
        htmlStr = resetImageStyle(htmlStr);
        return htmlStr;
    }

    public static String resetImageStyle(String htmlStr) {
        Pattern pattern = Pattern.compile(imageRegex);
        Matcher matcher = pattern.matcher(htmlStr);
        while (matcher.find()) {
            String oldImageStr = matcher.group();
            String newImageStr = "<img src=\"#icesServerHostUrl#/" + matcher.group(3)+"\"";
            newImageStr = newImageStr+"style=\"max-width:450px;_width:expression(width > 450 ? \"450px\" : width); \" >";
            htmlStr = htmlStr.replace(oldImageStr,newImageStr);
        }
        return htmlStr;
    }

    /**
     * 添加字符串分割符，将html文本中的p标签替换成分割字符串
     *
     * @param htmlStr  需要处理的html文本
     * @param splitStr 用于分割的字符串
     * @return
     */
    private static String addSplitStrToHtml(String htmlStr, String splitStr) {
        Map<String, String> tableMap = new HashMap<>();
        List<String> tableList = new ArrayList<>();
        Pattern pattern = Pattern.compile(tableRegex);
        Matcher matcher = pattern.matcher(htmlStr);
        while (matcher.find()) {
            String tableKey = "{<ICES>TABLE:[" + UUID.randomUUID().toString() + "]</ICES>}";
            String tableValue = matcher.group();
            //去掉表内容中的splitStr字符串
            tableMap.put(tableKey, tableValue.replaceAll(splitStr, ""));
            tableList.add(tableKey);
            //将原html中的表格内容用tableKey替换掉
            htmlStr = htmlStr.replace(tableValue, tableKey);
        }
        //将删除表格内容的html中的p标签替换成分割字符串splitStr;
        htmlStr = htmlStr.replaceAll("<p[^>]*?>", "").replaceAll("</p[^>]*?>", splitStr);
        //重新将抽出来的表格内容替换回原html中
        for (int i = 0; i < tableList.size(); i++) {
            String tableKey = tableList.get(i);
            String tableValue = tableMap.get(tableKey);
            htmlStr = htmlStr.replace(tableKey, tableValue);
        }
        //删除连续多个splitStr分割字符串
        htmlStr = htmlStr.replaceAll("(" + splitStr + "){2,}", splitStr);
        return htmlStr;
    }

    /**
     * 获取考试试卷的基本信息
     *
     * @param htmlStr
     * @return
     */
    public static PaperInfo getPaperInfo(String htmlStr) {
        PaperInfo paper = new PaperInfo();
        Pattern pattern = Pattern.compile(paperNameHeadRegex);
        Matcher matcher = pattern.matcher(htmlStr);
        if (matcher.find()) {
            List<BigQuestion> bigQuestions = getBigQuestionList(htmlStr, splitHtmlStr);
            if (bigQuestions != null && bigQuestions.size() > 0) {
                paper.setTitle(matcher.group());
                paper.setName(matcher.group(1));
                paper.setTotalScore(matcher.group(3));
                paper.setTimeLength(matcher.group(5));
                paper.setBigQuestionList(bigQuestions);
            } else {
                throw new RuntimeException("获取试卷题目信息失败!");
            }
        } else {
            throw new RuntimeException("获取试卷头信息失败!");
        }
        return paper;
    }

    /**
     * 获取试卷的题目列表信息
     *
     * @param htmlStr
     * @return
     */
    public static List<BigQuestion> getBigQuestionList(String htmlStr) throws Exception {
        return getBigQuestionList(htmlStr, splitHtmlStr);
    }

    /**
     * 在大题标识信息前插入分割符
     *
     * @param regex
     * @param htmlStr
     * @param splitStr
     * @return
     */
    private static String insertSplitStr(String regex, String htmlStr, String splitStr) {
        String tempHtmlStr = htmlStr;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(htmlStr);
        while (matcher.find()) {
            String groupStr = matcher.group();
            tempHtmlStr = tempHtmlStr.replace(groupStr, splitStr + groupStr);
        }
        return tempHtmlStr;
    }

    /**
     * 获取大题列表信息
     *
     * @param htmlStr
     * @param splitStr
     * @return
     */

    private static List<BigQuestion> getBigQuestionList(String htmlStr, String splitStr) {
        List<BigQuestion> bigQuestionList = new LinkedList<>();
        String tempHtmlStr = insertSplitStr(bigQuestionRegex, htmlStr, splitStr);
        String htmlSplitList[] = tempHtmlStr.split(splitStr+"(<[b|B][r|R]/>)*");
        Pattern pattern = Pattern.compile(bigQuestionHeadRegex);
        for (int i = 0; i < htmlSplitList.length; i++) {
            String bigQuestionStr = htmlSplitList[i].toString().trim();
            if (!"".equals(bigQuestionStr)) {
                Matcher matcher = pattern.matcher(bigQuestionStr);
                if (matcher.find()) {
                    String questionType = matcher.group(3);
                    List<SmallQuestion> questionList = getSmallQuestionList(bigQuestionStr, splitStr, questionType);
                    if (questionList != null && questionList.size() > 0) {
                        BigQuestion bigQuestion = new BigQuestion();
                        bigQuestion.setTitle(matcher.group(0));
                        bigQuestion.setName(matcher.group(1));
                        bigQuestion.setNo(matcher.group(2));
                        bigQuestion.setType(matcher.group(3));
                        bigQuestion.setCount(matcher.group(4));
                        bigQuestion.setSingleScore(matcher.group(5));
                        bigQuestion.setTotalScore(matcher.group(6));
                        bigQuestion.setDescription(matcher.group(7));
                        bigQuestion.setQuestionList(questionList);
                        bigQuestionList.add(bigQuestion);
                    } else {
                        throw new RuntimeException("获取大题[" + matcher.group(2) + "]的题目信息时失败!");
                    }
                }
            }
        }
        return bigQuestionList;
    }

    /**
     * 获取大题中的小题列表
     *
     * @param bigQuestionStr
     * @param splitStr
     * @return
     */
    private static List<SmallQuestion> getSmallQuestionList(String bigQuestionStr, String splitStr, String questionType) {
        List<SmallQuestion> smallQuestionList = new LinkedList<>();
        bigQuestionStr = bigQuestionStr.replaceAll(bigQuestionHeadRegex, "");
        String tempBigQuestionStr = insertSplitStr(smallQuestionRegex, bigQuestionStr, splitStr);
        String htmlSplitList[] = tempBigQuestionStr.split(splitStr+"(<[b|B][r|R]/>)*");
        Pattern pattern = Pattern.compile(smallQuestionHeadRegex);
        for (int i = 0; i < htmlSplitList.length; i++) {
            String smallQuestionStr = htmlSplitList[i].toString().trim();
            if (!"".equals(smallQuestionStr)) {
                Matcher matcher = pattern.matcher(smallQuestionStr);
                if (matcher.find()) {
                    SmallQuestion smallQuestion = getKnowledgeAndAnswerAndAnalysis(smallQuestionStr, splitStr);
                    smallQuestionStr = smallQuestionStr.replaceAll(knowledgeAndAnswerAndAnalysisRegex, "");
                    List<ChoiceItem> choiceList = getChoiceList(smallQuestionStr, splitStr);
                    String questionTitle = smallQuestionStr.replaceAll(choiceItemRegex, "");
                    smallQuestion.setQuestionType(questionType.trim().replaceAll("(<[b|B][r|R]/>)+", "").replaceAll("\"","\\\""));
                    smallQuestion.setTitle(questionTitle.replaceAll("[\\\"]", "\\\\\\\""));
                    smallQuestion.setQuestionNO(matcher.group(1).trim().replaceAll("(<[b|B][r|R]/>)+", "").replaceAll("\"","\\\""));
                    smallQuestion.setReferenceName(matcher.group(2));
                    smallQuestion.setQuestionName(matcher.group(3).trim().replaceAll("(<[b|B][r|R]/>)+", "").substring(0, 10).replaceAll("\"","\\\""));
                    smallQuestion.setChoiceImgList("");
                    smallQuestion.setTitleImg("");
                    smallQuestion.setChoiceList(choiceList);
                    smallQuestionList.add(smallQuestion);
                }
            }
        }
        return smallQuestionList;
    }


    public static String getFirstMatcherStr(String regex, String matherStr) {
        String resultStr = "";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(matherStr);
        if (matcher.find()) {
            resultStr = matcher.group();
        }
        return resultStr;
    }

    /**
     * 获取小题的选择项(题型为选择题或判断题)
     *
     * @param smallQuestionStr
     * @return
     */
    private static List<ChoiceItem> getChoiceList(String smallQuestionStr, String splitStr) {
        List<ChoiceItem> choiceList = new LinkedList<>();
        String tempSmallQuestionStr = insertSplitStr(choiceRegex, smallQuestionStr, splitStr);
        String htmlSplitList[] = tempSmallQuestionStr.split(splitStr+"(<[b|B][r|R]/>)*");
        for (int i = 0; i < htmlSplitList.length; i++) {
            String choiceStr = htmlSplitList[i].toString().trim();
            if (!"".equals(choiceStr)) {
                choiceStr = insertSplitStr(splitChoiceItemRegex, choiceStr, splitStr);
                String splitChoiceList[] = choiceStr.split(splitStr);
                for (int j = 0; j < splitChoiceList.length; j++) {
                    String choiceItemStr = splitChoiceList[j].toString().trim();
                    Pattern pattern = Pattern.compile(choiceItemRegex);
                    Matcher matcher = pattern.matcher(choiceItemStr);
                    while (matcher.find()) {
                        ChoiceItem choiceMap = new ChoiceItem();
                        choiceMap.setItem(matcher.group(1).trim().replaceAll("(<[b|B][r|R]/>)+", "").replaceAll("[\\\"]", "\\\\\\\""));
                        choiceMap.setItemNO(matcher.group(2).trim().replaceAll("(<[b|B][r|R]/>)+", "").replaceAll("[\\\"]", "\\\\\\\""));
                        choiceMap.setItemStr(matcher.group(3).trim().replaceAll("(<[b|B][r|R]/>)+","").replaceAll("[\\\"]", "\\\\\\\""));
                        choiceList.add(choiceMap);
                    }
                }
            }
        }
        return choiceList;
    }

    public static SmallQuestion getKnowledgeAndAnswerAndAnalysis(String smallQuestionStr, String splitStr) {
        SmallQuestion smallQuestion = new SmallQuestion();
        String questionBottomAreaStr = getFirstMatcherStr(knowledgeAndAnswerAndAnalysisRegex, smallQuestionStr);
        String tempSmallQuestionStr = insertSplitStr(splitKnowledgeAnswerAnalysisRegex, questionBottomAreaStr, splitStr);
        String htmlSplitList[] = tempSmallQuestionStr.split(splitStr+"(<[b|B][r|R]/>)*");
        String answersStr = getKnowledgeAndAnswerAndAnalysisStr(answerRegex, htmlSplitList);
        String analysisStr = getKnowledgeAndAnswerAndAnalysisStr(analysisRegex, htmlSplitList);
        String knowledgeStr = getKnowledgeAndAnswerAndAnalysisStr(knowledgeRegex, htmlSplitList);
        smallQuestion.setAnswers(answersStr.trim().replaceAll("(<[b|B][r|R]/>)+", "").replaceAll("\"","\\\""));
        smallQuestion.setAnalysis(analysisStr.trim().replaceAll("(<[b|B][r|R]/>)+", "").replaceAll("\"","\\\""));
        smallQuestion.setKnowledge(knowledgeStr.trim().replaceAll("(<[b|B][r|R]/>)+","").replaceAll("\"","\\\""));
        return smallQuestion;
    }

    /**
     * 获取标准答案串,知识点,试题解析
     *
     * @param regex
     * @param htmlSplitList
     * @return
     */
    private static String getKnowledgeAndAnswerAndAnalysisStr(String regex, String htmlSplitList[]) {
        String resultStr = "";
        Pattern pattern = Pattern.compile(regex);
        for (int i = 0; i < htmlSplitList.length; i++) {
            String itemStr = htmlSplitList[i].toString().trim();
            if (!"".equals(itemStr)) {
                Matcher matcher = pattern.matcher(itemStr);
                if (matcher.find()) {
                    resultStr = matcher.group(2).trim().replaceAll("(<[b|B][r|R]/>)+","").replaceAll("\"","\\\"");
                    break;
                }
            }
        }
        return resultStr;
    }

    /**
     * 获取知识点
     *
     * @param smallQuestionStr
     * @return
     */
    private static String getKnowledgeStr(String smallQuestionStr) {
        String resultStr = "";
        Pattern pattern = Pattern.compile(knowledgeRegex);
        Matcher matcher = pattern.matcher(smallQuestionStr);
        if (matcher.find()) {
            resultStr = matcher.group(2);
        }
        return resultStr;
    }

    /**
     * 获取试题解析
     *
     * @param smallQuestionStr
     * @return
     */
    private static String getAnalysisStr(String smallQuestionStr) {
        String resultStr = "";
        Pattern pattern = Pattern.compile(analysisRegex);
        Matcher matcher = pattern.matcher(smallQuestionStr);
        if (matcher.find()) {
            resultStr = matcher.group(2);
        }
        return resultStr;
    }

    /**
     * 获取小题的填空项(题型为填空题)
     *
     * @param smallQuestionStr
     * @return
     */
    private static List<Map<String, String>> getFillList(String smallQuestionStr) {
        List<Map<String, String>> fillList = new LinkedList<Map<String, String>>();
        String rgx = "";
        Pattern pattern = Pattern.compile(rgx);
        Matcher matcher = pattern.matcher(smallQuestionStr);
        while (matcher.find()) {
            Map<String, String> bigQuestionMap = new HashMap<>();
            bigQuestionMap.put("title", matcher.group().trim().replaceAll("\"","\\\""));
            bigQuestionMap.put("choiceNO", matcher.group(1));
            bigQuestionMap.put("choiceStr", matcher.group(2).trim().replaceAll("\"","\\\""));
            fillList.add(bigQuestionMap);
        }
        return fillList;
    }



    /*public String formatSpecialCharacter(String htmlStr){
        htmlStr = htmlStr.replaceAll("\"","\\\"").replaceAll("\\{","\\\\{").replaceAll("\\}","\\\\}");
        htmlStr = htmlStr.replaceAll("\\(","\\\\(").replaceAll("\\)","\\\\)");
        htmlStr = htmlStr.replaceAll("\\[","\\\\[").replaceAll("\\]","\\\\]");
        return htmlStr;
    }*/
    /**
     * 获取小题的问题项(用于主观题)
     *
     * @param smallQuestionStr
     * @return
     */
    private static List<Map<String, String>> getAnswerList(String smallQuestionStr) {
        List<Map<String, String>> answerList = new LinkedList<>();
        String rgx = "";
        Pattern pattern = Pattern.compile(rgx);
        Matcher matcher = pattern.matcher(smallQuestionStr);
        while (matcher.find()) {
            Map<String, String> bigQuestionMap = new HashMap<>();
            bigQuestionMap.put("title", matcher.group());
            bigQuestionMap.put("choiceNO", matcher.group(1));
            bigQuestionMap.put("content", "{}");
            answerList.add(bigQuestionMap);
        }
        return answerList;
    }

    /**
     * 还原显示试卷题结构
     *
     * @param bigQuestionList
     */
    private static void ShowQuestionList(List<Map<String, Object>> bigQuestionList) {
        if (bigQuestionList != null) {
            for (int bigIdx = bigQuestionList.size() - 1; bigIdx >= 0; bigIdx--) {
                Map<String, Object> bigMap = bigQuestionList.get(bigIdx);
                System.out.println(bigMap.get("title"));
                List<Map<String, Object>> smallList = (List) bigMap.get("smallQuestionList");
                if (smallList != null) {
                    for (int samllIdx = smallList.size() - 1; samllIdx >= 0; samllIdx--) {
                        Map<String, Object> smallMap = smallList.get(samllIdx);
                        System.out.println(smallMap.get("title"));
                        List<Map<String, Object>> selectList = (List) smallMap.get("choiceList");
                        if (selectList != null) {
                            for (int selectIdx = selectList.size() - 1; selectIdx >= 0; selectIdx--) {
                                Map<String, Object> selMap = selectList.get(selectIdx);
                                System.out.println(selMap.get("title") + ":" + selMap.get("item"));
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 分析html
     *
     * @param contentHtml
     */
    public static String analysisHtmlString(String contentHtml) {

        //删除html文本中的html标签
        contentHtml = WordAnalysis.deleteHtml(contentHtml);
        //使用<br/>换行分割符替换<p></p>标签
        contentHtml = WordAnalysis.addSplitStrToHtml(contentHtml, "<br/>");
        //获取试卷信息
        //Map<String, Object> paperInfo = getPaperInfo(contentHtml);
        //获取大题信息
        //List<Map<String, Object>> bigQuestionList = getQuestionList(contentHtml);
        // System.out.print(bigQuestionList);
        return contentHtml;
    }
}
