package com.example.kimvui.truyentranh;

import android.app.Notification;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class NotificationActivity extends Fragment {
    Button btn_notification;
    EditText et_title, et_body;
    Notificationdemo notificationdemo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_notification, container, false);
        super.onCreate(savedInstanceState);
        btn_notification = view.findViewById(R.id.btn_notification);
        et_title = view.findViewById(R.id.et_title);
        et_body = view.findViewById(R.id.et_body);
        notificationdemo = new Notificationdemo(getActivity());
        btn_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = et_title.getText().toString();
                String content = et_body.getText().toString();
                Notification.Builder builder = notificationdemo.gettambay(title, content);
                notificationdemo.getManager().notify(new Random().nextInt(), builder.build());
                Toast.makeText(notificationdemo, "as", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}
