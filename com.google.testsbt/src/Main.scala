/**
  * Created by Lee on 9/3/2016.
  */


object Main extends App {
  println("Testing systems of linear equations!")

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
  /*
  val p0 = new Plane(List(1, 2, 3), 5)
  val p1 = new Plane(List(-0.412, 3.806, 0.728), -3.46)
  val p2 = new Plane(List(1.03, -9.515, -1.82), 8.65)
  val p3 = new Plane(List(2.611, 5.528, 0.283), 4.6)
  val p4 = new Plane(List(7.715, 8.306, 5.342), 3.76)
  val p5 = new Plane(List(-7.926, 8.625, -7.217), -7.952)
  val p6 = new Plane(List(-2.642, 2.875, -2.404), -2.443)

  val s1 = new LinearSystem(List(p1, p2, p3))

  println(s1)
  println(s1.length())
  println(s1.indicesOfFirstNonzeroTermsPerRow())

  println("\nTriangular form: \n")
  println(s1.computeTriangularForm())
  */

  val p1 = new Plane(List(1, 1, 1), 1)
  val p2 = new Plane(List(0, 1, 1), 2)

  val s1 = new LinearSystem(List(p1, p2))

  println("Initial system 1:\n" + s1)
  println("Triangular form 1:\n" + s1.computeTriangularForm())

  val p3 = new Plane(List(1, 1, 1), 1)
  val p4= new Plane(List(1, 1, 1), 2)

  val s2 = new LinearSystem(List(p3, p4))
  println("Initial system 2:\n" + s2)
  println("Triangular form 2:\n" + s2.computeTriangularForm())

  val p5 = new Plane(List(1, 1, 1), 1)
  val p6= new Plane(List(0, 1, 0), 2)
  val p7 = new Plane(List(1, 1, -2), 3)
  val p8 = new Plane(List(1, 0, -2), 2)

  val s3 = new LinearSystem(List(p5, p6, p7, p8))
  println("Initial system 3:\n" + s3)
  println("Triangular form 3:\n" + s3.computeTriangularForm())

  val p9 = new Plane(List(0, 1, 1), 1)
  val p10= new Plane(List(1, -1, 1), 2)
  val p11 = new Plane(List(1, 2, -5), 3)

  val s4 = new LinearSystem(List(p9, p10, p11))
  println("Initial system 4:\n" + s4)
  println("Triangular form 4:\n" + s4.computeTriangularForm())
}
