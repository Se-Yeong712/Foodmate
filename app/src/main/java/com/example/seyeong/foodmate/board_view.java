    package com.example.seyeong.foodmate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class board_view extends AppCompatActivity {

    private Button btnwrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_view);

        btnwrite = findViewById(R.id.btn_write);

        btnwrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),write_view.class);
                startActivity(intent);
            }
        });

    }
}
