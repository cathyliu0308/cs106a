/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;

import java.awt.event.*;

import javax.swing.*;

public class FacePamphlet extends Program 
					implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	private JTextField nameField;
	private JButton add;
	private JButton delete;
	private JButton lookup;
	
	private JTextField statusField;
	private JTextField picField;
	private JTextField friendField;
	private JButton statusButton;
	private JButton picButton;
	private JButton friendButton;
	
	private FacePamphletDatabase db = new FacePamphletDatabase();
	private FacePamphletCanvas canvas = new FacePamphletCanvas();
	private FacePamphletProfile profile = null;
	
	public void init() {
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		
		//the buttons and fields in the north
		JLabel name = new JLabel("Name");
		add(name,NORTH);
		
		nameField = new JTextField(TEXT_FIELD_SIZE);
		add(nameField,NORTH);
		
		add = new JButton("Add");
		add(add,NORTH);
		
		delete = new JButton("Delete");
		add(delete,NORTH);
		
		lookup = new JButton("Lookup");
		add(lookup,NORTH);
		
		// the buttons and fields in the west
		statusField = new JTextField(TEXT_FIELD_SIZE);
		statusField.addActionListener(this);
		add(statusField,WEST);
		
		statusButton = new JButton("Change Status");
		add(statusButton,WEST);
		//statusButton.setActionCommand("enable");
		//statusButton.setEnabled(true);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		
		picField = new JTextField(TEXT_FIELD_SIZE);
		picField.addActionListener(this);
		add(picField,WEST);
		
		picButton = new JButton("Change Picture");
		add(picButton, WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		
		friendField = new JTextField(TEXT_FIELD_SIZE);
		friendField.addActionListener(this);
		add(friendField,WEST);
		
		friendButton = new JButton("Add Friend");
		add(friendButton, WEST);
		
		addActionListeners();
		add(canvas);
		
    }
    
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
    	
    	String name = nameField.getText();
    	String status = statusField.getText();
    	String imageName = picField.getText();
    	String friendName = friendField.getText();
    	//GImage image = null;
    	FacePamphletProfile curProfile = null;
    	
        if ((e.getSource() == add) && (name != EMPTY_LABEL_TEXT)) {
                //println("Add: " + name);
            if (db.containsProfile(name)) {
            	curProfile = db.getProfile(name);
            	canvas.displayProfile(curProfile);
            	canvas.showMessage("A profile with the name " + name + " already exists");
            	//println("Add: profile with name " + name + " already existes: " + profile.toString());   
            } else {
            	curProfile = new FacePamphletProfile(name);
            	db.addProfile(curProfile);
            	//println("Add: new profile: " + profile.toString());
            	canvas.displayProfile(curProfile);
            	canvas.showMessage("New profile created");
            } 
            profile = curProfile;
        } else if ((e.getSource() == delete) && (name != EMPTY_LABEL_TEXT)) {
                //println("Delete: " + name); 
        	canvas.removeAll();
        	if (db.containsProfile(name)) {
            	db.deleteProfile(name);
            	canvas.showMessage("Profile of " + name + " deleted");       		
            	//println("Add: profile with name " + name + " deleted ");              	
            } else {
            	canvas.showMessage("A profile with name " + name + " dose not exist");
            }  
        	profile = null;
        } else if ((e.getSource() == lookup) && (name != EMPTY_LABEL_TEXT)) {
        	canvas.removeAll();
                //println("Lookup: " + nameField.getText());
        	if (db.containsProfile(name)) {
        		curProfile = db.getProfile(name);
            	canvas.displayProfile(curProfile);
            	canvas.showMessage("Displaying " + name);
            	profile = curProfile;
        	} else {
        		canvas.showMessage("A profile with the name " + name + " dose not exist");
        	}                      
        } else if ((e.getSource() == statusField) || (e.getSource() == statusButton)) {
                //println("Change Status: " + statusField.getText());
        	if (name == EMPTY_LABEL_TEXT || !db.containsProfile(name) ) {
        		canvas.showMessage("Please select a profile to change status");
        	} else {
    			curProfile = db.getProfile(name);
    			curProfile.setStatus(name + " is " + status);
    			canvas.displayProfile(curProfile);
    			canvas.showMessage("Status updated to " + status);
    			profile = curProfile;
    		}       
        } else if ((e.getSource() == picField) || (e.getSource() == picButton)) {
        	if (name == EMPTY_LABEL_TEXT || !db.containsProfile(name)) {
        		canvas.showMessage("Please select a profile to change status.");
        	} else {
                //println("Change Picture: " + picField.getText());  
            	curProfile = db.getProfile(name);
            	GImage image = null;
            	try {
            		image = new GImage(imageName);
            		profile.setImage(image);
            	} catch (ErrorException ex) {
            		image = null;
            	}
            	canvas.displayProfile(curProfile);
            	profile = curProfile;
    			if(image == null) {
    				canvas.showMessage("Unable to open image file: " + imageName);
    			}
    			else{
    				canvas.showMessage("Picture updated");
    			}
            }            
        } else if ((e.getSource() == friendField) || (e.getSource() == friendButton)) {
          //  if (friendField.getText() != EMPTY_LABEL_TEXT) {
           //     println("Add Friend: " + friendField.getText());
            //} 
        	if (name == EMPTY_LABEL_TEXT) {
        		canvas.showMessage("Please select a profile to change status.");
        	} else if (db.containsProfile(name)) {
        		curProfile = db.getProfile(name);
        		if(name.equals(friendName)) {
    				canvas.showMessage("You cannot add ourself as friend");
    			} else if (db.containsProfile(friendName)) {
    				FacePamphletProfile friendProfile = db.getProfile(friendName);
    				if (curProfile.addFriend(friendName)) {
    					friendProfile.addFriend(name);
    					canvas.displayProfile(curProfile);
    					//profile = curProfile;
    					canvas.showMessage(friendName + " added as a friend.");
    				} else {
    					canvas.showMessage(name + " already has " + friendName + " as a friend.");
    				}
    			} else{
    				canvas.showMessage(friendName + " does not exist.");
    			}
        	} else{
    			canvas.showMessage("Profile with name " + name + " dose not exist.");
    		}
        }
        profile = curProfile;
	}

}
