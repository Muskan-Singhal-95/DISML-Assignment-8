
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf


object Multiply
{
    def main(args: Array[String])
    {
        //val conf = new SparkConf().setAppName("Multiply matrices")
        //val sc = new SparkContext(conf)

        // total time we want to repeat matrix multiplication
        val iteration = 1000

        // total time of performing matrix multiplication
        var total_time = 0.0

        // average time of performing matrix multiplication
        var average_time = 0


        // First Matrix
        val first_matrix = sc.textFile("data/m.txt").map(line => {
                           val row = line.split("\\s+");
                           (row(0).trim.toInt, row(1).trim.toInt, row(2).trim.toInt)
                           })


        // Second Matrix
        val second_matrix = sc.textFile("data/n.txt").map(line => {
                            val row = line.split("\\s+");
                            (row(0).trim.toInt, row(1).trim.toInt, row(2).trim.toInt)
                            })

        for(i <- 1 to iteration)
        {

            val start_time = System.currentTimeMillis()
            // Result matrix
            val multiply = first_matrix.map(first_matrix => (first_matrix._2, first_matrix)).
                       join(second_matrix.map(second_matrix => (second_matrix._1, second_matrix))).
                       map( {case (k, (matrix_m, matrix_n)) => ((matrix_m._1, matrix_n._2),(matrix_m._3 * matrix_n._3))
                       })

            val reduceValues = multiply.reduceByKey(_ + _)
            val end_time = System.currentTimeMillis()
            val time_diff = end_time - start_time

            println(i + " Execution time = " + time_diff + " ms")
            total_time = total_time + time_diff

            val matrix_multiplication_result = reduceValues.sortByKey()

            matrix_multiplication_result.collect.foreach(println)

            //Verify results
            val result = sc.textFile("data/res.txt").map(line => {
            val row = line.split("\\s+");
                      ((row(0).trim.toInt, row(1).trim.toInt), row(2).trim.toInt)})
                      .sortByKey()


            // checking the validation of matrix multiplication with the resultant file
            val count = matrix_multiplication_result.join(result).
                        filter( { case (k, (matrix_multiplication_result, result)) => matrix_multiplication_result == result})
                        .count

            println("Total elements in the result matrix = " + count)

        }

        println("TOTAL TIME = " + total_time + " ms")
        average_time = total_time.toInt / iteration

        println("AVERAGE TIME = " + average_time + " ms")
        sc.stop()
    }
}
