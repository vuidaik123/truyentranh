package com.example.kimvui.truyentranh;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kimvui.truyentranh.interfaces.IRecycler;

import java.util.List;

public class MyChapterAdapter extends RecyclerView.Adapter<MyChapterAdapter.MyViewHolder> {
    Context context;
    List<Chapter> chapterList;
    LayoutInflater inflater;
    @NonNull
    @Override
    public MyChapterAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=inflater.inflate(R.layout.chapter_item,viewGroup,false);
        return new MyViewHolder(view);
    }

    public MyChapterAdapter(Context context, List<Chapter> chapterList) {
        this.context = context;
        this.chapterList = chapterList;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public void onBindViewHolder(@NonNull MyChapterAdapter.MyViewHolder myViewHolder, int i) {

        myViewHolder.tv_chapter.setText(chapterList.get(i).Name);
        myViewHolder.setiRecycler(new IRecycler() {
            @Override
            public void onclick(View view, int position) {
                Common.chapterSelected=chapterList.get(position);
                Common.chapterIndex=position;
                context.startActivity(new Intent(context,ViewComicActivity.class));

            }
        });
    }

    @Override
    public int getItemCount() {
        return chapterList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_chapter;
        IRecycler iRecycler;
        public void setiRecycler(IRecycler iRecycler) {
            this.iRecycler = iRecycler;
        }
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_chapter=(TextView)itemView.findViewById(R.id.tv_chapter);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            iRecycler.onclick(v,getAdapterPosition());
        }
    }
}
