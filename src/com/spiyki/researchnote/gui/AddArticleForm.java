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
import javax.swing.filechooser.FileFilter;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;
/**
 *
 * @author  samitha_2
 */
public class AddArticleForm extends JInternalFrame {
    Boolean bEditMode;
    int CurrentAID=0;
    MainFunctions thisFunctions;
    ViewArticleForm thisArticleViewer;
    String[] strArticleTypes={ "Journal Article", "Book", "Book Chapter", "Conference", "Technical Report", "Thesis" };
    /** Creates new form AddArticleForm */
    
    AbstractDocument docAbs;
    AbstractDocument docTitle;
    AbstractDocument docSerTitle;
    AbstractDocument docJournal;
    AbstractDocument docKeywords;
    
    Action italic_action = new StyledEditorKit.ItalicAction();
    
    public AddArticleForm(MainFunctions envMainFunctions, ViewArticleForm ArticleViewer) {
        initComponents();
        thisFunctions=envMainFunctions;        
        thisArticleViewer=ArticleViewer;
        this.setLocation(160, 0);
        InitThisGUI();
    }
    
    private void InitThisGUI(){        
        cmbArticleType.setModel(new DefaultComboBoxModel(strArticleTypes));
        bEditMode=false;
        InitTextPaneAbstract();
        InitTextPaneJournal();
        InitTextPaneKeywords();
        InitTextPaneSeriesTitle();
        InitTextPaneTitle();        
        InitFormTexts();
    }

    private void InitTextPaneAbstract(){
        textPaneAbs.setEditorKit(new HTMLEditorKit());
        textPaneAbs.setCaretPosition(0);
        StyledDocument styledDoc = textPaneAbs.getStyledDocument();
        if (styledDoc instanceof AbstractDocument) {
            docAbs = (AbstractDocument)styledDoc;
            docAbs.setDocumentFilter(new DocumentSizeFilter(300));
        } else {
            System.err.println("Text pane's document isn't an AbstractDocument!");
            System.exit(-1);
        }
        textPaneAbs.setForeground(Color.BLACK);
        textPaneAbs.setFont(new Font("Arial", Font.PLAIN, 11));
        InputMap inputMap = textPaneAbs.getInputMap();      
        KeyStroke key1 = KeyStroke.getKeyStroke(KeyEvent.VK_I,java.awt.event.InputEvent.CTRL_MASK);
        inputMap.put(key1, italic_action);    
        textPaneAbs.setPreferredSize(new Dimension(20,10));
    }

    private void InitTextPaneTitle(){
        textPaneTitle.setEditorKit(new HTMLEditorKit());
        textPaneTitle.setCaretPosition(0);
        StyledDocument styledDoc = textPaneTitle.getStyledDocument();
        if (styledDoc instanceof AbstractDocument) {
            docTitle = (AbstractDocument)styledDoc;
            docTitle.setDocumentFilter(new DocumentSizeFilter(300));
        } else {
            System.err.println("Text pane's document isn't an AbstractDocument!");
            System.exit(-1);
        }
        textPaneTitle.setForeground(Color.BLACK);
        textPaneTitle.setFont(new Font("Arial", Font.PLAIN, 11));
        InputMap inputMap = textPaneTitle.getInputMap();      
        KeyStroke key1 = KeyStroke.getKeyStroke(KeyEvent.VK_I,java.awt.event.InputEvent.CTRL_MASK);
        inputMap.put(key1, italic_action);           
    }

