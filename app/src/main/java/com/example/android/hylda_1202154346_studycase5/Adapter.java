package com.example.android.hylda_1202154346_studycase5;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by hp on 25/03/2018.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    //deklarasi variabel
    private Cursor mCursor;
    private Context mContext;
    int color;

    public Adapter(Cursor mCursor, int color, Context mContext) {
        //deklarasi konstruktor
        this.mCursor = mCursor;
        this.mContext = mContext;
        this.color = color;

    }

    //holder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        //membuat view baru
        View view = inflater.inflate(R.layout.text_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)) {
            return;
        }
        //memilih nilai yang disimpan oleh viewholder
        String id = mCursor.getString(mCursor.getColumnIndex(dbContract.DatabaseScheme.ID_DATABASE));
        String todo = mCursor.getString(mCursor.getColumnIndex(dbContract.DatabaseScheme.TODO_NAME));
        String desc = mCursor.getString(mCursor.getColumnIndex(dbContract.DatabaseScheme.DESCRIPTION));
        String prio = mCursor.getString(mCursor.getColumnIndex(dbContract.DatabaseScheme.PRIORITY));
        holder.infoText1.setText(todo);
        holder.infoText2.setText(desc);
        holder.infoText3.setText(prio);
        holder.itemView.setTag(todo);
        holder.itemView.setTag(desc);
        holder.itemView.setTag(prio);
        holder.itemView.setTag(R.string.key, id);

        //menentukan warna
        holder.cv.setCardBackgroundColor(mContext.getResources().getColor(this.color));


    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    //membuat aksi dari perubahan terhadap database kita memerlukan pertukaran cursor
    public void swapCursor(Cursor newCursor) {

        //adanya penutup cursor
        if (mCursor != null) mCursor.close();
        mCursor = newCursor;
        if (newCursor != null) {

            //memaksa recyclerview untuk merefresh data
            this.notifyDataSetChanged();
        }
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        //deklarasi variabel
        TextView infoText1, infoText2, infoText3;
        CardView cv;

        public MyViewHolder(View itemView) {
            super(itemView);
            //inisialisasi variabel terhadap id yang sudah ditentukan

            infoText1 = (TextView) itemView.findViewById(R.id.recycling_text);
            infoText2 = (TextView) itemView.findViewById(R.id.recycling_text2);
            infoText3 = (TextView) itemView.findViewById(R.id.recycling_text3);
            cv = (CardView) itemView.findViewById(R.id.cardView);

        }
    }
}
