package edu.neu.madcourse.numad21sp_colinriley;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class LinkCollectorActivity extends AppCompatActivity {

    private List<LinkItemCard> itemList = new ArrayList<>();

    private RecyclerView recyclerView;
    private LinkRviewAdapter rviewAdapter;
    private RecyclerView.LayoutManager rLayoutManger;
    private FloatingActionButton addButton;

    private static final String KEY_OF_INSTANCE = "KEY_OF_INSTANCE";
    private static final String NUMBER_OF_ITEMS = "NUMBER_OF_ITEMS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_collector);
        init(savedInstanceState);

        addButton = findViewById(R.id.addLinkButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = 0;
                addItem(pos);
            }
        });
    }

    // Handling Orientation Changes on Android
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        int size = itemList == null ? 0 : itemList.size();
        outState.putInt(NUMBER_OF_ITEMS, size);

        // Need to generate unique key for each item
        // This is only a possible way to do, please find your own way to generate the key
        for (int i = 0; i < size; i++) {
            // put linkName information into instance
            outState.putString(KEY_OF_INSTANCE + i + "0", itemList.get(i).getLinkName());
            // put url information into instance
            outState.putString(KEY_OF_INSTANCE + i + "1", itemList.get(i).getUrl());
        }
        super.onSaveInstanceState(outState);

    }

    private void init(Bundle savedInstanceState) {
        initialItemData(savedInstanceState);
        createRecyclerView();
    }

    private void initialItemData(Bundle savedInstanceState) {
        // Not the first time to open this Activity
        if (savedInstanceState != null && savedInstanceState.containsKey(NUMBER_OF_ITEMS)) {
            if (itemList == null || itemList.size() == 0) {

                int size = savedInstanceState.getInt(NUMBER_OF_ITEMS);

                // Retrieve keys we stored in the instance
                for (int i = 0; i < size; i++) {
                    String linkName = savedInstanceState.getString(KEY_OF_INSTANCE + i + "0");
                    String url = savedInstanceState.getString(KEY_OF_INSTANCE + i + "1");

                    LinkItemCard itemCard = new LinkItemCard(linkName, url);

                    itemList.add(itemCard);
                }
            }
        }
        // The first time to open this Activity
        else {
            LinkItemCard item1 = new LinkItemCard("test", "https://www.youtube.com/");
            itemList.add(item1);
        }
    }

    private void createRecyclerView() {
        rLayoutManger = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        rviewAdapter = new LinkRviewAdapter(itemList);
        ItemClickListener itemClickListener = new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //attributions bond to the item has been changed
                itemList.get(position).onItemClick(position);

                rviewAdapter.notifyItemChanged(position);
            }
        };
        rviewAdapter.setOnItemClickListener(itemClickListener);

        recyclerView.setAdapter(rviewAdapter);
        recyclerView.setLayoutManager(rLayoutManger);

    }

    private void addItem(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Title");

        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_link, findViewById(R.id.content), false);
        final EditText linkName = (EditText) viewInflated.findViewById(R.id.link_name);
        final EditText url = (EditText) viewInflated.findViewById(R.id.link_url);
        builder.setView(viewInflated);
        // Set up the buttons
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                String linkNameText = linkName.getText().toString();
                String urlText = url.getText().toString();
                itemList.add(position, new LinkItemCard(linkNameText, urlText));
                Toast.makeText(LinkCollectorActivity.this, "Added a link item", Toast.LENGTH_SHORT).show();
                rviewAdapter.notifyItemInserted(position);
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

        //itemList.add(position, new LinkItemCard("Test Link", "https://www.google.com/"));
        // TODO need a pop up so the user can specify the name and url to add
        //Toast.makeText(LinkCollectorActivity.this, "Added a link item", Toast.LENGTH_SHORT).show();

        //rviewAdapter.notifyItemInserted(position);
    }

    // TODO need onClick for Go button on each list item
    public void onClickGo(View view) {
        System.out.println(view.findViewById(R.id.url));
        System.out.println(view);
        // TODO the view given is the button view, how to get the textview of the url?
        //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(((TextView)view.findViewById(R.id.url)).getText().toString()));
        //startActivity(intent);
    }
}