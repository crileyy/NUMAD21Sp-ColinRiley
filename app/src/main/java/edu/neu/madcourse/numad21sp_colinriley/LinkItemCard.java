package edu.neu.madcourse.numad21sp_colinriley;

public class LinkItemCard implements ItemClickListener {

    private final String linkName;
    private final String url;

    public LinkItemCard(String linkName, String url) {
        this.linkName = linkName;
        this.url = url;
    }

    @Override
    public void onItemClick(int position) {
        // TODO fill in with something or remove
    }

    public String getLinkName() {
        return linkName;
    }

    public String getUrl() {
        return url;
    }
}
