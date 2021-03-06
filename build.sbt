name := "HomeBanking"

version := "0.1"

scalaVersion := "2.13.0"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-Xcheckinit", "-encoding", "utf8")

libraryDependencies ++= Seq("org.apache.poi" % "poi-ooxml" % "4.1.2",
  "org.scalafx"        %% "scalafx"   % "12.0.2-R18",
  "com.typesafe.slick" %% "slick"     % "3.3.2",
  "org.slf4j"           % "slf4j-nop" % "1.7.28",
  "com.h2database"      % "h2"        % "1.4.199",
  "net.liftweb" %% "lift-json" % "3.4.0",
  "com.fasterxml.jackson.core" % "jackson-core" % "2.10.1",
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.10.3",
  "org.apache.commons" % "commons-configuration2" % "2.7"
)

logLevel := Level.Debug

// Add OS specific JavaFX dependencies
val javafxModules = Seq("base", "controls", "fxml", "graphics", "media", "swing", "web")
val osName = System.getProperty("os.name") match {
  case n if n.startsWith("Linux") => "linux"
  case n if n.startsWith("Mac") => "mac"
  case n if n.startsWith("Windows") => "win"
  case _ => throw new Exception("Unknown platform!")
}
libraryDependencies ++= javafxModules.map(m => "org.openjfx" % s"javafx-$m" % "12.0.2" classifier osName)


shellPrompt := { _ => System.getProperty("user.name") + "> " }

// set the main class for the main 'run' task
// change Compile to Test to set it for 'test:run'
//mainClass in (Compile, run) := Some("org.scalafx.ScalaFXHelloWorld")

// Fork a new JVM for 'run' and 'test:run' to avoid JavaFX double initialization problems
fork := true