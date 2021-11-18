package com.igorwojda.tree.classic.traversal

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

private class BinarySearchTree<E : Comparable<E>> {
    var root: BinaryNode<E>? = null
        private set

    fun add(element: E) {
        val newNode = BinaryNode(element)

        if (root == null) {
            root = newNode
            return
        }

        var current: BinaryNode<E> = root ?: return

        while (true) {
            when {
                current.data == element -> {
                    return
                }
                element < current.data -> {
                    if (current.left == null) {
                        current.left = newNode
                        return
                    }

                    current.left?.let { current = it }
                }
                element > current.data -> {
                    if (current.right == null) {
                        current.right = newNode
                        return
                    }

                    current.right?.let { current = it }
                }
            }
        }
    }

    fun contains(element: E): Boolean {
        var current = root

        while (true) {
            if (current == null) {
                break
            } else if (current.data == element) {
                return true
            } else if (element < current.data) {
                current = current.left
            } else if (element > current.data) {
                current = current.right
            }
        }

        return false
    }

    fun isEmpty() = root == null


    // Using queue, as intended by the creator
    fun traverseBreadthFirst(): List<E> {
        if (root == null) {
            return listOf()
        } else {
            val queue = Queue<BinaryNode<E>>()
            val visited = mutableListOf<E>()
            root?.data?.let {
                visited.add(it)
            }
            root?.left?.let {
                queue.add(it)
                visited.add(it.data)
            }
            root?.right?.let {
                queue.add(it)
                visited.add(it.data)
            }
            while (queue.isNotEmpty()) {
                val node = queue.remove()
                node?.left?.let {
                    queue.add(it)
                    visited.add(it.data)
                }
                node?.right?.let {
                    queue.add(it)
                    visited.add(it.data)
                }
            }
            return visited
        }
    }
    // First, recursive (over levels) version
//    fun traverseBreadthFirst(currentList: List<BinaryNode<E>>? = null): List<E> {
//        if (currentList == null) {
//            if (root == null) {
//                return listOf()
//            }
//            root?.let {
//                return mutableListOf(it.data).apply {
//                    val nextLevel = listOfNotNull(root?.left, root?.right)
//                    this.addAll(traverseBreathFirst(nextLevel))
//                }
//            }
//        }
//        if (currentList.isNullOrEmpty()) {
//            return listOf()
//        }
//        val nextLevelList = mutableListOf<BinaryNode<E>?>()
//        val resultList = mutableListOf<E>()
//        currentList.forEach {
//            resultList.add(it.data)
//            nextLevelList.add(it.left)
//            nextLevelList.add(it.right)
//        }
//        return resultList.apply {
//            this.addAll(traverseBreathFirst(nextLevelList.filterNotNull()))
//        }
//
//    }

    fun traverseDepthFirstPreOrder(node: BinaryNode<E>? = null): List<E> {
        if (node == null) {
            if (root == null) {
                return listOf()
            } else {
                return run {
                    val list = mutableListOf<E>()
                    root?.data?.let {
                        list.add(it)
                    }
                    root?.left?.let {
                        list.addAll(traverseDepthFirstPreOrder(it))
                    }
                    root?.right?.let {
                        list.addAll(traverseDepthFirstPreOrder(it))
                    }
                    list
                }
            }
        }
        return run {
            val list = mutableListOf<E>()
            list.add(node.data)
            node.left?.let {
                list.addAll(traverseDepthFirstPreOrder(it))
            }
            node.right?.let {
                list.addAll(traverseDepthFirstPreOrder(it))
            }
            list
        }
    }

    fun traverseDepthFirstInOrder(node: BinaryNode<E>? = null): List<E> {
        if (node == null) {
            if (root == null) {
                return listOf()
            } else {
                return run {
                    val list = mutableListOf<E>()
                    root?.left?.let {
                        list.addAll(traverseDepthFirstInOrder(it))
                    }
                    root?.data?.let {
                        list.add(it)
                    }
                    root?.right?.let {
                        list.addAll(traverseDepthFirstInOrder(it))
                    }
                    list
                }
            }
        }
        return run {
            val list = mutableListOf<E>()
            node.left?.let {
                list.addAll(traverseDepthFirstInOrder(it))
            }
            list.add(node.data)
            node.right?.let {
                list.addAll(traverseDepthFirstInOrder(it))
            }
            list
        }
    }

    fun traverseDepthFirstPostOrder(node: BinaryNode<E>? = null): List<E> {
        if (node == null) {
            if (root == null) {
                return listOf()
            } else {
                return run {
                    val list = mutableListOf<E>()
                    root?.left?.let {
                        list.addAll(traverseDepthFirstPostOrder(it))
                    }
                    root?.right?.let {
                        list.addAll(traverseDepthFirstPostOrder(it))
                    }
                    root?.data?.let {
                        list.add(it)
                    }
                    list
                }
            }
        }
        return run {
            val list = mutableListOf<E>()
            node.left?.let {
                list.addAll(traverseDepthFirstPostOrder(it))
            }
            node.right?.let {
                list.addAll(traverseDepthFirstPostOrder(it))
            }
            list.add(node.data)
            list
        }
    }

