package com.example.seam.view.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.seam.R;
import com.example.seam.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    protected ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        // 获取NavController
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        // NavigationUI通过setupWithNavController将底部导航栏和导航控制器进行绑定
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController);
    }
}