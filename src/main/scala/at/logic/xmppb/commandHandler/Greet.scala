package at.logic.xmppb
package commandHandler

class Greet() extends CommandHandler {
  val command = "greet"
  def handle(user: String, callback: String => Unit) = {
    sender ! SendMessageToUser(user, "Someone is sending you greetings!")
    callback("Ok. I have sent greetings to " + user) 
  }
}