package liu.chj.mbabygo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.zhy.autolayout.AutoLayoutActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.ui.PhoneUtils;

/**
 * 作者：柳成建
 * 日期：2016/12/28 - 13:33
 * 注释：修改密码
 */
public class RevisePasswordActivity extends AutoLayoutActivity implements View.OnClickListener{
    @ViewInject(R.id.iv_revise_password_back)
    private ImageView iv_revise_password_back;
    @ViewInject(R.id.et_revise_new_password)
    private EditText et_revise_new_password;
    @ViewInject(R.id.et_revise_new_password_again)
    private EditText et_revise_new_password_again;
    @ViewInject(R.id.iv_revise_password_sure)
    private Button iv_revise_password_sure;
    String newpassword;
    String newpasswordagain;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revise_password);
        Intent intent = getIntent();
        x.view().inject(this);

        setPassword();
        iv_revise_password_back.setOnClickListener(this);
        iv_revise_password_sure.setOnClickListener(this);
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.iv_revise_password_back:
                finish();
                break;
            case R.id.iv_revise_password_sure:
                if(!isPassword())
                    break;
                if (!newpassword.equals(newpasswordagain)) {
                    Toast.makeText(RevisePasswordActivity.this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(RevisePasswordActivity.this, "修改密码成功", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
    /**
     * 判断密码是否输入正确
     */
    private boolean isPassword() {
        newpassword = et_revise_new_password.getText().toString().trim();
        newpasswordagain = et_revise_new_password_again.getText().toString().trim();
        boolean result = PhoneUtils.isPassword(newpassword);
        if (result == false) {
            Toast.makeText(RevisePasswordActivity.this, "请正确的输入密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        boolean resultagain = PhoneUtils.isPassword(newpasswordagain);
        if (resultagain== false) {
            Toast.makeText(RevisePasswordActivity.this, "请正确的输入密码", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
    private void setPassword(){
        /**
         * 隐藏密码
         */
        et_revise_new_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        et_revise_new_password.setSelection(et_revise_new_password.getText().toString().length());
        et_revise_new_password_again.setTransformationMethod(PasswordTransformationMethod.getInstance());
        et_revise_new_password_again.setSelection(et_revise_new_password_again.getText().toString().length());
        /**
         * 输入密码显示提交图标
         */
        et_revise_new_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (et_revise_new_password.getText().length()>5&&et_revise_new_password_again.getText().length()>5) {
                    iv_revise_password_sure.setBackgroundResource(R.mipmap.revise_password_sure_pre);
                    iv_revise_password_sure.setClickable(true);
                } else {
                    iv_revise_password_sure.setBackgroundResource(R.mipmap.revise_password_sure);
                    iv_revise_password_sure.setClickable(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_revise_new_password_again.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (et_revise_new_password.getText().length()>5&&et_revise_new_password_again.getText().length()>5) {
                    iv_revise_password_sure.setBackgroundResource(R.mipmap.revise_password_sure_pre);
                    iv_revise_password_sure.setClickable(true);
                } else {
                    iv_revise_password_sure.setBackgroundResource(R.mipmap.revise_password_sure);
                    iv_revise_password_sure.setClickable(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
