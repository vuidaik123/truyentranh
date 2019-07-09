package com.example.kimvui.truyentranh;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kimvui.truyentranh.interfaces.IRecycler;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MyComicAdapter extends RecyclerView.Adapter<MyComicAdapter.MyViewHolder> {
    Context context;
    List<Comic> comicList ;
    LayoutInflater inflater;


    public MyComicAdapter(Context context, List<Comic> comicList) {
        this.context = context;
        this.comicList = comicList;
        inflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=inflater.inflate(R.layout.comic_item,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Picasso.get().load(comicList.get(i).Image).into(myViewHolder.im_comic);
        myViewHolder.tv_name.setText(comicList.get(i).Name);
        myViewHolder.setiRecycler(new IRecycler() {
            @Override
            public void onclick(View view, int position) {
               Common.comicSelected=comicList.get(position);

                if(Common.comicSelected.Chapters!=null)
                context.startActivity(new Intent(context,ChaptersActivity.class));
                else Toast.makeText(context, "Chưa cập nhật", Toast.LENGTH_SHORT).show();


            }
        });
    }

    @Override
    public int getItemCount() {
        return comicList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView im_comic;
        TextView tv_name;
        IRecycler iRecycler;

        public void setiRecycler(IRecycler iRecycler) {
            this.iRecycler = iRecycler;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            im_comic=(ImageView)itemView.findViewById(R.id.im_comic);
            tv_name=(TextView)itemView.findViewById(R.id.tv_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            iRecycler.onclick(v,getAdapterPosition());
        }
    }
}
