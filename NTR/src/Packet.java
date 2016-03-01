
public class Packet {
    private int creation;
    private int beginSend;
    private int endSend;
    private int nbBitsLeft;
    private User user;

    public Packet(User user){
    	this.creation = 0;
    	this.beginSend = 0;
    	this.endSend = 0;
    	this.nbBitsLeft = 0;
    	this.user = user;
    }

    public int getCreation(){
    	return this.creation;
    }

    public int getBaginSend(){
    	return this.beginSend;
    }

    public int getEndSend(){
    	return this.endSend;
    }

    public int getNbBitsLeft(){
    	return this.nbBitsLeft;
    }

    public User getUser(){
    	return this.user;
    }

    public void setCreation(int c){
    	this.creation = c;
    }

    public void setBaginSend(int b){
    	this.beginSend = b;
    }

    public void setEndSend(int e){
   		this.endSend = e;
    }

    public void setNbBitsLeft(int nb){
   		this.nbBitsLeft = nb;
    }

    public void setUser(User u){
   		this.user = u;
    }
}
