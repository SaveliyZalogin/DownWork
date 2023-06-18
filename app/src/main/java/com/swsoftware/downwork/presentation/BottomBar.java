package com.swsoftware.downwork.presentation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.LayoutTransition;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.swsoftware.downwork.R;

import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderEffectBlur;
import eightbitlab.com.blurview.RenderScriptBlur;

public class BottomBar extends BlurView {

    public EditText searchBar;
    public BottomNavigationView bottomNavigation;

    public BottomBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.bottom_bar_layout, this, true);

        float radius = 15f;
        View decorView = ((AppCompatActivity) context).getWindow().getDecorView();
        ViewGroup rootView = (ViewGroup) decorView.findViewById(android.R.id.content);
        Drawable windowBackground = decorView.getBackground();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            this.setupWith(rootView, new RenderEffectBlur())
                    .setFrameClearDrawable(windowBackground)
                    .setBlurRadius(radius);
        } else {
            this.setupWith(rootView, new RenderScriptBlur(context))
                    .setFrameClearDrawable(windowBackground)
                    .setBlurRadius(radius);
        }

        searchBar = findViewById(R.id.searchBar);
        searchBar.getBackground().setAlpha(204);
        bottomNavigation = findViewById(R.id.bottomNavigation);

        Animator slideIn = AnimatorInflater.loadAnimator(getContext(), R.animator.slide_in);
        Animator slideOut = AnimatorInflater.loadAnimator(getContext(), R.animator.slide_out);
        LayoutTransition layoutTransition = new LayoutTransition();
        layoutTransition.setAnimator(LayoutTransition.DISAPPEARING, slideOut);
        layoutTransition.setAnimator(LayoutTransition.APPEARING, slideIn);
        ((LinearLayout) this.getChildAt(0)).setLayoutTransition(layoutTransition);
    }

    public void hide() {
        this.setVisibility(GONE);
    }

    public void show() {
        this.setVisibility(VISIBLE);
    }

    public void hideNavigation() {
        bottomNavigation.setVisibility(GONE);
    }

    public void showNavigation() {
        bottomNavigation.setVisibility(VISIBLE);
    }

    public void hideSearch() {
        searchBar.setVisibility(GONE);
    }

    public void showSearch() {
        searchBar.setVisibility(VISIBLE);
    }
}