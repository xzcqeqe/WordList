package com.example.wordlistsecond;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AlterActivity extends AppCompatActivity {
    Button b1;
    EditText e1,e2,e3,e4,e5;
    private MyDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter);
        b1 = (Button)findViewById(R.id.sure);
        e1 = (EditText)findViewById(R.id.alterWord);
        e2 = (EditText)findViewById(R.id.ukphonetic1);
        e3 = (EditText)findViewById(R.id.usphonetic1);
        e4 = (EditText)findViewById(R.id.alterExplain);
        e5 = (EditText)findViewById(R.id.alterExample);

        dbHelper = new MyDatabaseHelper(this,"WordList.db",null,1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               try {

                   final String query = e1.getText().toString();
                   final String ukphonetic = e2.getText().toString();
                   final String usphonetic = e3.getText().toString();
                   final String explain = e4.getText().toString();
                   final String example = e5.getText().toString();
                   SQLiteDatabase db = dbHelper.getWritableDatabase();
                   ContentValues values = new ContentValues();
                   values.put("usphonetic", usphonetic);
                   values.put("ukphonetic", ukphonetic);
                   values.put("translation ", explain);
                   values.put("example", example);
                   db.update("Book", values, "queryWord=?", new String[]{query});
                   Toast.makeText(AlterActivity.this,"修改成功",Toast.LENGTH_LONG).show();
               }catch (Exception e){ Toast.makeText(AlterActivity.this,"修改失败",Toast.LENGTH_LONG).show();}


            }
        });
    }
}
