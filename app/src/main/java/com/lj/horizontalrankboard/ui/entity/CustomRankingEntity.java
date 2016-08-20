package com.lj.horizontalrankboard.ui.entity;

/**
 * Description suit for CustomRankingItemView data
 * Created by langjian on 2016/8/8.
 * Version
 */
public class CustomRankingEntity {
    //用户id
    private long id;
    //用户icon地址
    private String icon;
    //用户标题
    private String title;

    public CustomRankingEntity() {

    }
    //用户副标题

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String subTitle;
    //序号
    private int sequence;
    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public CustomRankingEntity(int id, String icon, String title, String subName, int sequence) {
        id = id;
        icon = icon;
        title = title;
        subTitle = subName;
        sequence = sequence;
    }
}
