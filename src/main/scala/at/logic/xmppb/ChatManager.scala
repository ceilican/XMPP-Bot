package at.logic.xmppb

import org.jivesoftware.smack.{ConnectionConfiguration, Chat, ChatManagerListener, MessageListener}
import collection.mutable.{Map => MMap}

class ChatManager(connection: Connection) extends ChatManagerListener {
  private val activeChats = MMap[String, Chat]()
  private val chatManager = connection.getChatManager
  
  override def chatCreated(chat:Chat, locally:Boolean) = { activeChats(chat.getParticipant) = chat }
  
  def addChatListener(bot: XMPPBot) = chatManager.addChatListener(bot)
  def getOrCreateChat(user: String, bot: XMPPBot) = activeChats.getOrElseUpdate(user, {
    println("Created chat with user: " + user)
    chatManager.createChat(user, bot)
  })
}
