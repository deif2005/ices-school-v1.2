package cn.mteach.common;

import cn.mteach.common.util.DateUtil;
import cn.mteach.common.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * SystemConf
 * 读取系统配置类
 * @author yuhao
 * @date 2016/10/19 20:26
 */
public class SystemConfig {
    /**
     * 获取静态资源展示地址
     * @return 返回资源展示地址
     * @return 例：http://192.168.10.42:8085/ices-static/upload
     */
    public static String getStaticShowPath(){
        return PropertiesConfig.getConfigString("/static/showPath");
    }

    /**
     * 获取静态资源上传地址
     * @return 例：d:/tomcat/ices-static
     */
    public static String getStaticUploadBasePath(){
        return PropertiesConfig.getConfigString("/static/uploadPath");
    }

    /**
     * 获取试题资源上传地址
     * @param fileType 文件类型
     * @return 返回 根路径+试题资源路径
     */
    public static String getQuestionUploadPath(String fileType){
        return PropertiesConfig.getConfigString("/upload/question/resource/path").replaceAll("#date", DateUtil.getDateToString(new Date())).replaceAll("#fileType", fileType);
    }

    /**
     * 获取试题资源包上传规则
     * @return
     */
    public static String getQuestionResourceUploadRule(){
        return PropertiesConfig.getConfigString("/upload/question/resource/package/rule");
    }


    /**
     * 获取试卷导出临时地址/下载地址
     * 跟据日期及uuid分文件夹，日期yyyy-MM-dd，以天为单位，标记每天导出的试卷，uuid区分每次导出的临时文件夹
     * @param rootPath 可获取当前服务目录或通过配置文件配置目录
     * @return
     */
    public static String getExportPaperTempPath(String rootPath){
        String filePath = rootPath + PropertiesConfig.getConfigString("/download/paper/path").replaceAll("#date", DateUtil.getDateToString(new Date()));
        return filePath.replace("\\\\","\\");
    }

    /**
     * 获取试卷导出word模板文件
     * @param rootPath 可获取当前服务目录或通过配置文件配置目录
     */
    public static String getPaperWordTemplatePath(String rootPath){
        String filePath = rootPath + PropertiesConfig.getConfigString("/paper/template/path").replace("\\\\","\\");
        return filePath.replace("\\\\","\\");
    }

    /**
     * word试卷上传相对路径
     */
    public static String getWordPaperImportPath(String fieldId){
        String filePath = PropertiesConfig.getConfigString("/upload/wordPaper/path").replace("#fieldId", fieldId);
        return filePath;
    }

    /**
     * word转换成html格式后的路径
     *
     */
    public static String getHtmlPaperPath(String fieldId){
        String filePath = PropertiesConfig.getConfigString("/upload/htmlPaper/path").replace("#fieldId", fieldId);
        return filePath;
    }

    /**
     * 获取http静态资源地址
     */
    public static String getStaticResourcePath(HttpServletRequest request){
        String serverPath = request.getSession().getServletContext().getRealPath("/").toString();
        String path = StringUtil.isBlank(SystemConfig.getStaticUploadBasePath()) ?
                serverPath.substring(0, serverPath.lastIndexOf("\\")) : SystemConfig.getStaticUploadBasePath();
        return path;
    }

    /**
     * 获取http静态资源服务地址
     */
    public static String getHttpStaticResourcePath(HttpServletRequest request){
        String path = StringUtil.isBlank(SystemConfig.getStaticShowPath()) ?
                "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() : SystemConfig.getStaticShowPath();
        return path;
    }

    /**
     * 考生答案资源上传路径
     */
    public static String getUserQuestionResourceUploadPath(String examTimeId, String userId){
        String path = PropertiesConfig.getConfigString("/upload/user/question/resource/path").replace("#examTimeId", examTimeId).replace("#userId", userId);
        return path;
    }

}
