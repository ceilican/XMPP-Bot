name := "XMPP-Bot"

organization := "at.logic"

version := "0.1-SNAPSHOT"

scalaVersion := "2.9.1"

scalacOptions ++= Seq("-unchecked", "-deprecation")


resolvers ++= Seq(
  "Akka Repo" at "http://repo.akka.io/releases"
)

libraryDependencies ++= Seq(
  "jivesoftware" % "smack" % "3.1.0",
  "jivesoftware" % "smackx" % "3.1.0",
  "se.scalablesolutions.akka" % "akka-actor" % "1.1.3",
  "net.databinder" %% "dispatch-http" % "0.8.5",
  "org.scalatest" %% "scalatest" % "1.7.2",
  "org.specs2" %% "specs2" % "1.10",
  "org.scalacheck" %% "scalacheck" % "1.9",
  "junit" % "junit" % "4.10"
)

licenses := Seq("GNU GPL v3" -> url("http://www.gnu.org/licenses/gpl.html"))

homepage := Some(url("http://github.com/Paradoxika/Skeptik"))


publishMavenStyle := true

publishTo <<= version { (v: String) =>
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT")) 
    Some("snapshots" at nexus + "content/repositories/snapshots") 
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

publishArtifact in Test := false

//pomIncludeRepository := { _ => false }

pomExtra := (
  <scm>
    <url>git@github.com:Paradoxika/XMPP-Bot.git</url>
    <connection>scm:git:git@github.com:Paradoxika/XMPP-Bot.git</connection>
  </scm>
  <developers>
    <developer>
      <id>bruno.wp</id>
      <name>Bruno Woltzenlogel Paleo</name>
      <url>http://www.logic.at/People/Bruno/</url>
    </developer>
  </developers>
  <contributors>
  </contributors>
  	<properties>
		<encoding>UTF-8</encoding>
	</properties>
  	<build>
    <plugins>
			<plugin>
				<groupId>net.alchim31.maven</groupId>
				<artifactId>scala-maven-plugin</artifactId>
				<version>3.0.2</version>
				<executions>
					<execution>
						<goals>
							<goal>compile</goal>
							<goal>testCompile</goal>
						</goals>
						<configuration>
							<args>
								<arg>-make:transitive</arg>
								<arg>-dependencyfile</arg>
							</args>
						</configuration>
					</execution>
				</executions>
			</plugin>
		</plugins>
	</build>	
	<reporting>
		<plugins>
			<plugin>
				<groupId>net.alchim31.maven</groupId>
				<artifactId>scala-maven-plugin</artifactId>
				<version>3.0.2</version>
				<configuration>
					<scalaVersion>2.9.1</scalaVersion>
				</configuration>
			</plugin>
		</plugins>
	</reporting>
)