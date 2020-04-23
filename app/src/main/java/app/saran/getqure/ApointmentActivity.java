package app.saran.getqure;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ApointmentActivity extends AppCompatActivity {

    private EditText patientName,age;
    private Button appoinment;
    private RadioGroup genderGroup;
    private RadioButton genderButton;
    private String hospitalName,doctorName;
    private RequestQueue requestQueue;

    final String server_url  = "http://192.168.43.26/example_1.json";
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apointment);

        Intent intent = getIntent();
        HospitalDetails hospital = intent.getParcelableExtra("Hospital & Doctor");

        doctorName = hospital.doctor_Name;
        hospitalName = hospital.hospital_Name;

        final Patient op = new Patient(doctorName,hospitalName);

        patientName = (EditText)findViewById(R.id.patientName);
        age = (EditText)findViewById(R.id.age);
        appoinment = (Button)findViewById(R.id.appointment);
        genderGroup = (RadioGroup)findViewById(R.id.genderGroup);
        builder = new AlertDialog.Builder(ApointmentActivity.this);

        System.out.println("done");

        appoinment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Name = patientName.getText().toString().trim();
                String Sex = genderButton.getText().toString().trim();
                int ag =  Integer.parseInt(age.getText().toString());

                if (TextUtils.isEmpty(Name))
                {
                    patientName.setError("Invalid Name");
                    return;
                }
                if (TextUtils.isEmpty(age.getText().toString()))
                {
                    age.setError("Invalid Age Field");
                    return;
                }

                op.setPatientName(Name);
                op.setGender(Sex);
                op.setAge(ag);
                Gson gson = new Gson();
                String opJson = gson.toJson(op);
                System.out.println(opJson);
                Submit(opJson);

            }
        });

    }

    public void Submit(final String json){

        final String savedata = json;

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringReguest = new StringRequest(Request.Method.POST, server_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jobt = new JSONObject(response);
                            Toast.makeText(getApplicationContext(),jobt.toString(),Toast.LENGTH_LONG).show();
                            System.out.println(jobt);
                            startActivity(new Intent(getApplicationContext(),OPTicket.class));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),"server error",Toast.LENGTH_LONG).show();

            }
        }) {
            @Override
            public String getBodyContentType(){return "application/json; charset=utf-8";}

            @Override
            public byte[] getBody() throws AuthFailureError{
                try {
                return savedata == null ? null : savedata.getBytes("utf-8");
                }
                catch (UnsupportedEncodingException uee) {
                    return  null ;
                }
            }
        };
        requestQueue.add(stringReguest);
    }

    public void checkButton(View v){
        int Radioid = genderGroup.getCheckedRadioButtonId();

        genderButton = (RadioButton)findViewById(Radioid);

        if (Radioid != 0){
            appoinment.setEnabled(true);
        }
    }
}
