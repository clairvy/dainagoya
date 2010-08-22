import sbt._

class MyProject(info: ProjectInfo) extends DefaultProject(info) {
  val mockito = "org.mockito" % "mockito-core" % "1.8.5"
  val specs = "org.scala-tools.testing" % "specs_2.8.0-SNAPSHOT" % "1.6.5-SNAPSHOT" % "test"

  val mvn = "maven" at "http://repo2.maven.org/maven2/"
  val snapshots = "Snapshots" at "http://scala-tools.org/repo-snapshots/"

  val hadoop = "org.apache.mahout.hadoop" % "hadoop-core" % "0.20.1"
  val logging = "commons-logging" % "commons-logging" % "1.1.1"
}
