package kr.hs.foodmate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main2Activity extends AppCompatActivity {
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        Button b = findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplication(),"a",Toast.LENGTH_LONG);

//                FirebaseDatabase mdatabase2 = FirebaseDatabase.getInstance();
//                DatabaseReference mdatabaseRef2 = mdatabase2.getReference("member");
//
//                mdatabaseRef2.child("psy020207").child("chat").addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        for(DataSnapshot message:dataSnapshot.getChildren()){
//                            Toast.makeText(getApplication(),message.getValue().toString(),Toast.LENGTH_LONG).show();
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });

                databaseReference = FirebaseDatabase.getInstance().getReference();
                databaseReference.child("Test").push().setValue("Test");


            }
        });
    }
}
