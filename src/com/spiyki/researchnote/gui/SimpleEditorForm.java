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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;
import java.util.ArrayList;
/**
 *
 * @author  samitha_2
 */
public class SimpleEditorForm extends JInternalFrame {
    String newline = "\n";
    AbstractDocument doc;
    static final int MAX_CHARACTERS = 300;
    MainFunctions thisFunctions;
    Boolean bIsProject;                     //If this is a project or a note
    int iEditor;                            //Number of the active editor
    int iNID;                               //ProjectID or NoteID
    String sEdtID;
    AddProjectForm thisProjectView=null;
    ViewNotesForm thisNotesWindow=null;

    /** Creates new form SimpleEditorForm */
    public SimpleEditorForm(MainFunctions envMainFunctions, int iEditorNumber, Boolean bProject, int iItemID, ArrayList frmContainer) { 
        //This class takes Editor number (this is used to assign the save file name and to keep
        //track of the editor window details on the side info bar.
        //bProject=true IF editing a project ELSE considered as a note
        //iItemID is the PID if project or NID if note.
        initComponents();
        thisFunctions=envMainFunctions;        
        bIsProject=bProject;
        iEditor=iEditorNumber;
        iNID=iItemID;
        statusPane.setVisible(false);
        InitMyTextPane(frmContainer);
        ResizeAndPosition();        
        cmdBold.setText("");
        cmdItalic.setText("");
        cmdUnderline.setText("");
        cmdFontBig.setText("");
        cmdFontMid.setText("");
        cmdFontNor.setText("");
        cmdCenter.setText("");
        cmdLeft.setText("");
        cmdRight.setText("");
        cmdFontBlack.setText("");
        cmdFontRed.setText("");
        cmdFontBlue.setText("");
        cmdJustify.setText("");
    }

    Action bold_action = new StyledEditorKit.BoldAction();
    Action italic_action = new StyledEditorKit.ItalicAction();
    Action underline_action = new StyledEditorKit.UnderlineAction();
    Action Font_big=new StyledEditorKit.FontSizeAction("Big", 16);
    Action Font_Mid=new StyledEditorKit.FontSizeAction("Mid", 14);
    Action Font_Nor=new StyledEditorKit.FontSizeAction("Nor", 12);
    Action Font_Black=new StyledEditorKit.ForegroundAction("Black", Color.BLACK);
    Action Font_Blue=new StyledEditorKit.ForegroundAction("Black", Color.BLUE);
    Action Font_Red=new StyledEditorKit.ForegroundAction("Black", Color.RED);
    Action Align_Left=new StyledEditorKit.AlignmentAction("Left", StyleConstants.ALIGN_LEFT);
    Action Align_Center=new StyledEditorKit.AlignmentAction("Center", StyleConstants.ALIGN_CENTER);
    Action Align_Right=new StyledEditorKit.AlignmentAction("Right", StyleConstants.ALIGN_RIGHT);
    Action Align_Justify=new StyledEditorKit.AlignmentAction("Justify", StyleConstants.ALIGN_JUSTIFIED);
    
    private void InitMyTextPane(ArrayList frmContainer){
      
        textPane.setEditorKit(new HTMLEditorKit());
        textPane.setCaretPosition(0);
        StyledDocument styledDoc = textPane.getStyledDocument();
        if (styledDoc instanceof AbstractDocument) {
            doc = (AbstractDocument)styledDoc;
            doc.setDocumentFilter(new DocumentSizeFilter(MAX_CHARACTERS));
        } else {
            System.err.println("Text pane's document isn't an AbstractDocument!");
            System.exit(-1);
        }
        textPane.setForeground(Color.BLACK);
        textPane.setFont(new Font("Arial", Font.PLAIN, 11));
        CaretListenerLabel caretListenerLabel =                new CaretListenerLabel("Caret Status");
        statusPane.add(caretListenerLabel);        
        textPane.addCaretListener(caretListenerLabel);
        doc.addDocumentListener(new MyDocumentListener());     
        
 
//        textPane.setDocument((JTextPane)frmContainer.get(1));
        JTextPane dff=(JTextPane)frmContainer.get(1);
        Document dffDoc=dff.getDocument();
        Document fff= textPane.getDocument();
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
//                System.out.print(strTemp);
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
//        try{
//            fff.remove(0, 1);
//        }catch(BadLocationException ex1){
//
//        }         
        if(bIsProject){
            String thisProjName=(String)((ArrayList)thisFunctions.thisUserDatabase.GetProjectsList().get(iNID-1)).get(1);
            String thisProjectDes=thisFunctions.thisUserDatabase.GetProjectDescription(iNID)[1];
            sEdtID="PE"+ String.valueOf(iNID);
            this.setTitle(thisProjName+" - Project Editor");
//            ConverHTMLsaveAndDisplay(thisProjectDes);
            thisProjectView=(AddProjectForm)frmContainer.get(0);
        }else{
            ArrayList thisNoteDetails=thisFunctions.thisUserDatabase.GetNoteDetils(iNID);
            String thisNoteTitle=(String)thisNoteDetails.get(1);
            String thisNote=(String)thisNoteDetails.get(2);
//            System.out.println(iNID);
            sEdtID="NE"+ Integer.toString(iNID);
            this.setTitle(thisNoteTitle+ " - Note Editor");
//            ConverHTMLsaveAndDisplay(thisNote);
            thisNotesWindow=(ViewNotesForm)frmContainer.get(0);
        }
    }
    

