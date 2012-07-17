package at.logic.xmppb
package commandHandler

class Google() extends CommandHandler {
  val command = "google"
  def handle(query: String) = {
    "http://lmgtfy.com/?q=" + query.replaceAll(" ", "+")
  }
}