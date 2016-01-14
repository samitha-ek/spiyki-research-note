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
import javax.swing.text.html.HTMLDocument;

/**
 *
 * @author  samitha_2
 */
public class AddProjectForm extends javax.swing.JInternalFrame {
    MainFunctions thisFunctions;
    Boolean bEditMode=false;
    int iEditingPID=0;
    String sEdtID;
    /** Creates new form AddProjectForm */
    public AddProjectForm(MainFunctions envMainFunctions,Boolean EditMode) {
        initComponents();        
        thisFunctions=envMainFunctions;
        bEditMode=EditMode;
        this.setLocation(160, 0);      
        if(bEditMode){
            this.setTitle("Edit Project");
        }else{
            this.setTitle("Add Project");
            this.cmdEditor.setEnabled(false);
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

        jLabel1 = new javax.swing.JLabel();
        txtProjectName = new javax.swing.JTextField();
        cmdAddProjectIntoDatabase = new javax.swing.JButton();
        cmdCancel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtProjectDescription = new javax.swing.JTextPane();
        cmdEditor = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(null);
        setPreferredSize(new java.awt.Dimension(670, 500));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Project Name");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));
        getContentPane().add(txtProjectName, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 550, -1));

        cmdAddProjectIntoDatabase.setIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "EditProjectOKBut_N.jpg"))); // NOI18N
        cmdAddProjectIntoDatabase.setToolTipText("Save changes");
        cmdAddProjectIntoDatabase.setBorder(null);
        cmdAddProjectIntoDatabase.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "EditProjectOKBut_P.jpg"))); // NOI18N
        cmdAddProjectIntoDatabase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddProjectIntoDatabaseActionPerformed(evt);
            }
        });
        getContentPane().add(cmdAddProjectIntoDatabase, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 80, 80));

        cmdCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "CloseWindow.jpg"))); // NOI18N
        cmdCancel.setToolTipText("Exit");
        cmdCancel.setBorder(null);
        cmdCancel.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "CloseWindow_R.jpg"))); // NOI18N
        cmdCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCancelActionPerformed(evt);
            }
        });
        getContentPane().add(cmdCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, 80, 80));

        txtProjectDescription.setEditable(false);
        jScrollPane1.setViewportView(txtProjectDescription);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 650, 270));

        cmdEditor.setFont(new java.awt.Font("Tahoma", 1, 11));
        cmdEditor.setIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "EditorTool_P.jpg"))); // NOI18N
        cmdEditor.setBorder(null);
        cmdEditor.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "EditorTool_R.jpg"))); // NOI18N
        cmdEditor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdEditorActionPerformed(evt);
            }
        });
        getContentPane().add(cmdEditor, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 150, 120, 30));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 650, 10));

        jLabel2.setText("Project Description");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 102, 650, 10));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "ArticleViewForm.jpg"))); // NOI18N
        jLabel3.setOpaque(true);
        jLabel3.setPreferredSize(new java.awt.Dimension(725, 570));
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 670, 470));

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void cmdAddProjectIntoDatabaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAddProjectIntoDatabaseActionPerformed
    if(bEditMode){
        thisFunctions.thisUserDatabase.UpdateProject(iEditingPID, txtProjectName.getText(), txtProjectDescription.getText());
    }else{
        thisFunctions.thisUserDatabase.AddProject(txtProjectName.getText(), txtProjectDescription.getText());
        this.setVisible(false);
    }
//    this.setVisible(false);
    thisFunctions.RefreshProjectListWindow();
}//GEN-LAST:event_cmdAddProjectIntoDatabaseActionPerformed

private void cmdCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCancelActionPerformed
    this.setVisible(false);
    this.dispose();    
    thisFunctions.thisMainWindow.actionCloseProjectWindow(iEditingPID);
    thisFunctions.RefreshProjectListWindow();
}//GEN-LAST:event_cmdCancelActionPerformed

private void cmdEditorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEditorActionPerformed
    ArrayList tmpAdd=new ArrayList();
    tmpAdd.add(this);
    tmpAdd.add(txtProjectDescription);
    thisFunctions.thisMainWindow.actionOpenEditorWindow(true, iEditingPID,tmpAdd);
}//GEN-LAST:event_cmdEditorActionPerformed

private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
}//GEN-LAST:event_formComponentShown

public void PopulateProjectDetils(int thisProjecID){
//    bEditMode=true;
    iEditingPID=thisProjecID;
    sEdtID="PV"+String.valueOf(iEditingPID);
    String thisProjectDetails[]=thisFunctions.thisUserDatabase.GetProjectDescription(thisProjecID);
    txtProjectName.setText(thisProjectDetails[0]);
    new DisplayInHTMLformat(thisProjectDetails[1], sEdtID, txtProjectDescription);
//    ConverHTMLsaveAndDisplay(thisProjectDetails[1]);
//    txtProjectDescription.setText(thisProjectDetails[1]);
//    cmdAddProjectIntoDatabase.setText("Save");
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdAddProjectIntoDatabase;
    private javax.swing.JButton cmdCancel;
    private javax.swing.JButton cmdEditor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextPane txtProjectDescription;
    private javax.swing.JTextField txtProjectName;
    // End of variables declaration//GEN-END:variables

}