package com.autonomy.facebook;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.restfb.types.Post.Action;
import com.restfb.types.Post.Likes;

@XmlRootElement
public class FqlPost {
	
	public Likes action;
	public String objectName;
	
	
	
	public FqlPost(){
		
	}
	
	public FqlPost(Likes action, String objName){
		this.action = action;
		this.objectName = objName;
	}

	

	public Likes getAction() {
		return action;
	}

	public void setAction(Likes action) {
		this.action = action;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	  
}
	  
