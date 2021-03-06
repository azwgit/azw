package com.example.bq.edmp.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bq.edmp.R;

public class DialoggerFinsh extends Dialog {
    private final String TITLE;
    private final String MESSAGE;
    private final onConfirmClickListener ONCONFIRMCLICKLISTENER;
    private final onCancelClickListener ONCANCELCLICKLISTENER;
    private final onFinshListener ONFINSHLISTENER;
    public TextView tvMessage;

    public TextView getTvMessage() {
        return tvMessage;
    }

    public void setTvMessage(TextView tvMessage) {
        this.tvMessage = tvMessage;
    }

    public interface onFinshListener {
        void onClick();
    }
    private TextView getTextView(){
        return  tvMessage;
    }
    public interface onConfirmClickListener {
        void onClick(View view);
    }

    public interface onCancelClickListener {
        void onClick(View view);
    }


    private DialoggerFinsh(@NonNull Context context, String title, String message,
                           onConfirmClickListener onConfirmClickListener, onCancelClickListener onCancelClickListener, onFinshListener onFinshListener) {
        super(context, R.style.MyUsualDialog);
        this.TITLE = title;
        this.MESSAGE = message;
        this.ONCONFIRMCLICKLISTENER = onConfirmClickListener;
        this.ONCANCELCLICKLISTENER = onCancelClickListener;
        this.ONFINSHLISTENER = onFinshListener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usual_new_dialog);
        setCanceledOnTouchOutside(false);
        initView();
    }

    public static Builder Builder(Context context) {
        return new Builder(context);
    }

    private void initView() {
        TextView tvTitle = findViewById(R.id.tv_title);
        tvMessage = findViewById(R.id.tv_message);

        if (!TITLE.isEmpty()) {
            tvTitle.setText(TITLE);
        }
        if (!MESSAGE.isEmpty()) {
            tvMessage.setText(MESSAGE);
        }
    }

    public DialoggerFinsh shown() {
        show();
        return this;
    }

    public static class Builder {
        private String mTitle;
        private String mMessage;
        private String mConfirmText;
        private String mCancelText;
        private onConfirmClickListener mOnConfirmClickListener;
        private onCancelClickListener mOnCcancelClickListener;
        private Context mContext;

        private Builder(Context context) {
            this.mContext = context;
        }

        public Builder setTitle(String title) {
            this.mTitle = title;
            return this;
        }

        public Builder setMessage(String message) {
            this.mMessage = message;
            return this;
        }

        public Builder setOnConfirmClickListener(String confirmText, onConfirmClickListener confirmclickListener) {
            this.mConfirmText = confirmText;
            this.mOnConfirmClickListener = confirmclickListener;
            return this;
        }

        public Builder setOnCancelClickListener(String cancelText, onCancelClickListener onCancelclickListener) {
            this.mCancelText = cancelText;
            this.mOnCcancelClickListener = onCancelclickListener;
            return this;
        }

        public DialoggerFinsh build() {
            return new DialoggerFinsh(mContext, mTitle, mMessage,
                    mOnConfirmClickListener, mOnCcancelClickListener, null);
        }
    }
}
