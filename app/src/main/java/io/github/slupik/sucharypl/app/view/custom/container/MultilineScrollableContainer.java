/*
 * Copyright (c) 2019. by Sebastian Witasik
 * All rights reserved. No part of this application may be reproduced or be part of other software, without the prior written permission of the publisher. For permission requests, write to the author(WitasikSebastian@gmail.com).
 */

package io.github.slupik.sucharypl.app.view.custom.container;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

import io.github.slupik.sucharypl.R;

public class MultilineScrollableContainer extends ScrollView {

    private int mLineAmount;
    private boolean mIsVertical;
    private final List<LinearLayout> mAvailableContainer = new ArrayList<>();
    private int mLineIndex = 0;

    public MultilineScrollableContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.MultilineScrollableContainer,
                0, 0);
        try {
            mLineAmount = a.getInteger(R.styleable.MultilineScrollableContainer_lineAmount, 1);
            mIsVertical = a.getInteger(R.styleable.MultilineScrollableContainer_lineOrientation, 0)==0;
        } finally {
            a.recycle();
        }

        LinearLayout masterContainer = new LinearLayout(getContext());
        if(mIsVertical) {
            masterContainer.setOrientation(LinearLayout.HORIZONTAL);
        } else {
            masterContainer.setOrientation(LinearLayout.VERTICAL);
        }

        for(int i=0;i<mLineAmount;i++) {
            LinearLayout container = new LinearLayout(getContext());
            if(mIsVertical) {
                container.setOrientation(LinearLayout.VERTICAL);
            } else {
                container.setOrientation(LinearLayout.HORIZONTAL);
            }
            mAvailableContainer.add(container);
            masterContainer.addView(container);
        }

        super.addView(masterContainer);
    }

    @Override
    public void addView(View child) {
        LinearLayout container = mAvailableContainer.get(mLineIndex);
        mLineIndex = (mLineIndex+1)%mAvailableContainer.size();
        container.addView(child);
    }
}
