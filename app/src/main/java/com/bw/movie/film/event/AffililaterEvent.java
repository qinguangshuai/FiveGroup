package com.bw.movie.film.event;
/*
* AffililaterEvent
* */
public class AffililaterEvent {
    private int AffililaterId;

    public AffililaterEvent(int affililaterId) {
        AffililaterId = affililaterId;
    }

    public int getAffililaterId() {
        return AffililaterId;
    }

    public void setAffililaterId(int affililaterId) {
        AffililaterId = affililaterId;
    }
}
