package app.saran.getqure;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BackTask {

    Context context;
    ArrayList<HospitalDetails> arrayList = new ArrayList<>();
    final String jsonUrl = "http://192.168.43.26/example_1.json";

    public BackTask(Context context) {
        this.context = context;
    }

    public ArrayList<HospitalDetails> getData(){

        final JsonArrayRequest jsonarray = new JsonArrayRequest(Request.Method.POST, jsonUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                int count = 0;
                System.out.println(response.length());
                int x = response.length();
                while(count<x){
                    try {
                        JSONObject jsonObject = response.getJSONObject(count);
                        HospitalDetails hospital = new HospitalDetails(jsonObject.getString("hospitalName"),jsonObject.getString("doctorName"),jsonObject.getString("description"));
                        arrayList.add(hospital);
                        count++;


                    } catch (JSONException e) {
                        System.out.println("Error");
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context,"error===",Toast.LENGTH_LONG).show();
                error.printStackTrace();

            }
        });

        MySingelten.getInstance(context).addToRequestque(jsonarray);
        return arrayList;
    }
}
