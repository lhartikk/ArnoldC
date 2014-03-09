package org.arnoldc


class CommentsTest extends ArnoldGeneratorTest{
  it should "comment line inside method body" in {
    val code =
      "IT'S SHOWTIME\n" +
      "#Comment\n" +
      "TALK TO THE HAND \"Hello\"\n" +
      "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("Hello\n")
  }
}
