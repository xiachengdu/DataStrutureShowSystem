package com.test;

/**
 * Created by xcd on 2016/11/1.
 */
import java.util.ArrayList;
import java.util.HashMap;


import com.core.BinTreeNode;
import com.core.OrderedBinTreeNode;

public class test {
    public static void main(String[] args) {
        //奉行左加右减的原则，+表示左边是右边的左孩子结点
        //前面还应该加一个正确输入的判断，如果输入出错则抛出异常
        String createBinTree = "1+>2,3->2,4+>5,6->5,2+>7,5->7";
        HashMap<String,BinTreeNode> map = createChainedBinTree(createBinTree);
        String createOrderedBinTree1 = "1,2,3,4,5,null,6,null,7";
        HashMap<String,OrderedBinTreeNode> map1 = createOrderedBinTree(createOrderedBinTree1);
        //查找节点
        //有问题,map1.get()必须要从1开始，get("0")中存的值是0
        System.out.println(map1.get("1").getIndex());
        System.out.println(map1.get("1").getLChild(map1).getData());
        System.out.println(map1.get("1").getLChild(map1).getLChild(map1).getData());
        System.out.println(map1.get("1").getLChild(map1).getLChild(map1).getParent(map1).getData());
        System.out.println("huo");
    }

    /**
     * 顺序存储结构，
     * 传入顺序编写的构造二叉树的字符串，
     * 返回构造完的二叉树的根节点
     * @param source
     * @return
     */
//    public static BinTreeNode createOrderedBinTree(String source) {
//
//    }



    /**
     * 顺序存储
     * 构造一棵二叉树,
     * 传入字符串，返回二叉树的Map对象
     */

    public static HashMap createOrderedBinTree(String source) {
        String[] processingSource = source.split(",");
        HashMap<String,OrderedBinTreeNode> map = new HashMap<String,OrderedBinTreeNode>();
        for(int i=0;i<processingSource.length;i++) {
            map.put(Integer.toString(i),new OrderedBinTreeNode(processingSource[i],i));
        }
        //可能有问题
        return map;
    }



    /**
     * 链式存储结构
     * 传入用于构造二叉树的原始字符串，
     * 返回构造好的含有完整二叉树的HashMap，Map中有所有节点的实例
     * @param source
     * @return HashMap
     */
    public static HashMap createChainedBinTree(String source) {
        ArrayList<String> arrayListSource = new ArrayList<String>();
        String ProcessingString = source.replaceAll("\\+>",",").replaceAll("\\->",",");
        String[] ProcessedString = ProcessingString.split(",");
        for (int i=0;i<ProcessedString.length;i++) {
            if(!arrayListSource.contains(ProcessedString[i])) {
                arrayListSource.add(ProcessedString[i]);
            }
        }
        HashMap<String,BinTreeNode> map = new HashMap<String,BinTreeNode>();
        for(int i=0;i<arrayListSource.size();i++) {
            map.put(arrayListSource.get(i),new BinTreeNode(arrayListSource.get(i)));
        }
        String[] processingSourceStringArray = source.split(",");
        //构造含有一棵完整二叉树的HashMap
        for(int i=0;i<processingSourceStringArray.length;i++) {
            //if左孩子结点else右孩子结点
            if(processingSourceStringArray[i].matches(".\\+>.")) {
                processingSourceStringArray[i] = processingSourceStringArray[i].replaceAll("\\+>",",");
                String[] processedSourceStringArray = processingSourceStringArray[i].split(",");
                map.get(processedSourceStringArray[1]).setLChild(map.get(processedSourceStringArray[0]));
            } else {
                processingSourceStringArray[i] = processingSourceStringArray[i].replaceAll("\\->",",");
                String[] processedSourceStringArray = processingSourceStringArray[i].split(",");
                map.get(processedSourceStringArray[1]).setRChild(map.get(processedSourceStringArray[0]));
            }
        }
        return map;
    }
//    //有问题
//    public static BinTreeNode findRootNode(BinTreeNode anyNode) {
//
//    }
}
