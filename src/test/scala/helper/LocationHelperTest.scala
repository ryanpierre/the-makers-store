package main.helper

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalamock.scalatest.MockFactory
import main.controller.ItemController
import main.db.DbAdapterBase
import scala.collection.mutable.LinkedHashMap
import main.model.Location
import main.model.Item
import scala.collection.mutable.ArrayBuffer

class LocationHelperTest extends AnyWordSpec with Matchers with MockFactory {
  "A LocationHelper" should {
    "Retrieve what continent a particular city search is in" which {
      "Given a location, returns a continent where the location resides" in {
         val mockDbAdapter = mock[DbAdapterBase]
        val mockLocation1 = new Location(0, "Testville")
        val mockLocation2 = new Location(1, "Test Town")
        val mockLocation3 = new Location(2, "Testington")
        val mockLocation4 = new Location(3, "Der Test")
        val mockLocation5 = new Location(4, "Das Test")
        val mockLocations = LinkedHashMap(
          "NA" -> LinkedHashMap(
            "US" -> Seq(mockLocation1),
            "CA" -> Seq(mockLocation2)
          ),
          "EU" -> LinkedHashMap(
            "UK" -> Seq(mockLocation3),
            "DE" -> Seq(mockLocation4, mockLocation5)
          )
        )

        val mockItem1 = new Item(0, "TestAll", 5.00, 3, List("NA", "EU"))
        val mockItem2 = new Item(1, "TestEU", 4.00, 5, List("EU"))
        val mockItem3 = new Item(2, "TestNA", 3.00, 6, List("NA"))
        val mockItems =
          ArrayBuffer[Item]().appendAll(List(mockItem1, mockItem2, mockItem3))

        (mockDbAdapter.getLocations _)
          .expects()
          .returning(
            mockLocations
          )

        (mockDbAdapter.getItems _)
          .expects()
          .returning(
            mockItems
          )

        val subject = new ItemController(mockDbAdapter)
        val products = subject.getAvailableItemsByLocation("Testville")

        products shouldBe a[List[_]]
        products should contain only (mockItem1, mockItem3)
      }
    }
  }
}
