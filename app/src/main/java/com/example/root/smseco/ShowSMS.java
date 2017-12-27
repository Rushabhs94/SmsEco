package com.example.root.smseco;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowSMS extends AppCompatActivity {
    public ListView showsmslist;
    ArrayList<String> smslist=new ArrayList<String>();
    ArrayAdapter arrayAdapter;
    public static ShowSMS instance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_sms);
        instance=this;
        showsmslist=findViewById(R.id.showsmslist);
        arrayAdapter =new ArrayAdapter(ShowSMS.this,android.R.layout.simple_list_item_1,smslist);
        showsmslist.setAdapter(arrayAdapter);


        ContentResolver contentResolver = getContentResolver();
        Cursor smsinboxcursor = contentResolver.query(Uri.parse("content://sms/inbox"),
                    null, null, null, null);
        int inboxBody = smsinboxcursor.getColumnIndex("body");
        int inboxAddress = smsinboxcursor.getColumnIndex("address");
        if (inboxBody < 0 || !smsinboxcursor.moveToFirst()) {
                return;
            }
        arrayAdapter.clear();
        do {
                String str = "\n"+smsinboxcursor.getString(inboxAddress)+"\n";
                str += "\n"+smsinboxcursor.getString(inboxBody)+"\n";
                arrayAdapter.add(str);
            } while (smsinboxcursor.moveToNext());
    }
    public void updateList(final String sms){
        arrayAdapter.insert(sms,0);
        arrayAdapter.notifyDataSetChanged();
    }

    public static ShowSMS Instance() {
        return instance;
    }
}
