package com.lyw.robot;

import java.io.File;   
import java.io.IOException;   
   
public class TestOCR {   
   
    /**
  * @param args
  */   
    public static void main(String[] args) {   
        String path = "d://test//wo.jpg";      
        System.out.println("ORC Test Begin......");
        try {      
            String valCode = new OCR().recognizeText(new File(path), "jpg");      
            System.out.println(valCode);      
        } catch (IOException e) {      
            e.printStackTrace();      
        } catch (Exception e) {   
            e.printStackTrace();   
        }        
        System.out.println("ORC Test End......");
    }   
}  