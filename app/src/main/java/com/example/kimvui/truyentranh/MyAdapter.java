package com.example.kimvui.truyentranh;

import java.util.List;

import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class MyAdapter extends SliderAdapter {
    private List<String> listImages;
    public MyAdapter(List<String> listImages){
        this.listImages=listImages;
    }
    @Override
    public int getItemCount() {
        return listImages.size();
    }

    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder imageSlideViewHolder) {
        imageSlideViewHolder.bindImageSlide(listImages.get(position));
    }

}
