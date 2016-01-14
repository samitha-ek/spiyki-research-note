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
//import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;
import java.io.File;
import javax.swing.*;
import javax.swing.text.*;
//import javax.swing.event.*;
/**
 *
 * @author samitha_2
 */
public class DisplayInHTMLformat {
    JTextPane textPane;
    String sEdtID;    
    String strOutput="";
    String strFormattedOutput="";
    public DisplayInHTMLformat(String strBody, String editID, JTextPane txtDisplay){
        sEdtID=editID;
        textPane=txtDisplay;
        textPane.setEditorKit(new HTMLEditorKit());
        ConverHTMLsaveAndDisplay(strBody);
        strFormattedOutput=ReadHTMLandStore();
        DeleteFile();
        PreparePlainText();        
    }
    
    private void ConverHTMLsaveAndDisplay(String strBody){
        HTMLEditorKit htmlEDT=new HTMLEditorKit();
        String strStart="<html><head> </head><body>";
        String strEnd="</body></html>";
        String strHTML=strStart+strBody+strEnd;    
        WriteToFile(strHTML);

        try {
          FileInputStream fi = new FileInputStream(sEdtID+".html");
//          textPane.setText("");
          htmlEDT.read(fi, textPane.getDocument(), 0);
        } catch (FileNotFoundException e) {
          System.out.println("File not found");
        } catch (IOException e) {
          System.out.println("I/O error");
        } catch (BadLocationException e) {
        }

    }

    private void WriteToFile(String strWrite){
        BufferedWriter bw = null;
        try {
             bw = new BufferedWriter(new FileWriter(sEdtID+".html"));
             bw.write(strWrite);
             bw.flush();
        } 
        catch (IOException ioe) {
         ioe.printStackTrace();
        } finally {
        if(bw!=null){
            try{
                bw.close();
            }catch(IOException ioe1){
                ioe1.printStackTrace();
            }
        }
        }    
    }
    
    private void DeleteFile(){
        File thisFileToDel=new File(sEdtID+".html");
        thisFileToDel.delete();        
    }
    
    private void PreparePlainText(){
        Document dffDoc=textPane.getDocument();
        try{
            dffDoc.remove(0, 1);
        }catch(BadLocationException ex1){        }                  
        for(int k=0;k<dffDoc.getLength();k++){
            try {
                String strTemp=dffDoc.getText(k, 1);
                byte[] testws=strTemp.getBytes();
                if(testws[0]==(byte)10){
                    strTemp="\n";
                }else{
                }
                strOutput+=strTemp;
            } catch (BadLocationException ble) {
                ble.printStackTrace();
            }
        }        
    }
    
    public String GetThePlainText(){
        return strOutput; 
    }
    
    private String ReadHTMLandStore(){
        BufferedReader inputStream = null;        
        String strHTML="";
        try {
            inputStream = new BufferedReader(new FileReader(sEdtID+".html"));
            String l;
            int i=0;
            while ((l = inputStream.readLine()) != null) {
                strHTML+=l;                          
            }
            String[] strTemp=strHTML.split("<body>");
            String[] strRES=strTemp[1].split("</body>");
            strHTML=strRES[0];
        }catch(IOException ioe){
            
        }
        finally {
            try{
                if (inputStream != null) {                
                    inputStream.close();                
                }
                
            }catch(IOException ioe){
            
            }
        }        
        return strHTML;
    } 
    
}
