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

package com.spiyki.researchnote.gui;

import com.spiyki.researchnote.utils.*;
import com.spiyki.researchnote.data.*;
import javax.swing.JFileChooser;
import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.JTextPane;
/**
 *
 * @author  samitha_2
 */
public class ExportDataForm extends javax.swing.JInternalFrame {
    MainFunctions thisFunctions;
    String[] strCiteTypes={"BibTEX", "RIS", "Plain Text"};
    String[] strNoteTypes={"HTML", "Rich Text"};
    /** Creates new form ExportDataForm */

    public ExportDataForm(MainFunctions envMainFunctions) {
        initComponents();
        thisFunctions=envMainFunctions;
        this.setLocation(160, 0);  
        cmbCites.setModel(new javax.swing.DefaultComboBoxModel(strCiteTypes));
        cmbNotes.setModel(new javax.swing.DefaultComboBoxModel(strNoteTypes));
    }

    private void actionExportCitations(){
        ArticleDetails adThis=new ArticleDetails();
        ArrayList arrArticles = thisFunctions.thisUserDatabase.GetArticlesForTheProjectInPlainText(thisFunctions.CurrentPID);
        int iArticles=arrArticles.size();
        String FileName=txtExportLocation.getText() + File.separator +txtExportName.getText();
        for(int i=0;i<iArticles;i++){
            switch(cmbCites.getSelectedIndex()){            
                case 0: //bibTEX
                    String[] thisArtDetailsBib=(String[])arrArticles.get(i);
                    String toFileBib=adThis.ArticleFormatInBibTEX(i+1, thisArtDetailsBib, txtExportName.getText());
                    if(i==0){
                        try{
                            FileAppend thisAppend=new FileAppend(FileName+".bib", toFileBib,false);
                        }catch(IOException ioe){

                        }                        
                    }else{
                        try{
                            FileAppend thisAppend=new FileAppend(FileName+".bib", toFileBib,true);
                        }catch(IOException ioe){

                        }
                    }
                    break;
                case 1: //RIS
                    String[] thisArtDetailsRIS=(String[])arrArticles.get(i);
                    String toFileRIS=adThis.ArticleFormatInRIS(i+1, thisArtDetailsRIS, txtExportName.getText());
                    if(i==0){
                        try{
                            FileAppend thisAppend=new FileAppend(FileName+".RIS", toFileRIS,false);
                        }catch(IOException ioe){

                        }                        
                    }else{                    
                        try{
                            FileAppend thisAppend=new FileAppend(FileName+".RIS", toFileRIS,true);
                        }catch(IOException ioe){

                        }
                    }
                    break;
                case 2: //Plain
                    String[] thisArtDetailsPT=(String[])arrArticles.get(i);
//                    String toFilePL=adThis.ArticleFormatInBibTEX(i+1, thisArtDetailsPT, txtExportName.getText());
                    String toFilePL=adThis.ArticleFormatInPlainText(i+1, thisArtDetailsPT);
                    if(i==0){
                        try{
                            FileAppend thisAppend=new FileAppend(FileName+".txt", toFilePL,false);
                        }catch(IOException ioe){

                        }                        
                    }else{                     
                        try{
                            FileAppend thisAppend=new FileAppend(FileName+".txt", toFilePL,true);
                        }catch(IOException ioe){

                        }
                    }
                    break;
                default:
                    break;
            }            
        }
    }
    
    private void actionExportNotes(){
        String FileName=txtExportLocation.getText() + File.separator +txtExportName.getText();
        ArrayList arrProjectNotes=thisFunctions.thisUserDatabase.GenerateNoteListForProjects(thisFunctions.CurrentPID);
        String[] thisProjDetails=thisFunctions.thisUserDatabase.GetProjectDescription(thisFunctions.CurrentPID);                    
        int iProjectNotes=arrProjectNotes.size();
        String strHTMLStart="<html><head> </head><body><h1>"+thisProjDetails[0]+
                            "</h1><b>Created : "+
                            thisProjDetails[2]+
                            " Modified : "+
                            thisProjDetails[3]+
                            "</b><br>" +
                            thisProjDetails[1]+
                            "<br>";
        String strHTMLEnd="</body></html>";        
        String strHTMLProjectNoteBody="";
        for(int i=0;i<iProjectNotes;i++){
            String strNoteTitle="<h2>"+(String)((ArrayList)arrProjectNotes.get(i)).get(1)+
                    "</h2><b>Created : "+
                    (String)((ArrayList)arrProjectNotes.get(i)).get(3)+
                    " Modified : "+
                    (String)((ArrayList)arrProjectNotes.get(i)).get(4)+
                    "</b><br>";
            String strNote=(String)((ArrayList)arrProjectNotes.get(i)).get(2);
            strHTMLProjectNoteBody+=strNoteTitle+strNote;
        }
        ArrayList arrArticleList=thisFunctions.thisUserDatabase.GetArticlesForTheProjectInPlainText(thisFunctions.CurrentPID);
        int iArticles=arrArticleList.size();
        String strArticleNoteBody="<h2>Article Notes</h2>";
        JTextPane dff=new JTextPane();                
        for(int i=0;i<iArticles;i++){   
            ArticleDetails thisDetails=new ArticleDetails();
            strArticleNoteBody+= "<br>Article #"+Integer.toString(i+1)+"<br>"+thisDetails.GetTheFormattedReference((String[])arrArticleList.get(i));            
            String[] iArtDeo=(String[])arrArticleList.get(i);
            int iArtNo= Integer.parseInt(iArtDeo[15]);
            ArrayList arrArticleNoteList=thisFunctions.thisUserDatabase.GenerateNoteListForArticles(iArtNo,true);
            int iArticleNotes=arrArticleNoteList.size();
            for(int j=0;j<iArticleNotes;j++){
                String strNoteTitle="<h2>"+(String)((ArrayList)arrArticleNoteList.get(j)).get(1)+
                    "</h2><b>Created : "+
                    (String)((ArrayList)arrArticleNoteList.get(j)).get(3)+
                    " Modified : "+
                    (String)((ArrayList)arrArticleNoteList.get(j)).get(4)+
                    "</b><br>";  
                String strNote=(String)((ArrayList)arrArticleNoteList.get(j)).get(2);
                strArticleNoteBody+=strNoteTitle+strNote;
            }
        }
        String toHTML=strHTMLStart+strHTMLProjectNoteBody+strArticleNoteBody+strHTMLEnd;
        try{
            FileAppend thisAppend=new FileAppend(FileName+".html", toHTML,false);
        }catch(IOException ioe){

        } 
    }
    
