package com.invaders.saathi;

import androidx.annotation.Keep;

@Keep
public enum Gender {
    MALE, FEMALE, OTHERS;

    public static String getNormal(Gender gender) {
        switch (gender) {
            case MALE:
                return "Male";
            case FEMALE:
                return "Female";
            case OTHERS:
                return "Others";
            default:
                return "Invalid";
        }
    }
}
