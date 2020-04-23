object First
{
    def main(args: Array[String])
    {
          val first = Array.ofDim[Int](1000,1000)
          val second = Array.ofDim[Int](1000,1000)
          val result = Array.ofDim[Int](1000,1000)

          val num = scala.util.Random
          val n = 1000

          // use var for mutable variables
          var averageTime: Double = 0.0
          var totalTime: Double = 0.0
          var timeDifference: Double = 0.0
          var iteration = 1


          /*first(0)(0) = 2
          first(0)(1) = 7
          first(1)(0) = 3
          first(1)(1) = 4*/

          // Initializing elements of the first array
          for(i <- 0 to first.length - 1)
          {
                for(j <- 0 to first(0).length - 1)
                {
                    first(i)(j) = num.nextInt(20)
                }
          }

          print("...First Array...")
          println()
          for(i <- 0 to first.length - 1)
          {
                for(j <- 0 to first(0).length - 1)
                {
                    print(" " + first(i)(j))
                }
                println()
          }


          // Initializing elements of the second array
          for(i <- 0 to second.length - 1)
          {
                for(j <- 0 to second(0).length - 1)
                {
                    second(i)(j) = num.nextInt(20)
                }
          }

          println()
          print("...Second Array...")
          println()

          for(i <- 0 to second.length - 1)
          {
                for(j <- 0 to second(0).length - 1)
                {
                    print(" " + second(i)(j))
                }
                println()
          }





          // matrix multiplication
          // repeat multiplication n times
          for(t <- 0 to n - 1)
          {
                val startTime = System.currentTimeMillis()

                for(i <- 0 to (first.length - 1))
                {
                    for(j <- 0 to (second(0).length - 1))
                    {
                        for(k <- 0 to (second.length - 1))
                        {
                            result(i)(j) = result(i)(j) + first(i)(k) * second(k)(j)
                        }
                    }
                }
                val endTime = System.currentTimeMillis()


                println(iteration + " Elapsed time: " + (endTime - startTime) + " ms")
                iteration = iteration + 1

                timeDifference = endTime - startTime
                totalTime = totalTime + timeDifference
          }

          println("TOTAL TIME = " + totalTime + " ms")
          averageTime = totalTime / n

          println("AVERAGE TIME = " +  averageTime + " ms")



          println()
          print("...Result Array...")
          println()

          for(i <- 0 to result.length - 1)
          {
                for(j <- 0 to result(0).length - 1)
                {
                    print(" " + result(i)(j))
                }
                println()
          }


    }
}
