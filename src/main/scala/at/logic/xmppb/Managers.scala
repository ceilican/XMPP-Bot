package at.logic.xmppb

import org.jivesoftware.smack.packet.Message
import org.jivesoftware.smack.{XMPPConnection, ConnectionConfiguration, Chat, ChatManagerListener, MessageListener}


class XMPPManager(username: String, password: String, server: ConnectionConfiguration) extends XMPPConnection(server) {
  //val connection: XMPPConnection = new XMPPConnection(stringToServer(server))

//  connection.connect()
//  connection.login(username, password)

  connect()
  login(username, password)


  //TODO: group chat option
  def setupChat(listener: ChatManagerListener with MessageListener) {
    //connection.getChatManager.addChatListener(listener)
    getChatManager.addChatListener(listener)
    println("Who do you want to chat to?")
    val person = "lebekate@gmail.com"
    //val chat = connection.getChatManager.createChat(person, listener)
    val chat = getChatManager.createChat(person, listener)
    chat.sendMessage("Hi, I'm a bot.")
    println("setupChat finished")
  }
}
