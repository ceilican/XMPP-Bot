package drashid.agent

import akka.actor.Actor._

object Main{

  def main(args: Array[String]){
    var xmppConf = "src/main/resources/xmpp.conf"
    if(args.length >= 1){
      xmppConf = args(0)
    }
    val google = actorOf(Google())
    val umbrella = actorOf(Umbrella())
    val greet = actorOf(Greet())
    
//    val a = 
//    a.username = args(0)
//    a.password = args(1)
//    a.server = args(2)
    
    val manager = actorOf(new XMPPAgentManager(args(0), args(1), args(2), umbrella, google, greet) ).start()
    manager ! "!greet"

    println("Press any key to stop.")
    //System.in.read()
    //manager ! 'stop
  }

}