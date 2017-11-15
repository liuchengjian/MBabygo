package liu.chj.mbabygo.activity;

import android.content.Intent;
import android.icu.text.IDNA;
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

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;
import liu.chj.mbabygo.R;
import liu.chj.mbabygo.ui.PhoneUtils;

/**
 * 作者：柳成建
 * 日期：2016/12/28 - 16:43
 * 注释：手机绑定界面
 */
public class PhoneBundingActivity extends AutoLayoutActivity implements View.OnClickListener{
    /**
     * 返回键
     */
    @ViewInject(R.id.iv_phone_bunding_back)
    private ImageView iv_phone_bunding_back;
    /**
     * 获取验证码
     */
    @ViewInject(R.id.bt_phone_bunding_identifying_code)
    private Button bt_phone_bunding_identifying_code;
    /**
     * /**
     * 提交
     */
    @ViewInject(R.id.bt_phone_bunding_submit)
    private Button bt_phone_bunding_submit;
    /**
     * 输入手机号
     */
    @ViewInject(R.id.et_phone_bunding_phone)
    private EditText et_phone_bunding_phone;
    @ViewInject(R.id.tv_phone_bunding_again)
    private TextView tv_phone_bunding_again;
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

    private RegisterPage registerPage;
    @ViewInject(R.id.et_phone_bunding_code)
    private EditText et_phone_bunding_code;
    private Intent intent;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_phone_bunding);
        intent = getIntent();
        x.view().inject(this);

        //先隐藏密码
        setEditPhone();
//        setSMScode();
        iv_phone_bunding_back.setOnClickListener(this);
        bt_phone_bunding_identifying_code.setOnClickListener(this);
        bt_phone_bunding_submit.setOnClickListener(this);
        //先隐藏密码
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_phone_bunding_back:
                finish();
                break;
            /**
             * 获取验证码
             */
            case R.id.bt_phone_bunding_identifying_code:
                //必须有手机号才能点击验证码

                if (!isPhone())
                    break;
                bt_phone_bunding_identifying_code.setClickable(true);
                bt_phone_bunding_identifying_code.setBackgroundResource(R.mipmap.identifying_code);
                isChange = true;
                changeBtnGetCode();
//                getValidateCode();
                break;
            /**
             * 提交
             */
            case R.id.bt_phone_bunding_submit:
                if (!isPhone())
                    break;
                if (et_phone_bunding_code.getText().toString().equals("123")) {
//                    Toast.makeText(GetBackPasswordActivity.this, "提交成功！", Toast.LENGTH_SHORT).show();
                    intent.putExtra("phone", et_phone_bunding_phone.getText().toString());
                    setResult(RESULT_OK, intent); //intent为A传来的带有Bundle的intent，当然也可以自己定义新的Bundle
                    finish();
                } else {

                    Toast.makeText(PhoneBundingActivity.this, "验证码不对！", Toast.LENGTH_SHORT).show();
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
                        if (PhoneBundingActivity.this == null) {
                            break;
                        }
                        PhoneBundingActivity.this
                                .runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv_phone_bunding_again.setVisibility(View.VISIBLE);
                                        tv_phone_bunding_again.setText(
                                                i + "秒后可重新发送验证码");
                                        bt_phone_bunding_identifying_code
                                                .setClickable(false);
                                        bt_phone_bunding_identifying_code.setBackgroundResource(R.mipmap.code_publish);
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
                if (PhoneBundingActivity.this != null) {
                    PhoneBundingActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            bt_identifying_code.setText("获取验证码");
                            bt_phone_bunding_identifying_code.setClickable(true);
                            tv_phone_bunding_again.setVisibility(View.INVISIBLE);
                            bt_phone_bunding_identifying_code.setBackgroundResource(R.mipmap.identifying_code_pre);
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
        userphone = et_phone_bunding_phone.getText().toString().trim();
        if (TextUtils.isEmpty(userphone)) {
            Toast.makeText(PhoneBundingActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return false;
        }
        boolean result = PhoneUtils.isPhone(userphone);
        if (result == false) {
            Toast.makeText(PhoneBundingActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * 判断手机号
     */
    private void SetPhoneAndPassword() {

        userphone = et_phone_bunding_phone.getText().toString().trim();
        if (TextUtils.isEmpty(userphone)) {
            Toast.makeText(PhoneBundingActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        boolean result = PhoneUtils.isPhone(userphone);
        if (result == false) {
            Toast.makeText(PhoneBundingActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (userphone == "13513521352" || et_phone_bunding_code.getText().toString() == "123456") {
            Toast.makeText(PhoneBundingActivity.this, "提交成功！", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(PhoneBundingActivity.this, "请输入正确验证码", Toast.LENGTH_SHORT).show();
        }

    }

    public void setEditPhone() {
        et_phone_bunding_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(et_phone_bunding_code.getText()) || TextUtils.isEmpty(et_phone_bunding_phone.getText())) {
                    bt_phone_bunding_submit.setBackgroundResource(R.mipmap.submit);
                    bt_phone_bunding_submit.setClickable(false);
                } else {
                    bt_phone_bunding_submit.setBackgroundResource(R.mipmap.submit_pre);
                    bt_phone_bunding_submit.setClickable(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_phone_bunding_code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(et_phone_bunding_code.getText()) || TextUtils.isEmpty(et_phone_bunding_phone.getText())) {
                    bt_phone_bunding_submit.setBackgroundResource(R.mipmap.submit);
                    bt_phone_bunding_submit.setClickable(false);
                } else {
                    bt_phone_bunding_submit.setBackgroundResource(R.mipmap.submit_pre);
                    bt_phone_bunding_submit.setClickable(true);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    public void setSMScode(){
        // 初始化短信 SDK
        SMSSDK.initSDK(this, "1a5bb8cfc53c0", "165b7434df0bc9c3fc88bdb590c47c59");
        // 集成的 GUI
        registerPage=new RegisterPage();
        // 注册按钮


        bt_phone_bunding_submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                registerPage.setRegisterCallback(new EventHandler(){

                    @Override
                    public void afterEvent(int event, int result, Object data) {
                        // 解析注册结果
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            @SuppressWarnings("unchecked")
                            HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
                            String country = (String) phoneMap.get("country");
                            String phone = (String) phoneMap.get("phone");
                            Log.e("lchj","11111111"+phone);
                            Log.e("lchj","2222"+country);

//                            // 验证成功跳转到设置密码、信息界面，继续完成注册
//                            Intent intent=new Intent(PhoneBundingActivity.this, IDNA.Info.class);
//                            intent.putExtra("country", country);
//                            intent.putExtra("phone", phone);
//                            startActivity(intent);
                        }
                    }


                });
                registerPage.show(PhoneBundingActivity.this);
            }
        });

    }
}
