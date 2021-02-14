package edu.neu.madcourse.numad21sp_colinriley;

public class LinkItemCard implements ButtonClickListener {

    private final String linkName;
    private final String url;

    public LinkItemCard(String linkName, String url) {
        this.linkName = linkName;
        this.url = url;
    }

    @Override
    public void onButtonClick(String url) {

    }

    public String getLinkName() {
        return linkName;
    }

    public String getUrl() {
        return url;
    }
}
