package at.logic.xmppb

import org.scalaquery.session.Database

package object db {
  val database = Database.forURL("jdbc:mysql://localhost:3306/Alou",
                                 driver="com.mysql.jdbc.Driver",
                                 user="Alou",
                                 password="54321")   
}