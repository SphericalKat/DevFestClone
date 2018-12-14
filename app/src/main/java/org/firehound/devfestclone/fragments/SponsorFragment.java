package org.firehound.devfestclone.fragments;


import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import saschpe.android.customtabs.CustomTabsHelper;
import saschpe.android.customtabs.WebViewFallback;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;
import com.yarolegovich.discretescrollview.Orientation;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import org.firehound.devfestclone.R;
import org.firehound.devfestclone.adapters.SponsorAdapter;
import org.firehound.devfestclone.models.Sponsor;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SponsorFragment extends Fragment implements DiscreteScrollView.OnItemChangedListener, View.OnClickListener {
    private TextView currentSponsorName, currentSponsorType;
    private ImageView previousSponsorButton, nextSponsorButton;
    private DiscreteScrollView itemPicker;
    private FloatingActionButton websiteButton;
    private InfiniteScrollAdapter infiniteAdapter;
    private List<Sponsor> sponsorData;


    public SponsorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sponsor, container, false);
        sponsorData = Arrays.asList(new Sponsor(".tech DOMAINS", "Sponsor", "http://get.tech/", R.drawable.logo_dottech),
                new Sponsor("balsamiq", "Sponsor", "https://balsamiq.com/", R.drawable.logo_balsmiq),
                new Sponsor("Pixectra", "Sponsor", "http://www.pixectra.com/", R.drawable.logo_p),
                new Sponsor("Decoder", "Sponsor", "url", R.drawable.logo_decoders),
                new Sponsor("The Souled Store", "Sponsor", "https://www.thesouledstore.com/", R.drawable.logo_thesouledstore),
                new Sponsor("CNCF", "Sponsor", "https://www.cncf.io/", R.drawable.logo_cncf),
                new Sponsor("Khronos", "Sponsor", "https://www.khronos.org/", R.drawable.logo_khronos),
                new Sponsor("Agora", "Sponsor", "https://www.agora.io/en/", R.drawable.logo_agora),
                new Sponsor("Slang Labs", "Sponsor", "https://slanglabs.in/", R.drawable.logo_slanglabs),
                new Sponsor("Sketch", "Sponsor", "https://www.sketchapp.com/", R.drawable.logo_sketch),
                new Sponsor("LBRY", "Sponsor", "https://lbry.io/", R.drawable.logo_lbry),
                new Sponsor("dev.to", "Sponsor", "https://lbry.io/", R.drawable.logo_devto),
                new Sponsor("Travis", "Sponsor", "https://travis-ci.org/", R.drawable.logo_travis),
                new Sponsor("Bugsee", "Sponsor", "https://www.bugsee.com/", R.drawable.logo_bugsee),
                new Sponsor("Creative Tim", "Sponsor", "https://www.creative-tim.com/", R.drawable.logo_creativetim),
                new Sponsor("Yocto Project", "Sponsor", "https://www.yoctoproject.org/", R.drawable.logo_yocto),
                new Sponsor("Estimote", "Sponsor", "https://estimote.com/", R.drawable.logo_estimo),
                new Sponsor("hackerearth", "Sponsor", "https://hackerearth.com", R.drawable.logo_hackearth),
                new Sponsor("JetBrains", "Sponsor", "https://www.jetbrains.com", R.drawable.logo_jetbrains),
                new Sponsor("OpenSUSE", "Sponsor", "https://www.opensuse.org/", R.drawable.logo_opensuse));

        currentSponsorName = view.findViewById(R.id.sponsor_name);
        currentSponsorType = view.findViewById(R.id.sponsor_type);
        previousSponsorButton = view.findViewById(R.id.previous_sponsor_button);
        nextSponsorButton = view.findViewById(R.id.next_sponsor_button);
        websiteButton = view.findViewById(R.id.sponsor_website_button);

        itemPicker = view.findViewById(R.id.item_picker);
        itemPicker.setOrientation(Orientation.HORIZONTAL);
        itemPicker.addOnItemChangedListener(this);
        infiniteAdapter = InfiniteScrollAdapter.wrap(new SponsorAdapter(sponsorData));
        itemPicker.setAdapter(infiniteAdapter);
        itemPicker.setItemTransitionTimeMillis(100);
        itemPicker.setItemTransformer(new ScaleTransformer.Builder().setMinScale(0.8f).build());

        onItemChanged(sponsorData.get(0));

        view.findViewById(R.id.sponsor_name).setOnClickListener(this);
        view.findViewById(R.id.sponsor_type).setOnClickListener(this);
        view.findViewById(R.id.previous_sponsor_button).setOnClickListener(this);
        view.findViewById(R.id.next_sponsor_button).setOnClickListener(this);
        view.findViewById(R.id.sponsor_website_button).setOnClickListener(this);

        itemPicker.setSlideOnFling(true);
        itemPicker.setSlideOnFlingThreshold(2000);



        return view;


    }

    private void onItemChanged(Sponsor sponsor) {
        currentSponsorName.setText(sponsor.getSponsorName());
        currentSponsorType.setText(sponsor.getSponsorType());
    }

    @Override
    public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int i) {
        onItemChanged(sponsorData.get(infiniteAdapter.getRealPosition(i)));
    }

    @Override
    public void onClick(View v) {
        int realPosition = 0;
        Sponsor current;
        switch (v.getId()){
            case R.id.sponsor_website_button:
                realPosition = infiniteAdapter.getRealPosition(itemPicker.getCurrentItem());
                current = sponsorData.get(realPosition);
                CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder()
                        .addDefaultShareMenuItem()
                        .setToolbarColor(ContextCompat.getColor(getView().getContext(), R.color.colorPrimary))
                        .setShowTitle(true)
                        .build();
                CustomTabsHelper.addKeepAliveExtra(getView().getContext(), customTabsIntent.intent);
                CustomTabsHelper.openCustomTab(getView().getContext(), customTabsIntent, Uri.parse(current.getSponsorWebsite()), new WebViewFallback());
                break;

            case R.id.previous_sponsor_button:
                realPosition = infiniteAdapter.getRealPosition(itemPicker.getCurrentItem());
                smoothScrollToPreviousPosition(itemPicker, realPosition);
                break;
            case R.id.next_sponsor_button:
                realPosition = infiniteAdapter.getRealPosition(itemPicker.getCurrentItem());
                smoothScrollToNextPosition(itemPicker, realPosition);

        }

    }

    private void smoothScrollToNextPosition(DiscreteScrollView scrollView, int position){
        if (scrollView.getAdapter() instanceof  InfiniteScrollAdapter) {
            int destination = position + 1;
            scrollView.smoothScrollToPosition(((InfiniteScrollAdapter) scrollView.getAdapter()).getClosestPosition(destination));
        }
    }

    private void smoothScrollToPreviousPosition(DiscreteScrollView scrollView, int position) {
        if (scrollView.getAdapter() instanceof  InfiniteScrollAdapter) {
            int destination = position - 1;
            scrollView.smoothScrollToPosition(((InfiniteScrollAdapter) scrollView.getAdapter()).getClosestPosition(destination));
        }
    }
}
