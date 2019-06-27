package com.enjoy.leo_recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private Button btn;

    private int[] iv = new int[]{
            R.mipmap.icon_0,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.rv);
        btn = findViewById(R.id.btn);

        rv.setLayoutManager(new GridLayoutManager(this, 1));

        final List<Data> list = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Data data = new Data();
            data.setUid(System.nanoTime());
            data.setStr("item " + i);
            data.setResId(iv[0]);
            list.add(data);
        }
        final CustomAdapter adapter = new CustomAdapter(this, list);
        rv.setAdapter(adapter);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data data = new Data();
                data.setUid(System.nanoTime());
                data.setResId(iv[0]);
                list.add(0,data);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
