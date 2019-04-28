package kr.hs.foodmate;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class Chat extends AppCompatActivity {

    private ListView listView;
    MyAdapter mMyAdapter = new MyAdapter();
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private ChildEventListener mChild;
    String id;
    String list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Intent intent = getIntent(); /*데이터 수신*/
        id = intent.getExtras().getString("id"); /*String형*/
        list = intent.getExtras().getString("list");


        TextView my = findViewById(R.id.my);
        TextView plan = findViewById(R.id.plan);

        my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), My.class);
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

        listView = findViewById(R.id.listview);

        FirebaseDatabase mdatabase2 = FirebaseDatabase.getInstance();
        DatabaseReference mdatabaseRef2 = mdatabase2.getReference("Test");

        mdatabaseRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot message:dataSnapshot.getChildren()){
                    Toast.makeText(getApplication(),message.getValue().toString(),Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        if(list.equals("Y")){
            mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.img1), "윤수영", "안녕하세요");
        }

        listView.setAdapter(mMyAdapter);


    }
}




