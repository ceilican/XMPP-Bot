package at.logic.xmppb

import org.jivesoftware.smack.packet.Message
import org.jivesoftware.smack.{Chat => JiveChat, ChatManagerListener => JiveChatManagerListener, MessageListener => JiveMessageListener}
import org.jivesoftware.smack.{XMPPConnection, ConnectionConfiguration}

trait ChatManagerListener extends JiveChatManagerListener

trait MessageListener extends JiveMessageListener

//trait Chat extends JiveChat




class Connection(username: String, password: String, servername: String) 
extends XMPPConnection(servername.toLowerCase() match {
  case "gtalk" => new ConnectionConfiguration("talk.google.com", 5222, "gmail.com")
  case _ => throw new Exception("Unknown server.")
}) {
  connect()
  login(username, password)
}