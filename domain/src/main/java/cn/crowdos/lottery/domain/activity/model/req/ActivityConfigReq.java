package cn.crowdos.lottery.domain.activity.model.req;

import cn.crowdos.lottery.domain.activity.model.aggregates.ActivityConfigRich;
/**
 * @File : ActivityConfigReq
 * @Author : LiXin Huang, NWPU
 * @Desc : 活动配置请求对象
 * @Email : iHuanglixin@outlook.com
 */

public class ActivityConfigReq {

    /** 活动ID */
    private Long activityId;

    /** 活动配置信息 */
    private ActivityConfigRich activityConfigRich;

    public ActivityConfigReq() {
    }

    public ActivityConfigReq(Long activityId, ActivityConfigRich activityConfigRich) {
        this.activityId = activityId;
        this.activityConfigRich = activityConfigRich;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public ActivityConfigRich getActivityConfigRich() {
        return activityConfigRich;
    }

    public void setActivityConfigRich(ActivityConfigRich activityConfigRich) {
        this.activityConfigRich = activityConfigRich;
    }

}
