package com.example.android.hylda_1202154346_studycase5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    dbHelper myDatabase;
    RecyclerView recyclerView;
    Cursor cursor;
    Adapter adpt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mendeklarasi view yang digunakan
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        myDatabase = new dbHelper(this);
        cursor = myDatabase.getAllData();

        //menginisialisasi shared preference
        SharedPreferences sharedP = this.getApplicationContext().getSharedPreferences("Preferences", 0);
        int color = sharedP.getInt("Colourground", R.color.white);

        adpt = new Adapter(cursor, color, this);

        //mengambil data yang telah diinputkan lalu di masukkan menggunakan adapter
        adpt.swapCursor(myDatabase.getAllData());
        recyclerView.setAdapter(adpt);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //untuk menghapus data dengan arah swipe ke kiri maupun kekanan
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                String id = (String) viewHolder.itemView.getTag(R.string.key);
                //memanggil method deleteDataSwiping untuk menghapus data
                myDatabase.deleteDataSwipping(id);
                //mengambil kembali data yang masih ada/tidak dihapus
                adpt.swapCursor(myDatabase.getAllData());

            }
        }).attachToRecyclerView(recyclerView); //menampilkasn pada recycler view
    }

    //listener bagi tombol addtodo
    public void addToDo(View view) {

        //menjalankan intent ke class Todo
        startActivity(new Intent(MainActivity.this, ToDo.class));
    }

    //saat menu pada activity di buat
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //membuat menu dengan layout bernama menu_main
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //method yang berjalan saat item di pilih
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //mendapatkan id dari item yang dipilih
        int id = item.getItemId();
        //apabila klik setting
        if (id == R.id.action_settings) {
            //membuat intent baru dari to do ke setting
            startActivity(new Intent(MainActivity.this, setting.class));
            //mengakhiri aktivitas setelah intent dijalankan
            finish();
        }
        return true;
    }
}
