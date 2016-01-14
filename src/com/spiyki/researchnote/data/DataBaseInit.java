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

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Properties;
import javax.swing.JTextPane;

import com.spiyki.researchnote.utils.*;
import com.spiyki.researchnote.gui.*;

/**
 * @author samitha
 */
public class DataBaseInit {
    public String[] ProjectList;
    public String[] ArticlesList;
    public int[] ArticleIndexList;

    private String framework = "embedded";
    private String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private String protocol = "jdbc:derby:";
    MainFunctions thisFunctions;
    String ThisDBName;

    public DataBaseInit(MainFunctions envMainFunctions) {
        thisFunctions = envMainFunctions;
        System.out.println("SimpleApp starting in " + framework + " mode");
        loadDriver();
        ThisDBName = thisFunctions.CurrentUserDatabaseLocation + File.separator + "RNPE" + thisFunctions.CurrentUserName + File.separator + "DB";
        //CreateTheDatabase(ThisDBName);
        //GetProjectsList();
    }

    private void loadDriver() {
        try {
            Class.forName(driver).newInstance();
            System.out.println("Loaded the appropriate driver");
        } catch (ClassNotFoundException cnfe) {
            System.err.println("\nUnable to load the JDBC driver " + driver);
            System.err.println("Please check your CLASSPATH.");
            cnfe.printStackTrace(System.err);
        } catch (InstantiationException ie) {
            System.err.println(
                    "\nUnable to instantiate the JDBC driver " + driver);
            ie.printStackTrace(System.err);
        } catch (IllegalAccessException iae) {
            System.err.println(
                    "\nNot allowed to access the JDBC driver " + driver);
            iae.printStackTrace(System.err);
        }
    }

    public void CreateTheDatabase(String DataBaseName) {
        Connection connCreate = null;             //Connection to the database
        Statement thisStat = null;                //SQL statement to execute
        try {
            Properties props = new Properties();
            props.put("user", "user1");
            props.put("password", "user1");
            String dbName = DataBaseName;       // the name of the database
            connCreate = DriverManager.getConnection(protocol + dbName + ";create=true", props);
            connCreate.setAutoCommit(false);    //Auto commit mode disabled
            //This enable executing several commands at the same time. In this case we can create all
            //the tables or create none if one statement can not execute.
            thisStat = connCreate.createStatement();
            thisStat.execute("create table Articles(" +
                    "AID int not null" +                //Article ID
                    ",Type varchar(15)" +               //Type of article
                    ",Title long varchar," +            //Title of article
                    " Author long varchar," +           //Name of authors
                    " Journal long varchar" +            //Name of publication
                    ", YoP varchar(5)" +                //Year of publication
                    ", MoP varchar(10)" +               //Month of publication
                    ", Pages varchar(15)" +             //Pages of publication
                    ", Volume varchar(4)" +             //Volume of publication
                    ", Edition varchar(4)" +            //Edition/Issue of publication
                    ", Series long varchar" +           //Series of publication
                    ", Pubs long varchar" +             //Publisher title of publication
                    ", PubsAddr long varchar" +         //Publisher Address of publication
                    ", Editors long varchar" +          //Editors of publication
                    ", DOI long varchar" +              //Doc Online Identifier of publication
                    ", Keywords long varchar" +         //Keywords of publication
                    ", Abstract long varchar" +        //Abstract of publication
                    ", PDFName long varchar" +         //Name of the pdf file created by the software (if the copy option in ticked)
                    ", PDFPath long varchar)");         //Path of the pdf file

            System.out.println("Articles tables created");
            thisStat.execute("create table Projects(" +
                    "PID int not null" +                    //Project ID
                    ", ProjName varchar(100)" +             //Name of Project
                    ", ProjDescription long varchar" +     //Description of Project
                    ", CreateDate timestamp" +              //Created date
                    ", ModifiedDate timestamp)");           //Modified date
            System.out.println("Projects table created");
            thisStat.execute("create table Notes(" +
                    "NID int not null" +                     //Note ID
                    ", NoteTitle varchar(100)" +             //Title of the note
                    ", Note long varchar" +                  //The note
                    ", CreatedDate timestamp" +              //Created date
                    ", ModifiedDate timestamp)");            //Modified date
            System.out.println("Notes table created");
            thisStat.execute("create table ProjectNotes(" +
                    "PNID int not null" +                       //Project Note ID
                    ", PID int" +                               //Project ID
                    ", NID int)");                              //Note ID
            System.out.println("Project Notes link table created");
            thisStat.execute("create table ArticleNotes(" +
                    "ANID int not null" +                       //Article Note ID
                    ", AID int" +                               //Article ID
                    ", NID int)");                              //Note ID
            System.out.println("Article Notes link table created");
            thisStat.execute("create table ProjectArticles(" +
                    "PAID int not null" +                       //Project Article ID
                    ", PID int" +                               //Project ID
                    ", AID int)");                              //Article ID
            System.out.println("Project Article link table created");
            thisStat.execute("create table ProjectArticleNotes(" +
                    "PANID int not null" +                       //Project+Article Note ID
                    ", PAID int" +                               //Project Article ID 
                    ", NID int)");                               //Note ID
            System.out.println("Project Article Notes link table created");
            connCreate.commit();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        } finally {
            if (thisStat != null) {
                try {
                    thisStat.close();
                    thisStat = null;
                } catch (SQLException sqle) {

                }
            }
            if (connCreate != null) {
                try {
                    connCreate.close();
                    connCreate = null;
                } catch (SQLException sqle) {

                }
            }
        }
    }

