package com.example.news;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ViewHolder>
{

    private final Context context;
    private final ArrayList<News> items = new ArrayList<>();

    public NewsListAdapter(Context context) {
        this.context = context;

    }


    @NonNull
    @Override           // Where to get the single card as viewholder Object
    public NewsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent,false);
        return new ViewHolder(view);
    }


    @Override           // What will happen after we create the viewholder object
    public void onBindViewHolder(@NonNull NewsListAdapter.ViewHolder holder, int position) {

        News news = items.get(position);
        holder.mTitle.setText(news.getTitle());
        holder.mAuthor.setText(news.getAuthor());
        // Glide library used to show IMAGE
        Glide.with(holder.itemView.getContext()).load(news.getImageUrl()).into(holder.mImage);
    }

    @Override            // How many items?
    public int getItemCount() {
        return items.size();
    }

    public void updateNews(ArrayList<News> newsAfterUpdate)
    {
        // agr phle se kuch h to empty kr do
        items.clear();
        // updateed news add kr do  uske liye hme call krna pdega  notifyDataSetChanged();
        items.addAll(newsAfterUpdate);

        notifyDataSetChanged();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView mTitle;
        public TextView mAuthor;
        public ImageView mImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

             mTitle = itemView.findViewById(R.id.title12);
             mAuthor = itemView.findViewById(R.id.author12);
             mImage = itemView.findViewById(R.id.image12);


        }

        @Override
        public void onClick(View v) {
            int position = this.getBindingAdapterPosition();
            String url= items.get(position).getUrl();
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            CustomTabsIntent customTabsIntent = builder.build();
            customTabsIntent.launchUrl(context, Uri.parse(url));

        }
    }
}
