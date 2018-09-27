package myBlockchain;
import java.util.ArrayList;
import java.util.Date;
import com.google.gson.*;
import org.bouncycastle.*;

public class Blockchain {
	
	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	public static int difficulty = 5;
	
	
	public static void main(String[] args) {
		
		blockchain.add(new Block("First block!", "0"));
		System.out.println("Trying to Mine block 1... ");
		blockchain.get(0).mineBlock(difficulty);
		
		blockchain.add(new Block("Second block!",blockchain.get(blockchain.size()-1).hash)); 
		System.out.println("Mining block 2... ");
		blockchain.get(1).mineBlock(difficulty);
		
		blockchain.add(new Block("Third block!",blockchain.get(blockchain.size()-1).hash));
		System.out.println("Mining block 3... ");
		blockchain.get(2).mineBlock(difficulty);
		
		
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);		
		System.out.println("\nBlockchain is valid: " + isChainValid());
		System.out.println(blockchainJson);	
		
		

	}
	
	public static Boolean isChainValid() {
		Block currentBlock; 
		Block previousBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		
		//loop through blockchain to check hashes:
		for(int i=1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);
			//compare registered hash and calculated hash:
			if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
				System.out.println("Current Hashes not equal");			
				return false;
			}
			//compare previous hash and registered previous hash
			if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
				System.out.println("Previous Hashes not equal");
				return false;
			}
			//check if hash is solved
			if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
				System.out.println("This block hasn't been mined");
				return false;
			}
		}
		return true;
	}
}
