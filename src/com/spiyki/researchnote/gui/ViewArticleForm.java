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
import java.util.ArrayList;
import java.awt.*;
import javax.swing.text.*;
import javax.swing.text.html.HTMLEditorKit;
import com.adobe.acrobat.*;
import java.io.*;
import javax.swing.JInternalFrame;
/**
 *
 * @author  samitha_2
 */
public class ViewArticleForm extends JInternalFrame {
    MainFunctions thisFunctions;
    public int ThisAID;
    int[] thisPIDs;
    ArrayList ArticleInformation=new ArrayList();
    AddArticleForm thisEditArticle= null;
    AddEditArticle thisEditArticle1= null;
    ViewNotesForm thisNoteListWindow=null;
    String PDFlocation="";
    JInternalFrame frame;
    /** Creates new form ViewArticleForm */
    public ViewArticleForm(MainFunctions envMainFunctions, int thisAID) {
        initComponents();
        thisFunctions=envMainFunctions;
        this.setLocation(160, 0);
        ThisAID=thisAID;        
        txtArticleDetailsPane.setEditorKit(new HTMLEditorKit());
        RefreshProjectList();
    }

    public void RefreshProjectList(){
        PopulateArticleDetails(thisFunctions.thisUserDatabase.GetArticleInformation(ThisAID));
        PopulateProjectList();
        thisFunctions.thisMainWindow.SearchArticlesWindow.actionSearchArticles();
    }
    
    private void PopulateProjectList(){
        ArrayList userProjects=thisFunctions.thisUserDatabase.GetProjectsUsingTheArticle(ThisAID);
        int iProjectCnt=userProjects.size();
        if(iProjectCnt>0){
            final String[] ProjectTitles=new String[iProjectCnt];
            thisPIDs=new int[iProjectCnt];
            for(int i=0;i<iProjectCnt;i++){
                ProjectTitles[i]=(String)((ArrayList)userProjects.get(i)).get(1);
                thisPIDs[i]=(Integer)((ArrayList)userProjects.get(i)).get(0);
            }
            lstProjects.setModel(new javax.swing.AbstractListModel() { 
                String[] strings = ProjectTitles;
                public int getSize() { return strings.length; }
                public Object getElementAt(int i) { return strings[i]; }
            });         
            lstProjects.setEnabled(true);            
        }else{
            lstProjects.setModel(new javax.swing.AbstractListModel() { 
                String[] strings = {"Not listed in any project"};
                public int getSize() { return strings.length; }
                public Object getElementAt(int i) { return strings[i]; }
            });         
            lstProjects.setEnabled(false);
        }       
    }    
    
    private void PopulateArticleDetails(ArrayList thisArticle){
        ArticleDetails thisDetails=new ArticleDetails();
        ArticleInformation=thisArticle;
        SimpleAttributeSet italic = new SimpleAttributeSet();
        StyleConstants.setItalic(italic, true);
        StyleConstants.setBold(italic, true);
        
        SimpleAttributeSet oBold = new SimpleAttributeSet();
        StyleConstants.setBold(oBold, true);    
        txtArticleDetailsPane.setText("");
        if(ArticleInformation.size()!=0){
            txtArticleDetailsPane.setFont(new Font("Serif", Font.PLAIN, 16)); 
            Document testDoc=thisDetails.GetTheFormattedReferenceWithStyle((String[])ArticleInformation.get(1));
            txtArticleDetailsPane.setDocument(testDoc);
            PDFlocation=((String[])ArticleInformation.get(1))[17];

        }else{
            txtArticleDetailsPane.setText("The current article does not contain any value");
        }
    }
    
   protected void append(String s, AttributeSet attributes) {
        Document d = txtArticleDetailsPane.getDocument();
        try {
          d.insertString(d.getLength(), s, attributes);
          
        } catch (BadLocationException ble) {
        }
  }    
    
