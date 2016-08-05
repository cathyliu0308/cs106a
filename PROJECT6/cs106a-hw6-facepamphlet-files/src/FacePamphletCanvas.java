/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	
	double X = 0;
	double Y = 0;
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	
	public FacePamphletCanvas() {
		
	}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		//removeAll();
		GLabel message = new GLabel(msg);
		double posX = getWidth()/2 - message.getWidth()/2;
		double posY = getHeight() - BOTTOM_MESSAGE_MARGIN;
		message.setFont(MESSAGE_FONT);
		if (getElementAt(X,Y) != null) {
			GObject obj = getElementAt(X,Y);
			remove(obj);
		}
		X = posX; Y = posY;
		add(message, X, Y);		
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		removeAll();
		String name = profile.getName();
		String status = profile.getStatus();
		GImage image = profile.getImage();
		Iterator<String> friends = profile.getFriends();
		GLabel friendName;
		
		GLabel userName = new GLabel(name);
		userName.setFont(PROFILE_NAME_FONT);
		userName.setColor(Color.BLUE);
		double nameX = LEFT_MARGIN;
		double nameY = TOP_MARGIN + userName.getHeight();
		add(userName, nameX, nameY);
		
		GLabel userStatus = new GLabel(status);
		userStatus.setFont(PROFILE_STATUS_FONT);
		double statusX = LEFT_MARGIN;
		double statusY = TOP_MARGIN + userName.getHeight() + IMAGE_MARGIN + IMAGE_HEIGHT + STATUS_MARGIN + userStatus.getHeight();
		add(userStatus, statusX, statusY);
		
		double imageX = LEFT_MARGIN;
		double imageY = TOP_MARGIN + userName.getHeight() + IMAGE_MARGIN; 
		if(image != null) {
			image.setBounds(imageX, imageY, IMAGE_WIDTH, IMAGE_HEIGHT);
			add(image);
		} else {
			GRect frame = new GRect(imageX,imageY,IMAGE_WIDTH, IMAGE_HEIGHT);
			GLabel notice = new GLabel("No Image");
			notice.setFont(PROFILE_IMAGE_FONT);
			double noticeX = LEFT_MARGIN + IMAGE_WIDTH/2 - notice.getWidth()/2;
			double noticeY = imageY + IMAGE_HEIGHT/2;
			add(frame,imageX,imageY);
			add(notice,noticeX,noticeY);
		}
		
		GLabel userFriends = new GLabel("Friends:");
		userFriends.setFont(PROFILE_FRIEND_LABEL_FONT);
		double friendsX = getWidth()/2;
		double friendsY = TOP_MARGIN + userName.getHeight();
		add(userFriends, friendsX, friendsY);
		while (friends.hasNext()) {
			friendName = new GLabel(friends.next());
			friendName.setFont(PROFILE_FRIEND_FONT);
			friendsY += userFriends.getHeight();
			add(friendName, friendsX, friendsY);
		}
		
		
	}

	
}
