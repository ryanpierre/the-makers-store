import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalamock.scalatest.MockFactory
import scala.collection.mutable.ArrayBuffer
import main.model.Item

class AppTest extends AnyWordSpec with Matchers with MockFactory {
  "An App" should {
    "start up" which {
      "says 'OK'" in {
        val subject = App
        subject.start() shouldBe "OK"
      }
    }
    "read the database" which {
      "returns a list of items" in {
        val subject = App 
        subject.data() shouldBe a[ArrayBuffer[_]]
        subject.data().foreach(i => i shouldBe a[Item])
      }
    }
  }
}
