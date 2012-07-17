package at.logic.xmppb
package commandHandler

class Google() extends CommandHandler {
  val command = "google"
  val help = "<topic> : googles <topic> for you."
  def handle(query: String) = "http://lmgtfy.com/?q=" + query.replaceAll(" ", "+")
}