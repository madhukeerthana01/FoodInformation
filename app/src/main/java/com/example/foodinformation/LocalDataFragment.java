package com.example.foodinformation;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class LocalDataFragment extends Fragment {
    private RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    Button button;

    public LocalDataFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_local_data, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        button = rootView.findViewById(R.id.button);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Retrieve the stored data from SharedPreferences
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String jsonData = sharedPreferences.getString("data", "");

        // Convert the JSON string back to ArrayList<Model.categories>
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Model.categories>>() {}.getType();
        ArrayList<Model.categories> storedData = gson.fromJson(jsonData, listType);

        // Set the retrieved data to your RecyclerView adapter
        recyclerAdapter = new RecyclerAdapter(storedData);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
      button.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              PriceDetails priceDetails = new PriceDetails();

              // Replace the current fragment with the PriceDetailsFragment
              requireActivity().getSupportFragmentManager()
                      .beginTransaction()
                      .replace(R.id.fragmentContainer, priceDetails)
                      .addToBackStack(null)
                      .commit();
          }
      });

    }
}