package com.hellohasan.smsreader.VolleyNetworkingClass;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import com.hellohasan.smsreader.HelperClasses.KeyNameClass;
import com.hellohasan.smsreader.HelperClasses.Preferences;
import com.hellohasan.smsreader.Interface.HttpResponseInterface;

public class HttpConnectionClass {

//    private static String mainURL  = "http://192.168.0.100/smsreader/data.php";
//    private static String apiBaseRoute = "";
//    private static String BaseURL = mainURL + apiBaseRoute;
    private static HashMap<String, RequestType > map = new HashMap<>();
    static
    {
        map.put(KeyNameClass.LOGIN_ATTEMPT,new RequestType("POST","auth/login"));
        map.put(KeyNameClass.REGISTER_ATTEMPT, new RequestType("POST","auth/register"));
        map.put(KeyNameClass.FORGOT_PASSWORD_ATTEMPT, new RequestType("POST","auth/forgotpass"));
        map.put(KeyNameClass.ONE_TIME_LOGIN_ATTEMPT, new RequestType("POST", "auth/onetimelogin"));
        map.put(KeyNameClass.PASSWORD_CHANGE_ATTEMPT, new RequestType("POST","auth/changepass"));
        map.put(KeyNameClass.COMPLAIN_ATTEMPT, new RequestType("POST","user/complain"));
        map.put(KeyNameClass.PROFILE_UPDATE_ATTEMPT, new RequestType("POST","user/profile"));
        map.put(KeyNameClass.GET_HISTORY, new RequestType("GET","user/{0}/history"));
        map.put(KeyNameClass.GET_SUMMARY, new RequestType("GET","user/{0}/summary"));
        map.put(KeyNameClass.GET_PROBLEM_LIST, new RequestType("GET", "user/problemlist"));
        map.put(KeyNameClass.FEEDBACK_ATTEMPT, new RequestType("POST", "user/feedback"));
        map.put(KeyNameClass.SEND_DATA, new RequestType("POST", ""));
    }

    public static void requestHandler(final Context activityContext, String key, final Map<String,String> data, final HttpResponseInterface caller){

        Preferences preferences = Preferences.getInstance(activityContext);

//        String route = map.get(key).setRoute(String.valueOf(preferences.getUserId()));

//        String url = BaseURL+route;

        String url = preferences.getApiLink();

        System.out.println("URL: "+url);

        StringRequest stringRequest = new StringRequest(map.get(key).getType(),
                url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            caller.actionOnResponse(jsonObject);

                        } catch (JSONException e) {
                            JSONObject jsonExceptionObject = new JSONObject();
                            try {
                                jsonExceptionObject.put(KeyNameClass.SUCCESS, false);
                                jsonExceptionObject.put(KeyNameClass.MESSAGE, "Exception: "+e.toString());
                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            }
                            caller.actionOnResponse(jsonExceptionObject);
                       }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                System.out.println("Volley Error: " + error.toString());

                JSONObject errorJsonObject = new JSONObject();
                String errorMessage;

                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    errorMessage = "Request timed out of Connection problem!";
                } else if (error instanceof ServerError) {
                    errorMessage = "Server Error!";
                } else if (error instanceof NetworkError) {
                    errorMessage = "Network Error!";
                } else if (error instanceof ParseError) {
                    errorMessage = "Parsing problem!";
                }
                else
                    errorMessage = "Something wrong in API";

                try {
                    errorJsonObject.put(KeyNameClass.SUCCESS, false);
                    errorJsonObject.put(KeyNameClass.MESSAGE, errorMessage);
                } catch (JSONException e) {
                    Toast.makeText(activityContext, "Exception:->> " + e, Toast.LENGTH_LONG).show();
                }

                caller.actionOnResponse(errorJsonObject);

            }

        }) {

            @Override
            protected Map<String, String> getParams() {
                return data;
            }

        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                30000, //60 seconds is timeout
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppController.getInstance().addToRequestQueue(stringRequest);

    }
}
