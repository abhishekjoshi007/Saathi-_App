package com.invaders.saathi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.card1:
                intent = new Intent(this, WebViewActivity.class);
                intent.putExtra("card", 1);
                startActivity(intent);
                break;
            case R.id.card2:
                intent = new Intent(this, WebViewActivity.class);
                intent.putExtra("card", 2);
                startActivity(intent);
                break;
            case R.id.card3:
                intent = new Intent(this, WebViewActivity.class);
                intent.putExtra("card", 3);
                startActivity(intent);
                break;
            case R.id.card4:
                intent = new Intent(this, WebViewActivity.class);
                intent.putExtra("card", 4);
                startActivity(intent);
                break;
            case R.id.card5:
                intent = new Intent(this, AccessibilityActivity.class);
                startActivity(intent);
                break;
            case R.id.card6:
                intent = new Intent(this, WebViewActivity.class);
                intent.putExtra("card", 6);
                startActivity(intent);
                break;
            case R.id.card9:
                intent = new Intent(this, WebViewActivity.class);
                intent.putExtra("card", 9);
                startActivity(intent);
                break;
            case R.id.card10:
                intent = new Intent(this, WebViewActivity.class);
                intent.putExtra("card", 10);
                startActivity(intent);
                break;
        }
    }
}
