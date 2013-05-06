package com.lyw.io;

import java.io.File;

public class LookOverC {
    public static void main(String[] args) throws Exception {
        File f = new File("C:\\");
        showAllFile(f);
    }
    
    private static void showAllFile(File file) throws Exception{
        File[] files = file.listFiles();
       
        for(int i=0;i<files.length;i++){
            System.out.println(files[i].getAbsolutePath());
            if(files.length<0)
                continue;
            if(files[i].isDirectory()){
                showAllFile(files[i]);
            }
        }
    }
}