    fun traverseDepthFirstPreOrderReversed(node: BinaryNode<E>? = null): List<E> {
        if (node == null) {
            if (root == null) {
                return listOf()
            } else {
                return run {
                    val list = mutableListOf<E>()
                    root?.data?.let {
                        list.add(it)
                    }
                    root?.right?.let {
                        list.addAll(traverseDepthFirstPreOrderReversed(it))
                    }
                    root?.left?.let {
                        list.addAll(traverseDepthFirstPreOrderReversed(it))
                    }
                    list
                }
            }
        }
        return run {
            val list = mutableListOf<E>()
            list.add(node.data)
            node.right?.let {
                list.addAll(traverseDepthFirstPreOrderReversed(it))
            }
            node.left?.let {
                list.addAll(traverseDepthFirstPreOrderReversed(it))
            }
            list
        }
    }

    fun traverseDepthFirstInOrderReversed(node: BinaryNode<E>? = null): List<E> {
        if (node == null) {
            if (root == null) {
                return listOf()
            } else {
                return run {
                    val list = mutableListOf<E>()
                    root?.right?.let {
                        list.addAll(traverseDepthFirstInOrderReversed(it))
                    }
                    root?.data?.let {
                        list.add(it)
                    }
                    root?.left?.let {
                        list.addAll(traverseDepthFirstInOrderReversed(it))
                    }
                    list
                }
            }
        }
        return run {
            val list = mutableListOf<E>()
            node.right?.let {
                list.addAll(traverseDepthFirstInOrderReversed(it))
            }
            list.add(node.data)
            node.left?.let {
                list.addAll(traverseDepthFirstInOrderReversed(it))
            }
            list
        }
    }

    fun traverseDepthFirstPostOrderReverse(node: BinaryNode<E>? = null): List<E> {
        if (node == null) {
            if (root == null) {
                return listOf()
            } else {
                return run {
                    val list = mutableListOf<E>()
                    root?.right?.let {
                        list.addAll(traverseDepthFirstPostOrderReverse(it))
                    }
                    root?.left?.let {
                        list.addAll(traverseDepthFirstPostOrderReverse(it))
                    }
                    root?.data?.let {
                        list.add(it)
                    }
                    list
                }
            }
        }
        return run {
            val list = mutableListOf<E>()
            node.right?.let {
                list.addAll(traverseDepthFirstPostOrderReverse(it))
            }
            node.left?.let {
                list.addAll(traverseDepthFirstPostOrderReverse(it))
            }
            list.add(node.data)
            list
        }
    }
}

private data class BinaryNode<E : Comparable<E>>(
    val data: E,
    var left: BinaryNode<E>? = null,
    var right: BinaryNode<E>? = null
)

/*
We can use queue as helper class to implement breadth first traversal. This is not most optimal queue implementation,
however it's enough for this task. Check "Queue puzzle" solution for more details and more efficient queue
implementation.
*/
private class Queue<E> {
    private val list = mutableListOf<E>()

    fun add(element: E) {
        list.add(element)
    }

    fun remove() = if (list.isEmpty()) null else list.removeAt(0)

    fun peek() = list.firstOrNull()

    fun isEmpty() = list.isEmpty()

    fun isNotEmpty() = list.isNotEmpty()

    val size get() = list.size
}

private class Test {
    @Test
    fun `traverse breadth first`() {
        getTree().traverseBreadthFirst() shouldBeEqualTo listOf(
            'F',
            'B',
            'G',
            'A',
            'D',
            'I',
            'C',
            'E',
            'H'
        )
    }

    @Test
    fun `traverse depth first pre order`() {
        getTree().traverseDepthFirstPreOrder() shouldBeEqualTo listOf(
            'F',
            'B',
            'A',
            'D',
            'C',
            'E',
            'G',
            'I',
            'H'
        )
    }

    @Test
    fun `traverse depth first in order`() {
        getTree().traverseDepthFirstInOrder() shouldBeEqualTo listOf(
            'A',
            'B',
            'C',
            'D',
            'E',
            'F',
            'G',
            'H',
            'I'
        )
    }

    @Test
    fun `traverse depth first post order`() {
        getTree().traverseDepthFirstPostOrder() shouldBeEqualTo listOf(
            'A',
            'C',
            'E',
            'D',
            'B',
            'H',
            'I',
            'G',
            'F'
        )
    }

    @Test
    fun `traverse depth first pre order reversed`() {
        getTree().traverseDepthFirstPreOrderReversed() shouldBeEqualTo listOf(
            'F',
            'G',
            'I',
            'H',
            'B',
            'D',
            'E',
            'C',
            'A'
        )
    }

    @Test
    fun `traverse depth first in order reversed`() {
        getTree().traverseDepthFirstInOrderReversed() shouldBeEqualTo listOf(
            'I',
            'H',
            'G',
            'F',
            'E',
            'D',
            'C',
            'B',
            'A'
        )
    }

    @Test
    fun `traverse depth first post order reverse`() {
        getTree().traverseDepthFirstPostOrderReverse() shouldBeEqualTo listOf(
            'H',
            'I',
            'G',
            'E',
            'C',
            'D',
            'A',
            'B',
            'F'
        )
    }

    // ---------Tree------------
    //
    //           F
    //         /   \
    //        B     G
    //       / \     \
    //      A   D     I
    //         / \   /
    //        C   E H
    //
    // --------------------------
    private fun getTree() = BinarySearchTree<Char>().apply {
        add('F')
        add('B')
        add('A')
        add('D')
        add('C')
        add('E')
        add('G')
        add('I')
        add('H')
    }
}
