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
/**
 *
 * @author  samitha_2
 */
public class ViewArticleNotesForm extends javax.swing.JInternalFrame {
    MainFunctions thisFunctions;
    int CurrentAIDforNotes;
    ArrayList CurrentNoteList=new ArrayList();
    ArrayList ArticleInformation=new ArrayList();
    /** Creates new form ViewArticleNotesForm */
    public ViewArticleNotesForm(MainFunctions envMainFunctions, int thisAID) {
        initComponents();
        thisFunctions=envMainFunctions;
        this.setLocation(160, 0);
        CurrentAIDforNotes=thisAID;
        PopulateArticleDetails(thisFunctions.thisUserDatabase.GetArticleInformation(thisAID));
        PopulateNotes(thisFunctions.thisUserDatabase.GenerateNoteListForArticles(CurrentAIDforNotes,false));        
    }

    private void PopulateNotes(ArrayList NoteList){
        CurrentNoteList=NoteList;
        if(CurrentNoteList.size()!=0){  //Notes available for this article
            int iNotes=CurrentNoteList.size();
            final String[] sNoteTitle=new String[iNotes];
            for(int i=0;i<iNotes;i++){
                sNoteTitle[i]=(String)((ArrayList)CurrentNoteList.get(i)).get(2);
            }
            lstNotes.setModel(new javax.swing.AbstractListModel() {
                String[] strings = sNoteTitle;
                public int getSize() { return strings.length; }
                public Object getElementAt(int i) { return strings[i]; }
            });
            lstNotes.setEnabled(true);
        }else{
            lstNotes.setModel(new javax.swing.AbstractListModel() {
                String[] strings = {"Notes not found."};
                public int getSize() { return strings.length; }
                public Object getElementAt(int i) { return strings[i]; }
            });            
            lstNotes.setEnabled(false);
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
        if(ArticleInformation.size()!=0){
            txtArticleDetailsPane.setFont(new Font("Serif", Font.PLAIN, 16));                     
            append(thisDetails.GetTheFormattedReference((String[])ArticleInformation.get(1)), oBold);
            append("\n\nKeywords: ",italic);
            append(((String[])ArticleInformation.get(1))[13],null);
            append("\n\nAbstract: ",italic);
            append(((String[])ArticleInformation.get(1))[14],null);            
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
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtArticleDetailsPane = new javax.swing.JTextPane();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstNotes = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtNote = new javax.swing.JTextArea();

        setClosable(true);
        setTitle("Notes for the article");
        setPreferredSize(new Dimension(900, 550));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Article Details"));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane4.setViewportView(txtArticleDetailsPane);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 590, 160));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 610, 190));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Actions"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("View Article");
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 120, -1));

        jButton2.setText("Edit Article");
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 120, -1));

        jButton3.setText("New Note");
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 120, -1));

        jButton4.setText("Export Notes");
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 120, -1));

        jButton5.setText("Close");
        jPanel2.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 120, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 10, 160, 190));

        lstNotes.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(lstNotes);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 300, 320));

        txtNote.setColumns(20);
        txtNote.setRows(5);
        jScrollPane3.setViewportView(txtNote);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 210, 440, 320));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JList lstNotes;
    private javax.swing.JTextPane txtArticleDetailsPane;
    private javax.swing.JTextArea txtNote;
    // End of variables declaration//GEN-END:variables

}
