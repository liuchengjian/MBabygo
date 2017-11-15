package liu.chj.mbabygo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.autolayout.AutoLayoutActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.ui.PhoneUtils;

/**
 * 作者：柳成建
 * 日期：2016/12/27 - 14:27
 * 注释：登录界面activity
 */
public class LoginActivity extends AutoLayoutActivity implements View.OnClickListener {
    /**
     * 返回键
     */
    @ViewInject(R.id.iv_login_back)
    private ImageView iv_login_back;
    /**
     * 登录
     */
    @ViewInject(R.id.iv_login_login)
    private ImageView iv_login_login;
    /**
     * 显示密码
     */
    @ViewInject(R.id.iv_login_see)
    private ImageView iv_login_see;
    /**
     * 输入手机号
     */
    @ViewInject(R.id.et_login_phone)
    private EditText et_login_phone;
    /**
     * 输入密码
     */
    @ViewInject(R.id.et_login_password)
    private EditText et_login_password;
    String userphone;
    /**
     * 是否显示密码
     */
    private Boolean showPassword = true;
    /**
     * 找回密码
     */
    @ViewInject(R.id.tv_get_back_password)
    private TextView tv_get_back_password;
    /**
     * 快速注册
     */
    @ViewInject(R.id.tv_quick_register)
    private TextView tv_quick_register;
    Intent intent;
    private Bundle bundle;
    Intent quick_register;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        intent = getIntent();
        x.view().inject(this);
        //先隐藏密码
        et_login_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        /**
         * 显示黄色登录键的业务逻辑
         */
        setedit();


        //设置监听
        iv_login_back.setOnClickListener(this);
        iv_login_login.setOnClickListener(this);
        iv_login_see.setOnClickListener(this);
        tv_get_back_password.setOnClickListener(this);
        tv_quick_register.setOnClickListener(this);
    }
    public void setedit(){
        et_login_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(et_login_password.getText())||TextUtils.isEmpty(et_login_phone.getText())) {
                    iv_login_login.setImageResource(R.mipmap.bt_login_login);
                } else {
                    iv_login_login.setImageResource(R.mipmap.bt_login_login_pre);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_login_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(et_login_password.getText())||TextUtils.isEmpty(et_login_phone.getText())) {
                    iv_login_login.setImageResource(R.mipmap.bt_login_login);
                } else {
                    iv_login_login.setImageResource(R.mipmap.bt_login_login_pre);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.iv_login_login:
                if (TextUtils.isEmpty(et_login_password.getText())||TextUtils.isEmpty(et_login_phone.getText())) {
                    return;
                } else {
                    SetPhoneAndPassword();
                }
                break;
            case R.id.iv_login_see:
                if (showPassword) {//显示密码
                    showPassword = !showPassword;
//                    Toast.makeText(LoginActivity.this, "你点了我", Toast.LENGTH_SHORT).show();
                    iv_login_see.setImageResource(R.mipmap.login_password_see);
                    et_login_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    et_login_password.setSelection(et_login_password.getText().toString().length());
                } else {//隐藏密码
//                    Toast.makeText(LoginActivity.this, "你点了他", Toast.LENGTH_SHORT).show();
                    showPassword = !showPassword;
                    iv_login_see.setImageResource(R.mipmap.login_password_see_pre);
                    et_login_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    et_login_password.setSelection(et_login_password.getText().toString().length());
                }
                break;
            case R.id.tv_get_back_password:
                Intent get_back_password = new Intent(LoginActivity.this,GetBackPasswordActivity.class);
                startActivity(get_back_password);
                break;
            case R.id.tv_quick_register:
                quick_register = new Intent(LoginActivity.this,RegisterActivity.class);
                bundle = new Bundle();
                quick_register.putExtras(bundle);
                startActivityForResult(quick_register,0);
                break;
            default:
                break;
        }
    }

    /**
     * 判断手机号和密码，显示密码等业务
     */
    private void SetPhoneAndPassword() {

        userphone = et_login_phone.getText().toString().trim();
        if (TextUtils.isEmpty(userphone)) {
            Toast.makeText(LoginActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        boolean result = PhoneUtils.isPhone(userphone);
        if (result == false) {
            Toast.makeText(LoginActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
            Log.e("lchj", "" + userphone);
            return;
        }
        if (TextUtils.isEmpty(et_login_password.getText())) {
            Toast.makeText(LoginActivity.this, "密码名不能为空！", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(LoginActivity.this, "输入正确", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            //返回电话号码和密码
            et_login_phone.setText(data.getStringExtra("mUserphone"));
            et_login_password.setText(data.getStringExtra("password"));
        }
    }


}
