package at.logic.xmppb


import actors.Actor
import actors.Actor._

// Messages accepted by CommandHandlers
case class HandleCommandWithCallback(message: String, callback: String => Unit )
//case class HandleCommand(message: String)

abstract class CommandHandler extends Actor {
  //!COMMAND EXTRA STUFF HERE
  //Example: !google something I want to know about
  val Command = """^!([^\s]+)\s*(.*)$""".r
  def command: String
  
  def handle(parameters: String, callback: String => Unit): Unit
  
  def act() {
    loop {
      react {
        case HandleCommandWithCallback(Command(c, parameters), callback) => {
//          val message = SendMessageToUser("lebekate@gmail.com", "hihihi")
//          sender ! "working"
          if (command == c) handle(parameters, callback)
        }                
      }
    }
  }  
}