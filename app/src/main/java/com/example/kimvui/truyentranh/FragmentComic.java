package com.example.kimvui.truyentranh;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kimvui.truyentranh.interfaces.IBannerLoadDone;
import com.example.kimvui.truyentranh.interfaces.IComicLoadDone;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import ss.com.bannerslider.Slider;

public class FragmentComic extends Fragment implements IComicLoadDone, IBannerLoadDone, View.OnClickListener {
    Slider slider;
    DatabaseReference banners,comics;
    IComicLoadDone comicListener;
    IBannerLoadDone bannerListener;
    AlertDialog alertDialog;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView_comic;
    TextView tv_comic;
    ImageView btn_seach;
    View view;
    Button btnSend;
    private NotificationCompat.Builder notBuilder;
    private static final int MY_NOTIFICATION_ID = 12345;

    private static final int MY_REQUEST_CODE = 100;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        btnSend = (Button)view.findViewById(R.id.btn_send);
//        btnSend.setOnClickListener(this);
//        this.notBuilder = new NotificationCompat.Builder(getActivity());
//        this.notBuilder.setAutoCancel(true);
        boolean ret = ConnectionReceiver.isConnected();
        if (ret == true) {
            view=inflater.inflate(R.layout.fragment_comic,container,false);

        banners=FirebaseDatabase.getInstance().getReference("Banners");
        comics=FirebaseDatabase.getInstance().getReference("Comics");
        slider=(Slider)view.findViewById(R.id.slider);
        slider.init(new PicassoLoadingService());
        comicListener=this;
        bannerListener=this;

        btn_seach= (ImageView)view.findViewById(R.id.btn_seach);
        btn_seach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),SeachActivity.class));
            }
        });
        swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadBanner();
                loadComic();
            }
        });
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                loadBanner();
                loadComic();
            }
        });
        recyclerView_comic=(RecyclerView)view.findViewById(R.id.recycler_comic);
        recyclerView_comic.setHasFixedSize(true);
        recyclerView_comic.setLayoutManager(new GridLayoutManager(getActivity(),2));

        tv_comic=(TextView)view.findViewById(R.id.tv_comic);
        } else {
            Toast.makeText(getContext(), "Thiết bị chưa kết nối internet", Toast.LENGTH_SHORT).show();
            view=inflater.inflate(R.layout.activity_check_internet,container,false);
        }
        return view;
    }

    private void loadBanner() {
        banners.addListenerForSingleValueEvent(new ValueEventListener() {
            List<String> bannerList=new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot bannershot:dataSnapshot.getChildren()){
                    String image=bannershot.getValue(String.class);
                    bannerList.add(image);
                }
                bannerListener.onBannerLoadDoneListener(bannerList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void loadComic(){
        alertDialog=new SpotsDialog.Builder().setContext(getContext())
                .setCancelable(false)
                .setMessage("Vui lòng đợi...")
                .build();
        alertDialog.show();
        if(!swipeRefreshLayout.isRefreshing())
            comics.addListenerForSingleValueEvent(new ValueEventListener() {
                List<Comic> comic_load=new ArrayList<>();

                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot comicSnapshot:dataSnapshot.getChildren()){
                        Comic comic=comicSnapshot.getValue(Comic.class);
                        comic_load.add(comic);
                    }
                    comicListener.onComicLoadDoneListener(comic_load);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getActivity(), ""+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
    }

    @Override
    public void onBannerLoadDoneListener(List<String> banners) {
        slider.setAdapter(new MyAdapter(banners));
    }

    @Override
    public void onComicLoadDoneListener(List<Comic> comicsList) {
        Common.comicsList=comicsList;
        recyclerView_comic.setAdapter(new MyComicAdapter(getContext(),comicsList));
        tv_comic.setText(new StringBuilder("Truyện tranh hay nhất (")
                .append(comicsList.size())
                .append(")"));
        if(!swipeRefreshLayout.isRefreshing())
            alertDialog.dismiss();
    }



    public void notiButtonClicked(View view)  {

        // --------------------------
        // Chuẩn bị một thông báo
        // --------------------------

        this.notBuilder.setSmallIcon(R.mipmap.ic_launcher);
        this.notBuilder.setTicker("This is a ticker");

        // Sét đặt thời điểm sự kiện xẩy ra.
        // Các thông báo trên Panel được sắp xếp bởi thời gian này.
        this.notBuilder.setWhen(System.currentTimeMillis()+ 10* 1000);
        this.notBuilder.setContentTitle("This is title");
        this.notBuilder.setContentText("This is content text ....");

        // Tạo một Intent
        Intent intent = new Intent(getActivity(), FragmentComic.class);


        // PendingIntent.getActivity(..) sẽ start mới một Activity và trả về
        // đối tượng PendingIntent.
        // Nó cũng tương đương với gọi Context.startActivity(Intent).
        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), MY_REQUEST_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);


        this.notBuilder.setContentIntent(pendingIntent);

        // Lấy ra dịch vụ thông báo (Một dịch vụ có sẵn của hệ thống).
        NotificationManager notificationService  =
                (NotificationManager)getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

        // Xây dựng thông báo và gửi nó lên hệ thống.

        Notification notification =  notBuilder.build();
        notificationService.notify(MY_NOTIFICATION_ID, notification);

    }

    @Override
    public void onClick(View v) {
       
    }
}
