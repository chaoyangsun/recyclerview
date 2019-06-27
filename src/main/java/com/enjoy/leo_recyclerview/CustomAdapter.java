package com.enjoy.leo_recyclerview;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private Context context;
    private List<Data> list;

    public CustomAdapter(Context context, List<Data> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public CustomAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onViewRecycled(CustomViewHolder holder) {
        super.onViewRecycled(holder);
    }

    @Override
    public void onBindViewHolder(final CustomAdapter.CustomViewHolder holder, final int position) {
        final Data data = list.get(position);
        holder.iv.setImageResource(data.getResId());
        holder.et.setTag(data.getUid());
        holder.et.setText(data.getStr());

        final TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (holder.et.getTag() instanceof Long && (long)holder.et.getTag() == data.getUid() && holder.et.hasFocus()){
                    data.setStr(editable.toString());
                }
            }
        };
        holder.et.addTextChangedListener(textWatcher);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv;
        private EditText et;


        public CustomViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv);
            et =  itemView.findViewById(R.id.et);
        }
        public void clear(){
            iv.setImageResource(0);
            et.setText(null);
        }
    }
}
