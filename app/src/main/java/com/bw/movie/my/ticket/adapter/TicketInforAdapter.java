package com.bw.movie.my.ticket.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.base.BaseRecyclerAdapter;
import com.bw.movie.my.ticket.bean.TicketFoemationEntity;

import java.util.List;

public class TicketInforAdapter extends BaseRecyclerAdapter<RecyclerView.ViewHolder, TicketFoemationEntity> {
    private List<TicketFoemationEntity.ResultBean> list;
    private Context context;

    public TicketInforAdapter(List<TicketFoemationEntity.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_ticket, viewGroup, false);
        MyTicketViewHolder ticketViewHolder=new MyTicketViewHolder(view);
        return ticketViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
       if(holder instanceof MyTicketViewHolder){
           //填充布局
           ((MyTicketViewHolder) holder).addresstext.setText(list.get(i).getCinemaName());
           ((MyTicketViewHolder) holder).datetext.setText(list.get(i).getBeginTime()+"");
            ((MyTicketViewHolder) holder).dingdantext.setText(list.get(i).getOrderId()+"");
            ((MyTicketViewHolder) holder).moneytext.setText(list.get(i).getPrice()+"");
            ((MyTicketViewHolder) holder).numtext.setText(list.get(i).getAmount()+"");
            ((MyTicketViewHolder) holder).nametext.setText(list.get(i).getCinemaName());
       }
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }


    class MyTicketViewHolder extends RecyclerView.ViewHolder {

        private final TextView dingdantext;
        private final TextView addresstext;
        private final TextView nametext;
        private final TextView moneytext;
        private final TextView datetext;
        private final TextView numtext;

        public MyTicketViewHolder(@NonNull View itemView) {
            super(itemView);

            //寻找布局
            dingdantext = itemView.findViewById(R.id.user_shop_token_adapter_dingdan_text);
            addresstext = itemView.findViewById(R.id.user_shop_token_adapter_address_text);
            nametext = itemView.findViewById(R.id.user_shop_token_adapter_cimera_name_text);
            datetext = itemView.findViewById(R.id.user_shop_token_adapter_date_text);
            moneytext = itemView.findViewById(R.id.user_shop_token_adapter_money_text);
            numtext = itemView.findViewById(R.id.user_shop_token_adapter_num_text);
        }
    }

}
