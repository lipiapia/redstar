package com.red.star.wechat.work.entity;

import com.red.star.wechat.data.entity.BaseEntity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by xulonglong on 2017/6/15.
 * 管理后台管理员用户实体
 */
@Data
@Table(name = "t_admin")
public class Admin extends BaseEntity implements UserDetails {

	private String username;

	/** 通过PasswordEncoder加密 **/
	private String password;

    private String name;

    private String employeeId;

    private String bRegionCode;

    private String sRegionCode;

    private String mallCode;

	private Integer merchantId;

	private String enumValue;

	@Transient
	private String bRegionName;

	@Transient
	private String sRegionName;

	@Transient
	private String mallName;

	@Transient
	private String merchantName;

    @Transient
    private String ruleId;

	@Transient
	private Set<Role> roles = new HashSet<>();

    @Transient
    private Set<Role> authorities = new HashSet<>();

	public Admin() {
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    authorities.addAll(roles);
		return authorities;
	}

	@Override
	public String getPassword() {
        return password;
    }

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
