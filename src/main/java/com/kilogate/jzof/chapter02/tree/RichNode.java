package com.kilogate.jzof.chapter02.tree;

/**
 * RichNode
 *
 * @author fengquanwei
 * @create 2020/2/27 下午9:31
 **/
public class RichNode {
    private String data;
    private RichNode parent;
    private RichNode left;
    private RichNode right;

    public RichNode() {
    }

    public RichNode(String data) {
        this.data = data;
    }

    public void setNodes(RichNode parent, RichNode left, RichNode right) {
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    public String getData() {
        return data;
    }

    public RichNode getParent() {
        return parent;
    }

    public RichNode getLeft() {
        return left;
    }

    public RichNode getRight() {
        return right;
    }
}
