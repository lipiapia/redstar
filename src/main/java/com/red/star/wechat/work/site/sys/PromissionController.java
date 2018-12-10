package com.red.star.wechat.work.site.sys;

import com.red.star.wechat.data.entity.vo.RoleInfo;
import com.red.star.wechat.work.entity.TableContainer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @description: 权限控制器
 * @author: liucancan
 * @create: 2018-12-03 10:41
 **/
@RestController
@RequestMapping("/sys/promission")
public class PromissionController {
    @Resource
    private RoleManageService roleService;

    @GetMapping()
    public ModelAndView promissionView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("promission");
        return modelAndView;
    }

    @RequestMapping(value = "/roleinfo")
    public ResponseEntity roleInfo(RoleInfo roleInfo) {
        TableContainer tableContainer = roleService.queryRoleInfo(roleInfo);
        return new ResponseEntity<>(tableContainer, HttpStatus.OK);

    }

    /**
     * @param roleInfo
     * @description: 启用/禁用角色
     * @author: lijing
     * @create: 2018-12-06 11:40
     */
    @RequestMapping(value = "/changerolestatus")
    public ResponseEntity changeRoleStatus(RoleInfo roleInfo) {
        if (roleInfo!=null){
            Integer i = roleService.changeRoleStatus(roleInfo);
            if (i==1){
                return new ResponseEntity(true, HttpStatus.OK);
            }else {
                return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
            }
        }else{
            return new ResponseEntity(false, HttpStatus.OK);
        }
    }
    /**
     * @param roleInfo
     * @description: 添加角色
     * @author: lijing
     * @create: 2018-12-06 14:40
     */
    @RequestMapping(value = "/addrole")
    public ResponseEntity addRole(RoleInfo roleInfo) {
        if (roleInfo!=null){
            Integer i = roleService.addRole(roleInfo);
            if (i==1){
                return new ResponseEntity(true, HttpStatus.OK);
            }else {
                return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
            }
        }else{
            return new ResponseEntity(false, HttpStatus.OK);
        }
    }

    /**
     * @param roleId
     * @description: 查询所有的菜单列表
     * @author: lijing
     * @create: 2018-12-10 14:20
     */
    @RequestMapping(value = "/queryallmenu")
    public Map qyeryAllMenu(Integer roleId) {
        //roleService.queryAllMenu(roleId);
        return null;
    }
}
