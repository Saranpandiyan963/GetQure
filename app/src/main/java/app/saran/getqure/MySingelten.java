package app.saran.getqure;


import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingelten
{
    private static MySingelten mInstance;
    private RequestQueue requestQueue;
    private static Context cntxt;

    public MySingelten(Context context){
        cntxt = context;
        requestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue(){

        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(cntxt.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized MySingelten getInstance(Context context){

        if (mInstance==null){

            mInstance = new MySingelten(context);
        }
        return mInstance;
    }

    public<T> void addToRequestque(Request<T> request){
        requestQueue.add(request);
    }


}
