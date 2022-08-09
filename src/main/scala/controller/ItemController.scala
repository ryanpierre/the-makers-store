package main.controller

import main.model.Item
import main.db.DbAdapter
import scala.collection.mutable.LinkedHashMap
import main.model.Location
import main.db.DbAdapterBase

case class FlatLocation(id: Int, name: String, continent: String, region: String)
class ItemController(dbAdapter: DbAdapterBase = DbAdapter) {
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

  def getAvailableItemsByLocation(location: String): List[Item] = {
    val locations = dbAdapter.getLocations()
    val items = dbAdapter.getItems()
    val flatLocations = flattenLocations(locations)
    val flatLocation = flattenLocations(locations).find(fl => fl.name == location)
    val locale = flatLocation match {
      case None => throw new Exception("Specified location does not exist")
      case Some(fl) => fl.continent
    }

    return items.filter(i => i.availableLocales.contains(locale)).toList
  }
}
