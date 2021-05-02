package com.example.ex_8;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity
{
    EditText e1;
    Button file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1 = (EditText) findViewById(R.id.editText2);
        file = (Button) findViewById(R.id.button4);

        file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname = e1.getText().toString();
                try {
                    File path = Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_PICTURES);
                    File f = new File("/sdcard/"+fname+".txt");
                    f.createNewFile();
//                    FileOutputStream fout=new FileOutputStream(f);
//                    fout.write("New file".getBytes());
//                    fout.close();
                    Toast.makeText(getBaseContext(), "File -> "+fname+" Created successfully!", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
                Intent n=new Intent(MainActivity.this,MainActivity2.class);
                n.putExtra("fname",fname);
                startActivity(n);

            }
        });
    }
}
