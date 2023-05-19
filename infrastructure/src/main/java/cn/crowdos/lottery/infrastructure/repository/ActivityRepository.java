package cn.crowdos.lottery.infrastructure.repository;

import cn.crowdos.lottery.common.Constants;
import cn.crowdos.lottery.domain.activity.model.aggregates.ActivityInfoLimitPageRich;
import cn.crowdos.lottery.domain.activity.model.req.ActivityInfoLimitPageReq;
import cn.crowdos.lottery.domain.activity.model.req.PartakeReq;
import cn.crowdos.lottery.domain.activity.model.res.StockResult;
import cn.crowdos.lottery.domain.activity.model.vo.*;
import cn.crowdos.lottery.domain.activity.repository.IActivityRepository;
import cn.crowdos.lottery.infrastructure.dao.*;
import cn.crowdos.lottery.infrastructure.po.*;
import cn.crowdos.lottery.infrastructure.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
/**
 * @File : ActivityRepository
 * @Author : LiXin Huang, NWPU
 * @Desc : 用户活动参与次数表
 * @Email : iHuanglixin@outlook.com
 */

@Repository
public class ActivityRepository implements IActivityRepository {

    private Logger logger = LoggerFactory.getLogger(ActivityRepository.class);

    @Resource
    private IActivityDao activityDao;
    @Resource
    private IAwardDao awardDao;
    @Resource
    private IStrategyDao strategyDao;
    @Resource
    private IStrategyDetailDao strategyDetailDao;
    @Resource
    private IUserTakeActivityCountDao userTakeActivityCountDao;

    @Resource
    private RedisUtil redisUtil;

    @Override
    public void addActivity(ActivityVO activity) {
        Activity req = new Activity();
        req.setId(activity.getId());
        req.setActivityId(activity.getActivityId());
        req.setActivityName(activity.getActivityName());
        req.setActivityDesc(activity.getActivityDesc());
        req.setBeginDateTime(activity.getBeginDateTime());
        req.setEndDateTime(activity.getEndDateTime());
        req.setStockCount(activity.getStockCount());
        req.setStockSurplusCount(activity.getStockSurplusCount());
        req.setTakeCount(activity.getTakeCount());
        req.setStrategyId(activity.getStrategyId());
        req.setState(activity.getState());
        req.setCreator(activity.getCreator());
        req.setCreateTime(activity.getCreateTime());
        req.setUpdateTime(activity.getUpdateTime());

        activityDao.insert(req);

        // 设置活动库存 KEY
        redisUtil.set(Constants.RedisKey.KEY_LOTTERY_ACTIVITY_STOCK_COUNT(activity.getActivityId()), 0);
    }

    @Override
    public void addAward(List<AwardVO> awardList) {
        List<Award> req = new ArrayList<>();
        for (AwardVO awardVO : awardList) {
            Award award = new Award();
            award.setAwardId(awardVO.getAwardId());
            award.setAwardType(awardVO.getAwardType());
            award.setAwardName(awardVO.getAwardName());
            award.setAwardContent(awardVO.getAwardContent());
            req.add(award);
        }
        awardDao.insertList(req);
    }

    @Override
    public void addStrategy(StrategyVO strategy) {
        Strategy req = new Strategy();
        req.setStrategyId(strategy.getStrategyId());
        req.setStrategyDesc(strategy.getStrategyDesc());
        req.setStrategyMode(strategy.getStrategyMode());
        req.setGrantType(strategy.getGrantType());
        req.setGrantDate(strategy.getGrantDate());
        req.setExtInfo(strategy.getExtInfo());

        strategyDao.insert(req);
    }

    @Override
    public void addStrategyDetailList(List<StrategyDetailVO> strategyDetailList) {
        List<StrategyDetail> req = new ArrayList<>();
        for (StrategyDetailVO strategyDetailVO : strategyDetailList) {
            StrategyDetail strategyDetail = new StrategyDetail();
            strategyDetail.setStrategyId(strategyDetailVO.getStrategyId());
            strategyDetail.setAwardId(strategyDetailVO.getAwardId());
            strategyDetail.setAwardName(strategyDetailVO.getAwardName());
            strategyDetail.setAwardCount(strategyDetailVO.getAwardCount());
            strategyDetail.setAwardSurplusCount(strategyDetailVO.getAwardSurplusCount());
            strategyDetail.setAwardRate(strategyDetailVO.getAwardRate());
            req.add(strategyDetail);
        }
        strategyDetailDao.insertList(req);
    }

    @Override
    public boolean alterStatus(Long activityId, Enum<Constants.ActivityState> beforeState, Enum<Constants.ActivityState> afterState) {
        AlterStateVO alterStateVO = new AlterStateVO(activityId, ((Constants.ActivityState) beforeState).getCode(), ((Constants.ActivityState) afterState).getCode());
        int count = activityDao.alterState(alterStateVO);
        return 1 == count;
    }

