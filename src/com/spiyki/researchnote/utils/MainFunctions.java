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

package com.spiyki.researchnote.utils;

import java.io.File;
import com.spiyki.researchnote.gui.*;
import com.spiyki.researchnote.data.*;


/**
 *
 * @author samitha_2
 */
public class MainFunctions {
    public Main thisMain;
    public UserSelectGUI thisStartup;
    public UserInfo thisUsers;
    public MainWindow thisMainWindow;
    public DataBaseInit thisUserDatabase;
    public int CurrentPID=0;
    public String CurrentUserName;
    public String CurrentUserDatabaseLocation;

    public static final String IMAGE_PATH = "/res/images/";
    
    
    public MainFunctions(Main EnvMain){
        thisMain =EnvMain;
        thisStartup= thisMain.theStartup;
        thisStartup.InitStartupForm(this);
        thisUsers=new UserInfo();
        InitStartGUI();        
    }
    
    private void InitStartGUI(){
        DisplayMainWindow();
    }
    
    public void DisplayMainWindow(){
        thisStartup.FirstPanel.setVisible(true);
        thisStartup.CreateUserPanel.setVisible(false);
        thisStartup.SelectUserPanel.setVisible(false);
        String[] thisUserList=thisUsers.GetUserList();
        if(thisUserList==null){
            thisUserList=new String[1];
            thisUserList[0]="No Current User";
            thisStartup.cmbSelectUser.setModel(new javax.swing.DefaultComboBoxModel(thisUserList)); 
            thisStartup.cmbSelectUser.setEnabled(false);
            thisStartup.cmdOpenSelectedUser.setEnabled(false);
        }else{
           thisStartup.cmbSelectUser.setModel(new javax.swing.DefaultComboBoxModel(thisUserList)); 
           thisStartup.cmbSelectUser.setEnabled(true);
           thisStartup.cmdOpenSelectedUser.setEnabled(true);
        }          
        thisStartup.cmdSelect.setSelected(true);
    }
    
    public void DisplayCreateUserWindow(){
        thisStartup.FirstPanel.setVisible(false);
        thisStartup.CreateUserPanel.setVisible(true);
        thisStartup.SelectUserPanel.setVisible(false);     
        thisStartup.txtUserName.setText("");
        thisStartup.txtUserLocation.setText("");
    }
    
    public void DisplaySelectUserWindow(){
        thisStartup.FirstPanel.setVisible(false);
        thisStartup.CreateUserPanel.setVisible(false);
        thisStartup.SelectUserPanel.setVisible(true);        
        String[] thisUserList=thisUsers.GetUserList();
        if(thisUserList==null){
            thisUserList=new String[1];
            thisUserList[0]="No Current User";
            thisStartup.cmbSelectUser.setModel(new javax.swing.DefaultComboBoxModel(thisUserList)); 
            thisStartup.cmbSelectUser.setEnabled(false);
        }else{
           thisStartup.cmbSelectUser.setModel(new javax.swing.DefaultComboBoxModel(thisUserList)); 
           thisStartup.cmbSelectUser.setEnabled(true);
        }        
    }
    
    public boolean AddUser(String Username,String Path){
        String[] CurrentUsers=thisUsers.GetUserList();
        Boolean result=true;
        if(CurrentUsers!=null){
            for(int i=0;i<CurrentUsers.length;i++){
                if(CurrentUsers[i].compareTo(Username)==0){//The user name exists
                    result=false;
                }
            }
        }
        if(result){
           thisUsers.InsertNewUser(Username, Path);  
           if(thisUserDatabase!=null){
            thisUserDatabase=null;
           }           
           thisUserDatabase=new DataBaseInit(this);
           thisUserDatabase.CreateTheDatabase(Path + File.separator + "RNPE"+Username + File.separator + "DB");
        }
        return result;
    }
    
    public void OpenMainWindowsForm(int ThisUser){
       CurrentUserName=thisUsers.GetUser(ThisUser);
       CurrentUserDatabaseLocation=thisUsers.GetLocationPath(ThisUser);
       if(thisUserDatabase!=null){
        thisUserDatabase=null;
       }
       thisUserDatabase=new DataBaseInit(this);
       thisStartup.setVisible(false);
       thisMainWindow=new MainWindow(this);
       java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                thisMainWindow.setVisible(true);
            }
        });
        
    }
    
    public void RefreshProjectListWindow(){
        if(thisMainWindow.SelectProjecWindow.isVisible()){
            thisMainWindow.SelectProjecWindow.RefreshProjectList();
        }
    }
}
