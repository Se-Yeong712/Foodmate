package kr.hs.foodmate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

    public class board_view extends AppCompatActivity {

        ListView listView;
        list_Adapter list_adapter;
        ArrayList<list_item> list_itemArrayList;
        private Button write;
        String id;
        String check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_view);

        Intent intent = getIntent(); /*데이터 수신*/
        id = intent.getExtras().getString("id"); /*String형*/
        check = intent.getExtras().getString("check");

        listView = (ListView)findViewById(R.id.board_listview);
        list_itemArrayList = new ArrayList<list_item>();



        TextView chat = findViewById(R.id.chat);
        TextView my = findViewById(R.id.my);

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Chat.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

        my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), My.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });




        //DB받아오기


        if(check.equals("N")) {

            list_itemArrayList.add(
                    new list_item(R.drawable.img4, "이수하", "한정식 먹으러 가자", "#한정식"));
        }

        list_itemArrayList.add(
                new list_item(R.drawable.img1,"윤수영","떡볶이 맛집 가자","#떡볶이 #즉석떡볶이"));
        list_itemArrayList.add(
                new list_item(R.drawable.img2,"박소연","통돼지구이먹자","#통돼지 #구이"));
        list_itemArrayList.add(
                new list_item(R.drawable.img3,"이세영","서울 맛집 탐험대 모집","#맛집 #탐험대"));

        list_adapter = new list_Adapter(board_view.this, list_itemArrayList);
        listView.setAdapter(list_adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), chatting.class);
                startActivity(intent);
            }
        });

        write = findViewById(R.id.btn_write);
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), write_view.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

    }
}
