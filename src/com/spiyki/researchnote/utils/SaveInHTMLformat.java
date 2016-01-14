/*
Copyright 2015 Samitha W. Ekanayake

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package com.spiyki.researchnote.utils;

import java.io.*;
import java.io.File;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.html.HTMLDocument;
/**
 *
 * @author samitha_2
 */
public class SaveInHTMLformat {
    JTextPane textPane;
    AbstractDocument doc;
    String thisRecord="";
    
    public SaveInHTMLformat(JTextPane txtDisplay, AbstractDocument thisDoc){
        textPane=txtDisplay;
        doc=thisDoc;
//        textPane.setEditorKit(new HTMLEditorKit());
        WriteHTMLandSaveInDataBase();
        DeleteFile();
    }
    
    public String ReadTheString(){
        return thisRecord;
    }
    
    private String ReadHTMLandStore(){
        BufferedReader inputStream = null;        
        String strHTML="";
        try {
            inputStream = new BufferedReader(new FileReader("test.html"));
            String l;
//            int i=0;
            while ((l = inputStream.readLine()) != null) {
                strHTML+=l;                          
            }
            String[] strTemp=strHTML.split("<body>");
            String[] strRES=strTemp[1].split("</body>");
            strHTML=strRES[0];
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
        finally {
            try{
                if (inputStream != null) {                
                    inputStream.close();                
                }
                
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
        }        
        return strHTML;
    }    
    
    private void WriteHTMLandSaveInDataBase(){      
        try{
            OutputStream os = new BufferedOutputStream(new FileOutputStream("test.html"));
            HTMLDocument docs = (HTMLDocument)textPane.getDocument();
            int length = docs.getLength();
            try {
              textPane.getEditorKit().write(os, doc, 0, length);
              os.close();
            } catch (BadLocationException ex) {
                ex.printStackTrace();
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }           
        thisRecord=ReadHTMLandStore();
    }
    
    private void DeleteFile(){
        File thisFileToDel=new File("test.html");
        thisFileToDel.delete();        
    }    
    
}
