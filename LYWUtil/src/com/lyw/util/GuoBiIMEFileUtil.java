package com.lyw.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.lyw.io.ReadFile;
import com.lyw.xmlparse.DOMParseImpl;
import com.lzx.util.io.Test;

public class GuoBiIMEFileUtil {

	public GuoBiIMEFileUtil() {
		// TODO Auto-generated constructor stub
	}
	
	private void renameIconFile() {
		RenameIcon mRenameIcon = this.new RenameIcon();
		mRenameIcon.getNameList();
		mRenameIcon.renameFile();
	}
	
	private void getSettingsLanguageList() {
		this.new LanguageListUtil().getSettingsLanguageList(); 
	}
	
	public static void main(String[] args) {
		GuoBiIMEFileUtil util = new GuoBiIMEFileUtil();
//		util.renameIconFile();
//		util.new LanguageListUtil().getSettingsLanguageList();
//		util.new LanguageListUtil().getBuildPropertiseEnableLangs();
//		util.new LanguageListUtil().getBuildSomeLangDataOnlyCondition();
//		util.new LanguageListUtil().getBuildSomeLangDataOnlyIfThen();
	}
	
	private class LanguageListUtil {
		private final static String BASE_PATH = "D:/projects/AlphabeticIME_2/GuoBiIME_Alphabetic_2_Develop/res/xml/";
		
		public LanguageListUtil() {}
		
		
		private void getSettingsLanguageList() {
			int count = 0;
			ReadFile mReadFile = new ReadFile();
			List<String> list = mReadFile.getFileList(
					BASE_PATH, 
					"language_", 
					".xml");
			
			for(String str : list) {
				System.out.print("<item>");
				System.out.print(DOMParseImpl.DOMParse(BASE_PATH + str).get(0).getName());
				System.out.print("_"+DOMParseImpl.DOMParse(BASE_PATH + str).get(0).getLength());
				System.out.println("</item>");
				count++;
			}
			System.out.println("count: " + count);
		}
		
		private void getBuildPropertiseEnableLangs() {
			int count = 0;
			ReadFile mReadFile = new ReadFile();
			LinkedList<String> list = mReadFile.getFileList(
					BASE_PATH, 
					"language_", 
					".xml");
			for(String str : list) {
				System.out.println("enable."+DOMParseImpl.DOMParse(BASE_PATH + str).get(0).getLength().toLowerCase() + "=");
				count++;
			}
			System.out.println("count: " + count);
		}
		
		private void getBuildSomeLangDataOnlyCondition() {
			int count = 0;
			ReadFile mReadFile = new ReadFile();
			LinkedList<String> list = mReadFile.getFileList(
					BASE_PATH,
					"language_", 
					".xml");
			for(String str : list) {
				String isoName = DOMParseImpl.DOMParse(BASE_PATH + str).get(0).getLength().toLowerCase();
				System.out.print("\t<condition property=\"is.enable.");
				System.out.print(isoName);
				System.out.print("\"><istrue value=\"${enable.");
				System.out.print(isoName);
				System.out.println("}\"/></condition>");
				count++;
			}
			System.out.println("count: " + count);
		}
	
		private void getBuildSomeLangDataOnlyIfThen() {
			int count = 0;
			ReadFile mReadFile = new ReadFile();
			LinkedList<String> list = mReadFile.getFileList(
					BASE_PATH,
					"language_", 
					".xml");
			for(String str : list) {
				String isoName = DOMParseImpl.DOMParse(BASE_PATH + str).get(0).getLength().toLowerCase();
				System.out.print("\t\t<if condition=\"${is.enable.");
				System.out.print(isoName);
				System.out.println("}\"><then>");
				System.out.println("\t\t\t<ant antfile=\"build-by-language-data-only.xml\" target=\"${action}\">");
				System.out.println("\t\t\t\t<property name=\"language\" value=\"" + isoName + "\"/>");
				System.out.println("\t\t\t</ant>");
				System.out.println("\t\t</then></if>");
				count++;
			}
			System.out.println("count: " + count);
		}
	}
	
	private class RenameIcon {
		
		private final static String BASE_PATH = "D:/projects/AlphabeticIME_2/pic/外语言图标96_10.30/";
		
		private LinkedList<String> mChineseName;
		private LinkedList<String> mShortName;
		private HashMap<String, String> mNameMap;
		private ReadFile readFile;
		public RenameIcon() {
			mNameMap = new HashMap<String, String>();
			readFile = new ReadFile();
		}
		
		private void getNameList() {
			mChineseName = readFile.read("D:/projects/AlphabeticIME_2/doc/语言中文名.txt", true);
			mShortName = readFile.read("D:/projects/AlphabeticIME_2/doc/语言简称.txt", true);
			
			int count = mChineseName.size();
			for(int i=0; i<count; i++) {
				mNameMap.put(mChineseName.get(i), mShortName.get(i));
			}
		}
		
		private void renameFile() {
			int count = 0;
			List<String> fileList = readFile.getFileList(BASE_PATH, true);
			for(String file : fileList) {
				file = file.substring(0, file.indexOf("."));
				String shortName = mNameMap.get(file);
				if(shortName != null) {
					
					System.out.println(shortName + " " + file);
					
					Test test = new Test(BASE_PATH + file + ".png", BASE_PATH + "icon_" +shortName + ".png");
					
					count++;
				}
			}
			
			System.out.println("count: " + count);
		}
	}
}
