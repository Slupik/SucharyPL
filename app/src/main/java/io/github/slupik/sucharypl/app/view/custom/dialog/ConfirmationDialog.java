/*
 * Copyright (c) 2019. by Sebastian Witasik
 * All rights reserved. No part of this application may be reproduced or be part of other software, without the prior written permission of the publisher. For permission requests, write to the author(WitasikSebastian@gmail.com).
 */

package io.github.slupik.sucharypl.app.view.custom.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.TextView;

import io.github.slupik.sucharypl.R;

public class ConfirmationDialog extends AskingDialog {

    public ConfirmationDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void setButtonsText(TextView confirm, TextView cancel) {
        confirm.setText(R.string.dialog_confirm_yes);
        cancel.setText(R.string.dialog_confirm_no);
    }
}
