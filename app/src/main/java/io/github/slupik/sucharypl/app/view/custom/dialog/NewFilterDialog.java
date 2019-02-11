/*
 * Copyright (c) 2019. by Sebastian Witasik
 * All rights reserved. No part of this application may be reproduced or be part of other software, without the prior written permission of the publisher. For permission requests, write to the author(WitasikSebastian@gmail.com).
 */

package io.github.slupik.sucharypl.app.view.custom.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import io.github.slupik.domain.entity.filter.FilterCreatorChecker;
import io.github.slupik.sucharypl.R;

public class NewFilterDialog extends Dialog {

    @BindView(R.id.filterName)
    TextInputEditText mFilterName;
    @BindView(R.id.newFilterErrorMsg)
    TextView mNewFilterErrorMsg;
    @BindView(R.id.btnSave)
    TextView mSave;

    private final FilterCreatorChecker checker;
    private OnSaveListener mListener;

    public NewFilterDialog(@NonNull Context context, FilterCreatorChecker checker) {
        super(context);
        this.checker = checker;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_new_filter);
        ButterKnife.bind(this);
        mNewFilterErrorMsg.setVisibility(View.GONE);
    }

    public NewFilterDialog setOnSave(OnSaveListener listener) {
        this.mListener = listener;
        return this;
    }

    @OnTextChanged(R.id.filterName)
    void actionNameChange(){
        if(checker.isAvailable(getName())) {
            mNewFilterErrorMsg.setVisibility(View.GONE);
            mSave.setVisibility(View.VISIBLE);
        } else {
            mNewFilterErrorMsg.setVisibility(View.VISIBLE);
            mSave.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.btnCancel)
    void actionCancel() {
        cancel();
    }

    @OnClick(R.id.btnSave)
    void actionSave() {
        if(mListener !=null) {
            //TODO connect to the background
            mListener.onSave(getName());
        }
        cancel();
    }

    private String getName() {
        if(mFilterName.getText()==null) {
            return "";
        } else {
            return mFilterName.getText().toString();
        }
    }

    @FunctionalInterface
    public interface OnSaveListener {
        void onSave(String name);
    }

}
