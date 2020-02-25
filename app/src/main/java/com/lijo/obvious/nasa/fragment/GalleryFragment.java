package com.lijo.obvious.nasa.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.lijo.obvious.nasa.R;
import com.lijo.obvious.nasa.adapter.ImageAdapater;
import com.lijo.obvious.nasa.helper.SharedPrefManager;
import com.lijo.obvious.nasa.model.ImageResponse;
import com.lijo.obvious.nasa.model.JsonData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GalleryFragment extends Fragment {

    private RecyclerView rv_images;
    private ArrayList<ImageResponse> imageResponses;
    ImageAdapater imageAdapater;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_gallery, container, false);

        rv_images = v.findViewById(R.id.rv_pic_gallery);
        imageResponses = new ArrayList<>();
        imageAdapater = new ImageAdapater(getContext(), imageResponses);
        rv_images.setAdapter(imageAdapater);

        try {

            //Get the instance of JSONArray that contains JSONObjects
            JSONArray jsonArray = new JSONArray(JsonData.getJson());

            //Iterate the jsonArray and print the info of JSONObjects
            for(int i=0; i < jsonArray.length(); i++){
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
            saveData(imageResponses);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return v;
    }

    private void saveData(List data) {
        Gson gson = new Gson();
        String json = gson.toJson(data);
        SharedPrefManager.getInstance(getContext()).setData(json);
    }
}
