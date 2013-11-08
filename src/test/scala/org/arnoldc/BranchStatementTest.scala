package org.arnoldc

class BranchStatementTest extends ArnoldGeneratorTest {
  it should "function using simple if statements" in {
    val code =
      "ITS SHOWTIME\n" +
        "RIGHT? WRONG! VARTRUE\n" +
        "YOU SET US UP NO PROBLEMO\n" +
        "BECAUSE IM GOING TO SAY PLEASE VARTRUE\n" +
        "TALK TO THE HAND \"this branch should be reached\"\n" +
        "YOU HAVE NO RESPECT FOR LOGIC\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("this branch should be reached\n")
  }

  it should "function using simple if statements vol2" in {
    val code =
      "ITS SHOWTIME\n" +
        "RIGHT? WRONG! VARTRUE\n" +
        "YOU SET US UP I LIED\n" +
        "BECAUSE IM GOING TO SAY PLEASE VARTRUE\n" +
        "TALK TO THE HAND \"this branch should not be reached\"\n" +
        "YOU HAVE NO RESPECT FOR LOGIC\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("")
  }

  it should "function using simple if else statements" in {
    val code =
      "ITS SHOWTIME\n" +
        "RIGHT? WRONG! VARTRUE\n" +
        "YOU SET US UP NO PROBLEMO\n" +
        "BECAUSE IM GOING TO SAY PLEASE VARTRUE\n" +
        "TALK TO THE HAND \"this branch should be reached\"\n" +
        "BULLSHIT\n" +
        "TALK TO THE HAND \"this branch should not be reached\"\n" +
        "YOU HAVE NO RESPECT FOR LOGIC\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("this branch should be reached\n")
  }

  it should "function using simple if else statements vol2" in {
    val code =
      "ITS SHOWTIME\n" +
        "RIGHT? WRONG! VARFALSE\n" +
        "YOU SET US UP I LIED\n" +
        "BECAUSE IM GOING TO SAY PLEASE VARFALSE\n" +
        "TALK TO THE HAND \"this branch should not be reached\"\n" +
        "BULLSHIT\n" +
        "TALK TO THE HAND \"this branch should be reached\"\n" +
        "YOU HAVE NO RESPECT FOR LOGIC\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("this branch should be reached\n")
  }

  it should "function using assigning variables in if statements" in {
    val code =
      "ITS SHOWTIME\n" +
        "HEY CHRISTMAS TREE VAR\n" +
        "YOU SET US UP 0\n" +
        "RIGHT? WRONG! VARTRUE\n" +
        "YOU SET US UP NO PROBLEMO\n" +
        "BECAUSE IM GOING TO SAY PLEASE VARTRUE\n" +
        "GET TO THE CHOPPER VAR\n" +
        "HERE IS MY INVITATION 3\n" +
        "ENOUGH TALK\n" +
        "YOU HAVE NO RESPECT FOR LOGIC\n" +
        "TALK TO THE HAND VAR\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("3\n")
  }


  it should "function using stub while statement" in {
    val code =
      "ITS SHOWTIME\n" +
        "RIGHT? WRONG! VARFALSE\n" +
        "YOU SET US UP I LIED\n" +
        "STICK AROUND VARFALSE\n" +
        "CHILL\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("")
  }



  it should "function when while loop executed once" in {
    val code =
      "ITS SHOWTIME\n" +
        "RIGHT? WRONG! VARFALSE\n" +
        "YOU SET US UP NO PROBLEMO\n" +
        "STICK AROUND VARFALSE\n" +
        "GET TO THE CHOPPER VARFALSE\n" +
        "HERE IS MY INVITATION I LIED\n" +
        "ENOUGH TALK\n" +
        "TALK TO THE HAND \"while statement printed once\"\n" +
        "CHILL\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("while statement printed once\n")
  }

  it should "function when while loop executed consequently" in {
    val code =
      "ITS SHOWTIME\n" +
        "RIGHT? WRONG! ISLESSTHAN10\n" +
        "YOU SET US UP NO PROBLEMO\n" +
        "HEY CHRISTMAS TREE N\n" +
        "YOU SET US UP 0\n" +
        "STICK AROUND ISLESSTHAN10\n" +
        "GET TO THE CHOPPER N\n" +
        "HERE IS MY INVITATION N\n" +
        "GET UP 1\n" +
        "ENOUGH TALK\n" +
        "TALK TO THE HAND N\n" +
        "GET TO THE CHOPPER ISLESSTHAN10\n" +
        "HERE IS MY INVITATION 10\n" +
        "LET OF SOME STEAM BENNET N\n" +
        "ENOUGH TALK\n" +
        "CHILL\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("1\n2\n3\n4\n5\n6\n7\n8\n9\n10\n")
  }
}
