import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.Date;

/**
 * @author ${BENMOUSSA-Younes RFIA}
 * @created ${2023}
 * @project ${Communication entre deux Agents sous Jade}
 * Agent 2
 */

public class SecondAgent extends Agent {
    int value;
    protected void setup(){
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage message = myAgent.receive();
                if(message != null){

                    String content = message.getContent();
                    System.out.println("Agent 2 a reçu le message : " + content);
                    int index = content.indexOf(", ");
                    if (index != -1) {
                        String numberString = content.substring(index + 2);
                        int number = Integer.parseInt(numberString);
                        value = number * number;
                    }

                    ACLMessage reply = new ACLMessage( ACLMessage.INFORM );
                    String content2 = new Date().toString() + ", " + value;
                    reply.setContent(content2);
                    reply.addReceiver( message.getSender() );
                    send(reply);
                }else{
                    this.block();
                }
            }
        });
    }
}
