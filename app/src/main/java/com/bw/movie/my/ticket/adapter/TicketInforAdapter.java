package com.bw.movie.my.ticket.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.bw.movie.R;
import com.bw.movie.base.BaseRecyclerAdapter;
import com.bw.movie.my.ticket.bean.ResultBean;
import com.bw.movie.my.ticket.bean.TicketFoemationEntity;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

public class TicketInforAdapter extends BaseRecyclerAdapter<RecyclerView.ViewHolder, TicketFoemationEntity> {
    private List<ResultBean> mList;
    private Context mContext;

    public TicketInforAdapter(List<ResultBean> list, Context context) {
        super(context);
        this.mList = list;
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_ticket, viewGroup, false);
        MyTicketViewHolder ticketViewHolder = new MyTicketViewHolder(view,mHttpClick);
        return ticketViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        if (holder instanceof MyTicketViewHolder) {
            if (mList.get(i).getStatus() != 2){
                //填充布局
                ((MyTicketViewHolder) holder).addresstext.setText(mList.get(i).getCinemaName());
                ((MyTicketViewHolder) holder).datetext.setText(mList.get(i).getBeginTime() + "");
                ((MyTicketViewHolder) holder).dingdantext.setText(mList.get(i).getOrderId() + "");
                ((MyTicketViewHolder) holder).moneytext.setText(mList.get(i).getPrice() + "");
                ((MyTicketViewHolder) holder).numtext.setText(mList.get(i).getAmount() + "");
                ((MyTicketViewHolder) holder).nametext.setText(mList.get(i).getCinemaName());
                //时间转换
                String createTime = mList.get(i).getCreateTime();
                GregorianCalendar gc = new GregorianCalendar();
                gc.setTimeInMillis(Long.parseLong(createTime));
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                ((MyTicketViewHolder) holder).longText.setText(df.format(gc.getTime()));
                if (mList.get(i).getStatus() == 1){
                    ((MyTicketViewHolder) holder).nopay.setText("待付款");
                }else  if (mList.get(i).getStatus() == 2){
                    ((MyTicketViewHolder) holder).nopay.setText("已付款");
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }


    class MyTicketViewHolder extends RecyclerView.ViewHolder {

        private final TextView dingdantext;
        private final TextView addresstext;
        private final TextView nametext;
        private final TextView moneytext;
        private final TextView datetext;
        private final TextView numtext, longText;
        private Button nopay;

        public MyTicketViewHolder(@NonNull View itemView, final HttpClick httpClick) {
            super(itemView);

            //寻找布局
            dingdantext = itemView.findViewById(R.id.user_shop_token_adapter_dingdan_text);
            addresstext = itemView.findViewById(R.id.user_shop_token_adapter_address_text);
            nametext = itemView.findViewById(R.id.user_shop_token_adapter_cimera_name_text);
            datetext = itemView.findViewById(R.id.user_shop_token_adapter_date_text);
            moneytext = itemView.findViewById(R.id.user_shop_token_adapter_money_text);
            numtext = itemView.findViewById(R.id.user_shop_token_adapter_num_text);
            longText = itemView.findViewById(R.id.longText);
            nopay = itemView.findViewById(R.id.user_shop_token_adapter_nopay);
            nopay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mHttpClick.click(v,getAdapterPosition());
                }
            });

        }
    }

    private HttpClick mHttpClick;

    public void setHttpClick(HttpClick httpClick) {
        mHttpClick = httpClick;
    }

    public interface HttpClick{
        void click(View view,int position);
    }
}
