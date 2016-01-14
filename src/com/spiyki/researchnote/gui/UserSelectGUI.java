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

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URL;

import com.spiyki.researchnote.utils.*;
import com.spiyki.researchnote.data.*;

/**
 *
 * @author samitha_2
 */
public class UserSelectGUI extends javax.swing.JFrame {

    MainFunctions thisFormHandles;

    /**
     * Creates new form StartupForm
     */
    public UserSelectGUI() {
        initComponents();
        //thisFormHandles=new MainFunctions(this);        
        InitGUIitems();
        System.out.println("Logo set");
    }

    public void InitStartupForm(MainFunctions passHandles) {
        thisFormHandles = passHandles;
    }

    private void InitGUIitems() {
        cmdMoveToOpenUserPanel.setVisible(false);
        int SCw = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int SCh = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation(SCw / 2 - 225, SCh / 2 - 200);
        java.awt.Toolkit toolkit = this.getToolkit();
        java.awt.Image appIcon = toolkit.createImage("ICON.jpg");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        FirstPanel = new javax.swing.JPanel();
        cmdMoveToCreateUserPanel = new javax.swing.JButton();
        cmdMoveToOpenUserPanel = new javax.swing.JButton();
        cmdExitFromMainWindow = new javax.swing.JButton();
        cmbSelectUser = new javax.swing.JComboBox();
        cmdOpenSelectedUser = new javax.swing.JButton();
        cmdSelect = new javax.swing.JButton();
        cmdMoveToCreateUserPanel2 = new javax.swing.JButton();
        CreateUserPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtUserLocation = new javax.swing.JTextField();
        cmdBrowseLocation = new javax.swing.JButton();
        cmdCreateUser = new javax.swing.JButton();
        cmdMoveToCreateUserPanel1 = new javax.swing.JButton();
        cmdExitFromMainWindow1 = new javax.swing.JButton();
        cmdSelect1 = new javax.swing.JButton();
        cmdMoveToCreateUserPanel3 = new javax.swing.JButton();
        SelectUserPanel = new javax.swing.JPanel();
        cmdBackFromSelectUser = new javax.swing.JButton();
        BackImage = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Research Note");
        setUndecorated(true);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        FirstPanel.setOpaque(false);
        FirstPanel.setPreferredSize(new java.awt.Dimension(400, 300));
        FirstPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cmdMoveToCreateUserPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "UserNew_N.jpg"))); // NOI18N
        cmdMoveToCreateUserPanel.setToolTipText("Add new user");
        cmdMoveToCreateUserPanel.setBorder(null);
        cmdMoveToCreateUserPanel.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "UserNew_R.jpg"))); // NOI18N
        cmdMoveToCreateUserPanel.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "UserNew_S.jpg"))); // NOI18N
        cmdMoveToCreateUserPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdMoveToCreateUserPanelActionPerformed(evt);
            }
        });
        FirstPanel.add(cmdMoveToCreateUserPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 100, 80));

        cmdMoveToOpenUserPanel.setText("Open Existing User");
        cmdMoveToOpenUserPanel.setEnabled(false);
        cmdMoveToOpenUserPanel.setMaximumSize(new java.awt.Dimension(150, 23));
        cmdMoveToOpenUserPanel.setMinimumSize(new java.awt.Dimension(150, 23));
        cmdMoveToOpenUserPanel.setPreferredSize(new java.awt.Dimension(150, 23));
        cmdMoveToOpenUserPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdMoveToOpenUserPanelActionPerformed(evt);
            }
        });
        FirstPanel.add(cmdMoveToOpenUserPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, 180, 30));

        cmdExitFromMainWindow.setIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "Exit_N.jpg"))); // NOI18N
        cmdExitFromMainWindow.setToolTipText("Exit");
        cmdExitFromMainWindow.setBorder(null);
        cmdExitFromMainWindow.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "Exit_R.jpg"))); // NOI18N
        cmdExitFromMainWindow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdExitFromMainWindowActionPerformed(evt);
            }
        });
        FirstPanel.add(cmdExitFromMainWindow, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 220, 100, 80));

        cmbSelectUser.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        cmbSelectUser.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbSelectUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSelectUserActionPerformed(evt);
            }
        });
        FirstPanel.add(cmbSelectUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 230, 50));

        cmdOpenSelectedUser.setIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "OpenUser_N.jpg"))); // NOI18N
        cmdOpenSelectedUser.setToolTipText("Use selected user profile");
        cmdOpenSelectedUser.setBorder(null);
        cmdOpenSelectedUser.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "OpenUser_R.jpg"))); // NOI18N
        cmdOpenSelectedUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdOpenSelectedUserActionPerformed(evt);
            }
        });
        FirstPanel.add(cmdOpenSelectedUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 130, 80, 50));

        cmdSelect.setIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "UserList_N.jpg"))); // NOI18N
        cmdSelect.setToolTipText("Select user");
        cmdSelect.setBorder(null);
        cmdSelect.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "UserList_R.jpg"))); // NOI18N
        cmdSelect.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "UserList_S.jpg"))); // NOI18N
        cmdSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSelectActionPerformed(evt);
            }
        });
        FirstPanel.add(cmdSelect, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 100, 80));

        cmdMoveToCreateUserPanel2.setIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "UserImport_N.jpg"))); // NOI18N
        cmdMoveToCreateUserPanel2.setToolTipText("Import User");
        cmdMoveToCreateUserPanel2.setBorder(null);
        cmdMoveToCreateUserPanel2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "UserImport_R.jpg"))); // NOI18N
        cmdMoveToCreateUserPanel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdMoveToCreateUserPanel2ActionPerformed(evt);
            }
        });
        FirstPanel.add(cmdMoveToCreateUserPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, 100, 80));

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        getContentPane().add(FirstPanel, gridBagConstraints);

        CreateUserPanel.setOpaque(false);
        CreateUserPanel.setPreferredSize(new java.awt.Dimension(400, 300));
        CreateUserPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("User Name");
        CreateUserPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));
        CreateUserPanel.add(txtUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 220, 30));

        jLabel3.setText("Location");
        CreateUserPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));
        CreateUserPanel.add(txtUserLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, 160, 30));

        cmdBrowseLocation.setIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "Browse_N.jpg"))); // NOI18N
        cmdBrowseLocation.setToolTipText("Browse folders");
        cmdBrowseLocation.setBorder(null);
        cmdBrowseLocation.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "Browse_R.jpg"))); // NOI18N
        cmdBrowseLocation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBrowseLocationActionPerformed(evt);
            }
        });
        CreateUserPanel.add(cmdBrowseLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 160, 50, 30));

        cmdCreateUser.setIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "AddUser_N.jpg"))); // NOI18N
        cmdCreateUser.setToolTipText("Add user details");
        cmdCreateUser.setBorder(null);
        cmdCreateUser.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "AddUser_R.jpg"))); // NOI18N
        cmdCreateUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCreateUserActionPerformed(evt);
            }
        });
        CreateUserPanel.add(cmdCreateUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 120, 70, 70));

        cmdMoveToCreateUserPanel1.setIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "UserNew_N.jpg"))); // NOI18N
        cmdMoveToCreateUserPanel1.setToolTipText("Add new user");
        cmdMoveToCreateUserPanel1.setBorder(null);
        cmdMoveToCreateUserPanel1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "UserNew_R.jpg"))); // NOI18N
        cmdMoveToCreateUserPanel1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "UserNew_S.jpg"))); // NOI18N
        cmdMoveToCreateUserPanel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdMoveToCreateUserPanel1ActionPerformed(evt);
            }
        });
        CreateUserPanel.add(cmdMoveToCreateUserPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 100, 80));

        cmdExitFromMainWindow1.setIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "Exit_N.jpg"))); // NOI18N
        cmdExitFromMainWindow1.setToolTipText("Exit");
        cmdExitFromMainWindow1.setBorder(null);
        cmdExitFromMainWindow1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "Exit_R.jpg"))); // NOI18N
        cmdExitFromMainWindow1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdExitFromMainWindow1ActionPerformed(evt);
            }
        });
        CreateUserPanel.add(cmdExitFromMainWindow1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 220, 100, 80));

        cmdSelect1.setIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "UserList_N.jpg"))); // NOI18N
        cmdSelect1.setToolTipText("Select user");
        cmdSelect1.setBorder(null);
        cmdSelect1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "UserList_R.jpg"))); // NOI18N
        cmdSelect1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "UserList_S.jpg"))); // NOI18N
        cmdSelect1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSelect1ActionPerformed(evt);
            }
        });
        CreateUserPanel.add(cmdSelect1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 100, 80));

        cmdMoveToCreateUserPanel3.setIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "UserImport_N.jpg"))); // NOI18N
        cmdMoveToCreateUserPanel3.setToolTipText("Import User");
        cmdMoveToCreateUserPanel3.setBorder(null);
        cmdMoveToCreateUserPanel3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "UserImport_R.jpg"))); // NOI18N
        cmdMoveToCreateUserPanel3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdMoveToCreateUserPanel3ActionPerformed(evt);
            }
        });
        CreateUserPanel.add(cmdMoveToCreateUserPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, 100, 80));

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        getContentPane().add(CreateUserPanel, gridBagConstraints);

        SelectUserPanel.setOpaque(false);
        SelectUserPanel.setPreferredSize(new java.awt.Dimension(400, 300));

        cmdBackFromSelectUser.setText("Back");
        cmdBackFromSelectUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBackFromSelectUserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SelectUserPanelLayout = new javax.swing.GroupLayout(SelectUserPanel);
        SelectUserPanel.setLayout(SelectUserPanelLayout);
        SelectUserPanelLayout.setHorizontalGroup(
            SelectUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SelectUserPanelLayout.createSequentialGroup()
                .addContainerGap(163, Short.MAX_VALUE)
                .addComponent(cmdBackFromSelectUser, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(137, 137, 137))
        );
        SelectUserPanelLayout.setVerticalGroup(
            SelectUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SelectUserPanelLayout.createSequentialGroup()
                .addContainerGap(234, Short.MAX_VALUE)
                .addComponent(cmdBackFromSelectUser, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        getContentPane().add(SelectUserPanel, gridBagConstraints);

        BackImage.setIcon(new javax.swing.ImageIcon(getClass().getResource(MainFunctions.IMAGE_PATH + "Backgroound_Blank.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        getContentPane().add(BackImage, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void cmdMoveToCreateUserPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdMoveToCreateUserPanelActionPerformed
// TODO add your handling code here:
    thisFormHandles.DisplayCreateUserWindow();
    cmdMoveToCreateUserPanel1.setSelected(true);
}//GEN-LAST:event_cmdMoveToCreateUserPanelActionPerformed

private void cmdMoveToOpenUserPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdMoveToOpenUserPanelActionPerformed
// TODO add your handling code here:
    thisFormHandles.DisplaySelectUserWindow();
}//GEN-LAST:event_cmdMoveToOpenUserPanelActionPerformed

private void cmdExitFromMainWindowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdExitFromMainWindowActionPerformed
// TODO add your handling code here:
    System.exit(0);
}//GEN-LAST:event_cmdExitFromMainWindowActionPerformed

private void cmdCreateUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCreateUserActionPerformed
// TODO add your handling code here:
    JOptionPane optionPane = new JOptionPane();
    if(txtUserLocation.getText().length()==0 || txtUserName.getText().length()==0)
    {
        optionPane.showMessageDialog(null, "Please enter a user name and a location to save the user information", "Research Note PE", JOptionPane.OK_OPTION);
    }else {
        if (thisFormHandles.AddUser(txtUserName.getText(), txtUserLocation.getText())) {
//        thisFormHandles.DisplaySelectUserWindow();
            thisFormHandles.DisplayMainWindow();
        } else {
            //optionPane.showConfirmDialog(null, "Do you need to backup now?", "Confirm your action" , JOptionPane.YES_NO_OPTION);
            optionPane.showMessageDialog(null, "The username exists, please select differnet name", "Research Note PE", JOptionPane.OK_OPTION);
            txtUserName.selectAll();
        }
    }
}//GEN-LAST:event_cmdCreateUserActionPerformed

private void cmdBackFromSelectUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBackFromSelectUserActionPerformed
// TODO add your handling code here:
    thisFormHandles.DisplayMainWindow();
}//GEN-LAST:event_cmdBackFromSelectUserActionPerformed

