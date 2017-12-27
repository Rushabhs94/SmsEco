package com.example.root.smseco;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

/**
 * Created by root on 27/12/17.
 */

public class SMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle;
        bundle=intent.getExtras();
        if (bundle!=null){
            Object[] sms=(Object[])bundle.get("pdus");
            String smsmsg="";
            for (int i=0;i<sms.length;i++){
                SmsMessage smsManager= SmsMessage.createFromPdu((byte[])sms[i]);
                String smsbody=smsManager.getMessageBody();
                String smsaddress=smsManager.getServiceCenterAddress();
                smsmsg += "SMS from :"+smsaddress+"/n";
                smsmsg +="/n"+ smsbody+"/n";
            }
            ShowSMS showSMS=ShowSMS.Instance();
            showSMS.updateList(smsmsg);
        }
    }
}
