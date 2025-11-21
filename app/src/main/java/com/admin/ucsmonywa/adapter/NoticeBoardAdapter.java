package com.admin.ucsmonywa.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.admin.ucsmonywa.R;
import com.admin.ucsmonywa.activity.NoticeBoardDetail;
import com.admin.ucsmonywa.constants.AppConstants;
import com.admin.ucsmonywa.model.ImageUploadInfo;
import com.admin.ucsmonywa.utils.ImageLoader;
import com.admin.ucsmonywa.utils.Utils;

import java.util.List;



public class NoticeBoardAdapter extends  RecyclerView.Adapter<NoticeBoardAdapter.NoticeBoardViewHolder> {

    List<ImageUploadInfo> imageUploadInfoList;
    Context context;





    public NoticeBoardAdapter( Context context,List<ImageUploadInfo> imageUploadInfoList) {
        this.imageUploadInfoList = imageUploadInfoList;
        this.context = context;
    }

    @NonNull
    @Override
    public NoticeBoardAdapter.NoticeBoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_noticeboard,parent,false);
        NoticeBoardViewHolder viewHolder=new NoticeBoardViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull final NoticeBoardAdapter.NoticeBoardViewHolder holder, final int position) {

        final ImageUploadInfo imageUploadInfo=imageUploadInfoList.get(position);
        holder.title.setText(imageUploadInfo.getImageTitle());
        holder.detail.setText(imageUploadInfo.getImageDetail());
        holder.date.setText(Utils.DateFormat(imageUploadInfo.getDate()));

        ImageLoader.loadImage(holder.imageView, imageUploadInfo.getImageUrl());

    }

    @Override
    public int getItemCount() {
        return imageUploadInfoList.size();
    }
    class NoticeBoardViewHolder extends RecyclerView.ViewHolder{

        public TextView title,detail,date;
        public ImageView imageView;

        public NoticeBoardViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.img_title);
            imageView=itemView.findViewById(R.id.noticeboard_img);
            detail=itemView.findViewById(R.id.img_detail);
            date=itemView.findViewById(R.id.date);




           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(final View view) {

                   Intent intent=new Intent(view.getContext(), NoticeBoardDetail.class);


                   final ImageUploadInfo info= imageUploadInfoList.get(getAdapterPosition());
                   intent.putExtra(AppConstants.IntentKeys.TITLE, info.getImageTitle());
                   intent.putExtra(AppConstants.IntentKeys.DETAIL, info.getImageDetail());
                   intent.putExtra(AppConstants.IntentKeys.IMAGE, info.getImageUrl());
                   intent.putExtra(AppConstants.IntentKeys.DATE, info.getDate());
                   Log.e("Data","carry");
                   view.getContext().startActivity(intent);

               }
           });

        }
        }
    }
