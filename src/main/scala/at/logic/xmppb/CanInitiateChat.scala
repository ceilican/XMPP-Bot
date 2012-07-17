package at.logic.xmppb

trait CanInitiateChat {
  def chatDB: ChatDB
  
  def sendMessage(user: String, message: String) = {
    println("Sent Message")
    println("  to user: " + user)
    println("  with content: " + message)
    println()
    chatDB.getOrCreateChat(user).sendMessage(message)
  }
}