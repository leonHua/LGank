package com.leon.lgank.ui;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.leon.lgank.R;

public class EditActivity extends BaseActivity {
    private View mView;
    private TextInputLayout mLayoutNickName;
    private TextInputLayout mLayoutBlog;
    private TextInputLayout mLayoutOther;
    private String mName;
    private String mBlog;
    private String mOther;
    private Button mBtnSave;

    @Override
    protected void initOperation(Intent intent) {

    }

    /**
     * 校验信息是否非空
     *
     * @return
     */
    private boolean checkInfo() {
        mName = mLayoutNickName.getEditText().getText().toString().trim();
        if (StringUtils.isEmpty(mName)) {
            mLayoutNickName.setError("请填写昵称");
            return false;
        }
        mBlog = mLayoutBlog.getEditText().getText().toString().trim();
        if (StringUtils.isEmpty(mBlog)) {
            mLayoutBlog.setError("请填写博客地址");
            return false;
        }
        mOther = mLayoutOther.getEditText().getText().toString().trim();
        if (StringUtils.isEmpty(mOther)) {
            mLayoutOther.setError("请填写其他地址");
            return false;
        }
        mLayoutNickName.setError("");
        mLayoutBlog.setError("");
        mLayoutOther.setError("");
        return true;
    }


    @Override
    protected View addContentView() {
        mView = View.inflate(this, R.layout.activity_edit, null);
        mLayoutNickName = (TextInputLayout) mView.findViewById(R.id.layout_nickname);
        mLayoutBlog = (TextInputLayout) mView.findViewById(R.id.layout_blog);
        mLayoutOther = (TextInputLayout) mView.findViewById(R.id.layout_other);
        mBtnSave = (Button) mView.findViewById(R.id.btn_save);
        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkInfo()) {
                    return;
                }
                //保存信息
                SPUtils.getInstance().put("nickname", mName);
                SPUtils.getInstance().put("blogurl", mBlog);
                SPUtils.getInstance().put("otherurl", mOther);
                finish();
            }
        });
        return mView;
    }

    @Override
    protected void updateOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_save).setVisible(false);
        menu.findItem(R.id.action_share).setVisible(false);
        menu.findItem(R.id.action_download).setVisible(false);
    }

    @Override
    protected String setToolbarTitle() {
        return "编辑";
    }
}
