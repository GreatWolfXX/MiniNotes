package com.artemnaumovdev.mininotes.ui.notedialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.artemnaumovdev.mininotes.R;
import com.artemnaumovdev.mininotes.data.dto.NoteDto;
import com.artemnaumovdev.mininotes.databinding.DNoteBinding;

public class NoteDialogFragment extends DialogFragment {

    public static String TAG = "NoteDialogFragment";

    private DNoteBinding binding;
    private final OnClickListeners onClickListeners;

    private boolean isCreate = true;
    private long id = -1;
    private String title;
    private String btnTitle;
    private String editTextTitle;
    private String editTextDescription;

    public NoteDialogFragment(OnClickListeners onClickListeners) {
        this.onClickListeners = onClickListeners;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            getDialog().getWindow().setBackgroundDrawableResource(R.drawable.v_rounded_corner);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setCancelable(false);
        binding = DNoteBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnCancel.setOnClickListener(listener -> {
            dismiss();
        });

        binding.btnDone.setOnClickListener(listener -> {
            boolean isTitleEmpty = binding.etTitle.getText().toString().trim().isEmpty();
            boolean isDescriptionEmpty = binding.etDescription.getText().toString().trim().isEmpty();
            if(isTitleEmpty) {
                binding.textInputTitle.setError(getString(R.string.err_empty));
            }
            if(isDescriptionEmpty) {
                binding.textInputDescription.setError(getString(R.string.err_empty));
            }
            if(!isTitleEmpty && !isDescriptionEmpty) {
                NoteDto noteDto = new NoteDto(id, binding.etTitle.getText().toString(), binding.etDescription.getText().toString());
                onClickListeners.onDoneClicked(isCreate, noteDto);
                dismiss();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if(binding != null) {
            setTextDialog();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        editTextTitle = binding.etTitle.getText().toString();
        editTextDescription = binding.etDescription.getText().toString();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        binding.etTitle.setText("");
        binding.etDescription.setText("");
        binding.textInputTitle.setError("");
        binding.textInputDescription.setError("");
        id = -1;
        editTextTitle = "";
        editTextDescription = "";
    }

    public interface OnClickListeners {
        void onDoneClicked(boolean isCreate, NoteDto noteDto);
    }

    private void setTextDialog() {
        binding.title.setText(title);
        binding.btnDone.setText(btnTitle);
        binding.etTitle.setText(editTextTitle);
        binding.etDescription.setText(editTextDescription);
    }

    public void fillEditText(NoteDto noteDto) {
        id = noteDto.getId();
        editTextTitle = noteDto.getTitle();
        editTextDescription = noteDto.getDescription();
    }

    public void setTypeDialog(String title, String btnTitle) {
        this.title = title;
        this.btnTitle = btnTitle;
    }

    public void setCreate(boolean create) {
        isCreate = create;
    }
}
