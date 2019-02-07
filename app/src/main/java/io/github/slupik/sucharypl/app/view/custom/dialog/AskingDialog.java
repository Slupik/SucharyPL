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
import android.support.annotation.StringRes;
import android.view.Window;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.slupik.sucharypl.R;

public abstract class AskingDialog extends Dialog {

    @BindView(R.id.dialog_ask_body)
    TextView mBody;
    @BindView(R.id.btnConfirm)
    TextView mConfirm;
    @BindView(R.id.btnCancel)
    TextView mCancel;

    private Listener mListener;
    private String message = "";

    public AskingDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_ask);
        ButterKnife.bind(this);

        setButtonsText(mConfirm, mCancel);
        mBody.setText(message);
    }

    protected abstract void setButtonsText(TextView confirm, TextView cancel);

    public AskingDialog setMessage(@StringRes int textId){
        return setMessage((String) getContext().getText(textId));
    }

    public AskingDialog setMessage(String text){
        if(mBody==null) {
            message = text;
        } else {
            mBody.setText(text);
        }
        return this;
    }

    @OnClick(R.id.btnCancel)
    void actionCancel() {
        cancel();
    }

    @OnClick(R.id.btnConfirm)
    void actionConfirm() {
        if(mListener !=null) {
            mListener.onConfirm();
        }
        cancel();
    }

    public AskingDialog setListener(Listener listener) {
        this.mListener = listener;
        return this;
    }

    @FunctionalInterface
    public interface Listener {
        void onConfirm();
    }
}
