package liu.chj.mbabygo.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Path;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.autolayout.AutoLayoutActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.cutpicture.CutPictureAty;
import liu.chj.mbabygo.cutpicture.SelectDialog;
import liu.chj.mbabygo.entity.StringIcon;

/**
 * 作者：柳成建
 * 日期：2016/12/19 - 11:08
 * 注释：
 */
public class ActivityPublishActivity extends AutoLayoutActivity implements View.OnClickListener {
    private final int CAMERA_WITH_DATA = 1;
    /**
     * 本地图片选取标志
     */
    private static final int FLAG_CHOOSE_IMG = 2;
    /**
     * 截取结束标志
     */
    private static final int FLAG_MODIFY_FINISH = 3;
    private static final int ACTIVITY_ICON = 4;
    private static final int WRITE_COMMENT_ACTIVITY = 5;
    public static final String TMP_PATH = "temp.jpg";
    private Context context;
    @ViewInject(R.id.iv_activity_cut_picture)
    private ImageView iv_activity_cut_picture;
    /**
     * 取消按钮
     */
    @ViewInject(R.id.tv_activity_remove)
    private TextView tv_activity_remove;
    /**
     * 显示icon
     */
    @ViewInject(R.id.tv_iocn_text)
    private TextView tv_iocn_text;
    /**
     * 发布按钮
     */
    @ViewInject(R.id.iv_activity_publish)
    private ImageView iv_activity_publish;
    /**
     * icon点击选择按钮
     */
    @ViewInject(R.id.tv_choose)
    private TextView tv_choose;
    /**
     * icon点击填写详情按钮
     */
    @ViewInject(R.id.tv_comment_revise)
    private TextView tv_comment_revise;
    /**
     * title输入框
     */
    @ViewInject(R.id.et_activity_publish_title)
    private EditText et_activity_publish_title;
     /**
     * theme输入框
     */
    @ViewInject(R.id.et_activity_publish_theme)
    private EditText et_activity_publish_theme;
    /**
     * time输入框
     */
    @ViewInject(R.id.et_activity_publish_time)
    private EditText et_activity_publish_time;
    /**
     * place输入框
     */
    @ViewInject(R.id.et_activity_publish_place)
    private EditText et_activity_publish_place;
    /**
     * price输入框
     */
    @ViewInject(R.id.et_activity_publish_price)
    private EditText et_activity_publish_price;
    /**
     * 详情textView
     */
    @ViewInject(R.id.tv_comment)
    private TextView tv_comment;