    public void AddProject(String pTitile, String pDescription) {
        Connection connCreate = null;             //Connection to the database
        //ArrayList statements= new ArrayList();  //List of statements
        Statement thisStat = null;                //SQL statement to execute
        PreparedStatement psInsert = null;
        ResultSet rsLastID = null;
        int PID;
        try {
            Properties props = new Properties();
            props.put("user", "user1");
            props.put("password", "user1");
            String dbName = ThisDBName;       // the name of the database
            connCreate = DriverManager.getConnection(protocol + dbName, props);
            connCreate.setAutoCommit(false);    //Auto commit mode disabled
            thisStat = connCreate.createStatement();
            psInsert = connCreate.prepareStatement("insert into Projects values (?,?,?,?,?)");
            rsLastID = thisStat.executeQuery("select max(PID) from Projects");
            if (rsLastID.next()) {
                PID = rsLastID.getInt(1) + 1;
            } else {
                PID = 1;
            }
            Timestamp ts = new Timestamp(new java.util.Date().getTime());
            psInsert.setInt(1, PID);
            psInsert.setString(2, pTitile);
            psInsert.setString(3, pDescription);
            psInsert.setTimestamp(4, ts);
            psInsert.setTimestamp(5, ts);
            psInsert.executeUpdate();
            connCreate.commit();
            System.out.println("New project details added");
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            sqle.printStackTrace();
        } finally {
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException sqle) {

                }
            }
            if (rsLastID != null) {
                try {
                    rsLastID.close();
                } catch (SQLException sqle) {

                }
            }
            if (thisStat != null) {
                try {
                    thisStat.close();
                    thisStat = null;
                } catch (SQLException sqle) {

                }
            }
            if (connCreate != null) {
                try {
                    connCreate.close();
                    connCreate = null;
                } catch (SQLException sqle) {

                }
            }
//            GetProjectsList();
        }
    }

    public void PopulateEditProjectWindow(AddProjectForm thisEditProject, int SelectedProjectID) {
        Connection connCreate = null;             //Connection to the database
        Statement psSearch1 = null;
        String[] ArticleInfo = new String[2];
        try {
            Properties props = new Properties();
            props.put("user", "user1");
            props.put("password", "user1");
            String dbName = ThisDBName;       // the name of the database
            connCreate = DriverManager.getConnection(protocol + dbName, props);
            connCreate.setAutoCommit(false);    //Auto commit mode disabled   
            psSearch1 = connCreate.createStatement();
            psSearch1.setEscapeProcessing(true);
            ResultSet rsThisArticleList = psSearch1.executeQuery("SELECT * FROM Projects WHERE PID=" + Integer.toString(SelectedProjectID));
            connCreate.commit();
            if (rsThisArticleList.next()) {
                for (int i = 0; i < 2; i++) {
                    ArticleInfo[i] = rsThisArticleList.getString(i + 2);
                }
            }
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            sqle.printStackTrace();
        } finally {
            if (psSearch1 != null) {
                try {
                    psSearch1.close();
                    psSearch1 = null;
                } catch (SQLException sqle) {

                }
            }
            if (connCreate != null) {
                try {
                    connCreate.close();
                    connCreate = null;
                } catch (SQLException sqle) {

                }
            }
            thisEditProject.PopulateProjectDetils(SelectedProjectID);
        }
    }

    public void UpdateProject(int PID, String pTitile, String pDescription) {
        Connection connCreate = null;             //Connection to the database
        //ArrayList statements= new ArrayList();  //List of statements
        PreparedStatement psInsert = null;
        try {
            Properties props = new Properties();
            props.put("user", "user1");
            props.put("password", "user1");
            String dbName = ThisDBName;       // the name of the database
            connCreate = DriverManager.getConnection(protocol + dbName, props);
            connCreate.setAutoCommit(false);    //Auto commit mode disabled
            psInsert = connCreate.prepareStatement("update Projects set ProjName=?,ProjDescription=?,ModifiedDate=? where PID=?");
            Timestamp ts = new Timestamp(new java.util.Date().getTime());
            psInsert.setInt(4, PID);
            psInsert.setString(1, pTitile);
            psInsert.setString(2, pDescription);
            psInsert.setTimestamp(3, ts);
            psInsert.executeUpdate();
            connCreate.commit();
            System.out.println("Project details updated");
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        } finally {
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException sqle) {

                }
            }
            if (connCreate != null) {
                try {
                    connCreate.close();
                    connCreate = null;
                } catch (SQLException sqle) {

                }
            }
