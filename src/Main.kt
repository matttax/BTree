import java.io.BufferedReader
import java.io.File

fun main(args: Array<String>) {
    if (args.size == 1) {
        val bufferedReader: BufferedReader = File(args[0]).bufferedReader()
        val inputCollection = bufferedReader.use { it.readText() }.split(" ").map { it.toInt() }.toMutableList()
        val b = BTree(inputCollection.first())
        inputCollection -= inputCollection.first()
        for (n in inputCollection)
            b.insert(n)
        b.print()
    }
    else {
        randomBTree().print()
    }
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