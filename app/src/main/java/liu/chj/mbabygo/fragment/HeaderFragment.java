package liu.chj.mbabygo.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.allure.lbanners.LMBanners;
import com.allure.lbanners.transformer.TransitionEffect;
import com.allure.lbanners.utils.ScreenUtils;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.L;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.activity.RecommendActivityActivity;
import liu.chj.mbabygo.activity.RecommendArticleActivity;
import liu.chj.mbabygo.adapter.HeaderRecommendActicleAdapter;
import liu.chj.mbabygo.adapter.HeaderRecommendActivityAdapter;
import liu.chj.mbabygo.app.StatusBarUtils;
import liu.chj.mbabygo.entity.Activity;
import liu.chj.mbabygo.entity.Article;
import liu.chj.mbabygo.polltorefresh.MyListener;
import liu.chj.mbabygo.polltorefresh.PullToRefreshLayout;
import liu.chj.mbabygo.polltorefresh.PullableScrollView;
import liu.chj.mbabygo.rotate.LocalImgAdapter;
import liu.chj.mbabygo.rotate.ParallaxTransformer;
import liu.chj.mbabygo.rotate.UrlImgAdapter;

/**
 * 作者：柳成建
 * 日期：2016/11/3 - 14:56
 * 注释：
 */
public class HeaderFragment extends Fragment {
    private PullToRefreshLayout pullToRefreshLayout;
    private PullableScrollView pullableScrollView;
    private LayoutInflater mInflater;
    @ViewInject(R.id.banners)
    private LMBanners mLBanners;
    private Context vie;
    //本地图片
    private ArrayList<Integer> localImages = new ArrayList<Integer>();
    //网络图片
    private List<String> networkImages = new ArrayList<>();
    @ViewInject(R.id.header_recommend_activity_listView)
    private ListView mActivitylistView;
    @ViewInject(R.id.header_recommend_article_listView)
    private ListView mArticlelistView;
    private HeaderRecommendActicleAdapter mArticleAdapter;
    private List<Article> mArticlelist = new ArrayList<>();
    private HeaderRecommendActivityAdapter mActivtyAdapter;
    private List<Activity> mActivitylist = new ArrayList<>();

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

    private float  mDownX;
    private float   mDownY;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_header, null);
//      ListView listView = (ListView) getActivity().findViewById(R.id.listView);
        x.view().inject(this, view);
        pullToRefreshLayout = ((PullToRefreshLayout) view.findViewById(R.id.refresh_header));
        pullableScrollView = ((PullableScrollView) view.findViewById(R.id.Psv));


        pullToRefreshLayout.setOnRefreshListener(new MyListener());
        //取消焦點，防止自定義listview顯示界面頭
//        mActivitylistView.setFocusable(false);
        //设置推荐文章的adpater的业务逻辑
        setArticle();
        //设置推荐活动的adpater的业务逻辑
        setActivity();
        /**
         * 设置推荐文章数据
         */
        setArticleData();
        /**
         * 设置推荐活动数据
         */
        setActivityData();
        setbanners();
        return view;
    }

    /**
     * 轮播图
     */
    private void setbanners() {

        initImageLoader();

        mInflater = getActivity().getLayoutInflater();

        addLocalImg();
        addUrilImg();

//        listView.setAdapter(new ArrayAdapter<String>(getActivity(),
//                android.R.layout.simple_list_item_1, strs));

//        mLBanners = (LMBanners) getActivity().findViewById(R.id.banners);
        //设置Banners高度
        mLBanners.setLayoutParams(new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.MATCH_PARENT, ScreenUtils.dip2px(getActivity(), 225)));
        //本地用法
//        mLBanners.setAdapter(new LocalImgAdapter(getActivity()),localImages);
        //网络图片
        mLBanners.setAdapter(new UrlImgAdapter(getActivity()), networkImages);
        //参数设置
        mLBanners.setAutoPlay(true);//自动播放
        mLBanners.setVertical(false);//是否可以垂直
        mLBanners.setScrollDurtion(222);//两页切换时间
        mLBanners.setCanLoop(true);//循环播放
        mLBanners.setSelectIndicatorRes(R.mipmap.header_banner_pre);//选中的原点
        mLBanners.setUnSelectUnIndicatorRes(R.mipmap.header_banner);//未选中的原点
        mLBanners.setIndicatorWidth(5);//默认为5dp
