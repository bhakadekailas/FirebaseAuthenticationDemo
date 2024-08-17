package com.kb.myfirebasedemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    EditText emailET, passwordET, mobileNoET;
    Button signUp;
    TextView signIn;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        initViews();
    }

    private void initViews() {
        emailET = findViewById(R.id.editTextText);
        passwordET = findViewById(R.id.editTextText2);
        mobileNoET = findViewById(R.id.editTextText3);
        signIn = findViewById(R.id.tvSignIn);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        signUp = findViewById(R.id.button);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSignUp();
            }
        });
    }

    private void doSignUp() {
        String email = emailET.getText().toString();
        String password = passwordET.getText().toString();
        String mobileNo = mobileNoET.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
//                        FirebaseUser user = mAuth.getCurrentUser();
//                        Log.e("KAILASA", "createUserWithEmail:Success = " + user.getEmail());
//                        db = FirebaseFirestore.getInstance();
//                        db.collection("User")
//                                .document(FirebaseAuth.getInstance().getUid())
//                                .set(new UserModel("Kailas", "9921710694", "MCA", email, password));
                        finish();
                    } else {
                        Log.e("KAILASA", "createUserWithEmail:failure", task.getException());
                    }
                });
    }
}