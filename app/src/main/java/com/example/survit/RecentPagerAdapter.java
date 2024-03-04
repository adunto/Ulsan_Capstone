package com.example.survit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecentPagerAdapter extends RecyclerView.Adapter<RecentPagerAdapter.ViewHolder> {

    private Context context;

    public RecentPagerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String[] titles = {"title1", "title2","title3","title4","title5","title6", "title7", "title8", "title9"};

        holder.title.setText(titles[position]);

        // 각 포지션에 따라서 아이템 설정
        holder.favoriteBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    holder.favoriteBtn.setButtonDrawable(R.drawable.fill_favorit_icon);
                }
                else {
                    holder.favoriteBtn.setButtonDrawable(R.drawable.favorite_icon);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 9; // 아이템 수
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, topic, qCnt, leadTime, expireTime;

        CheckBox favoriteBtn;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.grid_item_title);
            topic = itemView.findViewById(R.id.grid_item_topic);
            qCnt = itemView.findViewById(R.id.grid_item_QCnt);
            leadTime = itemView.findViewById(R.id.grid_item_leadTime);
            expireTime = itemView.findViewById(R.id.grid_item_expire);

            favoriteBtn = itemView.findViewById(R.id.grid_item_favorite);


        }
    }
}