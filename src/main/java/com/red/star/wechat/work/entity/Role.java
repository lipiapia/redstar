package com.red.star.wechat.work.entity;

import com.red.star.wechat.data.entity.BaseEntity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.HashSet;
import java.util.Set;

/**
 * @author nofish.yan@gmail.com
 * @date 2018/1/26.
 */
@Data
@Table(name = "t_role")
public class Role extends BaseEntity implements GrantedAuthority {

    private String name;

    /**
     * 枚举值
     */
    private String enumValue;

    @Transient
    private Set<Resource> resources = new HashSet<>();

    @Override
    public String getAuthority() {
        return this.name;
    }
}
