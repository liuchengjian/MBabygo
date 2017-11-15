package liu.chj.mbabygo.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.allure.lbanners.LMBanners;
import com.allure.lbanners.transformer.TransitionEffect;
import com.allure.lbanners.utils.ScreenUtils;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.adapter.HeaderRecommendActicleAdapter;
import liu.chj.mbabygo.adapter.HeaderRecommendActivityAdapter;
import liu.chj.mbabygo.adapter.TopicListAdapter;
import liu.chj.mbabygo.app.StatusBarUtils;
import liu.chj.mbabygo.entity.Activity;
import liu.chj.mbabygo.entity.Article;
import liu.chj.mbabygo.entity.ChatPic;
import liu.chj.mbabygo.entity.ItemActivityBean;
import liu.chj.mbabygo.entity.ItemchatBean;
import liu.chj.mbabygo.entity.TopicBaseItem;
import liu.chj.mbabygo.rotate.LocalImgAdapter;
import liu.chj.mbabygo.rotate.ParallaxTransformer;
import liu.chj.mbabygo.rotate.UrlImgAdapter;

/**
 * 作者：柳成建
 * 日期：2016/11/3 - 14:56
 * 注释：
 */
public class TopicFragment extends Fragment {
    private LayoutInflater mInflater;
    @ViewInject(R.id.topic_banners)
    private LMBanners mLBanners;
    //本地图片
    private ArrayList<Integer> localImages = new ArrayList<Integer>();
    //网络图片
    private List<String> networkImages = new ArrayList<>();
    @ViewInject(R.id.topic_listView)
    private ListView topic_listView;
    @ViewInject(R.id.lv)
    private ListView lv;
    private List<TopicBaseItem>listTopic = new ArrayList<>();
    private TopicListAdapter maAdapter;
    private ArrayList<ChatPic>listchatPic = new ArrayList<>();

    private int[] mImagesSrc = {
            R.mipmap.img1,
            R.mipmap.img2,
            R.mipmap.img3,
            R.mipmap.img4,
            R.mipmap.img5
    };
    private static final String[] strs = new String[]{
            "defaultEffect", "alpha", "rotate", "cube", "flip", "accordion", "zoomFade",
            "fade", "zoomCenter", "zoomStack", "stack", "depth", "zoom", "zoomOut", "parallax"
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_topic,null);
        x.view().inject(this,view);
        setbanners();
        init();
        return view;
    }
    /**
     * 轮播图
     */
    private void setbanners(){

        initImageLoader();

        mInflater = getActivity().getLayoutInflater();

        addLocalImg();
        addUrilImg();

//        listView.setAdapter(new ArrayAdapter<String>(getActivity(),
//                android.R.layout.simple_list_item_1, strs));

//        mLBanners = (LMBanners) getActivity().findViewById(R.id.banners);
        //设置Banners高度
        mLBanners.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ScreenUtils.dip2px(getActivity(), 200)));
        //本地用法
        mLBanners.setAdapter(new LocalImgAdapter(getActivity()),localImages);
        //网络图片
//        mLBanners.setAdapter(new UrlImgAdapter(getActivity()), networkImages);
        //参数设置
        mLBanners.setAutoPlay(true);//自动播放
        mLBanners.setVertical(false);//是否可以垂直
        mLBanners.setScrollDurtion(222);//两页切换时间
        mLBanners.setCanLoop(true);//循环播放
        mLBanners.setSelectIndicatorRes(R.drawable.page_indicator_select);//选中的原点
        mLBanners.setUnSelectUnIndicatorRes(R.drawable.page_indicator_unselect);//未选中的原点
//        mLBanners.setIndicatorWidth(5);//默认为5dp
//        mLBanners.setHoriZontalTransitionEffect(TransitionEffect.Default);//选中喜欢的样式
        mLBanners.setHoriZontalCustomTransformer(new ParallaxTransformer(R.id.id_image));//自定义样式
        mLBanners.setDurtion(4000);//切换时间
        mLBanners.hideIndicatorLayout();//隐藏原点
        mLBanners.showIndicatorLayout();//显示原点
        //设置原点显示位置
        mLBanners.setIndicatorPosition(LMBanners.IndicaTorPosition.BOTTOM_MID);
        mLBanners.setHoriZontalTransitionEffect(TransitionEffect.Default);//Accordion

    }
    private void addUrilImg() {

        networkImages.add("http://h.hiphotos.baidu.com/image/h%3D300/sign=ff62800b073b5bb5a1d726fe06d2d523/a6efce1b9d16fdfa7807474eb08f8c5494ee7b23.jpg");
        networkImages.add("http://g.hiphotos.baidu.com/image/h%3D300/sign=0a9ac84f89b1cb1321693a13ed5556da/1ad5ad6eddc451dabff9af4bb2fd5266d0163206.jpg");
        networkImages.add("http://a.hiphotos.baidu.com/image/h%3D300/sign=61660ec2207f9e2f6f351b082f31e962/500fd9f9d72a6059e5c05d3e2f34349b023bbac6.jpg");
        networkImages.add("http://c.hiphotos.baidu.com/image/h%3D300/sign=f840688728738bd4db21b431918a876c/f7246b600c338744c90c3826570fd9f9d62aa09a.jpg");

    }
    private void initImageLoader() {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().
                showImageForEmptyUri(R.mipmap.ic_launcher)
                .cacheInMemory(true).cacheOnDisk(true).build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getActivity().getApplicationContext()).defaultDisplayImageOptions(defaultOptions)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }
    private void addLocalImg() {
        localImages.add(R.mipmap.yuanyuanma1);
        localImages.add(R.mipmap.yuanyuanma2);
        localImages.add(R.mipmap.yuanyuanma2);
        localImages.add(R.mipmap.yuanyuanma1);
        localImages.add(R.mipmap.img5);
    }

    //停止事件,节省资源
    @Override
    public void onPause() {
        super.onPause();
        mLBanners.stopImageTimerTask();
    }

    @Override
    public void onResume() {
        super.onResume();
        mLBanners.startImageTimerTask();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLBanners.clearImageTimerTask();
    }
    public void init(){
        for(int i=0;i<3;i++){
            listTopic.add(new ItemActivityBean
                    (TopicListAdapter.ViewActivityHolder.VIEW_ACTIVITY_TYPE
                            ,R.mipmap.ic_launcher,R.mipmap.ic_launcher,
                            "八戒","师傅被妖怪抓走了","我们一起旅行","10","50","100+"));
        }
        for(int i=0;i<3;i++){
            listTopic.add(new ItemActivityBean
                    (TopicListAdapter.ViewArticleHolder.VIEW_ARTICLE_TYPE
                            ,R.mipmap.ic_launcher,R.mipmap.ic_launcher,
                            "悟空","师傅被妖怪抓走了","说走就走的旅行","1000","500","99+"));
        }
        maAdapter = new TopicListAdapter(getActivity(),listTopic);
        topic_listView.setAdapter(maAdapter);
//        lv.setAdapter(maAdapter);

    }
}
