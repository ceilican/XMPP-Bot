package at.logic.xmppb


import actors.Actor
import actors.Actor._
import org.jivesoftware.smack.Chat

// Messages accepted by CommandHandlers
case class HandleCommand(parameters: String, chat: Chat)

abstract class CommandHandler extends Actor {
  def command: String
  
  def handle(parameters: String): String
  
  def act() { loop { react {
    case HandleCommand(parameters, chat) => chat.sendMessage(handle(parameters))               
  }}}  
}