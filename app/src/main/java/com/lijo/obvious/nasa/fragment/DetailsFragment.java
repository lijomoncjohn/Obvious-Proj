package com.lijo.obvious.nasa.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lijo.obvious.nasa.R;
import com.lijo.obvious.nasa.adapter.DetailsAdapater;
import com.lijo.obvious.nasa.adapter.ImageAdapater;
import com.lijo.obvious.nasa.helper.SharedPrefManager;
import com.lijo.obvious.nasa.model.ImageResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {

    private RecyclerView rv_images;
    private ArrayList<ImageResponse> imageResponses;
    DetailsAdapater imageAdapater;
    int position;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_details, container, false);

        if (getArguments() != null) {
            position = Integer.parseInt(getArguments().getString("position"));
        }

        rv_images = v.findViewById(R.id.rv_details);
        imageResponses = new ArrayList<>();
        imageAdapater = new DetailsAdapater(getContext(), imageResponses);
        rv_images.setAdapter(imageAdapater);

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(rv_images);

        loadData();

        return v;
    }

    private void loadData() {
//        Gson gson = new Gson();
        String data = SharedPrefManager.getInstance(getContext()).getKeyData();
        if (data.isEmpty()) {
            Toast.makeText(getContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
        } else {
            /*Type type = new TypeToken<List<String>>() {
            }.getType();
            List<String> arrData = gson.fromJson(json, type);
            */

            // The above method is not working because of the invlid json format
            // Exception found

            try {

                //Get the instance of JSONArray that contains JSONObjects
                JSONArray jsonArray = new JSONArray(data);

                //Iterate the jsonArray and print the info of JSONObjects
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    String copyright = jsonObject.optString("copyright");
                    String date = jsonObject.optString("date");
                    String explanation = jsonObject.optString("explanation");
                    String hdurl = jsonObject.optString("hdurl");
                    String media_type = jsonObject.optString("media_type");
                    String service_version = jsonObject.optString("service_version");
                    String title = jsonObject.optString("title");
                    String url = jsonObject.optString("url");

                    ImageResponse imageResponse = new ImageResponse(copyright, date, explanation, hdurl, media_type, service_version, title, url);
                    imageResponses.add(imageResponse);
                }
                imageAdapater.notifyDataSetChanged();

            } catch (JSONException e) {
                e.printStackTrace();
            }

            rv_images.getLayoutManager().scrollToPosition(position);
        }
    }

}
