package com.example.gifyapi.view;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.gifyapi.R;
import com.example.gifyapi.model.DataItem;
import com.example.gifyapi.model.Response;
import com.example.gifyapi.viewmodel.MainActivityViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GifyFragment extends Fragment {

    private MainActivityViewModel viewModel;
    private Button btnSubmit;
    private EditText etQuery;
    private ImageView ivGify;
    private DataItem dataItem;
    private Response response;

    public GifyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gify, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnSubmit = view.findViewById(R.id.btnSubmit);
        etQuery = view.findViewById(R.id.etQuery);
        ivGify = view.findViewById(R.id.ivGify);
        viewModel = new ViewModelProvider.NewInstanceFactory().create(MainActivityViewModel.class);

        setupObservers();
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = etQuery.getText().toString();
                viewModel.fetchGifyData(query);
            }
        });
    }

    private void setupObservers() {
        viewModel.getGifyLiveData().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                if (strings != null) {
                    if (!strings.isEmpty()){
                        Glide.with(ivGify.getContext())
                                .load(strings)
                                .placeholder(R.drawable.ic_launcher_background)
                                .into(ivGify);
                    }
                }
            }
        });

        viewModel.getErrorLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String isError) {
                if (!isError.isEmpty())
                    Toast.makeText(getActivity(), isError, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
