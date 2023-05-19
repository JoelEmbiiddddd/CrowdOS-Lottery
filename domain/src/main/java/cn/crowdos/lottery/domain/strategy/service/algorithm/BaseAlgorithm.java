package cn.crowdos.lottery.domain.strategy.service.algorithm;

import cn.crowdos.lottery.common.Constants;
import cn.crowdos.lottery.domain.strategy.model.vo.AwardRateVO;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 * @File : BaseAlgorithm
 * @Author : LiXin Huang, NWPU
 * @Desc : 共用的算法逻辑抽象类
 * @Email : iHuanglixin@outlook.com
 */

public abstract class BaseAlgorithm implements IDrawAlgorithm {
    /***
     * 下边有两个抽奖算法
     */

    /**
     * 斐波那契散列增量，逻辑：黄金分割点：(√5 - 1) / 2 = 0.6180339887，Math.pow(2, 32) * 0.6180339887 = 0x61c88647
     */
    private final int HASH_INCREMENT = 0x61c88647;

    /**
     * 数组初始化长度 128，保证数据填充时不发生碰撞的最小初始化值
     */
    private final int RATE_TUPLE_LENGTH = 128;

    /**
     * 存放概率与奖品对应的散列结果，strategyId -> rateTuple
     */
    protected Map<Long, String[]> rateTupleMap = new ConcurrentHashMap<>();

    /**
     * 奖品区间概率值，strategyId -> [awardId->begin、awardId->end]
     */
    protected Map<Long, List<AwardRateVO>> awardRateInfoMap = new ConcurrentHashMap<>();

    @Override
    public synchronized void initRateTuple(Long strategyId, Integer strategyMode, List<AwardRateVO> awardRateInfoList) {

        // 前置判断
        if (isExist(strategyId)){
            return;
        }

        // 保存奖品概率信息
        awardRateInfoMap.put(strategyId, awardRateInfoList);

        // 非单项概率，不必存入缓存，因为这部分抽奖算法需要实时处理中奖概率。
        if (!Constants.StrategyMode.SINGLE.getCode().equals(strategyMode)) {
            return;
        }

        // 生成一个Map数组，判断是否存在，不存在就创建一个List集合, RATE_TUPLE_LENGTH 是128的原因是，0~100，尽可能选择2的n次方的倍数
        String[] rateTuple = rateTupleMap.computeIfAbsent(strategyId, k -> new String[RATE_TUPLE_LENGTH]);
        int cursorVal = 0;
        for (AwardRateVO awardRateInfo : awardRateInfoList) {
            int rateVal = awardRateInfo.getAwardRate().multiply(new BigDecimal(100)).intValue(); // 概率乘以100

            // 循环填充概率范围值
            for (int i = cursorVal + 1; i <= (rateVal + cursorVal); i++) {  // 不断地填充概率
                rateTuple[hashIdx(i)] = awardRateInfo.getAwardId();
            }

            cursorVal += rateVal;

        }
    }


    // 判断值是否存在，输入的是策略ID号
    @Override
    public boolean isExist(Long strategyId) {
        return awardRateInfoMap.containsKey(strategyId);
    }

    /**
     * 斐波那契（Fibonacci）散列法，计算哈希索引下标值
     *
     * @param val 值
     * @return 索引
     */
    protected int hashIdx(int val) {
        int hashCode = val * HASH_INCREMENT + HASH_INCREMENT;
        return hashCode & (RATE_TUPLE_LENGTH - 1);
    }

    /**
     * 生成百位随机抽奖码
     *
     * @return 随机值
     */
    protected int generateSecureRandomIntCode(int bound) {
        return new SecureRandom().nextInt(bound) + 1;    // 获取指定范围的随机数, 如果bound为9的话，则生成的数为0~8
    }

}