    private void InitTextPaneJournal(){
        textPaneJournal.setEditorKit(new HTMLEditorKit());
        textPaneJournal.setCaretPosition(0);
        StyledDocument styledDoc = textPaneJournal.getStyledDocument();
        if (styledDoc instanceof AbstractDocument) {
            docJournal = (AbstractDocument)styledDoc;
            docJournal.setDocumentFilter(new DocumentSizeFilter(300));
        } else {
            System.err.println("Text pane's document isn't an AbstractDocument!");
            System.exit(-1);
        }
        textPaneJournal.setForeground(Color.BLACK);
        textPaneJournal.setFont(new Font("Arial", Font.PLAIN, 11));
        InputMap inputMap = textPaneJournal.getInputMap();      
        KeyStroke key1 = KeyStroke.getKeyStroke(KeyEvent.VK_I,java.awt.event.InputEvent.CTRL_MASK);
        inputMap.put(key1, italic_action);           
    }    

    private void InitTextPaneSeriesTitle(){
        textPaneSerTitle.setEditorKit(new HTMLEditorKit());
        textPaneSerTitle.setCaretPosition(0);
        StyledDocument styledDoc = textPaneSerTitle.getStyledDocument();
        if (styledDoc instanceof AbstractDocument) {
            docSerTitle = (AbstractDocument)styledDoc;
            docSerTitle.setDocumentFilter(new DocumentSizeFilter(300));
        } else {
            System.err.println("Text pane's document isn't an AbstractDocument!");
            System.exit(-1);
        }
        textPaneSerTitle.setForeground(Color.BLACK);
        textPaneSerTitle.setFont(new Font("Arial", Font.PLAIN, 11));
        InputMap inputMap = textPaneSerTitle.getInputMap();      
        KeyStroke key1 = KeyStroke.getKeyStroke(KeyEvent.VK_I,java.awt.event.InputEvent.CTRL_MASK);
        inputMap.put(key1, italic_action);           
    } 
    
    private void InitTextPaneKeywords(){
        textPaneKeywords.setEditorKit(new HTMLEditorKit());
        textPaneKeywords.setCaretPosition(0);
        StyledDocument styledDoc = textPaneKeywords.getStyledDocument();
        if (styledDoc instanceof AbstractDocument) {
            docKeywords = (AbstractDocument)styledDoc;
            docKeywords.setDocumentFilter(new DocumentSizeFilter(300));
        } else {
            System.err.println("Text pane's document isn't an AbstractDocument!");
            System.exit(-1);
        }
        textPaneKeywords.setForeground(Color.BLACK);
        textPaneKeywords.setFont(new Font("Arial", Font.PLAIN, 11));
        InputMap inputMap = textPaneKeywords.getInputMap();      
        KeyStroke key1 = KeyStroke.getKeyStroke(KeyEvent.VK_I,java.awt.event.InputEvent.CTRL_MASK);
        inputMap.put(key1, italic_action);           
    } 
    
