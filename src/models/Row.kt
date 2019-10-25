package models

import util.closestTo

data class Row(
    private var _data: List<Int>?
) {
    var data: List<Int>
        get() = _data ?: listOf()
        set(value) {
            if (_data != null) return
            else {
                _data = value
            }
        }

    fun average(): Int {
        var summ: Float = 0f
        data.forEach {
            summ += it
        }
        val average = summ / data.size
        return data.closestTo(average)
    }
}

infix fun Int.with(other: Int): List<Int> {
    return listOf(this, other)
}

infix fun List<Int>.with(value: Int): List<Int> {
    return this.plus(value)
}

infix fun List<Int>.finishWith(finalValue: Int): Row {
    return Row(this.plus(finalValue))
}