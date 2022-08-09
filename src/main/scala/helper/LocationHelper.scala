package main.helper

import scala.collection.mutable.LinkedHashMap
import main.model.Location

case class FlatLocation(id: Int, name: String, continent: String, region: String)

trait LocationHelperBase {
  def flattenLocations(locations: LinkedHashMap[String, LinkedHashMap[String, Seq[Location]]]): List[FlatLocation]
  def getContinentFromLocation(locations: LinkedHashMap[String, LinkedHashMap[String, Seq[Location]]], location: String): String
}

object LocationHelper extends LocationHelperBase {
   def flattenLocations(locations: LinkedHashMap[String, LinkedHashMap[String, Seq[Location]]]): List[FlatLocation] = {
    locations.map((sMapTuple) => {
      val (continent, value) = sMapTuple

      value.foldLeft(Seq[FlatLocation]())((flatLocs, lMapTuple) => {
        val (region, locations) = lMapTuple

        flatLocs ++ locations.map(l => {
          FlatLocation(l.id, l.name, continent, region)
        })
      })
    }).toList.flatten
  }

  def getContinentFromLocation(locations: LinkedHashMap[String, LinkedHashMap[String, Seq[Location]]], location: String): String = {
    val flatLocations = LocationHelper.flattenLocations(locations)
    val flatLocation = LocationHelper.flattenLocations(locations).find(fl => fl.name == location)
    
    flatLocation match {
      case None => throw new Exception("Specified location does not exist")
      case Some(fl) => fl.continent
    }
  }
}