   private void OpenEditArticleWindow(){
       if(thisEditArticle==null){
            thisEditArticle=new AddArticleForm(thisFunctions,this);
            thisEditArticle.setVisible(true);
            thisFunctions.thisMainWindow.myDeskTop.add(thisEditArticle);  
            try {
                thisEditArticle.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {}    
            thisFunctions.thisUserDatabase.PopulateEditArticleWindow(thisEditArticle, ThisAID);
       }else{
           if(!thisEditArticle.isVisible()){
                thisEditArticle=new AddArticleForm(thisFunctions,this);
                thisEditArticle.setVisible(true);
                thisFunctions.thisMainWindow.myDeskTop.add(thisEditArticle);  
                thisFunctions.thisUserDatabase.PopulateEditArticleWindow(thisEditArticle, ThisAID);               
           }
           try {
                thisEditArticle.setSelected(true);
           } catch (java.beans.PropertyVetoException e) {}  
       }
   }
   
    private void ViewPDF(String filename){
        frame = new JInternalFrame("PDF Viewer");
        frame.setLayout(new BorderLayout());
        try{
            Viewer viewer = new Viewer();
            frame.add(viewer, BorderLayout.CENTER);
            InputStream input =new FileInputStream (new File(filename));
            viewer.setDocumentInputStream(input);
            viewer.setProperty("Default_Page_Layout","SinglePage");
            viewer.setProperty("Default_Zoom_Type","FitPage");
            viewer.setProperty("Default_Magnification","100");
            viewer.activate();

            frame.setSize(400, 500);
            frame.pack(); 
            frame.setVisible(true);
            thisFunctions.thisMainWindow.myDeskTop.add(frame);
            frame.setLocation(160, 0);
            frame.setSize(thisFunctions.thisMainWindow.getWidth()-200, thisFunctions.thisMainWindow.getHeight());
            frame.setResizable(true);
            frame.setClosable(true);
            try {
                frame.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {}                
        }catch(Exception x){
            x.printStackTrace();
        }
    }

   
   public void CloseEditArticleWindow(){
       
       if(thisEditArticle!=null){           
           try {
            thisEditArticle.setClosed(true);
           } catch (java.beans.PropertyVetoException e) {}  
           thisEditArticle=null;           
       }
   }
   
   private void OpenNoteListWindow(){
       if(thisNoteListWindow==null){
            thisNoteListWindow=new ViewNotesForm(thisFunctions,ThisAID,false);
            thisNoteListWindow.setVisible(true);
            thisFunctions.thisMainWindow.myDeskTop.add(thisNoteListWindow);  
            try {
                thisNoteListWindow.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {}    
       }else{
           if(!thisNoteListWindow.isVisible()){
                thisNoteListWindow=new ViewNotesForm(thisFunctions,ThisAID,false);
                thisNoteListWindow.setVisible(true);
                thisFunctions.thisMainWindow.myDeskTop.add(thisNoteListWindow);                
           }
           try {
                thisNoteListWindow.setSelected(true);
           } catch (java.beans.PropertyVetoException e) {}  
       }       
   }
   
   public void CloseNoteListWindow(){
       if(thisNoteListWindow!=null){           
           try {
            thisNoteListWindow.setClosed(true);
           } catch (java.beans.PropertyVetoException e) {}  
           thisNoteListWindow=null;
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

        jScrollPane4 = new javax.swing.JScrollPane();
        txtArticleDetailsPane = new javax.swing.JTextPane();
        jPanel2 = new javax.swing.JPanel();
        cmdViewNotes = new javax.swing.JButton();
        cmdEditArticle = new javax.swing.JButton();
        cmdOpenPDF = new javax.swing.JButton();
        cmdClose = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstProjects = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtProjectDes = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new Color(255, 255, 255));
        setBorder(null);
        setTitle("Article Details");
        setPreferredSize(new Dimension(670, 500));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtArticleDetailsPane.setEditable(false);
        jScrollPane4.setViewportView(txtArticleDetailsPane);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 560, 220));

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cmdViewNotes.setIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "ArticleNotesBut_N.jpg"))); // NOI18N
        cmdViewNotes.setToolTipText("View notes");
        cmdViewNotes.setBorder(null);
        cmdViewNotes.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "ArticleNotesBut_P.jpg"))); // NOI18N
        cmdViewNotes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdViewNotesActionPerformed(evt);
            }
        });
        jPanel2.add(cmdViewNotes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 80, 80));

        cmdEditArticle.setIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "ArticleEditBut_N.jpg"))); // NOI18N
        cmdEditArticle.setToolTipText("Edit article details");
        cmdEditArticle.setBorder(null);
        cmdEditArticle.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "ArticleEditBut_P.jpg"))); // NOI18N
        cmdEditArticle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdEditArticleActionPerformed(evt);
            }
        });
        jPanel2.add(cmdEditArticle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 80, 80));

        cmdOpenPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "ArticlePDFBut_N.jpg"))); // NOI18N
        cmdOpenPDF.setToolTipText("Open pdf file");
        cmdOpenPDF.setBorder(null);
        cmdOpenPDF.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "ArticlePDFBut_P.jpg"))); // NOI18N
        cmdOpenPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdOpenPDFActionPerformed(evt);
            }
        });
        jPanel2.add(cmdOpenPDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 80, 80));

        cmdClose.setIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "CloseWindow.jpg"))); // NOI18N
        cmdClose.setToolTipText("Close");
        cmdClose.setBorder(null);
        cmdClose.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "CloseWindow_R.jpg"))); // NOI18N
        cmdClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCloseActionPerformed(evt);
            }
        });
        jPanel2.add(cmdClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 80, 80));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, 80, 440));

        lstProjects.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstProjects.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstProjectsValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lstProjects);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 170, 190));

        txtProjectDes.setEditable(false);
        jScrollPane3.setViewportView(txtProjectDes);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 270, 380, 190));

        jLabel1.setText("Related Projects");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, -1));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 560, 20));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "ArticleViewForm.jpg"))); // NOI18N
        jLabel3.setOpaque(true);
        jLabel3.setPreferredSize(new Dimension(725, 570));
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 670, 470));

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void lstProjectsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstProjectsValueChanged
// TODO add your handling code here:
    if(!lstProjects.isSelectionEmpty()){
        int iPID=thisPIDs[lstProjects.getSelectedIndex()];
        new DisplayInHTMLformat(thisFunctions.thisUserDatabase.GetProjectDescription(iPID)[1], "PLTmp", txtProjectDes);
    }
}//GEN-LAST:event_lstProjectsValueChanged

