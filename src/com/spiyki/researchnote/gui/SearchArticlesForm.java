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
import javax.swing.ImageIcon;



/**
 *
 * @author  samitha_2
 */
public class SearchArticlesForm extends javax.swing.JInternalFrame {
    MainFunctions thisFunctions;
    String[] strSearchTypes={"Any word", "Title", "Author", "Keyword"};
    String[] strSearchLink={"OR", "AND"};
    ViewArticleForm thisOpenedArticle;
    ArrayList OpenedArticles=new ArrayList();
    /** Creates new form SearchArticlesForm */
    public SearchArticlesForm(MainFunctions envMainFunctions) {
        initComponents();
        thisFunctions=envMainFunctions;
        this.setLocation(160, 0);
        InitGUI();
    }

    private void InitGUI(){ 
        cmbSearchType1.setModel(new javax.swing.DefaultComboBoxModel(strSearchTypes));
        cmbSearchType2.setModel(new javax.swing.DefaultComboBoxModel(strSearchTypes));
        cmbSearchType3.setModel(new javax.swing.DefaultComboBoxModel(strSearchTypes));
        cmbSearchLink1.setModel(new javax.swing.DefaultComboBoxModel(strSearchLink));
        cmbSearchLink2.setModel(new javax.swing.DefaultComboBoxModel(strSearchLink));
        lstArticlesFound.setModel(new javax.swing.AbstractListModel() {
            String[] strings = {"Enter search parameters"};
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });        
        lstArticlesFound.setEnabled(false);
        RefreshButtons();
        cmdEditArticle.setVisible(false);
    }
    
