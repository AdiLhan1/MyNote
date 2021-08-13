package com.geektech.mynote.ui.note;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.geektech.mynote.R;
import com.geektech.mynote.databinding.FragmentNoteBinding;
import com.geektech.mynote.utils.Constants;


public class NoteFragment extends Fragment {
    FragmentNoteBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNoteBinding.inflate(inflater, container, false);
        initView();
        return binding.getRoot();
    }

    private void initView() {
        binding.btnSave.setOnClickListener(v -> {
            String text = binding.editText.getText().toString().trim();
            if (TextUtils.isEmpty(text)) {
                binding.editText.setError("Вы не правильно ввели");
                return;
            }

            Bundle bundle = new Bundle();
            bundle.putString(Constants.BUNDLE_KEY, text);
            getParentFragmentManager().setFragmentResult(Constants.REQUEST_KEY, bundle);
            close();
        });
    }

    private void close() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
        navController.navigateUp();
    }

    @Override
    public void onResume() {
        super.onResume();
        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.show();
        }
    }
}