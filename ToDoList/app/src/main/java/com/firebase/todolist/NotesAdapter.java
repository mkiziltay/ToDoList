package com.firebase.todolist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;

import java.util.ArrayList;

public class NotesAdapter extends BaseAdapter {

    int stat=0;

    Context context;
    ArrayList<NotesModel> list;

    public NotesAdapter(Context context, ArrayList<NotesModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.not_list_item,viewGroup,false);
        TextView titleV,descriptionV,addtimeV,notftimeV;
        ImageView notifyImageV;
        titleV = view.findViewById(R.id.title);
        descriptionV = view.findViewById(R.id.desc);
        addtimeV = view.findViewById(R.id.addtime);
        notftimeV = view.findViewById(R.id.notftime);
        notifyImageV = view.findViewById(R.id.notfImage);
        ImageView collapse = view.findViewById(R.id.collapse); collapse.setImageResource(R.drawable.arrow_down);


        addtimeV.setText(list.get(i).added);
        descriptionV.setText(list.get(i).description);
        titleV.setText(list.get(i).title);

        if (list.get(i).getNotify()){
            notftimeV.setText(list.get(i).notification);
            notftimeV.setTextColor(R.color.green);
            notifyImageV.setBackgroundResource(R.drawable.remind);
        }else{notftimeV.setText("Yok");
        notftimeV.setTextColor(R.color.red);
            notifyImageV.setBackgroundResource(R.drawable.noremind);}

        collapse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (stat==0){
                    stat=1;
                    collapse.setImageResource(R.drawable.arrow_up);
                    descriptionV.setVisibility(View.VISIBLE);
                }else{  stat=0;
                    collapse.setImageResource(R.drawable.arrow_down);
                    descriptionV.setVisibility(View.GONE);}
            }
        });
        return view;
    }
}
