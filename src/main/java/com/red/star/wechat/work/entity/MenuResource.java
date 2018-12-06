package com.red.star.wechat.work.entity;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: liucancan
 * @create: 2018-12-04 10:23
 **/
@Data
public class MenuResource extends Resource {
    List<MenuResource> childs;
}
