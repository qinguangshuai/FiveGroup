package com.bw.movie.cinema.bean;


import java.io.Serializable;
import java.util.List;

public class NeightbourBean implements Serializable {


    /**
     * result : {"followCinemas":[{"address":"北京市西城区天桥南大街3号楼一层、二层（天桥艺术大厦南侧）","commentTotal":6,"distance":0,"followCinema":1,"id":3,"logo":"http://172.17.8.100/images/movie/logo/sd.jpg","name":"首都电影院"},{"address":"东城区滨河路乙1号雍和航星园74-76号楼","commentTotal":21,"distance":0,"followCinema":1,"id":1,"logo":"http://172.17.8.100/images/movie/logo/qcgx.jpg","name":"青春光线电影院"},{"address":"西城区前门大街大栅栏街36号","commentTotal":4,"distance":0,"followCinema":1,"id":2,"logo":"http://172.17.8.100/images/movie/logo/dgl.jpg","name":"大观楼电影院"}],"nearbyCinemaList":[{"address":"北京市海淀区远大路1号B座5层魔影国际影城","commentTotal":7,"distance":0,"followCinema":1,"id":4,"logo":"http://172.17.8.100/images/movie/logo/mygj.jpg","name":"魔影国际影城"},{"address":"朝阳区湖景东路11号新奥购物中心B1(东A口)","commentTotal":5,"distance":0,"followCinema":2,"id":5,"logo":"http://172.17.8.100/images/movie/logo/CGVxx.jpg","name":"CGV星星影城"}]}
     * message : 查询成功
     * status : 0000
     */

    private ResultBean result;
    private String message;
    private String status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

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

    public static class ResultBean {
        private List<FollowCinemasBean> followCinemas;
        private List<NearbyCinemaListBean> nearbyCinemaList;

        public List<FollowCinemasBean> getFollowCinemas() {
            return followCinemas;
        }

        public void setFollowCinemas(List<FollowCinemasBean> followCinemas) {
            this.followCinemas = followCinemas;
        }

        public List<NearbyCinemaListBean> getNearbyCinemaList() {
            return nearbyCinemaList;
        }

        public void setNearbyCinemaList(List<NearbyCinemaListBean> nearbyCinemaList) {
            this.nearbyCinemaList = nearbyCinemaList;
        }

        public static class FollowCinemasBean {
            /**
             * address : 北京市西城区天桥南大街3号楼一层、二层（天桥艺术大厦南侧）
             * commentTotal : 6
             * distance : 0
             * followCinema : 1
             * id : 3
             * logo : http://172.17.8.100/images/movie/logo/sd.jpg
             * name : 首都电影院
             */

            private String address;
            private int commentTotal;
            private int distance;
            private int followCinema;
            private int id;
            private String logo;
            private String name;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getCommentTotal() {
                return commentTotal;
            }

            public void setCommentTotal(int commentTotal) {
                this.commentTotal = commentTotal;
            }

            public int getDistance() {
                return distance;
            }

            public void setDistance(int distance) {
                this.distance = distance;
            }

            public int getFollowCinema() {
                return followCinema;
            }

            public void setFollowCinema(int followCinema) {
                this.followCinema = followCinema;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class NearbyCinemaListBean {
            /**
             * address : 北京市海淀区远大路1号B座5层魔影国际影城
             * commentTotal : 7
             * distance : 0
             * followCinema : 1
             * id : 4
             * logo : http://172.17.8.100/images/movie/logo/mygj.jpg
             * name : 魔影国际影城
             */

            private String address;
            private int commentTotal;
            private int distance;
            private int followCinema;
            private int id;
            private String logo;
            private String name;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getCommentTotal() {
                return commentTotal;
            }

            public void setCommentTotal(int commentTotal) {
                this.commentTotal = commentTotal;
            }

            public int getDistance() {
                return distance;
            }

            public void setDistance(int distance) {
                this.distance = distance;
            }

            public int getFollowCinema() {
                return followCinema;
            }

            public void setFollowCinema(int followCinema) {
                this.followCinema = followCinema;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
