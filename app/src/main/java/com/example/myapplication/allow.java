package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;


public class allow extends AppCompatActivity {

    private static final int RESULT_PICK_CONTACT =1;
    private TextView phone;
    ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_allow);

        phone = findViewById (R.id.textView11);
        Button select = findViewById(R.id.button2);
        Button dec=findViewById(R.id.button3);

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult o) {
                if(o.getResultCode()==RESULT_OK)
        {
            switch (o.getResultCode()) {
                case RESULT_PICK_CONTACT:
                    contactPicked (o.getData());
                    break;
            }
        }
        else
        {
            Toast.makeText (allow.this, "Failed To pick contact", Toast.LENGTH_SHORT).show ();
        }

            }
        });


        select.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent in = new Intent (Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
       //         startActivityForResult(in, RESULT_PICK_CONTACT);
                activityResultLauncher.launch(in);
            }
        });

    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
//        if(resultCode==RESULT_OK)
//        {
//            switch (requestCode) {
//                case RESULT_PICK_CONTACT:
//                    contactPicked (data);
//                    break;
//            }
//        }
//        else
//        {
//            Toast.makeText (this, "Failed To pick contact", Toast.LENGTH_SHORT).show ();
//        }
//    }

    private void contactPicked(Intent data) {
        Cursor cursor = null;

        try {
            String phoneNo = null;
            Uri uri = data.getData ();
            cursor = getContentResolver ().query (uri, null, null,null,null);
            cursor.moveToFirst ();
            int phoneIndex = cursor.getColumnIndex (ContactsContract.CommonDataKinds.Phone.NUMBER);

            phoneNo = cursor.getString (phoneIndex);

            phone.setText (phoneNo);


        } catch (Exception e) {
            e.printStackTrace ();
        }
    }

}