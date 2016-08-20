package com.lj.horizontalrankboard.ui.entity;

/**
 * Description
 * Created by langjian on 2016/8/20.
 * Version
 */
public class StarEntity {
    private long wallId; //圈子id
    private String starHeadIcon; //明星头像
    private String starName; //明星名字
    private int rank; //排名

    public void setScore(String score) {
        this.score = score;
    }

    private String score;

    public long getWallId() {
        return wallId;
    }

    public void setWallId(long wallId) {
        this.wallId = wallId;
    }

    public String getStarHeadIcon() {
        return starHeadIcon;
    }

    public void setStarHeadIcon(String starHeadIcon) {
        this.starHeadIcon = starHeadIcon;
    }

    public String getStarName() {
        return starName;
    }

    public void setStarName(String starName) {
        this.starName = starName;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getScore() {
        return score;
    }
}
