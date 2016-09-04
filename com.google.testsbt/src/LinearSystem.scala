/**
  * Created by gardneli on 9/4/16.
  */
class LinearSystem(eqns: List[Plane]) {
  var planes = eqns
  val dimension = planes(0).dimension
  val threshold = 1e-10

  // Check that the dimension of all the planes is the same
  require(planes.forall(p => p.dimension == this.dimension))

  def swapRows(row1: Int, row2: Int): Unit = {

  }

  def multiplyCoefficientAndRow(coefficient: Double, row: Int): Unit = {

  }

  def addCoefficientTimesRowToRow(coefficient: Double, row1: Int, row2: Int): Unit = {

  }

  def indicesOfFirstNonzeroTermsPerRow(): List[Option[(Int, Double)]] = {
    planes.map(_.firstNonzeroIndex())
  }

  override def toString: String = {
      val buf = new StringBuilder
      var i: Int = 0
      buf.append("Linear System: \n")
      while (i < this.length()) {
        buf.append("Equation " + i.toString + " : " + this.planes(i) + "\n")
        i += 1
      }
      buf.toString()
  }

  def length(): Int = {
    this.planes.length
  }

  def getItem(i: Int): Plane = {
    this.planes(i)
  }

  def setItem(i: Int, plane: Plane): Unit = {
    this.planes = this.planes.updated(i, plane)
  }

}
