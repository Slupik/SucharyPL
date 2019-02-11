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
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.slupik.domain.entity.category.JokeCategory;
import io.github.slupik.domain.entity.filter.FilterCreatorChecker;
import io.github.slupik.sucharypl.R;
import io.github.slupik.sucharypl.app.view.custom.element.LabelFactory;
import io.github.slupik.sucharypl.app.view.custom.element.LabelWithAction;

public class FavouritesDialog extends Dialog {

    @BindView(R.id.tvSelectFiltersTitle)
    TextView mFiltersTitle;
    @BindView(R.id.flJokeFilters)
    FlexboxLayout mJokeFilters;

    private LabelWithAction mAddNew;
    private OnSaveListener mListener;

    private final List<LabelWithAction> mSetCategories = new ArrayList<>();

    public FavouritesDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_favourite);
        ButterKnife.bind(this);

        mAddNew = LabelFactory.createAddingLabel(getContext());
        mAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new NewFilterDialog(getContext(), new FilterCreatorChecker() {
                    @Override
                    public boolean isAvailable(String name) {
                        //TODO replace with real checker
                        return true;
                    }
                }).setOnSave(new NewFilterDialog.OnSaveListener() {
                    @Override
                    public void onSave(String name) {
                        LabelWithAction lbl = LabelFactory.createLabel(getContext(), name);
                        LabelFactory.markSelected(lbl, true);
                        mJokeFilters.removeView(mAddNew);
                        mJokeFilters.addView(lbl);
                        mJokeFilters.addView(mAddNew);
                    }
                }).show();
            }
        });
        mJokeFilters.addView(mAddNew);
    }

    public void setFilterList(List<JokeCategory> all, List<JokeCategory> selected) {
        setFilterList(all.toArray(new JokeCategory[0]), selected.toArray(new JokeCategory[0]));
    }

    public void setFilterList(JokeCategory[] all, JokeCategory[] selected) {
        mJokeFilters.removeAllViews();
        mSetCategories.clear();
        for(JokeCategory category:all) {
            LabelWithAction label = LabelFactory.createLabel(getContext(), category);
            LabelFactory.markSelected(label, isSelected(category, selected));
            label.putTempData(category);

            mJokeFilters.addView(label);
            mSetCategories.add(label);
        }
        mJokeFilters.addView(mAddNew);
    }

    private boolean isSelected(JokeCategory category, JokeCategory[] selected) {
        for(JokeCategory selection:selected) {
            if(category.getId()==selection.getId()) {
                return true;
            }
        }
        return false;
    }

    public void setOnSave(OnSaveListener listener) {
        this.mListener = listener;
    }

    @OnClick(R.id.btnCancel)
    void actionCancel() {
        cancel();
    }

    @OnClick(R.id.btnSave)
    void actionSave() {
        if(mListener !=null) {
            List<JokeCategory> selected = getSelectedCategories();
            mListener.onSave(selected);
        }
        cancel();
    }

    private List<JokeCategory> getSelectedCategories() {
        List<JokeCategory> selected = new ArrayList<>();
        for(LabelWithAction label:mSetCategories) {
            if(label.isSelected()) {
                selected.add((JokeCategory) label.getTempData());
            }
        }
        return selected;
    }

    @FunctionalInterface
    public interface OnSaveListener {
        void onSave(List<JokeCategory> selected);
    }
}