    private void InitFormTexts(){
        lblTitle.setText("Title");
        textPaneTitle.setEnabled(true);
        lblJournal.setText("Journal");
        lblIssue.setText("Number/Issue");
        lblSeries.setText("Series title");
        switch(cmbArticleType.getSelectedIndex()){         
            case 0:
                break;
            case 1:
                lblTitle.setText(" ");
                textPaneTitle.setEnabled(false);
                lblJournal.setText("Book Title");
                lblIssue.setText("Edition");
                break;
            case 2:
                lblJournal.setText("Book Title");
                lblIssue.setText("Edition");               
                break;
            case 3:
                lblJournal.setText("Conference");
                break;
            case 4:
                lblJournal.setText("Report Title");
                break;
            case 5:
                textPaneTitle.setEnabled(false);
                lblTitle.setText(" ");
                lblJournal.setText("Thesis Title");   
                lblSeries.setText("Thesis Type");
                break;
            case 6:
                
                break;
            case 7:
                
                break;                
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

        jLabel1 = new JLabel();
        cmbArticleType = new JComboBox();
        lblAuthors = new JLabel();
        txtAuthors = new JTextField();
        lblTitle = new JLabel();
        lblJournal = new JLabel();
        cmdAdd = new JButton();
        cmdClose = new JButton();
        jLabel5 = new JLabel();
        txtYear = new JTextField();
        jLabel6 = new JLabel();
        txtVol = new JTextField();
        lblIssue = new JLabel();
        txtIss = new JTextField();
        jLabel8 = new JLabel();
        txtPub = new JTextField();
        lblSeries = new JLabel();
        jLabel10 = new JLabel();
        txtPubAddr = new JTextField();
        jLabel11 = new JLabel();
        jLabel12 = new JLabel();
        jLabel13 = new JLabel();
        txtPP = new JTextField();
        cmdHelp = new JButton();
        jLabel14 = new JLabel();
        txtMonth = new JTextField();
        chkAddToCurrentProject = new JCheckBox();
        jLabel17 = new JLabel();
        txtEds = new JTextField();
        txtDOI = new JTextField();
        jLabel15 = new JLabel();
        jLabel19 = new JLabel();
        txtPDFLocation = new JTextField();
        cmdBrowse = new JButton();
        jScrollPane1 = new JScrollPane();
        textPaneAbs = new JTextPane();
        jScrollPane2 = new JScrollPane();
        textPaneTitle = new JTextPane();
        jScrollPane3 = new JScrollPane();
        textPaneJournal = new JTextPane();
        jScrollPane4 = new JScrollPane();
        textPaneSerTitle = new JTextPane();
        jScrollPane5 = new JScrollPane();
        textPaneKeywords = new JTextPane();
        jLabel3 = new JLabel();

        setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        setTitle("Add Article");
        setPreferredSize(new Dimension(760, 660));
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Article Type");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 60, -1, -1));

        cmbArticleType.setModel(new DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbArticleType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbArticleTypeActionPerformed(evt);
            }
        });
        getContentPane().add(cmbArticleType, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 60, 170, -1));

        lblAuthors.setText("Author(s)");
        getContentPane().add(lblAuthors, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));
        getContentPane().add(txtAuthors, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 600, 60));

        lblTitle.setText("Title");
        getContentPane().add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));

        lblJournal.setText("Journal/Book/Conference");
        getContentPane().add(lblJournal, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, -1));

        cmdAdd.setIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "ArticleAddOKBut_N.jpg"))); // NOI18N
        cmdAdd.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cmdAdd.setRolloverIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "ArticleAddOKBut_P.jpg"))); // NOI18N
        cmdAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddActionPerformed(evt);
            }
        });
        getContentPane().add(cmdAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 80, 80));

        cmdClose.setIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "CloseWindow.jpg"))); // NOI18N
        cmdClose.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cmdClose.setRolloverIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "CloseWindow_R.jpg"))); // NOI18N
        cmdClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCloseActionPerformed(evt);
            }
        });
        getContentPane().add(cmdClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 10, 80, 80));

        jLabel5.setText("Year");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, -1, -1));
        getContentPane().add(txtYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 280, 70, -1));

        jLabel6.setText("Volume");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, -1, -1));
        getContentPane().add(txtVol, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 310, 70, -1));

        lblIssue.setText("Issue");
        getContentPane().add(lblIssue, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 310, -1, -1));
        getContentPane().add(txtIss, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 310, 70, -1));

        jLabel8.setText("Publisher/ Organization/");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, -1, -1));
        getContentPane().add(txtPub, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 380, 600, 20));

        lblSeries.setText("Series title");
        getContentPane().add(lblSeries, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, -1, -1));

        jLabel10.setText("Editors for edited books");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, -1, -1));

        txtPubAddr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPubAddrActionPerformed(evt);
            }
        });
        getContentPane().add(txtPubAddr, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 410, 600, -1));

        jLabel11.setText("Keywords");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, -1, -1));

        jLabel12.setText("Abstract");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 540, -1, -1));

        jLabel13.setText("Pages");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 280, -1, -1));
        getContentPane().add(txtPP, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 280, 80, -1));

        cmdHelp.setText("Help");
        cmdHelp.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        getContentPane().add(cmdHelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 60, 80, 30));

        jLabel14.setText("Month");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 280, -1, -1));
        getContentPane().add(txtMonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 280, 70, -1));

        chkAddToCurrentProject.setText("Include into the current project");
        chkAddToCurrentProject.setOpaque(false);
        getContentPane().add(chkAddToCurrentProject, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, -1, -1));

        jLabel17.setText("Publisher address");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, -1, -1));
        getContentPane().add(txtEds, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 440, 600, -1));
        getContentPane().add(txtDOI, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 470, 600, -1));

        jLabel15.setText("DOI");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, -1, -1));

        jLabel19.setText("PDF File Location");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, -1, -1));
        getContentPane().add(txtPDFLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, 460, -1));

        cmdBrowse.setIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "PDFPathBut_N.jpg"))); // NOI18N
        cmdBrowse.setToolTipText("Browse");
        cmdBrowse.setBorder(null);
        cmdBrowse.setRolloverIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "PDFPathBut_P.jpg"))); // NOI18N
        cmdBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBrowseActionPerformed(evt);
            }
        });
        getContentPane().add(cmdBrowse, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 10, 80, 40));

        jScrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setViewportView(textPaneAbs);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 540, 600, 80));

        jScrollPane2.setViewportView(textPaneTitle);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 600, 50));

        jScrollPane3.setViewportView(textPaneJournal);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, 600, 30));

        jScrollPane4.setViewportView(textPaneSerTitle);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 340, 600, 30));

        jScrollPane5.setViewportView(textPaneKeywords);

        getContentPane().add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 500, 600, 30));

        jLabel3.setIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "ArticleAddEdit.jpg"))); // NOI18N
        jLabel3.setOpaque(true);
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 760, 660));

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void cmdAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAddActionPerformed
    if(!bEditMode){//If new article
        LicenceMgmt thisLicTest=new LicenceMgmt();
        if(!thisLicTest.LICENSED){
            if(thisFunctions.thisUserDatabase.IfArticleCountExeedsNonLicensedLimit(thisLicTest)){
                JOptionPane optionPane = new JOptionPane();
                optionPane.showMessageDialog(null, "Limit for number of articles reached.\nPlease purchase the full version to remove this limit.", "DEMO version limit", JOptionPane.YES_OPTION);
            }else{
                AddArticle(false);
            }
        }else{
            AddArticle(false);
        }
    }else{//If editing existing article
        AddArticle(true);
    }
    this.setVisible(false);
}//GEN-LAST:event_cmdAddActionPerformed

