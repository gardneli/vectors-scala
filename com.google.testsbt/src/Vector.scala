/**
  * Created by Lee on 8/27/2016.
 Refactor to remove .get from Options
  Use either getOrElse or foreach
  See http://alvinalexander.com/scala/best-practice-option-some-none-pattern-scala-idioms
  */
class Vector(coords: List[Double]) {
  val coordinates = coords
  val dimension = coords.length

  override def toString: String = s"Vector: {$coordinates}"

  override def equals(obj: scala.Any): Boolean =
    obj match {
      case obj: Vector =>
        obj.coordinates == this.coordinates
      case _ => false
    }

  def +(that: Vector): Option[Vector] =
    if (that.dimension == this.dimension) {
      Some(new Vector((this.coordinates, that.coordinates).zipped.map(_ + _)))
    } else None

  def -(that: Vector): Option[Vector] =
    if (that.dimension == this.dimension) {
      Some(new Vector((this.coordinates, that.coordinates).zipped.map(_ - _)))
    } else None

  def *(that: Double): Vector = {
    new Vector(this.coordinates.map(_ * that))
  }

  def *(that: Vector): Option[Double] =
    if (that.dimension == this.dimension) {
      Some((this.coordinates, that.coordinates).zipped.map(_ * _).foldLeft(0.0)(_ + _))
    } else None

  def getAngleToVectorInRadians(that: Vector): Option[Double] =
    if (that.dimension == this.dimension) {
      Some(scala.math.acos((this * that).get / (this.l2Norm() * that.l2Norm())))
    } else None

  def getAngleToVectorInDegrees(that: Vector): Option[Double] =
    Some(this.getAngleToVectorInRadians(that).get.toDegrees)

  def l2Norm(): Double = {
    scala.math.sqrt(this.coordinates.foldLeft(0.0)(_ + scala.math.pow(_, 2)))
  }

  def normalize(): Vector = this * (1 / this.l2Norm())

  def isOrthogonalTo(that: Vector): Boolean = {
    if (this.isZeroVector() || that.isZeroVector())
      true
    else
      scala.math.round(this.getAngleToVectorInDegrees(that).get) == 90 || scala.math.round(this.getAngleToVectorInDegrees(that).get) == 270
  }

  def isParallelTo(that: Vector): Boolean = {
    if (this.isZeroVector() || that.isZeroVector())
      true
    else
      scala.math.round(this.getAngleToVectorInDegrees(that).get) == 0 || scala.math.round(this.getAngleToVectorInDegrees(that).get) == 180
  }

  def isZeroVector() = this.l2Norm() == 0

  def parallelComponentToVector(that: Vector): Option[Vector] = {
    if (this.isZeroVector() || that.isZeroVector())
      None
    else
      Some(that.normalize() * (this * that.normalize()).get)
  }

  def orthogonalComponentToVector(that: Vector): Option[Vector] = {
    if (this.isZeroVector() || that.isZeroVector())
      None
    else
      Some((this - this.parallelComponentToVector(that).get).get)
  }

  def crossProduct(that: Vector): Option[Vector] = {
    if(this.dimension == 3 && this.dimension == that.dimension) {
      if (this.isZeroVector() || that.isZeroVector())
        Some(new Vector(List(0)))
      else
        Some(new Vector(List(
          this.coordinates(1) * that.coordinates(2) - that.coordinates(1) * this.coordinates(2),
          -1 * (this.coordinates(0) * that.coordinates(2) - that.coordinates(0) * this.coordinates(2)),
          this.coordinates(0) * that.coordinates(1) - that.coordinates(0) * this.coordinates(1)
        )))
    } else None
  }

  def areaOfParallelogram(that: Vector): Option[Double] = {
    val product = this.crossProduct(that)
    if(product.isEmpty)
      None
    else
      Some(product.get.l2Norm())
  }

  def areaOfTriangle(that: Vector): Option[Double] = {
    val parallelogram = this.areaOfParallelogram(that)
    if(parallelogram.isEmpty)
      None
    else
      Some(parallelogram.get * 0.5)
  }

}
