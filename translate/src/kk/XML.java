package kk;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XML {
	static String APP_ID;
	static String SECURITY_KEY;
	
	static {	
		try {
			// 创建XML读取对象
			SAXReader reader = new SAXReader();
			// 读取XML文件
			Document doc;
			doc = reader.read("Config/Idkey.xml");
			// 获取XML文件根节点
			Element p = doc.getRootElement();
			// 获取元素对象
			Element Idelement = p.element("APP_ID");
			Element Keyelement = p.element("SECURITY_KEY");
			// 获取元素配对的值
			APP_ID = Idelement.attributeValue("v");
			SECURITY_KEY = Keyelement.attributeValue("v");
		} catch (DocumentException e) {
			e.printStackTrace();
		}					
	}

}

