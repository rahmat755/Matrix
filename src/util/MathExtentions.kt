package util

import errors.MatrixEmptyException
import kotlin.math.abs

fun List<Int>.closestTo(other: Int): Int {
    if (this.isEmpty()) throw MatrixEmptyException("Cannot use this on empty matrix")
    var lowestValue = this.first()
    var lowestValueDiff = abs(lowestValue - other)
    this.forEach {
        val diff = abs(it.minus(other))
        if (diff < lowestValueDiff) {
            lowestValueDiff = diff
            lowestValue = it
        }
    }
    return lowestValue
}

fun List<Int>.closestTo(other: Float): Int {
    if (this.isEmpty()) throw MatrixEmptyException("Cannot use this on empty matrix")
    var lowestValue = this.first()
    var lowestValueDiff = abs(lowestValue - other)
    this.forEach {
        val diff = abs(it.minus(other))
        if (diff < lowestValueDiff) {
            lowestValueDiff = diff
            lowestValue = it
        }
    }
    return lowestValue
}