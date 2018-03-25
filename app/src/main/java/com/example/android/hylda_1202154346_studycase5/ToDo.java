package com.example.android.hylda_1202154346_studycase5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ToDo extends AppCompatActivity {

    //mendeklarasi variabel
    dbHelper myDatabase;
    EditText name, desc , prio;
    RecyclerView recyclerView;
    Adapter adapt;
    Cursor cursor;
    Toast myToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        recyclerView = (RecyclerView)findViewById(R.id.rv);
        //pembuatan objek dbHelper baru
        myDatabase = new dbHelper(this);
        //untuk mengambil data yang ada
        cursor  = myDatabase.getAllData();

        //inisiasi variabel terhadap id view
        name = (EditText)findViewById(R.id.namaTodo);
        desc = (EditText)findViewById(R.id.descTodo);
        prio = (EditText)findViewById(R.id.prioTodo);

        //deklarasi sharedPreference
        SharedPreferences sharedP = this.getApplicationContext().
                getSharedPreferences("Preferences", 0);
        int color = sharedP.getInt("Colourground", R.color.white);

        //pembuatan objek Adapter yang baru
        adapt = new Adapter(cursor, color, this);
    }

    //method listener untuk tombol AddTOdo
    public void addDataClicked(View view) {
        boolean isInserted;
        //deklarasi variabel penampung isi dari edit text name, desc, maupun prio
        String todoname = name.getText().toString();
        String description = desc.getText().toString();
        String priority = prio.getText().toString();
        if(myToast!=null){
            myToast.cancel();
        }
        //kondisi yang harus dipenuhi
        if(!todoname.isEmpty()&&!description.isEmpty()&&!priority.isEmpty()) {
            isInserted =  myDatabase.insertData(todoname,description,priority);
            if(!isInserted){
                //jika tidak berhasil diinput
                myToast.makeText(this,"Try again",
                        Toast.LENGTH_SHORT).show();
            }else {
                //jika berhasil diinput
                myToast.makeText(this,"Sucessful Saved.",
                        Toast.LENGTH_SHORT).show();
                //menjalankan intent kembali pada MainActivity
                startActivity(new Intent(ToDo.this, MainActivity.class));
            }
        }else {
            //jika edit text ada yang tidak terisi
            myToast.makeText(this,"Try again",
                    Toast.LENGTH_SHORT).show();
        }

    }

}
