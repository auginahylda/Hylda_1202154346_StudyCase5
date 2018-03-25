package com.example.android.hylda_1202154346_studycase5;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class setting extends AppCompatActivity {
    TextView sc;
    int idcolor;
    AlertDialog.Builder alert;
    SharedPreferences.Editor pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setTitle("Setting");

        //menambahkan alert
        alert = new AlertDialog.Builder(this);

        //menginisiasi shared preference
        SharedPreferences sharedP = getApplicationContext().getSharedPreferences("Preferences", 0);
        pref = sharedP.edit();
        idcolor = sharedP.getInt("Colourground", R.color.white);
        //inisiasi text view di layout
        sc = findViewById(R.id.shapecolor);
        //mengatur warna dengan color id
        sc.setText(getShapeColor(idcolor));
    }

    //listener tombol back
    @Override
    public void onBackPressed() {
        //menjalankan intent kembali ke MainActivity
        startActivity(new Intent(setting.this, MainActivity.class));
        //menutup activity setelah intent di jalankan
        finish();
    }

    //method yang dijalankan ketika pilihan pada menu dipilih
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            //menjalankan method onbackpressed
            this.onBackPressed();
        }
        return true;
    }

    //mendapatkan string warna yang telah di buat di layout set color
    public String getShapeColor(int i){
        if (i==R.color.red){
            return "Red";
        }else if (i==R.color.green){
            return "Green";
        }else if (i==R.color.blue){
            return "Blue";
        }else{
            return "Default";
        }
    }

    //mendapatkan id dari warna yang akan dipilih
    public int getColorid(int i){
        if (i==R.color.red){
            return R.id.red;
        }else if (i==R.color.green){
            return R.id.green;
        }else if (i==R.color.blue){
            return R.id.blue;
        }else{
            return R.id.white;
        }
    }

    public void choosecolor(View view) {
        //set title dari alert dialog menjadi Shape Color
        alert.setTitle("Choose Favorite Shape Color");
        //membuat view baru
        View view1 = getLayoutInflater().inflate(R.layout.setcolor, null);
        //menampilkan view
        alert.setView(view1);

        //mengakses radiogroup pada layout
        final RadioGroup rg = view1.findViewById(R.id.radiocolor);
        rg.check(getColorid(idcolor));

        //aksi saat klik OK pada alert dialog
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //mendapatkan id radio button yang di pilih
                int a = rg.getCheckedRadioButtonId();
                switch (a){ //switch case inisialisasi id setiap warna
                    //ketika dipilih
                    case R.id.red:
                        idcolor = R.color.red;
                        break;
                    case R.id.green:
                        idcolor = R.color.green;
                        break;
                    case R.id.blue:
                        idcolor = R.color.blue;
                        break;
                    case R.id.white:
                        idcolor = R.color.white;
                        break;
                }
                //memilih Warna menjadi color id yang dipilih
                sc.setText(getShapeColor(idcolor));
                //meletakan shared preference
                pref.putInt("Colourground", idcolor);
                //mengcommit shared preference
                pref.commit();
            }
        });

        //negative button untuk dialog
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        //membuat dan menampilkan alert dialog
        alert.create().show();
    }
}