    Bitmap b;
     String path;
    private Set<Integer> setiocn = new HashSet<>();
    private Intent iconintent;
    private Intent writeintent;
    private Bundle bundle;
    private String icon;
    private List<String> listicon = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_publish);
        Intent intent = getIntent();
        x.view().inject(this);
        setEdit();
        iv_activity_cut_picture.setOnClickListener(this);
        tv_activity_remove.setOnClickListener(this);
        tv_choose.setOnClickListener(this);
        iv_activity_publish.setOnClickListener(this);
        tv_comment_revise.setOnClickListener(this);
        context = ActivityPublishActivity.this;
    }

    /**
     * 弹出选择照片菜单
     */
    public void showSelectPictureMenu() {
        new SelectDialog(context)
                .builder()
                .setCancelable(true)
                .setTitle("请选择操作")
                .setCanceledOnTouchOutside(true)
                .addSelectItem("相机", SelectDialog.SelectItemColor.Blue,
                        new SelectDialog.OnSelectItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                startCamera();
                            }
                        })
                .addSelectItem("图库", SelectDialog.SelectItemColor.Blue,
                        new SelectDialog.OnSelectItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                startAlbum();
                            }
                        }).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == FLAG_CHOOSE_IMG && resultCode == RESULT_OK) {
            if (data != null) {
                Uri uri = data.getData();
                if (!TextUtils.isEmpty(uri.getAuthority())) {
                    Cursor cursor = getContentResolver().query(uri,
                            new String[]{MediaStore.Images.Media.DATA},
                            null, null, null);
                    if (null == cursor) {
                        Toast.makeText(getApplicationContext(), "图片没找到", Toast.LENGTH_SHORT)
                                .show();
                        return;
                    }
                    cursor.moveToFirst();
                    String path = cursor.getString(cursor
                            .getColumnIndex(MediaStore.Images.Media.DATA));
                    cursor.close();

                    Intent intent = new Intent(this, CutPictureAty.class);
                    intent.putExtra("path", path);
                    startActivityForResult(intent, FLAG_MODIFY_FINISH);
                } else {
                    Intent intent = new Intent(this, CutPictureAty.class);
                    intent.putExtra("path", uri.getPath());
                    startActivityForResult(intent, FLAG_MODIFY_FINISH);
                }
            }
        } else if (requestCode == FLAG_MODIFY_FINISH && resultCode == RESULT_OK) {
            if (data != null) {
                path = data.getStringExtra("path");
                Bitmap b = BitmapFactory.decodeFile(path);
                Log.e("lchj","sdddddd"+path);
                iv_activity_cut_picture.setImageBitmap(b);
            }
        } else if (requestCode == ACTIVITY_ICON && resultCode == RESULT_OK) {
            /**
             * 回传添加icon数据
             */
//            Bundle b=data.getExtras(); //data为B中回传的Intent
//            String strName=b.getString("strName");//strName即为回传的值
//            Log.e("lchj","aaaaaaaaaa"+strName);
            bundle = data.getExtras();

            setiocn = (Set<Integer>) bundle.getSerializable("icon");
//            Log.e("lchj","aaaaaaaaaa"+setiocn);
            /**
             * 遍历set列表icon
             */
            tv_iocn_text.setText("");
            for (Integer integer : setiocn) {
                Log.e("lchj", "qqqqqqqqqq" + setiocn.size());
                icon = StringIcon.mVals[integer];
                tv_iocn_text.append(icon + "、");
            }
            /**
             * 截取文本的0到tv_iocn_text.length()-1长度，去掉最后个顿号;
             */
            tv_iocn_text.setText(tv_iocn_text.getText().toString().substring(0, tv_iocn_text.length() - 1));
            if (tv_iocn_text.getText().equals("")) {
                tv_choose.setText("点击选择");
            } else {
                tv_choose.setText("点击修改");

            }

        }else if(requestCode == ACTIVITY_ICON && resultCode == CONTEXT_INCLUDE_CODE){
            Log.e("lchj","111111111111");
            tv_iocn_text.setText("");
        }
        switch (requestCode) {
            case CAMERA_WITH_DATA:
                // 照相机程序返回的,再次调用图片剪辑程序去修剪图片
                startCropImageActivity(Environment.getExternalStorageDirectory()
                        + "/" + TMP_PATH);
                break;

            default:
                break;
        }
    }

    // 裁剪图片的Activity
    private void startCropImageActivity(String path) {
        Intent intent = new Intent(this, CutPictureAty.class);
        intent.putExtra("path", path);
        startActivityForResult(intent, FLAG_MODIFY_FINISH);
    }

    private void startAlbum() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, FLAG_CHOOSE_IMG);
    }

    private void startCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(
                Environment.getExternalStorageDirectory(), TMP_PATH)));
        startActivityForResult(intent, CAMERA_WITH_DATA);
    }

    /**
     * 通过uri获取文件路径
     *
     * @param mUri
     * @return
     */
    public String getFilePath(Uri mUri) {
        try {
            if (mUri.getScheme().equals("file")) {
                return mUri.getPath();
            } else {
                return getFilePathByUri(mUri);
            }
        } catch (FileNotFoundException ex) {
            return null;
        }
    }

    // 获取文件路径通过url
    private String getFilePathByUri(Uri mUri) throws FileNotFoundException {
        Cursor cursor = getContentResolver()
                .query(mUri, null, null, null, null);
        cursor.moveToFirst();
        return cursor.getString(1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /**
             * 添加图片进行剪切
             */
            case R.id.iv_activity_cut_picture:
                showSelectPictureMenu();
                break;
            /**
             * 取消按钮
             */
            case R.id.tv_activity_remove:
                setRemoveActivity();
                break;
            /**
             * icon点击选择按钮
             */
            case R.id.tv_choose:

                iconintent = new Intent(ActivityPublishActivity.this, ActivityIconActivity.class);
                bundle = new Bundle();
                //传set集合需要序列化,要注意转化(Serializable)
                bundle.putSerializable("icon", (Serializable) setiocn);
                iconintent.putExtras(bundle);
                startActivityForResult(iconintent, ACTIVITY_ICON);
                break;
            case R.id.tv_comment_revise:

                writeintent = new Intent(ActivityPublishActivity.this, WriteCommentActivity.class);
//                bundle = new Bundle();
//                //传set集合需要序列化,要注意转化(Serializable)
//                bundle.putSerializable("icon", (Serializable) setiocn);
//                iconintent.putExtras(bundle);
                startActivityForResult( writeintent, WRITE_COMMENT_ACTIVITY);
                break;
            /**
             * 发布按钮
             */
            case R.id.iv_activity_publish:
                if(TextUtils.isEmpty(et_activity_publish_title.getText())
                        & TextUtils.isEmpty(et_activity_publish_theme.getText())
                        & TextUtils.isEmpty(et_activity_publish_time.getText())
                        & TextUtils.isEmpty(et_activity_publish_place.getText())
                        & TextUtils.isEmpty(et_activity_publish_price.getText())

                        ){

                }else{
                    Toast.makeText(ActivityPublishActivity.this,"你点了我",Toast.LENGTH_SHORT).show();

                }
                break;

            default:
                break;
        }
    }

    /**
     * 取消编辑活动业务逻辑
     */

    public void setRemoveActivity() {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("确定取消编辑")
//                .setMessage("确定吗？")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        dialog.dismiss();

                    }
                })
                .setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        alertDialog.show();
    }
    /**
     * 输入框的设置监听,输入有字时才能显示发布按钮
     */
    public void setEdit(){
        et_activity_publish_title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(et_activity_publish_title.getText())){
                    iv_activity_publish.setImageResource(R.mipmap.mbt_publish);
                }else {
                    iv_activity_publish.setImageResource(R.mipmap.mbt_publish_pre);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_activity_publish_theme.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(et_activity_publish_theme.getText())){
                    iv_activity_publish.setImageResource(R.mipmap.mbt_publish);
                }else {
                    iv_activity_publish.setImageResource(R.mipmap.mbt_publish_pre);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_activity_publish_time.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(et_activity_publish_time.getText())){
                    iv_activity_publish.setImageResource(R.mipmap.mbt_publish);
                }else {
                    iv_activity_publish.setImageResource(R.mipmap.mbt_publish_pre);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_activity_publish_place.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(et_activity_publish_place.getText())){
                    iv_activity_publish.setImageResource(R.mipmap.mbt_publish);
                }else {
                    iv_activity_publish.setImageResource(R.mipmap.mbt_publish_pre);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_activity_publish_price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(et_activity_publish_price.getText())){
                    iv_activity_publish.setImageResource(R.mipmap.mbt_publish);
                }else {
                    iv_activity_publish.setImageResource(R.mipmap.mbt_publish_pre);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


}
