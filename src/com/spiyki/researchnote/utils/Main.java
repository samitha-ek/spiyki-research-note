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

import javax.swing.UIManager;
import com.spiyki.researchnote.gui.*;

/**
 *
 * @author samitha_2
 */
public class Main {

    MainFunctions thisFormHandles;
    UserSelectGUI theStartup;
    
    public void InitializeCode(){
      try
      {                  
         UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      }
      catch (Exception e) {
          e.printStackTrace();
      }
        theStartup=new UserSelectGUI();
        
        thisFormHandles=new MainFunctions(this);        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                theStartup.setVisible(true);
            }
        }); 
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        //DataBaseInit thisDb=new DataBaseInit();
       Main thisMain=new Main();
       thisMain.InitializeCode();

    }
   


}
