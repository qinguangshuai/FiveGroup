package com.bw.movie.cinema.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.bw.movie.R;
import com.bw.movie.base.BaseEvent;
import com.bw.movie.cinema.bean.neightbourbean.NeightBourResultBean;
import com.bw.movie.cinema.event.NeighbourEvent;
import com.bw.movie.cinema.follow.bean.FollowBean;
import com.bw.movie.cinema.follow.presenter.FollowProsenter;
import com.bw.movie.cinema.follow.view.FollowView;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/*
* NeightbourAdapder
* */
public class NeightbourAdapder extends RecyclerView.Adapter<NeightbourAdapder.NeightbourViewHolder> {
    List<NeightBourResultBean> listBeans;
    private Context mContext;



    public NeightbourAdapder(List< NeightBourResultBean> listBeans, Context mContext) {
        this.listBeans = listBeans;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public NeightbourViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.neightbouritem, viewGroup, false);
        return new NeightbourViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final NeightbourViewHolder neightbourViewHolder, final int i) {
        if (listBeans.get(i).getFollowCinema() == 1) {
            neightbourViewHolder.checkBox.setButtonDrawable(R.mipmap.com_icon_collection_selected_hdpi);
            neightbourViewHolder.checkBox.setChecked(true);

        } else {
            neightbourViewHolder.checkBox.setButtonDrawable(R.mipmap.com_icon_collection_default_hdpi);
            neightbourViewHolder.checkBox.setChecked(false);

        }
        neightbourViewHolder.textViewname.setText(listBeans.get(i).getName());
        neightbourViewHolder.textViewaddress.setText(listBeans.get(i).getAddress());
        neightbourViewHolder.textViewk.setText(listBeans.get(i).getCommentTotal() + "km");
        neightbourViewHolder.simpleDraweeView.setImageURI(Uri.parse(listBeans.get(i).getLogo()));
        neightbourViewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                BaseEvent.post(new NeighbourEvent(isChecked,neightbourViewHolder.checkBox,listBeans.get(i).getId()));

            }
        });
    }

    @Override
    public int getItemCount() {
        return listBeans == null ? 0 : listBeans.size();
    }

    class NeightbourViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView simpleDraweeView;
        TextView textViewname;
        TextView textViewaddress;
        TextView textViewk;
        CheckBox checkBox;

        public NeightbourViewHolder(@NonNull View itemView) {
            super(itemView);
            simpleDraweeView = itemView.findViewById(R.id.simpview);
            textViewname = itemView.findViewById(R.id.nameneighthour);
            textViewaddress = itemView.findViewById(R.id.addressneighthour);
            textViewk = itemView.findViewById(R.id.kneighthour);
            checkBox = itemView.findViewById(R.id.checkeds);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getListener.getList(v, getAdapterPosition());
                }
            });
        }
    }

    private getListener getListener;

    public void setGetListener(NeightbourAdapder.getListener getListener) {
        this.getListener = getListener;

    }

    public interface getListener {
        void getList(View view, int position);
    }
}
