package at.logic.xmppb


import actors.Actor
import actors.Actor._
import org.jivesoftware.smack.Chat

// Internal messages accepted by CommandHandlers
case class HandleCommand(parameters: String, chat: Chat)

abstract class CommandHandler extends Actor {
  def command: String
  def help: String
  
  def handle(parameters: String): String
  
  def act() { loop { react {
    case HandleCommand(parameters, chat) => chat.sendMessage(handle(parameters))               
  }}}  
}

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