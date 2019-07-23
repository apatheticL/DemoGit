package com.nhatle.demosqlline;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreViewHoder> {
    private IStore inter;
    private int img = R.drawable.ahihi;

    public StoreAdapter(IStore inter) {
        this.inter = inter;
    }

    @NonNull
    @Override
    public StoreViewHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_store,viewGroup,false);
        return new StoreViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final StoreViewHoder storeViewHoder, int i) {
        Store store = inter.getStore(i);
        storeViewHoder.tv_name_store.setText(store.getName());
        storeViewHoder.img_store.setImageResource(img);
        storeViewHoder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inter.onClick(storeViewHoder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return inter.getCount();
    }

    static class StoreViewHoder extends RecyclerView.ViewHolder{
        private TextView tv_name_store;
        private ImageView img_store;
        public StoreViewHoder(@NonNull View itemView) {
            super(itemView);
            tv_name_store = itemView.findViewById(R.id.name_store);
            img_store = itemView.findViewById(R.id.img_store);
        }
    }
}
