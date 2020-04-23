package app.saran.getqure;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText email,passwaord;
    private TextView alrdy;
    private Button logIn;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth mAuth;

    final String Tag = "testcase";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.lemail);
        passwaord = findViewById(R.id.lpassword);
        logIn = findViewById(R.id.Log_in);
        alrdy = findViewById(R.id.alrdy);
        mAuth = FirebaseAuth.getInstance();



        alrdy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainUser.class));
            }
        });


        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String Email = email.getText().toString().trim();
                String Password = passwaord.getText().toString().trim();

                if (TextUtils.isEmpty(Email))
                {
                    email.setError("Invalid Email address");
                    return;
                }
                if (TextUtils.isEmpty(Password))
                {
                    passwaord.setError("Passward field can't be Empty");
                    return;
                }

                if(Password.length() < 8){
                    passwaord.setError("password cant be less then 8");
                }


                mAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(),MainUser.class) );

                        }
                        else{
                            startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
                        }
                    }
                });

            }
        });



    }

}
