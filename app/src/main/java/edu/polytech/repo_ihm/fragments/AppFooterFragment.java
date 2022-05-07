package edu.polytech.repo_ihm.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import edu.polytech.repo_ihm.R;
import edu.polytech.repo_ihm.activities.ContactActivity;
import edu.polytech.repo_ihm.activities.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class AppFooterFragment extends Fragment {


    public AppFooterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_app_footer, container, false);

        //Accueil
        rootView.findViewById(R.id.homeButton).setOnClickListener((View v) -> {
            Intent i = new Intent(getActivity(), MainActivity.class);
            startActivity(i);
        });


        //Contact ToDo
        rootView.findViewById(R.id.contactButton).setOnClickListener((View v) -> {
            Intent i = new Intent(getActivity(), ContactActivity.class);
            startActivity(i);
        });

        //Polytech
        rootView.findViewById(R.id.polytechButton).setOnClickListener((View v) -> {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.polytech-reseau.org/"));
            startActivity(i);
        });

        //A Propos ToDo
        rootView.findViewById(R.id.aboutButton).setOnClickListener((View v) -> {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://lms.univ-cotedazur.fr/mod/page/view.php?id=39235"));
            startActivity(i);
        });

        return rootView;


    }
}