package kr.hs.foodmate;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.view.View;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class write_view extends AppCompatActivity {

    private static final String TAG = "write_view";



    private Button btChoose;
    private Button btUpload;
    private EditText title;
    private EditText content;
    private EditText tag;

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;

    private Uri filePath;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_view);

        Intent intent = getIntent(); /*데이터 수신*/
        id = intent.getExtras().getString("id"); /*String형*/

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        btChoose = findViewById(R.id.bt_choose);
        btUpload = findViewById(R.id.bt_upload);
        title = findViewById(R.id.edit_title);
        content=findViewById(R.id.edit_content);
        tag = findViewById(R.id.edit_tag);



        //버튼 클릭 이벤트
        btChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "이미지를 선택하세요."), 0);
            }
        });

        btUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //업로드
                uploadFile();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0 && resultCode == RESULT_OK){
            filePath = data.getData();
            Log.d(TAG, "uri:" + String.valueOf(filePath));
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //upload the file
    private void uploadFile() {

        final String uptitle = title.getText().toString();
        final String upcontent = content.getText().toString();
        final String uptag = tag.getText().toString();

        if (filePath != null) {

            databaseReference.child("board").push().setValue(id);
            databaseReference.child("board").push().setValue(upcontent);
            databaseReference.child("board").push().setValue(uptag);

            databaseReference.child("member").child(id).child("board").push().setValue(uptitle);
            databaseReference.child("member").child(id).child("board").push().setValue(upcontent);
            databaseReference.child("member").child(id).child("board").push().setValue(uptag);

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("업로드중...");
            progressDialog.show();

            FirebaseStorage storage = FirebaseStorage.getInstance();

            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMHH_mmss");
            Date now = new Date();
            String filename = formatter.format(now) + ".png";
            StorageReference storageRef = storage.getReferenceFromUrl("gs://foodmate-f68bd.appspot.com").child("images/" + filename);

            storageRef.putFile(filePath)

                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "업로드 완료!", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplicationContext(), board_view.class);
                            intent.putExtra("id",id);
                            intent.putExtra("check","N");
                            startActivity(intent);

                        }
                    })
                    //실패시
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "업로드 실패!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    //진행중
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            @SuppressWarnings("VisibleForTests")
                                    double progress = (100 * taskSnapshot.getBytesTransferred()) /  taskSnapshot.getTotalByteCount();

                            progressDialog.setMessage("Uploaded " + ((int) progress) + "% ...");
                        }
                    });


        } else {
            Toast.makeText(getApplicationContext(), "파일을 먼저 선택하세요.", Toast.LENGTH_SHORT).show();
        }
    }

}
