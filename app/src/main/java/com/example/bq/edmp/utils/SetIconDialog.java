package com.example.bq.edmp.utils;


import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.bq.edmp.R;

import java.io.File;
import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class SetIconDialog extends DialogFragment {
    public static final int TAKE_PICTURE = 10;
    public static final int CHOOSE_PICTURE = 11;

    private TextView mTakePictureTextView;
    private TextView mChoosePictureTextView;
    private TextView mCancelTextView;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_set_icon_dialog, null);

        mTakePictureTextView = (TextView) v.findViewById(R.id.take_picture_text_view);
        mTakePictureTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File outputPhotoFile = new File(Environment.getExternalStorageDirectory(), "userIcon.jpg");

                try {
                    if (outputPhotoFile.exists()) outputPhotoFile.delete();
                    outputPhotoFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Uri imageUri = Uri.fromFile(outputPhotoFile);

                Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                captureImage.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                if (getTargetFragment() != null)
                    getTargetFragment().startActivityForResult(captureImage, TAKE_PICTURE);
                else
                    getActivity().startActivityForResult(captureImage, TAKE_PICTURE);
                dismiss();
            }
        });

        mChoosePictureTextView = (TextView) v.findViewById(R.id.choose_picture_text_view);
        mChoosePictureTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent choosePhoto = new Intent(Intent.ACTION_PICK);
                choosePhoto.setType("image/*");
                if (getTargetFragment() != null)
                    getTargetFragment().startActivityForResult(choosePhoto, CHOOSE_PICTURE);
                else getActivity().startActivityForResult(choosePhoto, CHOOSE_PICTURE);
                dismiss();
            }
        });

        mCancelTextView = (TextView) v.findViewById(R.id.cancel_text_view);
        mCancelTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return new AlertDialog.Builder(getActivity()).setView(v).create();
    }
}
