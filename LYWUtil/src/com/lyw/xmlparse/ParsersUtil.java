package com.lyw.xmlparse;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * XML解析工具类
 * 
 * @author javamickey
 * @date 2012-11-01
 * @version 2.0
 */
public class ParsersUtil {

	/**
	 * 通过XML文件名，来解析XML对象，此函数只针对XML中存在一个bean值的情况
	 * 
	 * @paramfileName XML文件名
	 * @param typebean类名
	 * @return 单个bean对象
	 * @throwsException
	 */
	public static Object parserXMLByFileName(String fileName, Class<?> type)
			throws Exception {
		Class<? extends Object> beanClass = Class.forName(type.getName());
		Object bean = beanClass.newInstance();
		File inputXml = new File(fileName);
		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read(inputXml);
			Element element = document.getRootElement();
			BeanInfo beanInfo = Introspector.getBeanInfo(type);
			PropertyDescriptor[] propertyDescriptors = beanInfo
					.getPropertyDescriptors();
			for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
				String propertyName = propertyDescriptor.getName();
				for (Iterator<?> iterator = element.elementIterator(); iterator
						.hasNext();) {
					Element employeeElement = (Element) iterator.next();
					if (!propertyName.equals("class")) {
						Method readMethod = propertyDescriptor.getWriteMethod();
						String value = employeeElement
								.elementText(propertyName);
						System.out.println("propertyName=" + propertyName
								+ "value = " + value);
						if (value != null) {
							readMethod.invoke(type, value);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}

	/**
	 * 通过XML文件名，来解析XML对象，此函数针对XML中存在一个或者多个bean值的情况
	 * 
	 * @paramfileName XML文件名
	 * @param typebean类名
	 * @return 返回一个List<bean>
	 * @throwsException
	 */
	public static List<Object> parserXMLByFileNameList(String fileName,
			Class<?> type) throws Exception {
		Class<? extends Object> beanClass = Class.forName(type.getName());
		List<Object> beanList = new ArrayList<Object>();
		File inputXml = new File(fileName);
		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read(inputXml);
			Element element = document.getRootElement();
			BeanInfo beanInfo = Introspector.getBeanInfo(type);
			PropertyDescriptor[] propertyDescriptors = beanInfo
					.getPropertyDescriptors();
			for (Iterator<?> iterator = element.elementIterator(); iterator
					.hasNext();) {
				Object bean = beanClass.newInstance();
				Element employeeElement = (Element) iterator.next();
				for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
					String propertyName = propertyDescriptor.getName();
					if (!propertyName.equals("class")) {
						Method readMethod = propertyDescriptor.getWriteMethod();
						String value = employeeElement
								.elementText(propertyName);
						System.out.println("propertyName=" + propertyName
								+ "value = " + value);
						if (value != null) {
							readMethod.invoke(bean, value);

						}
					}
				}
				beanList.add(bean);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return beanList;
	}

}