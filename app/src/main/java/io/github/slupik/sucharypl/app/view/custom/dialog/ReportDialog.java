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
import android.view.Window;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.slupik.sucharypl.R;
import io.github.slupik.sucharypl.app.view.custom.element.StylizedSpinner;
import io.github.slupik.sucharypl.app.view.model.ReportCategory;

public class ReportDialog extends Dialog {

    private Listener mListener;

    public ReportDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_report);
        ButterKnife.bind(this);

        setupSpinner();
    }

    private void setupSpinner() {
        StylizedSpinner spinner = findViewById(R.id.spinner_report);

        ArrayAdapter<Object> langAdapter = new ArrayAdapter<>(getContext(), R.layout.custom_spinner_text, getReasonsCategories());
        langAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        spinner.setAdapter(langAdapter);
    }

    private List<Object> getReasonsCategories() {
        List<Object> categories = new ArrayList<>();
        categories.add(createCategory(0, R.string.report_category_typo));
        categories.add(createCategory(1, R.string.report_category_offensive));
        categories.add(createCategory(2, R.string.report_category_copyright));
        categories.add(createCategory(3, R.string.report_category_other));
        return categories;
    }

    private Object createCategory(int id, int idRes) {
        return new ReportCategory(getContext(), id, idRes);
    }

    @OnClick(R.id.btnReport)
    public void actionReport() {
        if(mListener!=null) {
            StylizedSpinner spinner = findViewById(R.id.spinner_report);
            mListener.onConfirm(((ReportCategory) spinner.getSelectedItem()));
        }
        cancel();
    }

    @OnClick(R.id.btnCancel)
    public void actionCancel() {
        cancel();
    }

    public ReportDialog setListener(Listener listener) {
        this.mListener = listener;
        return this;
    }

    @FunctionalInterface
    public interface Listener {
        void onConfirm(ReportCategory category);
    }
}
