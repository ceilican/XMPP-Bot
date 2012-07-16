package at.logic.xmppb

import org.jivesoftware.smack.{ConnectionConfiguration, Chat, ChatManagerListener, MessageListener}
import collection.mutable.{Map => MMap}

class ChatManager(connection: Connection) {
  val activeChats = MMap[String, Chat]()
  val chatManager = connection.getChatManager
  def addChatListener(bot: XMPPBot) = chatManager.addChatListener(bot)
  def getOrCreateChat(user: String, bot: XMPPBot) = activeChats.getOrElseUpdate(user, {
    println("Created chat with user: " + user)
    chatManager.createChat(user, bot)
  })
}
