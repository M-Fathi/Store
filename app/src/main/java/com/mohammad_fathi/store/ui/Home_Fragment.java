package com.mohammad_fathi.store.ui;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.mohammad_fathi.store.R;
//import com.mohammad_fathi.store.di.component.DaggerAdapter_Product_component;
import com.mohammad_fathi.store.model.room.entity.Discount_Items_entity;
import com.mohammad_fathi.store.ui.adapter.Adapter_recyclerView_discountItems_FragHome;
import com.mohammad_fathi.store.viewmodel.Items_ViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Home_Fragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {


    int height, width;
    Context context;
    Application application;

    @BindView(R.id.slider)
    SliderLayout sliderLayout_top;
    @BindView(R.id.rv_Home_frag_discount)
    RecyclerView rv_discountItems;
    List<Discount_Items_entity> list;

    Items_ViewModel items_viewModel;

    Adapter_recyclerView_discountItems_FragHome myAdapter;


    List list2;


    public Home_Fragment() {
    }

    public Home_Fragment(int display_height, int display_width, Context contextMain) {
        height = display_height;
        width = display_width;
        context = contextMain;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //context = getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        items_viewModel = ViewModelProviders.of(this).get(Items_ViewModel.class);

        dataLoader_Local_sliderTop();

        recyclerView_initialize();

        return view;
    }

    private void getDisplayDimensionFromFragment() {
        // ----------------------------------------- دریافت ابعاد صفحه نمایش از داخل فرگمنت --------------------------
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;

        Log.d("Display", "onCreateView: height: " + height + "  width: " + width);
    }

    private void dataLoader_Online_sliderTop() {
        HashMap<String, String> url_maps = new HashMap<String, String>();
        url_maps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
        url_maps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");

        for (String name : url_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            //.setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            sliderLayout_top.addSlider(textSliderView);
        }
        slider_costumization();
    }
//  Slider initializing --------------------------------------------------------------------
    private void dataLoader_Local_sliderTop() {
        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("تابستان شگفت انگیز", R.drawable.slider_pic5);
        file_maps.put("سرو و پذیرایی", R.drawable.slider_pic3);
        file_maps.put("تصفیه آب", R.drawable.slider_pic2);
        file_maps.put("ویژه این هفته", R.drawable.slider_pic6);

        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            sliderLayout_top.addSlider(textSliderView);
        }

        slider_costumization();


    }

    @Override
    public void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        sliderLayout_top.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(getActivity(), slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void slider_costumization() {
        sliderLayout_top.setPresetTransformer(SliderLayout.Transformer.Default);
        sliderLayout_top.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout_top.setCustomAnimation(new DescriptionAnimation());
        sliderLayout_top.setDuration(4000);
        sliderLayout_top.addOnPageChangeListener(this);
    }

    // Recycler View Adapter --------------------------------------------------
    public void recyclerView_initialize() {
        //-----------RecyclerView initialize ------------------------------------------------------

        myAdapter = new Adapter_recyclerView_discountItems_FragHome(getActivity());
        rv_discountItems.setAdapter(myAdapter);
        setDataToAdapter2();
        rv_discountItems.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rv_discountItems.setItemAnimator(new DefaultItemAnimator());
    }

    //--- Get all data from local database by DAO (only for TEST) -------------------------------------------
    private List<Discount_Items_entity> getSampleData() {


        list = new ArrayList<>();
        list.add(new Discount_Items_entity(1, "title1", "20%", 870000, "https://itmag.ua/upload/iblock/cd2/142.png"));

        return list;
    }

    private void setDataToAdapter() {

        LiveData<List<Discount_Items_entity>> Discount_Items_list = items_viewModel.selectAll_discountItems();
        if (Discount_Items_list == null) {
            myAdapter.setList(getSampleData());
        } else {
            Discount_Items_list.observe(this, new Observer<List<Discount_Items_entity>>() {
                @Override
                public void onChanged(List<Discount_Items_entity> discount_items_entities) {
                    myAdapter.setList(discount_items_entities);

                    for (Discount_Items_entity product : discount_items_entities) {
                        Log.d("category1", "onChanged: " + product.getItem_name() + " / " + product.getDiscount_percent());
                    }
                }
            });
        }

    }

    private void setDataToAdapter2() {

        List<Discount_Items_entity> list =items_viewModel.selectAll_discountItems4Test();
        if (list == null) {
            myAdapter.setList(getSampleData());
        } else {
            myAdapter.setList(list);
        }
    }
}