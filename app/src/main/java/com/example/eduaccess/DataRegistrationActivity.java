package com.example.eduaccess;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.eduaccess.databinding.ActivityDataRegistrationBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DataRegistrationActivity extends AppCompatActivity {
    Button confirmButton;
    ActivityDataRegistrationBinding binding;
    String firstName, lastName, age, job;
    FirebaseDatabase db;
    DatabaseReference reference;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDataRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        confirmButton = findViewById(R.id.confirm_button);

        FirebaseUser user;
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();


        binding.confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstName = binding.firstName.getText().toString();
                lastName = binding.lastName.getText().toString();
                age = binding.age.getText().toString();
                job = binding.job.getText().toString();

                if (!firstName.isEmpty() && !lastName.isEmpty() && !age.isEmpty() && !job.isEmpty()){
                    Users users = new Users(firstName, lastName, age, job);

                    db = FirebaseDatabase.getInstance();
                    reference = db.getReference("Users");
                    reference.child(uid).setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            binding.firstName.setText("");
                            binding.lastName.setText("");
                            binding.age.setText("");
                            binding.job.setText("");
                            Toast.makeText(DataRegistrationActivity.this, "Successfully updated", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(DataRegistrationActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                }
                else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(DataRegistrationActivity.this, "Update failed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}