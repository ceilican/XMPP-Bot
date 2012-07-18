package at.logic.xmppb

import actors.Actor
import actors.Actor._


// Internal messages accepted by XMPPBot
case class Delegate(command: String, parameters: String, chat: Chat) 


class XMPPBot(commandHandlers: CommandHandler*) extends Actor 
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
   
  def processCreatedChat(chat:Chat) = { chat.addMessageListener(this) }
  
  def processReceivedMessage(chat: Chat, message: String) = {    
    //Example: !google something I want to know about
    val Command = """^!([^\s]+)\s*(.*)$""".r
    message match {
      case Command(c, parameters) => this ! Delegate(c, parameters, chat)
      case _ => help(chat.participant)
    }
  }
  
  def help(user: String) = sendMessage(user,
"""      
Sorry. I did not understand your command.
Please use one of the following commands:
      
""" + (commandHandlers.map(h => "  !" + h.command + " " + h.help + "\n") :\ "")(_ + _) 
  ) 
}