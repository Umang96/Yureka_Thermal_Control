package com.umang96.yurekathermalcontrol;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        addButtonClickListener3();
    }

    private void addButtonClickListener3() {
        Button b2 = (Button) findViewById(R.id.button3);
        assert b2 != null;
        final TextView editText1 = (TextView) findViewById(R.id.textView2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://forum.xda-developers.com/android/apps-games/app-t3409436");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }}
        );}



}
