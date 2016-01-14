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
import java.awt.Dimension;

/**
 *
 * @author  samitha_2
 */
public class SideBarForm extends javax.swing.JInternalFrame {
    MainFunctions thisFunctions;
    /** Creates new form SideBarForm */
    public SideBarForm(MainFunctions envMainFunctions) {
        initComponents();
        thisFunctions=envMainFunctions;
//        this.setSelected(true);
        actionPositionTheWindow();
    }

    public void actionPositionTheWindow(){
        int iWidth=200;
        int iHeight=thisFunctions.thisMainWindow.getSize().height-60;
        this.setSize(iWidth, iHeight);
        int iPosX=thisFunctions.thisMainWindow.getWidth()-this.getWidth()-10;
        this.setLocation(iPosX, 0);
        this.setTitle("Information Bar");
        actionSetProjectInfoBar();
        cmdProjectSelect.setSelected(true);
        cmdArticleSelect.setSelected(true);
    }
    
    private void actionSetProjectInfoBar(){
        pnlProjects.setLocation(1, 1);
        if(cmdProjectSelect.isSelected()){
            actExpandProjects(true);
        }else{
            actExpandProjects(false);
        }
        actExpandArticle(cmdArticleSelect.isSelected());
    }
    
    private void actExpandProjects(Boolean bShrink){        
        if(bShrink){
            pnlProjects.setSize(this.getSize().width-2, 20);
            cmdProjectSelect.setLocation(1, 0);
            cmdProjectSelect.setSize(this.getSize().width-4, 20);
            int iTxtY=cmdProjectSelect.getLocation().y+cmdProjectSelect.getSize().height+1;
            srlTextContainer.setLocation(1, iTxtY);
            srlTextContainer.setSize(pnlProjects.getSize().width-2,0);            
            txtProjInfo.setLocation(0,0);            
            txtProjInfo.setPreferredSize(new Dimension(srlTextContainer.getSize().width-1,srlTextContainer.getSize().height));
            txtProjInfo.setSize(new Dimension(srlTextContainer.getSize().width-1,srlTextContainer.getSize().height));                
        }else{
            pnlProjects.setSize(this.getSize().width-2, 300);
            cmdProjectSelect.setLocation(1, 0);            
            cmdProjectSelect.setSize(pnlProjects.getSize().width-2, 20);
            int iTxtY=cmdProjectSelect.getLocation().y+cmdProjectSelect.getSize().height+1;
            srlTextContainer.setLocation(1, iTxtY);
            srlTextContainer.setSize(pnlProjects.getSize().width-2,pnlProjects.getSize().height-iTxtY-1);
            txtProjInfo.setLocation(0,0);
            txtProjInfo.setPreferredSize(new Dimension(srlTextContainer.getSize().width-1,srlTextContainer.getSize().height));
            txtProjInfo.setSize(new Dimension(srlTextContainer.getSize().width-1,srlTextContainer.getSize().height));            
        }
    }
    
    private void actExpandArticle(Boolean bShrink){
        pnlArticles.setLocation(1,pnlProjects.getLocation().y+pnlProjects.getSize().height+1);
        if(bShrink){
            pnlArticles.setSize(this.getSize().width-2, 20);
            cmdArticleSelect.setLocation(1, 0);
            cmdArticleSelect.setSize(this.getSize().width-4, 20);
            int iTxtY=cmdArticleSelect.getLocation().y+cmdArticleSelect.getSize().height+1;
            srlArticleTextCont.setLocation(1, iTxtY);
            srlArticleTextCont.setSize(pnlArticles.getSize().width-2,pnlArticles.getSize().height-iTxtY-1);            
            txtArticleInfo.setLocation(0,0);            
            txtArticleInfo.setPreferredSize(new Dimension(srlArticleTextCont.getSize().width-1,srlArticleTextCont.getSize().height));
            txtArticleInfo.setSize(new Dimension(srlArticleTextCont.getSize().width-1,srlArticleTextCont.getSize().height));               
        }else{
            pnlArticles.setSize(this.getSize().width-2, 300);
            cmdArticleSelect.setLocation(1, 0);
            cmdArticleSelect.setSize(this.getSize().width-4, 20);
            int iTxtY=cmdArticleSelect.getLocation().y+cmdArticleSelect.getSize().height+1;
            srlArticleTextCont.setLocation(1, iTxtY);
            srlArticleTextCont.setSize(pnlArticles.getSize().width-2,pnlArticles.getSize().height-iTxtY-1);             
            txtArticleInfo.setLocation(0,0);            
            txtArticleInfo.setPreferredSize(new Dimension(srlArticleTextCont.getSize().width-1,srlArticleTextCont.getSize().height));
            txtArticleInfo.setSize(new Dimension(srlArticleTextCont.getSize().width-1,srlArticleTextCont.getSize().height));              
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

        pnlProjects = new javax.swing.JPanel();
        cmdProjectSelect = new javax.swing.JToggleButton();
        srlTextContainer = new javax.swing.JScrollPane();
        txtProjInfo = new javax.swing.JEditorPane();
        pnlArticles = new javax.swing.JPanel();
        cmdArticleSelect = new javax.swing.JToggleButton();
        srlArticleTextCont = new javax.swing.JScrollPane();
        txtArticleInfo = new javax.swing.JEditorPane();

        setBorder(null);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlProjects.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlProjects.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cmdProjectSelect.setText("Project Info");
        cmdProjectSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdProjectSelectActionPerformed(evt);
            }
        });
        pnlProjects.add(cmdProjectSelect, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 0, 200, 20));

        srlTextContainer.setViewportView(txtProjInfo);

        pnlProjects.add(srlTextContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 200, 140));

        getContentPane().add(pnlProjects, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 20));

        pnlArticles.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlArticles.setPreferredSize(new Dimension(200, 180));
        pnlArticles.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cmdArticleSelect.setText("Article Info");
        cmdArticleSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdArticleSelectActionPerformed(evt);
            }
        });
        pnlArticles.add(cmdArticleSelect, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 20));

        srlArticleTextCont.setViewportView(txtArticleInfo);

        pnlArticles.add(srlArticleTextCont, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 200, 140));

        getContentPane().add(pnlArticles, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, -1, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void cmdProjectSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdProjectSelectActionPerformed
// TODO add your handling code here:
    actionSetProjectInfoBar();
}//GEN-LAST:event_cmdProjectSelectActionPerformed

private void cmdArticleSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdArticleSelectActionPerformed
// TODO add your handling code here:
    if(cmdArticleSelect.isSelected()){
        actExpandArticle(true);
    }else{
        actExpandArticle(false);
    }
}//GEN-LAST:event_cmdArticleSelectActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton cmdArticleSelect;
    private javax.swing.JToggleButton cmdProjectSelect;
    private javax.swing.JPanel pnlArticles;
    private javax.swing.JPanel pnlProjects;
    private javax.swing.JScrollPane srlArticleTextCont;
    private javax.swing.JScrollPane srlTextContainer;
    private javax.swing.JEditorPane txtArticleInfo;
    private javax.swing.JEditorPane txtProjInfo;
    // End of variables declaration//GEN-END:variables

}
