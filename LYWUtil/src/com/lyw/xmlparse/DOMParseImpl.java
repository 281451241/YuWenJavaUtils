package com.lyw.xmlparse;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.lyw.util.TestArray;
import com.util.P;


public class DOMParseImpl
{
	/**
	 * DOM解析
	 **/
	public static List<River> DOMParse(String fileName)
	{
		List<River> rivers = new ArrayList<River>();
		DocumentBuilderFactory factory = null;
		DocumentBuilder builder = null;
		Document document = null;
		InputStream inputStream = null;
		// 首先找到xml文件
		factory = DocumentBuilderFactory.newInstance();
		try
		{
			// 找到xml，并加载文档
			builder = factory.newDocumentBuilder();
			inputStream = new FileInputStream(fileName);
			document = builder.parse(inputStream);
			// 找到根Element
			Element root = document.getDocumentElement();
			NodeList nodes = root.getElementsByTagName(ROOT_ELEMENT);
			// 遍历根节点所有子节点,rivers 下所有river
			River river = null;
			for (int i = 0; i < nodes.getLength(); i++)
			{
				river = new River();
				// 获取river元素节点
				Element riverElement = (Element) (nodes.item(i));
				// 获取river中name属性值
				river.setName(riverElement.getAttribute(ELEMENT_1));
				String element_2 = riverElement.getAttribute(ELEMENT_2);
				if(element_2 != null && element_2.length() > 1)
					river.setLength(element_2);
				else
					river.setLength(riverElement.getAttribute(ELEMENT_3));
				// 获取river下introduction标签
//				Element introduction = (Element) riverElement.getElementsByTagName(INTRODUCTION).item(0);
//				river.setIntroduction(introduction.getFirstChild().getNodeValue());
				rivers.add(river);
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		} catch (SAXException e)
		{
			e.printStackTrace();
		} catch (ParserConfigurationException e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				inputStream.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return rivers;
	}
	
	public static String[] DOMParse(String fileName,String rootStr,String itemStr)
	{
		String[] res = null;
		DocumentBuilderFactory factory = null;
		DocumentBuilder builder = null;
		Document document = null;
		InputStream inputStream = null;
		// 首先找到xml文件
		factory = DocumentBuilderFactory.newInstance();
		try
		{
			// 找到xml，并加载文档
			builder = factory.newDocumentBuilder();
			inputStream = new FileInputStream(fileName);
			document = builder.parse(inputStream);
			
			// 找到根Element
			Element root = document.getDocumentElement();
			
			NodeList nodes =  root.getElementsByTagName(rootStr);
			
			for(int index=0,n=nodes.getLength(); index<n; index++) {
				
				Element node = (Element)nodes.item(index);
				NodeList itemList = node.getElementsByTagName(itemStr);
				int nl=itemList.getLength();
				res = new String[nl];
				for(int i=0; i<nl; i++){
					res[i] = itemList.item(i).getTextContent();
				}
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		} catch (SAXException e)
		{
			e.printStackTrace();
		} catch (ParserConfigurationException e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				inputStream.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		return res;
	}
	
	public static String[] DOMParseXml(String fileName,String rootStr,String itemStr) {
		String[] result = null;
		
		Document document = getInstance().getDocument(fileName);
		
		Element root = document.getDocumentElement();
		
		NodeList nodes =  root.getElementsByTagName(rootStr);
		
		for(int index=0,n=nodes.getLength(); index<n; index++) {
			
			Element node = (Element)nodes.item(index);
			NodeList itemList = node.getElementsByTagName(itemStr);
			int nl=itemList.getLength();
			result = new String[nl];
			for(int i=0; i<nl; i++){
				result[i] = itemList.item(i).getTextContent();
			}
		}
		
		return result;
	}
	
	private final Document getDocument(String xmlFileName) {
		Document document = null;
		try
		{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputStream fileStream = new FileInputStream(xmlFileName);
			document = builder.parse(fileStream);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (SAXException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (ParserConfigurationException e)
		{
			e.printStackTrace();
		}
		return document;
	}
	
	public void getItem(String fileName,String rootStr,String itemStr) {
		List<River> rivers = new ArrayList<River>();
		DocumentBuilderFactory factory = null;
		DocumentBuilder builder = null;
		Document document = null;
		InputStream inputStream = null;
		// 首先找到xml文件
		factory = DocumentBuilderFactory.newInstance();
		try
		{
			// 找到xml，并加载文档
			builder = factory.newDocumentBuilder();
			inputStream = new FileInputStream(fileName);
			document = builder.parse(inputStream);
			// 找到根Element
			Element root = document.getDocumentElement();
			NodeList nodes = root.getElementsByTagName(rootStr);
			// 遍历根节点所有子节点,rivers 下所有river
			River river = null;
			for (int i = 0; i < nodes.getLength(); i++)
			{
				river = new River();
				// 获取river元素节点
				Element riverElement = (Element) (nodes.item(i));
				// 获取river中name属性值
				river.setName(riverElement.getAttribute(ELEMENT_1));
				String element_2 = riverElement.getAttribute(ELEMENT_2);
				if(element_2 != null && element_2.length() > 1)
					river.setLength(element_2);
				else
					river.setLength(riverElement.getAttribute(ELEMENT_3));
				// 获取river下introduction标签
				Element introduction = (Element) riverElement.getElementsByTagName(itemStr).item(0);
				
				NodeList itemList = riverElement.getElementsByTagName(itemStr);
				for(int index=0,n=itemList.getLength(); index<n; index++) {
					Element item = (Element)itemList.item(index);
//					System.out.println(item.getFirstChild().getNodeValue());
					
					getArray(item.getFirstChild().getNodeValue(), index);
				}
				tranArray();
				river.setIntroduction(introduction.getFirstChild().getNodeValue());
				rivers.add(river);
//				System.out.println("itemStr: " + river.getIntroduction());
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		} catch (SAXException e)
		{
			e.printStackTrace();
		} catch (ParserConfigurationException e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				inputStream.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	String[][] nodeValues = new String[5][];
	public void getArray(String nodeValue, int idx) {
		nodeValues[idx] = nodeValue.split(" ");
//		for(int i=0; i<nodeValues[idx].length; i++) {
//			System.out.println(nodeValues[idx][i]);
//		}
	}
	
	public void tranArray() {
		int count = nodeValues[0].length;
		TestArray ta = new TestArray();
		for(int i=0;i<count;i++) {
			P.printSurroundBy(ta.tranArray(nodeValues, i),", ",
					"new String[]{","},",
					"\"","\"");
		}
	}
	
	public List f(String fileName) {
		List<River> rivers = new ArrayList<River>();
		DocumentBuilderFactory factory = null;
		DocumentBuilder builder = null;
		Document document = null;
		InputStream inputStream = null;
		// 首先找到xml文件
		factory = DocumentBuilderFactory.newInstance();
		try
		{
			// 找到xml，并加载文档
			builder = factory.newDocumentBuilder();
			inputStream = new FileInputStream(fileName);
			document = builder.parse(inputStream);
			// 找到根Element
			Element root = document.getDocumentElement();
			NodeList nodes = root.getElementsByTagName(ROOT_ELEMENT);
			// 遍历根节点所有子节点,rivers 下所有river
			River river = null;
			for (int i = 0; i < nodes.getLength(); i++)
			{
				river = new River();
				// 获取river元素节点
				Element riverElement = (Element) (nodes.item(i));
				// 获取river中name属性值
				river.setName(riverElement.getAttribute(ELEMENT_1));
				String element_2 = riverElement.getAttribute(ELEMENT_2);
				if(element_2 != null && element_2.length() > 1)
					river.setLength(element_2);
				else
					river.setLength(riverElement.getAttribute(ELEMENT_3));
				// 获取river下introduction标签
				Element introduction = (Element) riverElement.getElementsByTagName(ELEMENT_3).item(0);
				river.setIntroduction(introduction.getFirstChild().getNodeValue());
				rivers.add(river);
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		} catch (SAXException e)
		{
			e.printStackTrace();
		} catch (ParserConfigurationException e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				inputStream.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return rivers;
	}
	
	public static void main(String[] args)
	{
//		for(River river : DOMParse("d:/IDE/language_latin_fr.xml")) {
//			System.out.println(river.getLength());;
//		}
		
//		new DOMParseImpl().getItem("D:/other/xml/majin_string.xml", "string-array", "item");
		
		DOMParseImpl.DOMParse("D:/other/xml/majin_string.xml", "string-array", "item");
	}
	
	public static final DOMParseImpl getInstance() {
		if(_instance == null) 
			_instance = new DOMParseImpl();
		return _instance;
	}
	
	private DOMParseImpl()
	{
	}
	
	private static DOMParseImpl _instance = null;
	
	private static final String ROOT_ELEMENT = "language";
	private static final String ELEMENT_1 = "full-name";
	private static final String ELEMENT_2 = "iso-name";
	private static final String ELEMENT_3 = "shot-name";
}
