package org.firehound.devfestclone.fragments;


import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.firehound.devfestclone.R;
import org.firehound.devfestclone.adapters.AgendaAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class AgendaFragment extends Fragment {


    public AgendaFragment() {
        // Required empty public constructor
    }

    private static final String TAG = "AgendaFragment";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_agenda, container, false);
        Log.d(TAG, "Loaded agenda fragment");
        Resources resources = getResources();
        RecyclerView recyclerView1 = view.findViewById(R.id.rv_6oct);
        recyclerView1.setHasFixedSize(true);
        AgendaAdapter adapter1 = new AgendaAdapter(
                new ArrayList<String>(Arrays.asList(resources.getStringArray(R.array.agenda_6_oct_events))),
                new ArrayList<String>(Arrays.asList(resources.getStringArray(R.array.agenda_6_oct_timings))),
                new ArrayList<String>(Arrays.asList(resources.getStringArray(R.array.agenda_6_oct_icons))));
        recyclerView1.setAdapter(adapter1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(requireContext()));

        RecyclerView recyclerView2 = view.findViewById(R.id.rv_7oct);
        recyclerView2.setHasFixedSize(true);
        AgendaAdapter adapter2 = new AgendaAdapter(
                new ArrayList<String>(Arrays.asList(resources.getStringArray(R.array.agenda_7_oct_events))),
                new ArrayList<String>(Arrays.asList(resources.getStringArray(R.array.agenda_7_oct_timings))),
                new ArrayList<String>(Arrays.asList(resources.getStringArray(R.array.agenda_7_oct_icons))));

        recyclerView2.setAdapter(adapter2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(requireContext()));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