    @Override
    public ActivityBillVO queryActivityBill(PartakeReq req) {

        // 查询活动信息
        Activity activity = activityDao.queryActivityById(req.getActivityId());

        // 从缓存中获取库存
        Object usedStockCountObj = redisUtil.get(Constants.RedisKey.KEY_LOTTERY_ACTIVITY_STOCK_COUNT(req.getActivityId()));

        // 查询领取次数
        UserTakeActivityCount userTakeActivityCountReq = new UserTakeActivityCount();
        userTakeActivityCountReq.setuId(req.getuId());
        userTakeActivityCountReq.setActivityId(req.getActivityId());
        UserTakeActivityCount userTakeActivityCount = userTakeActivityCountDao.queryUserTakeActivityCount(userTakeActivityCountReq);

        // 封装结果信息
        ActivityBillVO activityBillVO = new ActivityBillVO();
        activityBillVO.setuId(req.getuId());
        activityBillVO.setActivityId(req.getActivityId());
        activityBillVO.setActivityName(activity.getActivityName());
        activityBillVO.setBeginDateTime(activity.getBeginDateTime());
        activityBillVO.setEndDateTime(activity.getEndDateTime());
        activityBillVO.setTakeCount(activity.getTakeCount());
        activityBillVO.setStockCount(activity.getStockCount());
        activityBillVO.setStockSurplusCount(null == usedStockCountObj ? activity.getStockSurplusCount() : activity.getStockCount() - Integer.parseInt(String.valueOf(usedStockCountObj)));
        activityBillVO.setStrategyId(activity.getStrategyId());
        activityBillVO.setState(activity.getState());
        activityBillVO.setUserTakeLeftCount(null == userTakeActivityCount ? null : userTakeActivityCount.getLeftCount());

        return activityBillVO;
    }

    @Override
    public int subtractionActivityStock(Long activityId) {
        return activityDao.subtractionActivityStock(activityId);
    }

    @Override
    public List<ActivityVO> scanToDoActivityList(Long id) {
        List<Activity> activityList = activityDao.scanToDoActivityList(id);
        List<ActivityVO> activityVOList = new ArrayList<>(activityList.size());
        for (Activity activity : activityList) {
            ActivityVO activityVO = new ActivityVO();
            activityVO.setId(activity.getId());
            activityVO.setActivityId(activity.getActivityId());
            activityVO.setActivityName(activity.getActivityName());
            activityVO.setBeginDateTime(activity.getBeginDateTime());
            activityVO.setEndDateTime(activity.getEndDateTime());
            activityVO.setState(activity.getState());
            activityVOList.add(activityVO);
        }
        return activityVOList;
    }

    @Override
    public StockResult subtractionActivityStockByRedis(String uId, Long activityId, Integer stockCount) {

        //  1. 获取抽奖活动库存 Key
        String stockKey = Constants.RedisKey.KEY_LOTTERY_ACTIVITY_STOCK_COUNT(activityId);

        // 2. 扣减库存，目前占用库存数。「给incr的结果加一层分段锁，在不影响性能的情况下，会可靠。以往压测tps 2500 ~ 5000 计算结果 1 + 100万，最终结果不是100万，不过可能因环境导致」
        Integer stockUsedCount = (int) redisUtil.incr(stockKey, 1);  // 按照delta的数递增

        // 3. 超出库存判断，进行恢复原始库存
        if (stockUsedCount > stockCount) {
            redisUtil.decr(stockKey, 1);
            return new StockResult(Constants.ResponseCode.OUT_OF_STOCK.getCode(), Constants.ResponseCode.OUT_OF_STOCK.getInfo());
        }

        // 4. 以活动库存占用编号，生成对应加锁Key，细化锁的颗粒度
        String stockTokenKey = Constants.RedisKey.KEY_LOTTERY_ACTIVITY_STOCK_COUNT_TOKEN(activityId, stockUsedCount);

        // 5. 使用 Redis.setNx 加一个分布式锁
        boolean lockToken = redisUtil.setNx(stockTokenKey, 350L);
        if (!lockToken) {
            logger.info("抽奖活动{}用户秒杀{}扣减库存，分布式锁失败：{}", activityId, uId, stockTokenKey);
            return new StockResult(Constants.ResponseCode.ERR_TOKEN.getCode(), Constants.ResponseCode.ERR_TOKEN.getInfo());
        }

        return new StockResult(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo(), stockTokenKey, stockCount - stockUsedCount);
    }

    @Override
    public void recoverActivityCacheStockByRedis(Long activityId, String tokenKey, String code) {
        if (null == tokenKey) {
            return;
        }
        // 删除分布式锁 Key
        redisUtil.del(tokenKey);
    }

    @Override
    public ActivityInfoLimitPageRich queryActivityInfoLimitPage(ActivityInfoLimitPageReq req) {
        Long count = activityDao.queryActivityInfoCount(req);
        List<Activity> list = activityDao.queryActivityInfoList(req);

        List<ActivityVO> activityVOList = new ArrayList<>();
        for (Activity activity : list) {
            ActivityVO activityVO = new ActivityVO();
            activityVO.setId(activity.getId());
            activityVO.setActivityId(activity.getActivityId());
            activityVO.setActivityName(activity.getActivityName());
            activityVO.setActivityDesc(activity.getActivityDesc());
            activityVO.setBeginDateTime(activity.getBeginDateTime());
            activityVO.setEndDateTime(activity.getEndDateTime());
            activityVO.setStockCount(activity.getStockCount());
            activityVO.setStockSurplusCount(activity.getStockSurplusCount());
            activityVO.setTakeCount(activity.getTakeCount());
            activityVO.setStrategyId(activity.getStrategyId());
            activityVO.setState(activity.getState());
            activityVO.setCreator(activity.getCreator());
            activityVO.setCreateTime(activity.getCreateTime());
            activityVO.setUpdateTime(activity.getUpdateTime());

            activityVOList.add(activityVO);
        }

        return new ActivityInfoLimitPageRich(count, activityVOList);
    }

}