    private String ReadHTMLandStore(){
        BufferedReader inputStream = null;        
        String strHTML="";
        try {
            inputStream = new BufferedReader(new FileReader("test.html"));
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
    
    private void WriteHTMLandSaveInDataBase(){

        try{
            OutputStream os = new BufferedOutputStream(new FileOutputStream("test.html"));
//            System.out.println(textPane.getContentType());
            HTMLDocument docs = (HTMLDocument)textPane.getDocument();
            int length = docs.getLength();
            try {
              textPane.getEditorKit().write(os, doc, 0, length);
              os.close();
            } catch (BadLocationException ex) {
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }           
        String thisRecord=ReadHTMLandStore();
        if(bIsProject)//Save and update the project
        {
            String thisProjTitle=(String)((ArrayList)thisFunctions.thisUserDatabase.GetProjectsList().get(iNID-1)).get(1);
            thisFunctions.thisUserDatabase.UpdateProject(iNID, thisProjTitle, thisRecord);            
        }else{//Save and update the note
            ArrayList thisNoteDetails=thisFunctions.thisUserDatabase.GetNoteDetils(iNID);
            String thisNoteTitle=(String)thisNoteDetails.get(1);
            thisFunctions.thisUserDatabase.UpdateNote(iNID, thisNoteTitle, thisRecord);
        }
        
    }    
    
    private void ResizeAndPosition(){
//        int iWd,iHt;
        this.setLocation(160,0);
//        iWd=thisFunctions.thisMainWindow.getSize().width-160-thisFunctions.thisMainWindow.SideBarWindow.getSize().width;
//        iHt=thisFunctions.thisMainWindow.SideBarWindow.getSize().height;
//        this.setSize(iWd, iHt);
//        actionResizeEditorWindow();
    }
    
   
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmdBold = new JButton(bold_action);
        cmdItalic = new JButton(italic_action);
        cmdUnderline = new JButton(underline_action);
        cmdFontMid = new JButton(Font_Mid);
        cmdFontBig = new JButton(Font_big);
        cmdFontNor = new JButton(Font_Nor);
        cmdFontBlack = new JButton(Font_Black);
        cmdFontRed = new JButton(Font_Red);
        cmdFontBlue = new JButton(Font_Blue);
        cmdRight = new JButton(Align_Right);
        cmdLeft = new JButton(Align_Left);
        cmdCenter = new JButton(Align_Center);
        cmdJustify = new JButton(Align_Justify);
        cmdSave = new JButton();
        cmdExit = new JButton();
        jScrollPane1 = new JScrollPane();
        textPane = new JTextPane();
        statusPane = new JPanel();
        jLabel3 = new JLabel();

        setBorder(null);
        setPreferredSize(new Dimension(870, 560));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cmdBold.setIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "Bold_N.jpg"))); // NOI18N
        cmdBold.setToolTipText("Bold");
        cmdBold.setBorder(null);
        cmdBold.setRolloverIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "Bold_P.jpg"))); // NOI18N
        getContentPane().add(cmdBold, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 40, 40));

        cmdItalic.setFont(new Font("Tahoma", 2, 11));
        cmdItalic.setIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "Italic_N.jpg"))); // NOI18N
        cmdItalic.setToolTipText("Italic ");
        cmdItalic.setBorder(null);
        cmdItalic.setRolloverIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "Italic_P.jpg"))); // NOI18N
        getContentPane().add(cmdItalic, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 40, 40));

        cmdUnderline.setIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "UnderLine_N.jpg"))); // NOI18N
        cmdUnderline.setToolTipText("Underlined");
        cmdUnderline.setBorder(null);
        cmdUnderline.setRolloverIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "UnderLine_P.jpg"))); // NOI18N
        getContentPane().add(cmdUnderline, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 40, 40));

        cmdFontMid.setFont(new Font("Arial", 0, 14));
        cmdFontMid.setIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "FontMid_N.jpg"))); // NOI18N
        cmdFontMid.setToolTipText("Normal");
        cmdFontMid.setBorder(null);
        cmdFontMid.setRolloverIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "FontMid_P.jpg"))); // NOI18N
        getContentPane().add(cmdFontMid, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 40, 40));

        cmdFontBig.setFont(new Font("Arial", 0, 24));
        cmdFontBig.setIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "FontLarge_N.jpg"))); // NOI18N
        cmdFontBig.setToolTipText("Large");
        cmdFontBig.setBorder(null);
        cmdFontBig.setRolloverIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "FontLarge_P.jpg"))); // NOI18N
        cmdFontBig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdFontBigActionPerformed(evt);
            }
        });
        getContentPane().add(cmdFontBig, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 40, 40));

        cmdFontNor.setFont(new Font("Arial", 0, 11));
        cmdFontNor.setIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "FontNor_N.jpg"))); // NOI18N
        cmdFontNor.setToolTipText("Small");
        cmdFontNor.setBorder(null);
        cmdFontNor.setRolloverIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "FontNor_P.jpg"))); // NOI18N
        getContentPane().add(cmdFontNor, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 40, 40));

        cmdFontBlack.setFont(new Font("Tahoma", 1, 14));
        cmdFontBlack.setIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "Black_N.jpg"))); // NOI18N
        cmdFontBlack.setToolTipText("Black");
        cmdFontBlack.setBorder(null);
        cmdFontBlack.setRolloverIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "Black_P.jpg"))); // NOI18N
        getContentPane().add(cmdFontBlack, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 40, 40));

        cmdFontRed.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        cmdFontRed.setForeground(Color.red);
        cmdFontRed.setIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "Red_N.jpg"))); // NOI18N
        cmdFontRed.setToolTipText("Red");
        cmdFontRed.setBorder(null);
        cmdFontRed.setRolloverIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "Red_P.jpg"))); // NOI18N
        getContentPane().add(cmdFontRed, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 40, 40));

        cmdFontBlue.setFont(new Font("Tahoma", 1, 14));
        cmdFontBlue.setForeground(Color.blue);
        cmdFontBlue.setIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "Blue_N.jpg"))); // NOI18N
        cmdFontBlue.setToolTipText("Blue");
        cmdFontBlue.setBorder(null);
        cmdFontBlue.setRolloverIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "Blue_P.jpg"))); // NOI18N
        getContentPane().add(cmdFontBlue, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 40, 40));

        cmdRight.setFont(new Font("Tahoma", 1, 11));
        cmdRight.setIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "Right_P.jpg"))); // NOI18N
        cmdRight.setToolTipText("Align right");
        cmdRight.setBorder(null);
        cmdRight.setRolloverIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "Right_N.jpg"))); // NOI18N
        getContentPane().add(cmdRight, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, 40, 40));

        cmdLeft.setFont(new Font("Tahoma", 1, 11));
        cmdLeft.setIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "Left_P.jpg"))); // NOI18N
        cmdLeft.setToolTipText("Align left");
        cmdLeft.setBorder(null);
        cmdLeft.setRolloverIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "Left_N.jpg"))); // NOI18N
        getContentPane().add(cmdLeft, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 10, 40, 40));

        cmdCenter.setFont(new Font("Tahoma", 1, 11));
        cmdCenter.setIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "Centre_P.jpg"))); // NOI18N
        cmdCenter.setToolTipText("Align centre");
        cmdCenter.setBorder(null);
        cmdCenter.setRolloverIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "Centre_N.jpg"))); // NOI18N
        getContentPane().add(cmdCenter, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, 40, 40));

        cmdJustify.setFont(new Font("Tahoma", 1, 11));
        cmdJustify.setIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "Justify_P.jpg"))); // NOI18N
        cmdJustify.setToolTipText("Justify");
        cmdJustify.setBorder(null);
        cmdJustify.setRolloverIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "Justify_N.jpg"))); // NOI18N
        getContentPane().add(cmdJustify, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 10, 40, 40));

        cmdSave.setIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "EditorSaveBut_N.jpg"))); // NOI18N
        cmdSave.setToolTipText("Save");
        cmdSave.setBorder(null);
        cmdSave.setRolloverIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "EditorSaveBut_P.jpg"))); // NOI18N
        cmdSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSaveActionPerformed(evt);
            }
        });
        getContentPane().add(cmdSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 80, 80));

        cmdExit.setIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "CloseWindow.jpg"))); // NOI18N
        cmdExit.setToolTipText("Close");
        cmdExit.setBorder(null);
        cmdExit.setRolloverIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "CloseWindow_R.jpg"))); // NOI18N
        cmdExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdExitActionPerformed(evt);
            }
        });
        getContentPane().add(cmdExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 10, 80, 80));

        jScrollPane1.setViewportView(textPane);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 850, 420));
        getContentPane().add(statusPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, 300, 30));

        jLabel3.setIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "EditorForm.jpg"))); // NOI18N
        jLabel3.setOpaque(true);
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 870, 530));

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void cmdSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSaveActionPerformed
// TODO add your handling code here:
    WriteHTMLandSaveInDataBase();
    if(bIsProject){
        thisProjectView.PopulateProjectDetils(iNID);
    }else{
        thisNotesWindow.RefreshNoteList();
    }
}//GEN-LAST:event_cmdSaveActionPerformed

