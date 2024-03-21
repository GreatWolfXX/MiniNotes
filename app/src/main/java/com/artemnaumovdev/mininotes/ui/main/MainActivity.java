package com.artemnaumovdev.mininotes.ui.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.artemnaumovdev.mininotes.R;
import com.artemnaumovdev.mininotes.data.dto.NoteDto;
import com.artemnaumovdev.mininotes.databinding.ActivityMainBinding;
import com.artemnaumovdev.mininotes.ui.notedialog.NoteDialogFragment;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements NotesListAdapter.OnClickListeners, NoteDialogFragment.OnClickListeners {

    MainViewModel viewModel;
    private ActivityMainBinding binding;
    private NotesListAdapter adapter;
    private NoteDialogFragment createNoteDialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        initializeList();

        createNoteDialogFragment = new NoteDialogFragment(this);

        binding.btnAddNotes.setOnClickListener(listener -> {
            createNoteDialogFragment.setTypeDialog(getString(R.string.title_create_note), getString(R.string.create));
            createNoteDialogFragment.setCreate(true);
            createNoteDialogFragment.show(getSupportFragmentManager(), NoteDialogFragment.TAG);
        });
    }

    private void initializeList() {
        viewModel.getAllNotes().observe(this, list -> {
            if(adapter == null) {
                adapter = new NotesListAdapter(list, this);
                binding.listNotes.setAdapter(adapter);
                binding.listNotes.setLayoutManager(new LinearLayoutManager(this));
            } else {
                adapter.setItems(list);
            }
        });
    }

    @Override
    public void onDeleteClicked(NoteDto noteDto) {
        viewModel.deleteNoteById(noteDto.getId());
    }

    @Override
    public void onEditClicked(NoteDto noteDto) {
        createNoteDialogFragment.setTypeDialog(getString(R.string.title_edit_note), getString(R.string.edit));
        createNoteDialogFragment.setCreate(false);
        createNoteDialogFragment.fillEditText(noteDto);
        createNoteDialogFragment.show(getSupportFragmentManager(), NoteDialogFragment.TAG);
    }

    @Override
    public void onDoneClicked(boolean isCreate, NoteDto noteDto) {
        if(isCreate) {
            viewModel.addNote(noteDto);
        } else {
            viewModel.editNoteById(noteDto);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.disposeAll();
    }
}