    private void RefreshButtons(){
        if(lstArticlesFound.isSelectionEmpty()){
            cmdAddArticleToProject.setEnabled(false);
            cmdAddArticleToProject.setVisible(false);
            cmdEditArticle.setEnabled(false);
            cmdViewNotes.setEnabled(false);
        }else{
            cmdAddArticleToProject.setVisible(true);
            cmdAddArticleToProject.setEnabled(true);            
            int thisAID=thisFunctions.thisUserDatabase.ArticleIndexList[lstArticlesFound.getSelectedIndex()];
            if((Boolean)thisFunctions.thisUserDatabase.GetArticleInformation(thisAID).get(2)==true){
                cmdAddArticleToProject.setText("");
                cmdAddArticleToProject.setIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "ArticleFromProjectBut_N.jpg")));
                cmdAddArticleToProject.setRolloverIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "ArticleFromProjectBut_P.jpg")));
            }else{
                cmdAddArticleToProject.setText("");              
                cmdAddArticleToProject.setIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "ArticleToProjectBut_N.jpg")));
                cmdAddArticleToProject.setRolloverIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "ArticleToProjectBut_P.jpg")));
            }
            cmdEditArticle.setEnabled(true);
            cmdViewNotes.setEnabled(true);            
        }
    }
    
    private void OpenArticle(int AID){
        //Check if the article is already opened
        Boolean bArticleOpened=false;
        int iPrjLst=OpenedArticles.size();
        for(int i=0;i<iPrjLst;i++){
            int eAID=((ViewArticleForm)OpenedArticles.get(i)).ThisAID;
            if(AID==eAID){//If this window has already been opened       
                iPrjLst=i;
                bArticleOpened=true;
            }else{       //Open a new window and populate the stuff
                bArticleOpened=false;
            }
        }
        if(!bArticleOpened){
            thisOpenedArticle=new ViewArticleForm(thisFunctions,AID);
            OpenedArticles.add(thisOpenedArticle);
            iPrjLst=OpenedArticles.size()-1;
            ((ViewArticleForm)OpenedArticles.get(iPrjLst)).setVisible(true);
            thisFunctions.thisMainWindow.myDeskTop.add((ViewArticleForm)OpenedArticles.get(iPrjLst));
        }
        try {
            ((ViewArticleForm)OpenedArticles.get(iPrjLst)).setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}          
    }
    
    public void actionCloseArticleWindow(int AID){
        //This function should find and remove the appropriate array item
        int iPrjLst=OpenedArticles.size();
        for(int i=0;i<iPrjLst;i++){
            int eAID=((ViewArticleForm)OpenedArticles.get(i)).ThisAID;
            if(AID==eAID){//If this window has already been opened       
                OpenedArticles.remove(i);
                i=iPrjLst;
            }
        }    
    } 
    
    public void actionSearchArticles(){
        final String[] strListOfArticles;
        checkAndArrangeSearchBoxes();
        thisFunctions.thisUserDatabase.SearchForArticle(txtSearchField1.getText(), cmbSearchType1.getSelectedIndex(), cmbSearchLink1.getSelectedIndex(), txtSearchField2.getText(), cmbSearchType2.getSelectedIndex(), cmbSearchLink2.getSelectedIndex(), txtSearchField3.getText(), cmbSearchType3.getSelectedIndex(), chkFromThisProject.isSelected());
        strListOfArticles=thisFunctions.thisUserDatabase.ArticlesList;
        lstArticlesFound.setModel(new javax.swing.AbstractListModel() {
            String[] strings = strListOfArticles;
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });        
        lstArticlesFound.setEnabled(true);      
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
        txtSearchField1 = new javax.swing.JTextField();
        cmbSearchType1 = new javax.swing.JComboBox();
        txtSearchField2 = new javax.swing.JTextField();
        cmbSearchType2 = new javax.swing.JComboBox();
        txtSearchField3 = new javax.swing.JTextField();
        cmbSearchType3 = new javax.swing.JComboBox();
        cmbSearchLink1 = new javax.swing.JComboBox();
        cmbSearchLink2 = new javax.swing.JComboBox();
        chkFromThisProject = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        cmdViewNotes = new javax.swing.JButton();
        cmdAddArticleToProject = new javax.swing.JButton();
        cmdSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstArticlesFound = new javax.swing.JList();
        cmdClose = new javax.swing.JButton();
        cmdEditArticle = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(null);
        setTitle("Search Articles");
        setPreferredSize(new java.awt.Dimension(725, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(txtSearchField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 350, -1));

        cmbSearchType1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cmbSearchType1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 140, -1));
        jPanel1.add(txtSearchField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 350, -1));

        cmbSearchType2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cmbSearchType2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, 140, -1));
        jPanel1.add(txtSearchField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 350, -1));

        cmbSearchType3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cmbSearchType3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 70, 140, -1));

        cmbSearchLink1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cmbSearchLink1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 10, 80, -1));

        cmbSearchLink2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cmbSearchLink2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 40, 80, -1));

        chkFromThisProject.setText("Articles from the current project only");
        chkFromThisProject.setOpaque(false);
        jPanel1.add(chkFromThisProject, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 350, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 610, 110));

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cmdViewNotes.setIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "ArticleOpenBut_N.jpg"))); // NOI18N
        cmdViewNotes.setToolTipText("Open article");
        cmdViewNotes.setBorder(null);
        cmdViewNotes.setRolloverIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "ArticleOpenBut_P.jpg"))); // NOI18N
        cmdViewNotes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdViewNotesActionPerformed(evt);
            }
        });
        jPanel2.add(cmdViewNotes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 80, 80));

        cmdAddArticleToProject.setIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "ArticleToProjectBut_P.jpg"))); // NOI18N
        cmdAddArticleToProject.setToolTipText("Add/remove from current project");
        cmdAddArticleToProject.setBorder(null);
        cmdAddArticleToProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddArticleToProjectActionPerformed(evt);
            }
        });
        jPanel2.add(cmdAddArticleToProject, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 80, 80));

        cmdSearch.setIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "ArticleSearchBut_N.jpg"))); // NOI18N
        cmdSearch.setToolTipText("Apply search criteria");
        cmdSearch.setBorder(null);
        cmdSearch.setRolloverIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "ArticleSearchBut_P.jpg"))); // NOI18N
        cmdSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSearchActionPerformed(evt);
            }
        });
        jPanel2.add(cmdSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 80, 80));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 0, 100, 290));

        lstArticlesFound.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstArticlesFound.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lstArticlesFoundMouseEntered(evt);
            }
        });
        lstArticlesFound.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstArticlesFoundValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lstArticlesFound);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 600, 440));

        cmdClose.setIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "CloseWindow.jpg"))); // NOI18N
        cmdClose.setToolTipText("Close window");
        cmdClose.setBorder(null);
        cmdClose.setRolloverIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "CloseWindow_R.jpg"))); // NOI18N
        cmdClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCloseActionPerformed(evt);
            }
        });
        getContentPane().add(cmdClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 480, 80, 80));

        cmdEditArticle.setText("Edit Selected");
        cmdEditArticle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdEditArticleActionPerformed(evt);
            }
        });
        getContentPane().add(cmdEditArticle, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 400, 80, 80));

        jLabel3.setIcon(new ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "ArticleEdit.jpg"))); // NOI18N
        jLabel3.setOpaque(true);
        jLabel3.setPreferredSize(new java.awt.Dimension(725, 570));
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 725, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void cmdSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSearchActionPerformed
    actionSearchArticles();
}