//        mLBanners.setHoriZontalTransitionEffect(TransitionEffect.Default);//选中喜欢的样式
        mLBanners.setHoriZontalCustomTransformer(new ParallaxTransformer(R.id.id_image));//自定义样式
        mLBanners.setDurtion(4000);//切换时间
        mLBanners.hideIndicatorLayout();//隐藏原点
        mLBanners.showIndicatorLayout();//显示原点
        //设置原点显示位置
        mLBanners.setIndicatorPosition(LMBanners.IndicaTorPosition.BOTTOM_MID);
        mLBanners.setHoriZontalTransitionEffect(TransitionEffect.Accordion);//Accordion

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                switch (position){
//                    case 0:
//                        mLBanners.setHoriZontalTransitionEffect(TransitionEffect.Default);//Default
//                        break;
//                    case 1:
//                        mLBanners.setHoriZontalTransitionEffect(TransitionEffect.Alpha);//Alpha
//                        break;
//                    case 2:
//                        mLBanners.setHoriZontalTransitionEffect(TransitionEffect.Rotate);//Rotate
//                        break;
//                    case 3:
//                        mLBanners.setHoriZontalTransitionEffect(TransitionEffect.Cube);//Cube
//                        break;
//                    case 4:
//                        mLBanners.setHoriZontalTransitionEffect(TransitionEffect.Flip);//Flip
//                        break;
//                    case 5:
//                        mLBanners.setHoriZontalTransitionEffect(TransitionEffect.Accordion);//Accordion
//                        break;
//                    case 6:
//                        mLBanners.setHoriZontalTransitionEffect(TransitionEffect.ZoomFade);//ZoomFade
//                        break;
//                    case 7:
//                        mLBanners.setHoriZontalTransitionEffect(TransitionEffect.Fade);//Fade
//                        break;
//                    case 8:
//                        mLBanners.setHoriZontalTransitionEffect(TransitionEffect.ZoomCenter);//ZoomCenter
//                        break;
//                    case 9:
//                        mLBanners.setHoriZontalTransitionEffect(TransitionEffect.ZoomStack);//ZoomStack
//                        break;
//                    case 10:
//                        mLBanners.setHoriZontalTransitionEffect(TransitionEffect.Stack);//Stack
//                        break;
//                    case 11:
//                        mLBanners.setHoriZontalTransitionEffect(TransitionEffect.Depth);//Depth
//                        break;
//                    case 12:
//                        mLBanners.setHoriZontalTransitionEffect(TransitionEffect.Zoom);//Zoom
//                        break;
//                    case 13:
//                        mLBanners.setHoriZontalTransitionEffect(TransitionEffect.ZoomOut);//ZoomOut
//                        break;
//                    case 14:
//                        mLBanners.setHoriZontalCustomTransformer(new ParallaxTransformer(R.id.id_image));//Parallax
//                        break;
//
//                }
//            }
//        });
    }

    /**
     * 设置推荐文章数据
     */
    private void setArticleData() {
        for (int i = 0; i < 3; i++) {
            mArticlelist.add(new Article("R.mipmap.yuanyuanma1", "和妈妈一起去旅游", "圆子妈妈", "2016-11-16", "R.mipmap.yuanyuanma2"));
        }
    }

    /**
     * 设推荐活动数据
     */
    private void setActivityData() {
        for (int i = 0; i < 3; i++) {
            mActivitylist.add(new Activity("【h和妈妈一起旅行】", "h和妈妈一起去成都玩",
                    "6 +", "手工课", "趣味活动", "文化传媒", "2017-10-10",
                    "500人以参加", R.mipmap.xianshi2, R.mipmap.yuanyuanma1));
        }
    }

    /**
     * 设置推荐文章的adpater的业务逻辑
     */
    private void setArticle() {
        mArticleAdapter = new HeaderRecommendActicleAdapter(getActivity(), mArticlelist);
        if (mArticlelistView.getAdapter() == null) {
            mArticlelistView.setAdapter(mArticleAdapter);
        } else {
            mArticleAdapter.notifyDataSetChanged();
        }

        mArticlelistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), RecommendArticleActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 设置推荐活动的adpater的业务逻辑
     */
    private void setActivity() {
        mActivtyAdapter = new HeaderRecommendActivityAdapter(getActivity(), mActivitylist);
        if (mActivitylistView.getAdapter() == null) {
            mActivitylistView.setAdapter(mActivtyAdapter);
        } else {
            mActivtyAdapter.notifyDataSetChanged();
        }

        mActivitylistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), RecommendActivityActivity.class);
                startActivity(intent);
            }
        });
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
}
