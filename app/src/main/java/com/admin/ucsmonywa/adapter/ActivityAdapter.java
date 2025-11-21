package com.admin.ucsmonywa.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.admin.ucsmonywa.R;
import com.admin.ucsmonywa.activity.ActivityDetail;
import com.admin.ucsmonywa.constants.AppConstants;
import com.admin.ucsmonywa.model.ActivityInfo;
import com.admin.ucsmonywa.utils.ImageLoader;

import java.util.List;

public class ActivityAdapter  extends  RecyclerView.Adapter<ActivityAdapter.ActivityViewHolder> {

    List<ActivityInfo> activityInfoList;
    Context context;

    public ActivityAdapter(List<ActivityInfo> activityInfoList, Context context) {
        this.activityInfoList = activityInfoList;
        this.context = context;
    }

    @NonNull
    @Override
    public ActivityAdapter.ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_noticeboard, parent, false);
        ActivityViewHolder viewHolder = new ActivityViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityAdapter.ActivityViewHolder holder, int position) {

        ActivityInfo activityInfo = activityInfoList.get(position);
        holder.title.setText(activityInfo.getImageTitle());
        holder.detail.setText(activityInfo.getImageDetail());

        ImageLoader.loadImage(holder.imageView, activityInfo.getImage());
    }

    @Override
    public int getItemCount() {
        return activityInfoList.size();
    }

    public class ActivityViewHolder extends RecyclerView.ViewHolder {

        public TextView title, detail;
        public ImageView imageView;

        public ActivityViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.img_title);
            detail = itemView.findViewById(R.id.img_detail);
            imageView = itemView.findViewById(R.id.noticeboard_img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {

                    Intent intent = new Intent(view.getContext(), ActivityDetail.class);

                    final ActivityInfo info = activityInfoList.get(getAdapterPosition());
                    intent.putExtra(AppConstants.IntentKeys.TITLE, info.getImageTitle());
                    intent.putExtra(AppConstants.IntentKeys.DETAIL, info.getImageDetail());
                    intent.putExtra(AppConstants.IntentKeys.IMAGE, info.getImage());
                    view.getContext().startActivity(intent);
                }


            });
        }
    }
}