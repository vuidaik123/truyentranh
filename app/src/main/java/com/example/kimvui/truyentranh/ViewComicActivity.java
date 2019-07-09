package com.example.kimvui.truyentranh;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.wajahatkarim3.easyflipviewpager.BookFlipPageTransformer;

public class ViewComicActivity extends AppCompatActivity {
    ViewPager viewPager;
    TextView chapter_name;
    View back,next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_comic);
        viewPager=(ViewPager) findViewById(R.id.view_pager);
        chapter_name=(TextView)findViewById(R.id.chapter_name);
        back=(View)findViewById(R.id.chapter_back);
        next=(View)findViewById(R.id.chapter_next);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Common.chapterIndex==0){
                    Toast.makeText(ViewComicActivity.this, "Bạn đang đọc chương đầu", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Common.chapterIndex--;
                    getLinks(Common.chapterList.get(Common.chapterIndex));
                    chapter_name.setText(Common.formatString(Common.chapterList.get(Common.chapterIndex).Name));
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Common.chapterIndex==Common.chapterList.size()-1){
                    Toast.makeText(ViewComicActivity.this, "Bạn đang đọc chương cuối", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Common.chapterIndex++;
                    getLinks(Common.chapterList.get(Common.chapterIndex));
                    chapter_name.setText(Common.formatString(Common.chapterList.get(Common.chapterIndex).Name));
                }
            }
        });
        getLinks(Common.chapterSelected);

    }
      private void getLinks(Chapter chapter) {
        if(chapter.Links!=null){
            if(chapter.Links.size()>0){
                MyViewAdapter adapter=new MyViewAdapter(getBaseContext(),chapter.Links);
                viewPager.setAdapter(adapter);
                chapter_name.setText(Common.formatString(Common.chapterSelected.Name));
                BookFlipPageTransformer bookFlipPageTransformer=new BookFlipPageTransformer();
                bookFlipPageTransformer.setScaleAmountPercent(10f);
                viewPager.setPageTransformer(true,bookFlipPageTransformer);
            }
            else Toast.makeText(this, "no", Toast.LENGTH_SHORT).show();
            }
            else Toast.makeText(this, "chưa cập nhật", Toast.LENGTH_SHORT).show();
            

    }
}