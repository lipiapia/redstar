package com.red.star.wechat.work.entity;

import com.red.star.wechat.data.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.HashSet;
import java.util.Set;

/**
 * @author nofish.yan@gmail.com
 * @date 2018/1/26.
 */
@Data
@Table(name = "t_resource")
public class Resource extends BaseEntity {

    private Integer pid;


    /**
     * 0:button 1:导航栏
     */
    private Integer type;

    private String name;

    /** 资源请求地址 **/
    private String address;

    @Transient
    private Set<Role> roles = new HashSet<>();


    /**
     * 0:已选择,1:未选择
     */
    @Transient
    private Integer checked;
}
