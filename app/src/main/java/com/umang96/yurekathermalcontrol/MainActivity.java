package com.umang96.yurekathermalcontrol;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private RadioButton radio1, radio2, radio3, radio4;
    private RadioGroup radio;
   // private TextView text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        copyAssets();
        setContentView(R.layout.activity_main);
       // Context context = getApplicationContext();
      //  String filename="thermal.txt";
       // String x=read_file(context, filename);
       // text1 = (TextView) findViewById(R.id.textView5);
       // assert text1 != null;
       // text1.setText(x);
        addButtonClickListener1();
        addButtonClickListener2();
        radio = (RadioGroup) findViewById(R.id.radiog);
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override

            public void onCheckedChanged(RadioGroup group, int checkedId) {

                // find which radio button is selected

                if (checkedId == R.id.radio1) {

                    Toast.makeText(getApplicationContext(), "You selected Conservative",

                            Toast.LENGTH_SHORT).show();

                } else if (checkedId == R.id.radio2) {

                    Toast.makeText(getApplicationContext(), "You selected Balanced",

                            Toast.LENGTH_SHORT).show();

                } else if (checkedId == R.id.radio3) {

                    Toast.makeText(getApplicationContext(), "You selected Gaming",

                            Toast.LENGTH_SHORT).show();
                }
                else if (checkedId == R.id.radio4) {

                    Toast.makeText(getApplicationContext(), "You selected Ultimate",

                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        radio1 = (RadioButton) findViewById(R.id.radio1);
        radio2 = (RadioButton) findViewById(R.id.radio2);
        radio3 = (RadioButton) findViewById(R.id.radio3);
        radio4 = (RadioButton) findViewById(R.id.radio4);

    }

    /* public String read_file(Context context, String filename) {
        try {
            FileInputStream fis = context.openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            return "file not found";
        } catch (UnsupportedEncodingException e) {
            return "unsupported encoding";
        } catch (IOException e) {
            return "io exception";
        }
    } */

    private void addButtonClickListener1() {
        Button b1 = (Button) findViewById(R.id.button1);
        assert b1 != null;
        final TextView editText1 = (TextView) findViewById(R.id.textView2);
        b1.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      assert editText1 != null;
                                      int flag = radio.getCheckedRadioButtonId();
                                      Process p = null;
                                      try {
                                          //editText1.setText("");
                                          p = Runtime.getRuntime().exec("su");
                                          if (flag == radio1.getId())
                                          { //editText1.setText("1");
                                              p.getOutputStream().write("source sdcard/Android/data/com.umang96.yurekathermalcontrol/files/path1.sh".getBytes());
                                              Toast.makeText(getApplicationContext(), "Applied, Now Reboot !",
                                                      Toast.LENGTH_SHORT).show();}
                                          if (flag == radio2.getId())
                                          { //editText1.setText("2");
                                              p.getOutputStream().write("source sdcard/Android/data/com.umang96.yurekathermalcontrol/files/path2.sh".getBytes());
                                              Toast.makeText(getApplicationContext(), "Applied, Now Reboot !",
                                                      Toast.LENGTH_SHORT).show();}
                                          if (flag == radio3.getId())
                                          {  ///editText1.setText("3");
                                              p.getOutputStream().write("source sdcard/Android/data/com.umang96.yurekathermalcontrol/files/path3.sh".getBytes());
                                              Toast.makeText(getApplicationContext(), "Applied, Now Reboot !",
                                                      Toast.LENGTH_SHORT).show();}
                                          if (flag == radio4.getId())
                                          { //editText1.setText("2");
                                              p.getOutputStream().write("source sdcard/Android/data/com.umang96.yurekathermalcontrol/files/path4.sh".getBytes());
                                              Toast.makeText(getApplicationContext(), "Applied, Now Reboot !",
                                                      Toast.LENGTH_SHORT).show();}
                                      }  catch(IOException e){
                                          Toast.makeText(getApplicationContext(), "Failed, do you have root access ?",
                                                  Toast.LENGTH_SHORT).show();
                                          e.printStackTrace();
                                      }

                                  }
                              }
        );
    }

    private void addButtonClickListener2() {
        Button b2 = (Button) findViewById(R.id.button2);
        assert b2 != null;
        final TextView editText1 = (TextView) findViewById(R.id.textView2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this,
                        Main2Activity.class);
                startActivity(myIntent);
        }}
            );}


    private void copyAssets() {
        AssetManager assetManager = getAssets();
        String[] files = null;
        try {
            files = assetManager.list("");
        } catch (IOException e) {
            Log.e("tag", "Failed to get asset file list.", e);
        }
        if (files != null) for (String filename : files) {
            InputStream in = null;
            OutputStream out = null;
            try {
                in = assetManager.open(filename);
                File outFile = new File(getExternalFilesDir(null), filename);
                out = new FileOutputStream(outFile);
                copyFile(in, out);
            } catch(IOException e) {
                Log.e("tag", "Failed to copy asset file: " + filename, e);
            }
            finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        // NOOP
                    }
                }
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        // NOOP
                    }
                }
            }
        }
    }
    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }
}
