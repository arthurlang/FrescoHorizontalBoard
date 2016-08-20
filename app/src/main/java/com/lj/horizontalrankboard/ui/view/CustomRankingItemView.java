package com.lj.horizontalrankboard.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lj.horizontalrankboard.MyImageLoader;
import com.lj.horizontalrankboard.R;
import com.lj.horizontalrankboard.ui.entity.CustomRankingEntity;

/**
 * Description
 * Created by langjian on 2016/8/8.
 * Version
 */
public class CustomRankingItemView extends RelativeLayout {
    private Drawable mSequenceIcon;
    private SimpleDraweeView mIcon;
    private TextView mSequenceNo;
    private TextView mSubTitle;
    private TextView mTitle;

    public CustomRankingItemView(Context context) {
        this(context,null);
    }

    public CustomRankingItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomRankingItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initCustomProperty(attrs);
        initView();
    }

    private void initView() {

        View root = LayoutInflater.from(getContext()).inflate(R.layout.custom_ranking_item, this);

        mSequenceNo = (TextView)root.findViewById(R.id.user_rank_no);
        mIcon = (SimpleDraweeView)root.findViewById(R.id.user_icon);
        mTitle = (TextView)root.findViewById(R.id.user_ranking_item_name);
        mSubTitle = (TextView)root.findViewById(R.id.user_ranking_item_name_second);
        if(mSequenceIcon != null){
            mSequenceNo.setBackgroundDrawable(mSequenceIcon);
            mSequenceNo.setVisibility(VISIBLE);
        }
    }

    private void initCustomProperty(AttributeSet attrs) {
        TypedArray typedArray  = getContext().obtainStyledAttributes(attrs, R.styleable.DiscoveryStarRankingItemView);
        mSequenceIcon = typedArray.getDrawable(R.styleable.CustomRankingItemView_sequence);
    }

    /**
     * 动态设置左下角排名icon序号
     * V7.8 added by langjian@qiyi.com
     * @param resId
     */
    public void setSequence(int resId) {
        if(mSequenceNo != null && resId > 0){
            mSequenceNo.setBackgroundResource(resId);
            mSequenceNo.setVisibility(VISIBLE);
        }
    }

    public void updateUI(CustomRankingEntity customRankingEntity) {
        if(customRankingEntity == null){
            return;
        }

        if(!TextUtils.isEmpty(customRankingEntity.getIcon())){
            MyImageLoader.loadFrescoImg(mIcon, customRankingEntity.getIcon(), null);
        }

        if(TextUtils.isEmpty(customRankingEntity.getTitle())){

        }else{
            mTitle.setText(customRankingEntity.getTitle());
        }

        if(TextUtils.isEmpty(customRankingEntity.getSubTitle())){

        }else{
            mSubTitle.setText(customRankingEntity.getSubTitle());
        }
    }

    /**
     *
     * @param type ALIGN_LEFT = 5;ALIGN_TOP = 6;ALIGN_RIGHT = 7;ALIGN_BOTTOM = 8;
     * @param offSet Margin offset of sequence icon ,unit is px;
     */
    public void setSequenceMarginAlignToIcon(int type, int offSet) {
        RelativeLayout.LayoutParams layoutParams = (LayoutParams) mSequenceNo.getLayoutParams();

        switch(type){
            case ALIGN_LEFT:{
                layoutParams.addRule(RelativeLayout.ALIGN_LEFT,R.id.user_icon);
                layoutParams.leftMargin = offSet;
                break;
            }
            case ALIGN_TOP:{
                layoutParams.addRule(RelativeLayout.ALIGN_LEFT,R.id.user_icon);
                layoutParams.topMargin = offSet;
                break;
            }
            case ALIGN_RIGHT:{
                layoutParams.addRule(RelativeLayout.ALIGN_LEFT,R.id.user_icon);
                layoutParams.rightMargin = offSet;
                break;
            }
            case ALIGN_BOTTOM:{
                layoutParams.addRule(RelativeLayout.ALIGN_LEFT,R.id.user_icon);
                layoutParams.bottomMargin = offSet;
                break;
            }
            default :{
                break;
            }
        }
    }

    public TextView getSubTitle() {
        return mTitle;
    }

    public TextView getTitle() {
        return mSubTitle;
    }

    public void setIconWidthPx(int px) {
        if(mIcon != null){
            RelativeLayout.LayoutParams relativeLayoutParams = (RelativeLayout.LayoutParams)mIcon.getLayoutParams();
            relativeLayoutParams.width = px;
        }
    }

    public void setIconHeightPx(int px) {
        if(mIcon != null){
            RelativeLayout.LayoutParams relativeLayoutParams = (RelativeLayout.LayoutParams)mIcon.getLayoutParams();
            relativeLayoutParams.height = px;
        }
    }

    public void setSequenceIconWidthPx(int px) {
        if(mSequenceNo != null){
            RelativeLayout.LayoutParams relativeLayoutParams = (RelativeLayout.LayoutParams)mSequenceNo.getLayoutParams();
            relativeLayoutParams.width = px;
        }
    }

    public void setSequenceIconHeightPx(int px) {
        if(mSequenceNo != null){
            RelativeLayout.LayoutParams relativeLayoutParams = (RelativeLayout.LayoutParams)mSequenceNo.getLayoutParams();
            relativeLayoutParams.height = px;
        }
    }
}
