package models

import errors.MatrixEmptyException

class Matrix {
    private var _rows: Int = 0
    private var _columns: Int = 0
    private var _data: List<Row>? = null
    var data: List<Row>?
        set(value) {
            if (!_data.isNullOrEmpty())
                _data = value
            else return
        }
        get() = _data

    val rows: Int
        get() = _rows
    val columns: Int
        get() = _columns

    fun dumpData(): List<Row>? = data

    private constructor()

    private constructor(rows: Int, columns: Int, data: List<Row>? = null) : this() {
        this._rows = rows
        this._columns = columns
        this._data = data
    }

    companion object {
        fun createMatrix(vararg rowsTotal: Row): Matrix {
            if (rowsTotal.isNullOrEmpty()) throw MatrixEmptyException("No rows entered")
            val rowsCount = rowsTotal.size
            val maxColumnSize = rowsTotal.maxBy {
                it.data.size
            }?.data?.size ?: throw MatrixEmptyException("All entered rows was empty")
            val rowsList = rowsTotal.map {
                return@map if (it.data.size < maxColumnSize) {
                    var diff = maxColumnSize - it.data.size
                    var filledWithNullsData = it.data
                    while (diff != 0) {
                        diff -= 1
                        filledWithNullsData = filledWithNullsData.plus(0)
                    }
                    Row(filledWithNullsData)
                } else {
                    Row(it.data)
                }
            }
            return Matrix(rows = rowsCount, columns = maxColumnSize, data = rowsList)
        }
    }
}


fun Matrix.transpose(): Matrix {
    val rowsCount = this.columns
    val columns = this.rows
    val data = arrayListOf<Row>()
    for (row in 0 until rowsCount) {
        val rowData = arrayListOf<Int>()
        for (column in 0 until columns) {
            this.data?.get(column)?.let { rowData.add(it.data[row]) }
        }
        data.add(Row(rowData))
    }
    return Matrix.createMatrix(*data.toTypedArray())
}

infix operator fun Matrix.plus(other: Matrix): Matrix {
    val otherData = other.data
    if (otherData.isNullOrEmpty()) throw MatrixEmptyException("Cannot summ with empty or null matrix")
    if (this.data.isNullOrEmpty()) throw MatrixEmptyException("Cannot summ empty or null matrix")
    val resultData = arrayListOf<Row>()
    for (row in 0 until rows) {
        val rowData = arrayListOf<Int>()
        for (column in 0 until columns) {
            this.data?.get(row)?.let {
                rowData.add(it.data[column] + otherData.get(row).data[column])
            }
        }
        resultData.add(Row(rowData))
    }
    return Matrix.createMatrix(*resultData.toTypedArray())
}

