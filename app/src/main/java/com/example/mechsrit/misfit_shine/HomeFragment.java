package com.example.mechsrit.misfit_shine;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.HashMap;

public class HomeFragment extends Fragment implements BaseSliderView.OnSliderClickListener,ViewPagerEx.OnPageChangeListener {
    SliderLayout sliderLayout;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view=inflater.inflate(R.layout.fragment_home, container, false);
       sliderLayout=view.findViewById(R.id.slider);
        HashMap<String, Integer> file_maps=new HashMap<>();

        file_maps.put(getString(R.string.imgSlide1),R.drawable.e);
        file_maps.put(getString(R.string.imgSlide2),R.drawable.c);
        file_maps.put(getString(R.string.imgSlide3),R.drawable.bridalmakeup);
        file_maps.put(getString(R.string.imgSlide4),R.drawable.h);
        file_maps.put(getString(R.string.imgSlide5),R.drawable.lipsticks);
        file_maps.put(getString(R.string.imgSlide6),R.drawable.eyeshadows);
        file_maps.put(getString(R.string.imgSlide7),R.drawable.brushes);
        file_maps.put(getString(R.string.imgSlide8),R.drawable.cosmetics);
        file_maps.put(getString(R.string.imgSlide9),R.drawable.makeupkit);

        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getActivity());
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString(getString(R.string.ex), name);

            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(3000);
        sliderLayout.addOnPageChangeListener(this);

        return view;
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

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
}
