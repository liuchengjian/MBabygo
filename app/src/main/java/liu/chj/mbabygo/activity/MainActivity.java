package liu.chj.mbabygo.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;
import com.oguzdev.circularfloatingactionmenu.library.animation.DefaultAnimationHandler;
import com.zhy.autolayout.AutoLayoutActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.app.StatusBarUtils;
import liu.chj.mbabygo.fragment.DoyenFragment;
import liu.chj.mbabygo.fragment.GDMapFragment;
import liu.chj.mbabygo.fragment.HeaderFragment;
import liu.chj.mbabygo.fragment.MyFragment;
import liu.chj.mbabygo.fragment.TopicFragment;
import liu.chj.mbabygo.ui.SuperCustomToast;

public class MainActivity extends AutoLayoutActivity implements View.OnClickListener {
    @ViewInject(R.id.rb_tab_menu_header)
    private RadioButton mRB_tab_header;
    @ViewInject(R.id.rb_tab_menu_topic)
    private RadioButton mRB_tab_topic;
    @ViewInject(R.id.rb_tab_menu_doyenO)
    private RadioButton mRB_tab_doyenO;
    @ViewInject(R.id.rb_tab_menu_my)
    private RadioButton mRB_tab_my;
    //    @ViewInject(R.id.toolbar_main)
//    private Toolbar mtoolbar;
    @ViewInject(R.id.iv_tab_map_Photo)
    private ImageView iv_tab_map_Photo;
    /**
     * Fragment碎片列表
     */
    private ArrayList<Fragment> fragments;
    /**
     * 4个Fragment碎片界面
     */
    private HeaderFragment Hf;
    private DoyenFragment Df;
    //    private DoyenOFragment Dof;
//    private DoyenOOFragment Doof;
    private TopicFragment Tf;
    private MyFragment myf;
    private GDMapFragment gdmapf;
//    private MyTwoFragment mTyf;
    /**
     * FragmentTransaction fragment碎片管理器
     */
    private FragmentTransaction ft;
    @ViewInject(R.id.fl_mian)
    private FrameLayout fl_mian = null;
    FragmentManager fm;
//    /**
//     * 发布帖子按钮
//     */
//    @ViewInject(R.id.iv_tab_public_Photo)
//    private ImageView iv_tab_public_Photo;
    /**
     * 快速发布
     */
    private ImageView ivQuickpublish;
    /**
     * 文章发布
     */
    private ImageView ivArticlrpublish;
    /**
     * 活动发布
     */
    private ImageView ivActivitypublish;
    @ViewInject(R.id.view_main_backgound)
    private TextView view_main_backgound;
    @ViewInject(R.id.view_main_backgound_two)
    private TextView view_main_backgound_two;
    FloatingActionMenu leftCenterMenu;
    FloatingActionMenu doyenleftCenterMenu;
    public FloatingActionButton leftCenterButton;
    public FloatingActionButton DoyenleftCenterButton;
    private PopupWindow mPopupWindow;

    private String tag;
    private long clickTime=0;

    SuperCustomToast toast;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            Hf = (HeaderFragment) fm.findFragmentByTag("Hf");
            Df = (DoyenFragment) fm.findFragmentByTag("Df");
            gdmapf = (GDMapFragment) fm.findFragmentByTag("gdmapf");
            Tf = (TopicFragment) fm.findFragmentByTag("Tf");
            myf = (MyFragment) fm.findFragmentByTag("myf");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toast = SuperCustomToast.getInstance(getApplicationContext());
        x.view().inject(this);
        listener();
        select(0);


