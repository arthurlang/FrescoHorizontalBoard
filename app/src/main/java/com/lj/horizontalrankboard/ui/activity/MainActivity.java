package com.lj.horizontalrankboard.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.lj.horizontalrankboard.R;
import com.lj.horizontalrankboard.ui.entity.CustomRankingEntity;
import com.lj.horizontalrankboard.ui.entity.StarEntity;
import com.lj.horizontalrankboard.ui.view.CustomRankingItemView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Fresco加载排行榜。
 * 1、支持候选人头像大小可配置，名次序号可配置，名次序号位置可配置，还支持隐藏冗余展位，服务端返回数据容错处理
 * 2、自定义CustomRankingItemView支持多个属性，例如sequence等
 * 3、动态生成每一个候选人展位，可扩展行好。
 *
 */
public class MainActivity extends AppCompatActivity {

    private static final int TOTAL_RANK_NUMBER = 4;
    //排名icon
    private int[] rankSequenceArray = {R.drawable.pp_fans_contribute_ranking_sequence_1,R.drawable.pp_fans_contribute_ranking_sequence_2,R.drawable.pp_fans_contribute_ranking_sequence_3,R.drawable.pp_fans_contribute_ranking_sequence_4};
    private LinearLayout mContainer;
    private List<StarEntity> mStarEntities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createRankBoard();
        loadData();
    }

    private void loadData() {
        requestHttp();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                updateRankingStarUI();
            }
        },1000);

    }

    private void requestHttp() {
        mStarEntities = new ArrayList<>();
        for(int i=0;i<TOTAL_RANK_NUMBER - 1;i++){
            StarEntity starEntity = new StarEntity();
            starEntity.setWallId(666);
            starEntity.setRank(i+1);
            starEntity.setScore("999");
            starEntity.setStarName("Name"+i);
            starEntity.setStarHeadIcon("http://img7.qiyipic.com/passport/20151027/21/999000002833_130x130.png");
            mStarEntities.add(starEntity);
        }
    }

    //更新明星排行榜
    private void updateRankingStarUI(){
        //一级容错预案
        if(mStarEntities == null || mStarEntities.size() == 0){
            mContainer.setVisibility(View.GONE);
            return;
        }else{
            mContainer.setVisibility(View.VISIBLE);
        }
        //二级容错预案，如果服务端给的数据过多，最多显示TOTAL_RANK_NUMBER条数据
        int serverRankListCount = ((mStarEntities.size() > TOTAL_RANK_NUMBER) ? TOTAL_RANK_NUMBER : mStarEntities.size());
        for(int i = 0; i < serverRankListCount;i++){
            updateItemView((CustomRankingItemView) mContainer.getChildAt(i),copy(mStarEntities.get(i)));
        }
        //隐藏没有数据的候选人展位
        if(mStarEntities.size() < TOTAL_RANK_NUMBER){
            for(int j = mStarEntities.size();j<TOTAL_RANK_NUMBER;j++){
                mContainer.getChildAt(j).setVisibility(View.INVISIBLE);
            }
        }
    }

    /**
     * 根据url显示每个用户头像等信息
     * @param fanView
     * @param rankEntity
     */
    private void updateItemView(CustomRankingItemView fanView, final CustomRankingEntity rankEntity) {
        fanView.updateUI(rankEntity);
        fanView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击粉丝头像，跳转至个人页。
                long wallid = rankEntity.getId();
                if (wallid > 0){
                    Toast.makeText(MainActivity.this,"to12345",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * V7.8 add 复制一份排行榜对象，为排行榜组件提供数据
     * @param
     * @return
     */
    private CustomRankingEntity copy(StarEntity starEntity) {
        if(null == starEntity){
            return null;
        }
        CustomRankingEntity customRankingEntity = new CustomRankingEntity();
        customRankingEntity.setId(starEntity.getWallId());
        customRankingEntity.setIcon(starEntity.getStarHeadIcon());
        customRankingEntity.setTitle(starEntity.getStarName());
        customRankingEntity.setSubTitle(starEntity.getScore()+"分");
        return customRankingEntity;
    }

    private void createRankBoard() {
        mContainer = (LinearLayout)findViewById(R.id.board_container);
        //动态生成包含默认数量候选人的排行榜
        for(int i = 0;i < TOTAL_RANK_NUMBER;i++){
            createItemView(i);
        }
    }

    private void createItemView(int position) {
        CustomRankingItemView childView = new CustomRankingItemView(this);
        if(rankSequenceArray != null && position < rankSequenceArray.length){
            childView.setSequence(rankSequenceArray[position]);
            childView.setSequenceMarginAlignToIcon(RelativeLayout.ALIGN_LEFT,0);//序号图片距离头像左边偏移量
            childView.setSequenceMarginAlignToIcon(RelativeLayout.ALIGN_BOTTOM,0);//序号图片距离头像底部偏移量
            childView.getTitle().setText("无名氏");
            childView.getSubTitle().setText("贡献0分");
        }
        mContainer.addView(childView,new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.WRAP_CONTENT,1));
        //更新头像宽高、名称颜色、字体大小
        childView.setIconWidthPx(150);
        childView.setIconHeightPx(150);
        childView.setSequenceIconWidthPx(60);
        childView.setSequenceIconHeightPx(60);
    }
}
