import AssemblyKeys._ // put this at the top of the file

assemblySettings

name := "ArnoldC"

version := "1.0"

scalaVersion := "2.9.2"

ideaExcludeFolders += ".idea"

ideaExcludeFolders += ".idea_modules"

libraryDependencies += "asm" % "asm-commons" % "3.3.1"

libraryDependencies += "org.parboiled" % "parboiled-scala" % "1.0.2"

libraryDependencies += "org.scalatest" %% "scalatest" % "1.6.1" % "test"
