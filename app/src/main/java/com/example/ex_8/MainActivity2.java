package com.example.ex_8;

import android.content.Intent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity2 extends AppCompatActivity {
    @SuppressLint("SdCardPath")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String fname = getIntent().getStringExtra("fname");
        //final EditText e1=(EditText)findViewById(R.id.editText1);
        final EditText e2=(EditText)findViewById(R.id.editText);

        Button read=(Button)findViewById(R.id.button);
        Button write=(Button)findViewById(R.id.button2);
        Button clear= (Button) findViewById(R.id.button3);
        Button back = (Button) findViewById(R.id.button5);
        String path=getPreferences(MODE_PRIVATE).getString("fpath", "/sdcard/file1");
        //e1.setText(path);
        read.setOnClickListener(
                new OnClickListener()
                {
                    @Override
                    public void onClick(View arg0) {
// TODO Auto-generated method stub
                        File f=new File("/sdcard/"+fname+".txt");
                        String s="";
                        StringBuilder sb=new StringBuilder();
                        FileReader fr = null;
                        try {
                            fr = new FileReader(f);
                        } catch (FileNotFoundException e) {
// TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        BufferedReader br=new BufferedReader(fr);
                        try {
                            while((s=br.readLine())!=null)
                            {
                                sb.append(s+"\n");
                            }
                        } catch (IOException e) {
// TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        Toast.makeText(getApplicationContext(), "File Read Successfully from "+fname+".txt !!!", Toast.LENGTH_LONG).show();
                        e2.setText(sb);
                    }
                });
        write.setOnClickListener(
                new OnClickListener()
                {
                    @Override
                    public void onClick(View arg0) {
// TODO Auto-generated method stub
                        File f=new File("/sdcard/"+fname+".txt");
                        FileWriter fw = null;
                        try {
                            fw = new FileWriter(f);
                        } catch (IOException e3) {
// TODO Auto-generated catch block
                            e3.printStackTrace();
                        }
                        try {
                            fw.write(e2.getText().toString());
                        } catch (IOException e2) {
// TODO Auto-generated catch block
                            e2.printStackTrace();
                        }

                        try {
                            fw.close();
                        } catch (IOException e2) {
// TODO Auto-generated catch block
                            e2.printStackTrace();
                        }
                        SharedPreferences.Editor
                                e=getPreferences(MODE_PRIVATE).edit();
                        e.putString("fpath", f.getPath());
                        e.commit();
                        Toast.makeText(getApplicationContext(), "Saved Successfully to "+fname+".txt !!!", Toast.LENGTH_LONG).show();
                    }
                });
        clear.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                e2.setText("");
                Toast.makeText(getApplicationContext(), "Data cleared from text field!!!", Toast.LENGTH_LONG).show();
            }
        });

        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent n=new Intent(MainActivity2.this,MainActivity.class);
                startActivity(n);
            }
        });
    }
}