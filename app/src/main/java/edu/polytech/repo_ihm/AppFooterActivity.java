package edu.polytech.repo_ihm;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class AppFooterActivity extends Fragment {


    public AppFooterActivity() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_app_footer, container, false);

        //Accueil
        ((Button) rootView.findViewById(R.id.BtnAccueil)).setOnClickListener((View v) -> {
            Intent i = new Intent(getActivity(), MainActivity.class);
            startActivity(i);
        });


        //Contact ToDo
        ((Button) rootView.findViewById(R.id.BtnAccueil)).setOnClickListener((View v) -> {

        });

        //Polytech
        ((Button) rootView.findViewById(R.id.btnPolytech)).setOnClickListener((View v) -> {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.polytech-reseau.org/"));
            startActivity(i);
        });

        //A Propos ToDo
        ((Button) rootView.findViewById(R.id.btnApropo)).setOnClickListener((View v) -> {

        });

        return rootView;


    }
}