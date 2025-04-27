package com.github.melonticket.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.melonticket.model.Concert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ConcertMapper extends BaseMapper<Concert> {
}
    