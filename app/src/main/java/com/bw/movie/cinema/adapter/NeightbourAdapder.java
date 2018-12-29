package com.bw.movie.cinema.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.cinema.bean.NeightbourBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class NeightbourAdapder extends RecyclerView.Adapter<NeightbourAdapder.NeightbourViewHolder> {
    private List<NeightbourBean.ResultBean.NearbyCinemaListBean> listBeans;
    private Context mContext;

    public NeightbourAdapder(List<NeightbourBean.ResultBean.NearbyCinemaListBean> listBeans, Context mContext) {
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
    public void onBindViewHolder(@NonNull NeightbourViewHolder neightbourViewHolder, int i) {
        neightbourViewHolder.textViewname.setText(listBeans.get(i).getName());
        neightbourViewHolder.textViewaddress.setText(listBeans.get(i).getAddress());
        neightbourViewHolder.textViewk.setText(listBeans.get(i).getCommentTotal()+"km");
        neightbourViewHolder.simpleDraweeView.setImageURI(Uri.parse(listBeans.get(i).getLogo()));
    }

    @Override
    public int getItemCount() {
        return listBeans==null?0:listBeans.size();
    }

    class  NeightbourViewHolder extends RecyclerView.ViewHolder{
    SimpleDraweeView simpleDraweeView;
    TextView textViewname;
    TextView textViewaddress;
    TextView textViewk;
        public NeightbourViewHolder(@NonNull View itemView) {
            super(itemView);
            simpleDraweeView=itemView.findViewById(R.id.simpview);
            textViewname  = itemView.findViewById(R.id.nameneighthour);
            textViewaddress = itemView.findViewById(R.id.addressneighthour);
            textViewk = itemView.findViewById(R.id.kneighthour);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                       getListener.getList(v,getAdapterPosition());
                }
            });
        }
    }
    private getListener getListener;

    public void setGetListener(NeightbourAdapder.getListener getListener) {
        this.getListener = getListener;

    }

    public interface getListener{
        void getList(View view, int position);
    }
}