private void cmdCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCloseActionPerformed
    this.setVisible(false);
}//GEN-LAST:event_cmdCloseActionPerformed

private void cmdEditArticleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEditArticleActionPerformed
// TODO add your handling code here:
//    if(thisFunctions.thisUserDatabase.ArticlesList.length!=0){
//        thisFunctions.thisMainWindow.actionEditArticleWindow(thisFunctions.thisUserDatabase.ArticleIndexList[lstArticlesFound.getSelectedIndex()]);
//    }
}//GEN-LAST:event_cmdEditArticleActionPerformed

private void lstArticlesFoundMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstArticlesFoundMouseEntered
// TODO add your handling code here:
  
}//GEN-LAST:event_lstArticlesFoundMouseEntered

private void cmdViewNotesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdViewNotesActionPerformed
// TODO add your handling code here:
    OpenArticle(thisFunctions.thisUserDatabase.ArticleIndexList[lstArticlesFound.getSelectedIndex()]);
//    thisFunctions.thisMainWindow.actionViewNotesWindow(thisFunctions.thisUserDatabase.ArticleIndexList[lstArticlesFound.getSelectedIndex()]);
}//GEN-LAST:event_cmdViewNotesActionPerformed

private void cmdAddArticleToProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAddArticleToProjectActionPerformed
// TODO add your handling code here:
    int thisAID=thisFunctions.thisUserDatabase.ArticleIndexList[lstArticlesFound.getSelectedIndex()];
    Boolean isInProject=(Boolean)thisFunctions.thisUserDatabase.GetArticleInformation(thisAID).get(2);    
    //Is in the current project --> remove ; else --> add
    thisFunctions.thisUserDatabase.AddRemoveArticleFromCurrentProject(thisAID, !isInProject);
    cmdSearchActionPerformed(evt);
}//GEN-LAST:event_cmdAddArticleToProjectActionPerformed

private void lstArticlesFoundValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstArticlesFoundValueChanged
// TODO add your handling code here:
    RefreshButtons();
}//GEN-LAST:event_lstArticlesFoundValueChanged

private void checkAndArrangeSearchBoxes(){
    if(txtSearchField1.getText().compareTo("")==0 & txtSearchField2.getText().compareTo("")!=0){        
        txtSearchField1.setText(txtSearchField2.getText());
        txtSearchField2.setText("");
    }
    if(txtSearchField2.getText().compareTo("")==0 & txtSearchField3.getText().compareTo("")!=0){        
        txtSearchField2.setText(txtSearchField3.getText());
        txtSearchField3.setText("");
    }    
    if(txtSearchField1.getText().compareTo("")==0 & txtSearchField2.getText().compareTo("")!=0){        
        txtSearchField1.setText(txtSearchField2.getText());
        txtSearchField2.setText("");
    }    
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox chkFromThisProject;
    private javax.swing.JComboBox cmbSearchLink1;
    private javax.swing.JComboBox cmbSearchLink2;
    private javax.swing.JComboBox cmbSearchType1;
    private javax.swing.JComboBox cmbSearchType2;
    private javax.swing.JComboBox cmbSearchType3;
    private javax.swing.JButton cmdAddArticleToProject;
    private javax.swing.JButton cmdClose;
    private javax.swing.JButton cmdEditArticle;
    private javax.swing.JButton cmdSearch;
    private javax.swing.JButton cmdViewNotes;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList lstArticlesFound;
    private javax.swing.JTextField txtSearchField1;
    private javax.swing.JTextField txtSearchField2;
    private javax.swing.JTextField txtSearchField3;
    // End of variables declaration//GEN-END:variables

}
