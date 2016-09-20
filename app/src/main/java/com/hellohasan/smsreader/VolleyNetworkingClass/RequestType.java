package com.hellohasan.smsreader.VolleyNetworkingClass;

import com.android.volley.Request;


public class RequestType {

    private String type;
    private String route;

    public RequestType(String type, String route)
    {
        this.type = type;
        this.route = route;
     }


    public int getType()
    {
        if(this.type.equalsIgnoreCase("POST"))
            return Request.Method.POST;
        else
            return Request.Method.GET;
    }

    public String setRoute(String id){

        if(this.type.equalsIgnoreCase("POST"))
            return this.route;

        StringBuffer str = new StringBuffer(this.route);

        int ind = str.indexOf("{");

        if(ind<0)
            return this.route;

        str.replace(ind,ind+3,id);

        return str.toString();
    }
}
