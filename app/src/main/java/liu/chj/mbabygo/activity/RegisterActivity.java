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

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;
import liu.chj.mbabygo.R;
import liu.chj.mbabygo.ui.PhoneUtils;

/**
 * 作者：柳成建
 * 日期：2016/12/28 - 15:36
 * 注释：快速注册
 */
public class RegisterActivity extends AutoLayoutActivity implements View.OnClickListener{
    /**
     * 返回键
     */
    @ViewInject(R.id.iv_quick_register_back)
    private ImageView iv_quick_register_back;
    /**
     * 获取验证码
     */
    @ViewInject(R.id.bt_quick_register_identifying_code)
    private Button bt_quick_register_identifying_code;
    /**
     * /**
     * 提交
     */
    @ViewInject(R.id.bt_quick_register_submit)
    private Button bt_quick_register_submit;
    /**
     * 输入手机号
     */

    @ViewInject(R.id.et_quick_register_phone)
    private EditText et_quick_register_phone;
    @ViewInject(R.id.tv_quick_register_again)
    private TextView tv_quick_register_again;
    /**
     * 输入验证码
     */
    private String userphone;
    private Thread thread = null;
    public boolean isChange = false;
    private boolean tag = true;
    private int i = 60;
    private Bundle bundle;
    Intent intent;
    Intent revisePassword;
    /**
     * 客户端输入的验证码
     */
    private String valicationCode;
    /**
     * 服务器端获取的验证码
     */
    private static String serverValicationCode;
    @ViewInject(R.id.et_quick_register_code)
    private EditText et_quick_register_code;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_register);
        intent = getIntent();
        x.view().inject(this);

        //注册短信回调监听
        SMSSDK.registerEventHandler(ev);
        //先隐藏密码
        setEditPhone();
        iv_quick_register_back.setOnClickListener(this);
        bt_quick_register_identifying_code.setOnClickListener(this);
        bt_quick_register_submit.setOnClickListener(this);


    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_quick_register_back:
                finish();
                break;
            /**
             * 获取验证码
             */
            case R.id.bt_quick_register_identifying_code:
                //必须有手机号才能点击验证码

                if (!isPhone())
                    break;
                bt_quick_register_identifying_code.setClickable(true);
                bt_quick_register_identifying_code.setBackgroundResource(R.mipmap.identifying_code);
                isChange = true;
                changeBtnGetCode();
                /**
                 * 获取验证码
                 *
                 */
                getSecurity();
                break;
            /**
             * 提交
             */
            case R.id.bt_quick_register_submit:
                if (!isPhone())
                    break;
                //在服务器验证验证码
                SecurityCode();
