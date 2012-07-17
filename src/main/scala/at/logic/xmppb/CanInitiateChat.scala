package at.logic.xmppb

trait CanInitiateChat {
  def chatManager: ChatManager
  
  def sendMessage(user: String, message: String) = chatManager.getOrCreateChat(user).sendMessage(message)
}