private void AddArticle(Boolean isUpdate){
    String[] ArticleInfo=new String[18];
    ArticleInfo[0]=strArticleTypes[cmbArticleType.getSelectedIndex()];
    SaveInHTMLformat saveTitle=new SaveInHTMLformat(textPaneTitle, docTitle);
    ArticleInfo[1]=saveTitle.ReadTheString();
    ArticleInfo[2]=txtAuthors.getText();
    SaveInHTMLformat saveJournal=new SaveInHTMLformat(textPaneJournal, docJournal);
    ArticleInfo[3]=saveJournal.ReadTheString();
    ArticleInfo[4]=txtYear.getText();
    ArticleInfo[5]=txtMonth.getText();
    ArticleInfo[6]=txtPP.getText();    
    ArticleInfo[7]=txtVol.getText();
    ArticleInfo[8]=txtIss.getText();
//    ArticleInfo[8]=txtEdition.getText();
    SaveInHTMLformat saveSerTitle=new SaveInHTMLformat(textPaneSerTitle, docSerTitle);
    ArticleInfo[9]=saveSerTitle.ReadTheString();    
    ArticleInfo[10]=txtPub.getText();
    ArticleInfo[11]=txtPubAddr.getText();
    ArticleInfo[12]=txtEds.getText();
    ArticleInfo[13]=txtDOI.getText();
    SaveInHTMLformat saveKeywords=new SaveInHTMLformat(textPaneKeywords, docKeywords);
    ArticleInfo[14]=saveKeywords.ReadTheString();    
    SaveInHTMLformat saveAbs=new SaveInHTMLformat(textPaneAbs, docAbs);
    ArticleInfo[15]=saveAbs.ReadTheString();    
    ArticleInfo[16]="";
    ArticleInfo[17]=txtPDFLocation.getText();
    
    if(!isUpdate){//If new article       
        thisFunctions.thisUserDatabase.AddArticle(ArticleInfo[0],ArticleInfo[1],ArticleInfo[2],ArticleInfo[3],ArticleInfo[4]
                                                 ,ArticleInfo[5],ArticleInfo[6],ArticleInfo[7],ArticleInfo[8],ArticleInfo[9]
                                                 ,ArticleInfo[10],ArticleInfo[11],ArticleInfo[12],ArticleInfo[13],ArticleInfo[14]
                                                 ,ArticleInfo[15],ArticleInfo[16],ArticleInfo[17],chkAddToCurrentProject.isSelected());
        
                                                 
    }else{//If editing existing article
        thisFunctions.thisUserDatabase.UpdateArticleRecord(CurrentAID,ArticleInfo[0],ArticleInfo[1],ArticleInfo[2],ArticleInfo[3],ArticleInfo[4]
                                                 ,ArticleInfo[5],ArticleInfo[6],ArticleInfo[7],ArticleInfo[8],ArticleInfo[9]
                                                 ,ArticleInfo[10],ArticleInfo[11],ArticleInfo[12],ArticleInfo[13],ArticleInfo[14]
                                                 ,ArticleInfo[15],ArticleInfo[16],ArticleInfo[17],chkAddToCurrentProject.isSelected());
        thisArticleViewer.RefreshProjectList();
    }    
}


