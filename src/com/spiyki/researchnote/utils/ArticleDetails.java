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

import javax.swing.text.*;
import javax.swing.JTextPane;

/**
 *
 * @author samitha_2
 */
public class ArticleDetails {
    public String GetTheFormattedReference(String[] ArticleInfo){
        String[] strArticleTypes={ "Journal Article", "Book", "Book Chapter", "Conference", "Technical Report", "Thesis" };
        String strOutPut="";
        
        if(ArticleInfo[0].compareTo(strArticleTypes[0])==0){//Journal article
            /*
             * This follows the format:
             * Author (year)."Title".Journal,Vol<(Iss)>,Pages<. doi: DOI>
             */
            if(ArticleInfo[8].compareTo("")!=0){
                strOutPut=GetAuthorListFormatted(ArticleInfo[2])+" ("+ArticleInfo[4]+"). \""+ArticleInfo[1]+"\". "+ArticleInfo[3] +". "+ArticleInfo[7]+"("+ArticleInfo[8]+"), "+ArticleInfo[6];                
            }else{
                strOutPut=GetAuthorListFormatted(ArticleInfo[2])+" ("+ArticleInfo[4]+"). \""+ArticleInfo[1]+"\". "+ArticleInfo[3] +". "+ArticleInfo[7]+", "+ArticleInfo[6];                
            }
            if(ArticleInfo[13].compareTo("")!=0){
                strOutPut+=", doi: "+ArticleInfo[13];
            }
        }
        
        if(ArticleInfo[0].compareTo(strArticleTypes[1])==0){//Book
            if(ArticleInfo[2].compareTo("")!=0){//if not an edited book
                strOutPut=GetAuthorListFormatted(ArticleInfo[2]);                
            }else{
                if(ArticleInfo[12].compareTo("")!=0){//if editors are available
                    strOutPut=GetAuthorListFormatted(ArticleInfo[12])+"(Ed.) ";
                }else{//if not this is a company article, so publisher as the author
                    strOutPut=ArticleInfo[10];
                }
            }
            strOutPut+=" ("+ArticleInfo[4]+"). "+ArticleInfo[3];//Add the publication year and the book title
            if(ArticleInfo[8].compareTo("")!=0) {//If this has an edition
                strOutPut+=SetEdition(ArticleInfo[8]);
            }
            if(ArticleInfo[7].compareTo("")!=0) {//If this has a volume
                strOutPut+=": Vol. "+ArticleInfo[7]+".";
                if(ArticleInfo[9].compareTo("")!=0) {//If this is a book series
                    strOutPut+=" "+ArticleInfo[9];
                }
            }else{
                if(ArticleInfo[9].compareTo("")!=0) {//If this is a book series
                    strOutPut+=": Ser. "+ArticleInfo[9];
                }                
            }
            strOutPut+=". "+ArticleInfo[11] +": "+ArticleInfo[10];//publisher add and publisher
        }
        
        if(ArticleInfo[0].compareTo(strArticleTypes[2])==0){//Book Chapter
//            strOutPut=GetAuthorListFormatted(ArticleInfo[2])+", \""+ArticleInfo[1]+"\", "+ArticleInfo[3] + ", Eds "+ ArticleInfo[12]+","+ArticleInfo[11] +", "+ArticleInfo[10]+" ("+ArticleInfo[4]+") pp "+ArticleInfo[6];
            strOutPut=GetAuthorListFormatted(ArticleInfo[2]);//Author list
            strOutPut+=" ("+ArticleInfo[4]+"). "+ArticleInfo[1];//Add the publication year and the chapter title
            strOutPut+=". In "+GetAuthorListFormatted(ArticleInfo[12])+" (Eds.), "+ArticleInfo[3];//Editors and book title
            if(ArticleInfo[8].compareTo("")!=0) {//If this has an edition
                strOutPut+=SetEdition(ArticleInfo[8]);
            }
            if(ArticleInfo[7].compareTo("")!=0) {//If this has a volume
                strOutPut+=": Vol. "+ArticleInfo[7]+".";
                if(ArticleInfo[9].compareTo("")!=0) {//If this is a book series
                    strOutPut+=" "+ArticleInfo[9];
                }
            }else{
                if(ArticleInfo[9].compareTo("")!=0) {//If this is a book series
                    strOutPut+=": Ser. "+ArticleInfo[9];
                }                
            }            
            strOutPut+=" (pp. "+ArticleInfo[6]+")";//pages
            strOutPut+=". "+ArticleInfo[11] +": "+ArticleInfo[10];//publisher add and publisher
        }
        
        if(ArticleInfo[0].compareTo(strArticleTypes[3])==0){//Conference Article
//            strOutPut=GetAuthorListFormatted(ArticleInfo[2])+", \""+ArticleInfo[1]+"\", "+ArticleInfo[3] + ", Eds "+ ArticleInfo[12]+","+ArticleInfo[11] +", "+ArticleInfo[10]+" ("+ArticleInfo[4]+") pp "+ArticleInfo[6];
            strOutPut=GetAuthorListFormatted(ArticleInfo[2]);//Author list
            strOutPut+=" ("+ArticleInfo[4]+"). "+ArticleInfo[1];//Add the publication year and the chapter title
            strOutPut+=". In ";
            if(ArticleInfo[12].compareTo("")!=0){//if editors are available
                strOutPut+=GetAuthorListFormatted(ArticleInfo[12])+" (Eds.), ";
            }          
            strOutPut+=ArticleInfo[3];//Conference title
            strOutPut+=" (pp. "+ArticleInfo[6]+")";//pages
            strOutPut+=". "+ArticleInfo[11] +": "+ArticleInfo[10];//publisher add and publisher            
        }
        
        if(ArticleInfo[0].compareTo(strArticleTypes[4])==0){//Technical Report
            if(ArticleInfo[2].compareTo("")!=0){//if not an edited book
                strOutPut=GetAuthorListFormatted(ArticleInfo[2]);                
            }else{
                if(ArticleInfo[12].compareTo("")!=0){//if editors are available
                    strOutPut=GetAuthorListFormatted(ArticleInfo[12])+"(Ed.) ";
                }else{//if not this is a company article, so publisher as the author
                    strOutPut=ArticleInfo[10];
                }
            }
            strOutPut+=" ("+ArticleInfo[4]+"). "+ArticleInfo[3];//Add the publication year and the book title
            if(ArticleInfo[8].compareTo("")!=0) {//If this has an edition
                strOutPut+=SetEdition(ArticleInfo[8]);
            }
            if(ArticleInfo[7].compareTo("")!=0) {//If this has a volume
                strOutPut+=": Vol. "+ArticleInfo[7]+".";
                if(ArticleInfo[9].compareTo("")!=0) {//If this is a book series
                    strOutPut+=" "+ArticleInfo[9];
                }
            }else{
                if(ArticleInfo[9].compareTo("")!=0) {//If this is a book series
                    strOutPut+=": Ser. "+ArticleInfo[9];
                }                
            }
            strOutPut+=". "+ArticleInfo[11] +": "+ArticleInfo[10];//publisher add and publisher
        }
        
        if(ArticleInfo[0].compareTo(strArticleTypes[5])==0){//Thesis
            if(ArticleInfo[2].compareTo("")!=0){//if not an edited book
                strOutPut=GetAuthorListFormatted(ArticleInfo[2]);                
            }else{
                if(ArticleInfo[12].compareTo("")!=0){//if editors are available
                    strOutPut=GetAuthorListFormatted(ArticleInfo[12])+"(Ed.) ";
                }else{//if not this is a company article, so publisher as the author
                    strOutPut=ArticleInfo[10];
                }
            }
            strOutPut+=" ("+ArticleInfo[4]+"). "+ArticleInfo[3];//Add the publication year and the book title
            if(ArticleInfo[9].compareTo("")!=0) {//If this is a book series
                strOutPut+=", "+ArticleInfo[9];
            }                            
            strOutPut+=". "+ArticleInfo[11] +": "+ArticleInfo[10];//publisher add and publisher
        }        
        return strOutPut;
    }
    
