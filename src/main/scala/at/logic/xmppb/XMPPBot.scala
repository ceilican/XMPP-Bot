package at.logic.xmppb

import actors.Actor
import actors.Actor._
import org.jivesoftware.smack.packet.Message
import org.jivesoftware.smack.{ConnectionConfiguration, Chat, ChatManagerListener, MessageListener}


// Messages accepted by XMPPBot
case class SendMessageToUser(user: String, message: String)
case class Delegate(message: String, callback: String => Unit) 

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
  
  def sendMessageToUser(user: String, message: String) = {
    println("Sent Message")
    println("  to user: " + user)
    println("  with content: " + message)
    println()
    chatManager.getOrCreateChat(user, this).sendMessage(message)
  }

  def act = {
    loop {
      react {
        case SendMessageToUser(user, message) => sendMessageToUser(user, message)
        case Delegate(message, callback) => commandHandlers foreach { handler =>
          handler ! HandleCommandWithCallback(message, callback)
        }
        case unknown => println("Received unknown internal message: " + unknown) // this case should never happen.
      }
    }
    
  }

  //this ! SendMessageToUser("lebekate@gmail.com", "hi")
  
  //Listen & Forward Messages
  override def chatCreated(chat:Chat, locally:Boolean) = { chat.addMessageListener(this) }
  
  
  override def processMessage(chat:Chat, message:Message) = {
    println("Received Message")
    println("  from user: " + chat.getParticipant)
    println("  with content: " + message.getBody())
    println()
    val callback = (answer: Any) => {
      val msg = new Message(chat.getParticipant, Message.Type.chat)
      msg.setBody(answer.toString)
      connection.sendPacket(msg)
    }
    this ! Delegate(message.getBody, callback)

  }
}

