package com.bw.movie.cinema.recommend;

public class RecommendEvent {

    private String Longitude;
    private String Latitude;

    public RecommendEvent(String longitude, String latitude) {
        Longitude = longitude;
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }
}
