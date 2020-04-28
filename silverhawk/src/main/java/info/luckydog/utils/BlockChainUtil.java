package info.luckydog.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;

import javax.annotation.PostConstruct;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BlockChainUtil {

    static List<Block> blockChain = new ArrayList<>();
    static int prefix = 4;
    static String prefixString = new String(new char[prefix]).replace('\0', '0');

//    @PostConstruct
//    public void init()
    static {
        Block newBlock = new Block(
                "The is a New Block.",
                "0",
                new Date().getTime());
        blockChain.add(newBlock);
        String hash = blockChain.get(CollectionUtils.isEmpty(blockChain) ? 0 : blockChain.size() - 1).getHash();
        newBlock.setHash(hash);

        newBlock.mineBlock(prefix);
        assertEquals(newBlock.getHash().substring(0, prefix), prefixString);
    }

    @Test
    public void givenBlockChain_whenNewBlockAdded_thenSuccess() {
        blockChain.forEach(System.out::println);
    }

    @Test
    public void givenBlockChain_whenValidated_thenSuccess() {
        boolean flag = true;
        for (int i = 0; i < blockChain.size(); i++) {
            String previousHash = i == 0 ? "0" : blockChain.get(i - 1).getHash();
            boolean hashFlag = blockChain.get(i).getHash().equals(blockChain.get(i).calculateBlockHash());
            boolean previousHashFlag = previousHash.equals(blockChain.get(i).getPreviousHash());
            boolean prefixStringFlag = blockChain.get(i).getHash().substring(0, prefix).equals(prefixString);
            System.out.println(hashFlag);
            System.out.println(previousHashFlag);
            System.out.println(prefixStringFlag);
            flag = hashFlag && previousHashFlag && prefixStringFlag;
            if (!flag) break;
        }
        assertTrue(flag);
    }

}

@Data
@Slf4j
class Block {
    private String hash;
    private String previousHash;
    private String data;
    private long timeStamp;
    private int nonce;

    public Block(String data, String previousHash, long timeStamp) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = timeStamp;
        this.hash = calculateBlockHash();
    }

    public String calculateBlockHash() {
        String dataToHash = previousHash
                + Long.toString(timeStamp)
                + Integer.toString(nonce)
                + data;
        MessageDigest digest = null;
        byte[] bytes = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            bytes = digest.digest(dataToHash.getBytes(UTF_8));
        } catch (NoSuchAlgorithmException ex) {
            log.info(ex.getMessage());
        }
        StringBuilder buffer = new StringBuilder();
        for (byte b : bytes) {
            buffer.append(String.format("%02x", b));
        }
        return buffer.toString();
    }

    public String mineBlock(int prefix) {
        String prefixString = new String(new char[prefix]).replace('\0', '0');
        while (!hash.substring(0, prefix).equals(prefixString)) {
            nonce++;
            hash = calculateBlockHash();
        }
        return hash;
    }
}
