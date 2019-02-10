/*
 * Copyright (c) 2019. by Sebastian Witasik
 * All rights reserved. No part of this application may be reproduced or be part of other software, without the prior written permission of the publisher. For permission requests, write to the author(WitasikSebastian@gmail.com).
 */

package io.github.slupik.sucharypl.app.view.model;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

public class ReportCategory {

    private final Context context;
    private int id;

    @StringRes
    private int idRes;

    public ReportCategory(Context context, int id, @StringRes int idRes) {
        this.context = context;
        this.id = id;
        this.idRes = idRes;
    }

    @NonNull
    @Override
    public String toString() {
        return context.getString(idRes);
    }
}