        /**
         * 消息红点初始化
         */
//        SuperBadgeHelper superBadgeHelper = SuperBadgeHelper.init(this,mRB_tab_my, Tag.MyTag);
//        superBadgeHelper.setBadgeGravity(Gravity.CENTER);
    }

    private void listener() {
        mRB_tab_header.setOnClickListener(this);
        mRB_tab_doyenO.setOnClickListener(this);
        mRB_tab_my.setOnClickListener(this);
        mRB_tab_topic.setOnClickListener(this);
        iv_tab_map_Photo.setOnClickListener(this);
//        iv_tab_public_Photo.setOnClickListener(this);
    }

    private void select(int i) {
        fm = getSupportFragmentManager(); // 获得Fragment管理器
        // FragmentManager fm = getChildFragmentManager(); // 获得Fragment管理器

        FragmentTransaction ft = fm.beginTransaction(); // 开启一个事务

        hidtFragment(ft); // 先隐藏 Fragment

        switch (i) {
            case 0:
                if (Hf == null) {
                    Hf = new HeaderFragment();
                    ft.add(R.id.fl_mian, Hf);

                } else {
                    ft.show(Hf);
                }
                break;
            case 1:
                if (Df == null) {
                    Df = new DoyenFragment();
                    ft.add(R.id.fl_mian, Df);
                } else {
                    ft.show(Df);
                }
                break;
            case 2:
                if (gdmapf == null) {
                    gdmapf = new GDMapFragment();
                    ft.add(R.id.fl_mian, gdmapf);
                } else {
                    ft.show(gdmapf);
                }

                break;
            case 3:
                if (Tf == null) {
                    Tf = new TopicFragment();
                    ft.add(R.id.fl_mian, Tf);
                } else {
                    ft.show(Tf);
                    //
                }
                break;
            case 4:
                if (myf == null) {
                    myf = new MyFragment();
                    ft.add(R.id.fl_mian, myf);
                } else {
                    ft.show(myf);

                }
                break;
        }
        ft.commit(); // 提交事务
    }

    // 隐藏所有Fragment
    private void hidtFragment(FragmentTransaction fragmentTransaction) {
        if (Hf != null) {
            fragmentTransaction.hide(Hf);
        }
        if (Df != null) {
            fragmentTransaction.hide(Df);
        }
        if (Tf != null) {
            fragmentTransaction.hide(Tf);
        }
        if (myf != null) {
            fragmentTransaction.hide(myf);
//            fragmentTransaction.hide(mTyf);
        }
        if (gdmapf != null) {
            fragmentTransaction.hide(gdmapf);
//            fragmentTransaction.hide(mapf);
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.rb_tab_menu_header:
                mRB_tab_header.setChecked(true);
                mRB_tab_doyenO.setChecked(false);
                mRB_tab_topic.setChecked(false);
                mRB_tab_my.setChecked(false);
//                iv_tab_public_Photo.setVisibility(View.GONE);
                select(0);
                if (DoyenleftCenterButton != null) {
                    DoyenleftCenterButton.setVisibility(View.GONE);
                    doyenleftCenterMenu.close(false);
                }
                if (leftCenterButton != null) {

                    leftCenterButton.setVisibility(View.GONE);
                    leftCenterMenu.close(false);
                }

                break;

            case R.id.rb_tab_menu_doyenO:

                mRB_tab_doyenO.setChecked(true);
                mRB_tab_header.setChecked(false);
                mRB_tab_topic.setChecked(false);
                mRB_tab_my.setChecked(false);
//                iv_tab_public_Photo.setVisibility(View.GONE);
                select(1);
                /**
                 *  隐藏社区悬浮按钮
                 */
                if (leftCenterButton != null) {
                    leftCenterButton.setVisibility(View.GONE);
                    leftCenterMenu.close(false);
                }
                /**
                 * 达人圈的悬浮按钮
                 */
                if (DoyenleftCenterButton == null) {
                    setDoyenSuspendButton();

                } else {
                    DoyenleftCenterButton.setVisibility(View.VISIBLE);
                    doyenleftCenterMenu.close(true);
                }

                break;
            case R.id.iv_tab_map_Photo:
                select(2);
                mRB_tab_my.setChecked(false);
                mRB_tab_topic.setChecked(false);
                mRB_tab_doyenO.setChecked(false);
                mRB_tab_header.setChecked(false);
//                iv_tab_public_Photo.setVisibility(View.VISIBLE);
                if (DoyenleftCenterButton != null) {
                    DoyenleftCenterButton.setVisibility(View.GONE);
                    doyenleftCenterMenu.close(false);
                }
                if (leftCenterButton != null) {

                    leftCenterButton.setVisibility(View.GONE);
                    leftCenterMenu.close(false);
                }
                break;

            case R.id.rb_tab_menu_topic:
                select(3);
                mRB_tab_topic.setChecked(true);
                mRB_tab_doyenO.setChecked(false);
                mRB_tab_header.setChecked(false);
                mRB_tab_my.setChecked(false);
//                iv_tab_public_Photo.setVisibility(View.GONE);
                if (DoyenleftCenterButton != null) {
                    DoyenleftCenterButton.setVisibility(View.GONE);
                    doyenleftCenterMenu.close(false);
                }
                /**
                 * 社区的悬浮按钮
                 */
                if (leftCenterButton == null) {
                    setTopicSuspendButton();

                } else {
                    leftCenterButton.setVisibility(View.VISIBLE);
                    leftCenterMenu.close(true);
                }
                break;
            case R.id.rb_tab_menu_my:
                select(4);
                mRB_tab_my.setChecked(true);
                mRB_tab_topic.setChecked(false);
                mRB_tab_doyenO.setChecked(false);
                mRB_tab_header.setChecked(false);
                if (DoyenleftCenterButton != null) {

                    DoyenleftCenterButton.setVisibility(View.GONE);
                    doyenleftCenterMenu.close(false);
                }
                if (leftCenterButton != null) {

                    leftCenterButton.setVisibility(View.GONE);
                    leftCenterMenu.close(false);
                }
                break;
            default:
                break;

        }

    }

    /**
     * 达人圈的悬浮按钮
     */
    public void setDoyenSuspendButton() {
        int redActionButtonSize = getResources().getDimensionPixelSize(R.dimen.publish_big_size);
        int redActionButtonMarginRight = getResources().getDimensionPixelSize(R.dimen.publish_big_margin_right);
        int redActionButtonMargin = getResources().getDimensionPixelSize(R.dimen.publish_big_margin_button);
        int redActionButtonContentSize = getResources().getDimensionPixelSize(R.dimen.publish_big_size);
        int redActionButtonContentMargin = getResources().getDimensionPixelSize(R.dimen.publish_big_margin_button);
        int redActionMenuRadius = getResources().getDimensionPixelSize(R.dimen.publish_radius);
        int blueSubActionButtonSize = getResources().getDimensionPixelSize(R.dimen.publish_small_size);
        int blueSubActionButtonContentMargin = 0;

        ImageView DoyenfabIconStar = new ImageView(MainActivity.this);

        DoyenfabIconStar.setImageDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
        DoyenfabIconStar.setVisibility(View.GONE);
        FloatingActionButton.LayoutParams starParams = new FloatingActionButton.LayoutParams(redActionButtonSize, redActionButtonSize);
        starParams.setMargins(redActionButtonMargin,
                redActionButtonMargin,
                redActionButtonMarginRight,
                redActionButtonMargin);
        DoyenfabIconStar.setLayoutParams(starParams);

        FloatingActionButton.LayoutParams fabIconStarParams = new FloatingActionButton.LayoutParams(redActionButtonContentSize, redActionButtonContentSize);
        fabIconStarParams.setMargins(redActionButtonContentMargin,
                redActionButtonContentMargin,
                redActionButtonContentMargin,
                redActionButtonContentMargin);

        DoyenleftCenterButton = new FloatingActionButton.Builder(MainActivity.this)
                .setContentView(DoyenfabIconStar, fabIconStarParams)
                .setBackgroundDrawable(R.mipmap.doyen_publish)
                .setPosition(FloatingActionButton.POSITION_BOTTOM_RIGHT)
                .setLayoutParams(starParams)
                .build();
        DoyenleftCenterButton.setMotionEventSplittingEnabled(false);
        SubActionButton.Builder lCSubBuilder = new SubActionButton.Builder(MainActivity.this);
//        lCSubBuilder.setBackgroundDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
        FrameLayout.LayoutParams blueContentParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        blueContentParams.setMargins(blueSubActionButtonContentMargin,
                blueSubActionButtonContentMargin,
                blueSubActionButtonContentMargin,
                blueSubActionButtonContentMargin);
        lCSubBuilder.setLayoutParams(blueContentParams);
        FrameLayout.LayoutParams blueParams = new FrameLayout.LayoutParams(blueSubActionButtonSize, blueSubActionButtonSize);
        lCSubBuilder.setLayoutParams(blueParams);

//        ivQuickpublish = new ImageView(MainActivity.this);
        ivArticlrpublish = new ImageView(MainActivity.this);
        ivActivitypublish = new ImageView(MainActivity.this);
//                ImageView lcIcon4 = new ImageView(MainActivity.this);
//                ImageView lcIcon5 = new ImageView(MainActivity.this);


//        ivQuickpublish.setImageDrawable(getResources().getDrawable(R.mipmap.tab_quick_publish));
        ivArticlrpublish.setImageDrawable(getResources().getDrawable(R.mipmap.tab_article_publish));
        ivActivitypublish.setImageDrawable(getResources().getDrawable(R.mipmap.tab_activity_publish));
//        lcIcon4.setImageDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
//        lcIcon5.setImageDrawable(getResources().getDrawable(R.mipmap.ic_launcher));

        // Build another menu with custom options
        doyenleftCenterMenu = new FloatingActionMenu.Builder(MainActivity.this)
//                .addSubActionView(lCSubBuilder.setContentView(ivQuickpublish, blueContentParams).build())
                .addSubActionView(lCSubBuilder.setContentView(ivArticlrpublish, blueContentParams).build())
                .addSubActionView(lCSubBuilder.setContentView(ivActivitypublish, blueContentParams).build())
//                .addSubActionView(lCSubBuilder.setContentView(lcIcon4, blueContentParams).build())
//                .addSubActionView(lCSubBuilder.setContentView(lcIcon5, blueContentParams).build())
                .setRadius(redActionMenuRadius)
                .setStartAngle(-140)
                .setEndAngle(-220)
                .attachTo(DoyenleftCenterButton)
                .setAnimationHandler(new DefaultAnimationHandler())
                .build();

        /**
         * 文章发布点击事件
         */
        ivArticlrpublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doyenleftCenterMenu.close(true);
                Intent Articlrpublishintent = new Intent(MainActivity.this, ArticlrPublishActivity.class);
                startActivity(Articlrpublishintent);
            }
        });
        /**
         * 活动发布点击事件
         */
        ivActivitypublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doyenleftCenterMenu.close(true);
                Intent Activitypublishintent = new Intent(MainActivity.this, ActivityPublishActivity.class);
                startActivity(Activitypublishintent);
            }
        });
        view_main_backgound_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doyenleftCenterMenu.close(true);
            }
        });
        doyenleftCenterMenu.setStateChangeListener(new FloatingActionMenu.MenuStateChangeListener() {
            @Override
            public void onMenuOpened(FloatingActionMenu floatingActionMenu) {
                Log.e("lchj", "22222222");
                /**
                 * 打开小图标显示背景
                 */
                view_main_backgound_two.setVisibility(View.VISIBLE);

            }

            @Override
            public void onMenuClosed(FloatingActionMenu floatingActionMenu) {
                Log.e("lchj", "333333333333333");
                /**
                 * 关闭小图标隐藏背景
                 */
                view_main_backgound_two.setVisibility(View.GONE);
            }
        });
    }

    /**
     * 社区的悬浮按钮
     */
    public void setTopicSuspendButton() {
        int redActionButtonSize = getResources().getDimensionPixelSize(R.dimen.publish_big_size);
        int redActionButtonMarginRight = getResources().getDimensionPixelSize(R.dimen.publish_big_margin_right);
        int redActionButtonMargin = getResources().getDimensionPixelSize(R.dimen.publish_big_margin_button);
        int redActionButtonContentSize = getResources().getDimensionPixelSize(R.dimen.publish_big_size);
        int redActionButtonContentMargin = getResources().getDimensionPixelSize(R.dimen.publish_big_margin_button);
        int redActionMenuRadius = getResources().getDimensionPixelSize(R.dimen.publish_radius);
        int blueSubActionButtonSize = getResources().getDimensionPixelSize(R.dimen.publish_small_size);
        int blueSubActionButtonContentMargin = 0;

        ImageView fabIconStar = new ImageView(MainActivity.this);

        fabIconStar.setImageDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
        fabIconStar.setVisibility(View.GONE);
        FloatingActionButton.LayoutParams starParams = new FloatingActionButton.LayoutParams(redActionButtonSize, redActionButtonSize);
        starParams.setMargins(redActionButtonMargin,
                redActionButtonMargin,
                redActionButtonMarginRight,
                redActionButtonMargin);
        fabIconStar.setLayoutParams(starParams);

        FloatingActionButton.LayoutParams fabIconStarParams = new FloatingActionButton.LayoutParams(redActionButtonContentSize, redActionButtonContentSize);
        fabIconStarParams.setMargins(redActionButtonContentMargin,
                redActionButtonContentMargin,
                redActionButtonContentMargin,
                redActionButtonContentMargin);

        leftCenterButton = new FloatingActionButton.Builder(MainActivity.this)
                .setContentView(fabIconStar, fabIconStarParams)
                .setBackgroundDrawable(R.mipmap.tab_buplish)
                .setPosition(FloatingActionButton.POSITION_BOTTOM_RIGHT)
                .setLayoutParams(starParams)
                .build();
        SubActionButton.Builder lCSubBuilder = new SubActionButton.Builder(MainActivity.this);
//                lCSubBuilder.setBackgroundDrawable(getResources().getDrawable(R.mipmap.ic_launcher));

        FrameLayout.LayoutParams blueContentParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        blueContentParams.setMargins(blueSubActionButtonContentMargin,
                blueSubActionButtonContentMargin,
                blueSubActionButtonContentMargin,
                blueSubActionButtonContentMargin);
        lCSubBuilder.setLayoutParams(blueContentParams);
        FrameLayout.LayoutParams blueParams = new FrameLayout.LayoutParams(blueSubActionButtonSize, blueSubActionButtonSize);
        lCSubBuilder.setLayoutParams(blueParams);

        ivQuickpublish = new ImageView(MainActivity.this);
        ivArticlrpublish = new ImageView(MainActivity.this);
        ivActivitypublish = new ImageView(MainActivity.this);
//                ImageView lcIcon4 = new ImageView(MainActivity.this);
//                ImageView lcIcon5 = new ImageView(MainActivity.this);


        ivQuickpublish.setImageDrawable(getResources().getDrawable(R.mipmap.tab_quick_publish));
        ivArticlrpublish.setImageDrawable(getResources().getDrawable(R.mipmap.tab_article_publish));
        ivActivitypublish.setImageDrawable(getResources().getDrawable(R.mipmap.tab_activity_publish));
//        lcIcon4.setImageDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
//        lcIcon5.setImageDrawable(getResources().getDrawable(R.mipmap.ic_launcher));

        // Build another menu with custom options
        leftCenterMenu = new FloatingActionMenu.Builder(MainActivity.this)
                .addSubActionView(lCSubBuilder.setContentView(ivQuickpublish, blueContentParams).build())
                .addSubActionView(lCSubBuilder.setContentView(ivArticlrpublish, blueContentParams).build())
                .addSubActionView(lCSubBuilder.setContentView(ivActivitypublish, blueContentParams).build())
//                .addSubActionView(lCSubBuilder.setContentView(lcIcon4, blueContentParams).build())
//                .addSubActionView(lCSubBuilder.setContentView(lcIcon5, blueContentParams).build())
                .setRadius(redActionMenuRadius)
                .setStartAngle(-130)
                .setEndAngle(-230)
                .attachTo(leftCenterButton)
                .setAnimationHandler(new DefaultAnimationHandler())
                .build();

        /**
         * 快速发布点击事件
         */
        ivQuickpublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leftCenterMenu.close(true);
                Intent quickintent = new Intent(MainActivity.this, QuickPublishActivity.class);
                startActivity(quickintent);
            }
        });
        /**
         * 文章发布点击事件
         */
        ivArticlrpublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leftCenterMenu.close(true);
                Intent Articlrpublishintent = new Intent(MainActivity.this, ArticlrPublishActivity.class);
                startActivity(Articlrpublishintent);
            }
        });
        /**
         * 活动发布点击事件
         */
        ivActivitypublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leftCenterMenu.close(true);
                Intent Activitypublishintent = new Intent(MainActivity.this, ActivityPublishActivity.class);
                startActivity(Activitypublishintent);
            }
        });
        view_main_backgound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leftCenterMenu.close(true);
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                leftCenterMenu.setStateChangeListener(new FloatingActionMenu.MenuStateChangeListener() {
                    @Override
                    public void onMenuOpened(FloatingActionMenu floatingActionMenu) {
                        Log.e("lchj", "22222222");
                        /**
                         * 打开小图标显示背景
                         */
                        view_main_backgound.setVisibility(View.VISIBLE);

                    }

                    @Override
                    public void onMenuClosed(FloatingActionMenu floatingActionMenu) {
                        Log.e("lchj", "333333333333333");
                        /**
                         * 关闭小图标隐藏背景
                         */
                        view_main_backgound.setVisibility(View.GONE);
                    }
                });
            }
        }).start();
    }

    /**
     * 判断是否显示发布悬浮控件
     *
     * @return
     */
    private boolean isFloatingMenu() {
        if (DoyenleftCenterButton != null) {

            DoyenleftCenterButton.setVisibility(View.GONE);
            doyenleftCenterMenu.close(false);
            return false;
        }
        if (leftCenterButton != null) {

            leftCenterButton.setVisibility(View.GONE);
            leftCenterMenu.close(false);
            return false;
        }
        return true;
    }


    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;        // a|=b的意思就是把a和b按位或然后赋值给a   按位或的意思就是先把a和b都换成2进制，然后用或操作，相当于a=a|b
        } else {
            winParams.flags &= ~bits;        //&是位运算里面，与运算  a&=b相当于 a = a&b  ~非运算符
        }
        win.setAttributes(winParams);
    }

    /**
     * 触摸手机底部返回键的业务逻辑
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            /**
             * 判断view是否可见
             */
            if(view_main_backgound.hasWindowFocus() && view_main_backgound.getVisibility()
                    == View.VISIBLE && view_main_backgound.isShown()){
                leftCenterMenu.close(true);
                return true;
            }
            if(view_main_backgound_two.hasWindowFocus() && view_main_backgound_two.getVisibility()
                    == View.VISIBLE && view_main_backgound_two.isShown()){
                doyenleftCenterMenu.close(true);
                return true;
            }
            exit();
            return true;

        }
        return super.onKeyDown(keyCode, event);
    }
    private void exit() {
        if ((System.currentTimeMillis() - clickTime) > 2000) {
            toast.showSameMsg("再次点击退出", 2000);
            clickTime = System.currentTimeMillis();
        } else {
            this.finish();
            System.exit(0);
        }
    }

}
