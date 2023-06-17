package com.swsoftware.downwork.presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.navigation.NavigationBarView;
import com.swsoftware.downwork.R;
import com.swsoftware.downwork.presentation.chats.ChatsFragment;
import com.swsoftware.downwork.presentation.main.MainFragment;
import com.swsoftware.downwork.presentation.orders.OrdersFragment;
import com.swsoftware.downwork.presentation.profile.ProfileFragment;
import com.swsoftware.downwork.presentation.projects.SearchFragment;

import java.util.Objects;

public class MainActivity extends AppCompatActivity
        implements FragmentManager.OnBackStackChangedListener, NavigationBarView.OnItemSelectedListener {

    AppBarLayout appBarLayout;
    public CollapsingToolbarLayout collapsingToolbarLayout;
    FragmentManager fragmentManager;
    public BottomBar bottomBar;
    Toolbar toolbar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appBarLayout = findViewById(R.id.appBarLayout);
        collapsingToolbarLayout = findViewById(R.id.collapsingToolbar);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bottomBar = findViewById(R.id.bottomBar);
        bottomBar.bottomNavigation.setOnItemSelectedListener(this);
        bottomBar.searchBar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    fragmentManager.beginTransaction()
                            .replace(R.id.frameLayout, new SearchFragment())
                            .addToBackStack("search")
                            .setReorderingAllowed(false)
                            .commit();
                }
                return false;
            }
        });

        fragmentManager = getSupportFragmentManager();
        fragmentManager.addOnBackStackChangedListener(this);
        fragmentManager.beginTransaction()
                .replace(R.id.frameLayout, new MainFragment())
                .addToBackStack("main")
                .setReorderingAllowed(false)
                .commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        fragmentManager.popBackStack();
        return true;
    }

    @Override
    public void onBackStackChanged() {
        if (fragmentManager.getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry lastFragmentBackStack =
                    fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1);
            switch (Objects.requireNonNull(lastFragmentBackStack.getName())) {
                case "main":
                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                    appBarLayout.setVisibility(View.VISIBLE);
                    bottomBar.show();
                    bottomBar.showNavigation();
                    bottomBar.showSearch();
                    collapsingToolbarLayout.setTitle("Категории");
                    collapsingToolbarLayout.setVisibility(View.VISIBLE);
                    break;
                case "search":
                case "projectsListCategory":
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    bottomBar.hideNavigation();
                    break;
                case "chatList":
                    collapsingToolbarLayout.setVisibility(View.VISIBLE);
                    collapsingToolbarLayout.setTitle("Чаты");
                    bottomBar.hideSearch();
                    break;
                case "orderList":
                    collapsingToolbarLayout.setVisibility(View.VISIBLE);
                    collapsingToolbarLayout.setTitle("Заказы");
                    break;
                case "profile":
                    collapsingToolbarLayout.setVisibility(View.GONE);
                    bottomBar.hideSearch();
                    break;
                case "category":
                    break;
                case "chat":
                    break;
                case "searchResult":
                    break;
            }
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                fragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, new MainFragment())
                        .addToBackStack("main")
                        .setReorderingAllowed(false)
                        .commit();
                break;
            case R.id.chat:
                fragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, new ChatsFragment())
                        .addToBackStack("chatList")
                        .setReorderingAllowed(false)
                        .commit();
                break;
            case R.id.orders:
                fragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, new OrdersFragment())
                        .addToBackStack("orderList")
                        .setReorderingAllowed(false)
                        .commit();
                break;
            case R.id.profile:
                fragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, new ProfileFragment())
                        .addToBackStack("profile")
                        .setReorderingAllowed(false)
                        .commit();
                break;
        }
        return true;
    }
}