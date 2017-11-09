package com.example.syed.sqlitedatabase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editname,editId,editEmail;
    TextView displayText;
    Button sendButton;

    MySqlite myDatabase = new MySqlite(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = myDatabase.addToTable(editId.getText().toString(),editname.getText().toString(),editEmail.getText().toString());

                if (check == true){
                    Toast.makeText(MainActivity.this,"Data Successfully Inserted ",Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(MainActivity.this,"Data Not Inserted ",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void initialize(){
        editId = (EditText) findViewById(R.id.edit_id);
        editname = (EditText) findViewById(R.id.edit_name);
        editEmail = (EditText) findViewById(R.id.edit_email);
        sendButton = (Button) findViewById(R.id.button_send);
        displayText = (TextView) findViewById(R.id.text_display);
    }

    public void button_display(View v){
        Cursor result;
        result = myDatabase.display();

        if (result.getCount() == 0){
            Toast.makeText(MainActivity.this,"You Have no Data ",Toast.LENGTH_SHORT).show();
            return;
        }
        result.moveToFirst();

        StringBuffer buffer = new StringBuffer();
        do {
            buffer.append("id "+result.getString(0)+" \n");
            buffer.append("name "+result.getString(1)+" \n");
            buffer.append("email "+result.getString(2)+" \n\n\n");


        }while (result.moveToNext());
        displayData(buffer.toString());
    }


    public void displayData(String data){
        displayText.setText(data);
    }

    public void button_update(View v){
        boolean result = myDatabase.dataUpdate(editId.getText().toString(),editname.getText().toString(),editEmail.getText().toString());
        if (result == true) Toast.makeText(MainActivity.this,"SuccessFully Data Updated ",Toast.LENGTH_SHORT).show();
        else Toast.makeText(MainActivity.this,"Data Update Failed ",Toast.LENGTH_SHORT).show();

    }

    public void button_delete(View v){
        boolean checker = myDatabase.delete(editId.getText().toString());
        if (checker == true) Toast.makeText(MainActivity.this,"Data Deletation Successfull ",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MainActivity.this,"Data Deletation Failed ",Toast.LENGTH_SHORT).show();
    }
}


