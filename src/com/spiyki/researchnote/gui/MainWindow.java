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

import java.awt.Frame;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * @author samitha_2
 */
public class MainWindow extends javax.swing.JFrame {
    public AddProjectForm AddProjectWindow;
    public SelectProjectForm SelectProjecWindow;
    //    AddArticleForm AddArticleWindow;
//    AddEditArticle AddArticleWindow;
    public AddArticleForm AddArticleWindow;
    public MainFunctions thisFunctions;
    public SearchArticlesForm SearchArticlesWindow;
    public ViewArticleNotesForm ArticleNotesWindow;
    public ViewProjectForm CurrentProjectWindow;
    public SideBarForm SideBarWindow;
    ArrayList EditorWindows = new ArrayList();

    ArrayList ProjectWindows = new ArrayList();
    ArrayList NoteWindows = new ArrayList();
    int iEditorWindows = 0;

    final static byte SEL_TYP_PROJECTS = 1;
    final static byte SEL_TYP_ARTICLES = 2;
    byte SelectedType = SEL_TYP_PROJECTS;

    /**
     * Creates new form MainWindow
     */
    public MainWindow(MainFunctions envMainFunctions) {
        initComponents();
        thisFunctions = envMainFunctions;
//        this.setState(Frame.MAXIMIZED_BOTH);
        SetTheUserInfo();
        this.setSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width, java.awt.Toolkit.getDefaultToolkit().getScreenSize().height);
        actionSelectProjectWindow();
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        myDeskTop = new javax.swing.JDesktopPane();
        pnlArticles = new javax.swing.JPanel();
        cmdAddArticle = new javax.swing.JButton();
        cmdImportArticles = new javax.swing.JButton();
        cmdArticleList = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        pnlProjects = new javax.swing.JPanel();
        cmdAddProject = new javax.swing.JButton();
        cmdCurrentProject = new javax.swing.JButton();
        cmdProjectList = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        myDeskTop.setBackground(new java.awt.Color(255, 255, 255));
        myDeskTop.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                myDeskTopComponentResized(evt);
            }
        });

        pnlArticles.setBackground(new java.awt.Color(255, 255, 255));
        pnlArticles.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cmdAddArticle.setBackground(new java.awt.Color(0, 153, 102));
        cmdAddArticle.setForeground(new java.awt.Color(255, 255, 255));
        cmdAddArticle.setIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "ArticleAddBut_N.jpg"))); // NOI18N
        cmdAddArticle.setToolTipText("Add new article");
        cmdAddArticle.setBorder(null);
        cmdAddArticle.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "ArticleAddBut_P.jpg"))); // NOI18N
        cmdAddArticle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddArticleActionPerformed(evt);
            }
        });
        pnlArticles.add(cmdAddArticle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 193, 80, 80));

        cmdImportArticles.setBackground(new java.awt.Color(0, 102, 102));
        cmdImportArticles.setForeground(new java.awt.Color(255, 255, 255));
        cmdImportArticles.setIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "ArticleImportBut_P.jpg"))); // NOI18N
        cmdImportArticles.setToolTipText("Import articles");
        cmdImportArticles.setBorder(null);
        cmdImportArticles.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "ArticleImportBut_N.jpg"))); // NOI18N
        cmdImportArticles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdImportArticlesActionPerformed(evt);
            }
        });
        pnlArticles.add(cmdImportArticles, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 33, 80, 80));

        cmdArticleList.setBackground(new java.awt.Color(56, 166, 138));
        cmdArticleList.setForeground(new java.awt.Color(255, 255, 255));
        cmdArticleList.setIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "ArticleListBut_N.jpg"))); // NOI18N
        cmdArticleList.setToolTipText("Select/view articles");
        cmdArticleList.setBorder(null);
        cmdArticleList.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "ArticleListBut_P.jpg"))); // NOI18N
        cmdArticleList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdArticleListActionPerformed(evt);
            }
        });
        pnlArticles.add(cmdArticleList, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 113, 80, 80));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "ProjectBar.jpg"))); // NOI18N
        pnlArticles.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pnlArticles.setBounds(80, 0, 80, 500);
        myDeskTop.add(pnlArticles, javax.swing.JLayeredPane.DEFAULT_LAYER);

        pnlProjects.setBackground(new java.awt.Color(255, 255, 255));
        pnlProjects.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cmdAddProject.setBackground(new java.awt.Color(233, 147, 76));
        cmdAddProject.setForeground(new java.awt.Color(255, 255, 255));
        cmdAddProject.setIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "AddProject_N.jpg"))); // NOI18N
        cmdAddProject.setToolTipText("Add new project");
        cmdAddProject.setBorder(null);
        cmdAddProject.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "AddProject_R.jpg"))); // NOI18N
        cmdAddProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddProjectActionPerformed(evt);
            }
        });
        pnlProjects.add(cmdAddProject, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 193, 80, 80));

        cmdCurrentProject.setBackground(new java.awt.Color(128, 49, 10));
        cmdCurrentProject.setForeground(new java.awt.Color(255, 255, 255));
        cmdCurrentProject.setIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "CurrentProject_N.jpg"))); // NOI18N
        cmdCurrentProject.setToolTipText("Current Project Details");
        cmdCurrentProject.setBorder(null);
        cmdCurrentProject.setIconTextGap(0);
        cmdCurrentProject.setPreferredSize(new java.awt.Dimension(80, 80));
        cmdCurrentProject.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "CurrentProject_R.jpg"))); // NOI18N
        cmdCurrentProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCurrentProjectActionPerformed(evt);
            }
        });
        pnlProjects.add(cmdCurrentProject, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 33, 80, 80));

        cmdProjectList.setBackground(new java.awt.Color(167, 119, 64));
        cmdProjectList.setForeground(new java.awt.Color(255, 255, 255));
        cmdProjectList.setIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "ProjectList_N.jpg"))); // NOI18N
        cmdProjectList.setToolTipText("Select/View projects");
        cmdProjectList.setBorder(null);
        cmdProjectList.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "ProjectList_R.jpg"))); // NOI18N
        cmdProjectList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdProjectListActionPerformed(evt);
            }
        });
        pnlProjects.add(cmdProjectList, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 113, 80, 80));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "ProjectBar.jpg"))); // NOI18N
        pnlProjects.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pnlProjects.setBounds(0, 0, 80, 500);
        myDeskTop.add(pnlProjects, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(myDeskTop, javax.swing.GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(myDeskTop, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void SetTheUserInfo() {

//        System.out.println(this.getSize().height);
        String strTitleText = "Research Notes for " + thisFunctions.CurrentUserName;
        if (thisFunctions.CurrentPID != 0) {
            strTitleText += ", Current project: " + thisFunctions.thisUserDatabase.GetProjectDescription(thisFunctions.CurrentPID)[0];
        }
        LicenceMgmt thisLicTest = new LicenceMgmt();
        if (!thisLicTest.LICENSED) {
            strTitleText += " (DEMO VERSION)";
        }
        this.setTitle(strTitleText);
    }


    private void myDeskTopComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_myDeskTopComponentResized
// TODO add your handling code here:
//    jToolBar1.setSize(this.WIDTH , 20);
        actionRearrangeMainWindow();

    }//GEN-LAST:event_myDeskTopComponentResized

    public void actionAddArticleWindow() {
        if (AddArticleWindow == null) {
            AddArticleWindow = new AddArticleForm(thisFunctions, null);
            AddArticleWindow.setVisible(true);
            myDeskTop.add(AddArticleWindow);
            try {
                AddArticleWindow.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
            }
        } else {
            if (AddArticleWindow.isVisible() != true) {
                AddArticleWindow = new AddArticleForm(thisFunctions, null);
                AddArticleWindow.setVisible(true);
                myDeskTop.add(AddArticleWindow);
            }
            try {
                AddArticleWindow.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
            }
        }
    }


    public void actionSelectArticleWindow() {
        if (SearchArticlesWindow == null) {
            SearchArticlesWindow = new SearchArticlesForm(thisFunctions);
            SearchArticlesWindow.setVisible(true);
            myDeskTop.add(SearchArticlesWindow);
            try {
                SearchArticlesWindow.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
            }
        } else {
            if (SearchArticlesWindow.isVisible() != true) {
                SearchArticlesWindow = new SearchArticlesForm(thisFunctions);
                SearchArticlesWindow.setVisible(true);
                myDeskTop.add(SearchArticlesWindow);
            }
            try {
                SearchArticlesWindow.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
            }
        }
    }


    public void actionAddProjectWindow() {
        if (AddProjectWindow == null) {
            AddProjectWindow = new AddProjectForm(thisFunctions, false);
            AddProjectWindow.setVisible(true);
            myDeskTop.add(AddProjectWindow);
            try {
                AddProjectWindow.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
            }
        } else {
            if (AddProjectWindow.isVisible()) {

            } else {
                AddProjectWindow = new AddProjectForm(thisFunctions, false);
                AddProjectWindow.setVisible(true);
                myDeskTop.add(AddProjectWindow);
            }
            try {
                AddProjectWindow.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
            }
        }
    }

    public void actionEditProjectWindow(int PID) {
        //Check the ProjectWindows arraylist for any opened project windows
        Boolean bProjectFound = false;
        int iPrjLst = ProjectWindows.size();
        for (int i = 0; i < iPrjLst; i++) {
            int ePID = ((AddProjectForm) ProjectWindows.get(i)).iEditingPID;
            if (PID == ePID) {//If this window has already been opened
                iPrjLst = i;
                bProjectFound = true;
            } else {       //Open a new window and populate the stuff
                bProjectFound = false;
            }
        }
        if (!bProjectFound) {
            AddProjectWindow = new AddProjectForm(thisFunctions, true);
            ProjectWindows.add(AddProjectWindow);
            iPrjLst = ProjectWindows.size() - 1;
            ((AddProjectForm) ProjectWindows.get(iPrjLst)).setVisible(true);
            myDeskTop.add((AddProjectForm) ProjectWindows.get(iPrjLst));
            thisFunctions.thisUserDatabase.PopulateEditProjectWindow((AddProjectForm) ProjectWindows.get(iPrjLst), PID);
        }
        try {
            ((AddProjectForm) ProjectWindows.get(iPrjLst)).setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
        }
    }

    public void actionCloseProjectWindow(int PID) {
        //This function should find and remove the appropriate array item
        int iPrjLst = ProjectWindows.size();
        for (int i = 0; i < iPrjLst; i++) {
            int ePID = ((AddProjectForm) ProjectWindows.get(i)).iEditingPID;
            if (PID == ePID) {//If this window has already been opened
                ProjectWindows.remove(i);
                i = iPrjLst;
            }
        }
    }

    public void actionViewNotesWindow(int AID) {//Notes for the specific article
        ArticleNotesWindow = new ViewArticleNotesForm(thisFunctions, AID);
        ArticleNotesWindow.setVisible(true);
        myDeskTop.add(ArticleNotesWindow);
        try {
            ArticleNotesWindow.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
        }
    }

    public void actionViewSideBarWindow() {//Notes for the specific article
        if (SideBarWindow == null) {
            SideBarWindow = new SideBarForm(thisFunctions);
            SideBarWindow.setVisible(true);
            myDeskTop.add(SideBarWindow);
            try {
                SideBarWindow.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
            }
        } else {
            if (SideBarWindow.isVisible() != true) {
                SideBarWindow = new SideBarForm(thisFunctions);
                SideBarWindow.setVisible(true);
                myDeskTop.add(SideBarWindow);
            }
            try {
                SideBarWindow.setSelected(true);
                SideBarWindow.actionPositionTheWindow();
            } catch (java.beans.PropertyVetoException e) {
            }
        }
        SideBarWindow.actionPositionTheWindow();
    }

    public void actionSelectProjectWindow() {//Notes for the specific article
        if (SelectProjecWindow == null) {
            SelectProjecWindow = new SelectProjectForm(thisFunctions);
            SelectProjecWindow.setVisible(true);
            myDeskTop.add(SelectProjecWindow);
            try {
                SelectProjecWindow.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
            }
        } else {
            if (SelectProjecWindow.isVisible() != true) {
                SelectProjecWindow = new SelectProjectForm(thisFunctions);
                SelectProjecWindow.setVisible(true);
                myDeskTop.add(SelectProjecWindow);
            }
            try {
                SelectProjecWindow.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
            }
        }
    }

    public void actionCurrentProjectWindow() {//Notes for the specific article
        if (CurrentProjectWindow == null) {
            CurrentProjectWindow = new ViewProjectForm(thisFunctions, thisFunctions.CurrentPID);
            CurrentProjectWindow.setVisible(true);
            myDeskTop.add(CurrentProjectWindow);
            try {
                CurrentProjectWindow.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
            }
        } else {
            if (CurrentProjectWindow.isVisible() != true) {
                CurrentProjectWindow = new ViewProjectForm(thisFunctions, thisFunctions.CurrentPID);
                CurrentProjectWindow.setVisible(true);
                myDeskTop.add(CurrentProjectWindow);
            }
            try {
                CurrentProjectWindow.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
            }
        }
    }

    public void actionOpenEditorWindow(Boolean bProject, int iItem, ArrayList frmRef) {
//Check the EditorWindows arraylist for any opened editor windows
        Boolean bEditorFound = false;
        int iEdtLst = EditorWindows.size();
        for (int i = 0; i < iEdtLst; i++) {
            int eItem = ((SimpleEditorForm) EditorWindows.get(i)).iNID;
            Boolean eIsProj = ((SimpleEditorForm) EditorWindows.get(i)).bIsProject;
            if (iItem == eItem & eIsProj == bProject) {//If this window has already been opened
                iEdtLst = i;
                bEditorFound = true;
            } else {       //Open a new window and populate the stuff
                bEditorFound = false;
            }
        }
        if (!bEditorFound) {
            SimpleEditorForm thisEditor = new SimpleEditorForm(thisFunctions, iEditorWindows++, bProject, iItem, frmRef);
            EditorWindows.add(thisEditor);
            iEdtLst = EditorWindows.size() - 1;
            ((SimpleEditorForm) EditorWindows.get(iEdtLst)).setVisible(true);
            myDeskTop.add((SimpleEditorForm) EditorWindows.get(iEdtLst));
//        thisFunctions.thisUserDatabase.PopulateEditProjectWindow((AddProjectForm)EditorWindows.get(iEdtLst), PID);
        }
        try {
            ((SimpleEditorForm) EditorWindows.get(iEdtLst)).setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
        }
    }


    public void actionCloseEditorWindow(Boolean bProject, int iItem) {
        //This function will find and remove the given editor window
        int iEdtLst = EditorWindows.size();
        for (int i = 0; i < iEdtLst; i++) {
            int eItem = ((SimpleEditorForm) EditorWindows.get(i)).iNID;
            Boolean eIsProj = ((SimpleEditorForm) EditorWindows.get(i)).bIsProject;
            if (iItem == eItem & eIsProj == bProject) {//If this window has already been opened
                EditorWindows.remove(i);
                i = iEdtLst;
            }
        }
    }

    public void actionRearrangeMainWindow() {
        pnlProjects.setSize(80, this.getSize().height - 100);
        pnlArticles.setSize(80, this.getSize().height - 100);
        actionViewSideBarWindow();
    }

    private void actionSelectProjectTools() {
        pnlArticles.setLocation(0, 0);
        pnlProjects.setLocation(80, 0);
    }

    private void actionSelectArticleTools() {
        pnlArticles.setLocation(80, 0);
        pnlProjects.setLocation(0, 0);
    }

    private void cmdCurrentProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCurrentProjectActionPerformed
// TODO add your handling code here:
        //actionSelectProjectTools();
        if (thisFunctions.CurrentPID != 0) {
            actionCurrentProjectWindow();
        } else {
            JOptionPane optionPane = new JOptionPane();
            optionPane.showMessageDialog(null, "Select a project as current project");
            actionSelectProjectWindow();
        }

    }//GEN-LAST:event_cmdCurrentProjectActionPerformed

    private void cmdProjectListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdProjectListActionPerformed
// TODO add your handling code here:
        //actionSelectProjectTools();
        actionSelectProjectWindow();
    }//GEN-LAST:event_cmdProjectListActionPerformed

    private void cmdAddProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAddProjectActionPerformed
