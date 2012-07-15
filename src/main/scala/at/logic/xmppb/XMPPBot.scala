package at.logic.xmppb

import collection.JavaConversions._
import actors.Actor
import actors.Actor._
import org.jivesoftware.smack.packet.Message
import org.jivesoftware.smack.{XMPPConnection, ConnectionConfiguration, Chat, ChatManagerListener, MessageListener}


class XMPPBot(username: String, password: String, server: String, commandHandlers: CommandHandler*) extends Actor with ChatManagerListener with MessageListener {
  private def stringToServer(s:String) = s.toLowerCase() match {
    case "gtalk" => new ConnectionConfiguration("talk.google.com", 5222, "gmail.com")
    case _ => throw new Exception("Unknown server.")
  }
  
  val connectionManager = new XMPPManager(username, password, stringToServer(server))
  //Setup Connection
  

  //Start Chat
  connectionManager.setupChat(this)

  commandHandlers.foreach( _.start() )

  def act = {
    loop {
      react {
        case CommandHandlerResponse(Some(answer)) => reply(answer)
        case CommandHandlerResponse(None) => 
      }
    }
    
  }

  //Listen & Forward Messages
  override def chatCreated(chat:Chat, locally:Boolean) = { chat.addMessageListener(this) }
  println("chat created")
  
  
  override def processMessage(chat:Chat, message:Message) = {
    val callback = (answer: Any) => {
      val msg = new Message(chat.getParticipant, Message.Type.chat)
      msg.setBody(answer.toString)
      //connectionManager.connection.sendPacket(msg)
      connectionManager.sendPacket(msg)
    }
    commandHandlers foreach {
      println("delegating message");
      _ ! HandleCommand(message.getBody, callback)
    } 
  }
}