private void cmdBrowseLocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBrowseLocationActionPerformed
// TODO add your handling code here:
    JFileChooser thisChooser = new JFileChooser();
    thisChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    int chooserRes = thisChooser.showOpenDialog(this);
    if (chooserRes == JFileChooser.APPROVE_OPTION) {
        File thisFile = thisChooser.getSelectedFile();
        txtUserLocation.setText(thisFile.getAbsolutePath());
    }
}//GEN-LAST:event_cmdBrowseLocationActionPerformed

private void cmdOpenSelectedUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdOpenSelectedUserActionPerformed
// TODO add your handling code here:
    if (cmbSelectUser.getSelectedIndex() != -1) {
        thisFormHandles.OpenMainWindowsForm(cmbSelectUser.getSelectedIndex());
    }
}//GEN-LAST:event_cmdOpenSelectedUserActionPerformed

private void cmbSelectUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSelectUserActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_cmbSelectUserActionPerformed

private void cmdMoveToCreateUserPanel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdMoveToCreateUserPanel1ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_cmdMoveToCreateUserPanel1ActionPerformed

private void cmdExitFromMainWindow1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdExitFromMainWindow1ActionPerformed
// TODO add your handling code here:
    System.exit(0);
}//GEN-LAST:event_cmdExitFromMainWindow1ActionPerformed

