package at.logic.xmppb
package commandHandler

class Greet() extends CommandHandler {
  val command = "greet"
  def handle(destination: String) = {
    sender ! SendMessage(destination, "Someone is sending you greetings!")
    "Ok. I have sent greetings to " + destination
  }
}