package com.red.star.wechat.work.site.sys;

import com.red.star.wechat.work.base.BaseApiService;
import com.red.star.wechat.work.base.ResponseBase;
import com.red.star.wechat.work.constant.AdminSessionHolder;
import com.red.star.wechat.work.entity.Admin;
import com.red.star.wechat.work.entity.MenuResource;
import com.red.star.wechat.work.entity.Resource;
import com.red.star.wechat.work.site.admin.mapper.ResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * @description: 菜单控制器
 * @author: liucancan
 * @create: 2018-12-03 10:41
 **/
@RestController
@RequestMapping("/sys/menu")
public class MenuController extends BaseApiService {

    @Autowired
    private ResourceMapper resourceMapper;

    @GetMapping()
    public ModelAndView menuViews() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("menu");
        return modelAndView;
    }

    /**
     * 根据登录人d资源i查询
     *
     * @return com.red.star.wechat.work.base.ResponseBase
     * @author liucancan
     * @date 15:02 2018/12/6
     */
    @GetMapping("/menuList")
    public ResponseBase menuList() {
        Admin admin = AdminSessionHolder.get();
        List<MenuResource> menus = resourceMapper.findResourceByUserId(admin.getId());
        return setResultSuccess(menus);
    }

    /**
     * 查询所有资源信息
     *
     * @return com.red.star.wechat.work.base.ResponseBase
     * @author liucancan
     * @date 15:02 2018/12/6
     */
    @GetMapping("/menuAll")
    public ResponseBase menuAll() {
        List<Resource> resources = resourceMapper.selectAll();
        return setResultSuccess(resources);
    }

    /**
     * 根据id查询菜单详情
     */
    @GetMapping("/menuInfoById")
    public ResponseBase menuInfoById(Integer id) {
        MenuResource resource = resourceMapper.findResourceByid(id);
        return setResultSuccess(resource);
    }

    /**
     * 更新
     */
    @GetMapping("/updateMenu")
    public ResponseBase updateMenu(Resource resource) {
        resource.setUpdateTime(new Date());
        resourceMapper.updateByPrimaryKeySelective(resource);
        return setResultSuccess();
    }

    /**
     * 删除，禁用
     */
    @GetMapping("/deleteMenu")
    public ResponseBase deleteMenu(Integer id) {
        Resource resource = new Resource();
        resource.setDel(1);
        resource.setUpdateTime(new Date());
        resourceMapper.updateByPrimaryKeySelective(resource);
        return setResultSuccess();
    }

    /**
     * 新增
     */
    @GetMapping("/addMenu")
    public ResponseBase addMenu(Resource resource) {
        resource.setCreateTime(new Date());
        resource.setUpdateTime(resource.getCreateTime());
        resourceMapper.insert(resource);
        return setResultSuccess();
    }

}
