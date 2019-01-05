package com.bw.movie.film.adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.Constant;
import com.bw.movie.MyApp;
import com.bw.movie.R;
import com.bw.movie.cinema.activity.ParticularsActivity;
import com.bw.movie.cinema.cannelfollow.presenter.CannelFollowPresenter;
import com.bw.movie.cinema.cannelfollow.view.CannelFollowView;
import com.bw.movie.cinema.event.FollowEvent;
import com.bw.movie.cinema.follow.bean.FollowBean;
import com.bw.movie.cinema.follow.presenter.FollowProsenter;
import com.bw.movie.cinema.follow.view.FollowView;
import com.bw.movie.film.bean.CinemaBean;
import com.bw.movie.film.event.AffililaterEvent;
import com.bw.movie.util.EmptyUtil;
import com.bw.movie.util.ToastUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

/*
 *作者:ash
 *TODO:
 *
 */
public class AffililaterdAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private EmptyUtil emptyUtil = new EmptyUtil();

    private CinemaBean cinemaBean = new CinemaBean();

    public void setCinemaBean(CinemaBean cinemaBean) {
        this.cinemaBean = cinemaBean;
    }

    class Holder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView mImg;
        private final TextView mName;
        private final TextView mDetails;
        private final TextView mKm;
        private final CheckBox mGood;

        public Holder(View view) {
            super(view);
            mImg = view.findViewById(R.id.img_item_affililated);
            mName = view.findViewById(R.id.name_item_affililated);
            mDetails = view.findViewById(R.id.details_item_affililated);
            mKm = view.findViewById(R.id.km_item_affililated);
            mGood = view.findViewById(R.id.good_item_affililated);
            //点击事件
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getIntentsa.getInts(v,getAdapterPosition());
                }
            });


        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_affililated, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {


        final CinemaBean.ResultBean resultBean = cinemaBean.getResult().get(i);
        final Holder holder = (Holder) viewHolder;

        if (cinemaBean.getResult().get(i) .getFollowCinema() == 1) {
            holder.mGood.setButtonDrawable(R.mipmap.com_icon_collection_selected_hdpi);
            holder.mGood.setChecked(true);

        } else {
            holder.mGood.setButtonDrawable(R.mipmap.com_icon_collection_default_hdpi);
            holder.mGood.setChecked(false);

        }
//         holder.mGood.setChecked(resultBean.getFollowCinema()==1?true:false);
        holder.mName.setText(resultBean.getName());
        holder.mImg.setImageURI(Uri.parse(resultBean.getLogo()));
        holder.mKm.setText("待获取");
        holder.mDetails.setText(resultBean.getAddress());
        holder.mGood.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                  if (isChecked){
                      new FollowProsenter(new FollowView<FollowBean>() {
                          @Override
                          public void onDataSuccess(FollowBean followBean) {
                              if (followBean.getMessage().contains("成功")){
                                  holder.mGood.setButtonDrawable(R.mipmap.com_icon_collection_selected_hdpi);
                              }


                          }

                          @Override
                          public void onDataFailer(String msg) {

                          }

                          @Override
                          public void onShowLoading() {

                          }

                          @Override
                          public void onHideLoading() {

                          }
                      }).getFollow(resultBean.getId());
                  }else{
                      new CannelFollowPresenter(new CannelFollowView<FollowBean>() {
                          @Override
                          public void onDataSuccess(FollowBean followBean) {

                              if (followBean.getMessage().contains("成功")){
                                  holder.mGood.setButtonDrawable(R.mipmap.com_icon_collection_default_hdpi);
                              }


                          }
                          @Override
                          public void onDataFailer(String msg) {
                          }
                          @Override
                          public void onShowLoading() {
                          }
                          @Override
                          public void onHideLoading() {
                          }
                      }).getCannelFollow(resultBean.getId());
                  }
            }
        });



    }
    private getIntentsa getIntentsa;

    public void setGetIntents(AffililaterdAdapter.getIntentsa getIntents) {
        this.getIntentsa = getIntents;
    }

    public interface getIntentsa{
        void getInts(View view,int position);
    }

    @Override
    public int getItemCount() {
        if (emptyUtil.isNull(cinemaBean.getResult()) == false) {
            return cinemaBean.getResult().size();
        } else {
            return 0;
        }
    }
}
