/**
  * Created by gardneli on 9/3/16.
  */
// Ax + By = k1
// Cx + Dy = k2

class Line(nv: List[Double], ct: Double) {
  val threshold = 1e-10
  val normalVector = new Vector(nv)
  val dimension = normalVector.dimension
  val constantTerm = ct
  //val basepoint = new Vector(getBasepoint())

  override def toString: String = {
    val buf = new StringBuilder
    var i: Int = 0
    while (i < this.normalVector.dimension) {
      buf.append(this.normalVector.coordinates(i).toString + "X" + i.toString + " ")
      i += 1
    }
    buf.append("= " + constantTerm.toString)
    buf.toString()
  }

  def getBasepoint(): Vector = {
    var basepointCoords = List.fill[Double](this.dimension)(0)
    val nonZeroIndex = firstNonzeroIndex()
    nonZeroIndex.foreach { case (i, j) =>
      basepointCoords = basepointCoords.updated(i, this.constantTerm / j)
    }
    new Vector(basepointCoords)
  }

  def firstNonzeroIndex(): Option[(Int, Double)] = {
    var nonZeroIndex = None: Option[(Int, Double)]
    this.normalVector.coordinates.find(_ > threshold).foreach { i =>
      nonZeroIndex = Some((this.normalVector.coordinates.indexOf(i), i))
    }
    nonZeroIndex
  }

  def isLineParallel(that: Line): Boolean = {
    this.normalVector.isParallelTo(that.normalVector)
  }

  override def equals(obj: scala.Any): Boolean = {
    obj match {
      case obj: Line => {
        if (isLineParallel(obj)) {
          val v1 = this.getBasepoint() - obj.getBasepoint()
          if (v1.isDefined) {
            v1.get.isOrthogonalTo(this.normalVector)
          } else
            false
        } else
          false
      }
      case _ => false
    }
  }

  def intersectionPointWithLine(that: Line): Option[(Double, Double)] = {
    if (!isLineParallel(that)) {
      val determinant = this.normalVector.coordinates(0) * that.normalVector.coordinates(1) - this.normalVector.coordinates(1) * that.normalVector.coordinates(0)
      val x = (that.normalVector.coordinates(1) * this.constantTerm - this.normalVector.coordinates(1) * that.constantTerm) / determinant
      val y = (-1 * that.normalVector.coordinates(0) * this.constantTerm + this.normalVector.coordinates(0) * that.constantTerm) / determinant
      Some((x, y))
    } else
      None
  }
}