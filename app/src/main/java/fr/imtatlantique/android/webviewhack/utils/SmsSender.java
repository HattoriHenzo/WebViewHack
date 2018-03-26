package fr.imtatlantique.android.webviewhack.utils;

import android.telephony.SmsManager;

/**
 * Created by SOMBUGMA Emmanuel on 25/03/2018.
 */

public class SmsSender {

    private SmsManager smsManager = SmsManager.getDefault();


    public SmsSender() {

    }

    public void sendSms(String contact, String sms) {
        smsManager.sendTextMessage(contact, null, sms, null, null);
    }
}
