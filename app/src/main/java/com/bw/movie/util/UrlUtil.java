package com.bw.movie.util;

/**
 * date:2018/12/25    17:57
 * author:Therefore(Lenovo)
 * fileName:UrlUtil
 */
public interface UrlUtil {
     String TOTAL = "http://mobile.bwstudent.com/movieApi/";
    //public String TOTAL = "http://mobile.bwstudent.com/movieApi/";
    //取消点赞
    String NFOLLOWCINEMA = "cinema/v1/verify/cancelFollowCinema";
    //电影列表
    String SCHEDULELIST = "movie/v1/findMovieScheduleList";
    //点赞
    String YFOLLOWCINEMA = "cinema/v1/verify/followCinema";
    //影院评论点赞
    String GOOD = "cinema/v1/verify/cinemaCommentGreat";
    //查询电影信息明细
    String CINEMAMDETAILS = "cinema/v1/findCinemaInfo";
    //查询影院用户评论列表
    String MEVALUATE = "cinema/v1/findAllCinemaComment";
    //影院内容轮播接口
    String PARTICULARS = "movie/v1/findHotMovieList";
    //根据影院ID查询该影院当前排期的电影列表
    String FINDMOVIELIST = "movie/v1/findMovieListByCinemaId";
    //查询所有电影
    String FINDALLCINEMA = "cinema/v1/findAllCinemas";
    //附近影院
    String NEIGHBOUR = "cinema/v1/findRecommendCinemas";
    //根据电影Id 查询影院
    String CINEMAIDFINDCINEMA = "movie/v1/findCinemasListByMovieId";
    //电影取消关注
    String CANCELFOLLOWMOVIE = "movie/v1/verify/cancelFollowMovie";
    //通过ID 查找 电影详情页面
    String FINDMOVIESDETAIL = "movie/v1/findMoviesDetail";
    //电影关注电影
    String FOLLOWMOVIE = "movie/v1/verify/followMovie";
    //电影请求评论接口
    String MOVIECOMMENT = "movie/v1/findAllMovieComment";
    //评论回复
    String COMMENTREPLY = "movie/v1/verify/commentReply";
    //点赞
    String MOVIECOMMENTGREAT = "movie/v1/verify/movieCommentGreat";
    //关注影片
    String FINDCINEMAPAGELIST = "cinema/v1/verify/findCinemaPageList?count=10";
    String FINDMOVIEPAGELIST = "movie/v1/verify/findMoviePageList?count=5";
    //.根据用户ID查询用户信息
    String USERINFOBYID = "user/v1/verify/getUserInfoByUserId";
    //修改用户信息
    String UPDATEUSER = "user/v1/verify/modifyUserInfo";
    //查询新版本
    String NEWVERSION = "tool/v1/findNewVersion";
    //我的意见
    String MYRECORD = "tool/v1/verify/recordFeedBack";
    //查询系统消息列表
    String SYSMSG = "tool/v1/verify/findAllSysMsgList?count=8";
    //系统消息读取状态修改
    String CHANGESYSMSGSTATUS = "tool/v1/verify/changeSysMsgStatus";
    //查询用户当前未读消息数量
    String MESSAGECOUNT = "tool/v1/verify/findUnreadMessageCount";
    //购票信息
    String BUYRECORD ="user/v1/verify/findUserBuyTicketRecordList";
    //修改头像
    String UPDATEHEADPIC="user/v1/verify/uploadHeadPic";
    //注册
    String REGISTER="user/v1/registerUser";
    //登录
    String LOGIN="user/v1/login";
    //上传消息推送使用的token
    String PUSHTOKEN="tool/v1/verify/uploadPushToken";
    //支付成功
    String PAY="movie/v1/verify/pay";
    //微信登录
    String WECHATLOGIN="user/v1/weChatBindingLogin";
    //请求轮播数据
    String HOTMOVIE="movie/v1/findHotMovieList";
    //请求正在热映数据
    String RELEASEMOVIE="movie/v1/findReleaseMovieList";
    //正在上映
    String COMINGSOONMOVIE="movie/v1/findComingSoonMovieList";
    //附近影院
    String RECOMMEN="cinema/v1/findNearbyCinemas";
    //购票下单
    String SHOPPAY = "movie/v1/verify/buyMovieTicket";
}
