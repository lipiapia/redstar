package com.red.star.wechat.work.utils;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @description: 树形结构
 * @author: lijing
 * @create: 2018-12-10 17:37
 */
@Data
public class Tree<T> {

    /**
     * 节点Id
     */
    private String id ;
    /**
     * 显示节点文本
     */
    private String text ;
    /**
     * 节点状态 open/closed
     */
    private Map<String,Object> state ;
    /**
     * 节点是否被选中 true/false
     */
    private boolean check = false ;
    /**
     * 节点属性
     */
    private Map<String,Object> attribute ;
    /**
     * 父Id
     */
    private String parentid ;
    /**
     * 是否有父节点
     */
    private boolean hasParent =false ;
    /**
     * 是否有子节点
     */
    private boolean hasChildren =false ;/**
     * 节点的子节点
     */
    private List<Tree<T>> children;
}
