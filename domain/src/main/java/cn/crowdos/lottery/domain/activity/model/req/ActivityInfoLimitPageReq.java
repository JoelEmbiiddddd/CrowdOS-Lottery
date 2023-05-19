package cn.crowdos.lottery.domain.activity.model.req;

import cn.crowdos.lottery.common.PageRequest;
/**
 * @File : ActivityInfoLimitPageReq
 * @Author : LiXin Huang, NWPU
 * @Desc : 活动分页查询请求对象
 * @Email : iHuanglixin@outlook.com
 */

public class ActivityInfoLimitPageReq extends PageRequest {

    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 活动名称
     */
    private String activityName;

    public ActivityInfoLimitPageReq(int page, int rows) {
        super(page, rows);
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

}
