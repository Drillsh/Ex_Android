package com.example.ex_200714_dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

public class FragmentOne extends Fragment {

    private TextView txtId;
    private TextView txtEmail;
    private Button btnRegister;

    private MainActivity mainActivity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity)getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.one_fragment, container, false);

        findViewByIdFunction(viewGroup);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRegisterDialog();
            }
        });

        return viewGroup;
    }

    private void findViewByIdFunction(ViewGroup viewGroup) {
        txtId = viewGroup.findViewById(R.id.txtId);
        txtEmail = viewGroup.findViewById(R.id.txtEmail);
        btnRegister =viewGroup.findViewById(R.id.btnRegister);
    }

    private void showRegisterDialog() {
        final EditText edtId;
        final EditText edtEmail;

        View dialogView = View.inflate(mainActivity, R.layout.register_dialog, null);

        edtId = dialogView.findViewById(R.id.edtId);
        edtEmail = dialogView.findViewById(R.id.edtEmail);

        AlertDialog.Builder dialog = new AlertDialog.Builder(mainActivity);

        dialog.setTitle("회원 등록");
        dialog.setIcon(R.drawable.ball);

        dialog.setView(dialogView);

        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                txtId.setText(edtId.getText().toString());
                txtEmail.setText(edtEmail.getText().toString());
            }
        });

        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                txtId.setText("");
                txtEmail.setText("");
            }
        });

        dialog.show();
    }
}
