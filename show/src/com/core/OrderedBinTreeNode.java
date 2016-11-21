package com.core;



import java.util.HashMap;

/**
 * Created by Xia on 2016/11/8.
 * 顺序存储结构
 * 根据输入字符串的顺序，将每个节点实例化后存储在Map中，类似于存储数据在数组中
 * 取的时候根据索引的关系来取
 */
public class OrderedBinTreeNode {

    private Object data;
    private int index;
    private int height;
    private int size;

    //多传一个参数，用于确定除自己信息以外其他的位置信息
    public OrderedBinTreeNode(Object e,int i) {
        index = i;
        data = e;
        height = 0;
        size = 1;
    }
    public int getIndex() {
        return this.index;
    }
    public  int getData() {
        return Integer.valueOf(this.data.toString());
    }
    public  OrderedBinTreeNode getLChild(HashMap<String,OrderedBinTreeNode> map) {
        return map.get(Integer.toString(this.index*2));
    }

    public  OrderedBinTreeNode getRChild(HashMap<String,OrderedBinTreeNode> map) {
        return map.get(Integer.toString(this.index*2+1));
    }
    public  OrderedBinTreeNode getParent(HashMap<String,OrderedBinTreeNode> map) {
        return map.get(Integer.toString(this.index/2));
    }

}
