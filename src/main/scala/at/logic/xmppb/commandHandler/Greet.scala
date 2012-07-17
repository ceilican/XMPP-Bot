package at.logic.xmppb
package commandHandler

class Greet(val chatDB: ChatDB) extends CommandHandler with CanInitiateChat {
  val command = "greet"
  val help = "<user@gmail.com> : sends greetings to <user@gmail.com>."
  def handle(destination: String) = {
    sendMessage(destination, "Someone is sending you greetings!")
    "Ok. I have sent greetings to " + destination
  }
}