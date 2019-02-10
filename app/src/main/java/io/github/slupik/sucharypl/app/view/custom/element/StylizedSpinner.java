/*
 * Copyright (c) 2019. by Sebastian Witasik
 * All rights reserved. No part of this application may be reproduced or be part of other software, without the prior written permission of the publisher. For permission requests, write to the author(WitasikSebastian@gmail.com).
 */

package io.github.slupik.sucharypl.app.view.custom.element;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.slupik.sucharypl.R;

public class StylizedSpinner extends LinearLayout {

    @BindView(R.id.custom_spinner)
    Spinner mSpinner;

    public StylizedSpinner(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        createView(context);
    }

    public StylizedSpinner(Context context) {
        super(context);
        createView(context);
    }

    private void createView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_spinner, this, true);
        ButterKnife.bind(this);

        //Makes all area clickable
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Spinner spinner = findViewById(R.id.custom_spinner);
                spinner.performClick();
            }
        });
    }

    public Object getSelectedItem(){
        return mSpinner.getSelectedItem();
    }

    public void setAdapter(ArrayAdapter<Object> adapter) {
        mSpinner.setAdapter(adapter);
    }
}