// TODO add your handling code here:
        //actionSelectProjectTools();
//    actionAddProjectWindow();
        actionSelectProjectWindow();
        SelectProjecWindow.actionAddNewProject();
    }//GEN-LAST:event_cmdAddProjectActionPerformed

    private void cmdArticleListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdArticleListActionPerformed
// TODO add your handling code here:
        //actionSelectArticleTools();
        actionSelectArticleWindow();
    }//GEN-LAST:event_cmdArticleListActionPerformed

    private void cmdImportArticlesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdImportArticlesActionPerformed
// TODO add your handling code here:
        //actionSelectArticleTools();
//    TestEditor thisTestEdit=new TestEditor();
//    thisTestEdit.setVisible(true);
//    myDeskTop.add(thisTestEdit);
        JOptionPane optionPane = new JOptionPane();
        optionPane.showMessageDialog(null, "This version of Research Note can not import citations.\nPlease purchase the full software for this feature.", "DEMO version limit", JOptionPane.YES_OPTION);
        LicenceMgmt test = new LicenceMgmt();
        test.ReadMACAddress();

    }//GEN-LAST:event_cmdImportArticlesActionPerformed

    private void cmdAddArticleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAddArticleActionPerformed
// TODO add your handling code here:
        //actionSelectArticleTools();
        LicenceMgmt thisLicTest = new LicenceMgmt();
        if (!thisLicTest.LICENSED) {
            if (thisFunctions.thisUserDatabase.IfArticleCountExeedsNonLicensedLimit(thisLicTest)) {
                JOptionPane optionPane = new JOptionPane();
                optionPane.showMessageDialog(null, "Limit for number of articles reached.\nPlease purchase the full version to remove this limit.", "DEMO version limit", JOptionPane.YES_OPTION);
            } else {
                actionAddArticleWindow();
            }
        } else {
            actionAddArticleWindow();
        }

    }//GEN-LAST:event_cmdAddArticleActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new MainWindow().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdAddArticle;
    private javax.swing.JButton cmdAddProject;
    private javax.swing.JButton cmdArticleList;
    private javax.swing.JButton cmdCurrentProject;
    private javax.swing.JButton cmdImportArticles;
    private javax.swing.JButton cmdProjectList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    public javax.swing.JDesktopPane myDeskTop;
    private javax.swing.JPanel pnlArticles;
    private javax.swing.JPanel pnlProjects;
    // End of variables declaration//GEN-END:variables

}
