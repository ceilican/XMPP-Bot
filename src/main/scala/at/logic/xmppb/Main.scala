package at.logic.xmppb

import at.logic.xmppb.commandHandler._

object Main{

  def main(args: Array[String]){

    if (args.length == 3) {
      val username = args(0)
      val password = args(1)
      val servername = args(2)
      
      val chatDB = new ChatDB(username, password, servername) 
      val bot = new XMPPBot(chatDB, new Greet(chatDB), new Google(), new Umbrella())
      bot.start()     
      chatDB.addChatListener(bot)     
    }
    else {
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