    private Document IncludeFormattedSection(String strSection, Document docIn){
        JTextPane dff=new JTextPane(); 
        DisplayInHTMLformat disp=new DisplayInHTMLformat(strSection, "test", dff);
        Document dffDoc=dff.getDocument();
        Document docOut=docIn;
        int LastPos=docOut.getLength();
        Boolean LineBreakCheck=false;
        for(int k=0;k<dffDoc.getLength();k++){
            try {
                dff.setCaretPosition(k);
                String strTemp=dffDoc.getText(k, 1);
                byte[] testws=strTemp.getBytes();
                if(testws[0]==(byte)10){
                    LineBreakCheck=true;
                    strTemp="\n";
                }else{
                    LineBreakCheck=false;
                }
                if(!LineBreakCheck){
                    AttributeSet attThis=dff.getCharacterAttributes();
                    docOut.insertString(LastPos+k, strTemp, attThis);  
                }else{
                    docOut.insertString(LastPos+k, strTemp, null);  
                }
            } catch (BadLocationException ble) {
                ble.printStackTrace();
            }
        }
        return docOut;
    }
    
    private Document IncludeNonFormattedSection(String strSection, Document docIn){
        Document docOut=docIn;
        int LastPos=docOut.getLength();
        try{
            docOut.insertString(LastPos, strSection, null);
        }catch(BadLocationException ex){}
        
        return docOut;
    }
    
