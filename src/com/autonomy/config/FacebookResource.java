package com.autonomy.config;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.autonomy.facebook.FacebookFeedCollection;
import com.autonomy.facebook.FacebookInsight;
import com.autonomy.facebook.FacebookInsightCollection;
import com.autonomy.facebook.FacebookPost;
import com.autonomy.facebook.FqlPost;
import com.autonomy.facebook.ProxyWebRequester;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.DefaultJsonMapper;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.json.JsonObject;
import com.restfb.types.Insight;
import com.restfb.types.Page;
import com.restfb.types.Post;

@Path("/base")
public class FacebookResource {

	@GET
	@Path("/rest")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public FacebookInsightCollection facebookInsight() {
		String inputLine = null;
		String newArr[] = new String[5];
		FacebookInsightCollection insightCollection = null;
		try {
			
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(
					"16.113.84.10", 8080));
			// Get the client_id and client_Secret from facebook app you created
			URLConnection yc = new URL(
					"https://graph.facebook.com/oauth/access_token?client_id=*****&client_secret=****&grant_type=client_credentials")
					.openConnection(proxy);
			// = oracle.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					yc.getInputStream()));
				// The above url connection returns me a access_token=**********
				// I am splitting it to retrieve the value
			while ((inputLine = in.readLine()) != null) {
				newArr = inputLine.split("=");

			}
			in.close();
			// These are needed if you have proxy setup. I need this as I was runnig it behind firewall
			System.setProperty("http.proxySet", "true");
			System.setProperty("http.proxyHost", "16.113.84.10");
			System.setProperty("http.proxyPort", "8080");
			FacebookClient facebookClient = new DefaultFacebookClient(
					newArr[1], new ProxyWebRequester(), new DefaultJsonMapper());
			Page page = facebookClient.fetchObject("cocacola", Page.class);
			System.out.println(page.getLikes());
			Connection<Insight> insights = facebookClient.fetchConnection(
					"cocacola/insights", Insight.class);

			List<JsonObject> values = new ArrayList<JsonObject>();
			List<FacebookInsight> fbInsightList = new ArrayList<FacebookInsight>();
			// int index =0;
			for (Insight insight : insights.getData()) {
				System.out.println(insight.getName());
				System.out.println(insight.getId());
				List<String> valueList = new ArrayList<String>();
				// System.out.println(insight.getValues());
				values = insight.getValues();
				for (int i = 0; i < values.size(); i++) {
					System.out.println("values"
							+ values.get(i).getJsonObject("value")
									.getString("US"));
					valueList.add(values.get(i).getJsonObject("value")
							.getString("US"));

					// insightValues.add();
				}
				FacebookInsight insightFB = new FacebookInsight(
						insight.getName(), valueList, insight.getId());
				fbInsightList.add(insightFB);

				// index++;
			}
			insightCollection = new FacebookInsightCollection(fbInsightList);
			insightCollection.setInsightCollection(fbInsightList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return insightCollection;
	}

	@GET
	@Path("/feed")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public FacebookFeedCollection facebookFeed() {
		FacebookFeedCollection feedCollection = null;
		try {
			
			System.setProperty("http.proxySet", "true");
			System.setProperty("http.proxyHost", "16.113.84.10");
			System.setProperty("http.proxyPort", "8080");
			// Get this token from graph api tools/explorer
			FacebookClient facebookClient = new DefaultFacebookClient(
					"*****",
					new ProxyWebRequester(), new DefaultJsonMapper());

			Connection<Post> filteredFeed = facebookClient.fetchConnection(
					"me/feed", Post.class, Parameter.with("limit", 3),
					Parameter.with("until", "yesterday"));
		
					

			List<Post> fqlPosts = filteredFeed.getData();
			List<FqlPost> posts = new ArrayList<FqlPost>();
			for (Post pagePost : fqlPosts) {
				System.out.println(pagePost);
				// JSONObject jsobj = new JSONObject(pagePost, strArray);
				posts.add(new FqlPost(pagePost.getLikes(), pagePost
						.getObjectId()));

			}
			feedCollection = new FacebookFeedCollection(posts);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return feedCollection;
	}
	
	@POST
	@Path("/feed/update")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Boolean facebookStatus(FacebookPost post) {
		
		Boolean status = false;
		try {
			System.setProperty("http.proxySet", "true");
			System.setProperty("http.proxyHost", "16.113.84.10");
			System.setProperty("http.proxyPort", "8080");
			// Get this token from graphapi tools explorer
			FacebookClient facebookClient = new DefaultFacebookClient(
					"*****",
					new ProxyWebRequester(), new DefaultJsonMapper());
		
					status = facebookClient.publish("me/feed", Boolean.class,
						    Parameter.with("message", post.getPostMessage()));

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

}
