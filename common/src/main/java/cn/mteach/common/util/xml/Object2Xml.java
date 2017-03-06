package cn.mteach.common.util.xml;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class Object2Xml {
	public static String toXml(Object obj){
		XStream xstream=new XStream();
		xstream.processAnnotations(obj.getClass());
		
		return xstream.toXML(obj);
	}
	
	public static <T> T toBean(String xmlStr,Class<T> cls){
		XStream xstream=new XStream(new DomDriver());
		xstream.processAnnotations(cls);
		@SuppressWarnings("unchecked")
		T obj=(T)xstream.fromXML(xmlStr);
		return obj;
	}

	public void readStringXml(String xml) {
		Document doc=null;
		try {
			doc=DocumentHelper.parseText(xml);
			Element rootElt = doc.getRootElement(); // 获取根节点
			Iterator iter = rootElt.elementIterator("UAnswer");
			while (iter.hasNext()){
				Element element=(Element)iter.next();
	//			String title=element.attribute("qt").getData().toString();
	//			System.out.println("title：" +title);
				Element qs=element.element("qs");
				Iterator questionIter=qs.elementIterator("UQues");
				while (questionIter.hasNext()){
					Element questionElement=(Element)questionIter.next();
					String order=questionElement.attributeValue("qo");
					String score=questionElement.attributeValue("qs");
					String qt=questionElement.attributeValue("qt");
					String questionId=questionElement.attributeValue("qi");
		//			System.out.println("questionId：" +questionId);
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public static void main(String arg[]){
		Object2Xml object2Xml=new Object2Xml();
		object2Xml.readStringXml("");
	}
}
