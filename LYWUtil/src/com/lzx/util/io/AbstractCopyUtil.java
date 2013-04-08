package com.lzx.util.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCopyUtil{
	public final static int BYTE=0;
	public final static int CHAR=1;
	private int copyType=0;
	private File sourceFile;
	private File targetFile;
	private String inputLanguage;
	private String outputLanguage;
	private boolean isCover = false;
	private String[] bytePostfix;
	private String[] charPostfix;
	private List<String> bytePostfixList=new ArrayList<String>();
	private List<String> charPostfixList=new ArrayList<String>();
	

	public AbstractCopyUtil(File sourceFile,File targetFile,
				String inputLanguage,String outputLanguage,boolean isCover,int copyType){
		this.isCover=isCover;
		this.bytePostfix=setBytePostfix(this.bytePostfixList);
		this.charPostfix=setCharPostfix(this.charPostfixList);
		this.copyType=copyType;
		if(sourceFile!=null&&targetFile!=null){
			copy(sourceFile, targetFile, inputLanguage, outputLanguage);
		}
		
		
	}
	public AbstractCopyUtil(File sourceFile,File targetFile,String inputLanguage,String outputLanguage){
		this(sourceFile, targetFile, inputLanguage, outputLanguage, false,AbstractCopyUtil.BYTE);
		
	}
	public AbstractCopyUtil(File sourceFile,File targetFile,boolean isCover){
		this(sourceFile, targetFile, null, null, isCover,AbstractCopyUtil.BYTE);
		
	}
	public AbstractCopyUtil(File sourceFile,File targetFile){
		this(sourceFile, targetFile, null, null,false,AbstractCopyUtil.BYTE);
	}
	public AbstractCopyUtil(String sourcePath,String targetPath){
		this(new File(sourcePath), new File(targetPath), null, null,false,AbstractCopyUtil.BYTE);
	}
	public AbstractCopyUtil(){
		
	}
	


	
	
	private  void  copy(File sourceFile,File targetFile,String inputLanguage,String outputLanguage){
		if(sourceFile.isDirectory()){
			copyFolder(sourceFile, targetFile, inputLanguage, outputLanguage);
		}else if(sourceFile.isFile()){
			copyFile(sourceFile, targetFile, inputLanguage, outputLanguage);
		}
	}
	private void copyFolder(File sourceFile,File targetFile,String inputLanguage,String outputLanguage){
		try {
			String foldPath=createFolder(targetFile, isCover);
			File[] files=sourceFile.listFiles();
			for(File f:files){
				String name=f.getName();
				File temp=new File(foldPath+File.separator+name);
				if(f.isDirectory()){
					copyFolder(f, temp, inputLanguage, outputLanguage);
				}else{
					copyFile(f, temp, inputLanguage, outputLanguage);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void copyFile(File sourceFile,File targetFile,String inputLanguage,String outputLanguage){
		boolean byteCopy=false;
		boolean charCopy=false;
		if(charPostfix!=null&&charPostfix.length>=1&&exist(bytePostfix, sourceFile)){
			charCopy=true;
		}
		if(!charCopy&&charPostfixList.size()>=1&&exist(charPostfixList, sourceFile)){
			charCopy=true;
		}
		if(bytePostfix!=null&&bytePostfix.length>=1&&exist(bytePostfix, sourceFile)){
			byteCopy=true;
		}
		if(!byteCopy&&bytePostfixList.size()>=1&&exist(bytePostfixList, sourceFile)){
			byteCopy=true;
		}
	
		if(charCopy&&!byteCopy){
			copyFileByLanguage(sourceFile, targetFile, inputLanguage, outputLanguage);
		}else if(!charCopy&&byteCopy){
			copyFileByByte(sourceFile, targetFile);
		}else{
			if(copyType==AbstractCopyUtil.CHAR){
				copyFileByLanguage(sourceFile, targetFile, inputLanguage, outputLanguage);
			}else{
				copyFileByByte(sourceFile, targetFile);
			}
		}
	}
	private boolean exist(String[] postfix,File file){
		for(String temp:postfix){
			if(file.getName().endsWith(temp)){
				return true;
			}
		}
		return false;
	}
	private boolean exist(List<String> postfix,File file){
		for(String temp:postfix){
			if(file.getName().endsWith(temp)){
				return true;
			}
		}
		return false;
	}
	/**
	 * 创建目标文件夹
	 * @param targetFile
	 * @param isCover 是否覆盖
	 * @return
	 */
	private String createFolder(File targetFile,boolean isCover){
		String path=changeFolderTitle(targetFile,targetFile.getAbsolutePath());
		if(isCover&&targetFile.exists()){
			del(targetFile);
		}
		new File(path).mkdir();
		return path;
		
	}
	public boolean copyFileByByte(File sourceFile, File targetFile) {
		boolean flag=true;
		try{
			String targetPath=changeFileTitle(targetFile,targetFile.getAbsolutePath());
			FileInputStream fis=new FileInputStream(sourceFile);
			FileOutputStream fos=new FileOutputStream(targetPath);
			int len;
			byte[] buff=new byte[1024];
			while((len=fis.read(buff))!=-1){
				fos.write(buff, 0, len);
			}
			fis.close();
			fos.close();
		}catch (Exception e) {
			flag=false;
			e.printStackTrace();
		}
		return flag;
		
	}
	public boolean copyFileByLanguage(File sourceFile, File targetFile,String inputLanguage, String outputLanguage) {
		boolean flag=false;
		try {
			String targetPath=changeFileTitle(targetFile,targetFile.getAbsolutePath());
			StringBuffer sb=new StringBuffer();
			FileInputStream fis=new FileInputStream(sourceFile);
			FileOutputStream fos=new FileOutputStream(targetPath);
			Reader reader=null;
			
			if(inputLanguage!=null){
				new InputStreamReader(fis, inputLanguage);
			}else{
				new InputStreamReader(fis);
			}
			Writer writer=null;
			if(outputLanguage!=null){
				new OutputStreamWriter(fos,outputLanguage);
			}else{
				new OutputStreamWriter(fos);
			}
			int len;
			char[] buff=new char[1024];
			while((len=reader.read(buff))!=-1){
				sb.append(buff,0,len);
			}
			writer.write(changeContent(sb.toString()));
			reader.close();
			fis.close();
			writer.close();
			fos.close();
			flag=true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return flag;
	}
	/**
	 * 删除指定文件夹或文件
	 * @param target
	 * @return
	 */
	public  boolean del(File target){
		boolean flag=false;
		try {
			if(target.isDirectory()){
				File[] files=target.listFiles();
				for(File f:files){
					del(f);
				}
				flag=target.delete();
			}else{
				flag=target.delete();;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	/**
	 * 改变文件夹标题
	 * @param sourceFile
	 * @param allPath
	 * @return
	 */
	public abstract String changeFolderTitle(File sourceFile,String allPath);
	/**
	 * 改变文件标题
	 * @param sourceFile
	 * @param allPath
	 * @return
	 */
	public abstract String changeFileTitle(File sourceFile,String allPath);
	/**
	 * 改变文件内容
	 * @param content
	 * @return
	 */
	public abstract String changeContent(String content);
	/**
	 * 设置要字符流复制的文件后缀
	 * @param charPostfixList
	 * @return
	 */
	public abstract String[] setCharPostfix(List<String> charPostfixList);
	/**
	 * 设置要字节流复制的文件后缀
	 * @param bytePostfixList
	 * @return
	 */
	public abstract String[] setBytePostfix(List<String> bytePostfixList);
	
}
