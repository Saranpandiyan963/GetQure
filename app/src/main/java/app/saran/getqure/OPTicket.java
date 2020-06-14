package app.saran.getqure;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;

public class OPTicket extends AppCompatActivity {

    private TextView patientname,Time,hospital,id;
    private final String server ="http://192.168.43.26/example_4.json";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opticket);

        id = (TextView)findViewById(R.id.patientId);
        patientname = (TextView)findViewById(R.id.patientName);
        Time = (TextView)findViewById(R.id.appointment_time);
        hospital = (TextView)findViewById(R.id.Hospital_Name);
        Patient op = new Patient();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, server, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    id.setText(response.getString("id"));
                    patientname.setText(response.getString("patientname"));
                    Time.setText(response.getString("Time"));
                    hospital.setText(response.getString("hospital"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(OPTicket.this,"error dude",Toast.LENGTH_LONG).show();
                error.printStackTrace();

            }
        });
        MySingelten.getInstance(OPTicket.this).addToRequestque(jsonObjectRequest);

    }
}
