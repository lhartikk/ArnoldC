package org.arnoldc

class BranchStatementTest extends ArnoldGeneratorTest {
  it should "function using simple if statements" in {
    val code =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE vartrue\n" +
        "YOU SET US UP @NO PROBLEMO\n" +
        "BECAUSE I'M GOING TO SAY PLEASE vartrue\n" +
        "TALK TO THE HAND \"this branch should be reached\"\n" +
        "YOU HAVE NO RESPECT FOR LOGIC\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("this branch should be reached\n")
  }

  it should "function using simple if statements vol2" in {
    val code =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE vartrue\n" +
        "YOU SET US UP @I LIED\n" +
        "BECAUSE I'M GOING TO SAY PLEASE vartrue\n" +
        "TALK TO THE HAND \"this branch should not be reached\"\n" +
        "YOU HAVE NO RESPECT FOR LOGIC\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("")
  }

  it should "function using simple if else statements" in {
    val code =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE vartrue\n" +
        "YOU SET US UP @NO PROBLEMO\n" +
        "BECAUSE I'M GOING TO SAY PLEASE vartrue\n" +
        "TALK TO THE HAND \"this branch should be reached\"\n" +
        "BULLSHIT\n" +
        "TALK TO THE HAND \"this branch should not be reached\"\n" +
        "YOU HAVE NO RESPECT FOR LOGIC\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("this branch should be reached\n")
  }

  it should "function using simple if else statements vol2" in {
    val code =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE varfalse\n" +
        "YOU SET US UP @I LIED\n" +
        "BECAUSE I'M GOING TO SAY PLEASE varfalse\n" +
        "TALK TO THE HAND \"this branch should not be reached\"\n" +
        "BULLSHIT\n" +
        "TALK TO THE HAND \"this branch should be reached\"\n" +
        "YOU HAVE NO RESPECT FOR LOGIC\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("this branch should be reached\n")
  }

  it should "function using assigning variables in if statements" in {
    val code =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE var\n" +
        "YOU SET US UP 0\n" +
        "HEY CHRISTMAS TREE vartrue\n" +
        "YOU SET US UP @NO PROBLEMO\n" +
        "BECAUSE I'M GOING TO SAY PLEASE vartrue\n" +
        "GET TO THE CHOPPER var\n" +
        "HERE IS MY INVITATION 3\n" +
        "ENOUGH TALK\n" +
        "YOU HAVE NO RESPECT FOR LOGIC\n" +
        "TALK TO THE HAND var\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("3\n")
  }


  it should "function using stub while statement" in {
    val code =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE varfalse\n" +
        "YOU SET US UP @I LIED\n" +
        "STICK AROUND varfalse\n" +
        "CHILL\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("")
  }


  it should "function using stub while statement vol2" in {
    val code =
      "IT'S SHOWTIME\n" +
        "STICK AROUND @I LIED\n" +
        "CHILL\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("")
  }



  it should "function when while loop executed once" in {
    val code =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE varfalse\n" +
        "YOU SET US UP @NO PROBLEMO\n" +
        "STICK AROUND varfalse\n" +
        "GET TO THE CHOPPER varfalse\n" +
        "HERE IS MY INVITATION @I LIED\n" +
        "ENOUGH TALK\n" +
        "TALK TO THE HAND \"while statement printed once\"\n" +
        "CHILL\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("while statement printed once\n")
  }

  it should "function when while loop executed consequently" in {
    val code =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE isLessThan10\n" +
        "YOU SET US UP @NO PROBLEMO\n" +
        "HEY CHRISTMAS TREE n\n" +
        "YOU SET US UP 0\n" +
        "STICK AROUND isLessThan10\n" +
        "GET TO THE CHOPPER n\n" +
        "HERE IS MY INVITATION n\n" +
        "GET UP 1\n" +
        "ENOUGH TALK\n" +
        "TALK TO THE HAND n\n" +
        "GET TO THE CHOPPER isLessThan10\n" +
        "HERE IS MY INVITATION 10\n" +
        "LET OFF SOME STEAM BENNETT n\n" +
        "ENOUGH TALK\n" +
        "CHILL\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("1\n2\n3\n4\n5\n6\n7\n8\n9\n10\n")
  }
}