private void cmdEditArticleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEditArticleActionPerformed
// TODO add your handling code here:
    OpenEditArticleWindow();    
}//GEN-LAST:event_cmdEditArticleActionPerformed

private void cmdViewNotesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdViewNotesActionPerformed
// TODO add your handling code here:
    OpenNoteListWindow();
}//GEN-LAST:event_cmdViewNotesActionPerformed

private void cmdCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCloseActionPerformed
// TODO add your handling code here:
    thisFunctions.thisMainWindow.SearchArticlesWindow.actionCloseArticleWindow(ThisAID);
    try {
        this.setClosed(true);
        this.setVisible(false);
    } catch (java.beans.PropertyVetoException e) {}      
}//GEN-LAST:event_cmdCloseActionPerformed

private void cmdOpenPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdOpenPDFActionPerformed
// TODO add your handling code here:
    ViewPDF(PDFlocation);
}//GEN-LAST:event_cmdOpenPDFActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdClose;
    private javax.swing.JButton cmdEditArticle;
    private javax.swing.JButton cmdOpenPDF;
    private javax.swing.JButton cmdViewNotes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JList lstProjects;
    private javax.swing.JTextPane txtArticleDetailsPane;
    private javax.swing.JTextPane txtProjectDes;
    // End of variables declaration//GEN-END:variables

}
