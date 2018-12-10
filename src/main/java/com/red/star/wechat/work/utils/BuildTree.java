package com.red.star.wechat.work.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildTree {
    public static <T> Tree<T> Build(List<Tree<T>> nodes){
        if (nodes == null){
            return  null;
        }
        List<Tree<T>> topNodes = new ArrayList<>();
        for (Tree<T> children : nodes ) {
            String pid = children.getParentid();
            if (pid == null || "0".equals(pid)){
                topNodes.add(children);
                continue;
            }
            for (Tree<T> parent : nodes ) {
                String id = children.getParentid();
                if (id != null && "0".equals(pid)){
                    parent.getChildren().add(children);
                    children.setHasParent(true);
                    parent.setHasChildren(true);
                    continue;
                }
            }
        }
        Tree<T> root = new Tree<T>();
        if (topNodes.size()==1){
            root = topNodes.get(0);
        }else {
            root.setId("0");
            root.setParentid("");
            root.setHasParent(false);
            root.setChildren(topNodes);
            root.setHasChildren(true);
            root.setCheck(true);
            root.setText("顶级节点");
            Map<String,Object> state = new HashMap<>();
            state.put("opened",true);
            root.setState(state);
        }
        return root;
    }
}
