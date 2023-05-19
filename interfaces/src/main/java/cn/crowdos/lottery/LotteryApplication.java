package cn.crowdos.lottery;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @File : LotteryApplication
 * @Author : LiXin Huang, NWPU
 * @Desc : 抽奖活动部署
 * @Email : iHuanglixin@outlook.com
 */

@SpringBootApplication
@Configurable
@EnableDubbo
public class LotteryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LotteryApplication.class, args);
    }

}
