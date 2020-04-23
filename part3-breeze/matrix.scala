import breeze.linalg.{DenseMatrix, DenseVector}
import breeze.numerics.exp

object Matrix
{
  def main(args: Array[String])
  {
      var average_time = 0.0
      var total_time = 0.0
      val iteration = 1000

      val first_matrix = DenseMatrix.rand[Double](1000, 1000)
      val second_matrix = DenseMatrix.rand[Double](1000, 1000)

      for(i <- 1 to iteration)
      {
          val start_time = System.currentTimeMillis()
          var result = first_matrix * second_matrix
          val end_time = System.currentTimeMillis()
          val time_taken = end_time - start_time


          println()
          println(result)
          println("Iteration = " + i + ", Time taken = " + time_taken + " ms")
          println()
          total_time = total_time + time_taken
      }

      println()
      println("TOTAL TIME = " + total_time + " ms")
      average_time = total_time / iteration
      println("AVERAGE TIME = " + average_time + " ms")
  }
}
