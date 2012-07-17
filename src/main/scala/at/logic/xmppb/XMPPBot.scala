package at.logic.xmppb

import actors.Actor
import actors.Actor._
import org.jivesoftware.smack.packet.Message
import org.jivesoftware.smack.Chat


// Internal messages accepted by XMPPBot
case class Delegate(command: String, parameters: String, chat: Chat) 

class XMPPBot(val chatDB: ChatDB, commandHandlers: CommandHandler*) extends Actor 
with ChatManagerListener with MessageListener
with CanInitiateChat {
  
  commandHandlers.foreach( _.start() )
  
  println()
  println("XMPPBot started")
  println()
  

  def act = {
    loop {
      react {
        case Delegate(c, parameters, chat) => commandHandlers foreach { handler =>
          if (handler.command == c) handler ! HandleCommand(parameters, chat)
        }
        case unknown => throw new Exception("XMPPBot received unknown internal message: " + unknown) // this case should never happen.
      }
    }  
  }
   
  override def chatCreated(chat:Chat, locally:Boolean) = { chat.addMessageListener(this) }
  
  def processMessage(chat: Chat, message: Message) = {
    println("Received Message")
    println("  from user: " + chat.getParticipant)
    println("  with content: " + message.getBody())
    println()
    
    //Example: !google something I want to know about
    val Command = """^!([^\s]+)\s*(.*)$""".r
    message.getBody match {
      case Command(c, parameters) => this ! Delegate(c, parameters, chat)
      case _ => help(chat.getParticipant)
    }
  }
  
  def help(user: String) = sendMessage(user,
"""      
Sorry. I did not understand your command.
Please use one of the following commands:
      
""" + (commandHandlers.map(h => "  !" + h.command + " " + h.help + "\n") :\ "")(_ + _) 
  ) 
}