package org.arnoldc

class FeatureTest extends ArnoldGeneratorTest{

  it should "print fibonacci numbers from 1 to 10" in {
    val code  = "ITS SHOWTIME\n" +
      "HEY CHRISTMAS TREE prev\n" +
      "YOU SET US UP -1\n" +
      "HEY CHRISTMAS TREE result\n" +
      "YOU SET US UP 1\n" +
      "HEY CHRISTMAS TREE sum\n" +
      "YOU SET US UP 0\n" +
      "RIGHT? WRONG! loop\n" +
      "YOU SET US UP NO PROBLEMO\n" +
      "HEY CHRISTMAS TREE index\n" +
      "YOU SET US UP 0\n" +
      "HEY CHRISTMAS TREE limit\n" +
      "YOU SET US UP 10\n" +
      "\nSTICK AROUND loop\n" +
      "\tGET TO THE CHOPPER sum\n" +
      "\tHERE IS MY INVITATION result\n" +
      "\tGET UP prev\n\tENOUGH TALK\n" +
      "\n\tGET TO THE CHOPPER prev\n" +
      "\tHERE IS MY INVITATION result\n" +
      "\tENOUGH TALK\n\t" +
      "\n\tGET TO THE CHOPPER result\n" +
      "\tHERE IS MY INVITATION sum\n" +
      "\tENOUGH TALK\n\t" +
      "\n\tGET TO THE CHOPPER index\n" +
      "\tHERE IS MY INVITATION index\n" +
      "\tGET UP 1\n" +
      "\tENOUGH TALK\n\t" +
      "\n\tGET TO THE CHOPPER loop\n" +
      "\tHERE IS MY INVITATION limit\n" +
      "\tLET OF SOME STEAM BENNET index\n" +
      "\tENOUGH TALK\n\t" +
      "\n\tTALK TO THE HAND sum\n" +
      "CHILL\n" +
      "\nYOU HAVE BEEN TERMINATED"

    getOutput(code) should equal("0\n1\n1\n2\n3\n5\n8\n13\n21\n34\n")
  }


  it should "print squares from 1 to 10" in {
     val code = "ITS SHOWTIME\n" +
       "HEY CHRISTMAS TREE limit\n" +
       "YOU SET US UP 10\n" +
       "HEY CHRISTMAS TREE index\n" +
       "YOU SET US UP 1\n" +
       "HEY CHRISTMAS TREE squared\n" +
       "YOU SET US UP 1\n" +
       "RIGHT? WRONG! loop\n" +
       "YOU SET US UP NO PROBLEMO \n\n" +
       "STICK AROUND loop\n\n" +
       "\tGET TO THE CHOPPER squared\n" +
       "\tHERE IS MY INVITATION index\n" +
       "\tYOU'RE FIRED index\n" +
       "\tENOUGH TALK\n" +
       "\tTALK TO THE HAND squared\n\t\n" +
       "\tGET TO THE CHOPPER loop\n" +
       "\tHERE IS MY INVITATION limit\n" +
       "\tLET OF SOME STEAM BENNET index\n" +
       "\tENOUGH TALK\n\t\n" +
       "\tGET TO THE CHOPPER index\n" +
       "\tHERE IS MY INVITATION index\n" +
       "\tGET UP 1\n" +
       "\tENOUGH TALK\n\t\n" +
       "CHILL\n" +
       "YOU HAVE BEEN TERMINATED"

    getOutput(code) should equal("1\n4\n9\n16\n25\n36\n49\n64\n81\n100\n")
 }

}
