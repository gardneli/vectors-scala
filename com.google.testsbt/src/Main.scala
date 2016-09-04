/**
  * Created by Lee on 9/3/2016.
  */

object Main extends App {
  println("Testing lines and vectors!")

  val l1 = new Line(List(4.046, 2.836), 1.21)
  val l2 = new Line(List(10.115, 7.09), 3.025)

  val l3 = new Line(List(7.204, 3.182), 8.68)
  val l4 = new Line(List(8.172, 4.114), 9.883)

  val l5 = new Line(List(1.182, 5.562), 6.744)
  val l6 = new Line(List(1.773, 8.343), 9.525)

  println("Intersection: " + l1.intersectionPointWithLine(l2))
  println("Lines are parallel: " + l1.isLineParallel(l2))
  println("Lines are equal: " + l1.equals(l2))
  println("Intersection: " + l3.intersectionPointWithLine(l4))
  println("Lines are parallel: " + l3.isLineParallel(l4))
  println("Intersection: " + l5.intersectionPointWithLine(l6))
  println("Lines are parallel: " + l5.isLineParallel(l6))
  println("Lines are equal: " + l5.equals(l6))

}
