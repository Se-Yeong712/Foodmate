package kr.hs.foodmate;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class My extends AppCompatActivity {

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        Intent intent = getIntent(); /*데이터 수신*/
        id = intent.getExtras().getString("id"); /*String형*/

        TextView idcheck = findViewById(R.id.idcheck);
        idcheck.setText(id);

        TextView chat = findViewById(R.id.chat);
        TextView plan = findViewById(R.id.plan);

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Chat.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

        plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), board_view.class);
                intent.putExtra("id",id);
                intent.putExtra("check","y");
                startActivity(intent);
            }
        });
    }
}
