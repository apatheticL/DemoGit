package com.nhatle.demosqlline;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicViewHoder>{
    private IStore inter;
    private int img[] = {
           R.drawable.im_1, R.drawable.im_2, R.drawable.im_3, R.drawable.im_4, R.drawable.im_5, R.drawable.im_6,
           R.drawable.im_7, R.drawable.im_10, R.drawable.im_12, R.drawable.im_13, R.drawable.im_14,R.drawable.im_15,
           R.drawable.im_22, R.drawable.im_27,R.drawable.im_28

    };
    public TopicAdapter(IStore inter) {
        this.inter = inter;
    }

    @NonNull
    @Override
    public TopicViewHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view  = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_topic,viewGroup,false);
        return new TopicViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TopicViewHoder topicViewHoder, int i) {
        Topic topic = inter.getTopic(i);
        topicViewHoder.tvName.setText(topic.getName());
        topicViewHoder.imageView.setImageResource(img[i]);

        topicViewHoder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inter.onClick(topicViewHoder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return inter.getCount();
    }

    static class TopicViewHoder extends RecyclerView.ViewHolder{
        private TextView tvName;
        private ImageView imageView;
        public TopicViewHoder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            imageView = itemView.findViewById(R.id.image_topic);
        }
    }
}
