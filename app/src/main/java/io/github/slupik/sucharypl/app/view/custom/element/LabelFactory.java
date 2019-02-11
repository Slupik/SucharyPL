/*
 * Copyright (c) 2019. by Sebastian Witasik
 * All rights reserved. No part of this application may be reproduced or be part of other software, without the prior written permission of the publisher. For permission requests, write to the author(WitasikSebastian@gmail.com).
 */

package io.github.slupik.sucharypl.app.view.custom.element;


import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;

import io.github.slupik.domain.entity.category.JokeCategory;
import io.github.slupik.sucharypl.R;

public final class LabelFactory {

    private LabelFactory(){}

    public static void markSelected(LabelWithAction label, boolean isSelected) {
        if(isSelected) {
            label.setTextColor(label.getContext().getResources().getColor(R.color.colorPrimaryDark));
            label.setBorderColor(label.getContext().getResources().getColor(R.color.colorItemBorder));
            label.setRightActionSymbol("X");
        } else {
            label.setTextColor(label.getContext().getResources().getColor(R.color.colorInactiveTextDark));
            label.setBorderColor(Color.TRANSPARENT);
            label.setRightActionSymbol(null);
        }
        LabelUtils.markAsSelected(label, isSelected);
        label.setActionColor(label.getContext().getResources().getColor(R.color.colorSecondaryText));
    }

    public static LabelWithAction createAddingLabel(Context context) {
        LabelWithAction label = new LabelWithAction(context);
        label.setText((String) context.getText(R.string.add_filter_label));
        label.setLeftActionSymbol("+");
        label.setTextColor(label.getContext().getResources().getColor(R.color.colorPrimaryDark));
        label.setBorderColor(label.getContext().getResources().getColor(R.color.colorPrimaryDark));
        return label;
    }

    public static LabelWithAction createLabel(Context context, JokeCategory category) {
        return createLabel(context, category.getDisplayName(), null, null);
    }

    public static LabelWithAction createLabel(Context context, String name) {
        return createLabel(context, name, null, null);
    }

    public static LabelWithAction createLabel(Context context, String name, String leftAction, String rightAction) {
        LabelWithAction label = new LabelWithAction(context);
        label.setText(name);
        label.setLeftActionSymbol(leftAction);
        label.setRightActionSymbol(rightAction);

        markSelected(label, false);
        addStandardOption(label);
        return label;
    }

    private static void addStandardOption(final LabelWithAction label) {
        label.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                markSelected(label, !LabelUtils.isSelected(label));
            }
        });
        label.addCallback(new LabelWithAction.ActionCallback() {
            @Override
            public void onRightAction() {
                markSelected(label, !LabelUtils.isSelected(label));
            }
            @Override
            public void onLeftAction() {}
        });
    }

    private static int dpi(Context context, int value) {
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.getResources().getDisplayMetrics());
    }

}
