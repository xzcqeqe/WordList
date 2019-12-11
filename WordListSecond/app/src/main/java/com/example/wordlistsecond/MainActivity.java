package com.example.wordlistsecond;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button b1,b2,b3,b4,b5;
    MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = findViewById(R.id.web);
        b2 = findViewById(R.id.newWord);
        b3 = findViewById(R.id.add);
        b4 = findViewById(R.id.delete);
        b5 = findViewById(R.id.alter);

        dbHelper = new MyDatabaseHelper(this, "WordList.db", null, 1);
        dbHelper.getWritableDatabase();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in1 = new Intent(MainActivity.this,WebActivity.class);
                startActivity(in1);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in2 = new Intent(MainActivity.this,newWordActivity.class);
                startActivity(in2);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in3 = new Intent(MainActivity.this, ADDActivity.class);
                startActivity(in3);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in4 = new Intent(MainActivity.this,DeleteActivity.class);
                startActivity(in4);
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in5 = new Intent(MainActivity.this,AlterActivity.class);
                startActivity(in5);
            }
        });

    }

}
