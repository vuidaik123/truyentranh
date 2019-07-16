package com.example.kimvui.truyentranh;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class SeachActivity extends AppCompatActivity {
    RecyclerView recycler_search;
    EditText edt_seach;
    Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seach);
        edt_seach = findViewById(R.id.edt_seach);
        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SeachActivity.this, FragmentComic.class));
            }
        });
        edt_seach.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                fetchSeachComic(edt_seach.getText().toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                    fetchSeachComic(edt_seach.getText().toString());

            }

            @Override
            public void afterTextChanged(Editable s) {
                fetchSeachComic(edt_seach.getText().toString());
            }
        });
        recycler_search = findViewById(R.id.recycler_seach);
        recycler_search.setHasFixedSize(true);
        recycler_search.setLayoutManager(new GridLayoutManager(this, 2));

    }

//    private void showSeachDialog(){
//        AlertDialog.Builder alertDialog=new AlertDialog.Builder(SeachActivity.this);
//        alertDialog.setTitle("Tìm kiếm");
//        final LayoutInflater inflater=this.getLayoutInflater();
//        View view=inflater.inflate(R.layout.dialog_seach,null);
//        final EditText et_seach=(EditText)view.findViewById(R.id.et_seach);
//        et_seach.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                fetchSeachComic(et_seach.getText().toString());
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//        alertDialog.setView(view);
//        alertDialog.setNegativeButton("Trở về", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//        alertDialog.setPositiveButton("Tìm kiếm", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//                fetchSeachComic(et_seach.getText().toString());
//            }
//        });
//        alertDialog.show();
//    }

    private void fetchSeachComic(String s) {
        List<Comic> comics_seach = new ArrayList<>();
        for (Comic comic : Common.comicsList) {
            if (comic.Name.contains(s))
                comics_seach.add(comic);
        }
        recycler_search.setAdapter(new MyComicAdapter(SeachActivity.this, comics_seach));


    }
}