    private void actionExportNotesInPlainText(){
        JTextPane dff=new JTextPane();
        String FileName=txtExportLocation.getText() + File.separator +txtExportName.getText();
        ArrayList arrProjectNotes=thisFunctions.thisUserDatabase.GenerateNoteListForProjects(thisFunctions.CurrentPID);      
        String[] thisProjDetails=thisFunctions.thisUserDatabase.GetProjectDescription(thisFunctions.CurrentPID);                    
        int iProjectNotes=arrProjectNotes.size();
        DisplayInHTMLformat dispProj=new DisplayInHTMLformat(thisProjDetails[1], "test", dff);
        String strProjDetails=dispProj.GetThePlainText()+"\n"; 
        String strHTMLStart="\n***********************************\nStart of the record\n***********************************\n\n"+
                            "Project Title : "+thisProjDetails[0]+
                            "\nCreated : "+
                            thisProjDetails[2]+
                            " Modified : "+
                            thisProjDetails[3]+
                            "\nProject Description : \n" +                             
                            strProjDetails+
                            "\n------------------------\nNotes for the project\n------------------------\n";
        String strHTMLEnd="\n\n***********************************\nEnd of the record\n***********************************\n";        
        String strHTMLProjectNoteBody="";                       
        for(int i=0;i<iProjectNotes;i++){
            String strNoteTitle="\n"+(String)((ArrayList)arrProjectNotes.get(i)).get(1)+
                    "\nCreated : "+
                    (String)((ArrayList)arrProjectNotes.get(i)).get(3)+
                    " Modified : "+
                    (String)((ArrayList)arrProjectNotes.get(i)).get(4)+
                    "\n";
            DisplayInHTMLformat disp=new DisplayInHTMLformat((String)((ArrayList)arrProjectNotes.get(i)).get(2), "test", dff);
            String strNote=disp.GetThePlainText()+"\n";  
//            String strNote=(String)((ArrayList)arrProjectNotes.get(i)).get(2);
            strHTMLProjectNoteBody+=strNoteTitle+strNote;
        }
        ArrayList arrArticleList=thisFunctions.thisUserDatabase.GetArticlesForTheProjectInPlainText(thisFunctions.CurrentPID);
        int iArticles=arrArticleList.size();
        String strArticleNoteBody="\n------------------------\nArticle Notes\n------------------------\n";
        for(int i=0;i<iArticles;i++){   
            ArticleDetails thisDetails=new ArticleDetails();
            strArticleNoteBody+= "\n++++++++++++++++\nArticle #"+Integer.toString(i+1)+"\n"+thisDetails.GetTheFormattedReference((String[])arrArticleList.get(i));
            String[] iArtDeo=(String[])arrArticleList.get(i);
            int iArtNo= Integer.parseInt(iArtDeo[15]);
            ArrayList arrArticleNoteList=thisFunctions.thisUserDatabase.GenerateNoteListForArticles(iArtNo,true);
            int iArticleNotes=arrArticleNoteList.size();
            for(int j=0;j<iArticleNotes;j++){
                String strNoteTitle="\n"+(String)((ArrayList)arrArticleNoteList.get(j)).get(1)+
                    "\nCreated : "+
                    (String)((ArrayList)arrArticleNoteList.get(j)).get(3)+
                    " Modified : "+
                    (String)((ArrayList)arrArticleNoteList.get(j)).get(4)+
                    "\n";  
                DisplayInHTMLformat disp=new DisplayInHTMLformat((String)((ArrayList)arrArticleNoteList.get(j)).get(2), "test", dff);
                String strNote=disp.GetThePlainText()+"\n"; 
//                String strNote=(String)((ArrayList)arrArticleNoteList.get(j)).get(2);
                strArticleNoteBody+=strNoteTitle+strNote;
            }
        }
        String toHTML=strHTMLStart+strHTMLProjectNoteBody+strArticleNoteBody+strHTMLEnd;
        try{
            FileAppend thisAppend=new FileAppend(FileName+"_Notes.txt", toHTML,false);
        }catch(IOException ioe){

        } 
    }
      
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        txtExportName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cmdBrowse = new javax.swing.JButton();
        txtExportLocation = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cmdExportNotes = new javax.swing.JButton();
        cmbCites = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        lblCitationDes = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        cmbNotes = new javax.swing.JComboBox();
        cmdExportCitations = new javax.swing.JButton();
        cmdCancel = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        setTitle("Export Options");
        setPreferredSize(new java.awt.Dimension(480, 363));
        setVerifyInputWhenFocusTarget(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtExportName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtExportNameActionPerformed(evt);
            }
        });
        getContentPane().add(txtExportName, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, 240, -1));

        jLabel1.setText("Export Name");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        cmdBrowse.setIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "BrowseBut_N.jpg"))); // NOI18N
        cmdBrowse.setToolTipText("Browse");
        cmdBrowse.setBorder(null);
        cmdBrowse.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "BrowseBut_P.jpg"))); // NOI18N
        cmdBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBrowseActionPerformed(evt);
            }
        });
        getContentPane().add(cmdBrowse, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 70, 80, 40));
        getContentPane().add(txtExportLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 350, -1));

        jLabel2.setText("Set Export Location");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        cmdExportNotes.setIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "NoteExportBut_N.jpg"))); // NOI18N
        cmdExportNotes.setToolTipText("Export notes");
        cmdExportNotes.setBorder(null);
        cmdExportNotes.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "NoteExportBut_P.jpg"))); // NOI18N
        cmdExportNotes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdExportNotesActionPerformed(evt);
            }
        });
        getContentPane().add(cmdExportNotes, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 270, 80, 80));

        cmbCites.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(cmbCites, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, 160, -1));

        jLabel3.setText("Export Citations");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        lblCitationDes.setText("Citation description");
        getContentPane().add(lblCitationDes, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 350, 40));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 440, 10));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 440, 10));

        jLabel4.setText("Export Notes");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, -1, -1));

        cmbNotes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(cmbNotes, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 280, 160, -1));

        cmdExportCitations.setIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "ArticleExportBut_N.jpg"))); // NOI18N
        cmdExportCitations.setToolTipText("Export citations");
        cmdExportCitations.setBorder(null);
        cmdExportCitations.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "ArticleExportBut_P.jpg"))); // NOI18N
        cmdExportCitations.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdExportCitationsActionPerformed(evt);
            }
        });
        getContentPane().add(cmdExportCitations, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 170, 80, 80));

        cmdCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "CloseWindow_small.jpg"))); // NOI18N
        cmdCancel.setToolTipText("Exit");
        cmdCancel.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "CloseWindow_R_small.jpg"))); // NOI18N
        cmdCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCancelActionPerformed(evt);
            }
        });
        getContentPane().add(cmdCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, 40, 40));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "ExportForm_withExit.jpg"))); // NOI18N
        jLabel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jLabel5.setOpaque(true);
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 480, 370));

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void txtExportNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtExportNameActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_txtExportNameActionPerformed

