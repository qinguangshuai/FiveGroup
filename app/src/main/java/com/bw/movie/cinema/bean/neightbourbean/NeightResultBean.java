package com.bw.movie.cinema.bean.neightbourbean;

import java.util.List;

public class NeightResultBean {
    private List<NeightFollowCinemasBean> followCinemas;
    private List<NeightNearbyCinemaListBean> nearbyCinemaList;

    public List<NeightFollowCinemasBean> getFollowCinemas() {
        return followCinemas;
    }

    public void setFollowCinemas(List<NeightFollowCinemasBean> followCinemas) {
        this.followCinemas = followCinemas;
    }

    public List<NeightNearbyCinemaListBean> getNearbyCinemaList() {
        return nearbyCinemaList;
    }

    public void setNearbyCinemaList(List<NeightNearbyCinemaListBean> nearbyCinemaList) {
        this.nearbyCinemaList = nearbyCinemaList;
    }
}
