import scala.collection.mutable.ArrayBuffer
import main.model.Item
import main.db.DbAdapter

object App {
  def main(args: Array[String]): Unit = {
    App.start()
  }

  def start(): String = {
    "OK"
  }

  def data(): ArrayBuffer[Item] = {
    DbAdapter.getLocations()
    DbAdapter.getItems()
  }
}
