package at.logic.xmppb


import actors.Actor
import actors.Actor._

/**
 * Message Wrappers
 */
case class HandleCommand(data: Any, callback: Any => Unit )
case class CommandHandlerResponse(ans: Option[String])

/**
 * Agent Base that additionally pre-parses messages that follow the pattern:
 *   !COMMAND EXTRA STUFF HERE
 *   Example: !google something I want to know about
 */
case class CommandData(command: String, data: String)


abstract class CommandHandler extends Actor {
  val CommandPattern = """^!([^\s]+)\s*(.*)$""".r
  def handle(c: CommandData): Option[String]
  
  def act() {
    loop {
      react {
        case HandleCommand(CommandPattern(command, commandData), callback) => {
          val reply = handle(CommandData(command.toLowerCase(), commandData))
          sender ! CommandHandlerResponse(reply)
          callback(reply)
        }
                
      }
    }
  }
  

}