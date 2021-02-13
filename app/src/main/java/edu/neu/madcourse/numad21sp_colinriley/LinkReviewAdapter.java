package edu.neu.madcourse.numad21sp_colinriley;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LinkReviewAdapter extends RecyclerView.Adapter<LinkRViewHolder> {

    private final List<LinkItemCard> itemList;
    private ItemClickListener listener;

    public LinkReviewAdapter(List<LinkItemCard> itemList) {
        this.itemList = itemList;
    }

    public void setOnItemClickListener(ItemClickListener listener) {
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

        holder.linkName.setText("");
        holder.url.setText("");
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
