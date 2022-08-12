package main.helper

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalamock.scalatest.MockFactory
import scala.collection.mutable.LinkedHashMap
import main.model.Location

class LocationHelperTest extends AnyWordSpec with Matchers with MockFactory {
  "A LocationHelper" should {
    "flatten a hash map of locations into a list of FlatLocation" which {
      "Takes our keys and restructures them into a flat list which contains them" in {
        val mockLocation1 = new Location(0, "Testville")
        val mockLocation2 = new Location(1, "Test Town")
        val mockLocation3 = new Location(2, "Testington")
        val mockLocation4 = new Location(3, "Der Test")
        val mockLocation5 = new Location(4, "Das Test")

        val mockFlatLocation1 = new FlatLocation(0, "Testville", "NA", "US")
        val mockFlatLocation2 = new FlatLocation(1, "Test Town", "NA", "CA")
        val mockFlatLocation3 = new FlatLocation(2, "Testington", "EU", "UK")
        val mockFlatLocation4 = new FlatLocation(3, "Der Test", "EU", "DE")
        val mockFlatLocation5 = new FlatLocation(4, "Das Test", "EU", "DE")

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

        val subject = LocationHelper

        subject.flattenLocations(
          mockLocations
        ) should contain allOf (mockFlatLocation1,
        mockFlatLocation2,
        mockFlatLocation3,
        mockFlatLocation4,
        mockFlatLocation5)
      }
    }
  }
}
