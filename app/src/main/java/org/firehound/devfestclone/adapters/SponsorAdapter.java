package org.firehound.devfestclone.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.firehound.devfestclone.R;
import org.firehound.devfestclone.models.Sponsor;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SponsorAdapter extends RecyclerView.Adapter<SponsorAdapter.ViewHolder> {
    private List<Sponsor> sponsorData;

    public SponsorAdapter(List<Sponsor> sponsorData) {
        this.sponsorData = sponsorData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sponsor_gallery, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.image.setCropToPadding(true);
        Glide.with(holder.itemView.getContext()).load(sponsorData.get(position).getSponsorImage()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return sponsorData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
        }
    }
}
