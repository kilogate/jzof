package com.kilogate.jzof.chapter02.tree;

/**
 * 二叉树的下一个节点
 * <p>
 * 给定一棵二叉树和其中的一个节点，如何找出中序遍历序列的下一个节点？树中的节点除了有左右子树节点的指针还有父节点的指针。
 *
 * @author fengquanwei
 * @create 2020/2/27 下午9:33
 **/
public class FindTheNextNode {
    public static RichNode findTheNextNode(RichNode node) {
        if (node == null) {
            return null;
        }

        // case 1 有右子树：下一个节点是右子树的最左子树
        if (node.getRight() != null) {
            RichNode result = node.getRight();

            while (result.getLeft() != null) {
                result = result.getLeft();
            }

            return result;
        }

        // case 2 是左子树：下一个节点是父节点
        if (node.getParent() != null && node == node.getParent().getLeft()) {
            return node.getParent();
        }

        // case 3 是右子树：向上遍历父节点，下一个节点是第一个作为左子树的父节点
        RichNode parent = node.getParent();

        while (parent != null && parent.getParent() != null) {
            if (parent == parent.getParent().getLeft()) {
                return parent.getParent();
            } else {
                parent = parent.getParent();
            }
        }

        return null;
    }

    public static void main(String[] args) {
        RichNode a = new RichNode("a");
        RichNode b = new RichNode("b");
        RichNode c = new RichNode("c");
        RichNode d = new RichNode("d");
        RichNode e = new RichNode("e");
        RichNode f = new RichNode("f");
        RichNode g = new RichNode("g");
        RichNode h = new RichNode("h");
        RichNode i = new RichNode("i");

        a.setNodes(null, b, c);
        b.setNodes(a, d, e);
        c.setNodes(a, f, g);
        d.setNodes(b, null, null);
        e.setNodes(b, h, i);
        f.setNodes(c, null, null);
        g.setNodes(c, null, null);
        h.setNodes(e, null, null);
        i.setNodes(e, null, null);

        System.out.println(String.format("%s next node is: %s", a.getData(), findTheNextNode(a) == null ? null : findTheNextNode(a).getData()));
        System.out.println(String.format("%s next node is: %s", b.getData(), findTheNextNode(b) == null ? null : findTheNextNode(b).getData()));
        System.out.println(String.format("%s next node is: %s", c.getData(), findTheNextNode(c) == null ? null : findTheNextNode(c).getData()));
        System.out.println(String.format("%s next node is: %s", d.getData(), findTheNextNode(d) == null ? null : findTheNextNode(d).getData()));
        System.out.println(String.format("%s next node is: %s", e.getData(), findTheNextNode(e) == null ? null : findTheNextNode(e).getData()));
        System.out.println(String.format("%s next node is: %s", f.getData(), findTheNextNode(f) == null ? null : findTheNextNode(f).getData()));
        System.out.println(String.format("%s next node is: %s", g.getData(), findTheNextNode(g) == null ? null : findTheNextNode(g).getData()));
        System.out.println(String.format("%s next node is: %s", h.getData(), findTheNextNode(h) == null ? null : findTheNextNode(h).getData()));
        System.out.println(String.format("%s next node is: %s", i.getData(), findTheNextNode(i) == null ? null : findTheNextNode(i).getData()));
    }
}
