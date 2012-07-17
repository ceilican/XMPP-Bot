package at.logic.xmppb

import actors.Actor
import actors.Actor._
import org.jivesoftware.smack.packet.Message
import org.jivesoftware.smack.{ConnectionConfiguration, Chat, ChatManagerListener, MessageListener}


// Messages accepted by XMPPBot
@deprecated
case class SendMessage(user: String, message: String)
case class Delegate(command: String, parameters: String, chat: Chat) 
case object Help

class XMPPBot(username: String, password: String, servername: String, commandHandlers: CommandHandler*) extends Actor with ChatManagerListener with MessageListener {
  commandHandlers.foreach( _.start() )
  
  private val connection = new Connection(username, password, servername.toLowerCase() match {
    case "gtalk" => new ConnectionConfiguration("talk.google.com", 5222, "gmail.com")
    case _ => throw new Exception("Unknown server.")
  })
  
  private val chatManager = new ChatManager(connection)
  chatManager.addChatListener(this)
  
  println()
  println("XMPPBot started")
  println()
  

  def act = {
    loop {
      react {
        case SendMessage(user, message) => sendMessage(user, message)
        case Delegate(c, parameters, chat) => commandHandlers foreach { handler =>
          if (handler.command == c) handler ! HandleCommand(parameters, chat)
        }
        case Help => 
        case unknown => throw new Exception("XMPPBot received unknown internal message: " + unknown) // this case should never happen.
      }
    }  
  }
  
  
  override def chatCreated(chat:Chat, locally:Boolean) = { chat.addMessageListener(this) }
  
  
  def sendMessage(user: String, message: String) = {
    println("Sent Message")
    println("  to user: " + user)
    println("  with content: " + message)
    println()
    chatManager.getOrCreateChat(user, this).sendMessage(message)
  }
  
  override def processMessage(chat: Chat, message: Message) = {
    println("Received Message")
    println("  from user: " + chat.getParticipant)
    println("  with content: " + message.getBody())
    println()
    
    //Example: !google something I want to know about
    val Command = """^!([^\s]+)\s*(.*)$""".r
    message.getBody match {
      case Command(c, parameters) => this ! Delegate(c, parameters, chat)
      case _ => this ! Help
    }
  }
}

