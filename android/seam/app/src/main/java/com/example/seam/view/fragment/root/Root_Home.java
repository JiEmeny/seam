package com.example.seam.view.fragment.root;

import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.seam.R;
import com.example.seam.databinding.RootHomeBinding;
import com.example.seam.pojo.Banners;
import com.example.seam.pojo.Home_RV;
import com.example.seam.pojo.Serve;
import com.example.seam.view.adapter.AdapterBanners;
import com.example.seam.view.adapter.AdapterHomeRecyclerView;
import com.example.seam.view.adapter.HomeTopicPagerAdapter;
import com.example.seam.view.adapter.TopicAdapter;
import com.example.seam.viewmodel.Root_Home_VM;
import com.google.android.material.snackbar.Snackbar;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;

import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.DummyPagerTitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页
 *
 * @e-mail:2036573698@qq.com
 */
public class Root_Home extends Fragment {
    private RootHomeBinding binding;
    private Root_Home_VM model;
    private ArrayList<Banners> banners;
    private ArrayList<Home_RV> home_rvs;
    private AdapterHomeRecyclerView adapterHomeRecyclerView;
    private AdapterBanners adapterBanners;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = RootHomeBinding.inflate(LayoutInflater.from(container.getContext()), container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.toolbar.header.setText("首页");
        model = new ViewModelProvider(this).get(Root_Home_VM.class);
        Topic(1, 4);
        // 轮播图
        Banner();
        // 瀑布流视图
        RecyclerViewShow();


//        int[] i = {1, 2, 3, 4, 5, 6, 7, 8, 9, 7, 8, 9, 4, 5, 6};
//        int[] max = {10, 20, 30, 40, 50, 60, 70, 80, 90, 70, 80, 90, 40, 50, 60};
//        int[] min = {1, 2, 3, 4, 5, 6, 7, 8, 9, 7, 8, 9, 4, 5, 6};
//        binding.lineChar1.setTempMax(max);
//        binding.lineChar1.setTempMin(min);
//        binding.lineChar1.invalidate();
//        binding.lineChar2.setTempDay(i);
//        binding.lineChar2.invalidate();
    }

    /**
     * 瀑布流视图
     */
    private void RecyclerViewShow() {
        home_rvs = new ArrayList<>();
        home_rvs.add(new Home_RV("校园建设", R.drawable.banner1));
        home_rvs.add(new Home_RV("校园夕阳", R.drawable.banner2));
        home_rvs.add(new Home_RV("校园夜景", R.drawable.banner3));
        // 让RecyclerView滑动起来更丝滑
        binding.recyclerview.setHasFixedSize(true);
        binding.recyclerview.setNestedScrollingEnabled(false);
        // RecyclerView
        adapterHomeRecyclerView = new AdapterHomeRecyclerView(home_rvs);
        binding.recyclerview.setAdapter(adapterHomeRecyclerView);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        binding.recyclerview.setLayoutManager(staggeredGridLayoutManager);
    }

