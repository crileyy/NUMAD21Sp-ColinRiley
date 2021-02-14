package edu.neu.madcourse.numad21sp_colinriley;

import android.content.Intent;
import android.net.Uri;

public class LinkItemCard implements ItemClickListener {

    private final String linkName;
    private final String url;

    public LinkItemCard(String linkName, String url) {
        this.linkName = linkName;
        this.url = url;
    }

    @Override
    public void onItemClick(int position) {

    }

    public String getLinkName() {
        return linkName;
    }

    public String getUrl() {
        return url;
    }
}
