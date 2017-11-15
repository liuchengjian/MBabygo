package liu.chj.mbabygo.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhy.autolayout.AutoLayoutActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.cutpicture.SelectDialog;

/**
 * 作者：柳成建
 * 日期：2016/12/29 - 11:22
 * 注释：
 */
public class ApplyDoyenActivity extends AutoLayoutActivity implements View.OnClickListener {
    private Intent intent;
    private Bundle bundle;
    private Context context;
    private static final int APPLY_DOYEN_NAME = 1;
    private static final int APPLY_DOYEN_NICHENG = 2;
    private static final int APPLY_DOYEN_SEX = 3;
    private static final int APPLY_DOYEN_PHONE = 4;
    private static final int APPLY_DOYEN_WECHAT = 5;
    private static final int APPLY_DOYEN_ADDRESS = 6;
    private static final int APPLY_DOYEN_CALSSIFY = 7;
    private static final int APPLY_DOYEN_PICTURE = 8;
    @ViewInject(R.id.iv_apply_doyen_remove)
    private ImageView iv_apply_doyen_remove;
    /**
     * 姓名
     */
    @ViewInject(R.id.ll_apply_doyen_name)
    private LinearLayout ll_apply_doyen_name;
    /**
     * 显示姓名
     */
    @ViewInject(R.id.tv_apply_doyen_name)
    private TextView tv_apply_doyen_name;
    /**
     * 昵称
     */
    @ViewInject(R.id.ll_apply_doyen_nicheng)
    private LinearLayout ll_apply_doyen_nicheng;
    /**
     * 显示昵称
     */
    @ViewInject(R.id.tv_apply_doyen_nicheng)
    private TextView tv_apply_doyen_nicheng;
    /**
     * 性别
     */
    @ViewInject(R.id.ll_apply_doyen_sex)
    private LinearLayout ll_apply_doyen_sex;
    /**
     * 显示性别
     */
    @ViewInject(R.id.tv_apply_doyen_sex)
    private TextView tv_apply_doyen_sex;
    /**
     * 手机
     */
    @ViewInject(R.id.ll_apply_doyen_phone)
    private LinearLayout ll_apply_doyen_phone;
    /**
     * 显示手机
     */
    @ViewInject(R.id.tv_apply_doyen_phone)
    private TextView tv_apply_doyen_phone;
    /**
     * 微信账号
     */
    @ViewInject(R.id.ll_apply_doyen_wechat)
    private LinearLayout ll_apply_doyen_wechat;
    /**
     * 显示微信账号
     */
    @ViewInject(R.id.tv_apply_doyen_wechat)
    private TextView tv_apply_doyen_wechat;
    /**
     * 联系地址
     */
    @ViewInject(R.id.ll_apply_doyen_address)
    private LinearLayout ll_apply_doyen_address;
    /**
     * 显示联系地址
     */
    @ViewInject(R.id.tv_apply_doyen_address)
    private TextView tv_apply_doyen_address;
    /**
     * 分类达人
     */
    @ViewInject(R.id.ll_apply_doyen_classify)
    private LinearLayout ll_apply_doyen_classify;
    /**
     * 显示分类达人
     */
    @ViewInject(R.id.tv_apply_doyen_classify)
    private TextView tv_apply_doyen_classify;
    /**
     * 相片
     */
    @ViewInject(R.id.ll_apply_doyen_picture)
    private LinearLayout ll_apply_doyen_picture;
    /**
     * 显示相片
     */
    @ViewInject(R.id.iv_apply_doyen_picture)
    private ImageView iv_apply_doyen_picture;
    private String path =null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_doyen);
        intent = getIntent();
        x.view().inject(this);
        context = ApplyDoyenActivity.this;

        iv_apply_doyen_remove.setOnClickListener(this);
        ll_apply_doyen_name.setOnClickListener(this);
        ll_apply_doyen_nicheng.setOnClickListener(this);
        ll_apply_doyen_sex.setOnClickListener(this);
        ll_apply_doyen_phone.setOnClickListener(this);
        ll_apply_doyen_wechat.setOnClickListener(this);
        ll_apply_doyen_address.setOnClickListener(this);
        ll_apply_doyen_classify.setOnClickListener(this);
        ll_apply_doyen_picture.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_apply_doyen_remove:
                finish();
                break;
            case R.id.ll_apply_doyen_name:
                Intent personal_name_revise = new Intent(ApplyDoyenActivity.this, ApplyDoyenNameActivity.class);
                bundle = new Bundle();
                personal_name_revise.putExtras(bundle);
                startActivityForResult(personal_name_revise, APPLY_DOYEN_NAME);
                break;
            case R.id.ll_apply_doyen_nicheng:
                Intent apply_doyen_nicheng = new Intent(ApplyDoyenActivity.this, ApplyDoyenNichengActivity.class);
                bundle = new Bundle();
                apply_doyen_nicheng.putExtras(bundle);
                startActivityForResult(apply_doyen_nicheng, APPLY_DOYEN_NICHENG);
                break;
            case R.id.ll_apply_doyen_sex:
                Sex();
                break;
            case R.id.ll_apply_doyen_phone:
                Intent apply_doyen_phone = new Intent(ApplyDoyenActivity.this, ApplyDoyenPhoneActivity.class);
                bundle = new Bundle();
                apply_doyen_phone.putExtras(bundle);
                startActivityForResult(apply_doyen_phone, APPLY_DOYEN_PHONE);
                break;
            case R.id.ll_apply_doyen_wechat:
                Intent apply_doyen_wechat = new Intent(ApplyDoyenActivity.this, ApplyDoyenWechatActivity.class);
                bundle = new Bundle();
                apply_doyen_wechat.putExtras(bundle);
                startActivityForResult(apply_doyen_wechat, APPLY_DOYEN_WECHAT);
                break;
            case R.id.ll_apply_doyen_address:
                Intent apply_doyen_address = new Intent(ApplyDoyenActivity.this, ApplyDoyenAddressActivity.class);
                bundle = new Bundle();
                apply_doyen_address.putExtras(bundle);
                startActivityForResult(apply_doyen_address, APPLY_DOYEN_ADDRESS);
                break;
            case R.id.ll_apply_doyen_classify:
                Intent apply_doyen_classify = new Intent(ApplyDoyenActivity.this, ApplyDoyenClassifyActivity.class);
                bundle = new Bundle();
                apply_doyen_classify.putExtras(bundle);
                startActivityForResult(apply_doyen_classify, APPLY_DOYEN_CALSSIFY);
                break;
            /**
             * 照片
             */
            case R.id.ll_apply_doyen_picture:
                Intent apply_doyen_picture = new Intent(ApplyDoyenActivity.this, ApplyDoyenPictureActivity.class);
                bundle = new Bundle();
                apply_doyen_picture.putExtras(bundle);
                startActivityForResult( apply_doyen_picture, APPLY_DOYEN_PICTURE);
                break;
            default:
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == APPLY_DOYEN_NAME && resultCode == RESULT_OK) {
            /**
             * 回传添加icon数据
             */
            bundle = data.getExtras(); //data为B中回传的Intent
            String name = bundle.getString("name");
            tv_apply_doyen_name.setText(name);
        } else if (requestCode == APPLY_DOYEN_NICHENG && resultCode == RESULT_OK) {
            /**
             * 回传添加icon数据
             */
            bundle = data.getExtras(); //data为B中回传的Intent
            String nicheng = bundle.getString("nicheng");
            tv_apply_doyen_nicheng.setText(nicheng);
        } else if (requestCode == APPLY_DOYEN_PHONE && resultCode == RESULT_OK) {
            /**
             * 回传添加icon数据
             */
            bundle = data.getExtras(); //data为B中回传的Intent
            String phone = bundle.getString("phone");
            tv_apply_doyen_phone.setText(phone);
        } else if (requestCode == APPLY_DOYEN_WECHAT && resultCode == RESULT_OK) {
            /**
             * 回传添加icon数据
             */
            bundle = data.getExtras(); //data为B中回传的Intent
            String phone = bundle.getString("wechat");
            tv_apply_doyen_wechat.setText(phone);
        } else if (requestCode == APPLY_DOYEN_ADDRESS && resultCode == RESULT_OK) {
            /**
             * 回传添加icon数据
             */
            bundle = data.getExtras(); //data为B中回传的Intent
            String phone = bundle.getString("address");
            tv_apply_doyen_address.setText(phone);
        } else if (requestCode == APPLY_DOYEN_CALSSIFY && resultCode == RESULT_OK) {
            /**
             * 回传添加icon数据
             */
            bundle = data.getExtras(); //data为B中回传的Intent
            String phone = bundle.getString("classify");
            tv_apply_doyen_classify.setText(phone);
        } else if (requestCode == APPLY_DOYEN_PICTURE && resultCode == RESULT_OK) {
            /**
             * 回传添加icon数据
             */
            bundle = data.getExtras(); //data为B中回传的Intent
            path = bundle.getString("picture");

            if (path == null) {
                iv_apply_doyen_picture.setVisibility(View.GONE);

            }else{
                iv_apply_doyen_picture.setVisibility(View.VISIBLE);
                Bitmap b = BitmapFactory.decodeFile(path);
                    Log.e("lchj","sdddddd"+path);
                iv_apply_doyen_picture.setImageBitmap(b);
            }
        }
    }

    public void Sex() {
        new SelectDialog(context)
                .builder()
                .setCancelable(true)
//                .setTitle("请选择操作")
                .setCanceledOnTouchOutside(true)
                .addSelectItem("男", SelectDialog.SelectItemColor.Blue,
                        new SelectDialog.OnSelectItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                tv_apply_doyen_sex.setText("男");
                            }
                        })
                .addSelectItem("女", SelectDialog.SelectItemColor.Blue,
                        new SelectDialog.OnSelectItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                tv_apply_doyen_sex.setText("女");
                            }
                        }).show();
    }

}
