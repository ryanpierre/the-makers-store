package main.helper

import scala.collection.mutable.LinkedHashMap
import main.model.Location

case class FlatLocation(
    id: Int,
    name: String,
    continent: String,
    region: String
)

trait LocationHelperBase {
  def getContinentFromLocation(
      locations: LinkedHashMap[String, LinkedHashMap[String, Seq[Location]]],
      location: String
  ): String
}

object LocationHelper extends LocationHelperBase {
  private def foldRegionToFlatLocation(
      flatLocs: Seq[FlatLocation],
      locationMap: (String, Seq[Location]),
      continent: String
  ) = {

    val (region, locations) = locationMap

    flatLocs ++ locations.map(l => {
      FlatLocation(l.id, l.name, continent, region)
    })

  }

  private def mapToFlatLocation(
      regionMap: (String, LinkedHashMap[String, Seq[Location]])
  ): Seq[FlatLocation] = {
    val (continent, value) = regionMap

    value.foldLeft(Seq[FlatLocation]())(
      foldRegionToFlatLocation(_, _, continent)
    )
  }

  private def flattenLocations(
      locations: LinkedHashMap[String, LinkedHashMap[String, Seq[Location]]]
  ): List[FlatLocation] = {
    locations.map(mapToFlatLocation).toList.flatten
  }

  def getContinentFromLocation(
      locations: LinkedHashMap[String, LinkedHashMap[String, Seq[Location]]],
      location: String
  ): String = {
    val flatLocations = LocationHelper.flattenLocations(locations)
    val flatLocation =
      LocationHelper.flattenLocations(locations).find(fl => fl.name == location)

    flatLocation match {
      case None     => throw new Exception("Specified location does not exist")
      case Some(fl) => fl.continent
    }
  }
}