private void cmdCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCloseActionPerformed
// TODO add your handling code here:
    if(thisArticleViewer!=null){
        thisArticleViewer.CloseEditArticleWindow();
    }else{
        try {
            this.setClosed(true);
            this.setVisible(false);
        } catch (java.beans.PropertyVetoException e) {}  
    }
}//GEN-LAST:event_cmdCloseActionPerformed

private void txtPubAddrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPubAddrActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_txtPubAddrActionPerformed

private void cmdBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBrowseActionPerformed
// TODO add your handling code here:
    JFileChooser thisChooser=new JFileChooser(".");    
    FileFilter filter1 = new ExtensionFileFilter("PDF Files", new String[] { "PDF" });
    thisChooser.setFileFilter(filter1);
    thisChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    int chooserRes=thisChooser.showOpenDialog(this);
    if(chooserRes==JFileChooser.APPROVE_OPTION){
        File thisFile=thisChooser.getSelectedFile();
        txtPDFLocation.setText(thisFile.getAbsolutePath());
    }
}//GEN-LAST:event_cmdBrowseActionPerformed

private void cmbArticleTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbArticleTypeActionPerformed
// TODO add your handling code here:
    InitFormTexts();
}//GEN-LAST:event_cmbArticleTypeActionPerformed

public void PopulateArticleDetails(int AID, String[] strArticleInfo, Boolean isInCurrentProject){
    CurrentAID=AID;
    cmbArticleType.setSelectedItem((String)strArticleInfo[0]);    
    JTextPane dff=new JTextPane();
    DisplayInHTMLformat disp=new DisplayInHTMLformat(strArticleInfo[1], "test", dff);   
    Document dffDoc=dff.getDocument();
    Document fff= textPaneTitle.getDocument();
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
                fff.insertString(k, strTemp, attThis);                    
            }else{
                fff.insertString(k, strTemp, null);  
            }
        } catch (BadLocationException ble) {
            ble.printStackTrace();
        }
    }
   
    disp=new DisplayInHTMLformat(strArticleInfo[3], "test", dff);    
    dffDoc=dff.getDocument();
    fff= textPaneJournal.getDocument();
    LineBreakCheck=false;
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
                fff.insertString(k, strTemp, attThis);                    
            }else{
                fff.insertString(k, strTemp, null);  
            }
        } catch (BadLocationException ble) {
            ble.printStackTrace();
        }
    }

    disp=new DisplayInHTMLformat(strArticleInfo[9], "test", dff);    
    dffDoc=dff.getDocument();
    fff= textPaneSerTitle.getDocument();
    LineBreakCheck=false;
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
                fff.insertString(k, strTemp, attThis);                    
            }else{
                fff.insertString(k, strTemp, null);  
            }
        } catch (BadLocationException ble) {
            ble.printStackTrace();
        }
    }
    
    disp=new DisplayInHTMLformat(strArticleInfo[14], "test", dff);    
    dffDoc=dff.getDocument();
    fff= textPaneKeywords.getDocument();
    LineBreakCheck=false;
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
                fff.insertString(k, strTemp, attThis);                    
            }else{
                fff.insertString(k, strTemp, null);  
            }
        } catch (BadLocationException ble) {
            ble.printStackTrace();
        }
    }
     
    disp=new DisplayInHTMLformat(strArticleInfo[15], "test", dff);    
    dffDoc=dff.getDocument();
    fff= textPaneAbs.getDocument();
    LineBreakCheck=false;
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
                fff.insertString(k, strTemp, attThis);                    
            }else{
                fff.insertString(k, strTemp, null);  
            }
        } catch (BadLocationException ble) {
            ble.printStackTrace();
        }
    }    

    
    txtAuthors.setText(strArticleInfo[2]);
    txtYear.setText(strArticleInfo[4]);
    txtMonth.setText(strArticleInfo[5]);
    txtVol.setText(strArticleInfo[7]);
    txtIss.setText(strArticleInfo[8]);
    txtEds.setText(strArticleInfo[12]);
    txtPP.setText(strArticleInfo[6]);
    txtPub.setText(strArticleInfo[10]);
    txtPubAddr.setText(strArticleInfo[11]);
    txtDOI.setText(strArticleInfo[13]);
    txtPDFLocation.setText(strArticleInfo[17]);
    chkAddToCurrentProject.setSelected(isInCurrentProject);
    bEditMode=true;
