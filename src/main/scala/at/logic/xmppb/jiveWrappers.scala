package at.logic.xmppb

import org.jivesoftware.smack.packet.Message
import org.jivesoftware.smack.{Chat => JiveChat, ChatManagerListener => JiveChatManagerListener, MessageListener => JiveMessageListener}
import org.jivesoftware.smack.{XMPPConnection, ConnectionConfiguration}

trait ChatManagerListener extends JiveChatManagerListener {
  def processCreatedChat(chat: Chat)
  
  def chatCreated(c: JiveChat, locally: Boolean) = processCreatedChat(new Chat(c))
}

trait MessageListener extends JiveMessageListener {
  def processReceivedMessage(c: Chat, m: String)
  
  def processMessage(c: JiveChat, m: Message) = {
    println("Received Message")
    println("  from user: " + c.getParticipant)
    println("  with content: " + m.getBody)
    println()
    processReceivedMessage(new Chat(c), m.getBody)
  }
}

class Chat(c: JiveChat) {
  def sendMessage(m: String) = { 
    c.sendMessage(m)
    println("Sent Message")
    println("  to user: " + participant)
    println("  with content: " + m)
    println()
  }
  def participant = c.getParticipant
  def addMessageListener(l: MessageListener) = c.addMessageListener(l)
}


class Connection(username: String, password: String, servername: String) 
extends XMPPConnection(servername.toLowerCase() match {
  case "gtalk" => new ConnectionConfiguration("talk.google.com", 5222, "gmail.com")
  case _ => throw new Exception("Unknown server.")
}) {
  connect()
  login(username, password)
}