package cn.crowdos.lottery.application.process.draw.res;

import cn.crowdos.lottery.common.Result;
import cn.crowdos.lottery.domain.strategy.model.vo.DrawAwardVO;

/**
 * @File : DrawProcessResult
 * @Author : LiXin Huang, NWPU
 * @Desc : 活动抽奖结果
 * @Email : iHuanglixin@outlook.com
 */
public class DrawProcessResult extends Result {

    private DrawAwardVO drawAwardVO;

    public DrawProcessResult(String code, String info) {
        super(code, info);
    }

    public DrawProcessResult(String code, String info, DrawAwardVO drawAwardVO) {
        super(code, info);
        this.drawAwardVO = drawAwardVO;
    }

    public DrawAwardVO getDrawAwardVO() { 
        return drawAwardVO;
    }

    public void setDrawAwardVO(DrawAwardVO drawAwardVO) {
        this.drawAwardVO = drawAwardVO;
    }

}
