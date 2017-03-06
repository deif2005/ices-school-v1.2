package cn.mteach.common.jyeoo.util;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wuliangpu on 2017/2/15.
 */
public class HttpUtil {

    /**
     * 检测url是否含有参数，如果有，则把参数加到参数列表中
     *
     * @param url  资源地址
     * @param nvps 参数列表
     * @return 返回去掉参数的url
     */
    public static String checkHasParas(String url, List<NameValuePair> nvps) {
        // 检测url中是否存在参数
        if (url.contains("?") && url.indexOf("?") < url.indexOf("=")) {
            Map<String, String> map = buildParas(url.substring(url
                    .indexOf("?") + 1));
            map2List(nvps, map);
            url = url.substring(0, url.indexOf("?"));
        }
        return url;
    }

    /**
     * 参数转换，将map中的参数，转到参数列表中
     * @param nvps 参数列表
     * @param map  参数列表（map）
     */
    public static void map2List(List<NameValuePair> nvps, Map<String, String> map) {
        if (map == null) return;
        // 拼接参数
        for (Map.Entry<String, String> entry : map.entrySet()) {
            nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
    }


    /**
     * 生成参数，参数格式“k1=v1&k2=v2”
     * @param paras 参数列表
     * @return 返回参数列表（map）
     */
    public static Map<String, String> buildParas(String paras) {
        String[] p = paras.split("&");
        String[][] ps = new String[p.length][2];
        int pos = 0;
        for (int i = 0; i < p.length; i++) {
            pos = p[i].indexOf("=");
            ps[i][0] = p[i].substring(0, pos);
            ps[i][1] = p[i].substring(pos + 1);
            pos = 0;
        }
        return buildParas(ps);
    }

    /**
     * 生成参数，参数类型：{{"k1","v1"},{"k2","v2"}}
     *
     * @param paras 参数列表
     * @return 返回参数列表（map）
     */
    public static Map<String, String> buildParas(String[][] paras) {
        // 创建参数队列
        Map<String, String> map = new HashMap<>();
        for (String[] para : paras) {
            map.put(para[0], para[1]);
        }
        return map;
    }

    /**
     * 通过字节数组生成文件
     *
     * @param bfile
     * @param filePath
     * @param fileName
     */
    public static void getFile(byte[] bfile, String filePath,String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if(!dir.exists()&&dir.isDirectory()){//判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath+"\\"+fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
            bos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

}
