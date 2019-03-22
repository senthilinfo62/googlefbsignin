package com.googlefbsignin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class details extends AppCompatActivity {
    ImageView imageView;
    TextView textName, textEmail;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        mAuth = FirebaseAuth.getInstance();

        imageView = findViewById(R.id.imageView);
        textName = findViewById(R.id.textViewName);
        textEmail = findViewById(R.id.textViewEmail);


        FirebaseUser user = mAuth.getCurrentUser();

//        Glide.with(this)
//                .load(user.getPhotoUrl())
//                .into(imageView);

        textName.setText(user.getDisplayName());
        textEmail.setText(user.getEmail());
        Log.d("url", String.valueOf(user.getMetadata()));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             mAuth.signOut();
startActivity(new Intent(details.this,googledata.class));
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();

        //if the user is not logged in
        //opening the login activity
        if (mAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, googledata.class));
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (mAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, googledata.class));
        }

    }
}
