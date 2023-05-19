package cn.crowdos.lottery.interfaces.assembler;

import cn.crowdos.lottery.domain.strategy.model.vo.DrawAwardVO;
import cn.crowdos.lottery.rpc.activity.booth.dto.AwardDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
/**
 * @File : AwardMapping
 * @Author : LiXin Huang, NWPU
 * @Desc : 对象转换配置
 * @Email : iHuanglixin@outlook.com
 */

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AwardMapping extends IMapping<DrawAwardVO, AwardDTO> {

    @Mapping(target = "userId", source = "uId")
    @Override
    AwardDTO sourceToTarget(DrawAwardVO var1);

    @Override
    DrawAwardVO targetToSource(AwardDTO var1);

}
