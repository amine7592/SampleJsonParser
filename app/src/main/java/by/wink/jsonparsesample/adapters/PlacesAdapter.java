package by.wink.jsonparsesample.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import by.wink.jsonparsesample.R;
import by.wink.jsonparsesample.models.Place;

/**
 * Created by amine on 01/03/17.
 */

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.PlaceViewHolder> {

    private ArrayList<Place> dataSet = new ArrayList<>();

    public void setDataSet(ArrayList<Place> dataSet) {
        this.dataSet = dataSet;
        notifyDataSetChanged();
    }

    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_place, parent, false);

        return new PlaceViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PlaceViewHolder holder, int position) {

        Place place = dataSet.get(position);
        holder.placeName.setText(place.getName());
        holder.placeAdress.setText(place.getAddress());

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    class PlaceViewHolder extends RecyclerView.ViewHolder {
        TextView placeName;
        TextView placeAdress;

        PlaceViewHolder(View itemView) {
            super(itemView);
            placeName = (TextView) itemView.findViewById(R.id.place_name);
            placeAdress = (TextView) itemView.findViewById(R.id.place_address);
        }
    }
}
