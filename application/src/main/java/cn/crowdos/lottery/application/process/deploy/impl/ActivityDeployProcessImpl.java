package cn.crowdos.lottery.application.process.deploy.impl;

import cn.crowdos.lottery.application.process.deploy.IActivityDeployProcess;
import cn.crowdos.lottery.domain.activity.model.aggregates.ActivityInfoLimitPageRich;
import cn.crowdos.lottery.domain.activity.model.req.ActivityInfoLimitPageReq;
import cn.crowdos.lottery.domain.activity.service.deploy.IActivityDeploy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @File : ActivityDeployProcessImpl
 * @Author : LiXin Huang, NWPU
 * @Desc : 活动部署实现
 * @Email : iHuanglixin@outlook.com
 */


@Service
public class ActivityDeployProcessImpl implements IActivityDeployProcess {

    @Resource
    private IActivityDeploy activityDeploy;

    @Override
    public ActivityInfoLimitPageRich queryActivityInfoLimitPage(ActivityInfoLimitPageReq req) {
        return activityDeploy.queryActivityInfoLimitPage(req);
    }
}
