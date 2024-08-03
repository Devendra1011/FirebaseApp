package com.example.firebaseapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textView = findViewById(R.id.text_view);


        // Write a Message to the database

        // Initialize and Access the firebase Database
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        // Get a Reference to a specific node in the Database
        DatabaseReference myRef = database.getReference("Users");

//        // Write a value to the specified database location
//        User user1 = new User("Jack","jack123@gmail.com");
//
//        myRef.setValue(user1);
//        myRef.setValue("Hello From our Course");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //  String newValue = snapshot.getValue(String.class);
                //   textView.setText(newValue);

                User user = snapshot.getValue(User.class);
                textView.setText("Email "+ user.getEmail());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });
    }
}