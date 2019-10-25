import models.*

fun main() {
    val matrix = Matrix.createMatrix(
        1 with 2 with 3 with 4 finishWith 5,
        5 with 6 finishWith 7
    )
    val matrix2 = Matrix.createMatrix(
        1 with 2 with 3 with 4 finishWith 5,
        5 with 6 finishWith 7
    )
    println((matrix + matrix2).data)
}
