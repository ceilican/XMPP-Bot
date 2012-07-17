package at.logic.xmppb


import actors.Actor
import actors.Actor._
import org.jivesoftware.smack.Chat

// Internal messages accepted by CommandHandlers
case class HandleCommand(parameters: String, chat: Chat)

abstract class CommandHandler extends Actor {
  def command: String
  def help: String
}

trait SimpleAct extends CommandHandler {
  def handle(parameters: String): String
  
  def act() { loop { react {
    case HandleCommand(parameters, chat) => chat.sendMessage(handle(parameters))               
  }}}  
}

trait ActWithCommander extends CommandHandler {
  def handle(parameters: String, commander: String): String
  
  def act() { loop { react {
    case HandleCommand(parameters, chat) => chat.sendMessage(handle(parameters, chat.getParticipant))               
  }}}  
}