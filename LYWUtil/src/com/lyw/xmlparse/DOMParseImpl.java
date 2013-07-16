package com.lyw.xmlparse;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


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
			NodeList nodes = root.getElementsByTagName(RIVER);
			// 遍历根节点所有子节点,rivers 下所有river
			River river = null;
			for (int i = 0; i < nodes.getLength(); i++)
			{
				river = new River();
				// 获取river元素节点
				Element riverElement = (Element) (nodes.item(i));
				// 获取river中name属性值
				river.setName(riverElement.getAttribute(NAME));
//				river.setLength(Integer.parseInt(riverElement.getAttribute(LENGTH)));
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
			NodeList nodes = root.getElementsByTagName(RIVER);
			// 遍历根节点所有子节点,rivers 下所有river
			River river = null;
			for (int i = 0; i < nodes.getLength(); i++)
			{
				river = new River();
				// 获取river元素节点
				Element riverElement = (Element) (nodes.item(i));
				// 获取river中name属性值
				river.setName(riverElement.getAttribute(NAME));
				river.setLength(Integer.parseInt(riverElement.getAttribute(LENGTH)));
				// 获取river下introduction标签
				Element introduction = (Element) riverElement.getElementsByTagName(INTRODUCTION).item(0);
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
		for(River river : DOMParse("d:/IDE/language_latin_fr.xml")) {
			System.out.println(river.getName());;
		}
	}
	
	private static final String RIVER = "language";
	private static final String NAME = "full-name";
	private static final String LENGTH = "length";
	private static final String INTRODUCTION = "introduction";
}
