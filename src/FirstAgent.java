
/**
 * @author ${BENMOUSSA-Younes RFIA }
 * @created ${2023}
 * @project ${Communication entre deux Agents sous Jade}
 * Agent 1
 */
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.Date;
import java.util.Scanner;
public class FirstAgent extends Agent {
    protected void setup(){
                Scanner scanner = new Scanner(System.in);
                System.out.print("Agent01 ==> Enter an integer: ");
                int number = scanner.nextInt();
                // send message
                ACLMessage message  = new ACLMessage(ACLMessage.INFORM);
                message.addReceiver(new AID("secondagent", AID.ISLOCALNAME));
                String content = new Date().toString() + ", " + number;
                message.setContent(content);
                this.send(message);
               //addBehaviour();
                addBehaviour(new CyclicBehaviour() {
                    @Override
                    public void action() {
                    ACLMessage message = blockingReceive();
                    if(message != null){
                          System.out.println("Agent 1 a reçu le message : " + message.getContent());
                    }else{
                        this.block();
                }
            }
        });
    }
}
