package org.arnoldc


class CommentTest extends ArnoldGeneratorTest{
  it should "work without commented lines" in {
    val code =
      "IT'S SHOWTIME\n" +
      "TALK TO THE HAND \"Bye\"\n" +
      "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("Bye\n")
  }

  it should "ignore commented lines" in {
    val code =
      "IT'S SHOWTIME\n" +
       "TALK TO THE HAND \"Bye\"\n" +
       "%TALK TO THE HAND \"I'LL BE BACK\"\n" +
       "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("Bye\n")
  }

  it should "ignore commented lines and spaces" in {
    val code =
        "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE argument\n" +
        "YOU SET US UP 123\n" +
        "DO IT NOW printInteger argument\n" +
        "YOU HAVE BEEN TERMINATED\n" +
        "LISTEN TO ME VERY CAREFULLY printInteger\n" +
        "I NEED YOUR CLOTHES YOUR BOOTS AND YOUR MOTORCYCLE value\n" +
        " TALK TO THE HAND value\n" +
        " %TALK TO THE HAND value\n" +
        "HASTA LA VISTA, BABY"
    getOutput(code) should equal("123\n")
  }
}