private void cmdExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdExitActionPerformed
// TODO add your handling code here:
    this.setVisible(false);
    this.dispose();
    thisFunctions.thisMainWindow.actionCloseEditorWindow(bIsProject, iNID);
}//GEN-LAST:event_cmdExitActionPerformed

private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
// TODO add your handling code here:
}//GEN-LAST:event_formComponentResized

private void cmdFontBigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdFontBigActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_cmdFontBigActionPerformed

//private void actionResizeEditorWindow(){
//    Dimension x=new Dimension(this.getSize().width-20, this.getSize().height-140);
//    Dimension xE=new Dimension(this.getSize().width-30, this.getSize().height-150);
////    System.out.println("Dim = " + x.toString());
////    textPane.setSize(x);
////    textPane.setPreferredSize(x);
////    textPane.setPreferredSize(new Dimension((int)textPane.getPreferredSize().getWidth(), (int)textPane.getPreferredSize().getHeight()));
////    textPane.setSize(this.getSize().width-20, this.getSize().height-20);
////    textPane.setSize(new Dimension(100, Integer.MAX_VALUE));
////    textPane.setPreferredSize(new Dimension(100, (int)textPane.getPreferredSize().getHeight()));
////    System.out.println("Dim = " + textPane.getPreferredSize().toString());
//    jScrollPane1.setPreferredSize(x);
//    jScrollPane1.setSize(x);
////    textPane.setPreferredSize(new Dimension(jScrollPane1.getSize().width-1, jScrollPane1.getSize().height-1));
////    textPane.setSize(new Dimension(jScrollPane1.getSize().width-1, jScrollPane1.getSize().height-1));
//}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public JButton cmdBold;
    public JButton cmdCenter;
    private JButton cmdExit;
    private JButton cmdFontBig;
    private JButton cmdFontBlack;
    private JButton cmdFontBlue;
    private JButton cmdFontMid;
    private JButton cmdFontNor;
    private JButton cmdFontRed;
    public JButton cmdItalic;
    public JButton cmdJustify;
    public JButton cmdLeft;
    public JButton cmdRight;
    private JButton cmdSave;
    public JButton cmdUnderline;
    private JLabel jLabel3;
    private JScrollPane jScrollPane1;
    private JPanel statusPane;
    private JTextPane textPane;
    // End of variables declaration//GEN-END:variables

    
    protected class MyDocumentListener implements DocumentListener {
        public void insertUpdate(DocumentEvent e) {
            displayEditInfo(e);
        }
        public void removeUpdate(DocumentEvent e) {
            displayEditInfo(e);
        }
        public void changedUpdate(DocumentEvent e) {
            displayEditInfo(e);
        }
        private void displayEditInfo(DocumentEvent e) {
            Document document = e.getDocument();
            int changeLength = e.getLength();
//            System.out.println(e.getType().toString() + ": " +
//                changeLength + " character" +
//                ((changeLength == 1) ? ". " : "s. ") +
//                " Text length = " + document.getLength() +
//                "." + newline);
        }
    }
        
    protected class CaretListenerLabel extends JLabel
                                       implements CaretListener {
        public CaretListenerLabel(String label) {
            super(label);
        }

        //Might not be invoked from the event dispatch thread.
        public void caretUpdate(CaretEvent e) {
            displaySelectionInfo(e.getDot(), e.getMark());
        }

        //This method can be invoked from any thread.  It 
        //invokes the setText and modelToView methods, which 
        //must run on the event dispatch thread. We use
        //invokeLater to schedule the code for execution
        //on the event dispatch thread.
        protected void displaySelectionInfo(final int dot,
                                            final int mark) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    
                    if (dot == mark) {  // no selection
                        setText("caret: text position: " + dot + newline);
//                        System.out.println("caret: text position: " + dot + newline);
                    } else if (dot < mark) {
                        setText("selection from: " + dot
                                + " to " + mark + newline);
//                        System.out.println("selection from: " + dot
//                                + " to " + mark + newline);
                    } else {
                        setText("selection from: " + mark
                                + " to " + dot + newline);
//                        System.out.println("selection from: " + mark
//                                + " to " + dot + newline);
                    }
                }
            });
        }
    }        
}