    public Document GetTheFormattedReferenceWithStyle(String[] ArticleInfo){
        JTextPane tempPane=new JTextPane(); 
        DisplayInHTMLformat disp=new DisplayInHTMLformat(" ", "test", tempPane);
        Document docOut=tempPane.getDocument();
        try{
            docOut.remove(0, 1);
        }catch(BadLocationException s){}
        String[] strArticleTypes={ "Journal Article", "Book", "Book Chapter", "Conference", "Technical Report", "Thesis" };
          
        if(ArticleInfo[0].compareTo(strArticleTypes[0])==0){//Journal article
            String strOutPut="";           
            docOut=IncludeNonFormattedSection(GetAuthorListFormatted(ArticleInfo[2])+" ("+ArticleInfo[4]+"). \"", docOut);
            docOut=IncludeFormattedSection(ArticleInfo[1], docOut);
            docOut=IncludeNonFormattedSection("\". ", docOut);
            docOut=IncludeFormattedSection(ArticleInfo[3], docOut);
            if(ArticleInfo[8].compareTo("")!=0){
                strOutPut=". "+ArticleInfo[7]+"("+ArticleInfo[8]+"), "+ArticleInfo[6];                
            }else{
                strOutPut=". "+ArticleInfo[7]+", "+ArticleInfo[6];                
            }
            if(ArticleInfo[13].compareTo("")!=0){
                strOutPut+=", doi: "+ArticleInfo[13];
            }             
            docOut=IncludeNonFormattedSection(strOutPut, docOut);
          
        }
        
        if(ArticleInfo[0].compareTo(strArticleTypes[1])==0){//Book
            String strOutPut="";  
            if(ArticleInfo[2].compareTo("")!=0){//if not an edited book
                strOutPut=GetAuthorListFormatted(ArticleInfo[2]);                
            }else{
                if(ArticleInfo[12].compareTo("")!=0){//if editors are available
                    strOutPut=GetAuthorListFormatted(ArticleInfo[12])+"(Ed.) ";
                }else{//if not this is a company article, so publisher as the author
                    strOutPut=ArticleInfo[10];
                }
            }            
            strOutPut+=" ("+ArticleInfo[4]+"). ";//Add the publication year 
            docOut=IncludeNonFormattedSection(strOutPut, docOut);//These non-formatted
            docOut=IncludeFormattedSection(ArticleInfo[3], docOut);//and the book title formatted
            strOutPut="";  
            if(ArticleInfo[8].compareTo("")!=0) {//If this has an edition
                strOutPut+=SetEdition(ArticleInfo[8]);
            }
            if(ArticleInfo[7].compareTo("")!=0) {//If this has a volume
                strOutPut+=": Vol. "+ArticleInfo[7]+".";
                if(ArticleInfo[9].compareTo("")!=0) {//If this is a book series
                    strOutPut+=" ";
                }
            }else{
                if(ArticleInfo[9].compareTo("")!=0) {//If this is a book series
                    strOutPut+=": Ser. ";
                }                
            }
            docOut=IncludeNonFormattedSection(strOutPut, docOut);//These non-formatted
            if(ArticleInfo[9].compareTo("")!=0) {//If this is a book series
                docOut=IncludeFormattedSection(ArticleInfo[9], docOut);//Formatted Series title
            }   
            strOutPut=". "+ArticleInfo[11] +": "+ArticleInfo[10];//publisher add and publisher  
            docOut=IncludeNonFormattedSection(strOutPut, docOut);//These non-formatted
        }
        
        if(ArticleInfo[0].compareTo(strArticleTypes[2])==0){//Book Chapter
            String strOutPut="";  
            strOutPut=GetAuthorListFormatted(ArticleInfo[2])+" ("+ArticleInfo[4]+"). \"";//Author list and year
            docOut=IncludeNonFormattedSection(strOutPut, docOut);//These non-formatted
            docOut=IncludeFormattedSection(ArticleInfo[1], docOut);//Formatted article title

            strOutPut="\". In "+GetAuthorListFormatted(ArticleInfo[12])+" (Eds.), ";//Editors
            docOut=IncludeNonFormattedSection(strOutPut, docOut);//These non-formatted
            
            docOut=IncludeFormattedSection(ArticleInfo[3], docOut);//and the book title formatted
            
            strOutPut="";  
            if(ArticleInfo[8].compareTo("")!=0) {//If this has an edition
                strOutPut+=SetEdition(ArticleInfo[8]);
            }
            if(ArticleInfo[7].compareTo("")!=0) {//If this has a volume
                strOutPut+=": Vol. "+ArticleInfo[7]+".";
                if(ArticleInfo[9].compareTo("")!=0) {//If this is a book series
                    strOutPut+=" ";
                }
            }else{
                if(ArticleInfo[9].compareTo("")!=0) {//If this is a book series
                    strOutPut+=": Ser. ";
                }                
            }
            docOut=IncludeNonFormattedSection(strOutPut, docOut);//These non-formatted
            if(ArticleInfo[9].compareTo("")!=0) {//If this is a book series
                docOut=IncludeFormattedSection(ArticleInfo[9], docOut);//Formatted Series title
            }   
            strOutPut=". "+ArticleInfo[11] +": "+ArticleInfo[10];//publisher add and publisher  
            docOut=IncludeNonFormattedSection(strOutPut, docOut);//These non-formatted            
        }
        
        if(ArticleInfo[0].compareTo(strArticleTypes[3])==0){//Conference Article
            String strOutPut="";  
            strOutPut=GetAuthorListFormatted(ArticleInfo[2])+" ("+ArticleInfo[4]+"). \"";//Author list and year
            docOut=IncludeNonFormattedSection(strOutPut, docOut);//These non-formatted
            docOut=IncludeFormattedSection(ArticleInfo[1], docOut);//Formatted article title
            
            strOutPut="\". In ";
            if(ArticleInfo[12].compareTo("")!=0){//if editors are available
                strOutPut+=GetAuthorListFormatted(ArticleInfo[12])+" (Eds.), ";
            }          
            docOut=IncludeNonFormattedSection(strOutPut, docOut);//These non-formatted
            docOut=IncludeFormattedSection(ArticleInfo[3], docOut);//Formatted conference title

            strOutPut=" (pp. "+ArticleInfo[6]+")";//pages
            strOutPut+=". "+ArticleInfo[11] +": "+ArticleInfo[10];//publisher add and publisher    
            docOut=IncludeNonFormattedSection(strOutPut, docOut);//These non-formatted                    
        }
        if(ArticleInfo[0].compareTo(strArticleTypes[4])==0){//Technical Report
            String strOutPut="";  
            if(ArticleInfo[2].compareTo("")!=0){//if not an edited book
                strOutPut=GetAuthorListFormatted(ArticleInfo[2]);                
            }else{
                if(ArticleInfo[12].compareTo("")!=0){//if editors are available
                    strOutPut=GetAuthorListFormatted(ArticleInfo[12])+"(Ed.) ";
                }else{//if not this is a company article, so publisher as the author
                    strOutPut=ArticleInfo[10];
                }
            }            
            strOutPut+=" ("+ArticleInfo[4]+"). ";//Add the publication year 
            docOut=IncludeNonFormattedSection(strOutPut, docOut);//These non-formatted
            docOut=IncludeFormattedSection(ArticleInfo[3], docOut);//and the book title formatted
            strOutPut="";  
            if(ArticleInfo[8].compareTo("")!=0) {//If this has an edition
                strOutPut+=SetEdition(ArticleInfo[8]);
            }
            if(ArticleInfo[7].compareTo("")!=0) {//If this has a volume
                strOutPut+=": Vol. "+ArticleInfo[7]+".";
                if(ArticleInfo[9].compareTo("")!=0) {//If this is a book series
                    strOutPut+=" ";
                }
            }else{
                if(ArticleInfo[9].compareTo("")!=0) {//If this is a book series
                    strOutPut+=": Ser. ";
                }                
            }
            docOut=IncludeNonFormattedSection(strOutPut, docOut);//These non-formatted
            if(ArticleInfo[9].compareTo("")!=0) {//If this is a book series
                docOut=IncludeFormattedSection(ArticleInfo[9], docOut);//Formatted Series title
            }   
            strOutPut=". "+ArticleInfo[11] +": "+ArticleInfo[10];//publisher add and publisher  
            docOut=IncludeNonFormattedSection(strOutPut, docOut);//These non-formatted            
        }
        if(ArticleInfo[0].compareTo(strArticleTypes[5])==0){//Thesis
            String strOutPut="";  
            if(ArticleInfo[2].compareTo("")!=0){//if not an edited book
                strOutPut=GetAuthorListFormatted(ArticleInfo[2]);                
            }else{
                if(ArticleInfo[12].compareTo("")!=0){//if editors are available
                    strOutPut=GetAuthorListFormatted(ArticleInfo[12])+"(Ed.) ";
                }else{//if not this is a company article, so publisher as the author
                    strOutPut=ArticleInfo[10];
                }
            }            
            strOutPut+=" ("+ArticleInfo[4]+"). ";//Add the publication year 
            docOut=IncludeNonFormattedSection(strOutPut, docOut);//These non-formatted
            docOut=IncludeFormattedSection(ArticleInfo[3], docOut);//and the book title formatted
            docOut=IncludeNonFormattedSection(", ", docOut);//These non-formatted
            if(ArticleInfo[9].compareTo("")!=0) {//Thesis type
                docOut=IncludeFormattedSection(ArticleInfo[9], docOut);//Formatted type
            }   
            strOutPut=". "+ArticleInfo[11] +": "+ArticleInfo[10];//publisher add and publisher  
            docOut=IncludeNonFormattedSection(strOutPut, docOut);//These non-formatted            
        }        
        docOut=IncludeNonFormattedSection("\n\nKeywords:\n", docOut);
        docOut=IncludeFormattedSection(ArticleInfo[14], docOut);
        docOut=IncludeNonFormattedSection("\n\nAbstract:\n", docOut);
        docOut=IncludeFormattedSection(ArticleInfo[15], docOut);  
        return docOut;
    }

