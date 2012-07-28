package at.logic.xmppb
package db

// Import the session management, including the implicit threadLocalSession
//import org.scalaquery.session._
//import org.scalaquery.session.Database.threadLocalSession

// Import the query language
//import org.scalaquery.ql._

// Import the standard SQL types
//import org.scalaquery.ql.TypeMapper._

// Use MySQLDriver which implements ExtendedProfile and thus requires ExtendedTables
//import org.scalaquery.ql.extended.MySQLDriver.Implicit._
import org.scalaquery.ql.extended.{ExtendedTable => Table}

//import org.scalaquery._
//import org.scalaquery.ql.extended._


object User extends Table[(Long, String)]("user") {
  def id = column[Long]("id", O PrimaryKey, O AutoInc, O NotNull)
  def username = column[String]("user")
  def * = id ~ username
}