package com.autonomy.facebook;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="insightCollection")
@XmlAccessorType(XmlAccessType.FIELD)
public class FacebookInsightCollection {
	
	
	public List<FacebookInsight> insightCollection;
	
	public FacebookInsightCollection(List<FacebookInsight> fbInsight){
		this.insightCollection = fbInsight;
	}

	public List<FacebookInsight> getInsightCollection() {
		return insightCollection;
	}

	public void setInsightCollection(List<FacebookInsight> insightCollection) {
		this.insightCollection = insightCollection;
	}
	
	public FacebookInsightCollection(){
		
	}

	
	
}
