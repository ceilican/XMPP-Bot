package at.logic.xmppb

import org.jivesoftware.smack.packet.Message
import org.jivesoftware.smack.{XMPPConnection, ConnectionConfiguration}


class Connection(username: String, password: String, server: ConnectionConfiguration) extends XMPPConnection(server) {
  connect()
  login(username, password)
}
