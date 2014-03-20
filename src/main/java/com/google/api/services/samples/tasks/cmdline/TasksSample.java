/*
 * Copyright (c) 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.api.services.samples.tasks.cmdline;

import com.google.api.client.auth.oauth2.Credential;

import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.tasks.Tasks;
import com.google.api.services.tasks.TasksScopes;
import com.google.api.services.tasks.model.*;
import com.google.api.services.tasks.*;
import com.google.api.client.util.DateTime;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Main class for the Tasks API command line sample.
 * Demonstrates how to make an authenticated API call using OAuth 2 helper classes.
 */
public class TasksSample {

  /**
   * Be sure to specify the name of your application. If the application name is {@code null} or
   * blank, the application will log a warning. Suggested format is "MyCompany-ProductName/1.0".
   */
  private static final String APPLICATION_NAME = "API Project";

  /** Directory to store user credentials. */
  private static final java.io.File DATA_STORE_DIR =
      new java.io.File(System.getProperty("user.home"), ".store/tasks_sample");

  
  /**
   * Global instance of the {@link DataStoreFactory}. The best practice is to make it a single
   * globally shared instance across your application.
   */
  private static FileDataStoreFactory dataStoreFactory;

  /** Global instance of the JSON factory. */
  private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

  /** Global instance of the HTTP transport. */
  private static HttpTransport httpTransport;

  @SuppressWarnings("unused")
  private static Tasks client;

  /** Authorizes the installed application to access user's protected data. */
  private static Credential authorize() throws Exception {
    // load client secrets
    GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
        new InputStreamReader(TasksSample.class.getResourceAsStream("/client_secrets.json")));
    if (clientSecrets.getDetails().getClientId().startsWith("Enter") ||
        clientSecrets.getDetails().getClientSecret().startsWith("Enter ")) {
      System.out.println(
          "Overwrite the src/main/resources/client_secrets.json file with the client secrets file "
          + "you downloaded from the Quickstart tool or manually enter your Client ID and Secret "
          + "from https://code.google.com/apis/console/?api=tasks#project:544589805066 "
          + "into src/main/resources/client_secrets.json");
      System.exit(1);
    }

    // Set up authorization code flow.
    // Ask for only the permissions you need. Asking for more permissions will
    // reduce the number of users who finish the process for giving you access
    // to their accounts. It will also increase the amount of effort you will
    // have to spend explaining to users what you are doing with their data.
    // Here we are listing all of the available scopes. You should remove scopes
    // that you are not actually using.
    Set<String> scopes = new HashSet<String>();
    scopes.add(TasksScopes.TASKS);
    scopes.add(TasksScopes.TASKS_READONLY);

    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
        httpTransport, JSON_FACTORY, clientSecrets, scopes)
        .setDataStoreFactory(dataStoreFactory)
        .build();
    // authorize
    return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
  }

  /**
 * @throws IOException
 */
/**
 * @throws IOException
 */
/**
 * @throws IOException
 */
/**
 * @throws IOException
 */
/**
 * @throws IOException
 */
private static void showtasks() throws IOException{
	  com.google.api.services.tasks.model.Tasks result1 = client.tasks().list("@default").execute();
	  System.out.println(result1);
	  JFrame frame = new JFrame("Google Tasks");
	  JPanel main = new JPanel();
	  main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
	  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  JCheckBox[] cbox = new JCheckBox[result1.getItems().size()];
	  int i=0;
	  JPanel panel1 = new JPanel(new GridLayout(0, 1)); 
	  System.out.println(result1.getItems().size());
	  for(Task task : result1.getItems()){
		  cbox[i] = new JCheckBox(task.getTitle());
		  panel1.add(cbox[i]);
		  i++;
	  }
	  main.add(panel1);
	  System.out.println(1);
	  JPanel panel2 = new JPanel(new GridLayout(0,1)); 
	  JLabel label1 = new JLabel("Enter Task");
	  final JTextField tf1 = new JTextField(50);
	  JLabel label2 = new JLabel("Enter Note");
	  final JTextField tf2 = new JTextField(50);
	  JLabel label3 = new JLabel("Days for completion");
	  final JTextField tf3 = new JTextField(4);
	  panel2.add(label1);
	  panel2.add(tf1);
	  panel2.add(label2);
	  panel2.add(tf2);
	  panel2.add(label3);
	  panel2.add(tf3);
	  main.add(panel2);
	  System.out.println(2);
	  JPanel panel3 = new JPanel();
	  JButton insert = new JButton("Insert Task");
	  final JLabel succ = new JLabel();
	  insert.addActionListener(new ActionListener(){
		  public void actionPerformed(ActionEvent e){
			  String title = tf1.getText();
			  String note = tf2.getText();
			  int daysleft = Integer.parseInt(tf3.getText());
			  Task task = new Task();
			  task.setTitle(title);
			  task.setNotes(note);
			  task.setDue(new DateTime(System.currentTimeMillis() + (24*daysleft*3600000)));
			  Task result2;
			  try {
				result2 = client.tasks().insert("@default",task).execute();
				if(result2.getTitle().equals(title)){
					  succ.setText("Success");
				}
				else{
					  succ.setText("Some field missing");
				}
				System.out.println(5);
			  } catch (IOException e1) {
				// TODO Auto-generated catch block
				  System.out.println(6);	
				  e1.printStackTrace();
			  }
		  }
	  }
			  );
	  System.out.println(3);
	  panel3.add(insert);
	  panel3.add(succ);
	  main.add(panel3);
	  
	  frame.add(main);
	  frame.setSize(800,800);
	  frame.setVisible(true);
	  
	  
	/*Task task = new Task();
	task.setTitle("Java Task :D");
	task.setNotes("Please complete me");
	task.setDue(new DateTime(System.currentTimeMillis() + 3600000));

	Task result = client.tasks().insert("@default", task).execute();
	System.out.println(result.getTitle());*/
}
 
public static void main(String[] args) {
    try {
      // initialize the transport
      httpTransport = GoogleNetHttpTransport.newTrustedTransport();

      // initialize the data store factory
      dataStoreFactory = new FileDataStoreFactory(DATA_STORE_DIR);

      // authorization
      Credential credential = authorize();
      System.out.println(credential);
      // set up global Tasks instance
      client = new Tasks.Builder(httpTransport, JSON_FACTORY, credential)
          .setApplicationName(APPLICATION_NAME).build();

      System.out.println("Success! Now add code here.");
      com.google.api.services.tasks.model.Tasks result = client.tasks().list("@default").execute();
      
      System.out.println(result);
      System.out.println(result.getItems().size());
      for(Task task : result.getItems()){
    	  System.out.println(task.getTitle());
      }
      
      showtasks();
    } catch (IOException e) {
      System.err.println(e.getMessage());
    } catch (Throwable t) {
      t.printStackTrace();
    }
    //System.exit(1);
  }
}
