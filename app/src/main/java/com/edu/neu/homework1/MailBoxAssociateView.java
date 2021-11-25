package com.edu.neu.homework1;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.MultiAutoCompleteTextView;

import androidx.annotation.NonNull;

/**
 * 这个就是我们继承自MultiAutoCompleteTextView实现我们自定义的邮箱联想组件
 */
public class MailBoxAssociateView extends androidx.appcompat.widget.AppCompatMultiAutoCompleteTextView {
    //卧槽把好多构造方法都写上它就不报错了卧槽！！！！！
    public MailBoxAssociateView(@NonNull Context context) {
        super(context);
    }
    public MailBoxAssociateView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MailBoxAssociateView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    public boolean enoughToFilter() {
        // 如果字符中包含'@'并且不在第一位，则满足条件
        return getText().toString().contains("@") && getText().toString().indexOf("@") > 0;

    }
}
