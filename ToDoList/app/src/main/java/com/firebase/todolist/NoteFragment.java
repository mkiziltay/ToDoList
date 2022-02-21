package com.firebase.todolist;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NoteFragment extends DialogFragment {

    View view;
    EditText title,desc,addtime,notftime;
    Button update,delete;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_note, container, false);
        definition();
  // Do Fragment background transparent.
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return view;
    }

    private void definition() {
        title= view.findViewById(R.id.titlef);
        desc= view.findViewById(R.id.descf);
        addtime= view.findViewById(R.id.addtimef);
        notftime= view.findViewById(R.id.notftimef);
        update= view.findViewById(R.id.updatef);
        delete= view.findViewById(R.id.deletef);

         title.setText( ((MainActivity)getActivity()).titlem);
         desc.setText( ((MainActivity)getActivity()).descm);
         addtime.setText( ((MainActivity)getActivity()).addtimem);
         notftime.setText( ((MainActivity)getActivity()).notftimem);

         update.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Toast.makeText( getContext(), "Özellik mevcut değil", Toast.LENGTH_SHORT ).show();
             }
         });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText( getContext(), "Özellik mevcut değil", Toast.LENGTH_SHORT ).show();
            }
        });
    }
}
