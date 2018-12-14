package org.firehound.devfestclone.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.firehound.devfestclone.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AgendaAdapter extends RecyclerView.Adapter<AgendaAdapter.AgendaAdapterViewHolder> {
    private List<String> events;
    private List<String> timings;

    public AgendaAdapter(List<String> events, List<String> timings, List<String> icons) {
        this.events = events;
        this.timings = timings;
        this.icons = icons;
    }

    private List<String> icons;
    private Context context;

    @NonNull
    @Override
    public AgendaAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_agenda_light, parent, false);
        AgendaAdapterViewHolder holder = new AgendaAdapterViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AgendaAdapterViewHolder holder, int position) {
        holder.eventText.setText(events.get(position));
        holder.timingText.setText(timings.get(position));
        holder.iconImage.setImageResource(context.getResources().getIdentifier(icons.get(position), "drawable", context.getPackageName()));

    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    class AgendaAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView eventText, timingText;
        ImageView iconImage;

        AgendaAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            eventText = itemView.findViewById(R.id.text_agenda_light_event);
            timingText = itemView.findViewById(R.id.text_agenda_light_timing);
            iconImage = itemView.findViewById(R.id.image_agenda_light_icon);


        }
    }
}