    private String SetEdition(String Edition){
        String output="";
        int edn=Integer.parseInt(Edition);
        output="(";
        switch(edn){
            case 1:
                output+=Edition+"st";
                break;
            case 2:
                output+=Edition+"nd";
                break;
            case 3:
                output+=Edition+"rd";
                break;
            default:
                output+=Edition+"th";
                break;                  
        }
        output+="ed.)";
        return output;
    }
    
    private String GetAuthorListFormatted(String Authors){
        String strAuthorOut="";
        String[] AuthorList=Authors.split(";");
        if(AuthorList.length>2){
            int iAuthors=AuthorList.length;
            strAuthorOut=AuthorList[0];            
            for(int i=1;i<iAuthors-1;i++){
                strAuthorOut=strAuthorOut+", "+AuthorList[i];
            }
            strAuthorOut=strAuthorOut+" and "+AuthorList[iAuthors-1];
        }else if(AuthorList.length>1){
            strAuthorOut=AuthorList[0]+" and "+AuthorList[1];
        }else{
            strAuthorOut=AuthorList[0];
        }
            
        return strAuthorOut;
    }      
    
    private String GetAuthorListBibTEXFormatted(String Authors){
        String strAuthorOut="";
        String[] AuthorList=Authors.split(";");
        if(AuthorList.length>2){
            int iAuthors=AuthorList.length;
            strAuthorOut=AuthorList[0];            
            for(int i=1;i<iAuthors-1;i++){
                strAuthorOut=strAuthorOut+" and "+AuthorList[i];
            }
            strAuthorOut=strAuthorOut+" and "+AuthorList[iAuthors-1];
        }else if(AuthorList.length>1){
            strAuthorOut=AuthorList[0]+" and "+AuthorList[1];
        }else{
            strAuthorOut=AuthorList[0];
        }
            
        return strAuthorOut;
    } 
    
   
    public String ArticleFormatInBibTEX(int ArticleSeqID, String[] ArticleInfo, String BibTexName){
        String strFormatted="";
        String[] strArticleTypes={ "Journal Article", "Book", "Book Chapter", "Conference", "Technical Report", "Thesis" };
        if(ArticleInfo[0].compareTo(strArticleTypes[0])==0){//Journal article
            strFormatted="@ARTICLE{"+BibTexName+Integer.toString(ArticleSeqID)+",\n"+
                    "AUTHOR={"+GetAuthorListBibTEXFormatted(ArticleInfo[2])+"},"+"\n"+
                    "TITLE={"+ArticleInfo[1]+"},"+"\n"+
                    "JOURNAL={"+ArticleInfo[3]+"},"+"\n"+
                    "YEAR={"+ArticleInfo[4]+"},"+"\n"+
                    "VOLUME={"+ArticleInfo[7]+"},"+"\n"+
                    "NUMBER={"+ArticleInfo[8]+"},"+"\n"+
                    "PAGES={"+ArticleInfo[6]+"},"+"\n"+
                    "MONTH={"+ArticleInfo[5]+"}"+"\n}\n";
        }
        if(ArticleInfo[0].compareTo(strArticleTypes[1])==0){//Book
            strFormatted="@BOOK{"+BibTexName+Integer.toString(ArticleSeqID)+",\n"+
                    "AUTHOR={"+GetAuthorListBibTEXFormatted(ArticleInfo[2])+"},"+"\n"+
                    "TITLE={"+ArticleInfo[3]+"},"+"\n"+
                    "PUBLISHER={"+ArticleInfo[10]+"},"+"\n"+
                    "ADDRESS={"+ArticleInfo[11]+"},"+"\n"+
                    "YEAR={"+ArticleInfo[4]+"},"+"\n"+
                    "VOLUME={"+ArticleInfo[7]+"},"+"\n"+
                    "SERIES={"+ArticleInfo[9]+"},"+"\n"+
                    "EDITION={"+ArticleInfo[8]+"},"+"\n"+
                    "MONTH={"+ArticleInfo[5]+"}"+"\n}\n";            
        }
        if(ArticleInfo[0].compareTo(strArticleTypes[2])==0){//Book Chapter
            strFormatted="@INCOLLECTION{"+BibTexName+Integer.toString(ArticleSeqID)+",\n"+
                    "AUTHOR={"+GetAuthorListBibTEXFormatted(ArticleInfo[2])+"},"+"\n"+
                    "CHAPTER={"+ArticleInfo[1]+"},"+"\n"+
                    "TITLE={"+ArticleInfo[3]+"},"+"\n"+
                    "EDITOR={"+GetAuthorListBibTEXFormatted(ArticleInfo[12])+"},"+"\n"+
                    "PAGES={"+ArticleInfo[6]+"},"+"\n"+
                    "PUBLISHER={"+ArticleInfo[10]+"},"+"\n"+
                    "ADDRESS={"+ArticleInfo[11]+"},"+"\n"+
                    "YEAR={"+ArticleInfo[4]+"},"+"\n"+
                    "VOLUME={"+ArticleInfo[7]+"},"+"\n"+
                    "SERIES={"+ArticleInfo[9]+"},"+"\n"+
                    "EDITION={"+ArticleInfo[8]+"},"+"\n"+
                    "MONTH={"+ArticleInfo[5]+"}"+"\n}\n";       
        }
        if(ArticleInfo[0].compareTo(strArticleTypes[3])==0){//Conference Article
            strFormatted="@INPROCEEDINGS{"+BibTexName+Integer.toString(ArticleSeqID)+",\n"+
                    "AUTHOR={"+GetAuthorListBibTEXFormatted(ArticleInfo[2])+"},"+"\n"+
                    "TITLE={"+ArticleInfo[1]+"},"+"\n"+
                    "BOOKTITLE={"+ArticleInfo[3]+"},"+"\n"+
                    "YEAR={"+ArticleInfo[4]+"},"+"\n"+
                    "EDITOR={"+GetAuthorListBibTEXFormatted(ArticleInfo[12])+"},"+"\n"+
                    "SERIES={"+ArticleInfo[9]+"},"+"\n"+
                    "PAGES={"+ArticleInfo[6]+"},"+"\n"+
                    "PUBLISHER={"+ArticleInfo[10]+"},"+"\n"+  
                    "ADDRESS={"+ArticleInfo[11]+"},"+"\n"+
                    "MONTH={"+ArticleInfo[5]+"}"+"\n}\n";                             
        }
        if(ArticleInfo[0].compareTo(strArticleTypes[4])==0){//Technical Report
            strFormatted="@TECHREPORT{"+BibTexName+Integer.toString(ArticleSeqID)+",\n"+
                    "AUTHOR={"+GetAuthorListBibTEXFormatted(ArticleInfo[2])+"},"+"\n"+
                    "TITLE={"+ArticleInfo[1]+"},"+"\n"+
                    "INSTITUTION={"+ArticleInfo[10]+"},"+"\n"+
                    "YEAR={"+ArticleInfo[4]+"},"+"\n"+
                    "ADDRESS={"+ArticleInfo[11]+"},"+"\n"+
                    "NUMBER={"+ArticleInfo[9]+"},"+"\n"+
                    "MONTH={"+ArticleInfo[5]+"}"+"\n}\n";               
        }
        if(ArticleInfo[0].compareTo(strArticleTypes[5])==0){//Thesis
            strFormatted="@PHDTHESIS{"+BibTexName+Integer.toString(ArticleSeqID)+",\n"+
                    "AUTHOR={"+GetAuthorListBibTEXFormatted(ArticleInfo[2])+"},"+"\n"+
                    "TITLE={"+ArticleInfo[1]+"},"+"\n"+
                    "SCHOOL={"+ArticleInfo[10]+"},"+"\n"+
                    "YEAR={"+ArticleInfo[4]+"},"+"\n"+
                    "ADDRESS={"+ArticleInfo[11]+"},"+"\n"+
                    "MONTH={"+ArticleInfo[5]+"}"+"\n}\n";            
        }             
        return strFormatted;
    }
    
