package com.bw.movie.film.show.popular.bean;

import com.bw.movie.base.BaseEntity;
import java.util.List;

/*
 *作者:ash
 *TODO:
 *  热门的benan 类 同  轮播
 */
public class PopularBean extends BaseEntity {

    //成功 or 失败
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


    public class ResultBean {
        private int followMovie;
        private int id;
        private String imageUrl;
        private String name;
        private int rank;
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

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }
    }
}
