package com.blrbrothers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EventsRVAdapter extends RecyclerView.Adapter<EventsRVAdapter.EventsViewHolder> {

    ArrayList<EventModel> events;

    public EventsRVAdapter(ArrayList<EventModel> events) {
        this.events=events;
    }

    @NonNull
    @Override
    public EventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_view_events,parent,false);
        EventsViewHolder eventsViewHolder = new EventsViewHolder(view);
        return eventsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final EventsViewHolder holder, int position) {
        EventModel event = events.get(position);
        Picasso.get().load(event.imageLink).fit().centerCrop().into(holder.eventImage, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {

            }
        });
        holder.title.setText(event.title);
        holder.location.setText(event.location);
        holder.time.setText(event.time);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    class EventsViewHolder extends RecyclerView.ViewHolder{
        ImageView eventImage;
        TextView title,location,time;
        ProgressBar progressBar;
        public EventsViewHolder(@NonNull View itemView) {
            super(itemView);
            eventImage=itemView.findViewById(R.id.iv_EventsRV);
            title=itemView.findViewById(R.id.tv_EventsRV_title);
            location=itemView.findViewById(R.id.tv_EventsRV_Location);
            time=itemView.findViewById(R.id.tv_EventsRV_Time);
            progressBar=itemView.findViewById(R.id.pb_Events_RV);

        }
    }
}
