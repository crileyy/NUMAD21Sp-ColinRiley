package edu.neu.madcourse.numad21sp_colinriley;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Arrays;

public class FirstFragment extends Fragment {
    private static final int REQUEST_CODE = 1;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        view.findViewById(R.id.button_clicky).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ClickyActivity.class);
                startActivity(intent);
            }
        });

        view.findViewById(R.id.link_collector_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LinkCollectorActivity.class);
                startActivity(intent);
            }
        });

        view.findViewById(R.id.locator_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(getActivity(), LocatorActivity.class);
                    startActivity(intent);
                } else {
                    requestPermissions(new String[] { Manifest.permission.ACCESS_FINE_LOCATION },
                            REQUEST_CODE);
                }
            }
        });

        view.findViewById(R.id.service_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AtYourServiceActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission is granted
                    Intent intent = new Intent(getActivity(), LocatorActivity.class);
                    startActivity(intent);
                }  else {
                    // permission denied
                    Log.e("permission_error", "permission not granted " + Arrays.toString(grantResults));
                    Toast.makeText(getActivity(), "Location services permission not granted", Toast.LENGTH_LONG).show();
                }
        }

    }
}