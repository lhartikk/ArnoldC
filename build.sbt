import AssemblyKeys._

assemblySettings

name := "ArnoldC"

version := "1.0"

scalaVersion := "2.10.2"

ideaExcludeFolders += ".idea"

ideaExcludeFolders += ".idea_modules"

libraryDependencies += "asm" % "asm-commons" % "3.3.1"

libraryDependencies += "org.parboiled" %% "parboiled-scala" % "1.1.5"

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.0" % "test"
