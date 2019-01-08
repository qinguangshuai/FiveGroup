package com.bw.movie.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;

import com.bw.movie.R;
/*
* StatusView
* */
public class StatusView extends FrameLayout {

    private final int status_normal = 0;
    private final int status_loading = 1;
    private final int status_error = 2;
    private final int status_empity = 3;

    private View loadingView;
    private View errorView;
    private View contentView;
    private View empityView;

    private ViewStub loadingSub;
    private ViewStub errorSub;
    private ViewStub emptySub;
    private View.OnClickListener onRetryClickListener;

    private int loading_layout_id = -1;
    private int error_layout_id = -1;
    private int empty_layout_id = -1;

    private StatusView(Context context, Builder builder) {
        super(context);
        if (builder.contentView == null) {
            throw new NullPointerException("contView cant be null");
        }
        this.contentView = builder.contentView;
        error_layout_id = builder.error_layout_id;
        empty_layout_id = builder.empty_layout_id;
        loading_layout_id = builder.loading_layout_id;
        onRetryClickListener = builder.onRetryClickListener;
        init(context);
    }

    private void init(Context context) {
        loadingSub = new ViewStub(context);
        errorSub = new ViewStub(context);
        emptySub = new ViewStub(context);
        addView(loadingSub);
        addView(errorSub);
        addView(emptySub);
        addView(contentView);
    }

    public void showLoading() {
        changeStatus(status_loading);
    }

    public void showError() {
        changeStatus(status_error);
    }

    public void showEmpty() {
        changeStatus(status_empity);
    }

    public void showContent() {
        changeStatus(status_normal);
    }

    private void changeStatus(int status) {
        if (errorView != null) {
            errorView.setVisibility(GONE);
        }
        if (empityView != null) {
            empityView.setVisibility(GONE);
        }
        if (loadingView != null) {
            loadingView.setVisibility(GONE);
        }

        contentView.setVisibility(GONE);

        switch (status) {
            case status_empity:
                empityView = getEmpityView();
                empityView.setVisibility(VISIBLE);
                break;
            case status_error:
                errorView = getErrorView();
                errorView.setVisibility(VISIBLE);
                break;
            case status_loading:
                loadingView = getLoadingView();
                loadingView.setVisibility(VISIBLE);
                break;
            case status_normal:
                contentView.setVisibility(VISIBLE);
                break;
        }
    }

    private View getEmpityView() {
        if (empityView == null) {
            emptySub.setLayoutResource(empty_layout_id == -1 ? R.layout.layout_empity : empty_layout_id);
            empityView = emptySub.inflate();
            if (onRetryClickListener != null) {
                empityView.setOnClickListener(onRetryClickListener);
            }
        }
        return empityView;
    }

    public View getLoadingView() {
        if (loadingView == null) {
            loadingSub.setLayoutResource(loading_layout_id == -1 ? R.layout.layout_loading : loading_layout_id);
            loadingView = loadingSub.inflate();
        }
        return loadingView;
    }

    public View getErrorView() {
        if (errorView == null) {
            errorSub.setLayoutResource(error_layout_id == -1 ? R.layout.layout_error : error_layout_id);
            errorView = errorSub.inflate();
            if (onRetryClickListener != null) {
                errorView.setOnClickListener(onRetryClickListener);
            }
        }
        return errorView;
    }

    public static class Builder {
        private int loading_layout_id = -1;
        private int error_layout_id = -1;
        private int empty_layout_id = -1;
        private View.OnClickListener onRetryClickListener;
        private Context context;
        private View contentView;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder loadingId(int loadingId) {
            loading_layout_id = loadingId;
            return this;
        }

        public Builder erroryId(int erroryId) {
            error_layout_id = erroryId;
            return this;
        }

        public Builder emptyId(int emptyId) {
            empty_layout_id = emptyId;
            return this;
        }

        public Builder contentView(View contentView) {
            this.contentView = contentView;
            return this;
        }

        public Builder onRetryClickListener(OnClickListener onRetryClickListener) {
            this.onRetryClickListener = onRetryClickListener;
            return this;
        }

        public StatusView build() {
            return new StatusView(context, this);
        }
    }
}
