package com.orosoft.landroid;

import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class Frag_BottomSheet extends BottomSheetDialogFragment {

    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);

        View view = View.inflate(getContext(), R.layout.bottomsheet, null);
        
        Button btn_fragsheet_okay = (Button) view.findViewById(R.id.btn_fragsheet_okay);
        btn_fragsheet_okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "You clicked okay button", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        
        dialog.setContentView(view);

    }


}