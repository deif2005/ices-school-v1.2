package cn.mteach.common;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * UploadFile
 * 文件上传
 * @author yuhao
 * @date 2016/8/19
 */
public class UploadFileResponse implements Serializable{

    private static final long serialVersionUID = -1968837645835754263L;

    //上传至服务器后的文件列表
    private List<Map<String,String>> fileList;

    //原文件名
    private List<String> fileNameList;

    /**
     * 获取文件列表中第一个文件路径
     * @return
     */
    public String getFirstNewFilePath(){
        if(fileList.size() > 0){
            Iterator iterator = fileList.get(0).entrySet().iterator();
            Map.Entry entry = (Map.Entry)iterator.next();
            return entry.getValue().toString();
            //fileList.get(0).get(fileList.get(0).entrySet().iterator().next());
        }else{
            return null;
        }
    }

    /**
     * 获取文件列表中第一个文件名
     * @return
     */
    public String getFirstNewFileName(){
        if(fileList.size() > 0){
            Iterator iterator = fileList.get(0).entrySet().iterator();
            Map.Entry entry = (Map.Entry)iterator.next();
            return entry.getKey().toString(); //fileList.get(0).entrySet().iterator().next().toString();
        }else{
            return null;
        }
    }
    /**
     * 获取文件列表中第一个文件名称
     * @return
     */
    public String getFirstOriginalFileName(){
        if(fileNameList.size() > 0){
            return fileNameList.get(0);
        }else{
            return null;
        }
    }

    public List<Map<String,String>> getFileList() {
        return fileList;
    }

    public void setFileList(List<Map<String,String>> fileList) {
        this.fileList = fileList;
    }

    public List<String> getFileNameList() {
        return fileNameList;
    }

    public void setFileNameList(List<String> fileNameList) {
        this.fileNameList = fileNameList;
    }
}
