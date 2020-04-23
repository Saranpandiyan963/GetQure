package app.saran.getqure;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainUser extends AppCompatActivity implements HospitalDetailAdapter.OnNoteClickListener {

    private static final String TAG = "testcase";
    ArrayList<HospitalDetails> Hospitals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);

        RecyclerView rvHospital = (RecyclerView)findViewById(R.id.Recyle);

        Hospitals =HospitalDetails.createHospitalDetail(15);
        HospitalDetailAdapter hdAdapter = new HospitalDetailAdapter(Hospitals,this);
        rvHospital.setAdapter(hdAdapter);
        rvHospital.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onNoteClick(int position) {
        Intent intent = new Intent(this,ApointmentActivity.class);
        intent.putExtra("Hospital & Doctor",Hospitals.get(position));
        startActivity(intent);

    }
}
