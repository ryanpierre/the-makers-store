package main.helper

import scala.collection.mutable.LinkedHashMap
import main.model.Location

case class FlatLocation(id: Int, name: String, continent: String, region: String)

object LocationHelper {
  def flattenLocations(locations: LinkedHashMap[String, LinkedHashMap[String, Seq[Location]]]): List[FlatLocation] = {
    locations.foldLeft(List[FlatLocation]())((outputList, hMapOfRegion) => {
      val (continent, regionMap) = hMapOfRegion
      outputList ++ regionMap.foldLeft(List[FlatLocation]())((subList, hMapOfLocation) => {
        val (region, locationSeq) = hMapOfLocation
        subList ++ locationSeq.map(l => {
          FlatLocation(l.id, l.name, continent, region)
        }).toList
      })
    })
  }

  def getContinentFromLocation(locations: LinkedHashMap[String, LinkedHashMap[String, Seq[Location]]], location: String): String = {
    val flattened = LocationHelper.flattenLocations(locations)

    flattened.find(fl => fl.name == location) match {
      case None => throw new Exception("That city doesnt exist!")
      case Some(foundLocation) => foundLocation.continent
    }
  }
}