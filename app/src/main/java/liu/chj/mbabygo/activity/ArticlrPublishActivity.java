package liu.chj.mbabygo.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.autolayout.AutoLayoutActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.io.FileNotFoundException;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.cutpicture.CutPictureAty;
import liu.chj.mbabygo.cutpicture.SelectDialog;

/**
 * 作者：柳成建
 * 日期：2016/12/19 - 11:05
 * 注释：
 */
public class ArticlrPublishActivity extends AutoLayoutActivity implements View.OnClickListener{
    private final int CAMERA_WITH_DATA = 1;
    /** 本地图片选取标志 */
    private static final int FLAG_CHOOSE_IMG = 2;
    /** 截取结束标志 */
    private static final int FLAG_MODIFY_FINISH = 3;
    public static final String TMP_PATH = "temp.jpg";
    private Context context;
    private ImageView img_pic;
    //文本样式Aa
    @ViewInject(R.id.iv_article_publish_Aa)
    private ImageView iv_article_publish_Aa;
    //文本样式Aa
    @ViewInject(R.id.et_popup_title)
    private EditText et_article_publish_Aa;

    /**
     *文本样式弹框
     */
    private PopupWindow popAa = null;
    private RelativeLayout parent_aa;
    private LinearLayout ll_popup_Aa;
    /**
     *文本样式选择项
     */
    private int Aaicon=0;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_publish);
        Intent intent = getIntent();
        x.view().inject(this);
        img_pic = (ImageView) findViewById(R.id.img_pic);
        img_pic.setOnClickListener(this);
        context = ArticlrPublishActivity.this;
        iv_article_publish_Aa.setOnClickListener(this);
    }
    /** 弹出选择照片菜单 */
    public void showSelectPictureMenu() {
        new SelectDialog(context)
                .builder()
                .setCancelable(true)
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
                            new String[] { MediaStore.Images.Media.DATA },
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
                final String path = data.getStringExtra("path");
                Bitmap b = BitmapFactory.decodeFile(path);
                img_pic.setImageBitmap(b);
            }
        }
        switch (requestCode) {
            case CAMERA_WITH_DATA:
                // 照相机程序返回的,再次调用图片剪辑程序去修剪图片
                startCropImageActivity(Environment.getExternalStorageDirectory()
                        + "/" + TMP_PATH);
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
            case R.id.img_pic:
                showSelectPictureMenu();
                break;
            /**
             * 文本样式
             */
            case R.id.iv_article_publish_Aa:
                //弹出弹框
                popupTextAa();
                //关闭输入法
                setclosetypewriting();
                ll_popup_Aa.startAnimation(AnimationUtils.loadAnimation(
                        ArticlrPublishActivity.this, R.anim.activity_translate_in));
                popAa.showAtLocation(iv_article_publish_Aa, Gravity.BOTTOM, 0, 0);
                backgroundAlpha(0.5f);
                break;

            default:
                break;
        }
    }

    /**
     * 文本样式弹框
     */
    public void popupTextAa(){
        popAa = new PopupWindow(ArticlrPublishActivity.this);

        View view = getLayoutInflater().inflate(R.layout.popup_text_aa,
                null);

        ll_popup_Aa = (LinearLayout) view.findViewById(R.id.ll_popup_Aa);

        popAa.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        popAa.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        popAa.setBackgroundDrawable(new BitmapDrawable());
        popAa.setFocusable(true);
        popAa.setOutsideTouchable(true);
        popAa.setContentView(view);

        parent_aa = (RelativeLayout) view.findViewById(R.id.parent_aa);
        RelativeLayout rl_title =(RelativeLayout) view.findViewById(R.id.rl_popup_tltle);
        /**
         * 文本样式大标题
         */
        RelativeLayout rl_popup_Aa_big_title =(RelativeLayout) view.findViewById(R.id.rl_popup_Aa_big_title);
        final ImageView iv_popup_Aa_big_title =(ImageView) view.findViewById(R.id.iv_popup_Aa_big_title);
        /**
         * 文本样式小标题
         */
        RelativeLayout rl_popup_Aa_subtitle =(RelativeLayout) view.findViewById(R.id.rl_popup_Aa_subtitle);
        final ImageView iv_popup_Aa_subtitle =(ImageView) view.findViewById(R.id.iv_popup_Aa_subtitle);
        /**
         * 文本样式正文
         */
        RelativeLayout rl_popup_Aa_text =(RelativeLayout) view.findViewById(R.id.rl_popup_Aa_text);
        /**
         * 文本样式d的完成按钮
         */
        TextView tv_popup_Aa_complete = (TextView)view.findViewById(R.id.tv_popup_Aa_complete);
        final ImageView iv_popup_Aa_text =(ImageView) view.findViewById(R.id.iv_popup_Aa_text);
        rl_title.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

            }
        });
        popAa.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                popAa.dismiss();
                ll_popup_Aa.clearAnimation();
                backgroundAlpha(1f);
            }
        });
        parent_aa.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                popAa.dismiss();
                ll_popup_Aa.clearAnimation();
                backgroundAlpha(1f);
            }
        });
        rl_popup_Aa_big_title.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                iv_popup_Aa_big_title.setVisibility(View.VISIBLE);
                iv_popup_Aa_subtitle.setVisibility(View.GONE);
                iv_popup_Aa_text.setVisibility(View.GONE);
                Aaicon = 1;

            }
        });
        rl_popup_Aa_subtitle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                iv_popup_Aa_big_title.setVisibility(View.GONE);
                iv_popup_Aa_subtitle.setVisibility(View.VISIBLE);
                iv_popup_Aa_text.setVisibility(View.GONE);
                Aaicon = 2;
            }
        });
        rl_popup_Aa_text.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                iv_popup_Aa_big_title.setVisibility(View.GONE);
                iv_popup_Aa_subtitle.setVisibility(View.GONE);
                iv_popup_Aa_text.setVisibility(View.VISIBLE);
                Aaicon = 3;
            }
        });
        tv_popup_Aa_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Aaicon ==1){
                    AaBigTitle();
                }else if(Aaicon ==2){
                    //样式加粗
                    AaSubTitle();
                }else if(Aaicon ==3){
                    //样式默认
                    AaTextTitle();
                }else {
                    et_article_publish_Aa.setTypeface(Typeface.DEFAULT_BOLD);
                    et_article_publish_Aa.setTextSize(24);
                }
                popAa.dismiss();
                ll_popup_Aa.clearAnimation();
                backgroundAlpha(1f);
            }
        });
    }

    /**
     * 文本样式的大标题属性
     */
    public void AaBigTitle(){
        //加粗
        et_article_publish_Aa.setTypeface(Typeface.DEFAULT_BOLD);
        et_article_publish_Aa.setTextSize( getResources().getDimensionPixelSize(R.dimen.AaBigTitle));
    }
    /**
     * 文本样式的小标题属性
     */
    public void AaTextTitle(){
        //加粗
        et_article_publish_Aa.setTypeface(Typeface.DEFAULT_BOLD);
        et_article_publish_Aa.setTextSize( getResources().getDimensionPixelSize(R.dimen.AaTextTitle));
    }
    /**
     * 文本样式的正文属性
     */
    public void AaSubTitle(){
        //加粗
        et_article_publish_Aa.setTypeface(Typeface.DEFAULT);
        et_article_publish_Aa.setTextSize( getResources().getDimensionPixelSize(R.dimen.AaSubTitle));
    }
    // 设置屏幕透明度
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; // 0.0~1.0
        getWindow().setAttributes(lp);
    }
    /**
     * 关闭输入法
     */
    public void setclosetypewriting(){
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            InputMethodManager im = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(getCurrentFocus()
                            .getApplicationWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);

        }
    }
}
