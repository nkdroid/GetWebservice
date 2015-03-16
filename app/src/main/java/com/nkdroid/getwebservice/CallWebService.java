package com.nkdroid.getwebservice;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * 
 * @author Made by Dhruvil Patel
 *         <p>
 *         </p>
 *         This is a custom class that represents the impmementation of Volly
 *         classes
 * 
 *         <li>
 *         <h6>JsonObjectRequest</h6></li> <li>
 *         <h6>JsonArrayRequest</h6></li>
 *         <p>
 *         </p>
 *         <li>You have to give two params in this class as mentioned below</li>
 *         <li>And then write obj.call() to call webservice</li>
 * 
 *         <p>
 *         </p>
 *         <p>
 *         </p>
 *         * <blockquote></blockquote>
 * 
 *         <code>CallWebService obj = new CallWebService() {
 * @Override public void response(Strinm             g response) { // TODO Auto-generated
 *           method stub
 * 
 * 
 *           }
 * @Override public void error(VolleyError error) { // TODO Auto-generated
 *           method stub
 * 
 * 
 *           } }; <p></p>
 * 
 *           obj.setJsonObjectRequest(true); <p></p>
 *           obj.setUrl(AppConstants.SERVICE_URL); <p></p> obj.call();</code>
 * 
 */
public abstract class CallWebService implements IService {


	
	public abstract void response(String response);
	public abstract void error(VolleyError error);
	
	private String url;
	String response = null;

	public static int TYPE_JSONOBJECT = 0;
	public static int TYPE_JSONARRAY = 1;
	public static int TYPE_STRING = 2;
	public int type = 0;

	
	/**
	 * 
	 * @param url : server Url 
	 * @param type : The request which you want to receive from server
	 * <p><b>- Types Example</b></p>
	 * <p><li>CallWebService.TYPE_JSONOBJECT</li></p>
	 * <p><li>CallWebService.TYPE_JSONARRAY</li></p>
	 * <p><li>CallWebService.TYPE_STRING</li></p>
	 * 
	 */
	public CallWebService(String url, int type) {
		super();
		this.url = url;
		this.type = type;
	}

	// Main implementation of calling the webservice.
	
	public synchronized final CallWebService start(){
		
		call();
		
	return this;
		
	}

	public void call() {

		switch (type) {

		// case  for requesting json object
		case 0:

			JsonObjectRequest request = new JsonObjectRequest(url, null,
					new Listener<JSONObject>() {

						@Override
						public void onResponse(JSONObject jobj) {


							response(jobj.toString());

						}
					}, new ErrorListener() {

						@Override
						public void onErrorResponse(VolleyError e) {
							error(e);
						}
					});

            int socketTimeout = 30000;//30 seconds - change to what you want
            RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,0, 0);
            request.setRetryPolicy(policy);

			MyApplication.getInstance().addToRequestQueue(request);

			break;

		   //case for requesting json array

		case 1:

			JsonArrayRequest request2 = new JsonArrayRequest(url,
					new Listener<JSONArray>() {
						@Override
						public void onResponse(JSONArray jArray) {

							response(jArray.toString());
						}
					}, new ErrorListener() {

						@Override
						public void onErrorResponse(VolleyError arg0) {

							error(arg0);
						}
					});

            int socketTimeout2 = 30000;//30 seconds - change to what you want
            RetryPolicy policy2 = new DefaultRetryPolicy(socketTimeout2, 0, 0);
            request2.setRetryPolicy(policy2);
			MyApplication.getInstance().addToRequestQueue(request2);

			break;

		case 2:

			break;

		}

	}

}
