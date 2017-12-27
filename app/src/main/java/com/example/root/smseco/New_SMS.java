package com.example.root.smseco;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class New_SMS extends AppCompatActivity {
    Button sendsmsbtns;
    EditText textmessage, mobileNumber;
    public String number;
    public String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__sms);

        sendsmsbtns = findViewById(R.id.sendsmsbtn);
        textmessage = findViewById(R.id.textmessage);
        mobileNumber = findViewById(R.id.mobileNumber);

        sendsmsbtns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number=mobileNumber.getText().toString();
                message=textmessage.getText().toString();

                try {
                    SmsManager smsManager=SmsManager.getDefault();
                    smsManager.sendTextMessage(number,null,message,null,null);

                    Log.d("=========","Mobile Number");
                    Log.d("Mobile Number",number);
                    Log.d("=========","Message Text");
                    Log.d("Message Text",message);
                    Toast.makeText(New_SMS.this, "SMS Send Succefully", Toast.LENGTH_SHORT).show();
                    Log.d("=====SMS Status=====","Suceefully Send SMS");
                }catch (Exception e){

                    Log.d("=========","Mobile Number");
                    Log.d("Mobile Number",number);
                    Log.d("=========","Message Text");
                    Log.d("Message Text",message);
                    Log.d("=====SMS Status=====","failed Send SMS");
                    Toast.makeText(New_SMS.this, "SMS Send failed", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
}
