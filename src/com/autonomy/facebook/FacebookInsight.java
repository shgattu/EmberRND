package com.autonomy.facebook;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement(name="facebookInsight")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@XmlAccessorType(XmlAccessType.FIELD)
public class FacebookInsight{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String insightName;

	public List<String> facebookinsight;
	
	public String id;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	
	}
	public FacebookInsight(){
		
	}
	public FacebookInsight(String string, List<String> valueList, String string2) {
		// TODO Auto-generated constructor stub
		this.insightName = string;
		this.facebookinsight = valueList;
		this.id = string2;
	}
	
	public List<String> getFacebookinsight() {
		return facebookinsight;
	}
	public void setFacebookinsight(List<String> facebookinsight) {
		this.facebookinsight = facebookinsight;
	}
	
	public String getInsightName() {
		return insightName;
	}
	public void setInsightName(String insightName) {
		this.insightName = insightName;
	}
	
	
	
	
	
	
}
