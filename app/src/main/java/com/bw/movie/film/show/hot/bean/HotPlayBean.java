package com.bw.movie.film.show.hot.bean;

import com.bw.movie.base.BaseEntity;

import java.util.List;

/*
 *作者:ash
 *TODO:
 *  正在热映 benan  类
 */
public class HotPlayBean extends BaseEntity {
    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public  class ResultBean {
        private int followMovie;
        private int id;
        private String imageUrl;
        private String name;
        private int rank;
        private long releaseTime;
        private String releaseTimeShow;
        private String summary;

        public int getFollowMovie() {
            return followMovie;
        }

        public void setFollowMovie(int followMovie) {
            this.followMovie = followMovie;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public long getReleaseTime() {
            return releaseTime;
        }

        public void setReleaseTime(long releaseTime) {
            this.releaseTime = releaseTime;
        }

        public String getReleaseTimeShow() {
            return releaseTimeShow;
        }

        public void setReleaseTimeShow(String releaseTimeShow) {
            this.releaseTimeShow = releaseTimeShow;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }
    }
}
