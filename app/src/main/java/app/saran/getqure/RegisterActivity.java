package app.saran.getqure;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText username,email,password;
    private Button register;
    private ProgressBar prog;
    private FirebaseAuth mAuth;
    private TextView already;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username =findViewById(R.id.name);
        email =findViewById(R.id.email);
        password =findViewById(R.id.passWord);
        register =findViewById(R.id.register);
        prog = findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();
        already =findViewById(R.id.alredy);

        already.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });

        final String TAG = "testcase";

        if (mAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));

        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                String Email = email.getText().toString().trim();
                String Password = password.getText().toString().trim();

                if (TextUtils.isEmpty(Email))
                {
                    email.setError("Invalid Email address");
                    return;
                }
                if (TextUtils.isEmpty(Password))
                {
                    password.setError("Passward field can't be Empty");
                    return;
                }

                if(Password.length() < 8){
                    password.setError("password cant be less then 8");
                }

                prog.setVisibility(View.INVISIBLE);
                register.setVisibility(View.INVISIBLE);

                mAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>(){

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task){
                        if(task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                            user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(RegisterActivity.this,"VERIFICATION EMAIL SENT",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(),MainUser.class));

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(RegisterActivity.this,"NOT SENT",Toast.LENGTH_SHORT).show();

                                }
                            });

                            Toast.makeText(RegisterActivity.this,"created",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),OPTicket.class));
                            Log.i(TAG,"done");
                            finish();
                        }
                        else{
                            Toast.makeText(RegisterActivity.this,"Error"+ task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            Log.i(TAG,task.getException().getMessage());
                            startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
                        }
                    }
                });


            }
        });
    }
}
