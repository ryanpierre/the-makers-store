package main.db

import scala.collection.mutable.ArrayBuffer
import main.model.Item

class DbAdapter {
  // TODO: This could be refactored so we could test without actually reading a real json file
  def read(table: String): ArrayBuffer[ujson.Obj] = {
    val filename = table + ".json"
    val jsonString = os.read(os.pwd/"src"/"main"/"resources"/filename)
    val data = ujson.read(jsonString)
    data.value.asInstanceOf[ArrayBuffer[ujson.Obj]]
  }

  // TODO: Writes the new data to the database
  def write[T](table: String, data: ArrayBuffer[T]) = {

  }

  // TODO: Cleans the database back to the base state
  def flush: Boolean = {
    true
  }

  // TODO: This could be refactored to be passed the db as a param, and then mocked better in the test
  def getItems(): ArrayBuffer[Item] = {
    val db = this.read("items")

    db.map(item => {
      val id = item("id").value.asInstanceOf[Double].toInt
      val name = item("name").value.asInstanceOf[String]
      val price = item("price").value.asInstanceOf[Double]
      val quantity = item("quantity").value.asInstanceOf[Double].toInt
      val locales = item("availableLocales").arr.map(_.value.asInstanceOf[String]).toList
      new Item(id, name, price, quantity, locales)
    })
  }

  // TODO: Replaces an item in the database with the matching id of the one we provided
  def updateItem(id: Int, newItem: Item): Boolean = {
    true
  }

  // TODO: Creates a new item in the database
  def createItem(newItem: Item): Boolean = {
    true
  }
}
