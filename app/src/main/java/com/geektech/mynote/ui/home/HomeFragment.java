package com.geektech.mynote.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.geektech.mynote.adapter.NoteAdapter;
import com.geektech.mynote.databinding.FragmentHomeBinding;
import com.geektech.mynote.utils.Constants;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private NoteAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        initView();
        getData();
        return binding.getRoot();
    }

    private void getData() {
        getParentFragmentManager().setFragmentResultListener(Constants.REQUEST_KEY, getViewLifecycleOwner(), (requestKey, result) -> {
            String text = result.getString(Constants.BUNDLE_KEY);
            Log.e("TAG", "getData: " + text);
            adapter.addText(text);
        });
    }

    private void initView() {
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new NoteAdapter();
        adapter.addText("ljfaj");
        adapter.addText("ljfaj");
        adapter.addText("ljfaj");
        adapter.addText("ljfaj");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}