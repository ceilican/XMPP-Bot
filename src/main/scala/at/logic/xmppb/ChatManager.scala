package at.logic.xmppb

import collection.mutable.{Map => MMap}

class ChatManager(username: String, password: String, servername: String) extends ChatManagerListener {
  private val connection = new Connection(username, password, servername)
  
  private val activeChats = MMap[String, Chat]()
  private val manager = connection.getChatManager
  
  def processCreatedChat(chat:Chat) = { activeChats(chat.participant) = chat }
  
  def addChatListener(bot: XMPPBot) = manager.addChatListener(bot)
  def getOrCreateChat(user: String) = activeChats.getOrElseUpdate(user, {
    println("Created chat with user: " + user)
    new Chat(manager.createChat(user, dummyMessageListener))
  })
  
  // a dummy message listener is unfortunately needed,
  // because ChatManager.createChat requires a message listener to create a Chat
  private val dummyMessageListener = new MessageListener {
    def processReceivedMessage(chat: Chat, m: String) = { }
  }
}