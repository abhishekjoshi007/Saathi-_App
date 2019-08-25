package com.invaders.saathi;

public enum Disability {
    VISION, HEARING, SPEECH, PHYSICAL, MENTAL;

    public String getDisplayName() {
        switch (this) {
            case MENTAL:
                return "Mental Disability";
            case SPEECH:
                return "Speech Impairment";
            case VISION:
                return "Vision Disability";
            case HEARING:
                return "Hearing Disability";
            case PHYSICAL:
                return "Physical Disability";
            default:
                return "Error";
        }
    }
}
