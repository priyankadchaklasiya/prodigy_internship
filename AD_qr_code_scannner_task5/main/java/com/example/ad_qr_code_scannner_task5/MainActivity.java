package com.example.ad_qr_code_scannner_task5;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    private Button buttonScan;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonScan = findViewById(R.id.buttonScan);
        textViewResult = findViewById(R.id.textViewResult);

        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new IntentIntegrator(MainActivity.this).initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                textViewResult.setText("Cancelled");
            } else {
                String scannedData = result.getContents();
                textViewResult.setText(scannedData);
                handleScannedData(scannedData);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void handleScannedData(String data) {
        if (data.startsWith("http://") || data.startsWith("https://")) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(data));
            startActivity(browserIntent);
        } else {
            // Handle other types of data (e.g., plain text)
            textViewResult.setText(data);
        }
    }
}
