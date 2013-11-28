package com.lyw.xmlparse;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;

public class XmlProcessor{
   public Document readXML(String file){
      InputStream inputStream=this.getClass().getClassLoader().getResourceAsStream(file);
      SAXReader saxReader=new SAXReader();
      Document doc=null;
      try{
         doc=saxReader.read(inputStream);
      }catch(DocumentException e){
         e.printStackTrace();
      }
     return doc;
   }

   public static void main(String[] args){
      XmlProcessor xmlProcessor=new XmlProcessor();
      Document document=xmlProcessor.readXML("applicationContext-cxf.xml");
      //把解析路径切换到/beans下
      Node node=document.selectSingleNode("/beans");
      Map nsMap=new HashMap();
      nsMap.put("beans","http://www.springframework.org/schema/beans");
       //对document而言全路径为：/beans:beans/beans:bean 
      XPath xpath=document.createXPath("beans:bean");
      
      xpath.setNamespaceURIs(nsMap);
      List<Element> list=xpath.selectNodes(node);
      System.out.println("找到<bean></bean>节点数:"+list.size());
      //打印找到的每个目标节点的内容
      for(Element element:list){
        System.out.println(element.attributeValue("id")+":");
        System.out.println(element.asXML());
      }
      
      nsMap.put("jaxws","http://cxf.apache.org/jaxws");
      //对document而言全路径为：/beans:beans/jaxws:endpoint
      xpath=document.createXPath("jaxws:endpoint");
      list=xpath.selectNodes(node);
      System.out.println("找到<jaxws:endpoint></jaxws:endpoint>节点数:"+list.size());
      //打印找到的每个目标节点的内容
      for(Element element:list){
        System.out.println(element.attributeValue("id")+":");
        System.out.println(element.asXML());
      }
   }
}
