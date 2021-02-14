package edu.neu.madcourse.numad21sp_colinriley;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LinkRviewAdapter extends RecyclerView.Adapter<LinkRViewHolder> {

    private final List<LinkItemCard> itemList;
    private ButtonClickListener listener;


    public LinkRviewAdapter(List<LinkItemCard> itemList) {
        this.itemList = itemList;
    }

    public void setOnButtonClickListener(ButtonClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public LinkRViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.link_item_card, parent, false);
        return new LinkRViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull LinkRViewHolder holder, int position) {
        LinkItemCard currentItem = this.itemList.get(position);

        holder.linkName.setText(currentItem.getLinkName());
        holder.url.setText(currentItem.getUrl());
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
