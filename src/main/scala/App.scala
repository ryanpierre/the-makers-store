import scala.collection.mutable.ArrayBuffer
import main.model.Item
import main.db.DbAdapter
import main.payment.PaymentAdapter
import main.payment.Payment

// This class simply exists so we have a main method to run the application. Feel free to do with it what you please
object App {
  def main(args: Array[String]): Unit = {
    App.start()
  }
  
  def start(): String = {
    "OK"
  }
}