//            GetProjectsList();
        }
    }

    public ArrayList GetProjectsList() {
        ArrayList ProjectItems = new ArrayList();
        Connection connCreate = null;             //Connection to the database
        Statement thisStat = null;                //SQL statement to execute
        ResultSet rsLastID = null;
        int i = 0;
//        String[] thisProjectList;
        try {
            Properties props = new Properties();
            props.put("user", "user1");
            props.put("password", "user1");
            String dbName = ThisDBName;       // the name of the database
            connCreate = DriverManager.getConnection(protocol + dbName, props);
            connCreate.setAutoCommit(false);    //Auto commit mode disabled
            thisStat = connCreate.createStatement();
            rsLastID = thisStat.executeQuery("select PID,ProjName from Projects");

            while (rsLastID.next()) {
                ArrayList thisPDetails = new ArrayList();
                thisPDetails.add(rsLastID.getInt(1));
                thisPDetails.add(rsLastID.getString(2));
                ProjectItems.add(thisPDetails);
                i++;
            }
            connCreate.commit();
//            ProjectList=new String[i];
//            for(int j=0;j<i;j++){
//                ProjectList[j]=(String)ProjectItems.get(j);
//            }            
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        } finally {
            if (rsLastID != null) {
                try {
                    rsLastID.close();
                } catch (SQLException sqle) {

                }
            }
            if (thisStat != null) {
                try {
                    thisStat.close();
                    thisStat = null;
                } catch (SQLException sqle) {

                }
            }
            if (connCreate != null) {
                try {
                    connCreate.close();
                    connCreate = null;
                } catch (SQLException sqle) {

                }
            }
        }
        return ProjectItems;
    }

    public ArrayList GetProjectsUsingTheArticle(int AID) {
        ArrayList ProjectItems = new ArrayList();
        Connection connCreate = null;             //Connection to the database
        Statement thisStat = null;                //SQL statement to execute
        ResultSet rsLastID = null;
        int i = 0;
//        String[] thisProjectList;
        try {
            Properties props = new Properties();
            props.put("user", "user1");
            props.put("password", "user1");
            String dbName = ThisDBName;       // the name of the database
            connCreate = DriverManager.getConnection(protocol + dbName, props);
            connCreate.setAutoCommit(false);    //Auto commit mode disabled
            thisStat = connCreate.createStatement();
            rsLastID = thisStat.executeQuery("select PID,ProjName from Projects where PID in (select PID from ProjectArticles where AID=" + Integer.toString(AID) + ")");

            while (rsLastID.next()) {
                ArrayList thisPDetails = new ArrayList();
                thisPDetails.add(rsLastID.getInt(1));
                thisPDetails.add(rsLastID.getString(2));
                ProjectItems.add(thisPDetails);
                i++;
            }
            connCreate.commit();
//            ProjectList=new String[i];
//            for(int j=0;j<i;j++){
//                ProjectList[j]=(String)ProjectItems.get(j);
//            }            
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        } finally {
            if (rsLastID != null) {
                try {
                    rsLastID.close();
                } catch (SQLException sqle) {

                }
            }
            if (thisStat != null) {
                try {
                    thisStat.close();
                    thisStat = null;
                } catch (SQLException sqle) {

                }
            }
            if (connCreate != null) {
                try {
                    connCreate.close();
                    connCreate = null;
                } catch (SQLException sqle) {

                }
            }
        }
        return ProjectItems;
    }

    public String[] GetProjectDescription(int PID) {
        Connection connCreate = null;             //Connection to the database
        Statement thisStat = null;                //SQL statement to execute
        ResultSet rsLastID = null;
        String[] strResult = new String[4];
        try {
            Properties props = new Properties();
            props.put("user", "user1");
            props.put("password", "user1");
            String dbName = ThisDBName;       // the name of the database
            connCreate = DriverManager.getConnection(protocol + dbName, props);
            connCreate.setAutoCommit(false);    //Auto commit mode disabled
            thisStat = connCreate.createStatement();
            rsLastID = thisStat.executeQuery("select * from Projects where PID=" + Integer.toString(PID));
            if (rsLastID.next()) {
                strResult[0] = rsLastID.getString(2);
                strResult[1] = rsLastID.getString(3);
                strResult[2] = rsLastID.getTimestamp(4).toString();
                strResult[3] = rsLastID.getTimestamp(5).toString();
            }
            connCreate.commit();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        } finally {
            if (rsLastID != null) {
                try {
                    rsLastID.close();
                } catch (SQLException sqle) {

                }
            }
            if (thisStat != null) {
                try {
                    thisStat.close();
                    thisStat = null;
                } catch (SQLException sqle) {

                }
            }
            if (connCreate != null) {
                try {
                    connCreate.close();
                    connCreate = null;
                } catch (SQLException sqle) {

                }
            }
        }
        return strResult;
    }

    public void AddArticle(String aType, String aTitle, String aAuthors, String aJournal, String aYoP, String aMop
            , String aPP, String aVol, String aIss, String aSer, String aPubs, String aPubsAddr
            , String aEds, String aDOI, String aKeys, String aAbs, String aPDF, String aPATH, Boolean AddToProject) {
        Connection connCreate = null;             //Connection to the database
        Statement thisStat = null;                //SQL statement to execute
        PreparedStatement psInsert = null;
        PreparedStatement psInsertPA = null;    //Insert the project article
        int AID;
        try {
            Properties props = new Properties();
            props.put("user", "user1");
            props.put("password", "user1");
            String dbName = ThisDBName;       // the name of the database
            connCreate = DriverManager.getConnection(protocol + dbName, props);
            connCreate.setAutoCommit(false);    //Auto commit mode disabled
            thisStat = connCreate.createStatement();
            ResultSet rsLastID = thisStat.executeQuery("select max(AID) from Articles");
//            ResultSet rsLastID=thisStat.executeQuery("select max(PID) from Projects");
            if (rsLastID.next()) {
                AID = rsLastID.getInt(1) + 1;
            } else {
                AID = 1;
            }
            psInsert = connCreate.prepareStatement("insert into Articles values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            psInsert.setInt(1, AID);
            psInsert.setString(2, aType);
            psInsert.setString(3, aTitle);
            psInsert.setString(4, aAuthors);
            psInsert.setString(5, aJournal);
            psInsert.setString(6, aYoP);
            psInsert.setString(7, aMop);
            psInsert.setString(8, aPP);
            psInsert.setString(9, aVol);
            psInsert.setString(10, aIss);
            psInsert.setString(11, aSer);
            psInsert.setString(12, aPubs);
            psInsert.setString(13, aPubsAddr);
            psInsert.setString(14, aEds);
            psInsert.setString(15, aDOI);
            psInsert.setString(16, aKeys);
            psInsert.setString(17, aAbs);
            psInsert.setString(18, aPDF);
            psInsert.setString(19, aPATH);
            psInsert.executeUpdate();
            if (AddToProject & thisFunctions.CurrentPID != 0) {
                psInsertPA = connCreate.prepareStatement("insert into ProjectArticles values (?,?,?)");
                ResultSet rsLastPAID = thisStat.executeQuery("select max(PAID) from ProjectArticles");
                int PAID;
                if (rsLastPAID.next()) {
                    PAID = rsLastPAID.getInt(1) + 1;
                } else {
                    PAID = 1;
                }
                psInsertPA.setInt(1, PAID);
                psInsertPA.setInt(2, thisFunctions.CurrentPID);
                psInsertPA.setInt(3, AID);
                psInsertPA.executeUpdate();
            }
            connCreate.commit();
//            System.out.println("New article details added");  
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            sqle.printStackTrace();
        } finally {
            if (thisStat != null) {
                try {
                    thisStat.close();
                    thisStat = null;
                } catch (SQLException sqle) {

                }
            }
            if (connCreate != null) {
                try {
                    connCreate.close();
                    connCreate = null;
                } catch (SQLException sqle) {

                }
            }
        }
    }

    public void AddRemoveArticleFromCurrentProject(int AID, Boolean AddToProject) {
        Connection connCreate = null;             //Connection to the database
        Statement thisStat = null;                //SQL statement to execute
        PreparedStatement psInsert = null;
        PreparedStatement psInsertPA = null;    //Insert the project article
        try {
            Properties props = new Properties();
            props.put("user", "user1");
            props.put("password", "user1");
            String dbName = ThisDBName;       // the name of the database
            connCreate = DriverManager.getConnection(protocol + dbName, props);
            connCreate.setAutoCommit(false);    //Auto commit mode disabled
            thisStat = connCreate.createStatement();
            if (AddToProject & thisFunctions.CurrentPID != 0) {     //Add to PA
                psInsertPA = connCreate.prepareStatement("insert into ProjectArticles values (?,?,?)");
                ResultSet rsLastPAID = thisStat.executeQuery("select max(PAID) from ProjectArticles");
                int PAID;
                if (rsLastPAID.next()) {
                    PAID = rsLastPAID.getInt(1) + 1;
                } else {
                    PAID = 1;
                }
                psInsertPA.setInt(1, PAID);
                psInsertPA.setInt(2, thisFunctions.CurrentPID);
                psInsertPA.setInt(3, AID);
                psInsertPA.executeUpdate();
            }
            if (!AddToProject & thisFunctions.CurrentPID != 0) {    //Remove from PA
                int PID = thisFunctions.CurrentPID;
                ResultSet rsThisPAID = thisStat.executeQuery("select PAID from ProjectArticles where AID=" + Integer.toString(AID) + " and PID=" + Integer.toString(PID));
                if (rsThisPAID.next()) { //The article is in the current project
                    int PAID = rsThisPAID.getInt(1);
                    thisStat.executeUpdate("delete from ProjectArticles where PAID=" + Integer.toString(PAID));
                } else { //The article is not in the current project

                }
            }
            connCreate.commit();
//            System.out.println("Article sucessfully added/removed from the current project");  
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            sqle.printStackTrace();
        } finally {
            if (thisStat != null) {
                try {
                    thisStat.close();
                    thisStat = null;
                } catch (SQLException sqle) {

                }
            }
            if (connCreate != null) {
                try {
                    connCreate.close();
                    connCreate = null;
                } catch (SQLException sqle) {

                }
            }
        }
    }

    public void SearchForArticle(String strSearchWord1, int iSearchPara1, int iSearchLink1
            , String strSearchWord2, int iSearchPara2, int iSearchLink2
            , String strSearchWord3, int iSearchPara3
            , Boolean bThisProjectOnly) {

        String strSQLsearch1 = "";
        String strSQLsearch2 = "";
        String strSQLsearch3 = "";
        String[] strSearchLink = {"OR", "AND"};
        String strSQLfinal = "";
        Connection connCreate = null;             //Connection to the database
//        PreparedStatement psSearch1=null;
        Statement psSearch1 = null;
        strSearchWord1 = strSearchWord1.replace("'", "_");
        strSearchWord2 = strSearchWord2.replace("'", "_");
        strSearchWord3 = strSearchWord3.replace("'", "_");
        if (strSearchWord1.compareTo("") != 0) {
            strSearchWord1 = "%" + strSearchWord1 + "%";
        }
        if (strSearchWord2.compareTo("") != 0) {
            strSearchWord2 = "%" + strSearchWord2 + "%";
        }
        if (strSearchWord3.compareTo("") != 0) {
            strSearchWord3 = "%" + strSearchWord3 + "%";
        }
        try {
            if (strSearchWord1.compareTo("") != 0) {
                switch (iSearchPara1) {
                    case 0:
                        strSQLsearch1 = "SELECT DISTINCT AID FROM Articles WHERE" +
                                " Type LIKE '" + strSearchWord1 +
                                "' OR Title LIKE '" + strSearchWord1 +
                                "' OR Author LIKE '" + strSearchWord1 +
                                "' OR Journal LIKE '" + strSearchWord1 +
                                "' OR Series LIKE '" + strSearchWord1 +
                                "' OR Pubs LIKE '" + strSearchWord1 +
                                "' OR Editors LIKE '" + strSearchWord1 +
                                "' OR Keywords LIKE '" + strSearchWord1 +
                                "' OR Abstract LIKE '" + strSearchWord1 + "'";
                        break;
                    case 1:
                        strSQLsearch1 = "SELECT DISTINCT AID FROM Articles WHERE" +
                                " Title LIKE '" + strSearchWord1 + "'";
                        break;
                    case 2:
                        strSQLsearch1 = "SELECT DISTINCT AID FROM Articles WHERE" +
                                " Author LIKE '" + strSearchWord1 + "'";
                        break;
                    case 3:
                        strSQLsearch1 = "SELECT DISTINCT AID FROM Articles WHERE" +
                                " Keywords LIKE '" + strSearchWord1 + "'";
                        break;
                    default:
                        break;
                }
            }
            if (strSearchWord2.compareTo("") != 0) {
                switch (iSearchPara2) {
                    case 0:
                        strSQLsearch2 = "SELECT DISTINCT AID FROM Articles WHERE" +
                                " Type LIKE '" + strSearchWord2 +
                                "' OR Title LIKE '" + strSearchWord2 +
                                "' OR Author LIKE '" + strSearchWord2 +
                                "' OR Journal LIKE '" + strSearchWord2 +
                                "' OR Series LIKE '" + strSearchWord2 +
                                "' OR Pubs LIKE '" + strSearchWord2 +
                                "' OR Editors LIKE '" + strSearchWord2 +
                                "' OR Keywords LIKE '" + strSearchWord2 +
                                "' OR Abstract LIKE '" + strSearchWord2 + "'";
                        break;
                    case 1:
                        strSQLsearch2 = "SELECT DISTINCT AID FROM Articles WHERE" +
                                " Title LIKE '" + strSearchWord2 + "'";
                        break;
                    case 2:
                        strSQLsearch2 = "SELECT DISTINCT AID FROM Articles WHERE" +
                                " Author LIKE '" + strSearchWord2 + "'";
                        break;
                    case 3:
                        strSQLsearch2 = "SELECT DISTINCT AID FROM Articles WHERE" +
                                " Keywords LIKE '" + strSearchWord2 + "'";
                        break;
                }
            }
            if (strSearchWord3.compareTo("") != 0) {
                switch (iSearchPara3) {
                    case 0:
                        strSQLsearch3 = "SELECT DISTINCT AID FROM Articles WHERE" +
                                " Type LIKE '" + strSearchWord3 +
                                "' OR Title LIKE '" + strSearchWord3 +
                                "' OR Author LIKE '" + strSearchWord3 +
                                "' OR Journal LIKE '" + strSearchWord3 +
                                "' OR Series LIKE '" + strSearchWord3 +
                                "' OR Pubs LIKE '" + strSearchWord3 +
                                "' OR Editors LIKE '" + strSearchWord3 +
                                "' OR Keywords LIKE '" + strSearchWord3 +
                                "' OR Abstract LIKE '" + strSearchWord3 + "'";
                        break;
                    case 1:
                        strSQLsearch3 = "SELECT DISTINCT AID FROM Articles WHERE" +
                                " Title LIKE '" + strSearchWord3 + "'";
                        break;
                    case 2:
                        strSQLsearch3 = "SELECT DISTINCT AID FROM Articles WHERE" +
                                " Author LIKE '" + strSearchWord3 + "'";
                        break;
                    case 3:
                        strSQLsearch3 = "SELECT DISTINCT AID FROM Articles WHERE" +
                                " Keywords LIKE '" + strSearchWord3 + "'";
                        break;
                }
            }
            Properties props = new Properties();
            props.put("user", "user1");
            props.put("password", "user1");
            String dbName = ThisDBName;       // the name of the database
            connCreate = DriverManager.getConnection(protocol + dbName, props);
            connCreate.setAutoCommit(false);    //Auto commit mode disabled
            if (strSQLsearch1.compareTo("") != 0 & strSQLsearch2.compareTo("") != 0 & strSQLsearch3.compareTo("") != 0) {
                if (bThisProjectOnly) {
                    strSQLfinal = "SELECT * FROM Articles WHERE AID IN (" + strSQLsearch1 + ") " + strSearchLink[iSearchLink1] + " AID IN (" + strSQLsearch2 + ") " + strSearchLink[iSearchLink2] + " AID IN (" + strSQLsearch3 + ") AND AID IN (SELECT AID FROM ProjectArticles WHERE PID =" + Integer.toString(thisFunctions.CurrentPID) + ")";

                } else {
                    strSQLfinal = "SELECT * FROM Articles WHERE AID IN (" + strSQLsearch1 + ") " + strSearchLink[iSearchLink1] + " AID IN (" + strSQLsearch2 + ") " + strSearchLink[iSearchLink2] + " AID IN (" + strSQLsearch3 + ")";

                }
            }
            if (strSQLsearch1.compareTo("") != 0 & strSQLsearch2.compareTo("") != 0 & strSQLsearch3.compareTo("") == 0) {
                if (bThisProjectOnly) {
                    strSQLfinal = "SELECT * FROM Articles WHERE AID IN (" + strSQLsearch1 + ") " + strSearchLink[iSearchLink1] + " AID IN (" + strSQLsearch2 + ") AND AID IN (SELECT AID FROM ProjectArticles WHERE PID =" + Integer.toString(thisFunctions.CurrentPID) + ")";

                } else {
                    strSQLfinal = "SELECT * FROM Articles WHERE AID IN (" + strSQLsearch1 + ") " + strSearchLink[iSearchLink1] + " AID IN (" + strSQLsearch2 + ")";

                }
            }
            if (strSQLsearch1.compareTo("") != 0 & strSQLsearch2.compareTo("") == 0 & strSQLsearch3.compareTo("") == 0) {
                if (bThisProjectOnly) {
                    strSQLfinal = "SELECT * FROM Articles WHERE AID IN (" + strSQLsearch1 + ") AND AID IN (SELECT AID FROM ProjectArticles WHERE PID =" + Integer.toString(thisFunctions.CurrentPID) + ")";

                } else {
                    strSQLfinal = "SELECT * FROM Articles WHERE AID IN (" + strSQLsearch1 + ")";

                }
            }
            if (strSQLsearch1.compareTo("") == 0 & strSQLsearch2.compareTo("") == 0 & strSQLsearch3.compareTo("") == 0) {
                if (bThisProjectOnly) {
                    strSQLfinal = "SELECT * FROM Articles WHERE AID IN (SELECT AID FROM ProjectArticles WHERE PID =" + Integer.toString(thisFunctions.CurrentPID) + ")";

                } else {
                    strSQLfinal = "SELECT * FROM Articles";

                }
            }
            psSearch1 = connCreate.createStatement();
            psSearch1.setEscapeProcessing(true);
            ResultSet rsThisArticleList = psSearch1.executeQuery(strSQLfinal);
            connCreate.commit();
            ArrayList alThisResultsStr = new ArrayList();
            ArrayList alThisResultsInt = new ArrayList();
            int iRecCnt = 0;
            String strTemp = "";
            int iTemp = 0;
            ArticleDetails thisDetails = new ArticleDetails();
            while (rsThisArticleList.next()) {
                iTemp = rsThisArticleList.getInt(1);
                String[] strArtDetails = new String[17];
                for (int iA = 2; iA < 17; iA++) {
                    strArtDetails[iA - 2] = rsThisArticleList.getString(iA);
                }
                JTextPane dff = new JTextPane();
                DisplayInHTMLformat disp = new DisplayInHTMLformat(rsThisArticleList.getString(3), "test", dff);
                strArtDetails[1] = disp.GetThePlainText();
                disp = new DisplayInHTMLformat(rsThisArticleList.getString(5), "test", dff);
                strArtDetails[3] = disp.GetThePlainText();
                disp = new DisplayInHTMLformat(rsThisArticleList.getString(11), "test", dff);
                strArtDetails[9] = disp.GetThePlainText();
                strTemp = thisDetails.GetTheFormattedReference(strArtDetails);
//                strTemp=rsThisArticleList.getString(4)+",''"+rsThisArticleList.getString(3)+"'', "+
//                            rsThisArticleList.getString(5)+" "+rsThisArticleList.getString(8)+" "+rsThisArticleList.getString(9)+" "+rsThisArticleList.getString(10)+
//                            ", "+rsThisArticleList.getString(13)+", "+rsThisArticleList.getString(11)+", "+rsThisArticleList.getString(7)
//                            +" "+rsThisArticleList.getString(6);
                alThisResultsInt.add(iTemp);
                alThisResultsStr.add(strTemp);
                iRecCnt++;
            }
            String[] strCurrentResults = new String[iRecCnt];
            int[] iArticleIndexes = new int[iRecCnt];
            for (int i = 0; i < iRecCnt; i++) {
                iArticleIndexes[i] = (Integer) alThisResultsInt.get(i);
                strCurrentResults[i] = (String) alThisResultsStr.get(i);
            }
            ArticlesList = strCurrentResults;
            ArticleIndexList = iArticleIndexes;
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            sqle.printStackTrace();
        } finally {
            if (psSearch1 != null) {
                try {
                    psSearch1.close();
                    psSearch1 = null;
                } catch (SQLException sqle) {

                }
            }
            if (connCreate != null) {
                try {
                    connCreate.close();
                    connCreate = null;
                } catch (SQLException sqle) {

                }
            }
        }
    }

    public ArrayList GetArticlesForTheProject(int PID) {
        Connection connCreate = null;             //Connection to the database
        Statement psSearch1 = null;
        ArrayList theseArticles = new ArrayList();
        try {
            Properties props = new Properties();
            props.put("user", "user1");
            props.put("password", "user1");
            String dbName = ThisDBName;       // the name of the database
            connCreate = DriverManager.getConnection(protocol + dbName, props);
            connCreate.setAutoCommit(false);    //Auto commit mode disabled   
            psSearch1 = connCreate.createStatement();
            psSearch1.setEscapeProcessing(true);
            ResultSet rsThisArticleList = psSearch1.executeQuery("SELECT * FROM Articles WHERE AID IN (SELECT AID FROM ProjectArticles WHERE PID =" + Integer.toString(PID) + ")");
            connCreate.commit();
            while (rsThisArticleList.next()) {
                String[] ArticleInfo = new String[16];
                for (int i = 0; i < 15; i++) {
                    ArticleInfo[i] = rsThisArticleList.getString(i + 2);
                }
                ArticleInfo[15] = Integer.toString(rsThisArticleList.getInt(1));
                theseArticles.add(ArticleInfo);
            }
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            sqle.printStackTrace();
        } finally {
            if (psSearch1 != null) {
                try {
                    psSearch1.close();
                    psSearch1 = null;
                } catch (SQLException sqle) {

                }
            }
            if (connCreate != null) {
                try {
                    connCreate.close();
                    connCreate = null;
                } catch (SQLException sqle) {

                }
            }
        }
        return theseArticles;
    }

    public ArrayList GetArticlesForTheProjectInPlainText(int PID) {
        Connection connCreate = null;             //Connection to the database
        Statement psSearch1 = null;
        ArrayList theseArticles = new ArrayList();
        try {
            Properties props = new Properties();
            props.put("user", "user1");
            props.put("password", "user1");
            String dbName = ThisDBName;       // the name of the database
            connCreate = DriverManager.getConnection(protocol + dbName, props);
            connCreate.setAutoCommit(false);    //Auto commit mode disabled   
            psSearch1 = connCreate.createStatement();
            psSearch1.setEscapeProcessing(true);
            ResultSet rsThisArticleList = psSearch1.executeQuery("SELECT * FROM Articles WHERE AID IN (SELECT AID FROM ProjectArticles WHERE PID =" + Integer.toString(PID) + ")");
            connCreate.commit();
            while (rsThisArticleList.next()) {
                String[] ArticleInfo = new String[16];
                for (int i = 0; i < 15; i++) {
                    ArticleInfo[i] = rsThisArticleList.getString(i + 2);
                }
                JTextPane dff = new JTextPane();
                DisplayInHTMLformat disp = new DisplayInHTMLformat(rsThisArticleList.getString(3), "test", dff);
                ArticleInfo[1] = disp.GetThePlainText();
                disp = new DisplayInHTMLformat(rsThisArticleList.getString(5), "test", dff);
                ArticleInfo[3] = disp.GetThePlainText();
                disp = new DisplayInHTMLformat(rsThisArticleList.getString(11), "test", dff);
                ArticleInfo[9] = disp.GetThePlainText();
                ArticleInfo[15] = Integer.toString(rsThisArticleList.getInt(1));
                theseArticles.add(ArticleInfo);
            }
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            sqle.printStackTrace();
        } finally {
            if (psSearch1 != null) {
                try {
                    psSearch1.close();
                    psSearch1 = null;
                } catch (SQLException sqle) {

                }
            }
            if (connCreate != null) {
                try {
                    connCreate.close();
                    connCreate = null;
                } catch (SQLException sqle) {

                }
            }
        }
        return theseArticles;
    }

    public void PopulateEditArticleWindow(AddArticleForm thisEditArticle, int CurrentArticleID) {
        Connection connCreate = null;             //Connection to the database
        Statement psSearch1 = null;
        Statement thisStat = null;                //SQL statement to execute
        String[] ArticleInfo = new String[18];
        Boolean bIsCurrentProject = false;
        try {
            Properties props = new Properties();
            props.put("user", "user1");
            props.put("password", "user1");
            String dbName = ThisDBName;       // the name of the database
            connCreate = DriverManager.getConnection(protocol + dbName, props);
            connCreate.setAutoCommit(false);    //Auto commit mode disabled   
            psSearch1 = connCreate.createStatement();
            psSearch1.setEscapeProcessing(true);
            ResultSet rsThisArticleList = psSearch1.executeQuery("SELECT * FROM Articles WHERE AID=" + Integer.toString(CurrentArticleID));
            connCreate.commit();
            if (rsThisArticleList.next()) {
                for (int i = 0; i < 18; i++) {
                    ArticleInfo[i] = rsThisArticleList.getString(i + 2);
                }
            }
            thisStat = connCreate.createStatement();
            ResultSet rsTemp = thisStat.executeQuery("select * from ProjectArticles where AID=" + Integer.toString(CurrentArticleID) + "and PID=" + Integer.toString(thisFunctions.CurrentPID));
            if (rsTemp.next()) {
                bIsCurrentProject = true;
            } else {
                bIsCurrentProject = false;
            }
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            sqle.printStackTrace();
        } finally {
            if (psSearch1 != null) {
                try {
                    psSearch1.close();
                    psSearch1 = null;
                } catch (SQLException sqle) {

                }
            }
            if (connCreate != null) {
                try {
                    connCreate.close();
                    connCreate = null;
                } catch (SQLException sqle) {

                }
            }
            thisEditArticle.PopulateArticleDetails(CurrentArticleID, ArticleInfo, bIsCurrentProject);
        }
    }

    public void UpdateArticleRecord(int AID, String aType, String aTitle, String aAuthors, String aJournal, String aYoP, String aMop
            , String aPP, String aVol, String aIss, String aSer, String aPubs, String aPubsAddr
            , String aEds, String aDOI, String aKeys, String aAbs, String aPDF, String aPATH, Boolean AddToProject) {
        Connection connCreate = null;             //Connection to the database
        Statement thisStat = null;                //SQL statement to execute
        PreparedStatement psInsert = null;
        PreparedStatement psInsertPA = null;    //Insert the project article
        try {
            Properties props = new Properties();
            props.put("user", "user1");
            props.put("password", "user1");
            String dbName = ThisDBName;       // the name of the database
            connCreate = DriverManager.getConnection(protocol + dbName, props);
            connCreate.setAutoCommit(false);    //Auto commit mode disabled
            psInsert = connCreate.prepareStatement("update Articles set Type=?,Title=?,Author=?," +
                    "Journal=?,YoP=?,MoP=?,Pages=?,Volume=?,Edition=?,Series=?,Pubs=?,PubsAddr=?,Editors=?,DOI=?,Keywords=?,Abstract=?,PDFName=?,PDFPath=? where AID=?");
            psInsert.setInt(19, AID);
            psInsert.setString(1, aType);
            psInsert.setString(2, aTitle);
            psInsert.setString(3, aAuthors);
            psInsert.setString(4, aJournal);
            psInsert.setString(5, aYoP);
            psInsert.setString(6, aMop);
            psInsert.setString(7, aPP);
            psInsert.setString(8, aVol);
            psInsert.setString(9, aIss);
            psInsert.setString(10, aSer);
            psInsert.setString(11, aPubs);
            psInsert.setString(12, aPubsAddr);
            psInsert.setString(13, aEds);
            psInsert.setString(14, aDOI);
            psInsert.setString(15, aKeys);
            psInsert.setString(16, aAbs);
            psInsert.setString(17, aPDF);
            psInsert.setString(18, aPATH);
            psInsert.executeUpdate();
            if (AddToProject & thisFunctions.CurrentPID != 0) {
                thisStat = connCreate.createStatement();
                ResultSet rsTemp = thisStat.executeQuery("select * from ProjectArticles where AID=" + Integer.toString(AID) + "and PID=" + Integer.toString(thisFunctions.CurrentPID));
                if (rsTemp.next()) {
                    //The record already exists
                } else {
                    //Insert the record
                    psInsertPA = connCreate.prepareStatement("insert into ProjectArticles values (?,?,?)");
                    ResultSet rsLastPAID = thisStat.executeQuery("select max(PAID) from ProjectArticles");
                    int PAID;
                    if (rsLastPAID.next()) {
                        PAID = rsLastPAID.getInt(1) + 1;
                    } else {
                        PAID = 1;
                    }
                    psInsertPA.setInt(1, PAID);
                    psInsertPA.setInt(2, thisFunctions.CurrentPID);
                    psInsertPA.setInt(3, AID);
                    psInsertPA.executeUpdate();
                }

            } else {
                thisStat = connCreate.createStatement();
                ResultSet rsTemp = thisStat.executeQuery("select * from ProjectArticles where AID=" + Integer.toString(AID) + "and PID=" + Integer.toString(thisFunctions.CurrentPID));
                if (rsTemp.next()) {
                    //The record already exists, delete it
                    int PAID = rsTemp.getInt(1);
                    thisStat.execute("delete from ProjectArticles where PAID=" + Integer.toString(PAID));
                } else {
                    //The record does not exists.
                    psInsertPA = connCreate.prepareStatement("insert into ProjectArticles values (?,?,?)");
                    ResultSet rsLastPAID = thisStat.executeQuery("select max(PAID) from ProjectArticles");
                    int PAID;
                    if (rsLastPAID.next()) {
                        PAID = rsLastPAID.getInt(1) + 1;
                    } else {
                        PAID = 1;
                    }
                    psInsertPA.setInt(1, PAID);
                    psInsertPA.setInt(2, thisFunctions.CurrentPID);
                    psInsertPA.setInt(3, AID);
                    psInsertPA.executeUpdate();
                }
            }
            connCreate.commit();
            System.out.println("New article details added");
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            sqle.printStackTrace();
        } finally {
            if (thisStat != null) {
                try {
                    thisStat.close();
                    thisStat = null;
                } catch (SQLException sqle) {

                }
            }
            if (connCreate != null) {
                try {
                    connCreate.close();
                    connCreate = null;
                } catch (SQLException sqle) {

                }
            }
        }
    }

    public ArrayList GetArticleInformation(int AID) {
        Connection connCreate = null;             //Connection to the database
        Statement psSearch1 = null;
        Statement thisStat = null;                //SQL statement to execute
        ArrayList thisArticleInfo = new ArrayList();
        String[] ArticleInfo = new String[18];
        Boolean bIsCurrentProject = false;
        try {
            Properties props = new Properties();
            props.put("user", "user1");
            props.put("password", "user1");
            String dbName = ThisDBName;       // the name of the database
            connCreate = DriverManager.getConnection(protocol + dbName, props);
            connCreate.setAutoCommit(false);    //Auto commit mode disabled   
            psSearch1 = connCreate.createStatement();
            psSearch1.setEscapeProcessing(true);
            ResultSet rsThisArticleList = psSearch1.executeQuery("SELECT * FROM Articles WHERE AID=" + Integer.toString(AID));
            connCreate.commit();
            if (rsThisArticleList.next()) {
                for (int i = 0; i < 18; i++) {
                    ArticleInfo[i] = rsThisArticleList.getString(i + 2);
                }
            }
            thisStat = connCreate.createStatement();
            ResultSet rsTemp = thisStat.executeQuery("select * from ProjectArticles where AID=" + Integer.toString(AID) + "and PID=" + Integer.toString(thisFunctions.CurrentPID));
            if (rsTemp.next()) {
                bIsCurrentProject = true;
            } else {
                bIsCurrentProject = false;
            }
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            sqle.printStackTrace();
        } finally {
            if (psSearch1 != null) {
                try {
                    psSearch1.close();
                    psSearch1 = null;
                } catch (SQLException sqle) {

                }
            }
            if (connCreate != null) {
                try {
                    connCreate.close();
                    connCreate = null;
                } catch (SQLException sqle) {

                }
            }
            thisArticleInfo.add(AID);
            thisArticleInfo.add(ArticleInfo);
            thisArticleInfo.add(bIsCurrentProject);
            return thisArticleInfo;
        }
    }

    public ArrayList GenerateNoteListForArticles(int AID, Boolean bThisProjectOnly) {
        Connection connCreate = null;             //Connection to the database
        Statement psSearch1 = null;
        ArrayList thisNoteSet = new ArrayList();
        try {
            Properties props = new Properties();
            props.put("user", "user1");
            props.put("password", "user1");
            String dbName = ThisDBName;       // the name of the database
            connCreate = DriverManager.getConnection(protocol + dbName, props);
            connCreate.setAutoCommit(false);    //Auto commit mode disabled   
            psSearch1 = connCreate.createStatement();
            psSearch1.setEscapeProcessing(true);
            ResultSet rsThisArticleList = psSearch1.executeQuery("SELECT * FROM Notes WHERE NID IN (SELECT DISTINCT NID FROM ArticleNotes WHERE AID=" + Integer.toString(AID) + ")");
            connCreate.commit();
            while (rsThisArticleList.next()) {
                ArrayList thisNoteDetails = new ArrayList();
                thisNoteDetails.add(rsThisArticleList.getInt(1));
                thisNoteDetails.add(rsThisArticleList.getString(2));
                thisNoteDetails.add(rsThisArticleList.getString(3));
                thisNoteDetails.add(rsThisArticleList.getTimestamp(4).toString());
                thisNoteDetails.add(rsThisArticleList.getTimestamp(5).toString());
                thisNoteSet.add(thisNoteDetails);
            }
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            sqle.printStackTrace();
        } finally {
            if (psSearch1 != null) {
                try {
                    psSearch1.close();
                    psSearch1 = null;
                } catch (SQLException sqle) {

                }
            }
            if (connCreate != null) {
                try {
                    connCreate.close();
                    connCreate = null;
                } catch (SQLException sqle) {

                }
            }
//            thisNoteWindow.PopulateNotes(thisNoteSet);
            return thisNoteSet;
        }
    }

    public ArrayList GenerateNoteListForProjects(int PID) {
        Connection connCreate = null;             //Connection to the database
        Statement psSearch1 = null;
        ArrayList thisNoteSet = new ArrayList();
        try {
            Properties props = new Properties();
            props.put("user", "user1");
            props.put("password", "user1");
            String dbName = ThisDBName;       // the name of the database
            connCreate = DriverManager.getConnection(protocol + dbName, props);
            connCreate.setAutoCommit(false);    //Auto commit mode disabled   
            psSearch1 = connCreate.createStatement();
            psSearch1.setEscapeProcessing(true);
            ResultSet rsThisArticleList = psSearch1.executeQuery("SELECT * FROM Notes WHERE NID IN (SELECT DISTINCT NID FROM ProjectNotes WHERE PID=" + Integer.toString(PID) + ")");
            connCreate.commit();
            while (rsThisArticleList.next()) {
                ArrayList thisNoteDetails = new ArrayList();
                thisNoteDetails.add(rsThisArticleList.getInt(1));
                thisNoteDetails.add(rsThisArticleList.getString(2));
                thisNoteDetails.add(rsThisArticleList.getString(3));
                thisNoteDetails.add(rsThisArticleList.getTimestamp(4).toString());
                thisNoteDetails.add(rsThisArticleList.getTimestamp(5).toString());
                thisNoteSet.add(thisNoteDetails);
            }
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            sqle.printStackTrace();
        } finally {
            if (psSearch1 != null) {
                try {
                    psSearch1.close();
                    psSearch1 = null;
                } catch (SQLException sqle) {

                }
            }
            if (connCreate != null) {
                try {
                    connCreate.close();
                    connCreate = null;
                } catch (SQLException sqle) {

                }
            }
//            thisNoteWindow.PopulateNotes(thisNoteSet);
            return thisNoteSet;
        }
    }

    public void AddNewNote(String nTitile, String nDescription, int iItemID, Boolean bProjectNote) {
        Connection connCreate = null;             //Connection to the database
        //ArrayList statements= new ArrayList();  //List of statements
        Statement thisStat = null;                //SQL statement to execute
        PreparedStatement psInsertNote = null;
        PreparedStatement psInsertNoteRef = null;
        ResultSet rsLastID = null;
        int NID;
        try {
            Properties props = new Properties();
            props.put("user", "user1");
            props.put("password", "user1");
            String dbName = ThisDBName;       // the name of the database
            connCreate = DriverManager.getConnection(protocol + dbName, props);
            connCreate.setAutoCommit(false);    //Auto commit mode disabled
            thisStat = connCreate.createStatement();
            psInsertNote = connCreate.prepareStatement("insert into Notes values (?,?,?,?,?)");
            rsLastID = thisStat.executeQuery("select max(NID) from Notes");
            if (rsLastID.next()) {
                NID = rsLastID.getInt(1) + 1;
            } else {
                NID = 1;
            }
            Timestamp ts = new Timestamp(new java.util.Date().getTime());
            psInsertNote.setInt(1, NID);
            psInsertNote.setString(2, nTitile);
            psInsertNote.setString(3, nDescription);
            psInsertNote.setTimestamp(4, ts);
            psInsertNote.setTimestamp(5, ts);
            psInsertNote.executeUpdate();

            if (bProjectNote) {   //If project note
                //get the next PNID
                int PNID;
                rsLastID = thisStat.executeQuery("select max(PNID) from ProjectNotes");
                if (rsLastID.next()) {
                    PNID = rsLastID.getInt(1) + 1;
                } else {
                    PNID = 1;
                }
                psInsertNoteRef = connCreate.prepareStatement("insert into ProjectNotes values (?,?,?)");
                psInsertNoteRef.setInt(1, PNID);
                psInsertNoteRef.setInt(2, iItemID);
                psInsertNoteRef.setInt(3, NID);
                psInsertNoteRef.executeUpdate();
            } else {              //If article note
                //get the next ANID
                int ANID;
                rsLastID = thisStat.executeQuery("select max(ANID) from ArticleNotes");
                if (rsLastID.next()) {
                    ANID = rsLastID.getInt(1) + 1;
                } else {
                    ANID = 1;
                }
                psInsertNoteRef = connCreate.prepareStatement("insert into ArticleNotes values (?,?,?)");
                psInsertNoteRef.setInt(1, ANID);
                psInsertNoteRef.setInt(2, iItemID);
                psInsertNoteRef.setInt(3, NID);
                psInsertNoteRef.executeUpdate();
            }

            connCreate.commit();
            System.out.println("New Note details added");
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            sqle.printStackTrace();
        } finally {
            if (psInsertNote != null) {
                try {
                    psInsertNote.close();
                } catch (SQLException sqle) {

                }
            }
            if (psInsertNoteRef != null) {
                try {
                    psInsertNoteRef.close();
                } catch (SQLException sqle) {

                }
            }
            if (rsLastID != null) {
                try {
                    rsLastID.close();
                } catch (SQLException sqle) {

                }
            }
            if (thisStat != null) {
                try {
                    thisStat.close();
                    thisStat = null;
                } catch (SQLException sqle) {

                }
            }
            if (connCreate != null) {
                try {
                    connCreate.close();
                    connCreate = null;
                } catch (SQLException sqle) {

                }
            }
//            GetProjectsList();
        }
    }

    public ArrayList GetNoteDetils(int NID) {
        Connection connCreate = null;             //Connection to the database
        Statement psSearch1 = null;
        ArrayList thisNoteDetails = new ArrayList();
        try {
            Properties props = new Properties();
            props.put("user", "user1");
            props.put("password", "user1");
            String dbName = ThisDBName;       // the name of the database
            connCreate = DriverManager.getConnection(protocol + dbName, props);
            connCreate.setAutoCommit(false);    //Auto commit mode disabled   
            psSearch1 = connCreate.createStatement();
            psSearch1.setEscapeProcessing(true);
            ResultSet rsThisArticleList = psSearch1.executeQuery("SELECT * FROM Notes WHERE NID = " + Integer.toString(NID));
            connCreate.commit();
            while (rsThisArticleList.next()) {
                thisNoteDetails.add(rsThisArticleList.getInt(1));
                thisNoteDetails.add(rsThisArticleList.getString(2));
                thisNoteDetails.add(rsThisArticleList.getString(3));
                thisNoteDetails.add(rsThisArticleList.getTimestamp(4));
                thisNoteDetails.add(rsThisArticleList.getTimestamp(5));
            }
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            sqle.printStackTrace();
        } finally {
            if (psSearch1 != null) {
                try {
                    psSearch1.close();
                    psSearch1 = null;
                } catch (SQLException sqle) {

                }
            }
            if (connCreate != null) {
                try {
                    connCreate.close();
                    connCreate = null;
                } catch (SQLException sqle) {

                }
            }
            return thisNoteDetails;
        }
    }

    public void UpdateNote(int NID, String nTitile, String nDescription) {
        Connection connCreate = null;             //Connection to the database
        //ArrayList statements= new ArrayList();  //List of statements
        PreparedStatement psInsert = null;
        try {
            Properties props = new Properties();
            props.put("user", "user1");
            props.put("password", "user1");
            String dbName = ThisDBName;       // the name of the database
            connCreate = DriverManager.getConnection(protocol + dbName, props);
            connCreate.setAutoCommit(false);    //Auto commit mode disabled
            psInsert = connCreate.prepareStatement("update Notes set NoteTitle=?,Note=?,ModifiedDate=? where NID=?");
            Timestamp ts = new Timestamp(new java.util.Date().getTime());
            psInsert.setInt(4, NID);
            psInsert.setString(1, nTitile);
            psInsert.setString(2, nDescription);
            psInsert.setTimestamp(3, ts);
            psInsert.executeUpdate();
            connCreate.commit();
            System.out.println("Note details updated");
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        } finally {
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException sqle) {

                }
            }
            if (connCreate != null) {
                try {
                    connCreate.close();
                    connCreate = null;
                } catch (SQLException sqle) {

                }
            }
//            GetProjectsList();
        }
    }

    public Boolean IfArticleCountExeedsNonLicensedLimit(LicenceMgmt passLic) {
        Boolean test = false;
        Connection connCreate = null;             //Connection to the database
        Statement psSearch1 = null;
        try {
            Properties props = new Properties();
            props.put("user", "user1");
            props.put("password", "user1");
            String dbName = ThisDBName;       // the name of the database
            connCreate = DriverManager.getConnection(protocol + dbName, props);
            connCreate.setAutoCommit(false);    //Auto commit mode disabled   
            psSearch1 = connCreate.createStatement();
            psSearch1.setEscapeProcessing(true);
            ResultSet rsThisArticleList = psSearch1.executeQuery("SELECT COUNT(*) AS rowcount FROM Articles");
            connCreate.commit();

            if (rsThisArticleList.next()) {
                int count = rsThisArticleList.getInt("rowcount");
                if (count < passLic.ARTICLELIMIT) {
                    test = false;
                } else {
                    test = true;
                }
            }
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            sqle.printStackTrace();
        } finally {
            if (psSearch1 != null) {
                try {
                    psSearch1.close();
                    psSearch1 = null;
                } catch (SQLException sqle) {

                }
            }
            if (connCreate != null) {
                try {
                    connCreate.close();
                    connCreate = null;
                } catch (SQLException sqle) {

                }
            }
            return test;
        }
    }

    public Boolean IfProjectCountExeedsNonLicensedLimit(LicenceMgmt passLic) {
        Boolean test = false;
        Connection connCreate = null;             //Connection to the database
        Statement psSearch1 = null;
        try {
            Properties props = new Properties();
            props.put("user", "user1");
            props.put("password", "user1");
            String dbName = ThisDBName;       // the name of the database
            connCreate = DriverManager.getConnection(protocol + dbName, props);
            connCreate.setAutoCommit(false);    //Auto commit mode disabled   
            psSearch1 = connCreate.createStatement();
            psSearch1.setEscapeProcessing(true);
            ResultSet rsThisArticleList = psSearch1.executeQuery("SELECT COUNT(*) AS rowcount FROM Projects");
            connCreate.commit();

            if (rsThisArticleList.next()) {
                int count = rsThisArticleList.getInt("rowcount");
                if (count < passLic.PROJECTLIMIT) {
                    test = false;
                } else {
                    test = true;
                }
            }
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            sqle.printStackTrace();
        } finally {
            if (psSearch1 != null) {
                try {
                    psSearch1.close();
                    psSearch1 = null;
                } catch (SQLException sqle) {

                }
            }
            if (connCreate != null) {
                try {
                    connCreate.close();
                    connCreate = null;
                } catch (SQLException sqle) {

                }
            }
            return test;
        }
    }

    public Boolean IfProjectNoteCountExeedsNonLicensedLimit(int PID, LicenceMgmt passLic) {
        Boolean test = false;
        Connection connCreate = null;             //Connection to the database
        Statement psSearch1 = null;
        try {
            Properties props = new Properties();
            props.put("user", "user1");
            props.put("password", "user1");
            String dbName = ThisDBName;       // the name of the database
            connCreate = DriverManager.getConnection(protocol + dbName, props);
            connCreate.setAutoCommit(false);    //Auto commit mode disabled   
            psSearch1 = connCreate.createStatement();
            psSearch1.setEscapeProcessing(true);
            ResultSet rsThisArticleList = psSearch1.executeQuery("SELECT COUNT(*) AS rowcount FROM Notes WHERE NID IN (SELECT DISTINCT NID FROM ProjectNotes WHERE PID=" + Integer.toString(PID) + ")");
            connCreate.commit();

            if (rsThisArticleList.next()) {
                int count = rsThisArticleList.getInt("rowcount");
                if (count < passLic.PROJECTLIMIT) {
                    test = false;
                } else {
                    test = true;
                }
            }
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            sqle.printStackTrace();
        } finally {
            if (psSearch1 != null) {
                try {
                    psSearch1.close();
                    psSearch1 = null;
                } catch (SQLException sqle) {

                }
            }
            if (connCreate != null) {
                try {
                    connCreate.close();
                    connCreate = null;
                } catch (SQLException sqle) {

                }
            }
            return test;
        }
    }

    public Boolean IfArticleNoteCountExeedsNonLicensedLimit(int AID, LicenceMgmt passLic) {
        Boolean test = false;
        Connection connCreate = null;             //Connection to the database
        Statement psSearch1 = null;
        try {
            Properties props = new Properties();
            props.put("user", "user1");
            props.put("password", "user1");
            String dbName = ThisDBName;       // the name of the database
            connCreate = DriverManager.getConnection(protocol + dbName, props);
            connCreate.setAutoCommit(false);    //Auto commit mode disabled   
            psSearch1 = connCreate.createStatement();
            psSearch1.setEscapeProcessing(true);
            ResultSet rsThisArticleList = psSearch1.executeQuery("SELECT COUNT(*) AS rowcount FROM Notes WHERE NID IN (SELECT DISTINCT NID FROM ArticleNotes WHERE AID=" + Integer.toString(AID) + ")");
            connCreate.commit();

            if (rsThisArticleList.next()) {
                int count = rsThisArticleList.getInt("rowcount");
                if (count < passLic.PROJECTLIMIT) {
                    test = false;
                } else {
                    test = true;
                }
            }
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            sqle.printStackTrace();
        } finally {
            if (psSearch1 != null) {
                try {
                    psSearch1.close();
                    psSearch1 = null;
                } catch (SQLException sqle) {

                }
            }
            if (connCreate != null) {
                try {
                    connCreate.close();
                    connCreate = null;
                } catch (SQLException sqle) {

                }
            }
            return test;
        }
    }
}
