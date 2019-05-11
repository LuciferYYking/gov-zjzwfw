package org.warless.xender.mapper;

import org.apache.ibatis.annotations.Param;
import org.warless.xender.entity.Resource;

import java.util.List;

/**
 * ResourceMapper
 *
 * @author :  Noa Swartz
 * @version : 1.0
 * @date : Created in 2019/5/11
 */
public interface ResourceMapper {

    void insert(@Param("res")Resource res);

    List<Resource> selectAll();

}
