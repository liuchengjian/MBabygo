package liu.chj.mbabygo.rotate;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.allure.lbanners.LMBanners;
import com.allure.lbanners.adapter.LBaseAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.activity.RotateImageActivity;

/**
 * Created by luomin on 16/7/12.
 * 网络获取图片
 */
public class UrlImgAdapter implements LBaseAdapter<String> {
    private Context mContext;

    public UrlImgAdapter(Context context) {
        mContext=context;
    }



    @Override
    public View getView(final LMBanners lBanners, final Context context, int position, String data) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.id_image);
        ImageLoader.getInstance().displayImage(data,imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                        MainActivity.this.startActivity(new Intent(MainActivity.this,SeconedAc.class));
                Log.e("lchj","你点了我！！！！！！！！！");
                Intent intent = new Intent(context, RotateImageActivity.class);
                context.startActivity(intent);
            }
        });
        return view;
    }



}
