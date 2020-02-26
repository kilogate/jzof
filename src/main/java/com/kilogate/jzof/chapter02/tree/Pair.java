package com.kilogate.jzof.chapter02.tree;

/**
 * Pair
 *
 * @author fengquanwei
 * @create 2020/2/26 下午10:42
 **/
public class Pair<T> {
    private T left;
    private T right;

    public Pair() {
    }

    public Pair(T left, T right) {
        this.left = left;
        this.right = right;
    }

    public T getLeft() {
        return left;
    }

    public void setLeft(T left) {
        this.left = left;
    }

    public T getRight() {
        return right;
    }

    public void setRight(T right) {
        this.right = right;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Pair{");
        sb.append("left=").append(left);
        sb.append(", right=").append(right);
        sb.append('}');
        return sb.toString();
    }
}
