package com.oa.service.impl;

import com.oa.mapper.PositionMapper;
import com.oa.pojo.Position;
import com.oa.service.PositoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 享学课堂-SaiLing老师
 * @老师qq:2408524688
 */
@Service
public class PositoinServiceImpl implements PositoinService {
    @Autowired
    private PositionMapper positionMapper;
    @Override
    public List<Position> showPostions() {
        return positionMapper.selectByExample(null);
    }
}
