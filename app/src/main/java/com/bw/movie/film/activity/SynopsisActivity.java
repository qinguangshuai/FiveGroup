package com.bw.movie.film.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.film.adapter.WeakCurrencyAdapter;
import com.bw.movie.film.bean.DetailBean;
import com.bw.movie.film.p.FilmProsenter;
import com.bw.movie.film.v.DetailView;
import com.dak.weakview.adapter.viewholder.WeakCurrencyViewHold;
import com.dak.weakview.layout.WeakCardOverlapLayout;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.postprocessors.IterativeBoxBlurPostProcessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*-----------
 *🖐说明:
 *       详情 页面 Activity
 *       标题
 *       轮播图
 *       4个按钮
 */

public class SynopsisActivity extends BaseActivity {


    @BindView(R.id.card_synopsis)
    WeakCardOverlapLayout mCardSynopsis;
    @BindView(R.id.title_synopsis)
    TextView mTitleSynopsis;
    @BindView(R.id.background_synopsis)
    SimpleDraweeView mBackgroundSynopsis;
    @BindView(R.id.radiogroup_synopsis)
    RadioGroup mRadiogroupSynopsis;
    private WeakCurrencyAdapter<String> adapter;


    @Override
    public void initView() {
        ButterKnife.bind(this);
        Intent intent = getIntent();
        int id = intent.getIntExtra("详情id", -1);
        setCardSynopsis();
        getData(id);
        setRadioGroupListener();
    }

    @Override
    public void initListener() {
    }

    @Override
    public void initData() {
    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_synopsis;
    }

    @Override
    public void initVariable() {
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }


    //向第三方控件中set 数据
    public void setCardSynopsis() {
        mCardSynopsis.setParentClipChild(false);
        mCardSynopsis.setOnItemClickListener(new WeakCardOverlapLayout.OnItemClickListener() {
            @Override
            public void onWeakItemClickListener(int i, View view) {
            }
        });

        adapter = new WeakCurrencyAdapter<String>(this, R.layout.item_diycard) {
            @Override
            public void notifyItemView(WeakCurrencyViewHold holder, String item, int position) {
                Uri uri = Uri.parse(item);
                View convertView = holder.getConvertView();
                SimpleDraweeView img = convertView.findViewById(R.id.img_diycard);
                img.setImageURI(uri);
            }
        };
        mCardSynopsis.setAdapter(adapter);
    }


    //请求数据
    public void getData(int id) {
        new FilmProsenter(new DetailView<DetailBean>() {
            @Override
            public void onDataSuccess(DetailBean detailBean) {
                //拆装
                DetailBean.ResultBean result = detailBean.getResult();
                //获取图片集合
                List<String> posterList = result.getPosterList();
                //将数据装进适配器
                adapter.refreshData(posterList);

                //给tv 赋值
                mTitleSynopsis.setText(result.getName());
                //给背景 赋值
                onBlurry(Uri.parse(posterList.get(0)), mBackgroundSynopsis);
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
        }).getDetailBeanObservable(id);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    //高斯模糊效果
    public void onBlurry(Uri uri, SimpleDraweeView draweeView) {
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                //参数1:重度
                //参数2:半径
                .setPostprocessor(new IterativeBoxBlurPostProcessor(5, 5))
                .build();
        AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                .setOldController(draweeView.getController())
                .setImageRequest(request)
                .build();
        draweeView.setController(controller);
    }


    //设置点击事件
    public void setRadioGroupListener() {
        mRadiogroupSynopsis.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    //详情按钮
                    case R.id.rb_Datail_synopsis:
                        break;
                    //预告按钮
                    case R.id.rb_Trail_synopsis:
                        break;
                    //剧照按钮
                    case R.id.rb_Stills_synopsis:
                        break;
                    //影评
                    case R.id.rb_Review_synopsis:
                        break;
                }
            }
        });
    }


}



