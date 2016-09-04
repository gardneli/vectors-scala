/**
  * Created by Lee on 9/3/2016.
  */

object Main extends App {
  println("Testing lines and vectors!")

  /*
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
  */

  val p1 = new Plane(List(-0.412, 3.806, 0.728), -3.46)
  val p2 = new Plane(List(1.03, -9.515, -1.82), 8.65)
  val p3 = new Plane(List(2.611, 5.528, 0.283), 4.6)
  val p4 = new Plane(List(7.715, 8.306, 5.342), 3.76)
  val p5 = new Plane(List(-7.926, 8.625, -7.217), -7.952)
  val p6 = new Plane(List(-2.642, 2.875, -2.404), -2.443)

  println("Parallel: " + p1.isPlaneParallel(p2))
  println("Equal: " + p1.equals(p2))
  println("Parallel: " + p3.isPlaneParallel(p4))
  println("Equal: " + p3.equals(p4))
  println("Parallel: " + p5.isPlaneParallel(p6))
  println("Equal: " + p5.equals(p6))

}
