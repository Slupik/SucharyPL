/*
 * Copyright (c) 2019. by Sebastian Witasik
 * All rights reserved. No part of this application may be reproduced or be part of other software, without the prior written permission of the publisher. For permission requests, write to the author(WitasikSebastian@gmail.com).
 */

package io.github.slupik.sucharypl.app.view.custom.element;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.slupik.sucharypl.R;

public class LabelWithAction extends LinearLayout {

    @ColorInt
    private int mBorderColor;
    @ColorInt
    private int mTextColor;
    @ColorInt
    private int mActionColor;

    private String leftActionSymbol;
    private String rightActionSymbol;
    private String text;

    @BindView(R.id.custom_lwa_main_container)
    ConstraintLayout mainContainer;
    @BindView(R.id.custom_lwa_left_action)
    TextView leftAction;
    @BindView(R.id.custom_lwa_text)
    TextView name;
    @BindView(R.id.custom_lwa_right_action)
    TextView rightAction;

    private final List<ActionCallback> callbacks = new ArrayList<>();
    private Bundle bundle;

    public LabelWithAction(Context context) {
        this(context, null);
    }

    public LabelWithAction(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.LabelWithAction,
                0, 0);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE);
        View child = inflater.inflate(R.layout.custom_label_with_action, null);
        super.addView(child);

        ButterKnife.bind(this, child);

        try {
            setBorderColor(a.getColor(R.styleable.LabelWithAction_borderColor, Color.LTGRAY));
            setActionColor(a.getColor(R.styleable.LabelWithAction_actionColor, Color.DKGRAY));
            setTextColor(a.getColor(R.styleable.LabelWithAction_textColor, Color.BLACK));
            setLeftActionSymbol(a.getString(R.styleable.LabelWithAction_leftActionSymbol));
            setRightActionSymbol(a.getString(R.styleable.LabelWithAction_rightActionSymbol));
            setText(a.getString(R.styleable.LabelWithAction_labelName));
        } finally {
            a.recycle();
        }
    }

    public int getBorderColor() {
        return mBorderColor;
    }

    public void setBorderColor(int borderColor) {
        this.mBorderColor = borderColor;
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadius(1500);
        shape.setColor(Color.TRANSPARENT);
        shape.setStroke((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()), mBorderColor);
        mainContainer.setBackground(shape);
        invalidate();
        requestLayout();
    }

    public int getTextColor() {
        return mTextColor;
    }

    public void setTextColor(int textColor) {
        this.mTextColor = textColor;
        name.setTextColor(mTextColor);
        invalidate();
        requestLayout();
    }

    public int getActionColor() {
        return mActionColor;
    }

    public void setActionColor(int actionColor) {
        this.mActionColor = actionColor;
        rightAction.setTextColor(mActionColor);
        leftAction.setTextColor(mActionColor);
        invalidate();
        requestLayout();
    }

    public String getLeftActionSymbol() {
        return leftActionSymbol;
    }

    public void setLeftActionSymbol(String leftActionSymbol) {
        this.leftActionSymbol = leftActionSymbol;
        leftAction.setText(leftActionSymbol);
        invalidate();
        requestLayout();
    }

    public String getRightActionSymbol() {
        return rightActionSymbol;
    }

    public void setRightActionSymbol(String rightActionSymbol) {
        this.rightActionSymbol = rightActionSymbol;
        rightAction.setText(rightActionSymbol);
        invalidate();
        requestLayout();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        name.setText(text);
        invalidate();
        requestLayout();
    }

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    public void addCallback(ActionCallback callback) {
        callbacks.add(callback);
    }

    public void removeCallback(ActionCallback callback) {
        callbacks.remove(callback);
    }

    @OnClick(R.id.custom_lwa_right_action)
    void onRightAction() {
        for(ActionCallback callback:callbacks) {
            callback.onRightAction();
        }
    }

    @OnClick(R.id.custom_lwa_left_action)
    void onLeftAction() {
        for(ActionCallback callback:callbacks) {
            callback.onLeftAction();
        }
    }

    public interface ActionCallback {
        void onLeftAction();
        void onRightAction();
    }
}
