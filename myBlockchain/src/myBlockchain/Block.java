package myBlockchain;

import java.util.Date;
import java.security.MessageDigest;

public class Block {
	public String hash;
	public String previousHash;
	private long timeStamp; //as number of milliseconds since 1/1/1970.
	private String data; //our data will be a simple message.
	private int nonce;
	
	
	public Block(String data, String previousHash) {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash();
		
	}
	
	public String calculateHash() {
		String calculatedhash = StringUtil.applySha256(
				previousHash + 
				Long.toString(timeStamp) + 
				data +
				Integer.toString(nonce));
		return calculatedhash;
	}
	
	public void mineBlock(int difficulty) {
		String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0" 
		while(!hash.substring( 0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
		}
		System.out.println("Block has been Mined! : " + hash);
	}

}

