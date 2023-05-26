

package com.example.foodinformation;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteDataFragment extends Fragment implements BottomsheetClickListner{
    private Button getData;
    private RecyclerView recyclerView;
    TextView textView;
    private static final String TAG = "RemoteDataFragment";


    public RemoteDataFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_remote_data, container, false);
        getData = rootView.findViewById(R.id.getData);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
                Call<Model> call = methods.getAllData();
                call.enqueue(new Callback<Model>() {
                    @Override
                    public void onResponse(Call<Model> call, Response<Model> response) {
                        Log.e(TAG, "onResponse: code : " + response.code());
                        if (response.isSuccessful()) {
                            ArrayList<Model.categories> data = response.body().getCategories();

                            Gson gson = new Gson();
                            String jsonData = gson.toJson(data);

                            SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("data", jsonData);
                            editor.apply();

                            // Set the retrieved data to your RecyclerView adapter
                            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(data);
                            recyclerView.setAdapter(recyclerAdapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
                        } else {
                            Log.e(TAG, "onResponse: Request failed");
                        }
                    }

                    @Override
                    public void onFailure(Call<Model> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getMessage());
                    }
                });
            }
        });
    }
    public void onItemClicked(String description) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity());
        View bottomSheetView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_bottom_sheet,null);
        bottomSheetDialog.setContentView(bottomSheetView);
        TextView descriptionTextView = bottomSheetView.findViewById(R.id.textView);
        descriptionTextView.setText(description);
        bottomSheetDialog.show();
    }
}
