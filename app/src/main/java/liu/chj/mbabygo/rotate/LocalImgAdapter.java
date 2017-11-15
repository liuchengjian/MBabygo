package liu.chj.mbabygo.rotate;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.allure.lbanners.LMBanners;
import com.allure.lbanners.adapter.LBaseAdapter;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.activity.RotateImageActivity;


/**
 * 本地的照片
 */
public class LocalImgAdapter implements LBaseAdapter<Integer> {
    private Context mContext;

    public LocalImgAdapter(Context context) {
        mContext=context;
    }

    @Override
    public View getView(final LMBanners lBanners, final Context context, int position, Integer data) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.id_image);
        imageView.setImageResource(data);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("lchj","你点了我！！！！！！！！！");
                Intent intent = new Intent(context, RotateImageActivity.class);
                context.startActivity(intent);
            }
        });
        return view;
    }


}
