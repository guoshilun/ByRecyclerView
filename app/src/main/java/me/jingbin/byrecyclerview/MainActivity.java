package me.jingbin.byrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import me.jingbin.byrecyclerview.activity.SimpleActivity;
import me.jingbin.byrecyclerview.activity.SwipeRefreshActivity;
import me.jingbin.byrecyclerview.adapter.MainAdapter;
import me.jingbin.byrecyclerview.bean.MainItemBean;
import me.jingbin.byrecyclerview.databinding.ActivityMainBinding;
import me.jingbin.byrecyclerview.utils.DataUtil;
import me.jingbin.library.ByRecyclerView;
import me.jingbin.library.config.ByDividerItemDecoration;

/**
 * @author jingbin
 * link to https://github.com/youlookwhat/ByRecyclerView
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        final MainAdapter homeAdapter = new MainAdapter(DataUtil.getMainActivityList(this));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(homeAdapter);
        ByDividerItemDecoration itemDecoration = new ByDividerItemDecoration(binding.recyclerView.getContext(), DividerItemDecoration.VERTICAL, false);
        itemDecoration.setDrawable(ContextCompat.getDrawable(binding.recyclerView.getContext(), R.drawable.shape_line));
        binding.recyclerView.addItemDecoration(itemDecoration);
        binding.recyclerView.setOnItemClickListener(new ByRecyclerView.OnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                MainItemBean itemData = homeAdapter.getItemData(position);
                if (itemData.getCls() != null) {
                    startActivity(new Intent(MainActivity.this, itemData.getCls()));
                }
            }
        });
        binding.recyclerView.setLoadMoreEnabled(true);
        binding.recyclerView.loadMoreEnd();
    }


    public void goSimple(View view) {
        startActivity(new Intent(this, SimpleActivity.class));
    }

    public void goSwipeRefresh(View view) {
        startActivity(new Intent(this, SwipeRefreshActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.recyclerView.destroy();
    }
}
