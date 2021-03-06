package edu.neu.madcourse.numad21sp_colinriley;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LinkRViewHolder extends RecyclerView.ViewHolder {

    public TextView linkName;
    public TextView url;
    public Button goButton;

    public LinkRViewHolder(@NonNull View itemView, ButtonClickListener listener) {
        super(itemView);
        linkName = itemView.findViewById(R.id.link_name);
        url = itemView.findViewById(R.id.url);
        goButton = itemView.findViewById(R.id.go_button);

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    int position = getLayoutPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onButtonClick(url.getText().toString());
                    }
                }
            }
        });
    }
}
