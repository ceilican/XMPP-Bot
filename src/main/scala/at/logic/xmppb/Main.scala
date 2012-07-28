package at.logic.xmppb

import at.logic.xmppb.commandHandler._

import at.logic.xmppb.db._

// Import the session management, including the implicit threadLocalSession
import org.scalaquery.session._
import org.scalaquery.session.Database.threadLocalSession

// Import the query language
import org.scalaquery.ql._

// Import the standard SQL types
import org.scalaquery.ql.TypeMapper._

// Use MySQLDriver which implements ExtendedProfile and thus requires ExtendedTables
import org.scalaquery.ql.extended.MySQLDriver.Implicit._
import org.scalaquery.ql.extended.{ExtendedTable => Table}

import org.scalaquery._
import org.scalaquery.ql.extended._

object Main {
  
  def main(args: Array[String]) {

    if (args.length == 3) {
      val username = args(0)
      val password = args(1)
      val servername = args(2)
      
      val chatManager = new ChatManager(username, password, servername) 
      val bot = new XMPPBot(chatManager, new Greet(chatManager), new Google(), new Umbrella())
      bot.start()     
      chatManager.addChatListener(bot)     
    }
    else {
      
      database withSession {
        User insert (2.toLong, "bruno")  
        User insert (4.toLong, "katya")
        User.username insert "katya2"
        
        (for (u <- User if u.username === "bruno") yield u).delete
      }
      
      
      help()
    }

    def help() = print(
    """
      
    XMPPBot Usage Instructions
    ==========================
        
      sbt run <username> <password> <servername>    
      
        example:
          sbt run someuser@gmail.com gmail-password-for-someuser GTalk
      
          Then connect to GTalk with anotheruser's account and send a message to someuser.
          The bot, logged in as someuser, will reply.
    """       
    )

  }
}