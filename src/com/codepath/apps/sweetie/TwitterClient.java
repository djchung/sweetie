package com.codepath.apps.sweetie;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;

import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/*
 * 
 * This is the object responsible for communicating with a REST API. 
 * Specify the constants below to change the API being communicated with.
 * See a full list of supported API classes: 
 *   https://github.com/fernandezpablo85/scribe-java/tree/master/src/main/java/org/scribe/builder/api
 * Key and Secret are provided by the developer site for the given API i.e dev.twitter.com
 * Add methods for each relevant endpoint in the API.
 * 
 * NOTE: You may want to rename this object based on the service i.e TwitterClient or FlickrClient
 * 
 */
public class TwitterClient extends OAuthBaseClient {
    public static final Class<? extends Api> REST_API_CLASS = TwitterApi.class; 
    public static final String REST_URL = "https://api.twitter.com/1.1"; 
    public static final String REST_CONSUMER_KEY = "cShEcuSNoxKYu09EzNT4Q";
    public static final String REST_CONSUMER_SECRET = "7YWacujrPRQmAMBPSRI776ov4cGIlshRfaJkL8qco"; 
    public static final String REST_CALLBACK_URL = "oauth://sweetieapp"; // Change this (here and in manifest)
    
    public TwitterClient(Context context) {
        super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
    }
    
    public void getHomeTimeline(int count, String max_id, AsyncHttpResponseHandler handler) {
    	String url = getApiUrl("statuses/home_timeline.json");
    	RequestParams params = new RequestParams();
    	params.put("count", String.valueOf(count));
    	if (max_id != null ) {
    		params.put("max_id", max_id);
    	}
    	getClient().get(url, params, handler);
    }
    
    public void getMentions(int count, String max_id, AsyncHttpResponseHandler handler) {
    	String url = getApiUrl("statuses/mentions_timeline.json");
    	RequestParams params = new RequestParams();
    	params.put("count", String.valueOf(count));
    	if (max_id != null ) {
    		params.put("max_id", max_id);
    	}
    	getClient().get(url, null, handler);
    }
    
    public void postTweet(String body, AsyncHttpResponseHandler handler) {
    	String url = getApiUrl("statuses/update.json");
    	RequestParams params = new RequestParams();
    	params.put("status", body);
    	getClient().post(url, params, handler);
    	
    }
    
    public void getMyInfo(AsyncHttpResponseHandler handler) {
    	String url = getApiUrl("account/verify_credentials.json");
    	getClient().get(url, null, handler);
    }
    
    public void getUserTimeline(int count, String max_id, AsyncHttpResponseHandler handler) {
    	String url = getApiUrl("statuses/user_timeline.json");
    	RequestParams params = new RequestParams();
    	params.put("count", String.valueOf(count));
    	if (max_id != null ) {
    		params.put("max_id", max_id);
    	}
    	getClient().get(url, null, handler);
    }
    
    /* 1. Define the endpoint URL with getApiUrl and pass a relative path to the endpoint
     * 	  i.e getApiUrl("statuses/home_timeline.json");
     * 2. Define the parameters to pass to the request (query or body)
     *    i.e RequestParams params = new RequestParams("foo", "bar");
     * 3. Define the request method and make a call to the client
     *    i.e client.get(apiUrl, params, handler);
     *    i.e client.post(apiUrl, params, handler);
     */
}