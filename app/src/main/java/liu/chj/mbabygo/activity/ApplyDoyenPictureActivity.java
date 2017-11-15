package liu.chj.mbabygo.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.autolayout.AutoLayoutActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.io.FileNotFoundException;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.cutpicture.CutPictureActivityy;
import liu.chj.mbabygo.cutpicture.SelectDialog;

/**
 * 作者：柳成建
 * 日期：2016/12/30 - 10:13
 * 注释：申请达人添加图片界面
 */
public class ApplyDoyenPictureActivity extends AutoLayoutActivity implements View.OnClickListener{
    private final int CAMERA_WITH_DATA = 1;
    /**
     * 本地图片选取标志
     */
    private static final int FLAG_CHOOSE_IMG = 2;
    /**
     * 截取结束标志
     */
    private static final int FLAG_MODIFY_FINISH = 3;
    public static final String TMP_PATH = "temp.jpg";
    private Context context;
    @ViewInject(R.id.iv_apply_doyen_picture_back)
    private ImageView iv_apply_doyen_picture_back;
    @ViewInject(R.id.apply_doyen_add_picture)
    private ImageView apply_doyen_add_picture;
    @ViewInject(R.id.tv_apply_doyen_picture_sure)
    private TextView tv_apply_doyen_picture_sure;
    private Intent intent;
    private Bitmap b;
    private String path;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_doyen_picture);
        intent = getIntent();
        x.view().inject(this);
        iv_apply_doyen_picture_back.setOnClickListener(this);
        apply_doyen_add_picture.setOnClickListener(this);
        tv_apply_doyen_picture_sure.setOnClickListener(this);
        context = ApplyDoyenPictureActivity.this;

    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.iv_apply_doyen_picture_back:
                finish();
                break;
            case R.id.apply_doyen_add_picture:
                showSelectPictureMenu();
                break;
            case R.id.tv_apply_doyen_picture_sure:
                intent.putExtra("picture", path);
                Log.e("lchj","ddddd"+path);
                setResult(RESULT_OK, intent); //intent为A传来的带有Bundle的intent，当然也可以自己定义新的Bundle
                finish();
                break;
            default:
                break;
        }

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

                    Intent intent = new Intent(this, CutPictureActivityy.class);
                    intent.putExtra("path", path);
                    startActivityForResult(intent, FLAG_MODIFY_FINISH);
                } else {
                    Intent intent = new Intent(this, CutPictureActivityy.class);
                    intent.putExtra("path", uri.getPath());
                    startActivityForResult(intent, FLAG_MODIFY_FINISH);
                }
            }
        } else if (requestCode == FLAG_MODIFY_FINISH && resultCode == RESULT_OK) {
                if (data != null) {
                    path = data.getStringExtra("path");
                    Bitmap b = BitmapFactory.decodeFile(path);
//                    Log.e("lchj","sdddddd"+path);
                    apply_doyen_add_picture.setImageBitmap(b);
                }
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
        Intent intent = new Intent(this, CutPictureActivityy.class);
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

}
