package com.autonomy.facebook;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FacebookFeedCollection {
	
	@XmlElement
	public List<FqlPost> feedCollection;
	
	public FacebookFeedCollection(){
		
	}

	public FacebookFeedCollection(List<FqlPost> fqlPosts) {
		// TODO Auto-generated constructor stub
		this.feedCollection = fqlPosts;
	}

	public List<FqlPost> getFeedCollection() {
		return feedCollection;
	}

	public void setFeedCollection(List<FqlPost> feedCollection) {
		this.feedCollection = feedCollection;
	}
}
