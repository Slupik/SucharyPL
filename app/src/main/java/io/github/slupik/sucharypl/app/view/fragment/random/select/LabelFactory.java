/*
 * Copyright (c) 2019. by Sebastian Witasik
 * All rights reserved. No part of this application may be reproduced or be part of other software, without the prior written permission of the publisher. For permission requests, write to the author(WitasikSebastian@gmail.com).
 */

package io.github.slupik.sucharypl.app.view.fragment.random.select;


import android.content.Context;

import io.github.slupik.sucharypl.R;
import io.github.slupik.sucharypl.app.view.custom.element.LabelWithAction;

final class LabelFactory {

    private LabelFactory(){}

    static LabelWithAction createLabel(Context context, String name, String leftAction, String rightAction) {
        LabelWithAction label = new LabelWithAction(context);
        label.setText(name);
        label.setLeftActionSymbol(leftAction);
        label.setRightActionSymbol(rightAction);

        label.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
        label.setBorderColor(context.getResources().getColor(R.color.colorItemBorder));
        label.setActionColor(context.getResources().getColor(R.color.colorSecondaryText));

        return label;
    }

}
