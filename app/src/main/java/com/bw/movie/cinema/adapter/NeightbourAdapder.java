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
import com.bw.movie.base.BaseRecyclerAdapter;
import com.bw.movie.cinema.bean.neightbourbean.NeightBourResultBean;
import com.bw.movie.cinema.event.NeighbourEvent;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/*
* NeightbourAdapder
* */
public class NeightbourAdapder extends BaseRecyclerAdapter<RecyclerView.ViewHolder,NeightBourResultBean> {
    private List<NeightBourResultBean> mListbeans;
    private Context mContext;

    public NeightbourAdapder(List<NeightBourResultBean> list, Context context) {
        super(context);
        this.mListbeans = list;
        this.mContext = context;
    }

    @NonNull
    @Override
    public NeightbourViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.neightbouritem, viewGroup, false);
        return new NeightbourViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int i) {
        if (mListbeans.get(i).getFollowCinema() == 1) {
            ((NeightbourViewHolder) holder).checkBox.setButtonDrawable(R.mipmap.com_icon_collection_selected_hdpi);
            ((NeightbourViewHolder) holder).checkBox.setChecked(true);

        } else {
            ((NeightbourViewHolder) holder).checkBox.setButtonDrawable(R.mipmap.com_icon_collection_default_hdpi);
            ((NeightbourViewHolder) holder).checkBox.setChecked(false);

        }
        ((NeightbourViewHolder) holder).textViewname.setText(mListbeans.get(i).getName());
        ((NeightbourViewHolder) holder).textViewaddress.setText(mListbeans.get(i).getAddress());
        ((NeightbourViewHolder) holder).textViewk.setText(mListbeans.get(i).getCommentTotal() + "km");
        ((NeightbourViewHolder) holder).simpleDraweeView.setImageURI(Uri.parse(mListbeans.get(i).getLogo()));
        ((NeightbourViewHolder) holder).checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                BaseEvent.post(new NeighbourEvent(isChecked,((NeightbourViewHolder) holder).checkBox, mListbeans.get(i).getId()));

            }
        });
    }

    @Override
    public int getItemCount() {
        return mListbeans == null ? 0 : mListbeans.size();
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
