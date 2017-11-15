package liu.chj.mbabygo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
 * 日期：2016/12/28 - 9:32
 * 注释：找回密码（忘记密码）
 */
public class GetBackPasswordActivity extends AutoLayoutActivity implements View.OnClickListener {
    /**
     * 返回键
     */
    @ViewInject(R.id.iv_get_back_password_back)
    private ImageView iv_get_back_password_back;
    /**
     * 获取验证码
     */
    @ViewInject(R.id.bt_identifying_code)
    private Button bt_identifying_code;
    /**
     * /**
     * 提交
     */
    @ViewInject(R.id.iv_forget_password_submit)
    private Button iv_forget_password_submit;
    /**
     * 输入手机号
     */
    @ViewInject(R.id.et_forget_password_phone)
    private EditText et_forget_password_phone;
    @ViewInject(R.id.tv_again)
    private TextView tv_again;
    /**
     * 输入验证码
     */
    private String userphone;
    private Thread thread = null;
    public boolean isChange = false;
    private boolean tag = true;
    private int i = 60;
    /**
     * 客户端输入的验证码
     */
    private String valicationCode;
    /**
     * 服务器端获取的验证码
     */
    private static String serverValicationCode;
    @ViewInject(R.id.et_forget_password_identifying_code)
    private EditText et_forget_password_identifying_code;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_back_password);
        Intent intent = getIntent();
        x.view().inject(this);
        setEditPhone();
        iv_get_back_password_back.setOnClickListener(this);
        bt_identifying_code.setOnClickListener(this);
        iv_forget_password_submit.setOnClickListener(this);
        //先隐藏密码
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_get_back_password_back:
                finish();
                break;
            /**
             * 获取验证码
             */
            case R.id.bt_identifying_code:
                //必须有手机号才能点击验证码

                if (!isPhone())
                    break;
                bt_identifying_code.setClickable(true);
                bt_identifying_code.setBackgroundResource(R.mipmap.identifying_code);
                isChange = true;
                changeBtnGetCode();
//                getValidateCode();
                break;
            /**
             * 提交
             */
            case R.id.iv_forget_password_submit:
                if (!isPhone())
                    break;
                if (et_forget_password_identifying_code.getText().toString().equals("123")) {
//                    Toast.makeText(GetBackPasswordActivity.this, "提交成功！", Toast.LENGTH_SHORT).show();
                    Intent revisePassword = new Intent(GetBackPasswordActivity.this, RevisePasswordActivity.class);
                    startActivity(revisePassword);
                } else {

                    Toast.makeText(GetBackPasswordActivity.this, "验证码不对！", Toast.LENGTH_SHORT).show();
                }

                break;
            default:
                break;
        }
    }

    private void changeBtnGetCode() {
        thread = new Thread() {
            @Override
            public void run() {
                if (tag) {
                    while (i > 0) {
                        i--;
                        if (GetBackPasswordActivity.this == null) {
                            break;
                        }
                        GetBackPasswordActivity.this
                                .runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv_again.setVisibility(View.VISIBLE);
                                        tv_again.setText(
                                                i + "秒后可重新发送验证码");
                                        bt_identifying_code
                                                .setClickable(false);
                                        bt_identifying_code.setBackgroundResource(R.mipmap.identifying_code);
                                    }
                                });
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    tag = false;
                }
                i = 60;
                tag = true;
                if (GetBackPasswordActivity.this != null) {
                    GetBackPasswordActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            bt_identifying_code.setText("获取验证码");
                            bt_identifying_code.setClickable(true);
                            tv_again.setVisibility(View.INVISIBLE);
                            bt_identifying_code.setBackgroundResource(R.mipmap.identifying_code_pre);
                        }
                    });
                }
            }

            ;
        };
        thread.start();
    }

    /**
     * 判断手机号
     */
    private boolean isPhone() {
        // TODO Auto-generated method stub
        userphone = et_forget_password_phone.getText().toString().trim();
        if (TextUtils.isEmpty(userphone)) {
            Toast.makeText(GetBackPasswordActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return false;
        }
        boolean result = PhoneUtils.isPhone(userphone);
        if (result == false) {
            Toast.makeText(GetBackPasswordActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * 判断手机号
     */
    private void SetPhoneAndPassword() {

        userphone = et_forget_password_phone.getText().toString().trim();
        if (TextUtils.isEmpty(userphone)) {
            Toast.makeText(GetBackPasswordActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        boolean result = PhoneUtils.isPhone(userphone);
        if (result == false) {
            Toast.makeText(GetBackPasswordActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (userphone == "13513521352" || et_forget_password_identifying_code.getText().toString() == "123456") {
            Toast.makeText(GetBackPasswordActivity.this, "提交成功！", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(GetBackPasswordActivity.this, "请输入正确验证码", Toast.LENGTH_SHORT).show();
        }

    }

    public void setEditPhone() {
        et_forget_password_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(et_forget_password_identifying_code.getText()) || TextUtils.isEmpty(et_forget_password_phone.getText())) {
                    iv_forget_password_submit.setBackgroundResource(R.mipmap.submit);
                    iv_forget_password_submit.setClickable(false);
                } else {
                    iv_forget_password_submit.setBackgroundResource(R.mipmap.submit_pre);
                    iv_forget_password_submit.setClickable(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_forget_password_identifying_code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(et_forget_password_identifying_code.getText()) || TextUtils.isEmpty(et_forget_password_phone.getText())) {
                    iv_forget_password_submit.setBackgroundResource(R.mipmap.submit);
                    iv_forget_password_submit.setClickable(false);
                } else {
                    iv_forget_password_submit.setBackgroundResource(R.mipmap.submit_pre);
                    iv_forget_password_submit.setClickable(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
