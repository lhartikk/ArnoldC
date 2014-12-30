import AssemblyKeys._

assemblySettings

name := "ArnoldC"

version := "0.1"

scalaVersion := "2.11.4"

ideaExcludeFolders += ".idea"

ideaExcludeFolders += ".idea_modules"

libraryDependencies += "asm" % "asm-commons" % "3.3.1"

libraryDependencies += "org.parboiled" %% "parboiled-scala" % "1.1.6"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.1" % "test"

libraryDependencies ++= Seq(
  "javax.speech" % "jsapi" % "1.0",
  "org.mobicents.external.freetts" % "cmu_us_kal" % "1.0",
  "org.mobicents.external.freetts" % "freetts" % "1.0",
  "org.mobicents.external.freetts" % "en_us" % "1.0",
  "org.mobicents.external.freetts" % "cmulex" % "1.0"
)

resolvers += "Speech" at "http://maven.it.su.se/it.su.se/maven2"