//    cmdAdd.setText("Save");
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JCheckBox chkAddToCurrentProject;
    private JComboBox cmbArticleType;
    private JButton cmdAdd;
    private JButton cmdBrowse;
    private JButton cmdClose;
    private JButton cmdHelp;
    private JLabel jLabel1;
    private JLabel jLabel10;
    private JLabel jLabel11;
    private JLabel jLabel12;
    private JLabel jLabel13;
    private JLabel jLabel14;
    private JLabel jLabel15;
    private JLabel jLabel17;
    private JLabel jLabel19;
    private JLabel jLabel3;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel8;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JScrollPane jScrollPane3;
    private JScrollPane jScrollPane4;
    private JScrollPane jScrollPane5;
    private JLabel lblAuthors;
    private JLabel lblIssue;
    private JLabel lblJournal;
    private JLabel lblSeries;
    private JLabel lblTitle;
    private JTextPane textPaneAbs;
    private JTextPane textPaneJournal;
    private JTextPane textPaneKeywords;
    private JTextPane textPaneSerTitle;
    private JTextPane textPaneTitle;
    private JTextField txtAuthors;
    private JTextField txtDOI;
    private JTextField txtEds;
    private JTextField txtIss;
    private JTextField txtMonth;
    private JTextField txtPDFLocation;
    private JTextField txtPP;
    private JTextField txtPub;
    private JTextField txtPubAddr;
    private JTextField txtVol;
    private JTextField txtYear;
    // End of variables declaration//GEN-END:variables

}
