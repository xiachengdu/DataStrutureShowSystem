/**
 * Created by xcd on 2016/11/1.
 * 链式存储结构
 */
package com.core;

import com.interfaceall.Node;


public class BinTreeNode implements Node {
    private Object data;
    private BinTreeNode parent;
    private BinTreeNode lChild;
    private BinTreeNode rChild;
    private int height;
    private int size;
    public BinTreeNode() {
        this(null);
    }

    public BinTreeNode(Object e) {
        data = e;
        height = 0;
        size = 1;
        parent = lChild = rChild = null;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object obj) {
        data = obj;
    }
    //判断是否有父节点
    public boolean hasParent() {
        return parent != null;
    }
    public boolean hasLChild() {
        return lChild != null;
    }
    public boolean hasRChild() {
        return rChild != null;
    }
    public boolean isLeaf() {
        return (!hasLChild()) && (!hasRChild());
    }
    public boolean isLChild() {
        return hasParent() && this == parent.lChild;
    }
    public boolean isRChild() {
        return hasParent() && this == parent.rChild;
    }
    //取以当前节点为根的树的高度
    public int getHeight() {
        return height;
    }
    //更新当前节点及其祖先的高度
    public void updateHeight() {
        int newHeight = 0;
        if(hasLChild()) {
            newHeight = Math.max(newHeight,1 + getLChild().getHeight());
        }
        if(hasRChild()) {
            newHeight = Math.max(newHeight,1 + getRChild().getHeight());
        }
        if(newHeight == height) return;
        height = newHeight;
        if(hasParent()) getParent().updateHeight();
    }
    //取以当前节点为根的树的节点数
    public int getSize() {
        return size;
    }
    //更新当前节点及其祖先的子孙树
    public void updateSize() {
        size = 1;
        if(hasLChild()) {
            size += getLChild().getSize();
        }
        if(hasParent()) {
            getParent().updateSize();
        }
    }

    /**
     * 和父节点有关
     * @return
     */
    public BinTreeNode getParent() {
        return parent;
    }
//    public BinTreeNode setParent() {
//        BinTreeNode oldP = this.parent;
//
//    }
    //断开与父节点的关系
    public void server() {
        if(!hasParent()) return;
        if(isLChild()) parent.lChild = null;
        else  parent.rChild = null;
        parent.updateHeight();
        parent.updateSize();
        parent = null;
    }

    /**
     * 和左孩子节点有关
     * @return
     */

    public BinTreeNode getLChild() {
        return lChild;
    }
    //设置当前节点的左孩子，返回原左孩子
    public BinTreeNode setLChild(BinTreeNode lc) {
        BinTreeNode oldLC = this.lChild;
        if(hasLChild()) {
            lChild.server();
        }
        if(lc != null) {
            lc.server();
            this.lChild = lc;
            lc.parent = this;
            this.updateHeight();
            this.updateSize();
        }
        return oldLC;
    }

    /**
     * 和右孩子节点有关
     * @return
     */
    public BinTreeNode getRChild() {
        return rChild;
    }
    //设置当前节点的右孩子，返回原右孩子
    public BinTreeNode setRChild(BinTreeNode rc) {
        BinTreeNode oldRC = this.rChild;
        if(hasRChild()) {
            rChild.server();
        }
        if(rc != null) {
            rc.server();
            this.rChild = rc;
            rc.parent = this;
            this.updateHeight();
            this.updateSize();
        }
        return oldRC;
    }
}


