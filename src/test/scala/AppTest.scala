package main

import main.App
import org.scalamock.scalatest.MockFactory
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class AppTest extends AnyWordSpec with Matchers with MockFactory {
  "An App" should {
    "start up" which {
      "says 'OK'" in {
        val subject = App
        subject.start() shouldBe "OK"
      }
    }
  }
}
