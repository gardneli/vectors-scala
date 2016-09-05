/**
  * Created by gardneli on 9/4/16.
  */
class LinearSystem(eqns: List[Plane]) {
  var planes = eqns
  val dimension = planes(0).dimension
  val threshold = 1e-10

  // Check that the dimension of all the planes is the same
  require(planes.forall(p => p.dimension == this.dimension))

  //require(this.length() >= this.dimension)

  def swapRows(row1: Int, row2: Int): Unit = {
    val tmpPlane = this.planes(row1)
    this.planes = this.planes.updated(row1, this.planes(row2))
    this.planes = this.planes.updated(row2, tmpPlane)
  }

  def multiplyCoefficientAndRow(coefficient: Double, row: Int): Unit = {
    val tmpPlane = new Plane(this.planes(row).normalVector.coordinates.map(_ * coefficient),
      this.planes(row).constantTerm * coefficient)
    this.planes = this.planes.updated(row, tmpPlane)
  }

  def addCoefficientTimesRowToRow(coefficient: Double, row1: Int, row2: Int): Unit = {
    val tmpPlane = new Plane(this.planes(row1).normalVector.coordinates.map(_ * coefficient),
      this.planes(row1).constantTerm * coefficient)
    this.planes = this.planes.updated(row2, this.planes(row2) + tmpPlane)
  }

  def indicesOfFirstNonzeroTermsPerRow(): List[Option[(Int, Double)]] = {
    planes.map(_.firstNonzeroIndex())
  }

  def computeTriangularForm(): LinearSystem = {
    val newSystem = new LinearSystem(this.planes)
    // Rule 1: Swap with topmost nonzero coefficient
    // Rule 2: Don't multiply coefficient by row
    // Rule 3: Only add multiples of row to rows below

    // Get first row with nonzero first coefficient
    var i: Int = 0
    while (i < (newSystem.length())) {
      // Look for other row to swap with to obtain nonzero first coefficient
      if (newSystem.planes(i).firstNonzeroIndex().getOrElse(-1, -1)._1 > i) {
        var j: Int = i + 1
        while (j < newSystem.length()) {
          if (newSystem.planes(j).firstNonzeroIndex().getOrElse(-1, -1)._1 == i) {
            newSystem.swapRows(i, j)
            j = newSystem.length()
          } else {
            j += 1
          }
        }
      }
      // Need to clear all rows below in the same column
      var k: Int = i + 1
      while ((k + 1) <= newSystem.length()) {
        if (newSystem.planes(k).firstNonzeroIndex().getOrElse(-1, -1)._1 == i) {
          val multiplier = -1 * newSystem.planes(k).normalVector.coordinates(i) / newSystem.planes(i).normalVector.coordinates(i)
          newSystem.addCoefficientTimesRowToRow(multiplier, i, k)
        }
        k += 1
      }
      i += 1
    }
    newSystem
  }

  def computeRREF(): LinearSystem = {
    val triangularForm = this.computeTriangularForm()
    var i: Int = 0
    while (i < math.min(triangularForm.length(), triangularForm.dimension)) {
      if(triangularForm.planes(i).normalVector.coordinates(i) != 1 && triangularForm.planes(i).normalVector.coordinates(i) != 0){
        val multiplier = 1 / triangularForm.planes(i).normalVector.coordinates(i)
        triangularForm.multiplyCoefficientAndRow(multiplier, i)
      }
      i += 1
    }
    triangularForm
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