private void cmdBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBrowseActionPerformed
// TODO add your handling code here:
    JFileChooser thisChooser=new JFileChooser();
    thisChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    int chooserRes=thisChooser.showOpenDialog(this);
    if(chooserRes==JFileChooser.APPROVE_OPTION){
        File thisFile=thisChooser.getSelectedFile();
        txtExportLocation.setText(thisFile.getAbsolutePath());
    }    
}//GEN-LAST:event_cmdBrowseActionPerformed

private void cmdExportCitationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdExportCitationsActionPerformed
// TODO add your handling code here:
    actionExportCitations();
}//GEN-LAST:event_cmdExportCitationsActionPerformed

private void cmdExportNotesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdExportNotesActionPerformed
// TODO add your handling code here:
    if(cmbNotes.getSelectedIndex()==0){
        actionExportNotes();
    }else{
        actionExportNotesInPlainText();
    }
}//GEN-LAST:event_cmdExportNotesActionPerformed

    private void cmdCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCancelActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_cmdCancelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbCites;
    private javax.swing.JComboBox cmbNotes;
    private javax.swing.JButton cmdBrowse;
    private javax.swing.JButton cmdCancel;
    private javax.swing.JButton cmdExportCitations;
    private javax.swing.JButton cmdExportNotes;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblCitationDes;
    private javax.swing.JTextField txtExportLocation;
    private javax.swing.JTextField txtExportName;
    // End of variables declaration//GEN-END:variables

}
