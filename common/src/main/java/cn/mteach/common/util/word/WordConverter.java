package cn.mteach.common.util.word;

import java.io.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;

import org.apache.poi.xwpf.converter.core.BasicURIResolver;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.commons.lang.StringEscapeUtils;

import org.w3c.dom.Document;

/**
 * Created by Sugior on 2016/12/1.
 */
public class WordConverter {
    public static void main(String argv[]) {
        try {
            docConvertToHtmlFile("D://test//model.doc", "D://test//doc//doctest.html", "utf-8");
            docxConvertToHtmlFile("D://test//model.docx", "D://test//docx//docxtest.html", "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 2003版本word(doc)转换成htmlText
     *
     * @throws IOException
     * @throws TransformerException
     * @throws ParserConfigurationException
     */
    public static String docConvertToHtmlText(String docFileName, String imageSvaePath, String encodeing)
            throws TransformerException, ParserConfigurationException, IOException {
        //加载word文档到内存中
        File docxFile = new File(docFileName);
        if (docxFile.exists() && docxFile.getName().toLowerCase().endsWith(".doc")) {
            FileInputStream docFileInputStream = new FileInputStream(docFileName);
            HWPFDocument docDocument = new HWPFDocument(docFileInputStream);
            //创建wor转换成html文档的转换器
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            Document htmlDocument = docBuilderFactory.newDocumentBuilder().newDocument();
            WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(htmlDocument);
            wordToHtmlConverter.setPicturesManager(new WordPicturesManager(imageSvaePath));
            wordToHtmlConverter.processDocument(docDocument);
            //处理转换后的html文档
            DOMSource domSource = new DOMSource(wordToHtmlConverter.getDocument());
            //创建输出文件流
            StreamResult streamResult = new StreamResult(new ByteArrayOutputStream());
            //设置文档转换编码格式
            TransformerFactory formerFactory = TransformerFactory.newInstance();
            Transformer transformer = formerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, encodeing);
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "html");
            transformer.transform(domSource, streamResult);
            //生成html字符串
            String htmResultStr = streamResult.getOutputStream().toString();
            //将&#x开头的html加密字符转成具体字符
            htmResultStr = StringEscapeUtils.unescapeHtml(htmResultStr);
            return htmResultStr;
        } else {
            new RuntimeException("文件不存在，或不是MS Office 2003(DOC)格式的文件!");
            return "";
        }

    }

    /**
     * 2003版本word(doc)转换成html
     *
     * @throws IOException
     * @throws TransformerException
     * @throws ParserConfigurationException
     */
    public static String docConvertToHtmlFile(String docFileName, String htmlFileName, String encodeing)
            throws TransformerException, ParserConfigurationException, IOException {
        File htmlFile = new File(htmlFileName);
        String htmResultStr = docConvertToHtmlText(docFileName, htmlFile.getParent(), encodeing);
        FileUtils.writeStringToFile(new File(htmlFileName), htmResultStr, encodeing);
        return htmResultStr;
    }

    /**
     * 2007版本word(docx)转换成htmlText
     *
     * @throws IOException
     */
    public static String docxConvertToHtmlText(String docxFileName, String imageSvaePath, String encodeing)
            throws IOException {
        File docxFile = new File(docxFileName);
        if (docxFile.exists() && docxFile.getName().toLowerCase().endsWith(".docx")) {
            // 1) 加载word文档生成 XWPFDocument对象
            FileInputStream docxFileInputStream = new FileInputStream(docxFile);
            XWPFDocument docxDocument = new XWPFDocument(docxFileInputStream);
            // 2) 解析 XHTML配置 (这里设置IURIResolver来设置图片存放的目录)
            XHTMLOptions xhtmlOptions = XHTMLOptions.create();//.indent(4);
            // 设置存放图片的基本文件夹
            xhtmlOptions.setExtractor(new FileImageExtractor(new File(imageSvaePath + "/images")));
            // 设置html中图片的路径
            xhtmlOptions.URIResolver(new BasicURIResolver("images"));
            xhtmlOptions.setIgnoreStylesIfUnused(false);
            xhtmlOptions.setFragment(true);
            // 3) 将 XWPFDocument转换成XHTML
            ByteArrayOutputStream htmlFileOutputStream = new ByteArrayOutputStream();
            OutputStreamWriter htmlStreamWriter = new OutputStreamWriter(htmlFileOutputStream, encodeing);
            XHTMLConverter xhtmlConverter = (XHTMLConverter) XHTMLConverter.getInstance();
            xhtmlConverter.convert(docxDocument, htmlStreamWriter, xhtmlOptions);
            String htmResultStr = new String(htmlFileOutputStream.toByteArray());
            htmResultStr = StringEscapeUtils.unescapeHtml(htmResultStr);
            return htmResultStr;
        } else {
            new RuntimeException("文件不存在，或不是MS Office 2007(DOCX)格式的文件!");
            return "";
        }
    }

    /**
     * 2007版本word(docx)转换成html
     *
     * @throws IOException
     */
    public static String docxConvertToHtmlFile(String docxFileName, String htmlFileName, String encodeing)
            throws IOException {
        File htmlFile = new File(htmlFileName);
        String htmResultStr = docxConvertToHtmlText(docxFileName, htmlFile.getParent(), encodeing);
        FileUtils.writeStringToFile(new File(htmlFileName), htmResultStr, encodeing);
        return htmResultStr;
    }
}