    private String GetAuthorListRISFormatted(String Authors, String AuType){
        String strAuthorOut=AuType +"  - ";
        String[] AuthorList=Authors.split(";");
        strAuthorOut+=AuthorList[0]+"\n";
        if(AuthorList.length>1){
            int iAuthors=AuthorList.length;                 
            for(int i=1;i<iAuthors;i++){
                strAuthorOut+=strAuthorOut+AuType+"  - "+AuthorList[i]+"\n";
            }            
        }            
        return strAuthorOut;
    } 
    
    private String GetPYRISformatted(String YoP, String MoP){
        String PYOut="PY  - "+YoP+"/"+MoP+"//\n";        
        return PYOut;
    }
    
    private String GetPagesRISformatted(String pp){
        String PPout="SP  - ";
        String[] ppp=pp.split("-");        
        if(ppp.length>1){
            PPout+=ppp[0]+"\nEP  - "+ppp[1]+"\n";
        }else{
            PPout+=ppp[0]+"\nEP  - "+ppp[0]+"\n";
        }
        return PPout;
    }
    
    public String ArticleFormatInRIS(int ArticleSeqID, String[] ArticleInfo, String RISName){
        String strFormatted="";
        String[] strArticleTypes={ "Journal Article", "Book", "Book Chapter", "Conference", "Technical Report", "Thesis" };
        if(ArticleInfo[0].compareTo(strArticleTypes[0])==0){//Journal article
            strFormatted="TY  - JOUR\n"+
                    GetAuthorListRISFormatted(ArticleInfo[2],"A1")+
                    "TI  - "+ArticleInfo[1]+"\n"+
                    "JO  - "+ArticleInfo[3]+"\n"+
                    GetPYRISformatted(ArticleInfo[4],ArticleInfo[5])+
                    "VL  - "+ArticleInfo[7]+"\n"+
                    "IS  - "+ArticleInfo[8]+"\n"+
                    GetPagesRISformatted(ArticleInfo[6])+
                    "ER  - \n\n";
        }
        if(ArticleInfo[0].compareTo(strArticleTypes[1])==0){//Book
            strFormatted="TY  - BOOK\n"+
                    GetAuthorListRISFormatted(ArticleInfo[2],"A1")+
                    "BT  - "+ArticleInfo[3]+"\n"+
                    "PB  - "+ArticleInfo[10]+"\n"+
                    GetPYRISformatted(ArticleInfo[4],ArticleInfo[5])+
                    "VL  - "+ArticleInfo[7]+"\n"+
                    "T3  - "+ArticleInfo[9]+"\n"+
                    "IS  - "+ArticleInfo[8]+"\n"+
                    "ER  - \n\n";          
        }
        if(ArticleInfo[0].compareTo(strArticleTypes[2])==0){//Book Chapter
            strFormatted="TY  - CHAP\n"+
                    GetAuthorListRISFormatted(ArticleInfo[2],"A1")+
                    GetAuthorListRISFormatted(ArticleInfo[12],"A3")+
                    "T1  - "+ArticleInfo[1]+"\n"+
                    "T2  - "+ArticleInfo[3]+"\n"+
                    "PB  - "+ArticleInfo[10]+"\n"+
                    GetPagesRISformatted(ArticleInfo[6])+
                    GetPYRISformatted(ArticleInfo[4],ArticleInfo[5])+
                    "VL  - "+ArticleInfo[7]+"\n"+
                    "T3  - "+ArticleInfo[9]+"\n"+
                    "IS  - "+ArticleInfo[8]+"\n"+
                    "ER  - \n\n";                        
        }
        if(ArticleInfo[0].compareTo(strArticleTypes[3])==0){//Conference Article
            strFormatted="TY  - CONF\n"+
                    GetAuthorListRISFormatted(ArticleInfo[2],"A1")+
                    "TI  - "+ArticleInfo[1]+"\n"+
                    "JO  - "+ArticleInfo[3]+"\n"+
                    "T3  - "+ArticleInfo[9]+"\n"+
                    "PB  - "+ArticleInfo[10]+"\n"+
                    GetAuthorListRISFormatted(ArticleInfo[12],"A3")+
                    GetPYRISformatted(ArticleInfo[4],ArticleInfo[5])+
                    GetPagesRISformatted(ArticleInfo[6])+
                    "ER  - \n\n";                                           
        }
        if(ArticleInfo[0].compareTo(strArticleTypes[4])==0){//Technical Report
            strFormatted="TY  - RPRT\n"+
                    GetAuthorListRISFormatted(ArticleInfo[2],"A1")+
                    "TI  - "+ArticleInfo[1]+"\n"+
                    "JO  - "+ArticleInfo[3]+"\n"+
                    "T3  - "+ArticleInfo[9]+"\n"+
                    "PB  - "+ArticleInfo[10]+"\n"+
                    GetAuthorListRISFormatted(ArticleInfo[12],"A3")+
                    GetPYRISformatted(ArticleInfo[4],ArticleInfo[5])+
                    GetPagesRISformatted(ArticleInfo[6])+
                    "ER  - \n\n";              
        }
        if(ArticleInfo[0].compareTo(strArticleTypes[5])==0){//Thesis
            strFormatted="TY  - THES\n"+
                    GetAuthorListRISFormatted(ArticleInfo[2],"A1")+
                    "BT  - "+ArticleInfo[3]+"\n"+
                    "PB  - "+ArticleInfo[10]+"\n"+
                    GetPYRISformatted(ArticleInfo[4],ArticleInfo[5])+
                    "VL  - "+ArticleInfo[7]+"\n"+
                    "T3  - "+ArticleInfo[9]+"\n"+
                    "IS  - "+ArticleInfo[8]+"\n"+
                    "ER  - \n\n";              
        }             
        return strFormatted;
    }

    public String ArticleFormatInPlainText(int ArticleSeqID, String[] ArticleInfo){
        String strOutPut= Integer.toString(ArticleSeqID)+". ";
        strOutPut+=GetTheFormattedReference(ArticleInfo);      
        return strOutPut;
    }
        
}
