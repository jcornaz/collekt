package com.github.jcornaz.collekt;

import com.github.jcornaz.collekt.impl.KotlinList;

public enum ListImplementation {
    KOTLIN_LIST(KotlinList.Factory);

    public final PersistentListFactory factory;

    ListImplementation(PersistentListFactory factory) {
        this.factory = factory;
    }
}
