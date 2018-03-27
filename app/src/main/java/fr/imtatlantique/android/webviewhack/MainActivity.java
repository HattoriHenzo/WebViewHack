package fr.imtatlantique.android.webviewhack;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static String HACKER_HTML_PAGE = "file:///android_asset/www/index.html";
    private WebView webView = null;
    private EditText editTextWebSite = null;
    private String webSite = "";

    @Override
    @SuppressLint("JavascriptInterface")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextWebSite = (EditText)findViewById(R.id.editTextWebSite);

        // On initialise le web view
        webView = (WebView)findViewById(R.id.webViewWebSite);
        // Activation de l'exécution du code JavaScript dans le web view : "Bonjour les dégâts !"
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // Déclaration d'une classe permettant de manipuler du code Java dans du JavaScript : "De mal en pire !"
        webView.addJavascriptInterface(new WebAppBridge(this), "AndroidFunction");
        // On récupère l'URL
        editTextWebSite.setText(HACKER_HTML_PAGE);
    }

    public void onClickButtonWebSite(View v) throws Exception {
        webSite = editTextWebSite.getText().toString();
        // Accès au site malveillant
        webView.loadUrl(webSite);
    }
}
