package fr.imtatlantique.android.webviewhack.utils;

import android.telephony.SmsManager;

/**
 * Classe qui envoi des SMS sans Intent
 * Created by SOMBUGMA Emmanuel on 25/03/2018.
 */

public class SmsSender {

    private SmsManager smsManager = SmsManager.getDefault();

    public SmsSender() {

    }

    /**
     * Fonction qui envoie un sms
     * @param contact celui à qui est destiné le SMS
     * @param sms envoyé
     */
    public void sendSms(String contact, String sms) {
        smsManager.sendTextMessage(contact, null, sms, null, null);
    }
}
