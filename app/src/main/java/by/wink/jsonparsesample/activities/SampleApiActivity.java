package by.wink.jsonparsesample.activities;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import by.wink.jsonparsesample.R;
import by.wink.jsonparsesample.adapters.PlacesAdapter;
import by.wink.jsonparsesample.models.Place;
import by.wink.jsonparsesample.services.FoursquareAPI;

public class SampleApiActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    PlacesAdapter adapter;

    EditText searchEt;
    Button searchBtn;
    ProgressBar loading;

    FoursquareAPI foursquareAPI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_api);
        adapter = new PlacesAdapter();
        layoutManager = new LinearLayoutManager(this);
        recyclerView = (RecyclerView) findViewById(R.id.search_rv);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        searchEt = (EditText) findViewById(R.id.search_et);
        searchBtn = (Button) findViewById(R.id.search_btn);
        loading = (ProgressBar) findViewById(R.id.loading);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doSearch();
            }
        });

    }


    public void doSearch() {
        String query = searchEt.getText().toString();
        new FoursquareApiTask().execute(query);


    }


    private class FoursquareApiTask extends AsyncTask<String, Void, ArrayList<Place>> {

        private static final String RESPONSE = "response";
        private static final String VENUES = "venues";


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading.setVisibility(View.VISIBLE);
        }


        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected ArrayList<Place> doInBackground(String... strings) {

            ArrayList<Place> placesArraylist = new ArrayList<>();
            try {
                foursquareAPI = new FoursquareAPI();
                String url = foursquareAPI.getUrlString(strings[0]);



                JSONObject jsonResponse = foursquareAPI.getJSONObjectFromURL(url);
                JSONArray jsonPlaces = jsonResponse.getJSONObject(RESPONSE)
                                                        .getJSONArray(VENUES);


                for(int i = 0; i < jsonPlaces.length(); i++){
                    placesArraylist.add(new Place(jsonPlaces.getJSONObject(i)));

                }

                return placesArraylist;

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return placesArraylist;
        }

        @Override
        protected void onPostExecute(ArrayList<Place> places) {
            super.onPostExecute(places);
            adapter.setDataSet(places);
            loading.setVisibility(View.GONE);
        }
    }


}
