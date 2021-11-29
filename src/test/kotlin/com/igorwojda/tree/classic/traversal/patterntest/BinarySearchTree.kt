package com.igorwojda.tree.classic.traversal.patterntest

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

    interface Strategy<E : Comparable<E>> {
        val directionList: List<Direction>
        fun order(node: BinaryNode<E>?, recursiveCall: (BinaryNode<E>?) -> List<E>): List<E> {
            if (directionList.size != 3) {
                return listOf()
            }
            return run {
                val list = mutableListOf<E>()
                for (direction in directionList) {
                    when (direction) {
                        Direction.CURRENT -> node?.data?.let {
                            list.add(it)
                        }
                        Direction.LEFT -> node?.left?.let {
                            list.addAll(recursiveCall(it))
                        }
                        Direction.RIGHT -> node?.right?.let {
                            list.addAll(recursiveCall(it))
                        }
                    }
                }
                list
            }
        }
    }

    enum class Direction {
        LEFT,
        RIGHT,
        CURRENT
    }

    inner class TraverseWithStrategy(val strategy: Strategy<E>) {
        fun traverse(node: BinaryNode<E>? = null): List<E> {
            if (node == null) {
                return if (root == null) {
                    listOf()
                } else {
                    strategy.order(root, ::traverse)
                }
            }
            return strategy.order(node, ::traverse)
        }
    }

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

    fun traverseDepthFirstPreOrder(): List<E> {
        val traverse = TraverseWithStrategy(object : Strategy<E> {
            override val directionList: List<Direction>
                get() = listOf(Direction.CURRENT, Direction.LEFT, Direction.RIGHT)
        })
        return traverse.traverse()
    }

    fun traverseDepthFirstInOrder(): List<E> {
        val traverse = TraverseWithStrategy(object : Strategy<E> {
            override val directionList: List<Direction>
                get() = listOf(Direction.LEFT, Direction.CURRENT, Direction.RIGHT)
        })
        return traverse.traverse()
    }

    fun traverseDepthFirstPostOrder(): List<E> {
        val traverse = TraverseWithStrategy(object : Strategy<E> {
            override val directionList: List<Direction>
                get() = listOf(Direction.LEFT, Direction.RIGHT, Direction.CURRENT)
        })
        return traverse.traverse()
    }

    fun traverseDepthFirstPreOrderReversed(): List<E> {
        val traverse = TraverseWithStrategy(object : Strategy<E> {
            override val directionList: List<Direction>
                get() = listOf(Direction.CURRENT, Direction.RIGHT, Direction.LEFT)
        })
        return traverse.traverse()
    }

    fun traverseDepthFirstInOrderReversed(): List<E> {
        val traverse = TraverseWithStrategy(object : Strategy<E> {
            override val directionList: List<Direction>
                get() = listOf(Direction.RIGHT, Direction.CURRENT, Direction.LEFT)
        })
        return traverse.traverse()
    }

    fun traverseDepthFirstPostOrderReverse(): List<E> {
        val traverse = TraverseWithStrategy(object : Strategy<E> {
            override val directionList: List<Direction>
                get() = listOf(Direction.RIGHT, Direction.LEFT, Direction.CURRENT)
        })
        return traverse.traverse()
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