    /**
     * 轮播图
     */
    private void Banner() {
        banners = new ArrayList<>();
        banners.add(new Banners("校园建设", R.drawable.banner1));
        banners.add(new Banners("校园夕阳", R.drawable.banner2));
        banners.add(new Banners("校园夜景", R.drawable.banner3));
        adapterBanners = new AdapterBanners(banners);
        binding.banner.setAdapter(adapterBanners)
                .addBannerLifecycleObserver(this) // 添加到观察者中
                .setIndicator(new CircleIndicator(getContext()))
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(Object data, int position) {
                        Snackbar.make(binding.banner, banners.get(position).getTitle(), banners.get(position).getImage()).show();
                    }
                });
    }

    /**
     * @param rowNum
     * @param columnNum
     */
    private void Topic(int rowNum, int columnNum) {
        model.GetServe();
        model.serveMutableLiveData.observe(getViewLifecycleOwner(), serve -> {
            List<Serve.RowsBean> rows = serve.getRows();
            // 根据数据的多少类分页，每页的数据为rowNum*columnNum
            // 每个单页包含的数据量：rowNum*columnNum
            int singlePageDatasNum = rowNum * columnNum;
            // 算出有几页菜单：rows.size()%singlePageDatasNum（向上取整）
            int pageNum = rows.size() / singlePageDatasNum;
            if (rows.size() % singlePageDatasNum > 0) {
                // 如果取模大于0，就还要多一页出来，放剩下的不满项
                pageNum++;
            }
            ArrayList<RecyclerView> recyclerViews = new ArrayList<>();
            for (int i = 0; i < pageNum; i++) {
                RecyclerView recyclerView = new RecyclerView(getContext().getApplicationContext());
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext().getApplicationContext(), columnNum);
                recyclerView.setLayoutManager(gridLayoutManager);
                int fromIndex = i * singlePageDatasNum;
                int toIndex = (i + 1) * singlePageDatasNum;
                if (toIndex > rows.size()) toIndex = rows.size();
                // 截取每个页面包含数据
                ArrayList<Serve.RowsBean> rowsBeans = new ArrayList<>(rows.subList(fromIndex, toIndex));
                // 设置每个页面的适配器数据
                TopicAdapter menuAdapter = new TopicAdapter(getContext().getApplicationContext(), rowsBeans);
                menuAdapter.setOnItemClickListener(new TopicAdapter.OnItemClickListener() {
                    @Override
                    public void onTopicItemClick(Serve.RowsBean position) {
                        // 点击事件
                        Toast.makeText(getContext(), "后续将开通" + position.getName() + "项目", Toast.LENGTH_SHORT).show();
                    }
                });
                // 绑定适配器，并添加到list
                recyclerView.setAdapter(menuAdapter);
                recyclerViews.add(recyclerView);
                // ViewPager的适配器
                HomeTopicPagerAdapter menuViewPagerAdapter = new HomeTopicPagerAdapter(recyclerViews);
                binding.topicViewPager.setAdapter(menuViewPagerAdapter);
                // 动态设置ViewPager的高度，并加载所有页面
                // 这里的80为MainMenuAdapter中布局文件高度
                int height = dp2px(getContext().getApplicationContext(), 76.0f);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, rows.size() <= columnNum ? height : height * rowNum);
                binding.topicViewPager.setLayoutParams(layoutParams);
                binding.topicViewPager.setOffscreenPageLimit(pageNum - 1);
                // 创建指示器
                CommonNavigator commonNavigator = new CommonNavigator(getContext().getApplicationContext());
                commonNavigator.setAdjustMode(true);
                final int finalPageNum = pageNum;
                commonNavigator.setAdapter(new CommonNavigatorAdapter() {
                    @Override
                    public int getCount() {
                        return finalPageNum;
                    }

                    @Override
                    public IPagerTitleView getTitleView(Context context, int index) {
                        return new DummyPagerTitleView(context);
                    }

                    @Override
                    public IPagerIndicator getIndicator(Context context) {
                        LinePagerIndicator indicator = new LinePagerIndicator(context);
                        indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                        // 指示器的高
                        indicator.setLineHeight(UIUtil.dip2px(context, 3));
                        // 指示器的宽度，然后通过页数来评分
                        indicator.setLineWidth(UIUtil.dip2px(context, 66 / finalPageNum));
                        indicator.setRoundRadius(UIUtil.dip2px(context, 3));
                        indicator.setStartInterpolator(new AccelerateInterpolator());
                        indicator.setEndInterpolator(new DecelerateInterpolator(3));
                        indicator.setColors(ContextCompat.getColor(context, R.color.background));
                        return indicator;
                    }
                });
                // 配置指示器，冰河ViewPager产生绑定
                binding.topicIndicator.setNavigator(commonNavigator);
                ViewPagerHelper.bind(binding.topicIndicator, binding.topicViewPager);
            }
        });
    }

    /**
     * 获取ViewPager的高度
     *
     * @param context
     * @param dpVal
     * @return
     */
    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal,
                context.getResources().getDisplayMetrics());
    }
}
