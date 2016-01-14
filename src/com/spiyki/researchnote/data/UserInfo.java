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

package com.spiyki.researchnote.data;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author samitha_2
 */
public class UserInfo {
    private String[] UserList;
    private String[] LocationList;
    public UserInfo(){
        ReadCurrentUsers();
    }
    
    private void ReadCurrentUsers(){
        BufferedReader inputStream = null;        
        ArrayList Items=new ArrayList();
        try {
            inputStream = new BufferedReader(new FileReader("UserInfo"));
            String l;
            int i=0;
            while ((l = inputStream.readLine()) != null) {
                Items.add(l);
                i++;                               
            }
            UserList=new String[i];
            LocationList=new String[i];
            for(int j=0;j<i;j++){
                String[] thisUserInfo=((String)Items.get(j)).split("--");
                UserList[j]=thisUserInfo[0];
                LocationList[j]=thisUserInfo[1];
            }
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
    }
    
    public void InsertNewUser(String Username, String PathInfo){
      File thisUserFiles;
      BufferedWriter bw = null;
      BufferedWriter bw1 = null;
      String txtToAppend=Username+"--"+PathInfo;
      try {
         bw = new BufferedWriter(new FileWriter("UserInfo", true));
	 bw.write(txtToAppend);
	 bw.newLine();
	 bw.flush();
      } 
      catch (IOException ioe) {
	 ioe.printStackTrace();
      } finally {
        if(bw!=null){
            try{
                bw.close();
            }catch(IOException ioe){
                
            }
        }
        ReadCurrentUsers();
        thisUserFiles=new File(PathInfo + File.separator + "RNPE"+Username+File.separator + "Files");
        if(thisUserFiles.exists()){
            
        }else{
            thisUserFiles.mkdirs();
        }
          try {
             bw1 = new BufferedWriter(new FileWriter(PathInfo+ File.separator + "RNPE"+Username+ File.separator + "RNPE.info", false));
             bw1.write(Username);
             bw1.newLine();
             bw1.flush();
          } 
          catch (IOException ioe) {
             ioe.printStackTrace();
          } finally {
            if(bw1!=null){
                try{
                    bw1.close();
                }catch(IOException ioe){

                }
            }        
          } 
      }
    }
    
    public String[] GetUserList(){
        return UserList;
    }
    
    public String GetLocationPath(int iLoc){
        return LocationList[iLoc];
    }    
    
    public String GetUser(int iLoc){
        return UserList[iLoc];
    }

}
