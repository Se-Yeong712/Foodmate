package kr.hs.foodmate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class chatting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);

        Button btn = findViewById(R.id.btn_write);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Chat.class);
                intent.putExtra("id","id");
                intent.putExtra("list","Y");
                startActivity(intent);
            }
        });

        Button button = findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText e = findViewById(R.id.chattingtext);
                String s = e.getText().toString();

                TextView t = findViewById(R.id.text);
                t.setText(s);

            }
        });
    }
}
