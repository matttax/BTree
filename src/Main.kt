fun main(args: Array<String>) {
    if (args.isEmpty())
        randomBTree().print()
}

private fun randomBTree() : BTree {
    val deg = (2..7).random()
    val b = BTree(deg)
    val used: MutableSet<Int> = mutableSetOf()
    repeat((1..100).random()) {
        val nextNumber = (-100..100).random()
        if (!used.contains(nextNumber)) {
            b.insert(nextNumber)
            used += nextNumber
        }
    }
    println("Elements: ${used.size}\nDegree: $deg\n")
    return b
}