//                revisePassword = new Intent(RegisterActivity.this, RegisterPasswordActivity.class);
//                revisePassword.putExtra("userphone", userphone);
//                revisePassword.setFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
//                startActivity(revisePassword);
//                finish();
                break;
            default:
                break;
        }
    }

    /**
     * 1分钟验证码时间
     */
    private void changeBtnGetCode() {
        thread = new Thread() {
            @Override
            public void run() {
                if (tag) {
                    while (i > 0) {
                        i--;
                        if (RegisterActivity.this == null) {
                            break;
                        }
                        RegisterActivity.this
                                .runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv_quick_register_again.setVisibility(View.VISIBLE);
                                        tv_quick_register_again.setText(
                                                i + "秒后可重新发送验证码");
                                        bt_quick_register_identifying_code
                                                .setClickable(false);
                                        bt_quick_register_identifying_code.setBackgroundResource(R.mipmap.identifying_code);
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
                if (RegisterActivity.this != null) {
                    RegisterActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            bt_quick_register_identifying_code.setClickable(true);
                            tv_quick_register_again.setVisibility(View.INVISIBLE);
                            bt_quick_register_identifying_code.setBackgroundResource(R.mipmap.identifying_code_pre);
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
        userphone = et_quick_register_phone.getText().toString().trim();
        if (TextUtils.isEmpty(userphone)) {
            Toast.makeText(RegisterActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return false;
        }
        boolean result = PhoneUtils.isPhone(userphone);
        if (result == false) {
            Toast.makeText(RegisterActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * 判断手机号
     */
    private void SetPhoneAndPassword() {

        userphone = et_quick_register_phone.getText().toString().trim();
        if (TextUtils.isEmpty(userphone)) {
            Toast.makeText(RegisterActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        boolean result = PhoneUtils.isPhone(userphone);
        if (result == false) {
            Toast.makeText(RegisterActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (userphone == "13513521352" || et_quick_register_code.getText().toString() == "123456") {
            Toast.makeText(RegisterActivity.this, "提交成功！", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(RegisterActivity.this, "请输入正确验证码", Toast.LENGTH_SHORT).show();
        }

    }

    public void setEditPhone() {
        et_quick_register_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(et_quick_register_code.getText()) || TextUtils.isEmpty(et_quick_register_phone.getText())) {
                    bt_quick_register_submit.setBackgroundResource(R.mipmap.submit);
                    bt_quick_register_submit.setClickable(false);
                } else {
                    bt_quick_register_submit.setBackgroundResource(R.mipmap.submit_pre);
                    bt_quick_register_submit.setClickable(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_quick_register_code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(et_quick_register_code.getText()) || TextUtils.isEmpty(et_quick_register_phone.getText())) {
                    bt_quick_register_submit.setBackgroundResource(R.mipmap.submit);
                    bt_quick_register_submit.setClickable(false);
                } else {
                    bt_quick_register_submit.setBackgroundResource(R.mipmap.submit_pre);
                    bt_quick_register_submit.setClickable(true);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    /**
     * 短信验证的回调监听
     */
    private EventHandler ev = new EventHandler() {
        @Override
        public void afterEvent(int event, int result, Object data) {
            if (result == SMSSDK.RESULT_COMPLETE) { //回调完成
                //提交验证码成功,如果验证成功会在data里返回数据。data数据类型为HashMap<number,code>
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    Log.e("TAG", "提交验证码成功" + data.toString());
                    HashMap<String, Object> mData = (HashMap<String, Object>) data;
                    String country = (String) mData.get("country");//返回的国家编号
                    String phone = (String) mData.get("phone");//返回用户注册的手机号

                    Log.e("TAG", country + "====" + phone);

                    if (phone.equals(userphone)) {
                        runOnUiThread(new Runnable() {//更改ui的操作要放在主线程，实际可以发送hander
                            @Override
                            public void run() {
                                //跳转到设置密码界面
                                revisePassword = new Intent(RegisterActivity.this, RegisterPasswordActivity.class);
                                revisePassword.putExtra("userphone", userphone);
                                revisePassword.setFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
                                startActivity(revisePassword);
                                finish();
                                Toast.makeText(RegisterActivity.this, "通过验证", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                     Toast.makeText(RegisterActivity.this, "验证失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {//获取验证码成功
                    Log.e("TAG", "获取验证码成功");
                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                    //返回支持发送验证码的国家列表

                }
            } else {
                ((Throwable) data).printStackTrace();
            }
        }
    };


    /**
     * 获取验证码
     *
     */
    public void getSecurity() {
        //发送短信，传入国家号和电话---使用SMSSDK核心类之前一定要在MyApplication中初始化，否侧不能使用
        if (TextUtils.isEmpty(userphone)) {
            Toast.makeText(this, "号码不能为空！", Toast.LENGTH_SHORT).show();
        } else {
            SMSSDK.getVerificationCode("+86",userphone);
            Toast.makeText(this, "发送成功:" + userphone, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 向服务器提交验证码，在监听回调中判断是否通过验证
     *
     */
    public void SecurityCode() {
        String security = et_quick_register_code.getText().toString();
        if (!TextUtils.isEmpty(security)) {
            //提交短信验证码
            SMSSDK.submitVerificationCode("+86", userphone, security);//国家号，手机号码，验证码
            Toast.makeText(this, "提交了注册信息:" + userphone, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "验证码不能为空", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //短信验证要在activity销毁时反注册，否则会造成内存泄漏问题
        SMSSDK.unregisterAllEventHandler();
    }

    /**
     * 回调函数给上级界面
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if(resultCode==RESULT_OK){
//
//            intent.putExtra("userphone",  userphone);
//            setResult(RESULT_OK);
//            finish();
//        }
    }
}
