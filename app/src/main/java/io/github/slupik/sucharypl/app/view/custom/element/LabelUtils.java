/*
 * Copyright (c) 2019. by Sebastian Witasik
 * All rights reserved. No part of this application may be reproduced or be part of other software, without the prior written permission of the publisher. For permission requests, write to the author(WitasikSebastian@gmail.com).
 */

package io.github.slupik.sucharypl.app.view.custom.element;

import android.os.Bundle;

public abstract class LabelUtils {

    private static final String PARAM_SELECTED = "selected";

    private LabelUtils(){}

    public static void markAsSelected(LabelWithAction label, boolean selected) {
        Bundle bundle = label.getBundle();
        if(bundle==null) {
            bundle = new Bundle();
        }
        bundle.putBoolean(PARAM_SELECTED, selected);
        label.setBundle(bundle);
    }

    public static boolean isSelected(LabelWithAction label) {
        Bundle bundle = label.getBundle();
        return bundle==null || bundle.getBoolean(PARAM_SELECTED, false);
    }
}
