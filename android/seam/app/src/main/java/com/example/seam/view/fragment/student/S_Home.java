package com.example.seam.view.fragment.student;

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
import com.example.seam.databinding.SHomeBinding;
import com.example.seam.pojo.Banners;
import com.example.seam.pojo.Course;
import com.example.seam.pojo.Home_RV;
import com.example.seam.pojo.Serve;
import com.example.seam.pojo.Times;
import com.example.seam.view.adapter.AdapterBanners;
import com.example.seam.view.adapter.AdapterHomeRecyclerView;
import com.example.seam.view.adapter.HomeTopicPagerAdapter;
import com.example.seam.view.adapter.TopicAdapter;
import com.example.seam.pojo.Curriculum;
import com.example.seam.viewmodel.Root_Home_VM;
import com.example.seam.viewmodel.Student_VM;
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
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 首页
 *
 * @e-mail:2036573698@qq.com
 */
public class S_Home extends Fragment {
    private SHomeBinding binding;
    private Root_Home_VM model;
    private ArrayList<Banners> banners;
    private ArrayList<Home_RV> home_rvs;
    private AdapterHomeRecyclerView adapterHomeRecyclerView;
    private AdapterBanners adapterBanners;
    private Student_VM model1;
    private ArrayList<Curriculum> s;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = SHomeBinding.inflate(LayoutInflater.from(container.getContext()), container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.toolbar.header.setText("首页");
        model = new ViewModelProvider(this).get(Root_Home_VM.class);
        model1 = new ViewModelProvider(this).get(Student_VM.class);
        Topic(1, 4);
        // 轮播图
        Banner();
        // 瀑布流视图
        RecyclerViewShow();
        // 课程表
        Curriculum();
    }

    @Override
    public void onStart() {
        super.onStart();
        s.clear();
    }

    /**
     * 课程表
     */
    private void Curriculum() {
        AtomicInteger i = new AtomicInteger();
        AtomicInteger j = new AtomicInteger();
        s = new ArrayList<>();
        model1.GetCourses();
        model1.curriculumMutableLiveData.observe(getViewLifecycleOwner(), course -> {
            List<Course.RowsBean> rows = course.getRows();
            i.getAndIncrement();
            if (i.get() == 1) {
                model1.GetTime();
                model1.timesMutableLiveData.observe(getViewLifecycleOwner(), times -> {
                    List<Times.RowsBean> rows1 = times.getRows();
                    j.getAndIncrement();
                    if (j.get() == 1) {
                        s.add(new Curriculum("上午", rows1.get(0).getTimes(), rows.get(0).getCourses(), rows.get(1).getCourses(), rows.get(2).getCourses(), rows.get(3).getCourses(), rows.get(4).getCourses()));
                        s.add(new Curriculum("上午", rows1.get(1).getTimes(), rows.get(0).getCourses(), rows.get(1).getCourses(), rows.get(2).getCourses(), rows.get(3).getCourses(), rows.get(4).getCourses()));
                        s.add(new Curriculum("上午", rows1.get(2).getTimes(), "", "", "", "", ""));
                        s.add(new Curriculum("上午", rows1.get(3).getTimes(), "", "", "", "", ""));
                        s.add(new Curriculum("下午", rows1.get(4).getTimes(), "", rows.get(0).getCourses(), "", rows.get(1).getCourses(), ""));
                        s.add(new Curriculum("下午", rows1.get(5).getTimes(), "", rows.get(0).getCourses(), "", rows.get(1).getCourses(), ""));
                        s.add(new Curriculum("下午", rows1.get(6).getTimes(), rows.get(2).getCourses(), "", rows.get(3).getCourses(), rows.get(4).getCourses(), ""));
                        s.add(new Curriculum("下午", rows1.get(7).getTimes(), rows.get(2).getCourses(), "", rows.get(3).getCourses(), rows.get(4).getCourses(), ""));
                        s.add(new Curriculum("晚上", rows1.get(8).getTimes(), "", "", "", "", ""));
                        s.add(new Curriculum("晚上", rows1.get(9).getTimes(), "", "", "", "", ""));
                        binding.smartTable.setData(s);
                    }
                });
            }
        });
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
