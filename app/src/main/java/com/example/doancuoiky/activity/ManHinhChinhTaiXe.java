package com.example.doancuoiky.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.doancuoiky.R;
import com.example.doancuoiky.model.User;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class ManHinhChinhTaiXe extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    private int iđ;

    public int getIđ() {
        return iđ;
    }

    public void setIđ(int iđ) {
        this.iđ = iđ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chinh_tai_xe);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView= findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this , drawer, toolbar ,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState==null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TinNhanTaiXeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_message);
        }
        //lấy thông tin user từ bundle
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        User thumbs= (User)bundle.getSerializable("objectUser");
        setIđ(thumbs.getId());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_message:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new TinNhanTaiXeFragment()).commit();
                break;
            case R.id.taixe_misson:
                NhiemVuTaiXeFragment nhiemVuTaiXeFragment = new NhiemVuTaiXeFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("id", getIđ());
                nhiemVuTaiXeFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,nhiemVuTaiXeFragment).commit();
                break;
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new TrangCaNhanTaiXeFragment()).commit();
                break;
            case R.id.nav_share:
                Toast.makeText(this,"share123",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_send:
                Toast.makeText(this,"Send213",Toast.LENGTH_LONG).show();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
}