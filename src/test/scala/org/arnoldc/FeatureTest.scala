package org.arnoldc

class FeatureTest extends ArnoldGeneratorTest{

  it should "print fibonacci numbers" in {
    val code  = "ITS SHOWTIME\n" +
      "HEY CHRISTMAS TREE PREV\n" +
      "YOU SET US UP -1\n" +
      "HEY CHRISTMAS TREE RESULT\n" +
      "YOU SET US UP 1\n" +
      "HEY CHRISTMAS TREE SUM\n" +
      "YOU SET US UP 0\n" +
      "RIGHT? WRONG! LOOP\n" +
      "YOU SET US UP NO PROBLEMO\n" +
      "HEY CHRISTMAS TREE INDEX\n" +
      "YOU SET US UP 0\n" +
      "HEY CHRISTMAS TREE AMOUNT\n" +
      "YOU SET US UP 10\n" +
      "\nSTICK AROUND LOOP\n" +
      "\tGET TO THE CHOPPER SUM\n" +
      "\tHERE IS MY INVITATION RESULT\n" +
      "\tGET UP PREV\n\tENOUGH TALK\n" +
      "\n\tGET TO THE CHOPPER PREV\n" +
      "\tHERE IS MY INVITATION RESULT\n" +
      "\tENOUGH TALK\n\t" +
      "\n\tGET TO THE CHOPPER RESULT\n" +
      "\tHERE IS MY INVITATION SUM\n" +
      "\tENOUGH TALK\n\t" +
      "\n\tGET TO THE CHOPPER INDEX\n" +
      "\tHERE IS MY INVITATION INDEX\n" +
      "\tGET UP 1\n" +
      "\tENOUGH TALK\n\t" +
      "\n\tGET TO THE CHOPPER LOOP\n" +
      "\tHERE IS MY INVITATION AMOUNT\n" +
      "\tLET OF SOME STEAM BENNET INDEX\n" +
      "\tENOUGH TALK\n\t" +
      "\n\tTALK TO THE HAND SUM\n" +
      "CHILL\n" +
      "\nYOU HAVE BEEN TERMINATED\n"

    getOutput(code) should equal("0\n1\n1\n2\n3\n5\n8\n13\n21\n34\n")
  }


}