private void cmdSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSelectActionPerformed
// TODO add your handling code here:
    thisFormHandles.DisplayMainWindow();
}//GEN-LAST:event_cmdSelectActionPerformed

private void cmdSelect1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSelect1ActionPerformed
// TODO add your handling code here:
    thisFormHandles.DisplayMainWindow();
}//GEN-LAST:event_cmdSelect1ActionPerformed

private void cmdMoveToCreateUserPanel2ActionPerformed(java.awt.event.ActionEvent evt) {
    //This method is not currently implemented. So send a notice saying "This feature will be available in a future version of Research Notes"
    JOptionPane thisMessage = new JOptionPane();
    thisMessage.showMessageDialog(null, "This feature will be available in a future version of Research Note", "Research Note PE", JOptionPane.OK_OPTION);
}

private void cmdMoveToCreateUserPanel3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdMoveToCreateUserPanel3ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_cmdMoveToCreateUserPanel3ActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new StartupForm().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BackImage;
    public javax.swing.JPanel CreateUserPanel;
    public javax.swing.JPanel FirstPanel;
    public javax.swing.JPanel SelectUserPanel;
    public javax.swing.JComboBox cmbSelectUser;
    private javax.swing.JButton cmdBackFromSelectUser;
    private javax.swing.JButton cmdBrowseLocation;
    private javax.swing.JButton cmdCreateUser;
    private javax.swing.JButton cmdExitFromMainWindow;
    private javax.swing.JButton cmdExitFromMainWindow1;
    private javax.swing.JButton cmdMoveToCreateUserPanel;
    private javax.swing.JButton cmdMoveToCreateUserPanel1;
    private javax.swing.JButton cmdMoveToCreateUserPanel2;
    private javax.swing.JButton cmdMoveToCreateUserPanel3;
    private javax.swing.JButton cmdMoveToOpenUserPanel;
    public javax.swing.JButton cmdOpenSelectedUser;
    public javax.swing.JButton cmdSelect;
    public javax.swing.JButton cmdSelect1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    public javax.swing.JTextField txtUserLocation;
    public javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables

}
