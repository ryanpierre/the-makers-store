package main.db

import scala.collection.mutable.ArrayBuffer
import main.model.Item
import scala.collection.mutable.LinkedHashMap

class DbAdapter {
  def read(table: String): ArrayBuffer[ujson.Obj] = {
    val filename = table + ".json"
    val jsonString = os.read(os.pwd/"src"/"main"/"resources"/filename)
    val data = ujson.read(jsonString)
    data.value.asInstanceOf[ArrayBuffer[ujson.Obj]]
  }

  def readRaw(table: String): ujson.Arr = {
    val filename = table + ".json"
    val jsonString = os.read(os.pwd/"src"/"main"/"resources"/filename)
    ujson.read(jsonString).asInstanceOf[ujson.Arr]
  }

  def write(table: String, data: ArrayBuffer[Item]) = {
    val filename = table + ".json"

    val newData = ujson.Arr.from(data.map(item => {
      val id = ujson.Num(item.id.toDouble)
      val name =  ujson.Str(item.name)
      val price =  ujson.Num(item.price.toDouble)
      val quantity = ujson.Num(item.quantity.toDouble)
      val availableLocales = ujson.Arr.from(item.availableLocales)

      LinkedHashMap("id" -> id, "name" -> name, "price" -> price, "quantity" -> quantity, "availableLocales" -> availableLocales)
    }))

    os.remove(os.pwd/"src"/"main"/"resources"/"items.json")
    os.write(os.pwd/"src"/"main"/"resources"/filename, newData)
  }

  def reset: Unit = {
    os.remove(os.pwd/"src"/"main"/"resources"/"items.json")
    os.write(os.pwd/"src"/"main"/"resources"/"items.json", os.read(os.pwd/"src"/"main"/"resources"/"items.base.json"))
  }

  def getItems(): ArrayBuffer[Item] = {
    val db = this.read("items")

    db.map(item => {
      val id = item("id").value.asInstanceOf[Double].toInt
      val name = item("name").value.asInstanceOf[String]
      val price = item("price").value.asInstanceOf[Double]
      val quantity = item("quantity").value.asInstanceOf[Double].toInt
      val availableLocales = item("availableLocales").arr.map(_.value.asInstanceOf[String]).toList
      new Item(id, name, price, quantity, availableLocales)
    })
  }

  def updateItem(id: Int, newItem: Item): Unit = {
    val items = this.getItems()
    val newData = items.map(i => i.id match {
      case x if x == id => newItem
      case _ => i
    })

    this.write("items", newData)
  }

  def createItem(newItem: Item): Unit = {
      val id = ujson.Num(newItem.id.toDouble)
      val name =  ujson.Str(newItem.name)
      val price =  ujson.Num(newItem.price.toDouble)
      val quantity = ujson.Num(newItem.quantity.toDouble)
      val availableLocales = ujson.Arr.from(newItem.availableLocales)
      val newJsonObj = LinkedHashMap("id" -> id, "name" -> name, "price" -> price, "quantity" -> quantity, "availableLocales" -> availableLocales)
      val newJson = ujson.Value(newJsonObj)

      val db = this.readRaw("items")
      db.arr.append(newJson)

      os.remove(os.pwd/"src"/"main"/"resources"/"items.json")
      os.write(os.pwd/"src"/"main"/"resources"/"items.json", db)
  }
}
