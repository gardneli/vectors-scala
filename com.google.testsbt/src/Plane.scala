/**
  * Created by gardneli on 9/3/16.
  */
class Plane (nv: List[Double], ct: Double) {
  val threshold = 1e-10
  val normalVector = new Vector(nv)
  val dimension = normalVector.dimension
  val constantTerm = ct

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

  def isPlaneParallel(that: Plane): Boolean = {
    this.normalVector.isParallelTo(that.normalVector)
  }

  override def equals(obj: scala.Any): Boolean = {
    obj match {
      case obj: Plane => {
        if (isPlaneParallel(obj)) {
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

}
