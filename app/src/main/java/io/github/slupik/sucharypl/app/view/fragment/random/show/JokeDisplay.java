/*
 * Copyright (c) 2019. by Sebastian Witasik
 * All rights reserved. No part of this application may be reproduced or be part of other software, without the prior written permission of the publisher. For permission requests, write to the author(WitasikSebastian@gmail.com).
 */

package io.github.slupik.sucharypl.app.view.fragment.random.show;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.slupik.domain.entity.category.JokeCategory;
import io.github.slupik.domain.entity.joke.JokeOnline;
import io.github.slupik.sucharypl.R;
import io.github.slupik.sucharypl.app.view.custom.dialog.FavouritesDialog;

import static io.github.slupik.domain.entity.joke.JokeLikeState.DISLIKE;
import static io.github.slupik.domain.entity.joke.JokeLikeState.LIKE;
import static io.github.slupik.domain.entity.joke.JokeLikeState.NEUTRAL;

public class JokeDisplay extends FrameLayout {

    private final JokeOnline mJokeData;
    private final JokeController mController;
    @BindView(R.id.tvContent)
    TextView tvContent;
    @BindView(R.id.tvJokeCategories)
    TextView tvJokeCategories;
    @BindView(R.id.tvJokeRate)
    TextView tvRateInfo;
    @BindView(R.id.ivFavourite)
    ImageView ivFavourite;
    @BindView(R.id.ivLearn)
    ImageView ivLearn;
    @BindView(R.id.ivReport)
    ImageView ivReport;
    @BindView(R.id.ivJokeLike)
    ImageView ivLike;
    @BindView(R.id.ivJokeDislike)
    ImageView ivDislike;

    private boolean mFavourite;
    private boolean mReport;
    private boolean mToLearn;
    private short mLikeState;

    public JokeDisplay(@NonNull Context context, JokeOnline data, JokeController controller) {
        super(context);
        this.mJokeData = data;
        this.mController = controller;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_joke_display, this);
        ButterKnife.bind(this, view);

        setReport(mJokeData.isReport());
        setLikeState(mJokeData.getLikeState());
        setContent(mJokeData.getContent());
        setCategories(mJokeData.getCategories());
        setRate(mJokeData.getRate());
        setToLearn(mJokeData.isToLearn());
        setFavourite(mJokeData.isFavourite(), true);
    }

    @OnClick(R.id.ivJokeLike)
    void onLikeClick() {
        if(mLikeState==LIKE) {
            setLikeState(NEUTRAL);
        } else {
            setLikeState(LIKE);
        }
    }

    @OnClick(R.id.ivJokeDislike)
    void onDislikeClick() {
        if(mLikeState==DISLIKE) {
            setLikeState(NEUTRAL);
        } else {
            setLikeState(DISLIKE);
        }
    }

    @OnClick(R.id.ivLearn)
    void onLearnClick() {
        setToLearn(!mToLearn);
    }

    @OnClick(R.id.ivFavourite)
    void onFavouriteClick() {
        setFavourite(!mFavourite);
    }

    @OnClick(R.id.ivReport)
    void onReportClick() {
        setReport(!mReport);
    }

    private void setReport(boolean report) {
        //TODO add dialog
        mReport = report;
        if(report) {
            ivReport.setVisibility(View.GONE);
        } else {
            ivReport.setVisibility(View.VISIBLE);
        }
        mController.onReport(report);
    }

    private void setLikeState(short state) {
        mLikeState = state;
        switch (state) {
            case DISLIKE: {
                ivLike.setColorFilter(R.color.colorSecondaryTextLight);
                ivDislike.setColorFilter(Color.RED);
                break;
            }
            case LIKE: {
                ivLike.setColorFilter(Color.GREEN);
                ivDislike.setColorFilter(R.color.colorSecondaryTextLight);
                break;
            }
            default: {
                ivLike.setColorFilter(R.color.colorSecondaryTextLight);
                ivDislike.setColorFilter(R.color.colorSecondaryTextLight);
                break;
            }
        }
        mController.onLikeState(state);
    }

    private void setContent(String content) {
        tvContent.setText(content);
    }

    private void setCategories(JokeCategory[] categories) {
        StringBuilder text = new StringBuilder();
        for(int i=0;i<categories.length;i++) {
            JokeCategory category = categories[i];
            text.append(category.getDisplayName());
            if(i<categories.length-1) {
                text.append(", ");
            }
        }
        tvJokeCategories.setText(text.toString());
        if(text.length()==0) {
            tvJokeCategories.setVisibility(View.GONE);
        }
    }

    private void setRate(float rate) {
        String formatted = Math.round(rate*100)/100+"/10";
        tvRateInfo.setText(formatted);
    }

    private void setFavourite(boolean favourite) {
        setFavourite(favourite, false);
    }
    private void setFavourite(boolean favourite, boolean force) {
        if(force) {
            //TODO connect to the background
            mFavourite = favourite;
            if(favourite) {
                ivFavourite.setImageDrawable(getContext().getDrawable(R.drawable.ic_full_star));
                mController.addFavourite(mJokeData.getId());
            } else {
                ivFavourite.setImageDrawable(getContext().getDrawable(R.drawable.ic_empty_star));
                mController.removeFavourite(mJokeData.getId());
            }
        } else  {
            if(favourite) {
                FavouritesDialog dialog = new FavouritesDialog(getContext());
                dialog.setOnSave(new FavouritesDialog.OnSaveListener() {
                    @Override
                    public void onSave(List<JokeCategory> selected) {
                        setFavourite(true, true);
                    }
                });
                dialog.show();
            } else {
                //TODO stylisation
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage(getString(R.string.dialog_fav_remove_title))
                        .setCancelable(false)
                        .setPositiveButton(getString(R.string.dialog_fav_remove_yes), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                setFavourite(false, true);
                            }
                        })
                        .setNegativeButton(getString(R.string.dialog_fav_remove_no), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        }
    }

    private String getString(@StringRes int res) {
        return getContext().getString(res);
    }

    private void setToLearn(boolean toLearn) {
        //TODO add confirmation dialog
        mToLearn = toLearn;
        if(toLearn) {
            ivLearn.setImageDrawable(getContext().getDrawable(R.drawable.ic_notebook_checked));
            mController.addToLearn(mJokeData.getId());
        } else {
            ivLearn.setImageDrawable(getContext().getDrawable(R.drawable.ic_notebook));
            mController.removeToLearn(mJokeData.getId());
        }
    }

}
