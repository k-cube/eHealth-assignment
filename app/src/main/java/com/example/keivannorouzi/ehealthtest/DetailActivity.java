package com.example.keivannorouzi.ehealthtest;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.keivannorouzi.ehealthtest.R;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {

    EditText nameEdit,genderEdit,birthEdit;
    String name,gender,birth,family;
    Button saveChange;
    ImageButton editNameBtn,editGenderBtn,editBirthBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        nameEdit = findViewById(R.id.nameEdit);
        genderEdit = findViewById(R.id.genderEdit);
        birthEdit = findViewById(R.id.birthEdit);
        saveChange = findViewById(R.id.saveChange);
        editNameBtn = findViewById(R.id.editNameBtn);
        editGenderBtn = findViewById(R.id.editGenderBtn);
        editBirthBtn = findViewById(R.id.editBirthBtn);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        gender = intent.getStringExtra("gender");
        birth = intent.getStringExtra("birth");
        family = intent.getStringExtra("family");
        nameEdit.setText((name==null && family==null) ? "N/A" : Objects.toString(name, "") + " " + Objects.toString(family, ""));
        birthEdit.setText(birth==null? "N/A" : birth);
        genderEdit.setText(gender==null? "N/A" : gender);

        nameEdit.setEnabled(false);
        genderEdit.setEnabled(false);
        birthEdit.setEnabled(false);





    }
}
