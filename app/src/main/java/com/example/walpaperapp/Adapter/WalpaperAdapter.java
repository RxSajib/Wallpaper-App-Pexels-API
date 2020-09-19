package com.example.walpaperapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.walpaperapp.DataManager;
import com.example.walpaperapp.FullScreenActivity;
import com.example.walpaperapp.Modeal.WalpaperModel;
import com.example.walpaperapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class WalpaperAdapter extends RecyclerView.Adapter<WalpaperAdapter.WalpaperHolder>{

    private Context context;
    private List<WalpaperModel> walpaperHolderList;

    public WalpaperAdapter(Context context, List<WalpaperModel> walpaperHolderList) {
        this.context = context;
        this.walpaperHolderList = walpaperHolderList;
    }

    @NonNull
    @Override
    public WalpaperHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.walpaper_layout, parent, false);

        return new WalpaperHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WalpaperHolder holder, final int position) {
        Picasso.with(context).load(walpaperHolderList.get(position).getMediumurl()).into(holder.photoimage);

        holder.photoimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FullScreenActivity.class);
                intent.putExtra(DataManager.ClickImageUri, walpaperHolderList.get(position).getOrginalurl());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return walpaperHolderList.size();
    }

    public class WalpaperHolder extends RecyclerView.ViewHolder{

        private ImageView photoimage;

        public WalpaperHolder(@NonNull View itemView) {
            super(itemView);

            photoimage = itemView.findViewById(R.id.ImageView);
        }
    }
}
