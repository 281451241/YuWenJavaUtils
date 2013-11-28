package com.lyw.xmlparse;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;

public class ReadXML {
	
	private final static String BASE_FILE = "C:/Users/GB/Desktop/kb_latin_1_qwerty_0.xml";

	public ReadXML() {
	}

	/**
	 * 获得xml文档
	 * 
	 * @param fileName
	 * @return
	 */
	public static Document getDocument() {
		Document doc = null;
		
		if (doc == null) {
			try {
				File file = new File(BASE_FILE);
				SAXReader sax = new SAXReader();
				doc = sax.read(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return doc;
	}
	public static Document getDocument(String nameSpace) {
		Document doc = null;
		 Map map = new HashMap();
         map.put(nameSpace,"http://schemas.android.com/apk/res/android");
		if (doc == null) {
			try {
				File file = new File(BASE_FILE);
				SAXReader sax = new SAXReader();
				sax.getDocumentFactory().setXPathNamespaceURIs(map);
				doc = sax.read(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return doc;
	}
	
	/**
	 * 通过传入自定义标签名称 得到相应的List集合
	 * 
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Element> readXMLElementName(String name) {
		Document doc = getDocument();
		List<Element> list = (List<Element>) doc.selectNodes(name);
		return list;
	}

	/**
	 * 通过传入自定义标签名称 得到相应的Element对象
	 * 
	 * @param name
	 * @return
	 */
	public static Element readXMLElementName1(String name) {

		Document doc = getDocument();
		Element element = (Element) doc.selectSingleNode(name);
		return element;
	}
	
//	private final Attribute getAttribute(String nodeName, String attrName) {
//		return readXMLElementName1(nodeName).attribute(attrName);
//	}
	
	private final List getNodes(String nodeName) {
		Document doc = getDocument();
		return doc.selectNodes(nodeName);
	}
	
	private final void getAttribute(String nodeName, String attrName) {
		int idx = attrName.indexOf(":");
		List<Element> nodeList = readXMLElementName(nodeName);
		QName qn = null;
		if(idx < 0) {
			qn = new QName(nodeName);
		} else {
			String ns = attrName.substring(0, idx);
			System.out.println(ns);
			Namespace np = new NamespaceFactory().mNameSpaceList.get(ns);
			
			if(np == null) {
				System.out.println("Namespace is null");
				return;
			}
				
			qn = new QName(attrName.substring(idx + 1), np);
		}
		output(nodeList, qn);
	}
	
	private static void output(List<Element> nodeList, QName qn) {
		Iterator<Element> it = nodeList.iterator();
		while(it.hasNext()) {
			Element element = (Element)it.next();
			Attribute attr = element.attribute(qn);
			if(attr == null) {
				System.out.println("Attribute is null!~....");
				continue;
			}
			System.out.println(attr.getName() + ":" + attr.getStringValue());
		}
	}
	
	public static void main(String[] args) {
//		System.out.println(readXMLElementName1("default_config").attribute("keyboard-type").getStringValue());
		
		
		
		
		ReadXML r = new ReadXML();
		r.getAttribute("/Keyboard/Row/Key", "android:codes");
//		Iterator it = r.getNodes("/Keyboard/Row/Key").iterator();
//		while(it.hasNext()) {
//			Element element = (Element)it.next();
//			
//			Attribute attr = element.attribute("android:codes");
//			if(attr == null) {
//				System.out.println("Attribute is null!~....");
//				continue;
//			}
//			System.out.println(attr.getName() + ":" + attr.getStringValue());
//		}
//		System.out.println(new ReadXML().getAttribute("keyboard-26", "name"));
	}
	
	class NamespaceFactory {
		private Map<String, Namespace> mNameSpaceList = null;
		
		public NamespaceFactory() {
			mNameSpaceList = new HashMap<String, Namespace>();
			mNameSpaceList.put("android", new Namespace("android","http://schemas.android.com/apk/res/android"));
		}
		
		public void addNamespace(String prefix, String uri) {
			mNameSpaceList.put("android", new Namespace(prefix, uri));
		}
		
	}
}
